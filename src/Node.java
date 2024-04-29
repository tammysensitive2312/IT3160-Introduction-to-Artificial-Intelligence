import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    Node parent;
    int col;
    int row;
    // paramaters for hreustics f = h + g
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.BLACK);
        addActionListener(this);
    }

    // set start node
    public void setAsStart() {
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
//        setText("Start");
        start = true;
    }

    // set end node
    public void setAsGoal() {
        setBackground(Color.YELLOW);
        setForeground(Color.BLACK);
//        setText("Goal");
        goal = true;
    }

    public void setAsSolid() {
        setBackground(Color.black);
        setForeground(Color.BLACK);
        solid = true;
    }

    public void setAsOpen() {
        open = true;
    }
    public void setAsChecked() {
        if (!start && !goal) {
            setBackground(Color.ORANGE);
            setForeground(Color.BLACK);
        }
        checked = true;
    }

    public void setAsNormal() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        start = false;
        goal = false;
        solid = false;
    }

    public void setAsPath() {
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
    }

    public void reset() {
        parent = null;
        gCost = 0;
        hCost = 0;
        fCost = 0;
        start = false;
        goal = false;
        solid = false;
        open = false;
        checked = false;
        setText("");
        setBackground(Color.white);
        setForeground(Color.BLACK);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.ORANGE);
        if (!start && !goal && !solid) {
            if (((DemoPanel) getParent()).setStartOrGoalNode(this)) {
                if (start) {
                    setAsStart();
                } else if (goal) {
                    setAsGoal();
                }
            }
        }

        if (!start && !goal) {
            if (!solid) {
                setAsSolid();
            } else {
                setAsNormal();
            }
        }
    }

}
