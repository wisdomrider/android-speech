package net.gotev.speech;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.gotev.speech.ui.SpeechProgressView;

import java.util.List;
//    CREATED BY WISDOMRIDER
//    FOR APPS CONTACT avishekzone@gmail.com


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Speech.init(this, getPackageName());
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        Speech.getInstance().say("haha its cool !");
    }

    public void a(View v) {
        try {
            Speech.getInstance().startListening((SpeechProgressView) findViewById(R.id.progress), new SpeechDelegate() {
                @Override
                public void onStartOfSpeech() {
                    Log.e("ERROR", "start !");
                }

                @Override
                public void onSpeechRmsChanged(float value) {
                    Log.e("ERROR", "changed !");

                }

                @Override
                public void onSpeechPartialResults(List<String> results) {
                    Log.e("ERROR", "resukt !");

                }

                @Override
                public void onSpeechResult(String result) {
                    Log.e("ERROR", result);
                }
            });
        } catch (SpeechRecognitionNotAvailable speechRecognitionNotAvailable) {
            speechRecognitionNotAvailable.printStackTrace();
        } catch (GoogleVoiceTypingDisabledException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        Speech.getInstance().shutdown();
        super.onDestroy();

    }
}
