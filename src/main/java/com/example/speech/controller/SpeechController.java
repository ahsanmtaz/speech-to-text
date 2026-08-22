package com.example.speech.controller;

import com.example.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/speech")
public class SpeechController {

    final SpeechService speechService;

    @PostMapping("/upload-audio-file")
    public ResponseEntity<String> uploadAudioFile(@RequestParam("file") MultipartFile file) {
        String response = speechService.uploadAudioFile(file);
        return ResponseEntity.ok().body(response);
    }
}
