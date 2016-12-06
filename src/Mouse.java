import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Mouse {

    private int x;
    private int y;
    private Maze maze;

    Mouse(int x, int y, Maze maze) {
        this.x = x;
        this.y = y;
        this.maze = maze;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void goOut() {
        ArrayList<Room> rooms = this.maze.bfs(this.x, this.y);

        if (rooms.size() == 0)
            JOptionPane.showMessageDialog(null, "There is not solution for this room.", "No Solution", JOptionPane.INFORMATION_MESSAGE);


        final int[] index = {0};
        Timer timer = new Timer(1000, e -> {
            index[0]++;
            if (rooms.size() > index[0])
                this.go(rooms.get(index[0]));
            else if (rooms.size() == index[0])
                this.goExit(rooms.get(index[0] - 1));
            maze.repaint();
        });
        timer.start();
    }

    private void go(Room room) {
        this.x = room.getX();
        this.y = room.getY();
    }

    private void goExit(Room room) {
        if (room.getDoorPosition() == 0) this.y = room.getY() - 1;
        else if (room.getDoorPosition() == 1) this.x = room.getX() + 1;
        else if (room.getDoorPosition() == 2) this.y = room.getY() + 1;
        else if (room.getDoorPosition() == 3) this.x = room.getX() - 1;

    }

    private int dx() {
        return x * 50 + 70;
    }

    private int dy() {
        return y * 50 + 70;
    }

    void paint(Graphics g) {
        g.fillRect(this.dx(), this.dy(), 10, 10);
    }
}
