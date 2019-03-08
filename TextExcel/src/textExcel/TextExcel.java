
package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
        System.out.print("Enter a command: ");
        String input = console.nextLine();//take input
        Spreadsheet sheet = new Spreadsheet();
        while(!input.equals("quit")) {
        	System.out.println(sheet.processCommand(input));
        	Location loc = new SpreadsheetLocation(input);
        	System.out.println(loc.getCol());
        	System.out.println(loc.getRow());
        	System.out.println("Enter a command:");
        	input = console.nextLine();//get the next input
        	
        }
        console.close();// Add your command loop here
	}

}
