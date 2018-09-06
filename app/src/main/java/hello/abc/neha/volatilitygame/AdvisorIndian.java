package hello.abc.neha.volatilitygame;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class AdvisorIndian extends AppCompatActivity {
   DownloadManager downloadManager1;
    DownloadManager downloadManager2;
    BroadcastReceiver onComplete;
    ImageView image1,image2,image3,image4,image5;

    User user = SharedPrefManager.getInstance(this).getUser();


String usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investor_indian);
        image1 =  (ImageView) findViewById(R.id.conduct);

        image2 =  (ImageView) findViewById(R.id.ppt);


        image3 =  (ImageView) findViewById(R.id.view_tutorial);

        image4 =  (ImageView) findViewById(R.id.play_game);

        image5 =  (ImageView) findViewById(R.id.update_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        final NestedScrollView nestedScrollView=(NestedScrollView) findViewById(R.id.nestedScrollView);

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


            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadManager2 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse("https://volatilitygame.com/game/img/VolatilityGamePPT.ppt");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    downloadManager2.enqueue(request);
                    BroadcastReceiver onComplete = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {

                            Snackbar.make(nestedScrollView, getString(R.string.download), Snackbar.LENGTH_LONG).show();
                        }
                    };

                    registerReceiver(onComplete, new IntentFilter(
                            DownloadManager.ACTION_DOWNLOAD_COMPLETE));

                }
            });



            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(AdvisorIndian.this, ViewTutorial.class);
                    startActivity(i);
                }
            });


            image4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(AdvisorIndian.this, PlayGame.class);
                    startActivity(i);
                }
            });

            image5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Update().execute();

                }
            });

    }


    class Update extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                //converting response to json object
                JSONObject obj = new JSONObject(s);

                Intent i=new Intent(getApplicationContext(), UpdateProfile.class);
                i.putExtra("email",obj.getString("email"));
                i.putExtra("name",obj.getString("name"));
                i.putExtra("address",obj.getString("address"));
                i.putExtra("mobile",obj.getString("mobile"));
                i.putExtra("city",obj.getString("city"));
                i.putExtra("arn",obj.getString("arn"));
                i.putExtra("usertype",obj.getString("usertype"));
                i.putExtra("org",obj.getString("name_of_organisation"));
                startActivity(i);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();
            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(user.getId()));
            params.put("email", user.getEmail());
            return requestHandler.sendPostRequest(AppConfig.URL_UPDATE, params);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(AdvisorIndian.this, MainActivity1.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
    Intent intent = new Intent(AdvisorIndian.this,MainActivity1.class);
    startActivity(intent);
    }




}
