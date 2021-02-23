package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "events")
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "ProductionID", nullable = false)
    private Integer productionID;

    @Column(name = "VenueID", nullable = false)
    private Integer venueID;

    @Column(name = "DateEvent", nullable = false)
    private Date dateEvent;

    @Column(name = "PriceRange", nullable = false)
    private String priceRange;

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

    public void setProductionID(Integer productionID) {
        this.productionID = productionID;
    }

    public Integer getProductionID() {
        return productionID;
    }

    public void setVenueID(Integer venueID) {
        this.venueID = venueID;
    }

    public Integer getVenueID() {
        return venueID;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getPriceRange() {
        return priceRange;
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
        return "Events{" +
                "ID=" + ID + '\'' +
                "productionID=" + productionID + '\'' +
                "venueID=" + venueID + '\'' +
                "dateEvent=" + dateEvent + '\'' +
                "priceRange=" + priceRange + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}';
    }
}
