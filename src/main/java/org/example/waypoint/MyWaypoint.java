package org.example.waypoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
@NoArgsConstructor
public class MyWaypoint extends DefaultWaypoint {
    private JButton button;

    public MyWaypoint(GeoPosition coord, JButton button) {
        super(coord);
        this.button = button;
    }

    private void initButton() {
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click");
            }
        });
    }
}
