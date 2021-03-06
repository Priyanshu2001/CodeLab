package com.example.twoactivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST= 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    ActivityMainBinding b;

    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    private EditText mMessageEditText;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSecondActivity();
            }
        });
        mMessageEditText =b.editTextMain;
        mReplyHeadTextView= b.textHeaderReply;
        mReplyTextView= b.textMessageReply;
        if (savedInstanceState!=null){
            boolean isVisible= savedInstanceState.getBoolean("reply_visible");
            if (isVisible){
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("replyText"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== TEXT_REQUEST){
            if (resultCode==RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void  launchSecondActivity(){
        Log.d(LOG_TAG,"Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String SendMessage = mMessageEditText.getText().toString();
        if(SendMessage.length() ==0)
            Toast.makeText(this,"Cannot send Empty Message",Toast.LENGTH_SHORT).show();
        intent.putExtra(EXTRA_MESSAGE,SendMessage);
        Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
        startActivityForResult(intent,TEXT_REQUEST);


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mReplyTextView.getVisibility()==View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("replyText", mReplyTextView.getText().toString());
        }
    }
}