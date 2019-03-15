package textExcel;

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
		}else if(command.split(" ").length >= 3) {// for <cell> = ""
			SpreadsheetLocation loc = new SpreadsheetLocation(command.substring(0,3));
			sheet[loc.getRow()][loc.getCol()] = new TextCell(command.substring(5));
			toReturn = getGridText();
		}else if(command.equals("clear")) {//for clear
			for(int i=0; i <sheet.length; i++) {
				for(int j=0; j<sheet[0].length; j++) {
					sheet[i][j]= new EmptyCell();
				}
			}
			toReturn = getGridText();
		} else if(command.split(" ").length == 2) {//for clear <cell>
			SpreadsheetLocation loc = new SpreadsheetLocation(command.substring(6));
			sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
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

}
