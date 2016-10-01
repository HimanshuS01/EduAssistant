package com.codingblocks.customnavigationdrawer.ChatbotFiles;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codingblocks.customnavigationdrawer.ApiInterface;
import com.codingblocks.customnavigationdrawer.Networking.ApiClientChatbot;
import com.codingblocks.customnavigationdrawer.R;
import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    private EditText messageET;
    private ListView messagesContainer;
    private Button sendBtn;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;

    CatLoadingView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);

        super.onCreate(savedInstanceState);
        mView = new CatLoadingView();

        setContentView(R.layout.activity_chatbot);
        initControls();
    }

    private void initControls() {
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (Button) findViewById(R.id.chatSendButton);

        //TextView meLabel = (TextView) findViewById(R.id.meLbl);
       // TextView companionLabel = (TextView) findViewById(R.id.friendLabel);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        //companionLabel.setText("My Buddy");// Hard Coded
        loadDummyHistory();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(false);

                messageET.setText(""); //to make edittext aside send button empty

                displayMessage(chatMessage);
                networkcall(chatMessage.getMessage());
                //to actually display..sending chat mnessage
//                String server_text=networkcall(chatMessage.getMessage());
//                ChatMessage chatMessagereply = new ChatMessage();
//                chatMessage.setId(122);
//                chatMessage.setMessage(server_text);
//                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
//                chatMessage.setMe(true);
                //displayMessage(chatMessage);

            }
        });
    }
//this mehtod will call api link and return String...this will run on worker thread...whle this is happening show progress dialog.after this is finished call
    private void networkcall(String message) {
        String text_result="";
        //by network call take the sstring

        Log.e("sachin",message);

        Call<Question> call= ApiClientChatbot.getInterface().getAnswer(message);
        mView.show(getSupportFragmentManager(), "");
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if(response.isSuccessful())
                set_server_message(response.body().result.toString());
                else
                    Log.e("sachin","not succ");
                mView.dismiss();
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {

                mView.dismiss();
                Log.e("sachin","fail",t);

            }
        });

    }

    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    private void loadDummyHistory(){

        chatHistory = new ArrayList<ChatMessage>();

        ChatMessage msg = new ChatMessage();
        msg.setId(1);
        msg.setMe(true);
        msg.setMessage("Hi");
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg);
        ChatMessage msg1 = new ChatMessage();
        msg1.setId(2);
        msg1.setMe(true);
        msg1.setMessage("This is your Chatbot.Ask me any question!!");
        msg1.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg1);

        adapter = new ChatAdapter(ChatActivity.this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

        for(int i=0; i<chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }
    }
    public void set_server_message(String text)
    {
        mView.dismiss();
        if(text.substring(text.length()-3).equals("vid") && text.contains("youtube"))
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
            startActivity(intent);
        }
     else if(text.substring(text.length()-3).equals("jpg"))
        {
            RelativeLayout layout=(RelativeLayout)findViewById(R.id.container);
            ImageView imageView=new ImageView(ChatActivity.this);
            imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
            imageView.setMaxHeight(20);
            imageView.setMaxWidth(20);
            Picasso.with(ChatActivity.this).load(text).into(imageView);
            layout.addView(imageView);
        }
        else
        {

            ChatMessage msg = new ChatMessage();
            msg.setId(1);
            msg.setMe(true);
            msg.setMessage(text);
            msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
            displayMessage(msg); // might have to add in a Arraylist as well
        }
    }
}