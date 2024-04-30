package org.example.map;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    public MapPanel(MapViewer mapViewer) {
        this.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton zoomInButton = new JButton("+");
        zoomInButton.addActionListener(e -> mapViewer.getMapComponent().setZoom(mapViewer.getMapComponent().getZoom() - 1));

        JButton zoomOutButton = new JButton("-");
        zoomOutButton.addActionListener(e -> mapViewer.getMapComponent().setZoom(mapViewer.getMapComponent().getZoom() + 1));

        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
