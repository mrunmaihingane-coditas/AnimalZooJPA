package model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int zoo_id;

    private String zoo_name;

    @OneToMany(mappedBy = "zoo",cascade = CascadeType.ALL)
    private List<Animal> animalList;

    public int getZoo_id() {
        return zoo_id;
    }

    public void setZoo_id(int zoo_id) {
        this.zoo_id = zoo_id;
    }

    public String getZoo_name() {
        return zoo_name;
    }

    public void setZoo_name(String zoo_name) {
        this.zoo_name = zoo_name;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
