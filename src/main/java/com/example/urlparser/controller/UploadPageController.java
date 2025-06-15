package com.example.urlparser.controller;

import com.example.urlparser.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadPageController {

    private final UploadService uploadService;

    public UploadPageController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/upload")
    public String uploadRedirect() {
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file,
                               @RequestParam("outputDir") String outputDir,
                               @RequestParam("urlColumn") String urlColumn,
                               @RequestParam("brandColumn") String brandColumn,
                               Model model) {
        try {
            int processed = uploadService.process(file, outputDir, urlColumn, brandColumn);
            model.addAttribute("message", "✅ Processed " + processed + " entries successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "❌ Error: " + e.getMessage());
        }

        return "upload";
    }
}
