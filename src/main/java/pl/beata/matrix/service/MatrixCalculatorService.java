package pl.beata.matrix.service;

import pl.beata.matrix.model.Matrix;

public class MatrixCalculatorService {

    public Matrix addMatrices(Matrix m1Table, Matrix m2Table) {

	Matrix resultMatrix = new Matrix(m1Table.getRowsCount(), m1Table.getColumnsCount());

	for (int i = 0; i < m1Table.getRowsCount(); i++) {
	    for (int j = 0; j < m1Table.getColumnsCount(); j++) {
		int m1TableCellValue = m1Table.getCellValue(i, j);
		int m2TableCellValue = m2Table.getCellValue(i, j);
		int sum = m1TableCellValue + m2TableCellValue;
		resultMatrix.setCellValue(i, j, sum);
	    }
	}
	return resultMatrix;
    }

    public Matrix substractMatrices(Matrix m1Table, Matrix m2Table) {
	Matrix resultMatrix = new Matrix(m1Table.getRowsCount(), m1Table.getColumnsCount());

	for (int i = 0; i < m1Table.getRowsCount(); i++) {
	    for (int j = 0; j < m1Table.getColumnsCount(); j++) {
		int m1TableCellValue = m1Table.getCellValue(i, j);
		int m2TableCellValue = m2Table.getCellValue(i, j);
		int difference = m1TableCellValue - m2TableCellValue;
		resultMatrix.setCellValue(i, j, difference);
	    }
	}
	return resultMatrix;
    }

    public Matrix multiplyMatrices(Matrix m1Table, Matrix m2Table) {
	Matrix resultMatrix = new Matrix(m1Table.getRowsCount(), m2Table.getColumnsCount());
	for (int k = 0; k < m1Table.getRowsCount(); k++) {
	    for (int i = 0; i < m1Table.getRowsCount(); i++) {
		int result = 0;
		for (int j = 0; j < m1Table.getColumnsCount(); j++) {
		    int m1TableCellValue = m1Table.getCellValue(k, j);
		    int m2TableCellValue = m2Table.getCellValue(j, i);
		    result += m1TableCellValue * m2TableCellValue;
		}
		resultMatrix.setCellValue(k, i, result);
	    }
	}
	return resultMatrix;
    }

}
