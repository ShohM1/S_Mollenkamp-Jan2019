//@author Shoh Mollenkamp
//@version March 2019
//This class gets 
package textExcel;

public class RealCell implements Cell {
	private String value;
	public RealCell(String command) {//construct
		if(command.charAt(0)==' ')
			command=command.substring(1);
		value = command;
	}
	public String fullCellText() {
		return value;
	}
	public String abbreviatedCellText() {
		return (getDoubleValue() + "          ").substring(0, 10);
	}
	public double getDoubleValue(){
		return Double.parseDouble(value);//Turn string into double
	}
	

}

