package application;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Controller {
	
	@FXML
	private AnchorPane farmMap = new AnchorPane();
	@FXML
	private TreeView<FarmItem> farmTreeView;
	private FarmItem root = new FarmItem("Root", 0, 0, 0, 0, 0, 0);
	private TreeItem<FarmItem> rootItem = new TreeItem<FarmItem> (root);

    
    @FXML
    public void createCommandCenter() {
    	Rectangle cmndCntr = new Rectangle();
    	cmndCntr.setFill(Color.GRAY);
    	cmndCntr.setLayoutX(10);
    	cmndCntr.setLayoutY(10);
    	cmndCntr.setHeight(80);
    	cmndCntr.setWidth(90);
    	cmndCntr.setOpacity(1.0);
    	cmndCntr.setStroke(Color.BLACK);
        farmMap.getChildren().add(cmndCntr);
    }
    public void createCrops() {
    	Rectangle crops = new Rectangle();
    	crops.setFill(Color.GREEN);
    	crops.setLayoutX(500);
    	crops.setLayoutY(690);
    	crops.setHeight(100);
    	crops.setWidth(90);
    	crops.setOpacity(1.0);
    	crops.setStroke(Color.BLACK);
        farmMap.getChildren().add(crops);
    }
    public void createBarn() {
    	Rectangle barn = new Rectangle();
    	barn.setFill(Color.RED);
    	barn.setLayoutX(10);
    	barn.setLayoutY(640);
    	barn.setHeight(100);
    	barn.setWidth(150);
    	barn.setOpacity(1.0);
    	barn.setStroke(Color.BLACK);
        farmMap.getChildren().add(barn);
    }
	
	public void addItem() {
	    TreeItem<FarmItem> selected = farmTreeView.getSelectionModel().getSelectedItem();
	    farmTreeView.setRoot(rootItem);
	    if (selected != null) {
	    	FarmItem something = new FarmItem("something", 0, 0, 0, 0, 0, 0);
	        TreeItem<FarmItem> newItem = new TreeItem<>(something);
	        selected.getChildren().add(newItem);
	    }
	    else {
	    	System.out.print("here");
	    }
	}
	
	public void removeItem() {
	    TreeItem<FarmItem> selected = farmTreeView.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	        selected.getParent().getChildren().remove(selected);
	    }
	}
	
}
