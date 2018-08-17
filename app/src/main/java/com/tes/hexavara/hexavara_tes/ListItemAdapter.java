package com.tes.hexavara.hexavara_tes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.CategoryViewHolder> {
    private Context context;
    private List<ListItem> listItems;

    public ListItemAdapter(List<ListItem> listItems, Context context){
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_client, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.itHead.setText(listItem.getHead());
        holder.itDesc.setText(listItem.getDesc());

        Glide.with(context)
                .load(listItem.getPhoto())
                .override(120, 100)
                .crossFade()
                .into(holder.itPhoto);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView itHead;
        TextView itDesc;
        ImageView itPhoto;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itHead = (TextView)itemView.findViewById(R.id.clientName);
            itDesc = (TextView)itemView.findViewById(R.id.clientSalary);
            itPhoto = (ImageView)itemView.findViewById(R.id.client_img);
        }
    }
}
