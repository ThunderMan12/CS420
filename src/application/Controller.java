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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.paint.Color;

public class Controller {

	@FXML
	private AnchorPane farmMap = new AnchorPane();
	@FXML
	private TreeView<FarmComponent> farmTreeView;

	private TreeItem<FarmComponent> rootItem;



	public void initialize() {
		// Make behavior for the treeview
		// Use the interface as the type of treeview
		rootItem = new TreeItem<>(new FarmItem("Farm", 0, 0, 0, 0, 0, 0));
		farmTreeView.setRoot(rootItem);

		farmTreeView.setCellFactory(
				(Callback<TreeView<FarmComponent>, TreeCell<FarmComponent>>) new Callback<TreeView<FarmComponent>, TreeCell<FarmComponent>>() {
					@Override
					public TreeCell<FarmComponent> call(TreeView<FarmComponent> param) {
						return new TreeCell<FarmComponent>() {
							@Override
							protected void updateItem(FarmComponent item, boolean empty) {
								super.updateItem(item, empty);

								if (empty || item == null) {
									setText(null);
								} else {
									setText(item.getName());
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
				addFarmItem.setOnAction(
						e -> addFarmComponent(new FarmItem("New Farm Item", 0, 0, 0, 0, 0, 0), selectedComponent));

				MenuItem addItemContainer = new MenuItem("Add Item Container");
				addItemContainer.setOnAction(e -> addFarmComponent(
						new ItemContainer(null, "New Item Container", 0, 0, 0, 0, 0, 0), selectedComponent));

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
		// Refresh treeview and destroy and rebuild map from the treeview
		farmTreeView.refresh();
		rebuildFarmMapFromTreeView();
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
			TextField priceField = new TextField(String.valueOf(selectedComponent.getPrice()));
			TextField lengthField = new TextField(String.valueOf(selectedComponent.getLength()));
			TextField widthField = new TextField(String.valueOf(selectedComponent.getWidth()));
			TextField heightField = new TextField(String.valueOf(selectedComponent.getHeight()));
			// Could add fields for other attributes if need be

			GridPane grid = new GridPane();
			grid.add(new Label("Name:"), 0, 0);
			grid.add(nameField, 1, 0);
			grid.add(new Label("Location X:"), 0, 1);
			grid.add(locationXField, 1, 1);
			grid.add(new Label("Location Y:"), 0, 2);
			grid.add(locationYField, 1, 2);
			grid.add(new Label("Price:"), 0, 3);
			grid.add(priceField, 1, 3);
			grid.add(new Label("Length"), 0, 4);
			grid.add(lengthField, 1, 4);
			grid.add(new Label("Width"), 0, 5);
			grid.add(widthField, 1, 5);
			grid.add(new Label("Height"), 0, 6);
			grid.add(heightField, 1, 6);


			dialog.getDialogPane().setContent(grid);

			// Add buttons to the dialog
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

			// Set action for the ok button
			dialog.setResultConverter(buttonType -> {
				if (buttonType == ButtonType.OK) {
					// Update the selected item with the edited attributes
					selectedComponent.changeName(nameField.getText());
					selectedComponent.changeXCoord(Integer.parseInt(locationXField.getText()));
					selectedComponent.changeYCoord(Integer.parseInt(locationYField.getText()));
					selectedComponent.changePrice(Float.parseFloat(priceField.getText()));
					selectedComponent.changeLength(Integer.parseInt(lengthField.getText()));
					selectedComponent.changeWidth(Integer.parseInt(widthField.getText()));
					selectedComponent.changeHeight(Integer.parseInt(heightField.getText()));

					selectedItem.setValue(selectedComponent);

					farmTreeView.refresh();
					rebuildFarmMapFromTreeView();

					// Update other attributes similarly
					return selectedComponent;
				}
				return null;
			});

			// Show the dialog
			dialog.showAndWait();
		}
	}

	private void createShapeOnFarmMap(FarmComponent farmComponent) {
		if (farmComponent.getName().equals("Drone")) {
			//If it's a drone use drone image
			FarmComponent farmEntity = (FarmComponent) farmComponent;
			Image droneImage = new Image("drone.png");
			ImageView droneImageView = new ImageView(droneImage);
			
			droneImageView.setFitHeight(50);
			droneImageView.setFitWidth(50);
			
			// Ensure the shape stays in view
			double maxX = farmMap.getWidth() - farmEntity.getWidth();
			double maxY = farmMap.getHeight() - farmEntity.getHeight();
			droneImageView.setLayoutX(Math.min(farmEntity.getXCoord(), maxX));
			droneImageView.setLayoutY(Math.min(farmEntity.getYCoord(), maxY));

			Text itemName = new Text(farmEntity.getName());
			itemName.setLayoutX(farmEntity.getXCoord() + 5);
			itemName.setLayoutY(farmEntity.getYCoord() + 5);
			farmMap.getChildren().add(droneImageView);

		} else if (farmComponent instanceof FarmComponent) {
			//Otherwise, use a square
			FarmComponent farmEntity = (FarmComponent) farmComponent;

			Rectangle shape = new Rectangle();
			shape.setFill(Color.GREEN);
			shape.setLayoutX(farmEntity.getXCoord());
			shape.setLayoutY(farmEntity.getYCoord());
			shape.setWidth(farmEntity.getWidth());
			shape.setHeight(farmEntity.getLength());
			shape.setOpacity(1.0);
			shape.setStroke(Color.BLACK);

			// Ensure the shape stays in view
			double maxX = farmMap.getWidth() - shape.getWidth();
			double maxY = farmMap.getHeight() - shape.getHeight();
			shape.setLayoutX(Math.min(farmEntity.getXCoord(), maxX));
			shape.setLayoutY(Math.min(farmEntity.getYCoord(), maxY));

			farmMap.getChildren().add(shape);

			// Display the name of the farm item or item container
			Text itemName = new Text(farmEntity.getName());
			itemName.setLayoutX(shape.getLayoutX() + 5);
			itemName.setLayoutY(shape.getLayoutY() + shape.getHeight() + 15);
			farmMap.getChildren().add(itemName);
		}
	}

	public void removeItem() {
		TreeItem<FarmComponent> selected = farmTreeView.getSelectionModel().getSelectedItem();
		if (selected != null && selected != rootItem) {
			selected.getParent().getChildren().remove(selected);
			farmTreeView.refresh();
			rebuildFarmMapFromTreeView();
		}
	}

	public void rebuildFarmMapFromTreeView() {
		farmMap.getChildren().clear();

		// Iterate through the TreeView items and add shapes to the farmMap
		for (TreeItem<FarmComponent> treeItem : rootItem.getChildren()) {
			createShapeOnFarmMap(treeItem.getValue());
			rebuildFarmMapRecursive(treeItem);
		}
	}

	private void rebuildFarmMapRecursive(TreeItem<FarmComponent> parentItem) {
		for (TreeItem<FarmComponent> treeItem : parentItem.getChildren()) {
			createShapeOnFarmMap(treeItem.getValue());
			rebuildFarmMapRecursive(treeItem);
		}
	}

}
