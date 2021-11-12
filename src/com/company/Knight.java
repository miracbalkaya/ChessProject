package com.company;
import java.text.DecimalFormat;
public class Knight extends Piece{

    public Knight(int color, Square location) {
        super(color,location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int [] coordLoc = {location.row,location.col};
        int [] coordTar = {targetLocation.row, targetLocation.col};
        int locRow = coordLoc[0];
        int locCol = coordLoc[1];
        int tarRow = coordTar[0];
        int tarCol = coordTar[1];
        double distance =  Math.sqrt(Math.pow((tarRow - locRow),2) + Math.pow((tarCol - locCol),2));

        String dist = new DecimalFormat("##.##").format(distance);
        String str224 =  new DecimalFormat("##.##").format(2.23607);

        if(dist.equals(str224)){

            Square [] between = location.getBoard().getSquaresBetweenForKnight(location,targetLocation);
            for (Square square : between){
                validMove = square.isEmpty();
            }
            if(!validMove){
                if(targetLocation.getPiece() != null && targetLocation.getPiece().isEnemy(this))
                    validMove = true;
            }
            return validMove;
        }
        return false;
    }

    @Override
    public String toString() {
        return color == 0 ? "N":"n";
    }
}
