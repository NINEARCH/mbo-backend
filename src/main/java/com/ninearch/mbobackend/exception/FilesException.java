package com.ninearch.mbobackend.exception;

public class FilesException extends BaseException{
    public FilesException(String code) {
        super("files." + code);
    }

    //user.register.mail

    public static FilesException fileNull() {
        return new FilesException("files.request.null");
    }

    public static FilesException maxFileSize() {
        return new FilesException("files.request.max");
    }

    public static FilesException fileNotSupported() {
        return new FilesException("files.request.incorrect");
    }


    public static FilesException emailNull() {
        return new FilesException("files.email.null");
    }

    public static FilesException notFound() {
        return new FilesException("notfound");
    }
}
