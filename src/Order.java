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
	private int userId;
	private boolean approved;
	
	public Order(int id) {
		this.menu = new ArrayList<Menu>();
		this.userId = id;
		this.approved = true;
		this.type = "Eat-in";
	}
	
	public Order() {}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		if (type.equalsIgnoreCase("takeaway") || type.equalsIgnoreCase("Eat-in")){
			this.approved = true;
		} else this.approved = approved;
	}

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void addToOrder(Menu item) {
		menu.add(item);
	}
	
	public String toString() {
		String items = "";
		for (Menu m: menu) {
			items = items + m;
		}
		return String.format("User Id: %d%n"
				+ "Order Type: %s%n" +
				"Approved: %b"
				+ "Items: %n[%s]%n%n", userId, type, approved,items);
	}
}
