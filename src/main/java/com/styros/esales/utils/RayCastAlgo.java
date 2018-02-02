package com.styros.esales.utils;

import java.util.List;

/**
 * Created by atul on 12/5/2017.
 */
public class RayCastAlgo {
    public List<Vertex> lstvertices;
    /*public Vertex[] _vertices;*/
    public Vertex point;
    public boolean isInside(){
        boolean inside=false;
        Double x = point.X, y = point.Y;

        for (int i = 0, j = lstvertices.size() - 1; i < lstvertices.size(); j = i++) {
            double xi = lstvertices.get(i).X, yi = lstvertices.get(i).Y;
            double xj = lstvertices.get(j).X, yj = lstvertices.get(j).Y;

            boolean intersect = ((yi > y) != (yj > y))
                    && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            if (intersect) inside = !inside;
        }

        return inside;

    }

}
