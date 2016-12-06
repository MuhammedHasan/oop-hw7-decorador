import java.util.Random;

class MazeFactory {

    private Random rn;
    private int[][] matrix;

    MazeFactory() {
        this.rn = new Random();
    }

    Maze get(int size) {

        Maze maze = new Maze(this.optimalSize(size));
        int mousePosition = this.rn.nextInt(size) + 1;
        int c = 1;

        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getLength(); j++) {
                maze.put(this.generateRoom(i, j));
                if (c == mousePosition) maze.setMouse(new Mouse(i, j, maze));
                if (size < ++c) return maze;
            }
        }

        return maze;
    }

    private Room generateRoom(int i, int j) {
        Room room = new Room(i, j, this.rn.nextInt(4));
        if (this.rn.nextBoolean())
            return new DecoratedRoom(room);
        return room;
    }

    private int optimalSize(int size) {
        int osize = 1;
        while (Math.pow(osize, 2) < size) osize++;
        return osize;
    }
}
