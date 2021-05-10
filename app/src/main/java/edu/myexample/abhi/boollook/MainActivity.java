package edu.myexample.abhi.boollook;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;
    Animation top,bottom;
    ImageView img;


    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.imageView);


        txt=findViewById(R.id.logo_text);
        top= AnimationUtils.loadAnimation(this,R.anim.topanimation);

        bottom= AnimationUtils.loadAnimation(this,R.anim.bottomanimation);
        img.setAnimation(top);
        txt.setAnimation(bottom);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,Login.class);
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair(img,"logo_image");
                pairs[1]=new Pair(txt,"logo_title");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(i,options.toBundle());
                finish();
            }
        },SPLASH_SCREEN);
    }
}