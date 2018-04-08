package pl.beata.matrix.model;

import javafx.beans.value.ObservableValueBase;

public class ObservableInteger extends ObservableValueBase<Integer> {
    
    private int value;

    @Override
    public Integer getValue() {
	return value;
    }
    
    public void setValue(int value) {
	this.value = value;
	fireValueChangedEvent();
    }

}
