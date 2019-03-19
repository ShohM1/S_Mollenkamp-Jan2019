//@author Shoh Mollenkamp
//@version March 4, 2019
package textExcel;

public class EmptyCell implements Cell {
	public EmptyCell() {
	}
	public String abbreviatedCellText() {
		return "          "; 
	}// text for spreadsheet cell display, must be exactly length 10
	
	public String fullCellText(){
		return "" ;// text for individual cell inspection, not truncated or padded
	}
}
