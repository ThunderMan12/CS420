package application;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Controller {

	@FXML
	private AnchorPane farmMap = new AnchorPane();
    @FXML
    private TreeView<FarmComponent> farmTreeView;

    private TreeItem<FarmComponent> rootItem;

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
    
    public void initialize() {
        rootItem = new TreeItem<>(new FarmItem("Farm", 0, 0, 0, 0, 0, 0));
        farmTreeView.setRoot(rootItem);
        
        // Set up context menu for adding new items on right-click
        farmTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                FarmComponent selectedComponent = farmTreeView.getSelectionModel().getSelectedItem().getValue();

                ContextMenu menu = new ContextMenu();

                MenuItem addFarmItem = new MenuItem("Add Farm Item");
                addFarmItem.setOnAction(e -> addFarmComponent(new FarmItem("New Farm Item", 0, 0, 0, 0, 0, 0), selectedComponent));

                MenuItem addItemContainer = new MenuItem("Add Item Container");
                addItemContainer.setOnAction(e -> addFarmComponent(new ItemContainer(null, "New Item Container", 0, 0, 0, 0, 0, 0), selectedComponent));

                MenuItem editItem = new MenuItem("Edit");
                editItem.setOnAction(e -> editFarmComponent());

                MenuItem deleteItem = new MenuItem("Delete");
                deleteItem.setOnAction(e -> removeItem());

                menu.getItems().addAll(addFarmItem, addItemContainer, editItem, deleteItem);
                farmTreeView.setContextMenu(menu);
            }
        });
        
    }

    public void addFarmComponent(FarmComponent newComponent, FarmComponent parentComponent) {
        TreeItem<FarmComponent> parent = farmTreeView.getSelectionModel().getSelectedItem();
        TreeItem<FarmComponent> newItem = new TreeItem<>(newComponent);
        parent.getChildren().add(newItem);
    }

    public void editFarmComponent() {
        TreeItem<FarmComponent> selectedItem = farmTreeView.getSelectionModel().getSelectedItem();
        // Perform editing for the selected component
    }

    public void removeItem() {
        TreeItem<FarmComponent> selected = farmTreeView.getSelectionModel().getSelectedItem();
        if (selected != null && selected != rootItem) {
            selected.getParent().getChildren().remove(selected);
        }
    }

//    private TreeItem<FarmComponent> findTreeItem(FarmComponent component) {
//    	FarmComponent selectedComponent = farmTreeView.getSelectionModel().getSelectedItem().getValue();
//    	return ;
//        // Find the TreeItem corresponding to the specified FarmComponent
//        // Implement the search logic here, searching within the tree structure for the given component
//        // Return the TreeItem associated with the provided FarmComponent
//    }

}
