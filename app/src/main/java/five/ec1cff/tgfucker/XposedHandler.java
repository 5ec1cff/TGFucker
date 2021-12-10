package five.ec1cff.tgfucker;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedHandler implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("org.telegram.messenger")) {
            XposedHelpers.findAndHookMethod(
                    XposedHelpers.findClass("org.telegram.messenger.MessagesController", lpparam.classLoader),
                    "isChatNoForwards",
                    XposedHelpers.findClass("org.telegram.tgnet.TLRPC$Chat", lpparam.classLoader),
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(false);
                        }
                    }
            );
        }
    }
}
