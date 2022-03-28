/**
 * The Menu Class is the basic structure for the menu object. Each menu item is a food item that has a name,
 * description, price and id.
 * @author Liz Jones
 * @version 1.0
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
