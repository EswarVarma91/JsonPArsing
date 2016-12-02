package com.tonyapps.jsonparsing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GoggleNews extends Activity {
    private String TAG = GoggleNews.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String url_of_api_key =  " https://newsapi.org/v1/articles?source=abc-news-au&sortBy=top&apiKey=f95ac369aef148e29d6bf0766c8b2715";
    ArrayList<HashMap<String, String>> GoogleNewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goggle_news);
        GoogleNewsList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list2);

        new GoogleNews().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GoogleNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(GoggleNews.this);
            pDialog.setMessage("Please wait...");
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
                    JSONObject jsonObj1 = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray articles = jsonObj1.getJSONArray("articles");

                    // looping through All Contacts
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject c1 = articles.getJSONObject(i);

                        String author1 = c1.getString("author");
                        String title1 = c1.getString("title");
                        String description1 = c1.getString("description");
                        String url1 = c1.getString("url");
                        String publishedAt1=c1.getString("publishedAt");
                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("author", author1);
                        contact.put("title", title1);
                        contact.put("description", description1);
                        contact.put("url",url1);
                        contact.put("publishedAt",publishedAt1);




                        // adding contact to contact list
                        GoogleNewsList.add(contact);
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
                                "Couldn't get json from server. Check LogCat for possible errors!",
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
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    GoggleNews.this, GoogleNewsList,
                    R.layout.list_item, new String[]{"author","title", "description","url",
                    "publishedAt"}, new int[]{R.id.author,
                    R.id.title, R.id.description,R.id.url,R.id.publishedAt});

            lv.setAdapter(adapter);
        }

    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(GoggleNews.this);
            pDialog.setMessage("Please wait...");
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
                        GoogleNewsList.add(contact);
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
                                "Couldn't get json from server. Check LogCat for possible errors!",
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
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    GoggleNews.this, GoogleNewsList,
                    R.layout.list_item, new String[]{"author","title", "description","url",
                    "publishedAt"}, new int[]{R.id.author,
                    R.id.title, R.id.description,R.id.url,R.id.publishedAt});

            lv.setAdapter(adapter);
        }

    }

}
