
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Knight extends Piece {

    private ImageIcon whiteV, blackV;

    public Knight(int x, int y, String col) {
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
            Image image = ImageIO.read(new File("images/whiteknight.png"));
            Image image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            whiteV = new ImageIcon(image1);
            image = ImageIO.read(new File("images/blackknight.png"));
            image1 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            blackV = new ImageIcon(image1);
        } catch (IOException io) {

        }
    }

    public String toString() {
        return "Knight";
    }

    public boolean checkMove(int x, int y, int x1, int y1, ChessBoard c) {
        Knight k = (Knight) c.getPiece(x, y);
        if (Math.abs(x - x1) == 1 && Math.abs(y - y1) == 2 && (c.getPiece(x1, y1) == null || !c.getPiece(x1, y1).getColor().equals(k.getColor()))) {
            return true;
        } else if (Math.abs(x - x1) == 2 && Math.abs(y - y1) == 1 && (c.getPiece(x1, y1) == null || !c.getPiece(x1, y1).getColor().equals(k.getColor()))) {
            return true;
        }
        return false;
    }

}
