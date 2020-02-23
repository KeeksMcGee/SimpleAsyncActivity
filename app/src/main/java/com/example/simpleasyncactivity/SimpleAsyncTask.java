package com.example.simpleasyncactivity;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void,Integer,String>{

    //Weak references memory leak
    private WeakReference<TextView> mTextView;
   // private WeakReference<TextView> mResultTextView;
    private WeakReference<ProgressBar> mProgressBar;



    SimpleAsyncTask (TextView tv, ProgressBar pBar){
        mTextView = new WeakReference<>(tv);
        //mResultTextView = new WeakReference<>(result);
        mProgressBar = new WeakReference<>(pBar);

    }


    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;
        for (int i =0; i<5; i++ ) {
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100) / 5);
        }
        return "Awake at last after sleeping for " + s + " milliseconds";
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        //mResultTextView.get().setText("Current sleep progress: " + progress[0] + " milliseconds");
        mProgressBar.get().setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result){
        mTextView.get().setText(result);
    }


}
