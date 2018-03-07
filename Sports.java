package com.example.vinaysharma.n;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by vinay sharma on 25-01-2018.
 */

public class Sports extends Fragment  implements SearchView.OnQueryTextListener {
    private RecyclerView rv;
    String result;
    ArrayList<Data> list;
    Actoradapter actoradapter;
    ProgressDialog progressDialog;

    public static Sports newInstance() {
        Sports sports = new Sports();
        return sports;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.sports, null);
        progressDialog=new ProgressDialog(getActivity());
        rv = (RecyclerView) rootview.findViewById(R.id.RecyclerviewSport);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<Data>();
        setHasOptionsMenu(true);

        return rootview;
    }

    public void onStart() {
        super.onStart();
        DownloadTask task = new DownloadTask();

        try {
            result = task.execute("https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=23f5adcca2524cdaba37cc99a742eb48").get();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(result);
            String articleInfo = jsonObject.getString("articles");
            JSONArray jsonArray = new JSONArray(articleInfo);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String url = obj.getString("url");
                String title = obj.getString("title");
                String imageurl = obj.getString("urlToImage");
                Data object = new Data();
                object.textinfo(title);
                object.setname(imageurl);
                object.info(url);
                list.add(object);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        actoradapter = new Actoradapter(getActivity(), list);
        rv.setAdapter(actoradapter);

    }


    @Override
    public String toString() {
        return "Sports";
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.commommenu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchview = (SearchView) item.getActionView();
        searchview.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Data> newlist = new ArrayList<Data>();
        for (Data d : list) {
            String name = d.gettext().toLowerCase();
            if (name.contains(newText)) {
                newlist.add(d);

            }


        }

        actoradapter.setfilter(newlist);

        return true;

    }
}