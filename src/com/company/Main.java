package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        ChessBoard board = new ChessBoard();
        System.out.println(board);

        while(!board.isGameEnded()){
            System.out.println("It is " + (board.isWhitePlaying() ? "White" : "Black") + "'s turn");
            Piece piece = null;


            do {
                System.out.print("Enter the location of the piece:");
                String from = reader.next();

                piece = board.getPieceAt(from);
                System.out.println("Your Choice: "+piece);

            }while(piece == null || piece.getColor()!=(board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK));

            String to = null;
            do {
                System.out.print("Enter the new location of the piece:");
                to = reader.next();
            }while(!piece.canMove(to));

            piece.move(to);
            System.out.println(board);
        }
    }
}
