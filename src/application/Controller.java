package application;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    private TreeView<FarmComponent> farmTreeView;

    private TreeItem<FarmComponent> rootItem;

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
