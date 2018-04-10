package pl.beata.matrix.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import pl.beata.matrix.model.Matrix;
import pl.beata.matrix.model.TableRowModel;
import pl.beata.matrix.service.MatrixCalculatorService;

public class MatrixPaneController implements Initializable {

    @FXML
    private Spinner<Integer> m1ColumnsCount;

    @FXML
    private Spinner<Integer> m1RowsCount;

    @FXML
    private Spinner<Integer> m2ColumnsCount;

    @FXML
    private Spinner<Integer> m2RowsCount;

    @FXML
    private Button createMatrixButton;

    @FXML
    private TableView<TableRowModel> m1Table;

    @FXML
    private TableView<TableRowModel> m2Table;

    @FXML
    private TableView<TableRowModel> resultMatrixTable;

    @FXML
    private RadioButton addRButton;

    @FXML
    private RadioButton multiplyRButton;

    @FXML
    private RadioButton substractRButton;

    @FXML
    private Button calculateButton;

    private MatrixCalculatorService matrixCalculatorService = new MatrixCalculatorService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	groupRButtons();
	setButtonsDisabled();
	createMatrixButton.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		createM1M2Tables();
		createResultTable();
		makeButtonsEnabled();
		selectButton();
	    }
	});
	calculateButton.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		switchMathOperation();
	    }

	});
    }

    private void createTable(int columnsCount, int rowsCount, TableView<TableRowModel> table, boolean editable) {
	table.getColumns().clear();
	table.getItems().clear();
	ObservableList<TableRowModel> rowsList = FXCollections.observableArrayList();

	for (int i = 0; i < rowsCount; i++) {
	    rowsList.add(new TableRowModel(columnsCount));
	}

	for (int i = 0; i < columnsCount; i++) {
	    final int columnIndex = i;
	    TableColumn<TableRowModel, Integer> tableColumn = new TableColumn<TableRowModel, Integer>();
	    table.getColumns().add(tableColumn);
	    tableColumn.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<TableRowModel, Integer>, ObservableValue<Integer>>() {

			@Override
			public ObservableValue<Integer> call(CellDataFeatures<TableRowModel, Integer> param) {
			    TableRowModel row = param.getValue();

			    return row.getObservableCell(columnIndex);
			}
		    });
	    if (editable == true) {

		tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

		    @Override
		    public String toString(Integer object) {
			return object.toString();
		    }

		    @Override
		    public Integer fromString(String string) {
			return Integer.valueOf(string);
		    }

		}));
		tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableRowModel, Integer>>() {

		    @Override
		    public void handle(CellEditEvent<TableRowModel, Integer> event) {
			TableRowModel row = event.getRowValue();
			row.setCellValue(columnIndex, event.getNewValue());
		    }
		});
	    }
	}

	table.setItems(rowsList);
    }

    private void createM1M2Tables() {
	createTable(m1ColumnsCount.getValue(), m1RowsCount.getValue(), m1Table, true);
	createTable(m2ColumnsCount.getValue(), m2RowsCount.getValue(), m2Table, true);
    }

    private void groupRButtons() {
	ToggleGroup toggleGroup = new ToggleGroup();

	addRButton.setToggleGroup(toggleGroup);
	multiplyRButton.setToggleGroup(toggleGroup);
	substractRButton.setToggleGroup(toggleGroup);
    }

    private void createResultTable() {

	int m1RowsCount = m1Table.getItems().size();
	int m2ColumnsCount = m2Table.getColumns().size();

	createTable(m2ColumnsCount, m1RowsCount, resultMatrixTable, false);
    }

    private void makeButtonsEnabled() {
	setButtonsDisabled();
	if (m1ColumnsCount.getValue() == m2ColumnsCount.getValue()
		&& m1RowsCount.getValue() == m2RowsCount.getValue()) {
	    addRButton.setDisable(false);
	    substractRButton.setDisable(false);
	    calculateButton.setDisable(false);
	}
	if (m1ColumnsCount.getValue() == m2RowsCount.getValue()) {
	    multiplyRButton.setDisable(false);
	    calculateButton.setDisable(false);
	}
    }

    private void selectButton() {
	List<RadioButton> buttonList = Arrays.asList(addRButton, substractRButton, multiplyRButton);

	for (RadioButton radioButton : buttonList) {
	    if (radioButton.isSelected()) {
		if (radioButton.isDisable()) {
		    radioButton.setSelected(false);
		    selectFirstEnableButton(buttonList);
		}
		return;
	    }
	}
	selectFirstEnableButton(buttonList);
    }

    private void selectFirstEnableButton(List<RadioButton> buttonList) {
	buttonList.stream()
		.filter(s -> !s.isDisable())
		.findFirst()
		.ifPresent(f -> f.setSelected(true));
    }

    private void setButtonsDisabled() {
	addRButton.setDisable(true);
	multiplyRButton.setDisable(true);
	substractRButton.setDisable(true);
	calculateButton.setDisable(true);
    }

    private void setResultMatrixToTableView(Matrix result) {

	for (int i = 0; i < result.getRowsCount(); i++) {
	    TableRowModel row = new TableRowModel(result.getColumnsCount());
	    for (int j = 0; j < result.getColumnsCount(); j++) {
		int cellValue = result.getCellValue(i, j);
		row.setCellValue(j, cellValue);
	    }
	    resultMatrixTable.getItems().set(i, row);
	}
    }

    private void setTableViewToMatrix(TableView<TableRowModel> table, Matrix m) {
	for (int i = 0; i < m.getRowsCount(); i++) {
	    for (int j = 0; j < m.getColumnsCount(); j++) {
		int cellValue = table.getItems().get(i).getCellValue(j);
		m.setCellValue(i, j, cellValue);
	    }
	}
    }

    private void switchMathOperation() {
	Matrix m1 = new Matrix(m1Table.getItems().size(), m1Table.getColumns().size());
	Matrix m2 = new Matrix(m2Table.getItems().size(), m2Table.getColumns().size());
	setTableViewToMatrix(m1Table, m1);
	setTableViewToMatrix(m2Table, m2);
	if (addRButton.isSelected()) {
	    Matrix addResult = matrixCalculatorService.addMatrices(m1, m2);
	    setResultMatrixToTableView(addResult);
	}
	if (substractRButton.isSelected()) {
	    Matrix substractResult = matrixCalculatorService.substractMatrices(m1, m2);
	    setResultMatrixToTableView(substractResult);
	}
	if (multiplyRButton.isSelected()) {
	    Matrix multiplyResult = matrixCalculatorService.multiplyMatrices(m1, m2);
	    setResultMatrixToTableView(multiplyResult);
	}
    }

}