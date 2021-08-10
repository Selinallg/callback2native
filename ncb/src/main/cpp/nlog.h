//
// Created by 86183 on 2020/12/18.
//


#ifndef NLVR_NLOG_H
#define NLVR_NLOG_H
#include <android/log.h>
#define LOG(...)
#define LOGCB(...) __android_log_print(ANDROID_LOG_ERROR,"sonic_prote_sdk",__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_INFO,"sonic_prote_sdk",__VA_ARGS__)
#define LOGT(...) __android_log_print(ANDROID_LOG_INFO,"sonic_prote_sdk",__VA_ARGS__)
#define LOGR(...) __android_log_print(ANDROID_LOG_INFO,"sonic_prote_sdk",__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,"sonic_prote_sdk",__VA_ARGS__)

#endif //NLVR_NLOG_H
