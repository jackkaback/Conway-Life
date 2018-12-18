import java.util.Scanner;

public class life {

	public static void main(String[] args) {
		int x = 3;
		int y = 3;
		Scanner keyin = new Scanner(System.in);

		//Get x value
		x = getval(x, "width", keyin);

		//Get y val
		y = getval(y, "height", keyin);

		//edges get cut off
		x += 2;
		y += 2;

		boolean field[][] = new boolean[x][y];
		initializeArea(field, x, y);

		printBoard(field, x, y);

	}

	private static int getval(int val, String name, Scanner keyin){

		while(true) {
			System.out.println("Enter the " + name +" of the area");
			String temp = keyin.nextLine();
			if (isNumeric(temp)) {
				val = Integer.parseInt(temp);
				break;
			}
		}

		return val;
	}

	private static void initializeArea(boolean[][] f, int x, int y){
		for(int ii = 0; ii < x; ii++){
			for(int jj = 0; jj < y; jj++){
				f[ii][jj] = false;
			}
		}
	}

	private static boolean isNumeric(String checkString) {
		int ii = 0;

		if (checkString == null) {
			return false;
		}

		for ( ;ii < checkString.length(); ii++) {
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
}

