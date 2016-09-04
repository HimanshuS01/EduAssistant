package com.codingblocks.customnavigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

/**
 * Created by HIman$hu on 9/4/2016.
 */
public class ExpandableTvRecyclerAdapter extends RecyclerView.Adapter<ExpandableTvRecyclerAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    CourseDescription courseDescription;

    public ExpandableTvRecyclerAdapter(Context context,CourseDescription courseDescription) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.courseDescription=courseDescription;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.couses_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

//        holder.expandableTextView.setText("jbdjdhfjdfjdbfhdbfjdbfhdbhgfbdhbhfbhhgshdbhsdhs");
         holder.expandableTextView.setText(courseDescription.obj.toString()+"hbhbhbhvhgvghvmhvbv hvhvjhbnv bghvvhvhbvghjgv");//+"\n"
//                +courseDescription.obj.get(position).getDesc().toString());
//        holder.expandableTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, holder.tv1.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
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
//        return 0;
        return courseDescription.obj.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ExpandableTextView expandableTextView;
        TextView tv1;
        ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            expandableTextView = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view);

        }
    }

}




