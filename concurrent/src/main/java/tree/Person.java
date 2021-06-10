package tree;

import java.util.Random;

public class Person {

    public int age;

    Person(int a){
        this.age = a;
    }

    public int compare(Person person){
        if(this.age==person.age)
            return 0;
        return this.age > person.age ? 1 : -1;
    }

    public static void main(String[] args) {
        Btree<Person> personBtree = new Btree<>(new Person(50));
        Random random = new Random();
        for(int i=0;i<5;i++){
            personBtree.insert(new Person(random.nextInt(100)));
        }
        personBtree = personBtree.remove(personBtree.val);
        Btree.first(personBtree);
        personBtree = personBtree.remove(personBtree.left.val);
        Btree.first(personBtree);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
