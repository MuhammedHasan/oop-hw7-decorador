import java.awt.*;

class DecoratedRoom extends Room {

    private Room room;

    DecoratedRoom(Room room) {
        super(room.getX(), room.getY(), room.getDoorPosition());
        this.room = room;
    }

    void paint(Graphics g) {
        g.setColor(Color.MAGENTA);
        this.room.paint(g);
        g.setColor(Color.black);
    }
}
