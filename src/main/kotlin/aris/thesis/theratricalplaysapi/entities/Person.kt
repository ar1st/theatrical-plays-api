package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "persons")
@Entity
public class Persons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "Fullname", nullable = false)
    private String fullname;

    @Column(name = "SystemID", nullable = false)
    private Integer systemID;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setSystemID(Integer systemID) {
        this.systemID = systemID;
    }

    public Integer getSystemID() {
        return systemID;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "ID=" + ID + '\'' +
                "fullname=" + fullname + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}';
    }
}
