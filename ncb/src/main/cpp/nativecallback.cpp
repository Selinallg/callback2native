#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_nolovr_core_base_ncb_NoloFramework_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}