package com.jeziellago.jniteste;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edit_enter_text;
    TextView android_sha_textv, ndk_sha_textv;
    String entereText;
    Button generate_btn_sha;

    // ndk MD5
    public native String getAndroidId(String strText);

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        generate_btn_sha = findViewById(R.id.generate_btn_sha);
        edit_enter_text = findViewById(R.id.edit_enter_text);

        android_sha_textv = findViewById(R.id.java_sha_textv);
        ndk_sha_textv = findViewById(R.id.ndk_sha_textv);


        generate_btn_sha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entereText = edit_enter_text.getText().toString();

                //android
                android_sha_textv.setText(SignatureUtil.getSha256Hash(entereText));
                Log.e("ANDROID --> ", SignatureUtil.getSha256Hash(entereText));
                android_sha_textv.setText("NDROID--> " + SignatureUtil.getSha256Hash(entereText));

                // ndk c
                ndk_sha_textv.setText(getAndroidId(entereText));
                Log.e("NDK    --> ", getAndroidId(entereText) + "");
                ndk_sha_textv.setText("NDK     --> " + getAndroidId(entereText));
            }
        });
    }
}
