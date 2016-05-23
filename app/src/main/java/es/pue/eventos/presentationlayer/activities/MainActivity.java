package es.pue.eventos.presentationlayer.activities;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.PortUnreachableException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import es.pue.eventos.R;
import es.pue.eventos.model.businesslayer.entities.Evento;
import es.pue.eventos.model.servicelayer.manager.ServiceManager;
import es.pue.eventos.presentationlayer.androidextends.application.PueAndroidApplication;
import es.pue.eventos.presentationlayer.services.GeoPositionService;

public class MainActivity extends AppCompatActivity  implements TextWatcher, View.OnClickListener {

    private PueAndroidApplication app;
    private ServiceManager serviceManager;
    private EditText main_etDorsal;
    private ImageButton main_ibStartStop;
    private Intent intentGeoPositionService;


    private Timer timer=new Timer();
    private final long DELAY=1000; //milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app=(PueAndroidApplication) getApplication();
        serviceManager=app.getServiceManager();
        main_etDorsal=(EditText) findViewById(R.id.main_etDorsal);
        main_etDorsal.addTextChangedListener(this);

        main_ibStartStop=(ImageButton)findViewById(R.id.main_ibStartStop);
        main_ibStartStop.setOnClickListener(this);
        try {
        app.setEventos(serviceManager.getEventoService().createInitialLocalEventos());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_bt_maps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(getCurrentFocus()==main_etDorsal) {
            timer.cancel();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    new EventoAsincrono().execute(app,
                            main_etDorsal.getText().toString(),
                            main_ibStartStop
                    );

                }
            }, DELAY);

        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.main_ibStartStop){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(((ImageButton)v).getDrawable().getConstantState()==
                                getDrawable(R.drawable.start).getConstantState())
                {
                    iniciarAsistencia();
                    ((ImageButton)v).setImageResource(R.drawable.stop);
                }
                else{
                    ((ImageButton)v).setImageResource(R.drawable.start);
                    //Todo Parar la asistencia
                }
            }


        }
    }

    private void iniciarAsistencia(){

        app.setAsistenciaActual(
                serviceManager.getEventoService().
                        addCurrentAsistenciaToEvent(app.getEvento()));

        if(app.getAsistenciaActual()!=null) {
            intentGeoPositionService = new Intent(this, GeoPositionService.class);
            startService(intentGeoPositionService);
        }
    }


}

class EventoAsincrono extends AsyncTask<Object,Object,Evento>{

    private PueAndroidApplication app=null;
    private ImageButton main_ibStartStop=null;

    @Override
    protected Evento doInBackground(Object... params) {

        app= (PueAndroidApplication) params[0];
        String dorsal =(String)params[1];
        main_ibStartStop=(ImageButton)params[2];

        Evento evento=app.getServiceManager().getEventoService().getEventoByDorsal(dorsal);

        app.setEvento(evento);

        return evento;

    }

    @Override
    protected void onPostExecute(Evento evento) {
        super.onPostExecute(evento);

        if(evento!=null){
            main_ibStartStop.setVisibility(View.VISIBLE);
        }

    }


}


