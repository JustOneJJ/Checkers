import java.util.Scanner;


public class Test {

	/**
	 * @param args
	 */
	
	public static int getInput(){
	    @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	        while (!sc.hasNextInt()) {
	            System.out.println("That's not a number!");
	            sc.next();
	        }
	        int number = sc.nextInt();
	        //sc.close();
	        return number;
	}
	
	public static void main(String[] args) {
		
		Board b = new Board();
		System.out.println(b.print());
/*		Coordinate c = new Coordinate(1,6);
		System.out.println( c.toString() );
		
		
		Piece p1 = new Piece(c, Color.WHITE);
		System.out.println( p1.toString() );
		
		System.out.println( p1.isInPosition(c) );
		c.setX(7);
		System.out.println( p1.isInPosition(c) );
		
		System.out.println("Enter X coordinate:");
		int x = getInput();
		System.out.println("Enter Y coordinate:");
		int y = getInput();
		
		Coordinate custom = new Coordinate(x,y);
		System.out.println(custom);
		p1.println();
		abc
	*/	
		
	}
	
}
