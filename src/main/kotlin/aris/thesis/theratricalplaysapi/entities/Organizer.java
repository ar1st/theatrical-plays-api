package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "organizer")
public class Organizer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Town", nullable = false)
    private String town;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Doy", nullable = false)
    private String doy;

    @Column(name = "Afm", nullable = false)
    private String afm;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return town;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDoy(String doy) {
        this.doy = doy;
    }

    public String getDoy() {
        return doy;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAfm() {
        return afm;
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
        return "Organizer{" +
                "ID=" + ID + '\'' +
                "name=" + name + '\'' +
                "address=" + address + '\'' +
                "town=" + town + '\'' +
                "postcode=" + postcode + '\'' +
                "phone=" + phone + '\'' +
                "email=" + email + '\'' +
                "doy=" + doy + '\'' +
                "afm=" + afm + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}';
    }
}
