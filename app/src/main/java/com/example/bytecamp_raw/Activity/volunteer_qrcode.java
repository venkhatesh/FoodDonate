package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bytecamp_raw.R;

import com.example.bytecamp_raw.food_verify;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class volunteer_qrcode extends AppCompatActivity {
    ImageView imageView;
    DatabaseReference databaseReference;
    int i,x,j,y,z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_qrcode);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("RHA").child("KYC");
        imageView=findViewById(R.id.qrcode);
        String pt=getAlphaNumericString(16);
        databaseReference.setValue(pt);
        pt = pt.toUpperCase();
        int len = pt.length();
        char[] c1 = new char[len];
        char[] ct = new char[len];
        char[] rfc = new char[len];
        c1 = pt.toCharArray();
        char sub_key = '5';
        for(i=0;i<len;i++)
        {
            x = (c1[i]+sub_key);
            if(x>90) {
                y = x-90;
                ct[i] = (char)(65+(y-1)); }
            else
                ct[i] = (char)(x);
        }
        for(i=0;i<len;i=i+2)
        {
            rfc[j]=ct[i];
            j++;
        }
        for(i=1;i<len;i=i+2)
        {
            rfc[j]=ct[i];
            j++;
        }
        String cipher = new String(rfc);

        String text=cipher; // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,700,700);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                Intent intent=new Intent(getApplicationContext(), food_verify.class);
                startActivity(intent);
            }
        }, 2000);



    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
