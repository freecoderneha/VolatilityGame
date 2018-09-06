package hello.abc.neha.volatilitygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();
    private PopupWindow mPopupWindow;
    private Context mContext;

    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

mContext = getApplicationContext();
mRelativeLayout = (RelativeLayout) findViewById(R.id.relative);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JCnGYw34a-Y\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jZW7h0vpHoE\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6aGZqHegzcs\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/NF7_LQmo1kg\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);

        Button button1=(Button) findViewById(R.id.advisor_login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,AdvisorLogin.class);
                startActivity(i);
            }
        });
        Button button2=(Button) findViewById(R.id.investor_login);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(MainActivity.this,InvestorLogin.class);
                startActivity(i1);
            }
        });
Button pop= (Button) findViewById(R.id.popup);
pop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

         View customView = inflater.inflate(R.layout.activity_popup,null);

        mPopupWindow = new PopupWindow(
                customView,
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        );
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);

    }
});

        ImageView image1=(ImageView) findViewById(R.id.about);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
goToUrl("http://volatilitygame.com/about.php");
            }
        });


        ImageView image2=(ImageView) findViewById(R.id.game);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AdvisorLogin.class);
                startActivity(i);
            }
        });


        ImageView image3=(ImageView) findViewById(R.id.gallery);
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
goToUrl("http://volatilitygame.com/gallery.php");
            }
        });

        ImageView image4=(ImageView) findViewById(R.id.advisor);
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
goToUrl("http://volatilitygame.com/authorizedadvisors.php");
            }
        });

        ImageView image5=(ImageView) findViewById(R.id.contact);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("http://volatilitygame.com/#");

            }
        });


    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
