package com.company;

import java.util.ArrayList;

import java.util.Hashtable;

public class ChessBoard {

    public static final int WHITE = 1 ;
    public static int BLACK = 1;
    public static int WHİTE = 0;
    boolean isWhite;
    private int whitePieces = 16;
    private int blackPieces = 16;

    public Square[][] board2d = new Square[8][8];
    public Hashtable<Character, Integer> abcHashtable = new Hashtable<Character, Integer>();

    public ChessBoard(){
        this.isWhite = true;
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col ++){
                board2d[row][col] = new Square(row,col,this);
            }
        }

        for(int col = 0; col<8; col++) {
            new Pawn(WHITE, board2d[6][col]);
            new Pawn(BLACK, board2d[1][col]);
        }
        for(int col = 0; col<8; col+=7){
            new Rook(WHITE,board2d[7][col]);
            new Rook(BLACK,board2d[0][col]);
        }
        new King(WHİTE,board2d[7][4]);
        new King(BLACK,board2d[0][4]);
        new Queen(WHITE,board2d[7][3]);
        new Queen(BLACK,board2d[0][3]);
        for (int col = 2; col < 7; col+=3){
            new Bishop(WHİTE,board2d[7][col]);
        }
        for(int col = 1; col<7; col+=5){
            new Knight(WHITE,board2d[7][col]);
            new Knight(BLACK,board2d[0][col]);
        }

        createHashtable();
    }

    public void  decrementPiece(int color){
        if (color == WHİTE)
            this.whitePieces--;
        this.blackPieces--;
    }

    @Override
    public String toString(){

        String brdStr = "";
        brdStr += "    A   B   C   D   E   F   G   H  \n";
        brdStr += "  ---------------------------------\n";
        for (int row = 0; row < 8; ++row){
            brdStr += 8-row + " ";
            for (int col = 0; col < 8; ++col) {
                brdStr += "|";
                brdStr += " " + board2d[row][col] + " ";
                if (col == 7)
                    brdStr += "| ";
            }
            brdStr += 8 - row + " \n";
            brdStr += "  ---------------------------------\n";
        }
        brdStr += "    A   B   C   D   E   F   G   H  \n";

        return brdStr;
    }

    public boolean isGameEnded() {
        return (whitePieces == 0 || blackPieces == 0);
    }

    public boolean isWhitePlaying() {
        return isWhite;
    }

    public Piece getPieceAt(String from) {
        int [] coordinates = getCoordinates(from);
        return board2d[coordinates[0]][coordinates[1]].piece;
    }

    public int[] getCoordinates(String from) {
        from = from.toLowerCase();
        char colChar = from.charAt(0);
        char rowChar = from.charAt(1);

        int row = 8 - Integer.parseInt(String.valueOf(rowChar)) ;
        int col = abcHashtable.get(colChar);

        return new int[]{row, col};
    }

    private void createHashtable(){
        abcHashtable.put('a',0);
        abcHashtable.put('b',1);
        abcHashtable.put('c',2);
        abcHashtable.put('d',3);
        abcHashtable.put('e',4);
        abcHashtable.put('f',5);
        abcHashtable.put('g',6);
        abcHashtable.put('h',7);
    }
    public Square getSquareAt(String to) {
        int [] coordinates = getCoordinates(to);
        return board2d[coordinates[0]][coordinates[1]];
    }
    public Square[] getSquaresBetween(Square location, Square targetLocation){
        Square [] btw;

        if(location.isAtSameDiagonal(targetLocation) || location.isAtSameColumn(targetLocation) || location.isAtSameRow(targetLocation)){

            int [] coordLoc = {location.row,location.col};
            int [] coordTar = {targetLocation.row, targetLocation.col};
            int x1 = coordLoc[0];
            int y1 = coordLoc[1];
            int x2 = coordTar[0];
            int y2 = coordTar[1];

            btw = new Square[Math.max(Math.abs(x1-x2),Math.abs(y1-y2))];
            Square [] squaresReversed = new Square[btw.length];

            if(location.isAtSameRow(targetLocation)){
                if(y2>y1){
                    btw = new Square[y2-y1];
                    int index = 0;
                    for(int i=y1+1; i<=y2; i++){
                        btw[index]=board2d[x1][i];
                        index++;
                    }
            }else {
                    btw = new Square[y1-y2];
                    int index = 0;
                    for(int i=y1-1; i>=y2; i--){
                        btw[index]=board2d[x1][i];
                        index++;
                    }
                }
        }
            else if (location.isAtSameColumn(targetLocation)){
                if(x2>x1){
                    btw = new Square[x2-x1];
                    int index = 0;
                    for(int i = x1+1; i<=x2;i++){
                        btw[index]=board2d[i][y1];
                        index++;
                    }
                }else {
                    btw = new Square[x1-x2];
                    int index = 0;
                    for(int i=x1-1; i>=x2; i--){
                        btw[index]=board2d[i][y1];
                        index++;
                    }
                }
            }
            else {
                if (x1>x2){
                    btw = new Square[x1-x2];
                    if (y2>y1){
                        x1 = x1-1;
                        y1 = y1+1;
                        for (int i=0; i<btw.length; i++){
                            btw[i] = board2d[x1][y1];
                            x1--;
                            y1++;
                        }
                    }else {
                        x1 = x1-1;
                        y1 = y1-1;
                        for(int i = 0; i<btw.length;i++){
                            btw[i] = board2d[x1][y1];
                            x1--;
                            y1--;
                        }
                    }
                }else {
                    btw = new Square[x2-x1];
                    if (y1 > y2) {
                        x1=x1+1;
                        y1=y1-1;
                        for(int i = 0; i<btw.length ; i++){
                            btw[i] = board2d[x1][y1];
                            x1++;
                            y1--;
                        }
                    }else {
                        x1=x1+1;
                        y1=y1+1;
                        for(int i = 0; i<btw.length; i++){
                            btw[i] = board2d[x1][y1];
                            x1++;
                            y1++;
                        }
                    }
                }
            }

            squaresReversed = new Square[btw.length];
            int j = btw.length;
            for(int t = 0; t<btw.length; t++){
                squaresReversed[j-1] = btw[t];
                j = j -1;
            }
            return squaresReversed;
        }
        return new Square[0];
    }

    public void nextPlayer(){
        isWhite = !isWhite;
    }

    public Square[] getSquaresBetweenForKnight(Square location,  Square targetLocation){
        Square [] squares = new Square[2];

        int [] coordLoc = {location.row,location.col};
        int [] coordTar = {targetLocation.row, targetLocation.col};
        int locRow = coordLoc[0];
        int locCol = coordLoc[1];
        int tarRow = coordTar[0];
        int tarCol = coordTar[1];

        squares[1] = targetLocation;

        if (tarCol<locCol){
            if (tarRow<locRow)
                squares[0] = board2d[tarRow][tarCol+1];
            if (tarRow>locRow)
                squares[0] = board2d[tarRow-1][tarCol];
        }
        else {
            if(tarRow<locRow)
                squares[0] = board2d[tarRow][tarCol-1];
            if(tarRow>locRow)
                squares[0] = board2d[tarRow-1][tarCol];
        }
        return squares;
    }
}
