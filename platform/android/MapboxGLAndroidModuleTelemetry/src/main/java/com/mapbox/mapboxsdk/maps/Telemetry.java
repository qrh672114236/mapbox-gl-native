package com.mapbox.mapboxsdk.maps;

import android.content.Context;
import com.mapbox.android.telemetry.AppUserTurnstile;
import com.mapbox.android.telemetry.Event;
import com.mapbox.android.telemetry.MapEventFactory;
import com.mapbox.android.telemetry.MapState;
import com.mapbox.android.telemetry.MapboxTelemetry;
import com.mapbox.android.telemetry.SessionInterval;
import com.mapbox.android.telemetry.TelemetryEnabler;
import com.mapbox.mapboxsdk.module.BuildConfig;
import com.mapbox.mapboxsdk.utils.MapboxConfigurationWrapper;

import java.lang.reflect.Field;

public class Telemetry implements TelemetryBase {

  private static Telemetry instance;
  private MapboxTelemetry telemetry;

  /**
   * Get a single instance of Telemetry.
   *
   * @return instance of Telemetry
   * @deprecated reference instance from Mapbox.java instead
   */
  @Deprecated
  public static synchronized Telemetry getInstance() {
    if (instance == null) {
      instance = new Telemetry();
    }
    return instance;
  }

  private Telemetry() {
    Context appContext = MapboxConfigurationWrapper.getContext();
    String accessToken = MapboxConfigurationWrapper.getAccessToken();
    telemetry = new MapboxTelemetry(appContext, accessToken, BuildConfig.MAPBOX_EVENTS_USER_AGENT);
    TelemetryEnabler.State telemetryState = TelemetryEnabler.retrieveTelemetryStateFromPreferences();
    if (TelemetryEnabler.State.ENABLED.equals(telemetryState)) {
      telemetry.enable();
    }
  }

  /**
   * Register the app user turnstile event
   */
  @Override
  public void onAppUserTurnstileEvent() {
    AppUserTurnstile turnstileEvent = new AppUserTurnstile(BuildConfig.MAPBOX_SDK_IDENTIFIER,
      BuildConfig.MAPBOX_SDK_VERSION);
    telemetry.push(turnstileEvent);
    MapEventFactory mapEventFactory = new MapEventFactory();
    telemetry.push(mapEventFactory.createMapLoadEvent(Event.Type.MAP_LOAD));
  }

  /**
   * Register an end-user gesture interaction event.
   *
   * @param eventType type of gesture event occurred
   * @param latitude  the latitude value of the gesture focal point
   * @param longitude the longitude value of the gesture focal point
   * @param zoom      current zoom of the map
   */
  @Override
  public void onGestureInteraction(String eventType, double latitude, double longitude, double zoom) {
    MapEventFactory mapEventFactory = new MapEventFactory();
    MapState state = new MapState(latitude, longitude, zoom);
    state.setGesture(eventType);
    telemetry.push(mapEventFactory.createMapGestureEvent(Event.Type.MAP_CLICK, state));
  }

  /**
   * Set the end-user selected state to participate or opt-out in telemetry collection.
   */
  @Override
  public void setUserTelemetryRequestState(boolean enabledTelemetry) {
    if (enabledTelemetry) {
      TelemetryEnabler.updateTelemetryState(TelemetryEnabler.State.ENABLED);
      telemetry.enable();
    } else {
      telemetry.disable();
      TelemetryEnabler.updateTelemetryState(TelemetryEnabler.State.DISABLED);
    }
  }

  /**
   * Set the debug logging state of telemetry.
   *
   * @param debugLoggingEnabled true to enable logging
   */
  @Override
  public void setDebugLoggingEnabled(boolean debugLoggingEnabled) {
    telemetry.updateDebugLoggingEnabled(debugLoggingEnabled);
  }

  /**
   * Set the telemetry rotation session id interval
   *
   * @param interval the selected session interval
   * @return true if rotation session id was updated
   */
  @Override
  public boolean setSessionIdRotationInterval(int interval) {
    return telemetry.updateSessionIdRotationInterval(new SessionInterval(interval));
  }

  /**
   * Set the debug logging state of telemetry.
   *
   * @param debugLoggingEnabled true to enable logging
   * @deprecated use {@link #setDebugLoggingEnabled(boolean)} instead
   */
  @Deprecated
  public static void updateDebugLoggingEnabled(boolean debugLoggingEnabled) {
    getInstance().setDebugLoggingEnabled(debugLoggingEnabled);
  }

  /**
   * Update the telemetry rotation session id interval
   *
   * @param interval the selected session interval
   * @return true if rotation session id was updated
   * @deprecated use {@link #setSessionIdRotationInterval(int)} instead
   */
  @Deprecated
  public static boolean updateSessionIdRotationInterval(SessionInterval interval) {
    try {
      Field field = interval.getClass().getDeclaredField("interval");
      field.setAccessible(true);
      Integer intervalValue = (Integer) field.get(interval);
      return getInstance().setSessionIdRotationInterval(intervalValue);
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * Method to be called when an end-user has selected to participate in telemetry collection.
   *
   * @deprecated use {@link #setUserTelemetryRequestState(boolean)} with parameter true instead
   */
  @Deprecated
  public static void enableOnUserRequest() {
    getInstance().setUserTelemetryRequestState(true);
  }

  /**
   * Method to be called when an end-user has selected to opt-out of telemetry collection.
   *
   * @deprecated use {@link #setUserTelemetryRequestState(boolean)} with parameter false instead
   */
  @Deprecated
  public static void disableOnUserRequest() {
    getInstance().setUserTelemetryRequestState(false);
  }
}
