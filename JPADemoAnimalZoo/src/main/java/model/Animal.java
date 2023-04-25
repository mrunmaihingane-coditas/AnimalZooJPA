package model;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animal_id;

    private String animal_name;

    private Date animail_enitredDate;

    private int animal_age;

    private String animal_catogery;

    public Animal(String animal_name, Date animail_enitredDate, int animal_age, String animal_catogery) {
        this.animal_name = animal_name;
        this.animail_enitredDate = animail_enitredDate;
        this.animal_age = animal_age;
        this.animal_catogery = animal_catogery;
    }

    public Animal() {
    }

    @ManyToOne
    private Zoo zoo;

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public Date getAnimail_enitredDate() {
        return animail_enitredDate;
    }

    public void setAnimail_enitredDate(Date animail_enitredDate) {
        this.animail_enitredDate = animail_enitredDate;
    }

    public int getAnimal_age() {
        return animal_age;
    }

    public void setAnimal_age(int animal_age) {
        this.animal_age = animal_age;
    }

    public String getAnimal_catogery() {
        return animal_catogery;
    }

    public void setAnimal_catogery(String animal_catogery) {
        this.animal_catogery = animal_catogery;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animal_id=" + animal_id +
                ", animal_name='" + animal_name + '\'' +
                ", animail_enitredDate=" + animail_enitredDate +
                ", animal_age=" + animal_age +
                ", animal_catogery='" + animal_catogery + '\'' +
                ", zoo=" + zoo +
                '}';
    }
}
