
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Rook extends Piece {

    private ImageIcon whiteV, blackV;

    public Rook(int x, int y, String col) {
        super(x, y, col);
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
            Image image = ImageIO.read(new File("images/whiterook.png"));
            Image image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            whiteV = new ImageIcon(image1);
            image = ImageIO.read(new File("images/blackrook.png"));
            image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            blackV = new ImageIcon(image1);
        } catch (IOException io) {

        }
    }

    public String toString() {
        return "Rook";
    }

    public boolean checkMove(int x, int y, int x1, int y1, ChessBoard c) {
        Rook r = (Rook) c.getPiece(x, y);
        if (x1 - x == 0 || y1 - y == 0) {
            if (y1 - y == 0) {
                if (x > x1) {
                    for (int i = x - 1; i >= x1; i--) {
                        if (c.getPiece(i, y) != null && i != x1) {
                            return false;
                        }
                    }
                    if (c.getPiece(x1, y1) == null || !c.getPiece(x1, y1).getColor().equals(r.getColor())) {
                        return true;
                    }
                } else {
                    for (int i = x + 1; i <= x1; i++) {
                        if (c.getPiece(i, y) != null && i != x1) {
                            return false;
                        }
                    }
                    if (c.getPiece(x1, y1) == null || !c.getPiece(x1, y1).getColor().equals(r.getColor())) {
                        return true;
                    }
                }
            } else if (x1 - x == 0) {
                if (y > y1) {
                    for (int i = y - 1; i >= y1; i--) {
                        if (c.getPiece(x, i) != null && i != y1) {
                            return false;
                        }
                    }
                    if (c.getPiece(x1, y1) == null || !c.getPiece(x1, y1).getColor().equals(r.getColor())) {
                        return true;
                    }
                } else {
                    for (int i = y + 1; i <= y1; i++) {
                        if (c.getPiece(x, i) != null && i != y1) {
                            return false;
                        }
                    }
                    if (c.getPiece(x1, y1) == null || !c.getPiece(x1, y1).getColor().equals(r.getColor())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
