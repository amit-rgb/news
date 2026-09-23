package com.bheemnagartimes.news.controller;
import org.springframework.beans.factory.annotation.Value; import org.springframework.core.io.Resource; import org.springframework.core.io.UrlResource; import org.springframework.http.MediaType; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.nio.file.*;
@RestController public class UploadController {
private final Path uploadDir;
public UploadController(@Value("${news.upload-dir:./data/uploads}") String uploadDir){this.uploadDir=Paths.get(uploadDir).toAbsolutePath().normalize();}
@GetMapping("/uploads/{filename:.+}") public ResponseEntity<Resource> image(@PathVariable String filename)throws Exception{Path file=uploadDir.resolve(filename).normalize(); if(!file.startsWith(uploadDir))return ResponseEntity.badRequest().build(); Resource resource=new UrlResource(file.toUri()); if(!resource.exists())return ResponseEntity.notFound().build(); String lower=filename.toLowerCase();String ct=lower.endsWith(".png")?"image/png":lower.endsWith(".webp")?"image/webp":"image/jpeg"; return ResponseEntity.ok().contentType(MediaType.parseMediaType(ct)).body(resource);}
}