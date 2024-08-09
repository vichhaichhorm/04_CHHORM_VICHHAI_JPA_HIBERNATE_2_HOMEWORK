package com.vichhai.demo_data_jpa_2.exception.bookException;

public class BaseException extends Exception{
    public int status;

    public BaseException(String code,int status){
        super(code);
        this.status = status;
    }
}
