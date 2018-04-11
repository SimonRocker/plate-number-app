package simon.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11.10.2017.
 */

public class DBVerlauf extends AppCompatActivity {

    private ListView list;
    private DbSource dataSource;
    private DBMyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verlauf);
        dataSource = new DbSource(this);


        ArrayList<DbElement> listItems = new ArrayList<DbElement>();
        adapter = new DBMyCustomAdapter(listItems, this);
        list = (ListView) findViewById(R.id.list);

        TextView textView = (TextView) findViewById(R.id.empty);
        list.setEmptyView(textView);
        Button btn = (Button) findViewById(R.id.transparent_button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adapter.clear();
            }

        });
        dataSource.open();
            showHistoryEntries();
        dataSource.close();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = String.valueOf(list.getItemAtPosition(position));
                if (str.contains(" ")) {
                    str = str.substring(0, str.indexOf(" "));
                }
                Intent intent = new Intent(simon.test.DBVerlauf.this, DBSuche.class);
                intent.putExtra("EingabeString", str);
                startActivity(intent);
                //TODO: String cutten (Städte) und erfolgreich(!) in Activity "Suche" eingeben
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(DBVerlauf.this, DBEinstellungen.class);
            startActivity(intent);
        } else if(id == R.id.action_impressum){
            Intent intent = new Intent(DBVerlauf.this, Impressum.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    public void onStop() {
        super.onStop();
    }

    private void showHistoryEntries() {
        List<DbElement> listItems = dataSource.getAllDbElementsOrderByHistoryNumber();
        List<DbElement> listFinal = new ArrayList<DbElement>();
        for(DbElement element: listItems){
            if (element.getHistory_number()!=-1){
                listFinal.add(element);
            }
        }
        adapter = new DBMyCustomAdapter(listFinal, this);
        list.setAdapter(adapter);

    }


}


