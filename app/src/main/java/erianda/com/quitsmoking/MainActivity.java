package erianda.com.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void info (View v){
        Intent i = new Intent(this,ListBerita.class);
        startActivity(i);
    }
    public void profile (View v){
        Intent i = new Intent(this,ProfileCurrent.class);
        startActivity(i);
    }
    public void about (View v){
        Intent i = new Intent(this,Bout.class);
        startActivity(i);
    }

    public void kesehatan (View v){
        Intent i = new Intent(this,Achievement.class);
        startActivity(i);
    }

}
