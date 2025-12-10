package com.example.proyectopepper;


// MainActivity.java
import android.os.Bundle;
import android.util.Log;
import android.widget.Button; // FIX: Import Button
import android.widget.EditText; // FIX: Import EditText
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK; // FIX: Import QiSDK
import com.aldebaran.qi.sdk.RobotActivity; // FIX: Import RobotActivity
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.builder.PhraseBuilder;
import com.aldebaran.qi.sdk.util.Future;
import com.aldebaran.qi.sdk.builder.PhraseBuilder;
import com.aldebaran.qi.sdk.util.Future;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private Say sayAction;

    @Override // FIX: This is now a correct override from RobotActivity
    protected void onStart() {
        super.onStart();
        // Register the activity to listen for the robot's focus
        QiSDK.register(this, this);
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        // Robot is ready. Set up the UI button listener here.
        Button translateButton = findViewById(R.id.translate_button);
        EditText inputText = findViewById(R.id.input_text);

        // Build a base Say object. We will rebuild it with the SSML later.
        sayAction = SayBuilder.with(qiContext).withPhrase(PhraseBuilder.withText(qiContext, "")).build();

        translateButton.setOnClickListener(v -> {
            String text = inputText.getText().toString();
            if (!text.isEmpty()) {
                String morseCode = MorseTranslator.translate(text);
                playMorseCode(qiContext, morseCode);
            }
        });
    }

    private void playMorseCode(QiContext qiContext, String morseCode) {
        StringBuilder ssmlBuilder = new StringBuilder();

        // High-pitched, short tone for a 'beep'. \pau controls the duration of the tone.
        final String DOT_BEEP = "<pitch level=\"300\">\\pau=100\\vol=100\\</pitch>";
        final String DASH_BEEP = "<pitch level=\"300\">\\pau=300\\vol=100\\</pitch>";
        final String CHAR_PAUSE = "\\pau=400\\"; // Pause between dots/dashes of a letter
        final String WORD_PAUSE = "\\pau=1000\\"; // Pause between words

        for (char c : morseCode.toCharArray()) {
            switch (c) {
                case '.':
                    ssmlBuilder.append(DOT_BEEP).append(CHAR_PAUSE);
                    break;
                case '-':
                    ssmlBuilder.append(DASH_BEEP).append(CHAR_PAUSE);
                    break;
                case '/':
                    ssmlBuilder.append(WORD_PAUSE);
                    break;
                case ' ':
                    // Skip the space already appended after each code, rely on CHAR_PAUSE
                    break;
            }
        }

        // 1. Create a Say action with the constructed SSML string
        Say finalSay = SayBuilder.with(qiContext)
                .withText(ssmlBuilder.toString())
                .build();

        // 2. Run the action asynchronously
        finalSay.async().run();
    }

    @Override
    public void onRobotFocusLost() {
        // Clean up if focus is lost
        sayAction = null;
    }

    @Override
    public void onStop() {
        // Unregister the activity when it is stopped
        QiSDK.unregister(this, this);
        super.onStop();
    }

    @Override // FIX: This is the required third method
    public void onRobotFocusRefused(String reason) {
        Log.e("MainActivity", "Robot focus refused: " + reason);
    }

    @Override // FIX: This is now a correct override from RobotActivity
    protected void onStop() {
        // Unregister the activity when it is stopped
        QiSDK.unregister(this, this);
        super.onStop();
    }
}