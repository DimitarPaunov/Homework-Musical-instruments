package musical_instruments;

public class Demo {
	
	public static void main(String[] args) {
		
		Shop dp = new Shop("PaunovShop",4000);
		dp.receive("Fender", 3);
		System.out.println();
		System.out.println();
		dp.sell("Harman", 2);
		dp.printInstrumentByName();
		System.out.println();
		dp.printAvailableInstruments();
		
		
		
	}

}
