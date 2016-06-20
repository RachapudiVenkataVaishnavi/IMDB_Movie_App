package com.example.vaishnavirachapudi.imdbmovieapp;

import java.io.Serializable;

/**
 * Created by vaishnavirachapudi on 6/19/16.
 */
public class Movie implements Comparable<Movie>,Serializable {

    String title;
    String imdbID;
    String poster;
    String released;
    String genre;
    String director;
    String plot;
    String imdbRating;
    String runtime;
    String writer;
    String actors;
    String language;
    String country;
    String awards;
    String metascore;
    String rated;
    String imbdVotes;
    String type;
    String response;
    int year;

    public Movie() {
        this.title=null;
        this.imdbID=null;
        this.poster=null;
        this.year=0;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String wrtier) {
        this.writer = wrtier;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getImbdVotes() {
        return imbdVotes;
    }

    public void setImbdVotes(String idbdVotes) {
        this.imbdVotes = idbdVotes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String respinse) {
        this.response = respinse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", poster='" + poster + '\'' +
                ", released='" + released + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", plot='" + plot + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", runtime='" + runtime + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", metascore='" + metascore + '\'' +
                ", rated='" + rated + '\'' +
                ", imbdVotes='" + imbdVotes + '\'' +
                ", type='" + type + '\'' +
                ", response='" + response + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public int compareTo(Movie another) {

        return another.getYear()-this.getYear();
    }
}

