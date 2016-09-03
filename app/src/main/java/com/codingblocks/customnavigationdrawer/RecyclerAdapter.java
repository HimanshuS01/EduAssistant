package com.codingblocks.customnavigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HIman$hu on 9/3/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    String[] name = {"Androidwarriors", "Stackoverflow", "Developer Android", "AndroidHive",
            "Slidenerd", "TheNewBoston", "Truiton", "HmkCode", "JavaTpoint", "Javapeper"};

    String[] subText = {"aaaaaaaaaaaaa", "bbbbbbbbbb", "cccccccc", "eeeeeeeee",
            "fffffff", "ggggggggg", "hhhhhhhhhh", "iiiiiiiii", "jjjjjjjj", "kkkkkkk"};

    int[] image = {R.drawable.analyse, android.R.drawable.alert_dark_frame, android.R.drawable.arrow_down_float,
            android.R.drawable.arrow_up_float, android.R.drawable.bottom_bar, android.R.drawable.btn_dialog
            , android.R.drawable.toast_frame, android.R.drawable.btn_dropdown, android.R.drawable.btn_plus
            , android.R.drawable.btn_minus};
    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        holder.tv1.setText(name[position]);
        holder.tv2.setText(subText[position]);
        holder.imageView.setImageResource(image[position]);
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);

        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "subText is:" + subText[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder vholder = (RecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

            Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public int getItemCount() {
        return name.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv1, tv2;
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.list_title);
            tv2 = (TextView) itemView.findViewById(R.id.list_desc);
            imageView = (ImageView) itemView.findViewById(R.id.list_avatar);

        }
    }
}
