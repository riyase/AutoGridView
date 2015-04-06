package nl.obduro.AutoGridView;

import android.app.Activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends Activity {

    AutoGridView agvPlaces;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {

        agvPlaces = (AutoGridView)findViewById(R.id.agvPlaces);
        adapter = new GridAdapter(this,getPlaces());
        agvPlaces.setAdapter(adapter);
    }

    private ArrayList<HashMap<String,Object>> getPlaces() {

        String names[] = {
                "Green Tree",
                "Yellow Field",
                "Green Sky",
                "High Mountains",
                "Icy Place",

        };
        String places[] = {
                "Somewhere in Iceland",
                "Somewhere in Some place",
                "Himalaya",
                "Somewhere in Australia",
                "SOmewhere in Greenland",

        };
        String counties[] = {
                "Iceland",
                "Nepal",
                "India",
                "Australia",
                "Denmark",

        };

        int pic[]={
                R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3,
                R.drawable.a4,
                R.drawable.a5,

        };
        ArrayList<HashMap<String,Object>> alPlaces = new ArrayList<>();

        for(int i=0;i<5;i++) {
            HashMap<String,Object> place = new HashMap<>();
            place.put(Key.PIC,pic[i]);
            place.put(Key.NAME,names[i]);
            place.put(Key.PLACE,places[i]);
            place.put(Key.COUNTRY,counties[i]);
            alPlaces.add(place);
        }
        return alPlaces;
    }

}
