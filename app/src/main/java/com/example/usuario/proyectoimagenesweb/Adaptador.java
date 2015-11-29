package com.example.usuario.proyectoimagenesweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class Adaptador extends ArrayAdapter<String> {
    private Context ctx;
    private int res;
    private LayoutInflater i;
    private List<String> array;

    static class ViewHolder {
        TextView tv;
    }

    public Adaptador(Context context, int res, List<String> objects) {
        super(context, res, objects);
        this.ctx = context;
        this.res = res;
        this.array = objects;
        i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();

        if (convertView == null) {
            convertView = i.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            vh.tv = tv;

            convertView.setTag(vh);
        } else
            vh = (ViewHolder) convertView.getTag();
    if(array.size()!=0)
        vh.tv.setText(array.get(position));
    else
        vh.tv.setText("No imgs");
        return convertView;
    }
}
