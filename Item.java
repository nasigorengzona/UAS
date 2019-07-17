public class Item {
	private String name;
	private double weight;
	private double price;
	public Item(String name, double weight, double price) {
		this.name = name;
		this.weight = weight;
		this.price = price;
	}
	
	public String getName () { return this.name; }
	public double getWeight () { return this.weight; }
	public double getPrice () { return this.price; }

	public String toString() {
		return String.format("Item<%s:%.2f:%.2f>", this.getName(), this.getWeight(), this.getPrice());
	}
}
