package org.example;

import org.example.map.MapPanel;
import org.example.map.MapViewer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map viewer example");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        MapViewer viewer = new MapViewer();
        frame.getContentPane().add(viewer.getMapComponent());

        MapPanel panel = new MapPanel(viewer);
        frame.add(panel, BorderLayout.SOUTH);
    }
}