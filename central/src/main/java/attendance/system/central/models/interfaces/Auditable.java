//package attendance.system.central.models.interfaces;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.Instant;
//
///**
// * @author Nitesh (niteshsrivats.k@gmail.com)
// */
//
//@MappedSuperclass
//public abstract class Auditable {
//
//    @Column(nullable = false, updatable = false)
//    @CreatedDate
//    @JsonIgnore
//    private Instant createdAt;
//
//    @Column(nullable = false)
//    @LastModifiedDate
//    @JsonIgnore
//    private Instant updatedAt;
//
//    public Instant getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Instant createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Instant getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Instant updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//}
