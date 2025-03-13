package com.example.financemanager.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;

public class SwitchButton extends HBox {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SwitchButton.class);
    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
    private SimpleStringProperty leftLabel = new SimpleStringProperty();
    private SimpleStringProperty rightLabel = new SimpleStringProperty();

    private ObjectProperty<EventHandler<ActionEvent>> onAction = new SimpleObjectProperty<>();

    public SwitchButton() {
        super();
        Button switchBtn = new Button();
        switchBtn.setPrefWidth(40);
        switchBtn.setOnAction(t -> {
            switchedOn.set(!switchedOn.get());
            onAction.get().handle(t);
        });

        setStyle("-fx-spacing: 0;");

        Label label = new Label();
        label.setText(getLeftLabel());
        label.setStyle("-fx-background-color: green;-fx-text-fill:white;-fx-alignment:center;-fx-pref-height: 25");
        label.setPrefWidth(getPrefWidth() - switchBtn.getPrefWidth());
        label.setMinHeight(25);
        getChildren().addAll(label, switchBtn);

        switchedOn.addListener((a, b, t1) -> {
            log.debug("Switched on: " + t1);
            if (t1) {
                label.setPrefWidth(getPrefWidth() - switchBtn.getPrefWidth());
                label.setPrefHeight(switchBtn.getHeight());
                label.setText(getLeftLabel());
                label.setStyle("-fx-background-color: green;-fx-text-fill:white;-fx-alignment:center;");
                getChildren().clear();
                getChildren().addAll(label, switchBtn);
            } else {
                label.setPrefWidth(getPrefWidth() - switchBtn.getPrefWidth());
                label.setPrefHeight(switchBtn.getHeight());
                label.setText(getRightLabel());
                getChildren().clear();
                getChildren().addAll(switchBtn, label);
            }
        });

    }

    public SimpleBooleanProperty switchOnProperty() { return switchedOn; }

    public final void setSwitchOn(boolean var1) {
        this.switchOnProperty().set(var1);
    }

    public final boolean isSwitchOn() {
        return this.switchOnProperty().get();
    }

    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return this.onAction;
    }

    public final void setOnAction(EventHandler<ActionEvent> var1) {
        this.onActionProperty().set(var1);
    }

    public final EventHandler<ActionEvent> getOnAction() {
        return (EventHandler)this.onActionProperty().get();
    }

    public SimpleStringProperty leftLabelProperty() {
        return leftLabel;
    }

    public SimpleStringProperty rightLabelProperty() {
        return rightLabel;
    }

    public void setLeftLabel(String leftLabel) {
        this.leftLabel.set(leftLabel);
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel.set(rightLabel);
    }

    public String getLeftLabel() {
        return leftLabel.get();
    }

    public String getRightLabel() {
        return rightLabel.get();
    }

}