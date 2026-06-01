package com.reddit.reddit.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) throws IOException {

        String contentType = file.getContentType();
        String resourceType = "image"; // default

        if (contentType != null && contentType.startsWith("video")) {
            resourceType = "video";
        }

        Map result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "memesphere",
                        "resource_type", resourceType
                )
        );

        return result.get("secure_url").toString();
    }
}