package com.nachiket.hotel.exception;

public class ResourceNotFoudException extends RuntimeException {

  public ResourceNotFoudException(String s){
    super(s);
  }
  public ResourceNotFoudException(){
    super("Resource Not found !!!");
  }

}
