package projecttest.javaspringtest.Native;

public class TestJNI {

    /**
     * 네이티브 라이브러리 로드 (파일명은 OS가 알아서 접두/확장자 붙임)
     * Windows: TestJNI.dll
     * Linux  : libTestJNI.so
     * macOS  : libTestJNI.dylib
     */
    static { //dll 설정 // 원래 이방식 안쓰는데, 절대경로로 쓰려면 System.load 해야됨.
        System.load("C:\\Users\\SHPS\\Desktop\\자바\\JAVA - SPRING TEST\\Native\\TestJNI.dll");
    }


    // native 메서드 선언 (구현은 C에 있음)
    public native int add(int a, int b);
}
