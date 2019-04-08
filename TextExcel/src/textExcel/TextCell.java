//@author Shoh Mollenkamp
//@version March 2019
package textExcel;

public class TextCell implements Cell{
	private String cellValue;
	public TextCell(String input) {
		if(input.charAt(0)==' ')
			input=input.substring(1);
		cellValue = input;
	}
	public String fullCellText() {
		return cellValue;
	}
	public String abbreviatedCellText() {
		String abbreviated = cellValue.substring(1,cellValue.length()-1) + "          ";
			abbreviated = abbreviated.substring(0, 10);
		return abbreviated;
		
	}

}
