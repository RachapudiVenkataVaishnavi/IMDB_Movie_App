package com.example.vaishnavirachapudi.imdbmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static String MOVIE_KEY="MOVIE";
    EditText editText;
    Button search;
    String movieName;
    String message =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        search = (Button) findViewById(R.id.button_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieName = editText.getText().toString();
                if(getIntent().getExtras() != null){
                    message = getIntent().getExtras().getString(SearchMovieActivity.MESSAGE_KEY);

                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                }
                if (movieName.length() > 0 ) {
                    Intent intent = new Intent(MainActivity.this, SearchMovieActivity.class);
                    intent.putExtra(MOVIE_KEY, movieName);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "please enter an movie name", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    }

