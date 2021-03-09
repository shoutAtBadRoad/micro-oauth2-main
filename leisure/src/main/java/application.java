import annotation.FieldName;
import annotation.person;
import entity.Person;

import java.lang.reflect.Field;

public class application {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = Person.builder().age(10).build();
        Class aClass = person.getClass();
        if(aClass.isAnnotationPresent(annotation.person.class)){
            System.out.println("element has annotation");
            annotation.person annotation = (annotation.person) aClass.getAnnotation(annotation.person.class);
            person.setName(annotation.name());
            person.setAge(annotation.age());
        }
        Field[] fields = aClass.getDeclaredFields();
        for(Field field :fields) {
            if (field.isAnnotationPresent(FieldName.class)) {
                FieldName annotation = (FieldName) field.getAnnotation(FieldName.class);
                field.setAccessible(true);
                System.out.println(annotation.value()+":"+field.get(person));
            }
        }

    }

}
