package musical_instruments;

public class Demo {
	
	public static void main(String[] args) {
		
		Shop s = new Shop("PaunovShop",4000);
		s.receive("Yamaha", 3);
		s.printInstrumentByPrice();
		System.out.println();
		System.out.println();
		s.printInstrumentByName();
		
		
		
	}

}
