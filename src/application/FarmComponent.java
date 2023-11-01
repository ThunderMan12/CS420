package application;

public interface FarmComponent {
	void delete();
	void changeName(String newName);
	void changeLocation(int newX, int newY);
	void changePrice(float newPrice);
	void changeDimensions(int length, int width);
}
