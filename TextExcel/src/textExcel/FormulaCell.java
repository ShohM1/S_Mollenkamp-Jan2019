//@author Shoh Mollenkamp
//@version March 2019
package textExcel;

public class FormulaCell extends RealCell {
	private Cell[][] sheet;
	public FormulaCell(String command, Cell[][] grid) {
		super(command);
		sheet = grid;
	}
	public double getDoubleValue() {
		double sum=0;
		int count = 0;
		if(super.fullCellText().toUpperCase().contains("SUM")) {
			for(char c = super.fullCellText().toUpperCase().charAt(6); c <= super.fullCellText().toUpperCase().charAt(super.fullCellText().indexOf("-")+1);c++) {
				//find the alphabet by taking the char after "( AVG " then go until the char value of the alphabet after "-"
				for(int i = Integer.parseInt(super.fullCellText().substring(7, super.fullCellText().indexOf("-"))); i <= Integer.parseInt(super.fullCellText().substring(super.fullCellText().indexOf("-")+2, super.fullCellText().indexOf(")")-1));i++) {
					//Get the int value by taking the value after char to number before "-" and go to the value after "-" and char to before " )"
					Location tempLoc = new SpreadsheetLocation(c + "" + i );
					sum+=Double.parseDouble(sheet[tempLoc.getRow()][tempLoc.getCol()].abbreviatedCellText());
				}
			}
			return sum;
		}
		if(super.fullCellText().toUpperCase().contains("AVG")) {
			for(char c = super.fullCellText().toUpperCase().charAt(6); c <= super.fullCellText().toUpperCase().charAt(super.fullCellText().indexOf("-")+1);c++) {
				//find the alphabet by taking the char after "( AVG " then go until the char value of the alphabet after "-"
				for(int i = Integer.parseInt(super.fullCellText().substring(7, super.fullCellText().indexOf("-"))); i <= Integer.parseInt(super.fullCellText().substring(super.fullCellText().indexOf("-")+2, super.fullCellText().indexOf(")")-1));i++) {
					//Get the int value by taking the value after char to number before "-" and go to the value after "-" and char to before " )"
					Location tempLoc = new SpreadsheetLocation(c + "" + i );
					sum+=Double.parseDouble(sheet[tempLoc.getRow()][tempLoc.getCol()].abbreviatedCellText());
					count++;
				}
			}
			return sum/count;
		}
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
    		for(int j=0; j<3; j+=2) {
    			for(char c = 'A';c<'M';c++) {
    				if(toCalculate[j].toUpperCase().contains("" + c)) {
    					SpreadsheetLocation loc = new SpreadsheetLocation(toCalculate[j]);
    					toCalculate[j] = sheet[loc.getRow()][loc.getCol()].abbreviatedCellText();
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
