import java.util.Scanner;
import java.util.Random;

import static java.lang.Thread.*;


public class life {

	public static void main(String[] args) throws InterruptedException {
		int x = 3;
		int y = 3;
		Scanner keyin = new Scanner(System.in);

		//Get x value
		x = getval(x, "height", keyin);

		//Get y val
		y = getval(y, "width", keyin);

		//edges get cut off
		x += 2;
		y += 2;

		boolean field[][] = new boolean[x][y];
		initializeArea(field, x, y);

		printBoard(field, x, y);
		System.out.println("------------------------------------------------------");
		//getData(field, x, y, keyin);

		while(true) {
			updateBoard(field, x, y);
			printBoard(field, x, y);
			System.out.println("------------------------------------------------------");
			String input = keyin.nextLine();
			//placeCells(field, x, y, keyin);
			sleep(4000);
		}
	}

	//TODO add this feature in
	private static void placeCells(boolean[][] f, int x, int y, Scanner keyin){
		String input = keyin.nextLine();
	}

	private static int getval(int val, String name, Scanner keyin){

		while(true) {
			System.out.print("Enter the " + name +" of the area: ");
			String temp = keyin.nextLine();
			if (isNumeric(temp)) {
				val = Integer.parseInt(temp);
				break;
			}
		}

		return val;
	}

	private static void initializeArea(boolean[][] f, int x, int y){
		Random rand = new Random();
		for(int ii = 0; ii < x; ii++){
			for(int jj = 0; jj < y; jj++){
				f[ii][jj] = rand.nextBoolean();
//				if(ii == 0 || ii == x-1 || jj == 0 || jj == y-1){
//					f[ii][jj] = true;
//				}
//				else{
//					f[ii][jj] = false;
//				}
			}
		}
	}

	private static boolean isNumeric(String checkString) {
		if (checkString == null) {
			return false;
		}

		for (int ii = 0; ii < checkString.length(); ii++) {
			if (checkString.charAt(ii) < '0' || checkString.charAt(ii) > '9') { //this checks if the input is only numbers
				return false;
			}
		}
		return true;
	}

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

	private static void updateBoard(boolean[][] f, int x, int y){
		boolean temp[][] = f;

		for (int ii = 1; ii < x - 1; ii++){
			for (int jj = 1; jj < y - 1; jj++){
				int count = 0;

				//above
				if(temp[ii-1][jj-1]){
					count++;
				}
				if(temp[ii][jj-1]){
					count++;
				}
				if(temp[ii+1][jj-1]){
					count++;
				}

				//sides
				if(temp[ii-1][jj]){
					count++;
				}if(f[ii+1][jj]){
					count++;
				}

				//below
				if(temp[ii-1][jj+1]){
					count++;
				}
				if(temp[ii][jj+1]){
					count++;
				}
				if(temp[ii+1][jj+1]){
					count++;
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
			}
		}
	}
}

