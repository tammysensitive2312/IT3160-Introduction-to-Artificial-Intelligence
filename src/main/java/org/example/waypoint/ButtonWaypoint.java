package org.example.waypoint;

import javax.swing.*;
import java.awt.*;

public class ButtonWaypoint extends JButton {
    public ButtonWaypoint() {
        setContentAreaFilled(false);
        ImageIcon icon = new ImageIcon("D:\\Map-PathFinding\\src\\main\\resources\\pin.png");
        setIcon(icon);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24, 24));
    }
}
