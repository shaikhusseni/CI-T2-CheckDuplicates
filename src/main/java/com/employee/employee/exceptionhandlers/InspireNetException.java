package com.employee.employee.exceptionhandlers;

public class InspireNetException extends Throwable {

    //    Gives the message to thrown Exception or Reason Why Exception is  Throwing
    public InspireNetException(String message) {
        //It will show as output the exception Handled  on postman console
        super(message);

    }
}
