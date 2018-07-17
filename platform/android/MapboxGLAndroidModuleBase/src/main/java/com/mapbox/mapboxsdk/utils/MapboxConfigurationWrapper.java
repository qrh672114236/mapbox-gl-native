package com.mapbox.mapboxsdk.utils;

import android.content.Context;

/**
 * Application context wrapper, provides context to modular components.
 * <p>
 * Initialised as part of Mapbox.java.
 * </p>
 */
public class MapboxConfigurationWrapper {

  private static MapboxConfigurationWrapper INSTANCE;
  private String accessToken;
  private Context context;

  public static synchronized MapboxConfigurationWrapper initialize(Context context, String accessToken) {
    if (INSTANCE == null) {
      INSTANCE = new MapboxConfigurationWrapper(context.getApplicationContext(), accessToken);
    }
    return INSTANCE;
  }

  private MapboxConfigurationWrapper(Context appContext, String token) {
    context = appContext;
    accessToken = token;
  }

  public static Context getContext() {
    Context context = INSTANCE.context;
    if (context == null) {
      throw new IllegalStateException("MapboxConfigurationWrapper isn't initialised correctly.");
    }
    return context;
  }

  public static String getAccessToken() {
    if (INSTANCE.accessToken == null) {
      throw new IllegalStateException("MapboxConfigurationWrapper isn't initialised correctly.");
    }
    return INSTANCE.accessToken;
  }
}
