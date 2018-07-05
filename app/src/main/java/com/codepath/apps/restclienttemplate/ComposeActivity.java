package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    private TwitterClient client;
    @BindView(R.id.tvCounter) TextView tvCounter;
    @BindView(R.id.etTweet) EditText etTwe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApp.getRestClient(this);
        //butterknife tool
        ButterKnife.bind(this);
        //sets listener for text changes
        etTwe.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = etTwe.length();
                String convert = String.valueOf(140-length);
                tvCounter.setText(convert);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onSubmit(View v) {
        EditText etTweet = (EditText) findViewById(R.id.etTweet);
        String message = etTweet.getText().toString();
        client.sendTweet(message, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet tweet = new Tweet().fromJSON(response);
                    // Prepare data intent
                    Intent data = new Intent();
                    //pass relevant data
                    data.putExtra("tweet", Parcels.wrap(tweet));
                    // Activity finished ok, return the data
                    setResult(RESULT_OK, data); // set result code and bundle data for response
                    finish(); // closes the activity, pass data to parent
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
