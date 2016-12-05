package com.tonyapps.jsonparsing;
/**
 * Created by Eswar Varma on 03-12-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class HomeActivity extends Activity {
    ListView myList;
    String[] subnames={"Comprehensive, up-to-date news coverage, aggregated from sources all over the world by Google News.",
            "TechCrunch is a leading technology media property, dedicated to obsessively profiling startups, reviewing new Internet products, and breaking tech news.",
    "The Associated Press delivers in-depth coverage on today's Big Story including top stories, international, politics, lifestyle, business, entertainment, and more.",
    "BBC News is an operational business division of the British Broadcasting Corporation (BBC) responsible for the gathering and broadcasting of news and current affairs.",
    "The BBC holds the television and radio UK broadcasting rights to several sports.",
    "BILD International helps churches develop comprehensive, wisdom-based education programs through a church-based philosophy, curriculum, and network.",
    "Bloomberg delivers business and markets news, data, analysis, and video to the world, featuring stories from Businessweek and Bloomberg News.",
    "Get the latest news through Business Insider India on tech, finance, politics, strategy, life and entertainment.",
    "Get the latest news through Business Insider UK on tech, finance, politics, strategy, life and entertainment.",
    "BuzzFeed Motion Picture's flagship channel. Sometimes funny, sometimes serious, always shareable. New videos posted daily.",};
    String[] names={"Goggle News","Tech Crunch","Associated Press","BBC News","BBC Sport","Bild","Bloomberg","Business Insider","Business Insider (UK)","Buzzfeed"};
    Integer[] pics={R.drawable.googlenews,R.drawable.techcrunchlogo,R.drawable.associatepress,R.drawable.bbclogo,R.drawable.bbcsport,R.drawable.bild,R.drawable.bloomberg,R.drawable.businessinsider,R.drawable.businessinsideruk,R.drawable.buzzfeed};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        myList=(ListView)findViewById(R.id.listview);

        CustomAdapter adapter=new CustomAdapter(this,names,pics,subnames);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position){
                    case 0:
                        Intent i=new Intent(HomeActivity.this,GoggleNews.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent i1=new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(i1);
                        break;
                    case 2:
                        Intent i2=new Intent(HomeActivity.this,AssociatedPress.class);
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i3=new Intent(HomeActivity.this,BBC_News.class);
                        startActivity(i3);
                        break;
                    case 4:
                        Intent i4=new Intent(HomeActivity.this,BBC_Sport.class);
                        startActivity(i4);
                        break;
                    case 5:
                        Intent i5=new Intent(HomeActivity.this,Bild.class);
                        startActivity(i5);
                        break;
                    case 6:
                        Intent i6=new Intent(HomeActivity.this,Bloomberg.class);
                        startActivity(i6);
                        break;
                    case 7:
                        Intent i7=new Intent(HomeActivity.this,BusinessInsider.class);
                        startActivity(i7);
                        break;
                    case 8:
                        Intent i8=new Intent(HomeActivity.this,BusinessInsiderUK.class);
                        startActivity(i8);
                        break;
                    case 9:
                        Intent i9=new Intent(HomeActivity.this,Buzzfeed.class);
                        startActivity(i9);
                        break;
                }
            }
        });
    }


}
