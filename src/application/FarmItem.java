package application;

public class FarmItem implements FarmComponent{

	String name;
	float price;
	int xCoord;
	int yCoord;
	int length;
	int width;
	int height;
	
	public FarmItem(String name, float price, int xCoord, int yCoord, int length, int width, int height) {
		this.name = name;
		this.price = price;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeName(String newName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeLocation(int newX, int newY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePrice(float newPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDimensions(int length, int width) {
		// TODO Auto-generated method stub
		
	}

}
