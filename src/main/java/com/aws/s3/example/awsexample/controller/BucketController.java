package com.aws.s3.example.awsexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.example.awsexample.service.AmazonClient;

@RestController
@RequestMapping("/storage")
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    
    @GetMapping("/status")
    public String status() {
        return "success";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }
    
    @PostMapping("/readFile")
    public String readFile(@RequestPart(value = "fileName") String fileName) {
        return this.amazonClient.readFile(fileName);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "fileName") String fileName) {
        return this.amazonClient.deleteFileFromS3Bucket(fileName);
    }
}
