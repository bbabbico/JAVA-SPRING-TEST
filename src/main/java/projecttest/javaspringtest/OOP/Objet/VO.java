package projecttest.javaspringtest.OOP.Objet;


import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public final class VO { //불변성 , 동등성 , 자가 검증 -> 읽기전용 값 객체
    /**
     * 불변성을 위한 final 필드 <br>
     * 가변객체를 참조하거나, 객체의 상속, 스레드 필드 동기화 로도 불변성이 깨질수 있으니 확인요망
     */
    private final int name;
    private final int age;

    /**
     * <h2>동등성 메서드</h2>
     * {@code  equals}
     * {@code hashCode}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VO) obj;
        return this.name == that.name &&
                this.age == that.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

}
