package musical_instruments;

public abstract class Instrument {
	
	public enum InstrumentType{
		
		STRING,PERCUSSION,WIND,KEYBOARD,ELECTRICAL;
		
	}
	
	private String name;
	private double price;
	private int quantity;
	protected InstrumentType type;
	
	public Instrument(String name, double price, int quantity, InstrumentType type) {
		
		if(name != null && !(name.isEmpty())){
			this.name = name;
		}
		
		if(price > 0) {
			this.price = price;
		}
		
		if(quantity > -1) {
			this.quantity = quantity;
		}
		
		this.type = type;
		
	}

	@Override
	public String toString() {
		return "Instrument [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	public void decreaseQuantity(int q) {
		this.quantity-=q;
	}
	
	public void increaseQuantity(int q) {
		this.quantity+=q;
	}
	

	String getName() {
		return name;
	}

	int getQuantity() {
		return quantity;
	}

	double getPrice() {
		return price;
	}

	
}
