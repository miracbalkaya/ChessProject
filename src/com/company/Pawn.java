package com.company;

public class Pawn extends Piece{
    public static boolean initialLocation = true;
    public static int count = 0;

    public Pawn(int color, Square location){
        super(color,location);
    }

    @Override
    public boolean canMove(String to){
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location,this.color);
        if (this.location.isAtSameColumn(targetLocation)){
            if(color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2){
                if(rowDistance == 2){
                    if(initialLocation){
                        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove =targetLocation.isEmpty();
                }
                return validMove;
            } else if ( color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2){
                if(rowDistance == -2){
                    if(initialLocation){
                        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
            }
        }else if(this.location.isNeighbourColumn(targetLocation)){
            if(color == ChessBoard.WHITE && rowDistance == 1){
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.BLACK;
            }else if (color == ChessBoard.BLACK && rowDistance == -1){
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.WHITE;
            }
        }
        return validMove;
    }

    @Override
    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);

        if (targetLocation.isAtLastRow(color)){
            targetLocation.putNewQueen(color);
        }else{
            targetLocation.setPiece(this);
        }
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();

        count++;
        if (count > 1)
            initialLocation = false;
    }

    @Override
    public String toString() {
        return color == 0 ? "P":"p";
    }
}
