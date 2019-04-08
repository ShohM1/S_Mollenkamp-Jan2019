//@author Shoh Mollenkamp
//@version March 2019
//This class deals with percents
package textExcel;

public class PercentCell extends RealCell {
	public PercentCell(String command) {
		super(command);
	}
	public double getDoubleValue() {
		return Double.parseDouble(super.fullCellText().substring(0, super.fullCellText().length()-1))/100;
		//get the % value from super, take out the "%" and make it double and divide it by 100 to get the decimal value
	}
	public String fullCellText() {
		return getDoubleValue()+"";
	}
	public String abbreviatedCellText() {
		return (((int) (getDoubleValue() * 100) + "%")  + "          ").substring(0, 10);//convert it back into %
	}

}
