package lectures5and6.task6.models;

import java.time.Instant;
import java.util.Objects;

public class Human {
    private String name;
    private Integer age;
    private String surname;
    private int rate;
    private Instant date;

    public Human() {
    }

    public Human(String name, Integer age, String surname, int mark, Instant date) {
        this.name = name;
        this.age = age;
        this.surname = surname;
        this.rate = mark;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                ", rate=" + rate +
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return rate == human.rate && name.equals(human.name) && age.equals(human.age) && surname.equals(human.surname) && date.equals(human.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, surname, rate, date);
    }
}
