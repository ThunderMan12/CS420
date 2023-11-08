package application;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Controller {
	
	@FXML
	private TreeView<FarmItem> farmTreeView;
	private FarmItem root = new FarmItem("Root", 0, 0, 0, 0, 0, 0);
    private TreeItem<FarmItem> rootItem = new TreeItem<FarmItem> (root);
    		
	
	
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
