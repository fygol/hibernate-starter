package com.company.product.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_revision")
public class UserRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status_updated_by")
    private Long statusUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "status_updated_at")
    private Date statusUpdatedAt;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getStatusUpdatedBy() {
        return statusUpdatedBy;
    }

    public void setStatusUpdatedBy(Long statusUpdatedBy) {
        this.statusUpdatedBy = statusUpdatedBy;
    }

    public Date getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public void setStatusUpdatedAt(Date statusUpdatedAt) {
        this.statusUpdatedAt = statusUpdatedAt;
    }
}
