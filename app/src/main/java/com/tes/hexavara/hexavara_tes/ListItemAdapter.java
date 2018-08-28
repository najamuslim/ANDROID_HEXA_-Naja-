package com.tes.hexavara.hexavara_tes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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
        final ListItem listItem = listItems.get(position);

        holder.itemHead.setText(listItem.getHead());
        holder.itemDesc.setText(listItem.getDesc());

        Glide.with(context)
                .load(listItem.getPhoto())
                .override(120, 100)
                .crossFade()
                .into(holder.itemPhoto);

        holder.itemLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(context, ItemActivity.class);
                i1.putExtra(ItemActivity.EXTRA_ID,listItem.getID());
                i1.putExtra(ItemActivity.EXTRA_NAME,listItem.getHead());
                i1.putExtra(ItemActivity.EXTRA_SALARY,listItem.getDesc());
                context.startActivity(i1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView itemHead;
        TextView itemDesc;
        ImageView itemPhoto;
        LinearLayout itemLinear;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemHead = (TextView)itemView.findViewById(R.id.clientName);
            itemDesc = (TextView)itemView.findViewById(R.id.clientSalary);
            itemPhoto = (ImageView)itemView.findViewById(R.id.client_img);
            itemLinear = (LinearLayout)itemView.findViewById(R.id.itemrv);
        }
    }
}
