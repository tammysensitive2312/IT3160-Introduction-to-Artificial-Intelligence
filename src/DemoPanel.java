import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DemoPanel extends JPanel {
    /** change the maxCol maxRow and NodeSize can change the window */
    // Screen settings
    final int maxCol = 15;
    final int maxRow = 10;
    // một cạnh của node
    final int nodeSize = 70;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    // each node is the button
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    boolean goalReached = false;
    // This variable is used in cases where it never reaches the goal node
    // and serves to exit the loop ...
    int step = 0;

    // window
    public DemoPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // place node on the screen
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
        // Set start and goal node
        /* Pass another parameter value to initialize the start and goal nodes
          can use jbutton to retrieve the node parameter just pressed
          and pass it to the function ...*/
        setStartNode(3, 6);
        setGoalNode(11, 3);

        // Place solid npdes
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);

        setCostOnNodes();
    }
    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;
        while (col < maxCol && row < maxRow) {
            getCost(node[col][row]);
            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }
    // Visualization of f and g values on nodes
    private void getCost(Node node) {
        // get the distance from the start node to the current node (g)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        // get the estimated distance from the current node to the goal (h)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;

        if (node != startNode && node != goalNode) {
            node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
        }
    }


    public void autoSearch() {
        while (!goalReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // down node
            if (row + 1 < maxRow) {
                openNode(node[col][row + 1]);
            }
            // left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // right node
            if (col + 1  < maxCol) {
                openNode(node[col + 1][row]);
            }

            // find the best node
            int bestNodeIndex = 0;
            int bestNodeCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodeCost) {
                    // check f value of each open node
                    bestNodeIndex = i;
                    bestNodeCost = openList.get(i).fCost;
                }
                else if (openList.get(i).fCost == bestNodeCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }

    // method used to browse adjacent nodes ... find the
    private void openNode(Node node) {
        if(!node.open && !node.checked && !node.solid) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    // method to draw the best path
    private void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            current = current.parent;
            if (current != startNode) {
                current.setAsPath();
            }
        }
    }
}
