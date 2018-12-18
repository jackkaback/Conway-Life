import java.util.Scanner;

public class life {

	public static void main(String[] args) {
		int x = 3;
		int y = 3;
		Scanner keyin = new Scanner(System.in);

		//Get x value
		getval(x, "width", keyin);

		//Get y val
		getval(y, "height", keyin);

		boolean field[][] = new boolean[x][y];


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
}

