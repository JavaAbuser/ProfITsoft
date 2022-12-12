package lectures5and6.task6.models;

import lectures5and6.task6.annotations.Property;

import java.time.Instant;
import java.util.Objects;

public class Human5 {
    private String name;
    private Integer age;
    private String surname;

    private int rate;
    @Property(format = "yyyy.dd.MM HH:mm:ss")
    private Instant date;

    public Human5() {
    }

    public Human5(String name, Integer age, String surname, int rate, Instant date) {
        this.name = name;
        this.age = age;
        this.surname = surname;
        this.rate = rate;
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
        if (!(o instanceof Human5)) return false;
        Human5 human = (Human5) o;
        return rate == human.rate && name.equals(human.name) && age.equals(human.age) && surname.equals(human.surname) && date.equals(human.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, surname, rate, date);
    }
}
