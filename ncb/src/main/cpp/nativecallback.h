//
// Created by 86183 on 2021/8/10.
//

#ifndef CALLBACK2NATIVE_NATIVECALLBACK_H
#define CALLBACK2NATIVE_NATIVECALLBACK_H


/*
 * Method:   设备连接回调c#(unity)
 */
typedef void (*ScreenStatus)(int a);
void (* Changed)(int a);
int setScreenStatusFun(ScreenStatus nfun);

#endif //CALLBACK2NATIVE_NATIVECALLBACK_H
