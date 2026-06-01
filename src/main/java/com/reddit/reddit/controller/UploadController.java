package com.reddit.reddit.controller;

import com.reddit.reddit.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin("*")
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/media")
    public Map<String, String> uploadMedia(
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String url = cloudinaryService.uploadFile(file);
            String contentType = file.getContentType();
            String type = (contentType != null && contentType.startsWith("video"))
                    ? "video" : "image";

            Map<String, String> response = new HashMap<>();
            response.put("url", url);
            response.put("type", type);
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }
}