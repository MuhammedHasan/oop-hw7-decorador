import javax.swing.*;
import java.awt.*;
import java.util.*;

class Maze extends JPanel {

    private int length;
    private HashMap<Integer, Room> rooms;
    private Mouse mouse;

    Maze(int size) {
        this.rooms = new HashMap<>();
        this.length = size;
    }

    void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    Mouse getMouse() {
        return mouse;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Room room : this.rooms.values()) room.paint(g);
        mouse.paint(g);
    }

    void render() {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBounds(30, 30, 600, 600);
            window.getContentPane().add(this);
            window.setVisible(true);
        });
    }

    int getLength() {
        return length;
    }

    void put(Room r) {
        this.rooms.put(this.coordinate(r.getX(), r.getY()), r);
    }

    private Room get(int i, int j) {
        return this.rooms.get(this.coordinate(i, j));
    }

    private void connect() {
        this.rooms.values().forEach(this::connectToNeighbours);
    }

    private void connectToNeighbours(Room room) {
        Room nroom = null;
        if (room.getDoorPosition() == 0) {
            nroom = this.get(room.getX(), room.getY() - 1);
        } else if (room.getDoorPosition() == 1) {
            nroom = this.get(room.getX() + 1, room.getY());
        } else if (room.getDoorPosition() == 2) {
            nroom = this.get(room.getX(), room.getY() + 1);
        } else if (room.getDoorPosition() == 3) {
            nroom = this.get(room.getX() - 1, room.getY());
        }
        room.addNeighbour(nroom);
        if (nroom != null) nroom.addNeighbour(room);
    }

    private int coordinate(int i, int j) {
        if (i < 0) return -1;
        if (j < 0) return -1;
        if (this.length < i) return -1;
        if (this.length < j) return -1;
        return this.length * j + i;
    }

    ArrayList<Room> bfs(int i, int j) {

        this.connect();

        ArrayList<Room> path = new ArrayList<>();
        Queue<ArrayList<Room>> paths = new LinkedList<>();

        ArrayList<Room> root = new ArrayList<>();
        root.add(this.get(i, j));
        paths.add(root);

        while (paths.size() != 0) {
            ArrayList<Room> p = paths.remove();
            Room lastRoom = p.get(p.size() - 1);

            if (lastRoom.getNeighbours().contains(null))
                return p;

            for (Room r : lastRoom.getNeighbours()) {
                if (r.isVisited) continue;
                ArrayList<Room> newPath = new ArrayList<>(p);
                newPath.add(r);
                paths.add(newPath);
                r.isVisited = true;
            }
        }
        return path;
    }
}
