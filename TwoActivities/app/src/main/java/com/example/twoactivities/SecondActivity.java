package com.example.twoactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twoactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY= "com.example.android.twoactivities.extra.REPLY";
    private EditText mReply;
    private TextView mRecivedMessage;
    private TextView mRecieveHeader;
    ActivitySecondBinding a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        a = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(a.getRoot());
        mReply= a.editTextSecond;
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mRecieveHeader = a.textHeader;
        mRecivedMessage = a.textMessage;
        mRecivedMessage.setText(message);
        if (savedInstanceState!=null){
            boolean isVisible= savedInstanceState.getBoolean("reply_visible");
            if (isVisible){
                mRecieveHeader.setVisibility(View.VISIBLE);
              mRecivedMessage.setText(savedInstanceState.getString("replyText"));
                mRecivedMessage.setVisibility(View.VISIBLE);
            }
        }

        a.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnReply();

            }
        });

    }
    public void  returnReply(){
        String reply = mReply.getText().toString();
        if(reply.length() ==0)
            Toast.makeText(this,"Cannot send Empty Reply",Toast.LENGTH_SHORT).show();
        Intent replyIntent =new Intent();
        replyIntent.putExtra(EXTRA_REPLY,reply);
        setResult(RESULT_OK,replyIntent);
        Toast.makeText(this, "Reply Sent",Toast.LENGTH_SHORT).show();
        finish();

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mRecivedMessage.getVisibility()==View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("replyText", mRecivedMessage.getText().toString());
        }
        }
    }
}