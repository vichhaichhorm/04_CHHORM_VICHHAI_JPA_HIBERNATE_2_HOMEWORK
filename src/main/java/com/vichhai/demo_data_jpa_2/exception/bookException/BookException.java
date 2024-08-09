package com.vichhai.demo_data_jpa_2.exception.bookException;


public class BookException extends BaseException {
    public BookException(String code,int status) {
        super(code,status);
    }
    public static BookException bookNotFound(){
        return new BookException("Not Found",404);
    }

}
