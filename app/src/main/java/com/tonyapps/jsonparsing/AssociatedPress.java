package com.tonyapps.jsonparsing;
/**
 * Created by Eswar Varma on 03-12-2016.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class AssociatedPress extends Activity  implements SwipeRefreshLayout.OnRefreshListener{
    private String TAG = AssociatedPress.class.getSimpleName();
    private Dialog pDialog;
    private ListView lv;
    TextView tv1;
    SharedPreferences preferences;

    private SwipeRefreshLayout swipeRefreshLayout;
    // URL to get contacts JSON
    private static String url_of_api_key =  "https://newsapi.org/v1/articles?source=associated-press&sortBy=top&apiKey=f95ac369aef148e29d6bf0766c8b2715";
    ArrayList<HashMap<String, String>> AssociatedPressList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associated_press);

        AssociatedPressList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list3);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh3);
        // new GoogleNews().execute();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(false);

                                        new AssociatedPresso().execute();
                                    }
                                }
        );
    }
    @Override
    public void onRefresh() {
        new AssociatedPresso().execute();
    }
    private class AssociatedPresso extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new Dialog(AssociatedPress.this);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_of_api_key);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray articles = jsonObj.getJSONArray("articles");
                    // looping through All Contacts
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject c = articles.getJSONObject(i);
                        String author = c.getString("author");
                        String title = c.getString("title");
                        String description = c.getString("description");
                        String url = c.getString("url");
                        String publishedAt=c.getString("publishedAt");
                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();
                        // adding each child node to HashMap key => value
                        contact.put("author", author);
                        contact.put("title", title);
                        contact.put("description", description);
                        contact.put("url",url);
                        contact.put("publishedAt",publishedAt);
                        // adding contact to contact list
                        AssociatedPressList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Check Your Internet Connection",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    AssociatedPress.this, AssociatedPressList,
                    R.layout.list_item, new String[]{"author","title", "description","url",
                    "publishedAt"}, new int[]{R.id.author,
                    R.id.title, R.id.description,R.id.url,R.id.publishedAt});
            lv.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}


