package com.example.vincenttieng.weather;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;


class BeersAdapter extends RecyclerView.Adapter<BeersAdapter.BeerHolder> {

    private JSONArray beers;


    public BeersAdapter(JSONArray beers){
        this.beers = beers;
    }


    @Override
    public BeerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_beer_element,parent,false);
        return new BeerHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerHolder holder, int position) {


        try {
            JSONObject obj = beers.getJSONObject(position);
            int lenght = obj.length();
            Log.d("tag",Integer.toString(lenght));
            holder.display(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return beers.length();
    }

    public void setNewBeer(JSONArray beers){
        this.beers = beers;
        notifyDataSetChanged();
    }

    public class BeerHolder extends ViewHolder {
        private final TextView name;
        private final TextView creation;
        private final TextView year;

        public BeerHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);
            creation = (TextView) itemView.findViewById(R.id.creation);
            year = (TextView) itemView.findViewById(R.id.year);
        }


        public void display(JSONObject obj) {
            try {

                name.setText(obj.getString("name"));
                creation.setText(obj.getString("created_at"));
                year.setText(obj.getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}


