package com.dashdocnow.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.dashdocnow.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Service
public class FileService {
    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${aws.region}")
    private String region;

    public MediaType contentType (String fileName) {
        String[] fileArrSplit = fileName.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println("Error {} occurred while converting the multipart file" + e.getLocalizedMessage());
        }
        return file;
    }

    public ByteArrayOutputStream downloadFile(String keyName) {
        try {
            S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName, keyName));
            InputStream is = s3Object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (AmazonServiceException serviceException) {
            System.out.println(serviceException.getMessage());
        } catch (AmazonClientException clientException) {
            System.out.println(clientException.getMessage());
            throw clientException;
        }
        return null;
    }

    public void uploadFile(final MultipartFile multipartFile) throws BadRequestException {
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            final String fileName = LocalDateTime.now() + "_" + file.getName();
            System.out.println("Uploading file with name {}" + fileName);
            final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
            amazonS3.putObject(putObjectRequest);
            Files.delete(file.toPath()); // Remove the file locally created in the project folder
        } catch (AmazonServiceException e) {
            System.out.println("Error {} occurred while uploading file" + e.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println("Error {} occurred while deleting temporary file" + ex.getLocalizedMessage());
        }
    }
}




