#include <jni.h>
#include <stdio.h>

// 클래스가 디폴트 패키지(패키지 선언 없음)이면: Java_<ClassName>_<MethodName>
// 헤더파일 생성하면 자동으로 함수명 바뀜. C 파일 함수명이랑 헤더파일 함수명이랑 일치 해야됨.
JNIEXPORT jint JNICALL Java_projecttest_javaspringtest_Native_TestJNI_add(JNIEnv* env, jobject obj, jint a, jint b) {
    return a + b;
}
