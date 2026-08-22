package com.example.speech.service.impl;

import com.example.speech.service.SpeechService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.module.Configuration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class SpeechServiceImpl implements SpeechService {

    private static final Set<String> ALLOWED_AUDIO_MIME_TYPES = Set.of(
            "audio/mpeg",
            "audio/wav"
    );

    private static final String STORAGE_DIRECTORY_DATE_FORMAT = "dd-MM-yyyy"; // sample: 22-08-2026

    private static final String STORAGE_LOCATION = "transcriptionSourceAudio/uploads/";

    public String uploadAudioFile(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File seems to be empty.");
            }

            String contentType = file.getContentType();
            if (contentType == null
                    || !ALLOWED_AUDIO_MIME_TYPES.contains(contentType)) {
                // we may need to use an external library to validate file content against given MIME types
                throw new IllegalArgumentException("Invalid file input has been detected.");
            }

            // date based directory path for a clean folder structure
            Path storageLocation = Paths.get(STORAGE_LOCATION
                            + LocalDateTime
                            .now()
                            .format(DateTimeFormatter.ofPattern(STORAGE_DIRECTORY_DATE_FORMAT)))
                    .toAbsolutePath()
                    .normalize();

            Files.createDirectories(storageLocation);

            Path fileToStore = storageLocation.resolve(System.currentTimeMillis() + "_" + file.getOriginalFilename());

            Files.copy(file.getInputStream(),
                    fileToStore,
                    StandardCopyOption.REPLACE_EXISTING);

            // log.debug("New file has been stored at {0}: ", fileToStore.toAbsolutePath().normalize());

            return "File has been uploaded successfully.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Service has encountered an error while processing the uploaded file.";
        }
    }

    private String convertSpeechToText(Path audioFile) throws Exception {

        StringBuilder speechTextBuilder = new StringBuilder();

//        // Assuming Google Speech Client
//        SpeechClient speechClient = SpeechClient.create();
//
//        byte[] data = Files.readAllBytes(audioFile);
//        ByteString audioBytes = ByteString.copyFrom(data);
//
//        RecognitionConfig config = RecognitionConfig.newBuilder()
//                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
//                .setLanguageCode("en-US")
//                .build();
//
//        RecognitionAudio audio = RecognitionAudio.newBuilder()
//                .setContent(audioBytes)
//                .build();
//
//        RecognizeResponse response = speechClient.recognize(config, audio);
//        List<SpeechRecognitionResult> results = response.getResultsList();
//
//        for (SpeechRecognitionResult result : results) {
//            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//            // log.debug("Transcription: {0}", alternative.getTranscript());
//            speechTextBuilder.append(alternative.getTranscript());
//        }

        return speechTextBuilder.toString();
    }

    private void convertSpeechToTextWithTimestamps(Configuration config, File audioFile) throws Exception {

//        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(config);
//
//        InputStream stream = new FileInputStream(audioFile);
//
//        recognizer.startRecognition(stream);
//
//        SpeechResult result;
//
//        while ((result = recognizer.getResult()) != null) {
//
//            for (WordResult wordResult : result.getWords()) {
//
//                if (wordResult.isFiller()) {
//                    continue;
//                }
//
//                String word = wordResult.getWord().getSpelling();
//
//                double startSec = wordResult.getTimeFrame().getStart() / 1000.0;
//                double endSec = wordResult.getTimeFrame().getEnd() / 1000.0;
//
//                log.debug("Word: {0} | Start: {1} | End: {2}}", word, startSec, endSec);
//            }
//        }
//
//        recognizer.stopRecognition();
    }
}
