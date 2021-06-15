package com.example.aboutqr;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button qrBtn;
    Button qrScanBtn;
    ImageView imageViewQrCode;
    LinearLayout qrLayout;

    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrLayout = findViewById(R.id.llBtn);
        qrScanBtn = findViewById(R.id.scanBtn);
        tvResult = findViewById(R.id.tvResult);
        imageViewQrCode = (ImageView) findViewById(R.id.qrCode);

        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        // Scan 후 결과값 받는 곳
        ActivityResultLauncher<Intent> scanActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.d(TAG, "result OK");
                        // There are no request codes
                        Intent data = result.getData();
                        if(data != null) {
                            String qrData = data.getStringExtra("QR_DATA");
                            imageViewQrCode.setVisibility(View.GONE);
                            qrLayout.setVisibility(View.VISIBLE);
                            tvResult.setText(qrData);
                        }
//                            doSomeOperations();
                    }
                });

        qrScanBtn.setOnClickListener(v -> {
//            startActivityForResult(new Intent(this, ScanQrActivity.class), 200);
            scanActivityResultLauncher.launch(new Intent(this, ScanQrActivity.class));
        });
        qrBtn = findViewById(R.id.qrBtn);
        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQRCode("Put the Data What you want");
            }
        });


    }

    public void generateQRCode(String contents) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(contents, BarcodeFormat.QR_CODE, 400, 400);
            imageViewQrCode.setVisibility(View.VISIBLE);
            qrLayout.setVisibility(View.GONE);
            imageViewQrCode.setImageBitmap(bitmap); // qr코드 ImageView에 설정
        } catch(Exception e) {
            Log.d(TAG, "Exception e = " + e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        imageViewQrCode.setVisibility(View.GONE);
        qrLayout.setVisibility(View.VISIBLE);
    }

}