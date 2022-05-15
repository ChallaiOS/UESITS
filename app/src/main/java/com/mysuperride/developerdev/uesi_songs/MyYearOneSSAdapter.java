package com.mysuperride.developerdev.uesi_songs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyYearOneSSAdapter  extends RecyclerView.Adapter<MyYearOneSSAdapter.MyViewHolder> implements Serializable {

    Context context;
    List<itemModel> arrayList;

    public MyYearOneSSAdapter(Context context, List<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_item_list,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final itemModel itemmodel = arrayList.get(i);
        myViewHolder.list_day.setText(itemmodel.getList_day());
        myViewHolder.local_title.setText(itemmodel.getLocal_title());
        myViewHolder.list_month.setText(itemmodel.getList_month());
        myViewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, YearOneContentActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("user", itemmodel);

                intent.putExtras(b); //pass bundle to your intent
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView local_id,local_title,list_day,list_month;
        RelativeLayout relative;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //local_id = itemView.findViewById(R.id.local_id);
            list_day = itemView.findViewById(R.id.list_day);
            local_title = itemView.findViewById(R.id.local_title);
            relative = itemView.findViewById(R.id.relative);
            list_month = itemView.findViewById(R.id.local_month);


        }
    }

    public void setSearchOperation(List<itemModel> newList)
    {
        arrayList = new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}

