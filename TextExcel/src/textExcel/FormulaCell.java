package textExcel;

public class FormulaCell extends RealCell {
	public FormulaCell(String command) {
		super(command);
	}
	public double getDoubleValue() {
		String[] separation = super.fullCellText().replace("( ", "").replace(" )", "" ).split(" ");//split between operands and operators
		for(int i=0; i<(separation.length-1)/2; i++) {
    		int calcNow = 1;//what to calculate
    		//for(int j=1; j<separation.length; j+=2) {
    		//	if(separation[j].equals("/")||separation[j].equals("*")) {//if there's multiply or divide
    		//		calcNow = j;
    		//		j+=separation.length;//end the loop when found
    		//	}
    		//}
    		String[] toCalculate = {separation[calcNow-1], separation[calcNow], separation[calcNow+1]};
    		for(String s: toCalculate) {
    			for(char c = 'A';c<'M';c++) {
    				if(s.contains(c+"")) {
    					SpreadsheetLocation loc = new SpreadsheetLocation(s);
    					s = Spreadsheet.getCell(loc).abbreviatedCellText();
    				}
    			}
    		}
    		double op1 = Double.parseDouble(toCalculate[0]);//put in values of first number
    		double op2 = Double.parseDouble(toCalculate[2]);//put in values of second number
    		String answer;
    		if(separation[calcNow].equals("+")) {
    			answer = op1 + op2 + "";
    		}else if(separation[calcNow].equals("-")) {
    			answer = op1 - op2 + "";
    		}else if(separation[calcNow].equals("*")) {
    			answer = op1 * op2 + "";
    		}else {
    			answer = op1 / op2 + "";
    		}
    		
    		for(int k=0; k<separation.length; k++) {//move around the values in the array
    			separation[k] = separation[k];
    			if(k==calcNow-1) {
    				separation[k] = answer;
    			}else if(separation.length-k<3) {
    				separation[k] = "";
    			}else if(k-calcNow>=0) {
    				separation[k] = separation[k+2];
    			}
    		}
    	}
		return Double.parseDouble(separation[0]);
	}
	
}
