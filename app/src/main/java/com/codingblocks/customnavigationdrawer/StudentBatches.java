package com.codingblocks.customnavigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.codingblocks.customnavigationdrawer.DBMS.BatchTable;
import com.codingblocks.customnavigationdrawer.DBMS.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HIman$hu on 8/6/2016.
 */
public class StudentBatches extends Fragment {

    String m_Text;
    ListView batches_list_view;
    List<String> dataList;
    ArrayAdapter<String> adapter;
    BatchModel batchModel;
    static int batch_count=0;

    public StudentBatches() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db=MyDatabase.getInstance(getActivity()).getReadableDatabase();
//        db.beginTransaction();
        ArrayList<BatchModel> batches_list=BatchTable.getByArg(db);
        Log.i("database",batches_list.size()+"");
//        db.setTransactionSuccessful();
//        db.endTransaction();
        if(batches_list.size()==0){

        }
        else{
            dataList=new ArrayList<>();
            for(int i=0;i<batches_list.size();i++){
                dataList.add(batches_list.get(i).getBatch_name());
            }
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
            batches_list_view.setAdapter(adapter);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        batches_list_view = (ListView) rootView.findViewById(R.id.Batches_List_View);
        dataList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setBackgroundDrawable(getResources().getDrawable(R.drawable.plus_icon));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add new batch");

                // Set up the input
                final EditText input = new EditText(getActivity());

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        dataList.add(m_Text);
                        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
                        batches_list_view.setAdapter(adapter);//After this step you should be able to see the data in your list view.
                        batchModel=new BatchModel(m_Text);
                        //batch_count++;
                        SQLiteDatabase db= MyDatabase.getInstance(getActivity()).getWritableDatabase();
//                        db.beginTransaction();
                        BatchTable.save(db,batchModel);
//                        db.setTransactionSuccessful();
//                        db.endTransaction();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        batches_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete it?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataList.remove(dataList.get(position));
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return true;
            }
        });

        batches_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), StudentListRecyclerView.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
