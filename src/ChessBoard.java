
public class ChessBoard {

    private ChessSquare[][] board;
    private final String b = "Black";
    private final String w = "White";

    //Creates a board digitally to reprsent and sets each piece up
    public ChessBoard() {
        board = new ChessSquare[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new ChessSquare();
            }
        }
        board[0][0].setPiece(new Rook(0, 0, b));
        board[0][1].setPiece(new Knight(0, 1, b));
        board[0][2].setPiece(new Bishop(0, 2, b));
        board[0][3].setPiece(new Queen(0, 3, b));
        board[0][4].setPiece(new King(0, 4, b));
        board[0][5].setPiece(new Bishop(0, 5, b));
        board[0][6].setPiece(new Knight(0, 6, b));
        board[0][7].setPiece(new Rook(0, 7, b));
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(1, i, b));
        }
        board[7][0].setPiece(new Rook(7, 0, w));
        board[7][1].setPiece(new Knight(7, 1, w));
        board[7][2].setPiece(new Bishop(7, 2, w));
        board[7][3].setPiece(new Queen(7, 3, w));
        board[7][4].setPiece(new King(7, 4, w));
        board[7][5].setPiece(new Bishop(7, 5, w));
        board[7][6].setPiece(new Knight(7, 6, w));
        board[7][7].setPiece(new Rook(7, 7, w));
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(6, i, w));
        }
    }
    //Accessors
    public ChessSquare getSquare(int x, int y) {
        return board[x][y];
    }

    public Piece getPiece(int x, int y) {
        return board[x][y].getPiece();
    }
    //Two methods to set pieces
    //Used coordinates to move
    public void setPiece(int x, int y, int x1, int y1) {
        board[x1][y1].setPiece(board[x][y].getPiece());
        board[x][y].removePiece();
    }
    
    //Just sets specific piece down on specific spot
    public void setPiece(int x, int y, Piece p) {
        board[x][y].setPiece(null);
        board[x][y].setPiece(p);
    }
    
    //Checks if the moves are legal
    //Calls each piece's checkMove depending on piece
    public boolean checkMove(int x, int y, int x1, int y1) {
        if (x == 10 || x1 == 10) {
            return false;
        }
        if (board[x][y].getPiece().toString().equals("Pawn")) {
            Pawn p = (Pawn) board[x][y].getPiece();
            return p.checkMove(x, y, x1, y1, this);
        } else if (board[x][y].getPiece().toString().equals("Rook")) {
            Rook p = (Rook) board[x][y].getPiece();
            return p.checkMove(x, y, x1, y1, this);
        } else if (board[x][y].getPiece().toString().equals("Knight")) {
            Knight p = (Knight) board[x][y].getPiece();
            return p.checkMove(x, y, x1, y1, this);
        } else if (board[x][y].getPiece().toString().equals("Bishop")) {
            Bishop p = (Bishop) board[x][y].getPiece();
            return p.checkMove(x, y, x1, y1, this);
        } else if (board[x][y].getPiece().toString().equals("Queen")) {
            Queen p = (Queen) board[x][y].getPiece();
            return p.checkMove(x, y, x1, y1, this);
        } else {
            King p = (King) board[x][y].getPiece();
            return p.checkMove(x, y, x1, y1, this);
        }
    }

    //Makes a new copy of board so data isn't messed up due to references
    public ChessBoard boardCopy(ChessBoard b) {
        ChessBoard c = new ChessBoard();
        for (int r = 0; r < 8; r++) {
            for (int cc = 0; cc < 8; cc++) {
                c.getSquare(r, cc).removePiece();
                if (b.getPiece(r, cc) != null) {
                    c.getSquare(r, cc).setPiece(b.getPiece(r, cc));
                }
            }
        }
        return c;
    }
    
    //Checks inCheck but looking in each direction, kinda long
    public boolean checkInCheck(int x, int y, int x1, int y1, ChessBoard b) {
        int kX = 0;
        int kY = 0;
        ChessBoard c = boardCopy(b);
        c.setPiece(x, y, x1, y1);
        if(x == 10 || y == 10 || x1 == 10 || y1 == 10){
            return true;
        }
        if (c.getPiece(x1, y1).getColor().equals("White")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (c.getPiece(i, j) != null && c.getPiece(i, j).getColor().equals("White") && c.getPiece(i, j).toString().equals("King")) {
                        kX = i;
                        kY = j;
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (c.getPiece(i, j) != null && c.getPiece(i, j).getColor().equals("Black") && c.getPiece(i, j).toString().equals("King")) {
                        kX = i;
                        kY = j;
                    }
                }
            }
        }
        System.out.println(kX + "   " + kY);
        for (int i = 1; i < 8; i++) {
            if (kX + i < 8 && c.getPiece(kX + i, kY) != null && c.getPiece(kX + i, kY).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if(kX + i < 8 && c.getPiece(kX + i, kY) != null && !c.getPiece(kX + i, kY).getColor().equals(c.getPiece(kX, kY).getColor()) && !(c.getPiece(kX + i, kY).toString().equals("Rook") || c.getPiece(kX + i, kY).toString().equals("Queen"))){
                break;
            }
            if (kX + i < 8 && c.getPiece(kX + i, kY) != null && !c.getPiece(kX + i, kY).getColor().equals(c.getPiece(kX, kY).getColor()) && (c.getPiece(kX + i, kY).toString().equals("Rook") || c.getPiece(kX + i, kY).toString().equals("Queen"))) {
                return true;
            }
        }
        for (int i = 1; i < 8; i++) {
            
            if (kX - i >= 0 && c.getPiece(kX - i, kY) != null && c.getPiece(kX - i, kY).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if(kX -i >= 0  && c.getPiece(kX - i, kY) != null && !c.getPiece(kX - i, kY).getColor().equals(c.getPiece(kX, kY).getColor()) && !(c.getPiece(kX - i, kY).toString().equals("Rook") || c.getPiece(kX - i, kY).toString().equals("Queen"))){
                break;
            }
            if (kX - i >= 0 && c.getPiece(kX - i, kY) != null && !c.getPiece(kX - i, kY).getColor().equals(c.getPiece(kX, kY).getColor()) && (c.getPiece(kX - i, kY).toString().equals("Rook") || c.getPiece(kX - i, kY).toString().equals("Queen"))) {
                System.out.println("????");
                return true;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (kY + i < 8 && c.getPiece(kX, kY + i) != null && c.getPiece(kX, kY + i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if(kY + i < 8 && c.getPiece(kX, kY+i) != null && !c.getPiece(kX, kY+i).getColor().equals(c.getPiece(kX, kY).getColor()) && !(c.getPiece(kX, kY+i).toString().equals("Rook") || c.getPiece(kX, kY+i).toString().equals("Queen"))){
                break;
            }
            if (kY + i < 8 && c.getPiece(kX, kY + i) != null && !c.getPiece(kX, kY + i).getColor().equals(c.getPiece(kX, kY).getColor()) && (c.getPiece(kX, kY + i).toString().equals("Rook") || c.getPiece(kX, kY + i).toString().equals("Queen"))) {
                return true;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (kY - i >= 0 && c.getPiece(kX, kY - i) != null && c.getPiece(kX, kY - i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if(kY - i >= 0 && c.getPiece(kX, kY-i) != null && !c.getPiece(kX, kY-i).getColor().equals(c.getPiece(kX, kY).getColor()) && !(c.getPiece(kX, kY-i).toString().equals("Rook") || c.getPiece(kX, kY-i).toString().equals("Queen"))){
                break;
            }
            if (kY - i >= 0 && c.getPiece(kX, kY - i) != null && !c.getPiece(kX, kY - i).getColor().equals(c.getPiece(kX, kY).getColor()) && (c.getPiece(kX, kY - i).toString().equals("Rook") || c.getPiece(kX, kY - i).toString().equals("Queen"))) {
                return true;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (kY + i < 8 && kX + i < 8 && c.getPiece(kX + i, kY + i) != null && c.getPiece(kX + i, kY + i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if (kY + i < 8 && kX + i < 8 && c.getPiece(kX + i, kY + i) != null && !c.getPiece(kX + i, kY + i).getColor().equals(c.getPiece(kX, kY).getColor()) && (c.getPiece(kX + i, kY + i).toString().equals("Bishop") || c.getPiece(kX + i, kY + i).toString().equals("Queen"))) {
                return true;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (kY - i >= 0 && kX - i >= 0 && c.getPiece(kX - i, kY - i) != null && c.getPiece(kX - i, kY - i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if (kY - i >= 0 && kX - i >= 0 && c.getPiece(kX - i, kY - i) != null && !c.getPiece(kX - i, kY - i).getColor().equals(c.getPiece(kX, kY).getColor()) && (c.getPiece(kX - i, kY - i).toString().equals("Rook") || c.getPiece(kX - i, kY - i).toString().equals("Queen"))) {
                return true;
            }
        }
        if (c.getPiece(kX, kY).getColor().equals("Black")) {
            if (c.getPiece(kX + 1, kY + 1) != null && c.getPiece(kX + 1, kY + 1).toString().equals("Pawn") && !c.getPiece(kX + 1, kY + 1).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            } else if (c.getPiece(kX + 1, kY - 1) != null && c.getPiece(kX + 1, kY - 1).toString().equals("Pawn") && !c.getPiece(kX + 1, kY - 1).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            }

        } else {
            if (c.getPiece(kX - 1, kY + 1) != null && c.getPiece(kX - 1, kY + 1).toString().equals("Pawn") && !c.getPiece(kX - 1, kY + 1).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            } else if (c.getPiece(kX - 1, kY - 1) != null && c.getPiece(kX - 1, kY - 1).toString().equals("Pawn") && !c.getPiece(kX - 1, kY - 1).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            }
        }
        if (checkKnights(kX, kY, c)) {
            return true;
        }
        for (int i = 1; i < 8; i++) {
            if (!checkOnBoard(kX + i, kY + i)) {
                break;
            }
            if (c.getPiece(kX + i, kY + i) != null && c.getPiece(kX + i, kY + i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if (c.getPiece(kX + i, kY + i) != null && (c.getPiece(kX + i, kY + i).toString().equals("Bishop") || c.getPiece(kX + i, kY + i).toString().equals("Queen")) && !c.getPiece(kX + i, kY + i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (!checkOnBoard(kX - i, kY - i)) {
                break;
            }
            if (c.getPiece(kX - i, kY - i) != null && c.getPiece(kX - i, kY - i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if (c.getPiece(kX - i, kY - i) != null && (c.getPiece(kX - i, kY - i).toString().equals("Bishop") || c.getPiece(kX - i, kY - i).toString().equals("Queen")) && !c.getPiece(kX - i, kY - i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (!checkOnBoard(kX + i, kY - i)) {
                break;
            }
            if (c.getPiece(kX + i, kY - i) != null && c.getPiece(kX + i, kY - i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if (c.getPiece(kX + i, kY - i) != null && (c.getPiece(kX + i, kY - i).toString().equals("Bishop") || c.getPiece(kX + i, kY - i).toString().equals("Queen")) && !c.getPiece(kX + i, kY - i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            }

        }
        for (int i = 1; i < 8; i++) {
            if (!checkOnBoard(kX - i, kY + i)) {
                break;
            }
            if (c.getPiece(kX - i, kY + i) != null && c.getPiece(kX - i, kY + i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                break;
            }
            if (c.getPiece(kX - i, kY + i) != null && (c.getPiece(kX - i, kY + i).toString().equals("Bishop") || c.getPiece(kX - i, kY + i).toString().equals("Queen")) && !c.getPiece(kX - i, kY + i).getColor().equals(c.getPiece(kX, kY).getColor())) {
                return true;
            }
        }
        if(kingCheck(kX,kY,c)){
            return true;
        }
        return false;
    }
    //King moved into check
    public boolean kingCheck(int kX,int kY, ChessBoard c){
        if(checkOnBoard(kX+1,kY+1) && c.getPiece(kX+1,kY+1) != null && c.getPiece(kX+1,kY+1).toString().equals("King"))
            return true;
        if(checkOnBoard(kX+1,kY) && c.getPiece(kX+1,kY) != null && c.getPiece(kX+1,kY).toString().equals("King"))
            return true;
        if(checkOnBoard(kX+1,kY-1) && c.getPiece(kX+1,kY-1) != null && c.getPiece(kX+1,kY-1).toString().equals("King"))
            return true;
        if(checkOnBoard(kX,kY+1) && c.getPiece(kX,kY+1) != null && c.getPiece(kX,kY+1).toString().equals("King"))
            return true;
        if(checkOnBoard(kX,kY-1) && c.getPiece(kX,kY-1) != null && c.getPiece(kX,kY-1).toString().equals("King"))
            return true;
        if(checkOnBoard(kX-1,kY+1) && c.getPiece(kX-1,kY+1) != null && c.getPiece(kX-1,kY+1).toString().equals("King"))
            return true;
        if(checkOnBoard(kX-1,kY-1) && c.getPiece(kX-1,kY-1) != null && c.getPiece(kX-1,kY-1).toString().equals("King"))
            return true;
        if(checkOnBoard(kX-1,kY) && c.getPiece(kX-1,kY) != null && c.getPiece(kX-1,kY).toString().equals("King"))
            return true;
        return false;
    }
    //Not finished; for a rainy day
    public boolean checkGameEnd(boolean whitesTurn) {
        return true;
    }
    //Checks that the spot you are checking for is on the board
    public boolean checkOnBoard(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
    //Knights
    public boolean checkKnights(int x, int y, ChessBoard c) {
        if (checkOnBoard(x + 2, y + 1) && c.getPiece(x + 2, y + 1) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x + 2, y + 1).getColor()) && c.getPiece(x + 2, y + 1).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x + 2, y - 1) && c.getPiece(x + 2, y - 1) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x + 2, y - 1).getColor()) && c.getPiece(x + 2, y - 1).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x - 2, y - 1) && c.getPiece(x - 2, y - 1) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x - 2, y - 1).getColor()) && c.getPiece(x - 2, y - 1).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x - 2, y + 1) && c.getPiece(x - 2, y + 1) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x - 2, y + 1).getColor()) && c.getPiece(x - 2, y + 1).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x - 1, y + 2) && c.getPiece(x - 1, y + 2) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x - 1, y + 2).getColor()) && c.getPiece(x - 1, y + 2).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x - 1, y - 2) && c.getPiece(x - 1, y - 2) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x - 1, y - 2).getColor()) && c.getPiece(x - 1, y - 2).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x + 1, y + 2) && c.getPiece(x + 1, y + 2) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x + 1, y + 2).getColor()) && c.getPiece(x + 1, y + 2).toString().equals("Knight")) {
            return true;
        }
        if (checkOnBoard(x + 1, y - 2) && c.getPiece(x + 1, y - 2) != null && !c.getPiece(x, y).getColor().equals(c.getPiece(x + 1, y - 2).getColor()) && c.getPiece(x + 1, y - 2).toString().equals("Knight")) {
            return true;
        }
        return false;
    }

}
