package com.example.vaishnavirachapudi.imdbmovieapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vaishnavirachapudi on 6/19/16.
 */
public class JSONParser {
    static public class MovieJSONParser{
        static ArrayList<Movie> parseMovie(String in) throws JSONException {
            ArrayList<Movie> movieList = new ArrayList<Movie>();

            JSONObject root = new JSONObject(in);
            JSONArray movieJSONArray = root.getJSONArray("Search");
            for(int i=0;i<movieJSONArray.length();i++){
                JSONObject movieJSONObject = movieJSONArray.getJSONObject(i);
                Movie movie = new Movie();
                if(movieJSONObject.getString("Title")!=null){
                    movie.setTitle(movieJSONObject.getString("Title"));
                }
                if(movieJSONObject.getString("Year")!=null){
                    movie.setYear(Integer.parseInt(movieJSONObject.getString("Year")));
                }
                if(movieJSONObject.getString("imdbID")!=null){
                    movie.setImdbID(movieJSONObject.getString("imdbID"));
                }
                if(movieJSONObject.getString("Poster")!=null){
                    movie.setPoster(movieJSONObject.getString("Poster"));
                }
                movieList.add(movie);
            }
            return movieList;
        }




        static Movie parseMovieDetail(String in) throws JSONException {
            Movie movie = new Movie();
            JSONObject movieDetailJSONObject = new JSONObject(in);
            if(movieDetailJSONObject.getString("Title")!=null){
                movie.setTitle(movieDetailJSONObject.getString("Title"));
            }
            if(movieDetailJSONObject.getString("Year")!=null){
                movie.setYear(Integer.parseInt(movieDetailJSONObject.getString("Year")));
            }
            if(movieDetailJSONObject.getString("Rated")!=null){
                movie.setRated(movieDetailJSONObject.getString("Poster"));
            }
            if(movieDetailJSONObject.getString("Released")!=null){
                movie.setReleased(movieDetailJSONObject.getString("Released"));
            }
            if(movieDetailJSONObject.getString("Runtime")!=null){
                movie.setRuntime(movieDetailJSONObject.getString("Runtime"));
            }
            if(movieDetailJSONObject.getString("Genre")!=null){
                movie.setGenre(movieDetailJSONObject.getString("Genre"));
            }
            if(movieDetailJSONObject.getString("Director")!=null){
                movie.setDirector(movieDetailJSONObject.getString("Director"));
            }
            if(movieDetailJSONObject.getString("Writer")!=null){
                movie.setWriter(movieDetailJSONObject.getString("Writer"));
            }
            if(movieDetailJSONObject.getString("Actors")!=null){
                movie.setActors(movieDetailJSONObject.getString("Actors"));
            }
            if(movieDetailJSONObject.getString("Plot")!=null){
                movie.setPlot(movieDetailJSONObject.getString("Plot"));
            }
            if(movieDetailJSONObject.getString("Language")!=null){
                movie.setLanguage(movieDetailJSONObject.getString("Language"));
            }
            if(movieDetailJSONObject.getString("Country")!=null){
                movie.setCountry(movieDetailJSONObject.getString("Country"));
            }
            if(movieDetailJSONObject.getString("Awards")!=null){
                movie.setAwards(movieDetailJSONObject.getString("Awards"));
            }
            if(movieDetailJSONObject.getString("Poster")!=null){
                movie.setPoster(movieDetailJSONObject.getString("Poster"));
            }
            if(movieDetailJSONObject.getString("Metascore")!=null){
                movie.setMetascore(movieDetailJSONObject.getString("Metascore"));
            }
            if(movieDetailJSONObject.getString("imdbRating")!=null){
                movie.setImdbRating(movieDetailJSONObject.getString("imdbRating"));
            }
            if(movieDetailJSONObject.getString("imdbVotes")!=null){
                movie.setImbdVotes(movieDetailJSONObject.getString("imdbVotes"));
            }
            if(movieDetailJSONObject.getString("imdbID")!=null){
                movie.setImdbID(movieDetailJSONObject.getString("imdbID"));
            }
            if(movieDetailJSONObject.getString("Type")!=null){
                movie.setType(movieDetailJSONObject.getString("Type"));
            }
            if(movieDetailJSONObject.getString("Response")!=null){
                movie.setResponse(movieDetailJSONObject.getString("Response"));
            }
            return movie;
        }
    }

}

