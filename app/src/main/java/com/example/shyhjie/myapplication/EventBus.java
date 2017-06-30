package com.example.shyhjie.myapplication;

import com.jakewharton.rxrelay.PublishRelay;
import com.jakewharton.rxrelay.Relay;

import rx.Observable;

public class EventBus<T> {

  public static final EventBus<Integer> intBus = new EventBus<>();

  private final Relay<T, T> _bus = PublishRelay.create();

  public void send(T o) {
    _bus.call(o);
  }

  public Observable<T> toObserverable() {
    return _bus;
  }
}