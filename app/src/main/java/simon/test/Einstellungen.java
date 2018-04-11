package simon.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import static simon.test.R.id.parent;
import static simon.test.R.styleable.View;

/**
 * Created by admin on 09.10.2017.
 */

public class Einstellungen extends AppCompatActivity {
    public static boolean maps_enabled =true, history_enabled = true, input_caps = true;
    public static int maps_zoom = 5;
    private String zoom = "Deutschland";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.zoom_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                zoom = parent.getItemAtPosition(position).toString();
                if(zoom=="Deutschland"){
                    maps_zoom=5;
                } else if(zoom=="Ganze Welt"){
                    maps_zoom=1;
                } else {
                    maps_zoom=10;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                maps_zoom = 5;
            }

        });
        int position=0;
        if(maps_zoom==1){
            position = 1;
        } else if (maps_zoom==10){
            position = 2;
        }
        spinner.setSelection(position);

        Switch history_switch = (Switch) findViewById(R.id.history_switch);
        history_switch.setChecked(history_enabled);
        history_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                history_enabled=isChecked;
            }
        });

        Switch maps_switch = (Switch)findViewById(R.id.maps_switch);
        maps_switch.setChecked(maps_enabled);
        maps_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                maps_enabled=isChecked;
            }
        });



    }
}