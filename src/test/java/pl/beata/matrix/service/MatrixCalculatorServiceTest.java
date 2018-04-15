package pl.beata.matrix.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.beata.matrix.model.Matrix;

public class MatrixCalculatorServiceTest {

    private final static int[][] FIRST_SQUARE_MATRIX = { { 9, 2 }, { 5, 5 } };
    private final static int[][] SECOND_SQUARE_MATRIX = { { 2, 3 }, { 4, 5 } };
    private final static int[][] FIRST_RECTANGULAR_MATRIX = { { 3, 4, 3 }, { 0, 3, 1 } };
    private final static int[][] SECOND_RECTANGULAR_MATRIX = { { 1, 1 }, { 2, 2 }, { 0, 2 } };

    private MatrixCalculatorService matrixCalculatorService;

    @BeforeEach
    public void createServiceObject() {
	matrixCalculatorService = new MatrixCalculatorService();
    }

    public Matrix createMatrix(int[][] matrix) {
	Matrix m = new Matrix(matrix.length, matrix[0].length);
	for (int i = 0; i < m.getRowsCount(); i++) {
	    for (int j = 0; j < m.getColumnsCount(); j++) {
		m.setCellValue(i, j, matrix[i][j]);
	    }
	}
	return m;

    }

    @Test
    public void shouldAddMatrices() {
	// given

	Matrix m1 = createMatrix(FIRST_SQUARE_MATRIX);
	Matrix m2 = createMatrix(SECOND_SQUARE_MATRIX);

	Matrix expectedMatrix = new Matrix(2, 2);
	expectedMatrix.setCellValue(0, 0, 11);
	expectedMatrix.setCellValue(0, 1, 5);
	expectedMatrix.setCellValue(1, 0, 9);
	expectedMatrix.setCellValue(1, 1, 10);

	// when

	Matrix actualMatrix = matrixCalculatorService.addMatrices(m1, m2);

	// then

	Assert.assertEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void shouldSubstractMatrices() {
	// given

	Matrix m1 = createMatrix(FIRST_SQUARE_MATRIX);
	Matrix m2 = createMatrix(SECOND_SQUARE_MATRIX);

	Matrix expectedMatrix = new Matrix(2, 2);
	expectedMatrix.setCellValue(0, 0, 7);
	expectedMatrix.setCellValue(0, 1, -1);
	expectedMatrix.setCellValue(1, 0, 1);
	expectedMatrix.setCellValue(1, 1, 0);

	// when

	Matrix actualMatrix = matrixCalculatorService.substractMatrices(m1, m2);

	// then

	Assert.assertEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void shouldMultiplySquareMatrices() {
	// given

	Matrix m1 = createMatrix(FIRST_SQUARE_MATRIX);
	Matrix m2 = createMatrix(SECOND_SQUARE_MATRIX);

	Matrix expectedMatrix = new Matrix(2, 2);
	expectedMatrix.setCellValue(0, 0, 26);
	expectedMatrix.setCellValue(0, 1, 37);
	expectedMatrix.setCellValue(1, 0, 30);
	expectedMatrix.setCellValue(1, 1, 40);

	// when

	Matrix actualMatrix = matrixCalculatorService.multiplyMatrices(m1, m2);

	// then

	Assert.assertEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void shouldMultiplyRectangularMatrices() {
	Matrix m1 = createMatrix(FIRST_RECTANGULAR_MATRIX);
	Matrix m2 = createMatrix(SECOND_RECTANGULAR_MATRIX);

	Matrix expectedMatrix = new Matrix(2, 2);
	expectedMatrix.setCellValue(0, 0, 11);
	expectedMatrix.setCellValue(0, 1, 17);
	expectedMatrix.setCellValue(1, 0, 6);
	expectedMatrix.setCellValue(1, 1, 8);

	// when

	Matrix actualMatrix = matrixCalculatorService.multiplyMatrices(m1, m2);

	// then

	Assert.assertEquals(expectedMatrix, actualMatrix);
    }

}
