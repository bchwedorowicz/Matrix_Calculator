package pl.beata.matrix.model;

import pl.beata.matrix.service.MatrixCalculatorService;

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
    
    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < this.getRowsCount(); i++) {
	    for (int j = 0; j < this.getColumnsCount(); j++) {
		sum += this.getCellValue(i, j);
	    }}
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Matrix)) {
	    return false;
	}
	Matrix matrix = (Matrix) obj;
	if ((this.columnsCount != matrix.columnsCount) || (this.rowsCount != matrix.rowsCount)) {
	    return false;
	}
	for (int i = 0; i < rowsCount; i++) {
	    for ( int j = 0; j < columnsCount; j++) {
		if (this.getCellValue(i, j) != matrix.getCellValue(i, j)) {
		    return false;
		}
	    }
	}
	return true;
    }
}
