
import javax.swing.ImageIcon;

public abstract class Piece {
    
    //The X,Y coords, color, and whether piece has moved (for Pawns) 
    protected int xStart, yStart;
    protected String col;
    protected boolean firstMove;

    public Piece(int x, int y, String colo) {
        xStart = x;
        yStart = y;
        col = colo;
        firstMove = true;
    }
    //Basic accessors and mutators
    public String getColor() {
        return col;
    }

    public boolean getFirstMove() {
        return firstMove;
    }

    public void setfirstMove(boolean tru) {
        firstMove = tru;
    }

    public void changeFirstMove() {
        firstMove = false;
    }
    //Each piece has specific move restrictions, name and ImageIcon
    public abstract boolean checkMove(int x, int y, int x1, int x2, ChessBoard c);

    public abstract ImageIcon getIcon();

    public abstract String toString();
}
