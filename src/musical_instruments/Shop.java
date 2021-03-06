package musical_instruments;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import musical_instruments.Instrument.InstrumentType;

public class Shop {

	private String name;
	private double money;
	private TreeMap<InstrumentType, HashMap<String, Instrument>> instruments;

	public Shop(String name, double money) {

		if (name != null && (!name.isEmpty()) && money > 500) {

			this.name = name;
			this.money = money;

		} else {
			System.out.println("Invalid name or money!");
		}

		instruments = new TreeMap<>();

		this.instruments.put(InstrumentType.STRING, new HashMap<>());
		this.instruments.get(InstrumentType.STRING).put("Harman", new Violin("Harmans", 850, 2));
		this.instruments.get(InstrumentType.STRING).put("Fender", new Guitar("Fenders", 800, 4));

		this.instruments.put(InstrumentType.PERCUSSION, new HashMap<>());
		this.instruments.get(InstrumentType.PERCUSSION).put("Yamaha", new Drums("Yamahite", 2000, 7));
		this.instruments.get(InstrumentType.PERCUSSION).put("Steinway", new Drums("Steinways", 1700, 4));

		this.instruments.put(InstrumentType.ELECTRICAL, new HashMap<>());
		this.instruments.get(InstrumentType.ELECTRICAL).put("Sennheiser",new ElectricBassGuitar("Sennheisers", 900, 5));
		this.instruments.get(InstrumentType.ELECTRICAL).put("Roland", new ElectricBassGuitar("Rolands", 1000, 6));

		this.instruments.put(InstrumentType.WIND, new HashMap<>());
		this.instruments.get(InstrumentType.WIND).put("Milan", new Clarinet("Milans", 500, 7));
		this.instruments.get(InstrumentType.WIND).put("Kawai", new Flute("Kawais", 300, 10));

		this.instruments.put(InstrumentType.KEYBOARD, new HashMap<>());
		this.instruments.get(InstrumentType.KEYBOARD).put("Gibson", new Piano("Gibsons", 2000, 3));
		this.instruments.get(InstrumentType.KEYBOARD).put("Shure", new Piano("Shures", 1500, 2));

	}

	public void sell(String name, int quantity) {

		modify(name, quantity, '-');

	}
	
	public void receive(String name, int quantity) {

		modify(name, quantity, '+');

	}


	private void modify(String name, int quantity, char sign) {

		boolean containsName = false;

		for (InstrumentType type : instruments.keySet()) {

			if (instruments.get(type).containsKey(name)) {

				if (sign == '-') {

					containsName = true;
					Instrument i = instruments.get(type).get(name);

					if (i.getQuantity() < quantity) {
						System.out.println("Not enough quantity! Only " + i.getQuantity() + " left!");
					} else {
						i.decreaseQuantity(quantity);
						System.out.println("SOLD !");
						this.money += quantity * i.getPrice();
					}

				} else if (sign == '+') {

					containsName = true;
					Instrument i = instruments.get(type).get(name);
					i.increaseQuantity(quantity);
					this.money -= (quantity * i.getPrice()) * 0.5;

					if (this.money > 0) {
						System.out.println("Added sucessfuly! Now " + i.getQuantity() + " quantity is available!");
					} else {
						i.decreaseQuantity(quantity);
						this.money = 0;
						System.out.println("The shop run out of money!");
					}

				}

			}

		}
		if (!containsName) {
			System.out.println("Wrong name or there is no such instrument in the shop");
		}

	}

	private void showInfo(Comparator<Instrument> comp) {

		for (InstrumentType type : instruments.keySet()) {

			System.out.println("==============================" + type + "================================");

			TreeSet<Instrument> sorted = new TreeSet<>(comp);
			sorted.addAll(instruments.get(type).values());

			for (Instrument i : sorted) {
				System.out.println(i.getClass().getSimpleName() + " " + i);
			}

		}

	}

	public void printInstrumentsByType() {

		for (InstrumentType type : instruments.keySet()) {

			System.out.println("==============================" + type + "================================");

			for (Instrument i : instruments.get(type).values()) {
				System.out.println(i.getClass().getSimpleName() + " " + i);
			}

		}

	}

	public void printInstrumentByName() {

		this.showInfo(new Comparator<Instrument>() {

			@Override
			public int compare(Instrument o1, Instrument o2) {

				if (o1.getName().equals(o2.getName())) {
					return Double.compare(o1.getPrice(), o2.getPrice());
				}

				return o1.getName().compareTo(o2.getName());

			}

		});

	}

	public void printInstrumentByPrice() {

		this.showInfo(new Comparator<Instrument>() {

			@Override
			public int compare(Instrument o1, Instrument o2) {

				if (o1.getPrice() == o2.getPrice()) {
					return o1.getName().compareTo(o2.getName());
				}

				return Double.compare(o1.getPrice(), o2.getPrice());

			}

		});

	}

	public void printInstrumentByQuantity() {

		this.showInfo(new Comparator<Instrument>() {

			@Override
			public int compare(Instrument o1, Instrument o2) {

				if (o1.getQuantity() == o2.getQuantity()) {
					return o1.getName().compareTo(o2.getName());
				}

				return o1.getQuantity() - o2.getQuantity();

			}

		});

	}

	public void printAvailableInstruments() {

		System.out.println("The available instruments by categories are : \n");

		for (InstrumentType type : instruments.keySet()) {

			System.out.println("================================" + type + "==================================");

			for (Instrument i : instruments.get(type).values()) {

				if (i.getQuantity() > 0) {
					System.out.println(i.getClass().getSimpleName() + " " + i);
				} else {
					System.out.println("The instrument " + i.getClass().getSimpleName() + "(" + i.getName() + ")"
							+ " is not available right now!");
				}

			}

		}

	}

	public double getMoney() {
		return money;
	}
}
