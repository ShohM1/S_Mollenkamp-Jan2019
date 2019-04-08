//@author Shoh Mollenkamp
//@version March 2019
//Client code
package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
        System.out.print("Enter a command: ");
        String input = console.nextLine();//take input
        Spreadsheet sheet = new Spreadsheet();
        while(!input.equals("quit")) {
        	System.out.println(sheet.processCommand(input));
        	System.out.println("Enter a command:");
        	input = console.nextLine();//get the next input
        	
        }
        
        console.close();// Add your command loop here
	}

}
