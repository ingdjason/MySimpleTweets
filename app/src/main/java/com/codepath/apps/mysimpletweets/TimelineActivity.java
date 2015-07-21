package com.codepath.apps.mysimpletweets;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.TweetsArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        //find the list view
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        //create the arraylist (data source)
        tweets = new ArrayList<>();
        //construct the adapter from data source
        aTweets = new TweetsArrayAdapter(this, tweets);
        //connect adapter to list view
        lvTweets.setAdapter(aTweets);
        //get the client
        client = TwitterApplication.getTwitterClient();
        populateTimeline();
    }

    //Send an api request to get the timeline json
    // fill the listview by creating the tweets objects from json
    private void populateTimeline(){

        client.getHomeTimeline(new JsonHttpResponseHandler(){
            //success
            @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray json){

                Log.d("DEBUG", json.toString());
                // deserialize json
                //create models and add them to the adapter
                //load the model data into listview
                aTweets.addAll(Tweet.fromJSONArray(json));
                Log.d("DEBUG", aTweets.toString());
            }
            //failure
            @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
