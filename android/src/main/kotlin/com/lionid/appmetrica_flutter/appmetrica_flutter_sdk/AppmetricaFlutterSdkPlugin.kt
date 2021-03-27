package com.lionid.appmetrica_flutter.appmetrica_flutter_sdk

import android.util.Log;
import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


class AppmetricaFlutterSdkPlugin: FlutterPlugin, MethodCallHandler {
  private val CHANNEL_NAME = "appmetrica_flutter_sdk"


  private lateinit var channel : MethodChannel
  private var applicationContext: Context? = null
  private var application: Application? = null

  override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    applicationContext = binding.applicationContext
    application = applicationContext as Application
    channel = MethodChannel(binding.binaryMessenger, CHANNEL_NAME)
    channel.setMethodCallHandler(this)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null);
    applicationContext = null;
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when (call.method) {
      "activate" -> handleActivate(call, result)
      else -> result.notImplemented()
    }
  }


  private fun handleActivate(call: MethodCall, result: Result) {
    try {
      val arguments = call.arguments as Map<*, *>

      // Get activation parameters.
      val apiKey = arguments["apiKey"] as String?
      val locationTracking = arguments["locationTracking"] as Boolean
      val crashReporting = arguments["crashReporting"] as Boolean

      // Creating an extended library configuration.
      val config = YandexMetricaConfig.newConfigBuilder(apiKey!!)
              .withLogs()
              .withLocationTracking(locationTracking)
              .withCrashReporting(crashReporting)
              .build()

      YandexMetrica.activate(applicationContext!!, config);
      YandexMetrica.enableActivityAutoTracking(application!!);
    } catch (e: Exception) {
      Log.e(CHANNEL_NAME, e.message, e);
      return result.success(false)
    }
    result.success(true)
  }
}
