#include <jni.h>
#include <string>
#include "nativecallback.h"
#include "nlog.h"
#include <dlfcn.h>


void dlopenExp() {
    LOGCB("---------dlopenExp start ---------------");

    typedef void (*ConnectedStatus)(int a);
    typedef int(*SetConnectedStatus_)(ConnectedStatus nfun);
    void *runtime_handle = dlopen(
//            "/system/app/ncb/base.apk/lib/arm64-v8a/libNoloVRClient.so",
            "/system/app/ncb/lib/arm64/libNoloVRClient.so",
            RTLD_LAZY);
    if (runtime_handle == nullptr) {
        LOGCB("---------dlopenExp runtime_handle== nullptr---------------");
        return;
    }
    SetConnectedStatus_ runtime = (SetConnectedStatus_) dlsym(runtime_handle, "SetConnectedStatus");
    if (runtime != nullptr) {
        LOGCB("---------dlopenExp runtime != nullptr---------------");
        ConnectedStatus s;
        runtime(s);
    } else {
        LOGCB("---------dlopenExp runtime == nullptr---------------");
    }

    dlclose(runtime_handle);


    LOGCB("---------dlopenExp finish ---------------");

}

/**
 * status = 0; 息屏
 * status = 1; 亮屏
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_nolovr_core_base_ncb_NoloFramework_setScreenStatus(JNIEnv *env, jclass clazz,
                                                            jint status) {
    int screenstate = (jint) status;
    if (Changed != NULL) {
        LOGCB("-------Changed != NULL-----------------");
        Changed(screenstate);
    } else {
        LOGCB("---------Changed == NULL---------------");
    }
}



extern "C"
JNIEXPORT void JNICALL
Java_com_nolovr_core_base_ncb_NoloFramework_testdlOpen(JNIEnv *env, jclass clazz) {
    dlopenExp();
}