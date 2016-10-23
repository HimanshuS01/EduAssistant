package com.codingblocks.ChatBot_And_InterestRanker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codingblocks.customnavigationdrawer.R;

import java.util.List;

/**
 * Created by HIman$hu on 9/3/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<String> student_names_list;

    public RecyclerAdapter(Context context,List<String> student_names_list) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.student_names_list=student_names_list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.tv1.setText(student_names_list.get(position));
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.tv1.getText().toString(), Toast.LENGTH_SHORT).show();
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
        return student_names_list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.student_name_text_view);
            imageView = (ImageView) itemView.findViewById(R.id.list_avatar);
        }
    }
}
