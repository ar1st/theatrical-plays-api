package aris.thesis.theratricalplaysapi.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "changeLog")
class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var iD: Int? = null

    @Column(name = "EventType", nullable = false)
    var eventType: String? = null

    @Column(name = "TableName", nullable = false)
    var tableName: String? = null

    @Column(name = "Value", nullable = false)
    var value: String? = null

    @Column(name = "CollumnName", nullable = false)
    var collumnName: String? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: Date? = null
    override fun toString(): String {
        return "ChangeLog{" +
                "ID=" + iD + '\'' +
                "eventType=" + eventType + '\'' +
                "tableName=" + tableName + '\'' +
                "value=" + value + '\'' +
                "collumnName=" + collumnName + '\'' +
                "timestamp=" + timestamp + '\'' +
                '}'
    }
}