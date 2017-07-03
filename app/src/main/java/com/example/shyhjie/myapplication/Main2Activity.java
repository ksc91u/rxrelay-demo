package com.example.shyhjie.myapplication;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

public class Main2Activity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        System.out.println(">>>> Main2 onCreate");

        EventBus.intBus.toObserverable()
                .compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.DESTROY))
                .subscribe(integer -> System.out.println(">>>> Main2 receive " + integer + " from bus"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(">>>> Main2 destroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(">>>> Main2 onResume");
        Observable.timer(1, TimeUnit.SECONDS).subscribe(aLong -> finish());
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(">>>> Main2 stop");
    }
}
