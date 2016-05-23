package es.pue.eventos.presentationlayer.activities;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import es.pue.eventos.R;
import es.pue.eventos.model.businesslayer.entities.Posicion;
import es.pue.eventos.presentationlayer.androidextends.application.PueAndroidApplication;

@SuppressWarnings("MissingPermission")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private PueAndroidApplication application;
    private boolean mapActive=false;
    private UpdateTask updateTask;

    public boolean getMapActive(){
        return mapActive;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        application=(PueAndroidApplication)this.getApplication();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapActive=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapActive=true;

        if( mMap!=null) {
            if (!(updateTask != null && updateTask.getStatus() == AsyncTask.Status.RUNNING)) {
                updateTask = new UpdateTask(mMap, application, this);
                updateTask.execute();
            }
        }


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        updateTask=new UpdateTask(mMap,application,this);
        updateTask.execute();
        mMap.setMyLocationEnabled(true);
    }
}

class UpdateTask extends AsyncTask<Object,Object,Boolean>{

    private GoogleMap mMap;
    private PueAndroidApplication application;
    private MapsActivity activity;


    public UpdateTask(GoogleMap mMap, PueAndroidApplication application,MapsActivity activity){
        this.mMap=mMap;
        this.application=application;
        this.activity=activity;
    }

    private void updateMap(){
        if(application!=null
                &&
                application.getAsistenciaActual()!=null
                &&
                application.getAsistenciaActual().getRecorrido()!=null){

            PolylineOptions polyLine=new PolylineOptions();

            for(Posicion e:application.getAsistenciaActual().getRecorrido()) {
                LatLng pos=new LatLng(e.getLatitud(),e.getLongitud());
                mMap.addMarker(new MarkerOptions()
                        .position(pos)
                );
                Log.i("MapsActivity","Adding marker");
                polyLine.add(pos);
            }
            polyLine.width(5);
            polyLine.color(Color.RED);
            mMap.addPolyline(polyLine);

            if(!application.getAsistenciaActual().getRecorrido().isEmpty()){
                int lastPos=application.getAsistenciaActual().getRecorrido().size()-1;
                LatLngBounds bounds=new LatLngBounds.Builder().include(
                        new LatLng(
                                application.getAsistenciaActual().getRecorrido().get(0).getLatitud(),
                                application.getAsistenciaActual().getRecorrido().get(0).getLongitud()
                        )).include(
                        new LatLng(
                                application.getAsistenciaActual().getRecorrido().get(lastPos).getLatitud(),
                                application.getAsistenciaActual().getRecorrido().get(lastPos).getLongitud()
                        )).build();

                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(
                        bounds,
                        200
                ));
                Log.i("MapsActivity","Moving camera");
            }

        }
    }

    @Override
    protected Boolean doInBackground(Object... params) {

        while(activity.getMapActive()){
            publishProgress();
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){}
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        updateMap();
    }
}