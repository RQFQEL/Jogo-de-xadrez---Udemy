package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessExceptionn;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPositionn;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {

			try {

				Ui.clearScreen();
				
				Ui.printMatch(chessMatch, captured);

				Ui.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.println("Print source: ");
				ChessPositionn source = Ui.readChessPosition(sc);
				
				boolean [][] possibleMoves = chessMatch.possibleMovies(source);
				Ui.clearScreen();
				Ui.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();
				System.out.println("Target: ");
				ChessPositionn target = Ui.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(captured != null) {
					captured.add(capturedPiece);
				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.println("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
				
			} catch (ChessExceptionn e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		Ui.clearScreen();
		Ui.printMatch(chessMatch, captured);
	}
}
