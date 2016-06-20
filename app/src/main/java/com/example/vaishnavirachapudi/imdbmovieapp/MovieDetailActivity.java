package com.example.vaishnavirachapudi.imdbmovieapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {
    final static String IMAGE_KEY ="imageLink";
    ArrayList<Movie> movieList;
    int index;
    String id;
    String url;
    String imdbID;
    ProgressDialog progressDialog;
    StringBuilder sb;
    TextView title,releaseDate , genre,director,actors,description;
    ImageView image;
    RatingBar star;
    Button finish;
    ImageButton pre,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        title = (TextView) findViewById(R.id.textView_title);
        releaseDate = (TextView) findViewById(R.id.textView_releaseDate);
        genre = (TextView) findViewById(R.id.textView_genre);
        director = (TextView) findViewById(R.id.textView_director);
        actors = (TextView) findViewById(R.id.textView_actors);
        image = (ImageView) findViewById(R.id.imageView_Detail);
        star = (RatingBar) findViewById(R.id.ratingBar);
        description = (TextView) findViewById(R.id.textView_Description);
        finish = (Button) findViewById(R.id.button_finish);
        pre = (ImageButton) findViewById(R.id.imageButton_left);
        next = (ImageButton) findViewById(R.id.imageButton_right);

        if (getIntent().getExtras()!=null){
            movieList = (ArrayList<Movie>) getIntent().getExtras().getSerializable(SearchMovieActivity.MOVIE_LIST_KEY);
            index = getIntent().getExtras().getInt(SearchMovieActivity.IDEX_KEY);
            Log.d("demo","index>>"+index);
            id = getIntent().getExtras().getString(SearchMovieActivity.MOVIE_DETAIL_KEY);
            url = "http://www.omdbapi.com/?i="+id;
            new getDetail().execute(url);
        }
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(index == 0)
                    index = movieList.size() - 1;
                else
                    index--;


                    url = "http://www.omdbapi.com/?i="+movieList.get(index).getImdbID();
                    new getDetail().execute(url);


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index == movieList.size() - 1)
                    index = 0;
                else
                    index++;


                url = "http://www.omdbapi.com/?i=" + movieList.get(index).getImdbID();
                new getDetail().execute(url);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class getDetail extends AsyncTask<String ,Void , Movie> {
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MovieDetailActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading Movie");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Movie doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String line ="";
                while((line = reader.readLine())!=null){
                    sb.append(line);
                }
                //JSON parser
                return JSONParser.MovieJSONParser.parseMovieDetail(sb.toString());
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
        protected void onPostExecute(Movie movie) {
            progressDialog.dismiss();
            Log.d("movie", movie.toString());
            if(movie.getYear() !=0){
                title.setText(movie.getTitle() + " (" + String.valueOf(movie.getYear()) + ")");
            }
            if(movie.getReleased() !=null){
                releaseDate.setText("Release Date: " + movie.getReleased());
            }
            if(movie.getGenre() !=null){
                genre.setText("Genre: " + movie.getGenre());
            }
            if(movie.getDirector() !=null){
                director.setText("Director: " + movie.getDirector());
            }
            if(movie.getActors() !=null){
                actors.setText("Actors: "+movie.getActors());
            }
            if(movie.getImdbID()!=null){
                imdbID = movie.getImdbID();
            }
            String poster = movie.getPoster();
            if(poster!=null){
                new getImage().execute(movie.getPoster());
            }else{
                image.setVisibility(View.INVISIBLE);
            }
            if(movie.getImdbRating() !="N/A" ){
                try{
                    star.setRating(Float.valueOf(movie.getImdbRating()));
                }catch(NumberFormatException e){
                    star.setRating(0);
                }

            }
            if(movie.getPlot() !=null ){
                description.setText(movie.getPlot());
            }

        }
    }

    private class getImage extends AsyncTask<String ,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(bitmap);
            image.setClickable(true);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(MovieDetailActivity.this, WebViewActivity.class);
                    newIntent.putExtra(IMAGE_KEY,imdbID);
                    startActivity(newIntent);
                }
            });
        }
    }
}
