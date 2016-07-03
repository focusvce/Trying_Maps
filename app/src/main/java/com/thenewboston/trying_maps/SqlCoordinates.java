package com.thenewboston.trying_maps;


public class SqlCoordinates {

    //private variables
    String longi;
    String lati;

    // Empty constructor
    public SqlCoordinates(){

    }

    // constructor
    public SqlCoordinates(String longi, String lati){
        this.longi = longi;
        this.lati = lati;
    }

    public String getLongi(){
        return this.longi;
    }

    public void setLongi(String longi){
        this.longi = longi;
    }

    public String getLati(){
        return this.lati;
    }

    public void setLati(String lati){
        this.lati = lati;
    }
}
