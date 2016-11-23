package com.ollum.mazecape.Adapters;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.R;

public class SpecialTilesAdapter extends BaseAdapter {
    private Context context;

    public SpecialTilesAdapter(Context context, int center) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return GameFragment.specialTiles.size();
    }

    @Override
    public Integer getItem(int i) {
        return GameFragment.specialTiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return GameFragment.specialTiles.get(i);
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

        //holder.imageView.setImageResource(GameFragment.normalTiles.get(position));
        holder.imageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), GameFragment.specialTiles.get(position), null));
        //holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), GameFragment.normalTiles.get(position)));

        return row;
    }

    class ViewHolder {
        ImageView imageView;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.picture);
        }
    }
}