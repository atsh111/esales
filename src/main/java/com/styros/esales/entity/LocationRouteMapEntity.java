package com.styros.esales.entity;

import javax.persistence.*;

/**
 * Created by atul on 12/6/2017.
 */
@Entity
@Table(name = "location_route_map", schema = "esales", catalog = "esales")
public class LocationRouteMapEntity {
    private int id;
    private Integer routeid;
    private Integer locationid;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "routeid", nullable = true)
    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
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

        LocationRouteMapEntity that = (LocationRouteMapEntity) o;

        if (id != that.id) return false;
        if (routeid != null ? !routeid.equals(that.routeid) : that.routeid != null) return false;
        if (locationid != null ? !locationid.equals(that.locationid) : that.locationid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (routeid != null ? routeid.hashCode() : 0);
        result = 31 * result + (locationid != null ? locationid.hashCode() : 0);
        return result;
    }
}
