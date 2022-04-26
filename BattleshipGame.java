import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class BattleshipGame {
/*
 * Made By Samuel Zemen
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int gameBoardLength = 4;
			char water = '-';
			char ship = 's';
			char hit = 'x';
			char miss = '0';
			int shipNumber = 3;
			
			char[][] gameBoard = createGameBoard(gameBoardLength, water, ship, shipNumber);
			printGameBoard(gameBoard, water, ship);
			int undetectedShipNumber = shipNumber;
			while(undetectedShipNumber > 0) {
				int[] guessCoordinates = getUserCoordinates(gameBoardLength);
				char locationViewUpdate = evaluateGuessAndGetTheTarget(guessCoordinates, gameBoard, ship,water, hit , miss);
				if(locationViewUpdate == hit) {
					undetectedShipNumber--;
				}
				gameBoard= updateGameBoard(gameBoard,guessCoordinates,locationViewUpdate);
				printGameBoard(gameBoard,water,ship);
			}
			System.out.print("You won!");
	}

	private static char[][] updateGameBoard(char[][] gameBoard, int[] guessCoordinates, char locationViewUpdate) {
		int row = guessCoordinates[0];
				int col = guessCoordinates[1];
		gameBoard[row][col] = locationViewUpdate;
		return gameBoard;
				}

	private static char evaluateGuessAndGetTheTarget(int[] guessCoordinates, char[][] gameBoard, char ship, char water,
			char hit, char miss) {
		String message;
		int row= guessCoordinates[0];
		int col= guessCoordinates[1];
		char target = gameBoard[row][col];
		if(target == ship) {
			message = "Hit!";
			target = hit;
		}else if (target ==water) {
			message = "Miss!";
			target = miss;
		}else {
			message ="Already Hit!";
		}
		System.out.println(message);
		return target;
	}

	private static int[] getUserCoordinates(int gameBoardLength) {
		// TODO Auto-generated method stub
		int row; 
		int col;
		do {
			System.out.print("Row: ");
			row = new Scanner(System.in).nextInt();
		}while(row < 1 || row > gameBoardLength + 1);
		do {
			System.out.print("Column: ");
			col = new Scanner(System.in).nextInt();
		}while (col < 1 || col > gameBoardLength + 1);
		return new int[] {row -1, col -1};
	}

	private static void printGameBoard(char[][] gameBoard, char water, char ship) {
		int gameBoardLength = gameBoard.length;
		System.out.print(" ");
		for (int i = 0; i < gameBoardLength; i++) {
		System.out.print( i+1+"");
		}
		System.out.println();
		for(int row = 0; row < gameBoardLength; row++) {
			System.out.print(row + 1 + "");
			for(int col = 0; col < gameBoardLength; col++) {
				char position = gameBoard[row][col];
				if(position == ship) {
					System.out.print(water);
				}else {
					System.out.print(position+ "");
				}
			}
			System.out.println();
	}
		
  }
		


	private static char[][] createGameBoard(int gameBoardLength, char water, char ship, int shipNumber) {
		// TODO Auto-generated method stub
		char[][] gameBoard = new char[gameBoardLength][gameBoardLength];
		for(char[] row : gameBoard) {
			Arrays.fill(row, water);
			
		}
		return placeShips(gameBoard, shipNumber, water, ship);
	}

	private static char[][] placeShips(char[][] gameBoard, int shipNumber, char water, char ship) {
		// TODO Auto-generated method stub
		int placedShips = 0;
		int gameBoardLength = gameBoard.length;
		while(placedShips < shipNumber) {
			int[] location = generateShipCoordinates(gameBoardLength);
			char possiblePlacement = gameBoard[location[0]][location[1]];
			
			//iterate till water is in the possible placement
		
			if(possiblePlacement == water) {
				gameBoard[location[0]][location[1]] = ship;
				placedShips++;
			}
			
		}
		return gameBoard;
	}

	private static int[] generateShipCoordinates(int gameBoardLength) {
		// TODO Auto-generated method stub
		int[] coordinates = new int[2];
		for(int i = 0; i < coordinates.length; i++) {
			coordinates[i]= new Random().nextInt(gameBoardLength);
		}
		return coordinates;
	}

}
