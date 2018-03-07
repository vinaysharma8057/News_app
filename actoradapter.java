package com.example.vinaysharma.n;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vinay sharma on 28-01-2018.
 */

class Actoradapter extends RecyclerView.Adapter<Actoradapter.Itemviewholder> {

    private Context ctx;
    private ArrayList<Data> dataList;


    public Actoradapter(Context ctx, ArrayList<Data> dataList) {

        this.ctx = ctx;
        this.dataList = dataList;
    }

    @Override
    public Itemviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.list, null);
        Itemviewholder holder = new Itemviewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Itemviewholder holder, int position) {

        final Data obj = dataList.get(position);
        holder.text.setText(obj.gettext());
        Picasso.with(ctx)
                .load(obj.getName())
                .resize(100, 65)
                .into(holder.image);
        holder.relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, ArticleActivity.class);
                i.putExtra("articleurl", obj.getinfo());
                ctx.startActivity(i);
            }
        });
          holder.button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final PopupMenu popup = new PopupMenu(ctx, v);
                  MenuInflater inflater = popup.getMenuInflater();
                  inflater.inflate(R.menu.popupmenu, popup.getMenu());
                  popup.show();
                  new PopupMenu.OnDismissListener() {
                      @Override
                      public void onDismiss(PopupMenu popupMenu) {
                          popupMenu.dismiss();
                      }
                  };
              }
          });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Itemviewholder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView text;
        public RelativeLayout relativelayout;
         FloatingActionButton button;
        public Itemviewholder(View itemview) {
            super(itemview);

            image = (ImageView) itemview.findViewById(R.id.imageView);
            text = (TextView) itemview.findViewById(R.id.textView);
            relativelayout = (RelativeLayout) itemview.findViewById(R.id.relativelayout);
             button=(FloatingActionButton)itemview.findViewById(R.id.floating);
        }
    }

    public void setfilter(ArrayList<Data> newlist) {
        dataList = new ArrayList<>();
        dataList.addAll(newlist);
        notifyDataSetChanged();
    }
}


