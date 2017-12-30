package hr;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
