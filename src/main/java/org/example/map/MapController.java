package org.example.map;

import lombok.RequiredArgsConstructor;
import org.example.waypoint.ButtonWaypoint;
import org.example.waypoint.EventWaypoint;
import org.example.waypoint.MyWaypoint;
import org.example.waypoint.WaypointRender;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;


@RequiredArgsConstructor
public class MapController {
    private final JXMapViewer mapViewer;
    private final Set<MyWaypoint> waypoints;
    private final WaypointRender render;

    public void setup() {
        EventWaypoint event;

        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
//                if (e.getClickCount() >= 2) {
//                    Rectangle rect = mapViewer.getViewportBounds();
//                    int x = rect.x + p.x;
//                    int y = rect.y + p.y;
//                    GeoPosition geo = mapViewer.getTileFactory().pixelToGeo(new Point(x, y), mapViewer.getZoom());
//                    mapViewer.setCenterPosition(geo);
//                    mapViewer.setZoom(mapViewer.getZoom() - 1);
//                } else
                if (e.getClickCount()  == 1) {
                    GeoPosition position = mapViewer.convertPointToGeoPosition(e.getPoint());

                    ButtonWaypoint button = new ButtonWaypoint();
                    MyWaypoint waypoint = new MyWaypoint(position, button);
                    waypoints.add(waypoint);

                    render.setWaypoints(waypoints);
                    mapViewer.add(waypoint.getButton());
                }
            }
        });

    }

//
}
