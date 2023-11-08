package application;

public interface FarmComponent {
	void changeName(String newName);
	void changeXCoord(int newX);
	void changeYCoord(int newY);
	void changePrice(float newPrice);
	void changeLength(int length);
	void changeWidth(int width);
	void changeHeight(int height);

	String getName();
	int getXCoord();
	int getYCoord();
	float getPrice();
	int getLength();
	int getWidth();
	int getHeight();
	
}
