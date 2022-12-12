package lectures5and6.task6.models;

import lectures5and6.task6.annotations.Property;

import java.time.Instant;
import java.util.Objects;

public class Human4 {
    private String name;
    private Integer age;
    private String surname;
    @Property(value = "ra")
    private int mark;
    private Instant date;

    public Human4() {
    }

    public Human4(String name, Integer age, String surname, int mark, Instant date) {
        this.name = name;
        this.age = age;
        this.surname = surname;
        this.mark = mark;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                ", mark=" + mark +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human4)) return false;
        Human4 human = (Human4) o;
        return mark == human.mark && name.equals(human.name) && age.equals(human.age) && surname.equals(human.surname) && date.equals(human.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, surname, mark, date);
    }
}
