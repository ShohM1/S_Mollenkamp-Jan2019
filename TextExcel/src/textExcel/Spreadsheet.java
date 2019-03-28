//@author Shoh Mollenkamp
//@version March 2019
package textExcel;
import java.util.ArrayList;
// Update this file with your own code.

public class Spreadsheet implements Grid
{
//constructor
	private Cell[][] sheet;
	public Spreadsheet() {
		sheet = new Cell[20][12];//initialize a 2D array of Cells
		for(int i=0; i <sheet.length; i++) {
			for(int j=0; j<sheet[0].length; j++) {
				sheet[i][j]= new EmptyCell();
			}
		}
	}
	@Override
	public String processCommand(String command){
		String toReturn = "";
		if(command.length()==2||command.length()==3) {//for <cell>
			SpreadsheetLocation loc = new SpreadsheetLocation(command);
			toReturn = sheet[loc.getRow()][loc.getCol()].fullCellText();
		}else if(command.split(" ").length >= 3) {
			SpreadsheetLocation loc = new SpreadsheetLocation(command.substring(0,3));
			if(command.contains("\"")) {// for <cell> = ""
				sheet[loc.getRow()][loc.getCol()] = new TextCell(command.substring(5));
			}else if(command.contains("%")) {
				sheet[loc.getRow()][loc.getCol()] = new PercentCell(command.substring(5));
			}else if(command.contains("(")) {
				sheet[loc.getRow()][loc.getCol()] = new FormulaCell(command.substring(5), sheet);
			}else {
				sheet[loc.getRow()][loc.getCol()] = new ValueCell(command.substring(5));
			}
			toReturn = getGridText();
		}else if(command.toLowerCase().equals("clear")) {//for clear
			for(int i=0; i <sheet.length; i++) {
				for(int j=0; j<sheet[0].length; j++) {
					sheet[i][j]= new EmptyCell();
				}
			}
			toReturn = getGridText();
		} else if(command.split(" ").length == 2) {
			if(command.toLowerCase().contains("clear")) {//for clear <cell>
				SpreadsheetLocation loc = new SpreadsheetLocation(command.substring(6));
				sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
			}else {
				sort(command);
			}
			toReturn = getGridText();
		}
		return toReturn;
	}

	@Override
	public int getRows(){
		// TODO Auto-generated method stub
		return sheet.length;
	}

	@Override
	public int getCols(){
		// TODO Auto-generated method stub
		return sheet[0].length;
	}

	@Override
	public Cell getCell(Location loc){
		return sheet[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText(){
		String gridText ="   |";
		for(char i='A'; i<'A'+sheet[0].length; i++) {
			gridText+=i+"         |";
		}
		gridText+="\n";
		for(int i=0; i<sheet.length; i++) {
			
			if(i<9)
				gridText+=i+1 +"  |";
			else
				gridText+=i+1 +" |";
			for(int j=0; j<sheet[0].length; j++) {
				gridText+=(sheet[i][j].abbreviatedCellText() + "          ").substring(0,10) + "|";
			}
				gridText+="\n";
		}
		
		return gridText ;
		
	}
	public void sort (String command) {
		Cell[] toSort = new Cell[(command.toUpperCase().charAt(command.indexOf("-")+1)-command.toUpperCase().charAt(6)+1)*(Integer.parseInt(command.substring(command.indexOf("-")+2, command.length()))-Integer.parseInt(command.substring(7, command.indexOf("-")))+1)];
		int count = 0;
		Cell[] sorted = new Cell[toSort.length];
		RealCell[] toSortReal = new RealCell[toSort.length];
		RealCell[] sortedReal = new RealCell[toSort.length];
		boolean realCell=false;
		//get the difference in char values and int values add one on each to include the end and multiple them to get the number of cells that has to be made
		for(char c = command.toUpperCase().charAt(6); c <= command.toUpperCase().charAt(command.indexOf("-")+1);c++) {
			//find the alphabet by taking the char after "sort? " then go until the char value of the alphabet after "-"
			for(int i = Integer.parseInt(command.substring(7, command.indexOf("-"))); i <= Integer.parseInt(command.substring(command.indexOf("-")+2, command.length()));i++) {
				//Get the int value by taking the value after char to number before "-" and go to the value at the end
				Location tempLoc = new SpreadsheetLocation(c + "" + i );
				if(sheet[tempLoc.getRow()][tempLoc.getCol()] instanceof RealCell)
				toSort[count]=sheet[tempLoc.getRow()][tempLoc.getCol()];
				if(sheet[tempLoc.getRow()][tempLoc.getCol()] instanceof RealCell)
					realCell = true;
				count++;
			}
		}
		if(!realCell) {
			for(int i=0; i<toSort.length;i++) {//to moving along toSort array
				sorted[i]=toSort[i]; //place the value to be at the after all the values for now
				for(int j = 0; j<i; j++) {//to move along sorted array
					for(int k = 0; k<toSort[i].fullCellText().length();k++) {//to move along toSort chars
						if(k<sorted[j].fullCellText().length()) {//to see if the char exists for that location 
							if(toSort[i].fullCellText().toUpperCase().charAt(k)<sorted[j].fullCellText().toUpperCase().charAt(k)) { //comparing chars
								for(int l = i; l>j;l--) {//to move around the values in array to the right
									sorted[l]=sorted[l-1];
								}
								sorted[j]=toSort[i];//replace the value
								j+=i+1;//end the loop as placing the value is complete. 
								break;
							}else if(toSort[i].fullCellText().toUpperCase().charAt(k)>sorted[j].fullCellText().toUpperCase().charAt(k)) {//later char
								break; //the value is larger, so it would be later
							}
						}else {
							break;
						}
					}
				}	
			}
		}else {
			for(int i=0; i<toSort.length;i++) {//to moving along toSort array
				sorted[i]=toSort[i]; //place the value to be at the after all the values for now
				for(int j = 0; j<i; j++) {//to move along sorted array
					if(Double.parseDouble(toSort[i].fullCellText())<Double.parseDouble(sorted[i].fullCellText())) { //comparing chars
						for(int k = i; k>j;k--) {//to move around the values in array to the right
							sorted[k]=sorted[k-1];
						}
						sorted[j]=toSort[i];//replace the value
						j+=i+1;//end the loop as placing the value is complete. 
						break;
					}else if(Double.parseDouble(toSort[i].fullCellText())>Double.parseDouble(sorted[i].fullCellText())) {//later char
						break; //the value is larger, so it would be later
					}
				}
			}	
		}
		count=0;//reset count
		if(command.toUpperCase().substring(4,5).equals("D")) 
			count = toSort.length-1;
		for(int i = Integer.parseInt(command.substring(7, command.indexOf("-"))); i <= Integer.parseInt(command.substring(command.indexOf("-")+2, command.length()));i++) {
			//Get the int value by taking the value after char to number before "-" and go to the value at the end
			for(char c = command.toUpperCase().charAt(6); c <= command.toUpperCase().charAt(command.indexOf("-")+1);c++) {
				//find the alphabet by taking the char after "sort? " then go until the char value of the alphabet after "-"
				Location tempLoc = new SpreadsheetLocation(c + "" + i );
				sheet[tempLoc.getRow()][tempLoc.getCol()]=sorted[count];
				if(command.toUpperCase().substring(4,5).equals("D")) 
					count--;
				else
					count++;
			}
		}
	}
	//For sort make an array of an appropriate size and put each value in as it checks if it would be later or not

}
