package simon.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

/**
 * Created by admin on 13.10.2017.
 */

public class DBEinstellungen extends AppCompatActivity {
    public static boolean history_enabled,maps_enabled/*, input_caps*/;
    private String zoom;
    private DbSource dataSource;
    public static int maps_zoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        dataSource = new DbSource(this);
        dataSource.open();
        maps_zoom = Integer.valueOf(dataSource.getElement("maps_zoom").getResult());
        if(maps_zoom==5) {
            if( Locale.getDefault().getDisplayLanguage()=="de") {
                zoom = "Deutschland";
            } else {
                zoom = "Germany";
            }
        } else if(maps_zoom==1){
            if( Locale.getDefault().getDisplayLanguage()=="de") {
                zoom = "Ganze Welt";
            } else {
                zoom = "World";
            }
        } else{
            if( Locale.getDefault().getDisplayLanguage()=="de") {
                zoom = "Stadt";
            } else {
                zoom = "City";
            }
        }

        maps_enabled = dataSource.getElement("maps_enabled").getResult().equals("1");

        history_enabled = dataSource.getElement("history_enabled").getResult().equals("1");

        //input_caps = dataSource.getElement("input_caps").getResult().equals("1");


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.zoom_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                dataSource.open();
                zoom = parent.getItemAtPosition(position).toString();
                if(zoom.equals("Deutschland") || zoom.equals("Germany")){
                    maps_zoom=5;
                } else if(zoom.equals("Ganze Welt") || zoom.equals("World")){
                    maps_zoom=1;
                } else {
                    maps_zoom=10;
                }
                dataSource.update(dataSource.getElement("maps_zoom"),String.valueOf(maps_zoom), -1);
                dataSource.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                maps_zoom = 5;
            }

        });
        int position=0;
        if(maps_zoom==5){
            position = 1;
        } else if (maps_zoom==10){
            position = 2;
        }
        spinner.setSelection(position);

        final Switch history_switch = (Switch) findViewById(R.id.history_switch);
        history_switch.setChecked(history_enabled);
        history_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                history_enabled=isChecked;
                int i;
                if(history_enabled){
                    i = 1;
                } else {
                    i = 0;
                }
                dataSource.open();
                dataSource.update(dataSource.getElement("history_enabled"),String.valueOf(i), -1);
                dataSource.close();
            }
        });

        Switch maps_switch = (Switch)findViewById(R.id.maps_switch);
        maps_switch.setChecked(maps_enabled);
        maps_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                maps_enabled=isChecked;
                int i;
                if(maps_enabled){
                    i = 1;
                } else {
                    i = 0;
                }
                dataSource.open();
                dataSource.update(dataSource.getElement("maps_enabled"),String.valueOf(i), -1);
                dataSource.close();
            }
        });

     /*   Switch input_switch = (Switch)findViewById(R.id.input_switch);
        input_switch.setChecked(input_caps);
        input_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                input_caps=isChecked;
                int i;
                if(input_caps){
                    i = 1;
                } else {
                    i = 0;
                }
                dataSource.open();
                dataSource.update(dataSource.getElement("input_caps"),String.valueOf(i), -1);
                dataSource.close();
            }
        });*/

        dataSource.close();
    }
}
