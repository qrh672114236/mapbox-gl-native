package com.mapbox.mapboxsdk.log;

import android.util.Log;

/**
 * Logger for the Mapbox Maps SDK for Android
 * <p>
 * Default implementation relies on {@link Log}.
 * Alternative implementations can be set with {@link #setLoggerDefinition(LoggerDefinition)}.
 * </p>
 */
public final class Logger {

  private static final LoggerDefinition DEFAULT = new LoggerDefinition() {

    private static final String TAG_TEMPLATE = "Mapbox - %s";

    @Override
    public void v(String tag, String msg) {
      Log.v(String.format(TAG_TEMPLATE, tag), msg);
    }

    @Override
    public void v(String tag, String msg, Throwable tr) {
      Log.v(String.format(TAG_TEMPLATE, tag), msg, tr);
    }

    @Override
    public void d(String tag, String msg) {
      Log.d(String.format(TAG_TEMPLATE, tag), msg);
    }

    @Override
    public void d(String tag, String msg, Throwable tr) {
      Log.d(String.format(TAG_TEMPLATE, tag), msg, tr);
    }

    @Override
    public void i(String tag, String msg) {
      Log.i(String.format(TAG_TEMPLATE, tag), msg);
    }

    @Override
    public void i(String tag, String msg, Throwable tr) {
      Log.i(String.format(TAG_TEMPLATE, tag), msg, tr);
    }

    @Override
    public void w(String tag, String msg) {
      Log.w(String.format(TAG_TEMPLATE, tag), msg);
    }

    @Override
    public void w(String tag, String msg, Throwable tr) {
      Log.w(String.format(TAG_TEMPLATE, tag), msg, tr);
    }

    @Override
    public void e(String tag, String msg) {
      Log.e(String.format(TAG_TEMPLATE, tag), msg);
    }

    @Override
    public void e(String tag, String msg, Throwable tr) {
      Log.e(String.format(TAG_TEMPLATE, tag), msg, tr);
    }
  };

  private static volatile LoggerDefinition logger = DEFAULT;

  /**
   * Replace the current used logger definition.
   *
   * @param loggerDefinition the definition of the logger
   */
  public static void setLoggerDefinition(LoggerDefinition loggerDefinition) {
    logger = loggerDefinition;
  }

  /**
   * Send a verbose log message.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  public static void v(String tag, String msg) {
    logger.v(tag, msg);
  }

  /**
   * Send a verbose log message and log the exception.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr  An exception to log
   */
  public static void v(String tag, String msg, Throwable tr) {
    logger.v(tag, msg, tr);
  }

  /**
   * Send a debug log message.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  public static void d(String tag, String msg) {
    logger.d(tag, msg);
  }

  /**
   * Send a debug log message and log the exception.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr  An exception to log
   */
  public static void d(String tag, String msg, Throwable tr) {
    logger.d(tag, msg, tr);
  }

  /**
   * Send an info log message.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  public static void i(String tag, String msg) {
    logger.i(tag, msg);
  }

  /**
   * Send an info log message and log the exception.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr  An exception to log
   */
  public static void i(String tag, String msg, Throwable tr) {
    logger.i(tag, msg, tr);
  }

  /**
   * Send a warning log message.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  public static void w(String tag, String msg) {
    logger.w(tag, msg);
  }

  /**
   * Send a warning log message and log the exception.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr  An exception to log
   */
  public static void w(String tag, String msg, Throwable tr) {
    logger.w(tag, msg, tr);
  }

  /**
   * Send an error log message.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  public static void e(String tag, String msg) {
    logger.e(tag, msg);
  }

  /**
   * Send an error log message and log the exception.
   *
   * @param tag Used to identify the source of a log message.  It usually identifies
   *            the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr  An exception to log
   */
  public static void e(String tag, String msg, Throwable tr) {
    logger.e(tag, msg, tr);
  }

  /**
   * Send a log message based on severity.
   *
   * @param severity the log severity
   * @param tag      Used to identify the source of a log message.  It usually identifies
   *                 the class or activity where the log call occurs.
   * @param message  The message you would like logged.
   */
  public static void log(int severity, String tag, String message) {
    LoggerDefinition.log(severity, tag, message);
  }
}