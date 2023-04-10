package com.voiceassistant;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class VoiceAssistant {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("src/main/resources/0033.dic");
        configuration.setLanguageModelPath("src/main/resources/0033.lm");

        try {
            LiveSpeechRecognizer liveSpeechRecognizer=new LiveSpeechRecognizer(configuration);
            liveSpeechRecognizer.startRecognition(true);
            SpeechResult speechResult=null;
            while ((speechResult=liveSpeechRecognizer.getResult())!=null){
                String voiceCommand=speechResult.getHypothesis();
                System.out.println("Voice Command : "+voiceCommand);
                if(voiceCommand.equalsIgnoreCase("open chrome")){
                    Runtime.getRuntime().exec("cmd.exe /c START chrome");
                }else if (voiceCommand.equalsIgnoreCase("close chrome")){
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
                }else if(voiceCommand.equalsIgnoreCase("terminate")){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
