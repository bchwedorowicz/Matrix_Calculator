package pl.beata.matrix.model;

import javafx.beans.value.ObservableValue;

public class TableRowModel {

    private ObservableInteger[] values;

    public TableRowModel(int columnsCount) {
	values = new ObservableInteger[columnsCount];
	for (int i = 0; i < columnsCount; i++) {
	    values[i] = new ObservableInteger();
	}
    }

    public int getCellValue(int index) {
	ObservableInteger obj = values[index];
	return obj.getValue();
    }

    public void setCellValue(int index, int value) {
	ObservableInteger cell = values[index];
	cell.setValue(value);
    }

    public ObservableValue<Integer> getObservableCell(int columnIndex) {
	return values[columnIndex];
    }

}
