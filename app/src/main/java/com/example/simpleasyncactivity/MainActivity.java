package com.example.simpleasyncactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mProgressTextView;
    private ProgressBar mProgressBar;
    private static final String TEXT_PROGRESS = "CurrentSleepDuration";
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        //mProgressTextView = findViewById(R.id.textViewResult);
        if(savedInstanceState !=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressTextView.setText(savedInstanceState.getString(TEXT_PROGRESS));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        new SimpleAsyncTask(mTextView, mProgressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        //outState.putString(TEXT_PROGRESS, mProgressTextView.getText().toString());
    }


}
