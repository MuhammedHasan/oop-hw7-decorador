import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int mazeSize = Integer.parseInt(JOptionPane.showInputDialog("How many rooms are there in maze: "));
        Maze maze = new MazeFactory().get(mazeSize);
        maze.render();
        maze.getMouse().goOut();
    }

}
