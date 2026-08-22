package com.example.speech.service;

import org.springframework.web.multipart.MultipartFile;

public interface SpeechService {

    public String uploadAudioFile(MultipartFile file);

    String speechToText(String fileName, boolean withTimestamps);
}
