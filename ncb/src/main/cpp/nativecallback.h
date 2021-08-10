//
// Created by 86183 on 2021/8/10.
//

#ifndef CALLBACK2NATIVE_NATIVECALLBACK_H
#define CALLBACK2NATIVE_NATIVECALLBACK_H


/*
 * Method:   设备连接回调c#(unity)
 */
typedef void (*ConnectedStatus)(int a);
void (* Connected)(int a);
int SetConnectedStatus(ConnectedStatus nfun);

#endif //CALLBACK2NATIVE_NATIVECALLBACK_H
