package kr.ac.hansung.entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass

/*
 * 매핑정보만 상속받는 Superclass라는 의미의 @MappedSuperclass 어노테이션 

 * 공통 매핑 정보(id)가 필요할 때, 부모 클래스에 선언하고 
 * 속성만 상속 받아서 사용하고 싶을 때 @MappedSuperclass를 사용
 * @MappedSuperclass가 선언되어 있는 클래스는 엔티티가 아니기 때문에 당연히 테이블과 매핑도 안된다.  
 * 즉, 단순히 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공한다.
 */
public class AbstractEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.id, AbstractEntity.class.cast(obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
