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
	public void changeName(String newName) {
		// TODO Auto-generated method stub
		this.name = newName;
	}

	@Override
	public void changeXCoord(int newX) {
		// TODO Auto-generated method stub
		this.xCoord = newX;
	}

	@Override
	public void changeYCoord(int newY) {
		// TODO Auto-generated method stub
		this.yCoord = newY;
	}

	@Override
	public void changePrice(float newPrice) {
		// TODO Auto-generated method stub
		this.price = newPrice;
	}

	@Override
	public void changeLength(int length) {
		// TODO Auto-generated method stub
		this.length = length;
	}

	@Override
	public void changeWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
	}

	@Override
	public void changeHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getXCoord() {
		// TODO Auto-generated method stub
		return this.xCoord;
	}

	@Override
	public int getYCoord() {
		// TODO Auto-generated method stub
		return this.yCoord;
	}

	@Override
	public float getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public int getLength() {	
		// TODO Auto-generated method stub
		return this.length;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
}
