package simon.test;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Geocoder;
import android.location.Address;

import java.util.List;
import com.google.android.gms.maps.CameraUpdate;

/**
 *
 * Activity "nach" der Main Activity und des Eingebens des zu suchenden Kennzeichens.
 * Dies wird durch den intent weitergegeben und hier schließlich verarbeitet (SQLite DB).
 *
 */
public class DBSuche extends AppCompatActivity implements OnMapReadyCallback {

    String ausgabe = "";
    private GoogleApiClient client;
    private DbSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suche);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Fragment für Google Maps Einbindung, sehr einfach gehalten
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Der "Return" button, dieser führt zurück zur Mainactivity. Die Suche wird gespeichert.
        Button btn = (Button) findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DBSuche.this, DBMainActivity.class);
                //Diese Flag bereinigt alle anderen Activities außer die, auf die der Intent geht.
                //Dies ist eine sehr einfache Art die "Backnavigation" zu realisieren.
                //onBackPressed(); //Funktionalität des "Back" Buttons in Android. Alternative
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //Hier wird die Benutzereingabe aus dem Intent gezogen.
        String eingabe = "";
        Bundle extras = getIntent().getExtras();
        eingabe = extras.getString("EingabeString");
        //eingabe = extras.getString("EingabeString").replaceAll("\\s+","");
        while(eingabe.startsWith(" ")) {
            eingabe = eingabe.substring(1,eingabe.length());
        }
        while(eingabe.endsWith(" ")) {
           eingabe = eingabe.substring(0, eingabe.length()-1);
        }

        //Textfeld, in welchem nachher die Ausgabe stehen soll.
        TextView textView = (TextView) findViewById(R.id.ausgabe2);
        TextView textView1 = (TextView) findViewById(R.id.ausgabe);

        dataSource = new DbSource(this);
        dataSource.open();

        DbElement element = null;
        try {
             element = dataSource.getElement(eingabe);
            if(element != null) {
                ausgabe = element.getResult();
            } else {
                element = dataSource.getElementResult(eingabe);
                ausgabe = element.getPlate_number() + " - " + element.getResult();
                eingabe = eingabe.substring(0,1) + eingabe.substring(1).toLowerCase();
            }
        }catch(Exception e){

                ausgabe = getString(R.string.notExistingPlate);


        }

        textView1.setText(getString(R.string.result1) + eingabe + getString(R.string.result2));
        textView.setText(ausgabe);
        boolean history_enabled;
        if(dataSource.getElement("history_enabled").getResult().equals("1")) {
            history_enabled = true;
        } else {
            history_enabled = false;
        }
        if(ausgabe!= getString(R.string.notExistingPlate) && history_enabled) {
            DbElement counter = dataSource.getElement("count");
            int i = Integer.valueOf(counter.getResult());
            dataSource.update(element, i);
            i++;
            dataSource.update(counter, String.valueOf(i), -1);
        }
        dataSource.close();
    }

    private Context getContext() {
        return this;
    }


    @Override
    public void onMapReady(GoogleMap map) {
        dataSource.open();
        boolean maps_enabled;
        if(dataSource.getElement("maps_enabled").getResult().equals("1")){
            maps_enabled = true;
        } else {
            maps_enabled = false;
        }
        dataSource.close();
        if(maps_enabled) {
            GoogleMap[] mapsarray = {map};
            new DownloadFilesTask().execute(mapsarray);
        } else {
            if (ausgabe != getString(R.string.notExistingPlate)) {
                TextView textView = (TextView) findViewById(R.id.ausgabe2);
                textView.setText(ausgabe + " " + getString(R.string.maps_deactivated));
            }
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(51.163375, 10.447683), 5);
                map.moveCamera(cameraUpdate);

        }
    }
    private class DownloadFilesTask extends AsyncTask<GoogleMap, Integer, LatLng> {
        private GoogleMap map;
        protected LatLng doInBackground(GoogleMap... map) {
            this.map = map[0];
            LatLng coordinates;
            Geocoder coder = new Geocoder(getContext());
            try {

                List<Address> address = coder.getFromLocationName(ausgabe, 5);
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();
                coordinates = new LatLng(location.getLatitude(), location.getLongitude());
            } catch (Exception e) {
                return null;
            }

            return coordinates;
        }

        protected void onProgressUpdate(Integer... progress) {
            //Ladebalken? Die Ladezeit ist jedoch extrem kurz, sofern man wirklich Internet hat (auch schlechtes genügt) => kein "LIFI"
        }

        protected void onPostExecute(LatLng coordinates) {
            if(coordinates != null) {
                dataSource.open();
                int zoom = Integer.valueOf(dataSource.getElement("maps_zoom").getResult());
                dataSource.close();

                this.map.addMarker(new MarkerOptions()
                        .position(coordinates)
                        .title("Marker"));

                CameraUpdate cameraUpdate;
                if (zoom == 10) {
                    cameraUpdate = CameraUpdateFactory.newLatLngZoom(coordinates, zoom);
                } else {
                    cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(51.163375, 10.447683), zoom);
                }
                this.map.moveCamera(cameraUpdate);
            } else {
                if (ausgabe != getString(R.string.notExistingPlate)) {
                    TextView textView = (TextView) findViewById(R.id.ausgabe2);
                    textView.setText(ausgabe + " " + getString(R.string.unableToFind));
                }
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(51.163375, 10.447683),5);
                map.moveCamera(cameraUpdate);
            }
        }
    }
}



