
package com.fidme.faststorage;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.tencent.mmkv.MMKV;

public class RNFastStorageModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNFastStorageModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNFastStorage";
  }

  @ReactMethod
  public void setupLibrary() {
    MMKV.initialize(getReactApplicationContext());
  }

  @ReactMethod
  public void setItem(String key, String value, Promise promise) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      kv.encode(key, value);
      promise.resolve(value);
    } catch (Error | Exception e) {
      promise.reject("Error", "Unable to setItem");
    }
  }

  @ReactMethod
  public void getItem(String key, Promise promise) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      promise.resolve(kv.decodeString(key));
    } catch (Error | Exception e) {
      promise.reject("Error", "Unable to getItem");
    }
  }

  @ReactMethod
  public void removeItem(String key, Promise promise) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      kv.removeValueForKey(key);
      promise.resolve(key);
    } catch (Error | Exception e) {
      promise.reject("Error", "Unable to removeItem");
    }
  }

  @ReactMethod
  public void clearStore(Promise promise) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      kv.clearAll();
      promise.resolve("Done");
    } catch (Error | Exception e) {
      promise.reject("Error", "Unable to removeItem");
    }
  }
}