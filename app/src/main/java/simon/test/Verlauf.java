package simon.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Verlauf extends AppCompatActivity {

    private SharedPreferences speicher, counter;
    private SharedPreferences.Editor editor, editor2;
    private int used = 0;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verlauf);

        speicher = getApplicationContext().getSharedPreferences("Data", 0);
        editor = speicher.edit();
        counter = getApplicationContext().getSharedPreferences("Counter", 0);
        editor2 = counter.edit();


        ArrayList<String> listItems = new ArrayList<String>();
        MyCustomAdapter adapter = new MyCustomAdapter(listItems, this);
        list = (ListView) findViewById(R.id.list);
        TextView textView = (TextView) findViewById(R.id.empty);

        if(counter.getInt("counter",0)==0){
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
            for (int o = 0; o < counter.getInt("counter", 0); o++) {

                if(speicher.getString(String.valueOf(o), null)!=null) {
                    listItems.add(0, speicher.getString(String.valueOf(o), null));

                }
            }
        }
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String str = String.valueOf(list.getItemAtPosition(position));
            if(str.contains(" ")) {
                str = str.substring(0, str.indexOf(" "));
            }
            Intent intent = new Intent(Verlauf.this, Suche.class);
            intent.putExtra("EingabeString",str);
            startActivity(intent);
                //TODO: String cutten und erfolgreich(!) in Activity "Suche" eingeben
        }
    });
        adapter.notifyDataSetChanged();
    }




    @Override
    public void onStart() {
        super.onStart();

    }
    public void onStop() {
        super.onStop();
    }


}
