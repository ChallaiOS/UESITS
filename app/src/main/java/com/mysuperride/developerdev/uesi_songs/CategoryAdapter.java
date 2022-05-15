package com.mysuperride.developerdev.uesi_songs;
//https://www.youtube.com/watch?v=3czlQvOPosg&t=126s

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    ArrayList<Object> list;
    private static final int CATEGORY_ITEM = 0;
    private static final int HEADER = 1;
    public LayoutInflater inflater;

    public CategoryAdapter(Context context, ArrayList<Object> list) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof CategoryItem) {
            return CATEGORY_ITEM;
        } else {
            return HEADER;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2 ;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            switch (getItemViewType(i)) {

                case CATEGORY_ITEM:
                    view = inflater.inflate(R.layout.catogiry_item_listview, null);
                    break;

                case HEADER:
                    view = inflater.inflate(R.layout.catogiry_listview_header, null);
                    break;
            }
            }


            switch (getItemViewType(i)) {

                case CATEGORY_ITEM:
                    TextView name = (TextView) view.findViewById(R.id.itemlistName);
                    name.setText(((CategoryItem) list.get(i)).getName());
                    break;

                case HEADER:

                    TextView title = (TextView) view.findViewById(R.id.itemlistNameheader);
                    title.setText(((String) list.get(i)));
                    break;
            }

            return view;
        }

    }


