package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
//constructor
	private Cell[][] sheet;
	public Spreadsheet() {
		sheet = new EmptyCell[20][12];//initialize a 2D array of EmptyCells
	}
	@Override
	public String processCommand(String command)
	{
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getRows()
	{
		// TODO Auto-generated method stub
		return sheet.length;
	}

	@Override
	public int getCols()
	{
		// TODO Auto-generated method stub
		return sheet[0].length;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGridText()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
