package com.example.shyhjie.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, Main2Activity.class));

        Observable.timer(10, TimeUnit.SECONDS).subscribe(aLong -> {
            System.out.println(">>> send 100");
            EventBus.intBus.send(100);
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        });


    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}
