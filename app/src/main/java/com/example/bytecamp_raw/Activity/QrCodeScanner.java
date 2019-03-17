package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by venkat on 17/3/19.
 */
public class QrCodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // Programmatically initialize the scanner view
        mScannerView = new ZXingScannerView(this);
        // Set the scanner view as the content view
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Prints scan results
        // Prints the scan format (qrcode, pdf417 etc.)
        //If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("QRcode", rawResult.getText());
        int i,x,j,y,z;
        String cipher="asdfsdvads";
        int len = cipher.length();
        char[] c1 = new char[len];
        char[] ct = new char[len];
        char[] rfc = new char[len];
        if(len%2!=0) {
            z = (len/2);
            z = z+1;}
        else
            z = (len/2);
        rfc = cipher.toCharArray();
        j=z;
        for(i=0, x=0;i<z && j<len&& x<len;i++,j++) {
            ct[x] = rfc[i];
            x++;
            ct[x] = rfc[j];
            x++;
        }
        for(i=0;i<len;i++)
        {
            x = (ct[i]-4);
            if(x<65) {
                y = 65-x;
                rfc[i] = (char)(90-(y-1)); }
            else
                rfc[i] = (char)(x);

        }
        cipher = new String(rfc);
        setResult(RESULT_OK, intent);
        finish();
    }
}