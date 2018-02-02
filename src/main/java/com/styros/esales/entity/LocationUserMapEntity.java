package com.styros.esales.entity;

import javax.persistence.*;

/**
 * Created by atul on 12/6/2017.
 */
@Entity
@Table(name = "location_user_map", schema = "esales", catalog = "esales")
public class LocationUserMapEntity {
    private int id;
    private Integer userid;
    private Integer locationid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "locationid", nullable = true)
    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationUserMapEntity that = (LocationUserMapEntity) o;

        if (id != that.id) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (locationid != null ? !locationid.equals(that.locationid) : that.locationid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (locationid != null ? locationid.hashCode() : 0);
        return result;
    }
}
