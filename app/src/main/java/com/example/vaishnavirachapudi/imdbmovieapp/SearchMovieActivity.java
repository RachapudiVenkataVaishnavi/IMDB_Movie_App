package com.example.vaishnavirachapudi.imdbmovieapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class SearchMovieActivity extends AppCompatActivity {
    final static String MOVIE_DETAIL_KEY = "DETAIL_ID";
    final static String MOVIE_LIST_KEY="MOVIE_LIST";
    final static String IDEX_KEY = "index";
    final static String MESSAGE_KEY="MOVIE";
    String movieName;
    ProgressDialog progressDialog;
    Movie movie;
    String url = "http://www.omdbapi.com/?type=movie&s=";
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200));
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        if(getIntent().getExtras() != null){
            movieName = getIntent().getExtras().getString(MainActivity.MOVIE_KEY);
            if(isConnected()){
                url = url +movieName;
                new getLink().execute(url);
            }else{
                Toast.makeText(SearchMovieActivity.this, "INTERNET IS NOT CONNECTED", Toast.LENGTH_LONG).show();
            }
        }
    }


    private class getLink extends AsyncTask<String ,Void , ArrayList<Movie>> {
        StringBuilder sb = new StringBuilder();
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SearchMovieActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading Movie List");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }


        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line="";
                while((line=reader.readLine())!=null){
                    sb.append(line);
                }
                return JSONParser.MovieJSONParser.parseMovie(sb.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(final ArrayList<Movie> movies) {
            progressDialog.dismiss();
            if(movies==null || movies.isEmpty())
            {
                Intent intent = new Intent(SearchMovieActivity.this, MainActivity.class);
                intent.putExtra(MESSAGE_KEY, "Sorry Invalid Movie Name");
                startActivity(intent);
                finish();
            }
            if(movies!=null && !movies.isEmpty()) {
                Collections.sort(movies);
                for (int i = 0; i < movies.size(); i++) {
                    movie = movies.get(i);
                    TextView movieTextView = new TextView(SearchMovieActivity.this);
                    movieTextView.setLayoutParams(layoutParams);
                    movieTextView.setText(movie.getTitle() + " (" + String.valueOf(movie.getYear()) + ")");
                    final String id = movie.getImdbID();
                    final int finalI = i;
                    movieTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent newIntent = new Intent(SearchMovieActivity.this, MovieDetailActivity.class);
                            newIntent.putExtra(MOVIE_LIST_KEY, movies);
                            newIntent.putExtra(MOVIE_DETAIL_KEY, id);
                            newIntent.putExtra(IDEX_KEY, finalI);
                            startActivity(newIntent);
                        }
                    });
                    linearLayout.addView(movieTextView);
                }
            }
        }
    }


    private boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }

    }
}
