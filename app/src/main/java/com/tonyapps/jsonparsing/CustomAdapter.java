package com.tonyapps.jsonparsing;

/**
 * Created by Eswar Varma on 05-12-2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{
    private Context context;
    private String[] items,subitems;
    private TextView tv1,tv2;
    Integer[] img;
    public CustomAdapter(Context context, String[] names, Integer[] pics, String[] subnames) {
        super(context, R.layout.custom,names);
        this.items=names;
        this.img=pics;
        this.subitems=subnames;
        this.context=context;
    }
    //add the xml file
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView=inflater.inflate(R.layout.custom, null);
        ImageView imageView=(ImageView)rootView.findViewById(R.id.imageview1);
         tv1=(TextView)rootView.findViewById(R.id.maintext);
        tv2=(TextView)rootView.findViewById(R.id.subtext);
        //show the values
        imageView.setBackgroundResource(img[position]);
        tv1.setText(items[position]);
        tv2.setText(subitems[position]);
        return rootView;
    }

}
