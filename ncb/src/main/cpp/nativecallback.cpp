#include <jni.h>
#include <string>
#include "nativecallback.h"
#include "nlog.h"


/**
 * status = 0; 息屏
 * status = 1; 亮屏
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_nolovr_core_base_ncb_NoloFramework_setScreenStatus(JNIEnv *env, jclass clazz, jint status) {
    int screenstate = (jint) status;
    if (Changed != NULL) {
        LOGCB("-------Changed != NULL-----------------");
        Changed(screenstate);
    } else {
        LOGCB("---------Changed == NULL---------------");
    }
}