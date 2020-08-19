
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class King extends Piece {

    private ImageIcon whiteV, blackV;

    public King(int x, int y, String color) {
        super(x, y, color);
        loadImages();
    }

    public ImageIcon getIcon() {
        if (col.equals("Black")) {
            return blackV;
        }
        return whiteV;
    }

    public void loadImages() {
        try {
            Image image = ImageIO.read(new File("images/whiteking.png"));
            Image image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            whiteV = new ImageIcon(image1);
            image = ImageIO.read(new File("images/blackking.png"));
            image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            blackV = new ImageIcon(image1);
        } catch (IOException io) {

        }
    }

    public String toString() {
        return "King";
    }

    public boolean checkMove(int x, int y, int x1, int y1, ChessBoard c) {

        if (Math.abs(y - y1) <= 1 && Math.abs(x - x1) <= 1) {
            if (c.getPiece(x1, y1) == null) {
                return true;
            } 
            if (!c.getPiece(x1, y1).getColor().equals(c.getPiece(x, y).getColor())) {
                return true;
            }
            return false;
        }
        if (Math.abs(y1 - y) == 2 && c.getPiece(x, y).getFirstMove()) {
            if (c.getPiece(x, y).getColor().equals("White")) {
                if (y1 - y == 2 && x1 == x && x == 7) {
                    if (c.getPiece(7, 6) == null && c.getPiece(7, 5) == null) {
                        return true;
                    }
                } else if (y1 - y == -2 && x1 == x && x == 7) {
                    if (c.getPiece(7, 1) == null && c.getPiece(7, 2) == null && c.getPiece(7, 3) == null) {
                        return true;
                    }
                }
            } else {
                if (y1 - y == 2 && x1 == x && x == 0) {
                    if (c.getPiece(0, 6) == null && c.getPiece(0, 5) == null) {
                        return true;
                    }
                } else if (y1 - y == -2 && x1 == x && x == 0) {
                    if (c.getPiece(0, 1) == null && c.getPiece(0, 2) == null && c.getPiece(0, 3) == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
