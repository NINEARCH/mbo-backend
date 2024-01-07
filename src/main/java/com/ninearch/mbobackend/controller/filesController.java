package com.ninearch.mbobackend.controller;

import com.ninearch.mbobackend.exception.BaseException;
import com.ninearch.mbobackend.exception.FilesException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class filesController {
    public String singleUpload(MultipartFile file) throws BaseException {

        //validate files
        if (file == null) {
            throw FilesException.fileNull();
        }

        //validate size
        if(file.getSize() > 1048576 * 2){
            throw  FilesException.maxFileSize();
        }

        //validate type
        String contentType = file.getContentType();
        if(contentType == null){
            throw FilesException.fileNotSupported();
        }
        List<String> supportType = Arrays.asList("image/jpeg","image/png");
        if(!supportType.contains(contentType)){
            throw FilesException.fileNotSupported();
        }

        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "boo";
    }

}
