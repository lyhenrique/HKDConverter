package com.ericlau.hkdconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Welcome extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
                startActivity(new Intent(Welcome.this,
                        MainActivity.class));
            finish();

        } catch (Exception e) {
            Toast.makeText(Welcome.this,"Cannot run Homepage!",Toast.LENGTH_SHORT).show();
        }
    }
}

