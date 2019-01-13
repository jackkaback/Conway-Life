import java.util.Scanner;
import java.util.Random;

public class life {

	public static void main(String[] args) {
		Scanner keyin = new Scanner(System.in);

		//Get x value
		int x = getval("height", keyin);

		//Get y val
		int y = getval("width", keyin);

		//edges get cut off
		x += 2;
		y += 2;

		boolean field[][] = new boolean[x][y];
		initializeArea(field, x, y);

		printBoard(field, x, y);
		System.out.println("------------------------------------------------------");
		//getData(field, x, y, keyin);

		while(true) {
			System.out.println("Input cell placement? (y/n): ");
			String input = keyin.nextLine();
			input = input.toLowerCase();

			if(input.contains("y")) {
				placeCells(field, x, y, keyin);
			}

			//update and print board
			updateBoard(field, x, y);
			printBoard(field, x, y);
			System.out.println("------------------------------------------------------");
		}
	}

	//Gets the value for updating an individual dot
	private static int[] getDot(Scanner keyin){
		int [] retVal = {0,0};

		for (int ii = 0; ii < 2; ii++) {

			while (true) {

				if(ii == 0) {
					System.out.print("\nX value: ");
				}else{
					System.out.print("\nY value: ");
				}

				String temp = keyin.nextLine();
				if (isNumeric(temp)) {
					retVal[ii] = Integer.parseInt(temp);
					break;
				}
			}
		}

		return retVal;
	}

	//gets the value for the X or Y lengths for the array
	private static int getval(String name, Scanner keyin){

		while(true) {

			System.out.print("Enter the " + name +" of the area: ");
			String temp = keyin.nextLine();

			if (isNumeric(temp)) {
				return Integer.parseInt(temp);
			}
		}
	}

	//sets the board up
	private static void initializeArea(boolean[][] f, int xVal, int yVal){
		Random rand = new Random();

		for(int ii = 0; ii < xVal; ii++){
			for(int jj = 0; jj < yVal; jj++){

				f[ii][jj] = rand.nextBoolean();
//				if(ii == 0 || ii == xVal-1 || jj == 0 || jj == yVal-1){
//					f[ii][jj] = true;
//				}
//				else{
//					f[ii][jj] = false;
//				}
			}
		}
	}

	//checks if a string is a number
	private static boolean isNumeric(String checkString) {
		if (checkString == null) {
			return false;
		}

		//this checks if the input is only numbers
		for (int ii = 0; ii < checkString.length(); ii++) {
			if (checkString.charAt(ii) < '0' || checkString.charAt(ii) > '9') {
				return false;
			}
		}
		return true;
	}

	//TODO add this feature in
	private static void placeCells(boolean[][] f, int x, int y, Scanner keyin){

		while(true){
			System.out.print("(D)ot, (L)ine, (F)ill, (P)rint field, (E)xit");
			String input = keyin.nextLine().toLowerCase();

			if(input.contains("e")){
				break;
			}

			else if(input.contains("p")){
				printBoard(f, x, y);
			}

			else if(input.contains("d")){
				int [] temp = getDot(keyin);
				f[temp[0]][temp[1]] = !f[temp[0]][temp[1]];
			}

		}
	}

	//prints the board to the terminal
	private static void printBoard(boolean[][] f, int x, int y){
		for (int ii = 1; ii < x - 1; ii++){
			for (int jj = 1; jj < y - 1; jj++){
				if(f[ii][jj]) {
					System.out.print("x");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}


	//Runs for sme number of generations without asking
	private static void runGenerations(boolean[][] f, int x, int y, Scanner keyin){
		int gens;

		while(true){
			System.out.println("How many generations?");
			String temp = keyin.nextLine();

			if(isNumeric(temp)){
				gens = Integer.parseInt(temp);
				break;
			}
		}

		for(int ii = 0; ii < gens; ii++){
			updateBoard(f, x, y);
			printBoard(f, x, y);
		}
	}

	//determines what cells are alive or dead after every generation
	private static void updateBoard(boolean[][] f, int x, int y){
		boolean temp[][] = f;

		for (int ii = 1; ii < x - 1; ii++){
			for (int jj = 1; jj < y - 1; jj++){
				
				//counting cells around this cell that are alive
				int count = 0;
				
				//counting around the cell
				for(int a = -1; a <= 1; a++){
					for(int b = -1; b <= 1; b++){
						if(a == 0 && b == 0){
							continue;
						}
						
						if(temp[ii+a][jj+b]){
							count++;
						}
					}
				}
				
				//updating
				if(temp[ii][jj] && (count < 2 || count > 3)){
					f[ii][jj] = false;
				}
				else if(!temp[ii][jj] && count == 3){
					f[ii][jj] = true;
				}
				else if(temp[ii][jj] && (count == 2 || count == 3)){
					f[ii][jj] = true;
				}
				else{
					f[ii][jj] = false;
				}
			}
		}
	}
}

