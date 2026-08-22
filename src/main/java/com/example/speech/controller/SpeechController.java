package com.example.speech.controller;

import com.example.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/speech-to-text")
    public ResponseEntity<String> speechToText(
            @RequestParam("fileName") String fileName,
            @RequestParam(value = "withTimestamps", defaultValue = "false") boolean withTimestamps) {
        String response = speechService.speechToText(fileName, withTimestamps);
        return ResponseEntity.ok().body(response);
    }
}
