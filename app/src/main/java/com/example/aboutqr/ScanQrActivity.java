package com.example.aboutqr;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrActivity extends AppCompatActivity {

    IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

//        new IntentIntegrator(this).setOrientationLocked(true).initiateScan(); // `this` is the current Activity -> 카메라 나오게 하기
        integrator = new IntentIntegrator(this);
        integrator.setPrompt("QR을 스캔해주세요.");
        integrator.setOrientationLocked(true).initiateScan(); // `this` is the current Activity -> 카메라 나오게 하기

    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent.putExtra("QR_DATA", result.getContents()));
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}