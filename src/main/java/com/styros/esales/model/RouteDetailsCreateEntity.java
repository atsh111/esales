package com.styros.esales.model;

import java.util.List;

/**
 * Created by atul on 12/2/2017.
 */
public class RouteDetailsCreateEntity {
    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public int getRouteId() {

        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    int routeId;
    List<Integer> users;
}
