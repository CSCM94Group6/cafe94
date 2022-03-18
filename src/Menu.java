/**
 * The Menu Class is the basic structure for the menu object.
 * each food item is considered a menu item.
 * It contains attributes and methods to access the attributes.
 * It also has a toString method for data processing
 */
public class Menu {
	private String item;
	private String description;
	private double price;
	private int id;
	
	public Menu() {}
	public Menu(String item, double price, int id) {
		this.item = item;
		this.price = price;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return String.format("%S%n%s%n%.2f%n%n", item, description, price);
	}
}
