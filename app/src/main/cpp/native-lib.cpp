//
// Created by Jeziel Lago on 2019-09-30.
//

#include "native-lib.h"
#include <iostream>
#include <cstdio>

using namespace std;
using namespace picosha2;

extern "C"
JNIEXPORT jstring JNICALL Java_com_jeziellago_jniteste_MainActivity_getAndroidId(JNIEnv *env, jobject context,jstring data) {



    jboolean isCopy;
    // generate SHA-256 from ANDROID_ID

    const char *convertedValue = env->GetStringUTFChars(data, &isCopy);
    string src_str = string(convertedValue);



    vector<unsigned char> hash(k_digest_size);
    hash256(src_str.begin(), src_str.end(), hash.begin(), hash.end());

    string hex_str = bytes_to_hex_string(hash.begin(), hash.end());

    return env->NewStringUTF(hex_str.c_str());
}

