package com.styros.esales.model;

import java.util.List;

/**
 * Created by atul on 12/2/2017.
 */
public class RouteDetails {
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    int routeId;

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<User> getUnassignedUsers() {
        return unassignedUsers;
    }

    public void setUnassignedUsers(List<User> unassignedUsers) {
        this.unassignedUsers = unassignedUsers;
    }

    List<User> unassignedUsers;
    String routeName;
    List<User> users;

}
