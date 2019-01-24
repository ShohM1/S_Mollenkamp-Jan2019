package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) 
    {
    	Scanner console = new Scanner(System.in);
        System.out.println("Enter a mathematical expression that you want to calculate (e.g. 1_2/3 + 4)");
        String input = console.nextLine();//take input
        String ans = "1";
        while(!input.equals("quit")) {
        	input = input.replaceAll("ans", ans);
        	ans = produceAnswer(input);//run multiple produce answers
        	System.out.println(ans);//print out the answer
        	System.out.println("Enter another mathematical expression or type \"quit\", if you want to quit.");
        	input = console.nextLine();//get the next input
        } 
        console.close();

    }
    
    public static String produceAnswer(String input)
    { 
    	String[] separation = input.split(" ");//split between operands and operators
    	for(int i=0; i<(separation.length-1)/2; i++) {
    		int calcNow = 1;//what to calculate
    		//for(int j=1; j<separation.length; j+=2) {
    		//	if(separation[j].equals("/")||separation[j].equals("*")) {//if there's multiply or divide
    		//		calcNow = j;
    		//		j+=separation.length;//end the loop when found
    		//	}
    		//}
    		String checkValid = separation[calcNow].replace("-","+").replace("*","+").replace("/", "+");
    		for(int k=-1; k<=1; k+=2) {
    			String checkValid2 = separation[calcNow + k].replace("1","0").replace("2","0").replace("3","0").replace("4","0").replace("5","0").replace("6","0").replace("7","0").replace("8","0").replace("9","0").replace("/","").replace("_","").replace("-","");
    			for(int l=0; l<checkValid2.length();l++) {
    				if(checkValid2.charAt(l)!='0') {//checking if the input is just numbers,"-", "/", or "_"
    					checkValid = "notWork";
    				}
    			}
    			if(checkValid2.equals("")) {//checking if it's not only "-","/", or "_"
    				checkValid = "notWork";
    			}
    		}
    		if(!checkValid.equals("+")) {
    			return "ERROR: Input is in an invalid format.";//if it's not proper operator, error
    		}
    		String[] toCalculate = {separation[calcNow-1], separation[calcNow], separation[calcNow+1]};
    		if(toCalculate[1].equals("/")&&toCalculate[2].equals("0")) {
    			return "ERROR: Cannot divide by zero";
    		}
    		Fraction op1 = new Fraction(toCalculate[0]);//put in values of first fraction
    		Fraction op2 = new Fraction(toCalculate[2]);//put in values of second fraction
    		op1.operate(toCalculate[1], op2);
    		String answer1 = op1.toString();
    		for(int k=0; k<separation.length; k++) {//move around the values in the array
    			separation[k] = separation[k];
    			if(k==calcNow-1) {
    				separation[k] = answer1;
    			}else if(separation.length-k<3) {
    				separation[k] = "";
    			}else if(k-calcNow>=0) {
    				separation[k] = separation[k+2];
    			}
    		}
    	}
    	return separation[0];
    }

   
    
}
