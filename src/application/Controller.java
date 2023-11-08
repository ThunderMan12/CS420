package application;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
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
        
        farmTreeView.setCellFactory((Callback<TreeView<FarmComponent>, TreeCell<FarmComponent>>) new Callback<TreeView<FarmComponent>, TreeCell<FarmComponent>>() {
            @Override
            public TreeCell<FarmComponent> call(TreeView<FarmComponent> param) {
                return new TreeCell<FarmComponent>() {
                    @Override
                    protected void updateItem(FarmComponent item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName()); // Assuming there's a getName() method in FarmComponent
                        }
                    }
                };
            }
        });
        
        
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
        if (selectedItem != null) {
            FarmComponent selectedComponent = selectedItem.getValue();

            // Create a Dialog
            Dialog<FarmComponent> dialog = new Dialog<>();
            dialog.setTitle("Edit " + selectedComponent.getName());

            // Create fields for each attribute (name, location, price, dimensions)
            TextField nameField = new TextField(selectedComponent.getName());
            TextField locationXField = new TextField(String.valueOf(selectedComponent.getXCoord()));
            TextField locationYField = new TextField(String.valueOf(selectedComponent.getYCoord()));
            // Add fields for other attributes

            GridPane grid = new GridPane();
            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Location X:"), 0, 1);
            grid.add(locationXField, 1, 1);
            grid.add(new Label("Location Y:"), 0, 2);
            grid.add(locationYField, 1, 2);
            // Add fields for other attributes

            dialog.getDialogPane().setContent(grid);

            // Add buttons to the dialog
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Set action for the OK button
            dialog.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    // Update the selected component with the edited attributes
                    selectedComponent.changeName(nameField.getText());
                    selectedComponent.changeXCoord(Integer.parseInt(locationXField.getText()));
                    selectedComponent.changeYCoord(Integer.parseInt(locationYField.getText()));
                    
                    selectedItem.setValue(selectedComponent);
                    
                    farmTreeView.refresh();
                    
                    // Update other attributes similarly
                    return selectedComponent;
                }
                return null;
            });

            // Show the dialog
            dialog.showAndWait();
        }
    }

    public void removeItem() {
        TreeItem<FarmComponent> selected = farmTreeView.getSelectionModel().getSelectedItem();
        if (selected != null && selected != rootItem) {
            selected.getParent().getChildren().remove(selected);
        }
    }


}
