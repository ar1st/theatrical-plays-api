package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "production")
public class Production implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "OrganizerID")
    private Integer organizerID;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "URL", nullable = false)
    private String URL;

    @Column(name = "Producer", nullable = false)
    private String producer;

    @Column(name = "MediaURL", nullable = false)
    private String mediaURL;

    @Column(name = "Duration", nullable = false)
    private String duration;

    @Column(name = "SystemID", nullable = false)
    private Integer systemID;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "Description", nullable = false)
    private String description;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setOrganizerID(Integer organizerID) {
        this.organizerID = organizerID;
    }

    public Integer getOrganizerID() {
        return organizerID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Production{" +
                "ID=" + ID + '\'' +
                "organizerID=" + organizerID + '\'' +
                "title=" + title + '\'' +
                "URL=" + URL + '\'' +
                "producer=" + producer + '\'' +
                "mediaURL=" + mediaURL + '\'' +
                "duration=" + duration + '\'' +
                "systemID=" + systemID + '\'' +
                "timestamp=" + timestamp + '\'' +
                "description=" + description + '\'' +
                '}';
    }
}
