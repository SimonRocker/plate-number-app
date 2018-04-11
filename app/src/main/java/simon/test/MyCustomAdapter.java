package simon.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 09.10.2017.
 */

    public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
        private ArrayList<String> list = new ArrayList<String>();
        private Context context;
        private SharedPreferences speicher, counter;
        private SharedPreferences.Editor editor, editor2;
        private int i;
        private TextView textview;

        public MyCustomAdapter(ArrayList<String> list, Context context) {
            this.list = list;
            this.context = context;
            this.speicher = context.getSharedPreferences("Data",0);
            this.counter = context.getSharedPreferences("Counter", 0);
            editor = speicher.edit();
            editor2 = counter.edit();
            textview = (TextView) ((Verlauf)context).findViewById(R.id.empty);
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.custom_list_layout, null);
            }

            TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
            listItemText.setText(list.get(position));

            final Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

            deleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    editor.clear();
                    editor.commit();
                    i = 0;
                    for (String str: list
                         ) {
                        editor.putString(String.valueOf(i),str);
                        editor.commit();
                        i++;
                    }
                    if(i==0){
                        textview.setVisibility(View.VISIBLE);
                    }
                    editor2.clear();
                    editor2.commit();
                    editor2.putInt("counter", i);
                    editor2.commit();
                    notifyDataSetChanged();

                }
            });

            return view;
        }

    }

