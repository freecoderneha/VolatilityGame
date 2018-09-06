package hello.abc.neha.volatilitygame;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InvestorIndian extends AppCompatActivity{

    DownloadManager downloadManager1;
    DownloadManager downloadManager2;
    BroadcastReceiver onComplete;

    User user = SharedPrefManager.getInstance(this).getUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investor_indian);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        final NestedScrollView nestedScrollView=(NestedScrollView) findViewById(R.id.nestedScrollView);
        ImageView image1 = (ImageView) findViewById(R.id.conduct);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager1 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://volatilitygame.com/game/img/How%20To%20Conduct%20Volatility%20Game.docx");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager1.enqueue(request);
                onComplete = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        Snackbar.make(nestedScrollView, getString(R.string.download), Snackbar.LENGTH_LONG).show();
                    }
                };

                registerReceiver(onComplete, new IntentFilter(
                        DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }

        });

        ImageView image4=(ImageView) findViewById(R.id.play_game);
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InvestorIndian.this, PlayGame.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(InvestorIndian.this, MainActivity1.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InvestorIndian.this,MainActivity1.class);
        startActivity(intent);
    }


}
