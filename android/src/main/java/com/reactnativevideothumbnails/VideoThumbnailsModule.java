package com.reactnativevideothumbnails;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@ReactModule(name = VideoThumbnailsModule.NAME)
public class VideoThumbnailsModule extends ReactContextBaseJavaModule {
  public static final String NAME = "VideoThumbnails";

  public VideoThumbnailsModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void multiply(int a, int b, Promise promise) {
    promise.resolve(a * b);
  }

  public static native int nativeMultiply(int a, int b);

  @ReactMethod
  public void createThumbnail(String url, Callback callback) {
    ReactApplicationContext reactApplicationContext = getReactApplicationContext();
    RequestOptions myOptions = new RequestOptions()
      .override(300, 300);
    Glide.with(reactApplicationContext)
      .asBitmap()
      .apply(myOptions)
      .load(url) // your video url
      .centerCrop()
      .into(new CustomTarget<Bitmap>() {
        @Override
        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
          Uri uri = getImageUri(reactApplicationContext, resource);
          callback.invoke(uri.toString());
        }

        @Override
        public void onLoadCleared(@Nullable Drawable placeholder) {

        }
      });
  }

  public Uri getImageUri(Context context, Bitmap inImage) {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
    String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, UUID.randomUUID().toString() + ".png", "drawing");
    return Uri.parse(path);
  }
}
