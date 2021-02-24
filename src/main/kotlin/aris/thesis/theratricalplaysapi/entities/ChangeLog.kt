package aris.thesis.theratricalplaysapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "changeLog")
public class ChangeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;

    @Column(name = "EventType", nullable = false)
    private String eventType;

    @Column(name = "TableName", nullable = false)
    private String tableName;

    @Column(name = "Value", nullable = false)
    private String value;

    @Column(name = "CollumnName", nullable = false)
    private String collumnName;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setCollumnName(String collumnName) {
        this.collumnName = collumnName;
    }

    public String getCollumnName() {
        return collumnName;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ChangeLog{" +
                "ID=" + ID + '\'' +
                "eventType=" + eventType + '\'' +
                "tableName=" + tableName + '\'' +
                "value=" + value + '\'' +
                "collumnName=" + collumnName + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}';
    }
}
