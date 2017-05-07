package com.thepacific.demo;

import android.os.Bundle;

import com.thepacific.transformer.ObservableTransformerUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.reactivex.Observable;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fetchImage() {

        Observable
                .just("https://www.android.cn")
                .map(baseUrl -> fetchImages(baseUrl))
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .compose(ObservableTransformerUtil.io(lifecycle(), ActivityEvent.DESTROY))
                .subscribe();
    }

    public static class Image {
    }

    private List<Image> fetchImages(String url) {
        return null;
    }
}
