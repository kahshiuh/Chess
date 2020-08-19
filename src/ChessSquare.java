
public class ChessSquare {

    private Piece currentPiece;
    //Empty Square + Filled Square constructor
    public ChessSquare(Piece pie) {
        currentPiece = pie;
    }

    public ChessSquare() {

    }
    //Returns if that square has a piece
    public Piece getPiece() {
        if (currentPiece == null) {
            return null;
        }
        return currentPiece;
    }
    //Set a piece on that square
    public void setPiece(Piece pie) {
        currentPiece = pie;
    }
    //Remove a piece
    public void removePiece() {
        currentPiece = null;
    }

}
