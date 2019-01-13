import java.util.Scanner;
import java.util.Random;

import static java.lang.Thread.sleep;

public class life {

	public static void main(String[] args) throws InterruptedException {
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

		while(true) {
			System.out.println("Input cell placement? (y/n) Run for generations? (r) Quit (q): ");
			String input = keyin.nextLine();
			input = input.toLowerCase();

			if(input.contains("q")){
				break;
			}

			if(input.contains("r")){
				runGenerations(field, x, y, keyin);
			}

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

	private static void getFill(boolean[][] f, int x, int y, Scanner keyin){
		int x1, x2, y1, y2;

		while(true){
			System.out.println("First X value");
			String temp = keyin.nextLine();

			if (isNumeric(temp)){
				x1 = Integer.parseInt(temp);

				if(x1 < x){
					break;
				}
			}
		}

		while(true){
			System.out.println("First Y value");
			String temp = keyin.nextLine();

			if (isNumeric(temp)){
				y1 = Integer.parseInt(temp);

				if(y1 < y){
					break;
				}
			}
		}

		while(true){
			System.out.println("Second X value");
			String temp = keyin.nextLine();

			if (isNumeric(temp)){
				x2 = Integer.parseInt(temp);

				if(x2 < x){
					break;
				}
			}
		}

		while(true){
			System.out.println("Second y value");
			String temp = keyin.nextLine();

			if (isNumeric(temp)){
				y2 = Integer.parseInt(temp);

				if(y2 < y){
					break;
				}
			}
		}

		for(int ii = x1; ii < x2; ii++){
			for(int jj = y1; jj < y2; jj++){
				f[ii][jj] = !f[ii][jj];
			}
		}

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

				f[ii][jj] = false;

//				f[ii][jj] = rand.nextBoolean();
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

	private static void placeCells(boolean[][] f, int x, int y, Scanner keyin){

		while(true){
			System.out.print("(D)ot, (F)ill, (P)rint field, (E)xit");
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

			else if(input.contains("f")){
				getFill(f, x, y, keyin);
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
	private static void runGenerations(boolean[][] f, int x, int y, Scanner keyin) throws InterruptedException {
		int gens;
		int delay;

		while(true){
			System.out.println("How many generations?");
			String temp = keyin.nextLine();

			if(isNumeric(temp)){
				gens = Integer.parseInt(temp);
				break;
			}
		}

		while(true){
			System.out.println("How long of a delay between the generations in milliseconds?");
			String temp = keyin.nextLine();

			if(isNumeric(temp)){
				delay = Integer.parseInt(temp);
				break;
			}
		}

		for(int ii = 0; ii < gens; ii++){
			sleep(delay);
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

