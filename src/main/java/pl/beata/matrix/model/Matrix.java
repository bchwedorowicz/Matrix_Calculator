package pl.beata.matrix.model;

public class Matrix {

    private int rowsCount;
    private int columnsCount;
    private int[][] cellsValues;

    public Matrix(int rowsCount, int columnsCount) {
	this.rowsCount = rowsCount;
	this.columnsCount = columnsCount;
	cellsValues = new int[rowsCount][columnsCount];
    }

    public int getRowsCount() {
	return rowsCount;
    }

    public int getColumnsCount() {
	return columnsCount;
    }

    public int getCellValue(int rowIndex, int columnIndex) {
	return cellsValues[rowIndex][columnIndex];
    }

    public void setCellValue(int rowIndex, int columnIndex, int cellValue) {
	this.cellsValues[rowIndex][columnIndex] = cellValue;
    }
}
