#import "AppmetricaFlutterSdkPlugin.h"
#if __has_include(<appmetrica_flutter_sdk/appmetrica_flutter_sdk-Swift.h>)
#import <appmetrica_flutter_sdk/appmetrica_flutter_sdk-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "appmetrica_flutter_sdk-Swift.h"
#endif

@implementation AppmetricaFlutterSdkPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAppmetricaFlutterSdkPlugin registerWithRegistrar:registrar];
}
@end
