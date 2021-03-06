package entity;

import annotation.FieldName;
import annotation.person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@person(name = "lt",age = 18)
public class Person {

    @FieldName(value = "姓名")
    private String name;

    @FieldName(value = "年龄")
    private int age;
}
