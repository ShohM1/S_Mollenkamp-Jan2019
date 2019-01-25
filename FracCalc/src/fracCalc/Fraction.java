package fracCalc;

public class Fraction {
	private int[] fraction = {0, 0, 1};
	
	public Fraction (String operand){
		int slack = operand.indexOf("/");//checking for fraction
    	int undack = operand.indexOf("_");//checking for whole
    	if(slack==-1) {
    		operand = operand + "_0/1";
    	} else if(undack==-1) {
    		operand = "0_" + operand;
    	}
    	operand = operand.replace("_"," ").replace("/"," ");//make it splittable
    	String[] splitFrac = operand.split(" ");//split into fractions
    	for(int i=0; i<=2; i++) {
    		int inInt = Integer.parseInt(splitFrac[i]);//making string into int
    		fraction[i] = inInt;//store fractions
    	}
    	if(fraction[1]<0 && fraction[0]!=0) {
    		fraction[1] = -fraction[1];
    		fraction[0] = -fraction[0];
    	}
    	if(fraction[0]>0) {
    		fraction[1]+= fraction[0]*fraction[2];//make it improper
    	}else if(fraction[0]<0) {
    		fraction[1]= -fraction[1]+fraction[0]*fraction[2];//make it improper when negative
    	}
    	fraction[0] = 0;//get rid of the whole value because it's already added on numerator
	}
	
	public String toString (){
		String answer = fraction[1]/fraction[2] + "_" + Math.abs(fraction[1]%fraction[2]) +"/" + fraction[2];//back into string with mixed
        if(fraction[1]%fraction[2]==0) {
        	answer = fraction[1]/fraction[2]+"";//take out fraction if answer is whole
        } else if(fraction[1]/fraction[2]==0) {
        	answer = fraction[1]%fraction[2] + "/" + fraction[2];//take out 0_
        }
        return answer;
	}
	
	public void operate(String operator, Fraction frac2) {
		int[] answer = new int[3];
    	if(operator.equals("+")||operator.equals("-")) {//if it's + or -
    		if(operator.equals("-")) {
    			frac2.fraction[1]=-frac2.fraction[1];//make the second improper fraction negative if minus
    		}
    		answer[2] = fraction[2]*frac2.fraction[2];//denominator multiplied
    		answer[1] = fraction[1]*frac2.fraction[2] + frac2.fraction[1]*fraction[2];//add numerators multiplied by denom of other
    	}else if(operator.equals("*")||operator.equals("/")){//if it's * or /
    		if(operator.equals("/")) {
    			int temp = frac2.fraction[2];
    			frac2.fraction[2]=frac2.fraction[1];
    			frac2.fraction[1]=temp;//switch numerator and denominator
    		}
    		answer[1] = fraction[1]*frac2.fraction[1];//multiply numerator
    		answer[2] = fraction[2]*frac2.fraction[2];//multiply denominator
    	}
    	if(answer[2]<0) {
    		answer[2]=-answer[2];
    		answer[1]=-answer[1];//if negative on denominator, move it to numerator
    	}
    	int greatestCF = gcf(answer[1],answer[2]);
    	answer[1]/=greatestCF;
    	answer[2]/=greatestCF;
    	fraction = answer;//set fraction reference to answer
	}
	
	public static int gcf(int num1, int num2) {
		//finds greatest common factor by checking the divisibility of both values
		num1= Math.abs(num1);
		num2= Math.abs(num2);
		int divisor=num2;
		if(num1<num2 && num1!=0) {
			divisor = num1;
		}
		while(num1%divisor!=0||num2%divisor!=0){
		divisor--;
		}
		return divisor;
    }
}