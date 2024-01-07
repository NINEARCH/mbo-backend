package com.ninearch.mbobackend.api;

import com.ninearch.mbobackend.controller.filesController;
import com.ninearch.mbobackend.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FilesApi {

    private final filesController files;

    public FilesApi(filesController files) {
        this.files = files;
    }

    @PostMapping
    @RequestMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestPart MultipartFile file) throws BaseException {
        String response;
        response = files.singleUpload(file);
        return ResponseEntity.ok(response);

    }
}
