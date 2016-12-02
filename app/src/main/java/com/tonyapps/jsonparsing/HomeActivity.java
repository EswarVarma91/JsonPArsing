package com.tonyapps.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
Button google,espn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        google=(Button)findViewById(R.id.button2);
        espn=(Button)findViewById(R.id.button3);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,GoggleNews.class);
                startActivity(i);
            }
        });
        espn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i1);
            }
        });
    }
}
