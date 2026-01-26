package projecttest.javaspringtest.Native;

import java.io.File;
import java.io.IOException;

public class TestJNI {
    static File file = new File("Native\\TestJNI.dll"); // 일반적으로 모든 컴퓨터가 JAVA HOME 이 설정안되어있으니 절대경로 사용하기위해 사용

    /**
     * 네이티브 라이브러리 로드 (파일명은 OS가 알아서 접두/확장자 붙임)
     * Windows: TestJNI.dll
     * Linux  : libTestJNI.so
     * macOS  : libTestJNI.dylib
     */
    static { //dll 설정 // 원래 이방식 안쓰는데, 절대경로로 쓰려면 System.load 해야됨.
        try {
            System.load(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // native 메서드 선언 (구현은 C에 있음)
    public native int add(int a, int b);
}
