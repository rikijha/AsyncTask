package com.example.riki.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by riki on 4/29/2018.
 */

public class MyAsyncTask extends AsyncTask<Void,Integer,String> {
    Context ctx;
    ProgressDialog pd;

    public MyAsyncTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
       pd =new ProgressDialog(ctx);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle("Downloading...");
        pd.setMessage("Please wait...");
        pd.setMax(10);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                cancel(true);
            }
        });
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(7000);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "failure";
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
       int myval=values[0];
        pd.setProgress(myval);
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }
}
