package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieHashMap;
    HashMap<String, Director> directorHashMap;
    HashMap<String, List<String>> movieDirectorHashMap;


    // Constructor

    public MovieRepository() {
        movieHashMap=new HashMap<>();
        directorHashMap=new HashMap<>();
        movieDirectorHashMap=new HashMap<>();
    }


    public void saveMovie(Movie movie){
        movieHashMap.put(movie.getName(), movie);
    }
    public void saveDirector(Director director){
        directorHashMap.put(director.getName(), director);
    }
    public void saveMovieDirectorPair(String directorName, String movieName){
        List<String> moviesName=new ArrayList<>();
        if(movieDirectorHashMap.containsKey(directorName)){
            moviesName=movieDirectorHashMap.get(directorName);
            moviesName.add(movieName);
        }
        else moviesName.add(movieName);

        movieDirectorHashMap.put(directorName, moviesName);
    }



    public Movie getMovieByName(String movieName){
        if(movieHashMap.containsKey(movieName)) return movieHashMap.get(movieName);

        return null;   // when movie not found
    }
    public Director getDirectorByName(String directorName){
        if(directorHashMap.containsKey(directorName)) return directorHashMap.get(directorName);

        return null;  // when director not found
    }
    public List<String> getMoviesByDirectorName(String directorName){
        if(movieDirectorHashMap.containsKey(directorName)) return movieDirectorHashMap.get(directorName);

        return null;  // when director name not found
    }
    public List<String> getAllMovies(){
        List<String> moviesName=new ArrayList<>();

        for(String movieName:movieHashMap.keySet()) moviesName.add(movieName);
        return moviesName;
    }



    public void deleteDirectorByName(String directorName){
        if(directorHashMap.containsKey(directorName)){
            if(movieDirectorHashMap.containsKey(directorName)){
                List<String> moviesName=movieDirectorHashMap.get(directorName);
                for(String movieName:moviesName){
                    if(movieHashMap.containsKey(movieName)){
                        movieHashMap.remove(movieName);  // It will remove the movies of that particular Director from Movie DB
                    }
                }
                movieDirectorHashMap.remove(directorName); // It will remove that Director from MovieDirectorPair Database
            }
            directorHashMap.remove(directorName);  // It will remove that particular Director from Director Database
        }
    }
    public void deleteAllDirectors(){
        for(String directorName:directorHashMap.keySet()){
            if(movieDirectorHashMap.containsKey(directorName)){
                List<String> moviesName=movieDirectorHashMap.get(directorName);
                for(String movieName:moviesName){
                    if(movieHashMap.containsKey(movieName)){
                        movieHashMap.remove(movieName);  // It will remove the movies of that particular Director from Movie DB
                    }
                }
                movieDirectorHashMap.remove(directorName); // It will remove that Director from MovieDirectorPair Database
            }
            directorHashMap.remove(directorName);  // It will remove that particular Director from Director Database
        }
    }
}
