package fr.example.json;

import android.app.*;
import android.os.*;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity 
{
	private Toolbar toolbar;
	private Adapter adapter;
	private RecyclerView recycler;
	private List<ItemData> item_data;
	
	public static final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		toolbar = (Toolbar)findViewById(R.id.ma_toolbar);
		setSupportActionBar(toolbar);
		
		recycler = (RecyclerView)findViewById(R.id.ma_recyclerView);
		recycler.setLayoutManager(new LinearLayoutManager(this));
		recycler.setItemAnimator(new DefaultItemAnimator());
		
		String URL_JSON = "https://raw.githubusercontent.com/msay2/JSONExample/master/example%20json/item_json.json";
		new DownloadTask().execute(URL_JSON);
    }
	
	public class DownloadTask extends AsyncTask<String, Void, Integer>
	{
        @Override
        protected void onPreExecute() 
		{
            Toast.makeText(MainActivity.this, "show your dialog", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Integer doInBackground(String... params)
		{
            Integer result = 0;
            HttpURLConnection urlConnection;
            try
			{
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200)
				{
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

					while ((line = r.readLine()) != null)
					{
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                }

				else 
				{
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result)
		{
            Toast.makeText(MainActivity.this, "hide your dialog", Toast.LENGTH_SHORT).show();

            if (result == 1) 
			{
                adapter = new Adapter(MainActivity.this, item_data);
                recycler.setAdapter(adapter);
            } 

			else 
			{
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }
	
	private void parseResult(String result)
	{
        try 
		{
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("item");
            item_data = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) 
			{
                JSONObject post = posts.optJSONObject(i);
				
                ItemData item = new ItemData();
                item.setImageUrl(post.optString("image"));
				item.setTitle(post.optString("title"));
                item.setText(post.optString("text"));
				
                item_data.add(item);
            }
        }
		catch (JSONException e)
		{
			Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
