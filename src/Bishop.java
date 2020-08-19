
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bishop extends Piece {

    private ImageIcon whiteV, blackV;

    public Bishop(int x, int y, String col) {
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
            Image image = ImageIO.read(new File("images/whitebishop.png"));
            Image image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            whiteV = new ImageIcon(image1);
            image = ImageIO.read(new File("images/blackbishop.png"));
            image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            blackV = new ImageIcon(image1);
        } catch (IOException io) {

        }
    }

    public boolean checkMove(int x, int y, int x1, int y1, ChessBoard c) {
        if (Math.abs(x - x1) == Math.abs(y - y1)) {
            //Goes to Bottom Right
            if (x1 - x > 0 && y1 - y > 0) {
                for (int i = 1; i <= x1 - x; i++) {
                    if (c.getPiece(x + i, y + i) != null) {
                        if (i != x1 - x) {
                            return false;
                        }
                    }
                }
                if (c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals(c.getPiece(x, y).getColor())) {
                    return false;
                }
                //Goes to Top Left    
            } else if (x1 - x < 0 && y1 - y < 0) {
                for (int i = -1; i >= x1 - x; i--) {
                    if (c.getPiece(x + i, y + i) != null) {
                        if (i != x1 - x) {
                            return false;
                        }
                    }
                }
                if (c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals(c.getPiece(x, y).getColor())) {
                    return false;
                }
                //Goes to Bottom Left    
            } else if (x1 - x > 0 && y1 - y < 0) {
                for (int i = 1; i <= x1 - x; i++) {
                    if (c.getPiece(x + i, y - i) != null) {
                        if (i != Math.abs(x1 - x)) {
                            return false;
                        }
                    }
                }
                if (c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals(c.getPiece(x, y).getColor())) {
                    return false;
                }
                //Goes to Top Right    
            } else {
                for (int i = 1; i <= x1 - x; i++) {
                    if (c.getPiece(x - i, y + i) != null) {
                        if (i != Math.abs(x1 - x)) {
                            return false;
                        }
                    }
                }
                if (c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals(c.getPiece(x, y).getColor())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String toString() {
        return "Bishop";
    }

}
