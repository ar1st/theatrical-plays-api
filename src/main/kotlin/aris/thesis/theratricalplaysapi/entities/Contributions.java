package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contributions")
public class Contributions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "PeopleID", nullable = false)
    private Integer peopleID;

    @Column(name = "ProductionID", nullable = false)
    private Integer productionID;

    @Column(name = "RoleID", nullable = false)
    private Integer roleID;

    @Column(name = "subRole")
    private String subRole;

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

    public void setPeopleID(Integer peopleID) {
        this.peopleID = peopleID;
    }

    public Integer getPeopleID() {
        return peopleID;
    }

    public void setProductionID(Integer productionID) {
        this.productionID = productionID;
    }

    public Integer getProductionID() {
        return productionID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setSubRole(String subRole) {
        this.subRole = subRole;
    }

    public String getSubRole() {
        return subRole;
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
        return "Contributions{" +
                "ID=" + ID + '\'' +
                "peopleID=" + peopleID + '\'' +
                "productionID=" + productionID + '\'' +
                "roleID=" + roleID + '\'' +
                "subRole=" + subRole + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}';
    }
}
