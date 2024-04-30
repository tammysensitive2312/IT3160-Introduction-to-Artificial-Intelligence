package org.example.map;

import org.example.waypoint.MyWaypoint;
import org.example.waypoint.WaypointRender;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.HashSet;
import java.util.Set;

public class MapViewer {
    private final JXMapViewer mapViewer = new JXMapViewer();

    public MapViewer() {
        mapViewer.setTileFactory(new DefaultTileFactory((new OSMTileFactoryInfo())));

        GeoPosition initialPosition = new GeoPosition(21.028511, 105.804817);
        mapViewer.setAddressLocation(initialPosition);
        mapViewer.setZoom(12);

        WaypointRender render = new WaypointRender();
        Set<MyWaypoint> waypoints = new HashSet<>();
        render.setWaypoints(waypoints);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>();
        painter.setPainters(render);
        mapViewer.setOverlayPainter(painter);

        MapController controller = new MapController(mapViewer, waypoints, render);
//        MapController controller = new MapController(mapViewer);
        controller.setup();
    }

    public JXMapViewer getMapComponent() {
        return mapViewer;
    }
}
