package simon.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11.10.2017.
 */

public class DBMyCustomAdapter extends BaseAdapter implements ListAdapter {
    private List<DbElement> dblist = new ArrayList<DbElement>();
    private Context context;
    private TextView textView;
    private DbSource dataSource;

    public DBMyCustomAdapter(List<DbElement> list, Context context) {
        this.dblist = list;
        this.context = context;
        textView = (TextView) ((DBVerlauf)context).findViewById(R.id.empty);
        dataSource = new DbSource(context.getApplicationContext());
    }
    @Override
    public int getCount() {
        return dblist.size();
    }

    @Override
    public Object getItem(int pos) {
        return dblist.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    public void clear() {
        Log.d("Simon", "Länge: " + getCount());
        dataSource.open();
        for(int i = 0; i< getCount(); i++) {
            dataSource.update(dblist.get(i),-1); //updated richtiges Element, nicht nur löschen des Elementes im Verlauf
        }
        dataSource.close();
        dblist.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_layout, null);
        }


        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(dblist.get(position).toString());

        final Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dataSource.open();
                dataSource.update(dblist.get(position),-1); //updated richtiges Element, nicht nur löschen des Elementes im Verlauf
                dataSource.close();
                dblist.remove(position);
                notifyDataSetChanged();

            }
        });

        return view;
    }
}
