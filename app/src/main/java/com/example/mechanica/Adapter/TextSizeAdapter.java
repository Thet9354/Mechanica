package com.example.mechanica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mechanica.Model.TextSize;
import com.example.mechanica.R;

import java.util.List;

/********************************************
 *     Created by Thet Pine on 24-June-22.  *
 ********************************************/

public class TextSizeAdapter extends BaseAdapter {

    private final Context context;
    private final List<TextSize> textSizeList;

    public TextSizeAdapter(Context context, List<TextSize> textSizeList) {
        this.context = context;
        this.textSizeList = textSizeList;
    }

    @Override
    public int getCount() {
        return textSizeList != null ? textSizeList.size() :0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_textsize, viewGroup, false);

        TextView txtSize = rootView.findViewById(R.id.txtSize);

        txtSize.setText(textSizeList.get(i).getTextSize());

        return rootView;
    }
}
