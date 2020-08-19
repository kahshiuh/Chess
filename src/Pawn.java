
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pawn extends Piece {

    private ImageIcon whiteV, blackV;

    public Pawn(int x, int y, String col) {
        super(x, y, col);
        firstMove = true;
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
            Image image = ImageIO.read(new File("images/whitepawn.png"));
            Image image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            whiteV = new ImageIcon(image1);
            image = ImageIO.read(new File("images/blackpawn.png"));
            image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            blackV = new ImageIcon(image1);
        } catch (IOException io) {

        }
    }

    public String toString() {
        return "Pawn";
    }

    public boolean checkMove(int x, int y, int x1, int y1, ChessBoard c) {
        Pawn p = (Pawn) c.getPiece(x, y);
        if (p.getColor().equals("Black")) {
            if (p.getFirstMove()) {
                if (x1 - x == 2 && c.getPiece(x + 1, y) == null && c.getPiece(x + 2, y) == null && y1 - y == 0) {
                    return true;
                } else if (x1 - x == 1 && c.getPiece(x + 1, y) == null && y1 - y == 0) {
                    return true;
                } else if (x1 - x == 1 && y1 - y == 1 && c.getPiece(x + 1, y + 1) != null && c.getPiece(x1, y1).getColor().equals("White")) {
                    return true;
                } else if (x1 - x == 1 && y1 - y == -1 && c.getPiece(x + 1, y - 1) != null && c.getPiece(x1, y1).getColor().equals("White")) {
                    return true;
                }
            } else {
                if (x1 - x == 1 && c.getPiece(x + 1, y) == null && y1 - y == 0) {
                    return true;
                } else if (x1 - x == 1 && y1 - y == 1 && c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals("White")) {
                    return true;
                } else if (x1 - x == 1 && y1 - y == -1 && c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals("White")) {
                    return true;
                }    
            }
        } else {
            if (p.getFirstMove()) {
                if (x1 - x == -2 && c.getPiece(x - 1, y) == null && c.getPiece(x - 2, y) == null && y1 - y == 0) {
                    return true;
                } else if (x1 - x == -1 && c.getPiece(x - 1, y) == null && y1 - y == 0) {
                    return true;
                } else if (x1 - x == -1 && y1 - y == 1 && c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals("Black")) {
                    return true;
                } else if (x1 - x == -1 && y1 - y == -1 && c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals("Black")) {
                    return true;
                }
            } else {
                if (x1 - x == -1 && c.getPiece(x - 1, y) == null && y1 - y == 0) {
                    return true;
                } else if (x1 - x == -1 && y1 - y == 1 && c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals("Black")) {
                    return true;
                } else if (x1 - x == -1 && y1 - y == -1 && c.getPiece(x1, y1) != null && c.getPiece(x1, y1).getColor().equals("Black")) {
                    return true;
                }
            }

        }
        return false;
    }

}
