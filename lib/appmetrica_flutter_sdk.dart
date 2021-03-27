import 'dart:async';

import 'package:flutter/services.dart';

class AppmetricaFlutterSdk {
  static const MethodChannel _channel =
  const MethodChannel('appmetrica_flutter_sdk');

  static Future<bool> activate(apiKey) async {
    final arguments = <String, dynamic>{
      'apiKey': apiKey,
      'locationTracking': true,
      'crashReporting': true,
    };

    final bool result = await _channel.invokeMethod('activate', arguments);

    return result;
  }
}
