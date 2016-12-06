import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Room {

    private int x;
    private int y;
    private int doorPosition;
    boolean isVisited;
    private List<Room> neighbours;

    Room(int x, int y, int doorPosition) {
        this.x = x;
        this.y = y;
        this.doorPosition = doorPosition;
        this.neighbours = new ArrayList<>();
    }

    void addNeighbour(Room room) {
        this.neighbours.add(room);
    }

    List<Room> getNeighbours() {
        return neighbours;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getDoorPosition() {
        return doorPosition;
    }

    private int dx() {
        return x * 50 + 50;
    }

    private int dy() {
        return y * 50 + 50;
    }

    void paint(Graphics g) {

        g.drawRect(this.dx(), this.dy(), 50, 50);
        this.paintDoor(g);
    }

    private void paintDoor(Graphics g) {
        g.setColor(new Color(156, 93, 82));

        if (this.doorPosition == 0)
            g.fillRect(this.dx() + 15, this.dy(), 20, 5);
        else if (this.doorPosition == 1)
            g.fillRect(this.dx() + 45, this.dy() + 15, 5, 20);
        else if (this.doorPosition == 2)
            g.fillRect(this.dx() + 15, this.dy() + 45, 20, 5);
        else if (this.doorPosition == 3)
            g.fillRect(this.dx(), this.dy() + 15, 5, 20);

        g.setColor(Color.black);
    }

    @Override
    public String toString() {
        return Integer.toString(this.x) + "," + Integer.toString(this.y);
    }
}
