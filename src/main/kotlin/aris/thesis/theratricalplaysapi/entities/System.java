package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system")
public class System implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "name", nullable = false)
    private String name;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "System{" +
                "ID=" + ID + '\'' +
                "name=" + name + '\'' +
                '}';
    }
}
