package com.cagatayiba.coursePlatform.exception;

public class NoObjectWithGivenIdException extends Exception{
    private int id;
    public NoObjectWithGivenIdException(){
        super("No Object Found With The Given Id");
    }
    public NoObjectWithGivenIdException(String message){
        super(message);
    }
    public NoObjectWithGivenIdException(int id){
        this("No Object Found With The Given Id Of " + id);
        this.id = id;
    }
    private int getId(){
        return this.id;
    }
}
