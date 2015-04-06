package nl.obduro.AutoGridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;


public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String,Object>> locations;
    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<HashMap<String,Object>> locations) {
        this.context = context;
        this.locations = locations;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 999;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_location,parent,false);
            vh = new ViewHolder();
            vh.ivPicture = (ImageView)convertView.findViewById(R.id.ivPicture);
            vh.tvName = (TextView)convertView.findViewById(R.id.tvName);
            vh.tvPlace = (TextView)convertView.findViewById(R.id.tvPlace);
            vh.tvCountry = (TextView)convertView.findViewById(R.id.tvCountry);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        int drawable = (int) locations.get(position%5).get(Key.PIC);
        vh.ivPicture.setImageResource(drawable);
        vh.tvName.setText(locations.get(position%5).get(Key.NAME)+"");
        vh.tvPlace.setText(locations.get(position%5).get(Key.PLACE)+"");
        vh.tvCountry.setText(locations.get(position%5).get(Key.COUNTRY)+"");

        return convertView;
    }
    private class ViewHolder{

        public ViewHolder(){}

        public ImageView ivPicture;
        public TextView tvName;
        public TextView tvPlace;
        public TextView tvCountry;
    }
}
