package org.example.waypoint;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class WaypointRender extends WaypointPainter<MyWaypoint> {
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (MyWaypoint waypoint : getWaypoints()) {
            Point2D p = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
            Rectangle rect = map.getViewportBounds();
            int x = (int) (p.getX() - rect.getX());
            int y = (int) (p.getY() - rect.getY());

            JButton cmd = waypoint.getButton();
            cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
        }
    }
}
