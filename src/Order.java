import java.util.ArrayList;
/**
 * The Order Class is the basic structure for the order object.
 * It contains attributes and methods to access the attributes.
 * It also has a toString method for data processing. it also has a menu list
 * that will hold all the items for any instance of the order object.
 */
public class Order {
	private String type;
	private ArrayList<Menu> menu;
	private String dailySpecial;
	private int custId;
	
	public Order(int id) {
		this.menu = new ArrayList<Menu>();
		this.custId = id;
	}
	
	public Order() {}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDailySpecial() {
		return dailySpecial;
	}

	public void setDailySpecial(String dailySpecial) {
		this.dailySpecial = dailySpecial;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public void addToOrder(Menu item) {
		menu.add(item);
	}
	
	public String toString() {
		String items = "";
		for (Menu m: menu) {
			items = items + m;
		}
		return String.format("Customer Id: %d%n"
				+ "Order Type: %s%n"
				+ "Items: %n[%s]%n%n", custId, type, items);
	}
}
