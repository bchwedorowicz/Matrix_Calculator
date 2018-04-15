package pl.beata.matrix.model;

import org.junit.Test;

import junit.framework.Assert;

public class MatrixTest {

    @Test
    public void shouldBeEquals() {
	// given

	Matrix m1 = new Matrix(2, 2);
	Matrix m2 = new Matrix(2, 2);
	Matrix m3 = new Matrix(2, 2);

	m1.setCellValue(0, 0, 11);
	m1.setCellValue(0, 1, 5);
	m1.setCellValue(1, 0, 9);
	m1.setCellValue(1, 1, 10);

	m2.setCellValue(0, 0, 11);
	m2.setCellValue(0, 1, 5);
	m2.setCellValue(1, 0, 9);
	m2.setCellValue(1, 1, 10);

	m3.setCellValue(0, 0, 12);
	m3.setCellValue(0, 1, 5);
	m3.setCellValue(1, 0, 9);
	m3.setCellValue(1, 1, 10);

	// when

	boolean actualT = m1.equals(m2);
	boolean actualF = m1.equals(m3);

	// then

	Assert.assertEquals(true, actualT);
	Assert.assertEquals(false, actualF);

    }

}
