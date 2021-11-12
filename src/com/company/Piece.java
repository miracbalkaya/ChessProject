package com.company;

public abstract class Piece {

    public int color;
    public Square location;

    public Piece(int color, Square location){
        this.color = color;
        this.location = location;
        this.location.setPiece(this);
    }

    public int getColor(){
        return this.color;
    }

    public abstract boolean canMove(String to);

    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();

    }

    protected boolean isEnemy(Piece p){
        return !(this.color == p.color);
    }
}
