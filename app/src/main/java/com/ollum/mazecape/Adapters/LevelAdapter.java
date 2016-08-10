package com.ollum.mazecape.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.R;

public class LevelAdapter extends BaseAdapter {
    private Context context;

    public LevelAdapter(Context context, int center) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return GameFragment.items.size();
    }

    @Override
    public Integer getItem(int i) {
        return GameFragment.items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return GameFragment.items.get(i);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.imageView.setImageResource(GameFragment.items.get(position));


        return row;
    }

    class ViewHolder {
        ImageView imageView;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.picture);
        }
    }
}