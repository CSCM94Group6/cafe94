/**
 * The Booking Class is the basic structure for the booking object.
 * It contains attributes and methods to access the attributes.
 * It also has a toString method for data processing
 */
public class Booking {
	private int numOfGuests;
	private int custId;
	private String date;
	private String time;
	private boolean approved;
	
	public Booking(int guests, String date, String time) {
		this.numOfGuests = guests;
		this.date = date;
		this.time = time;
		this.approved = false;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getNumOfGuests() {
		return numOfGuests;
	}

	public void setNumOfGuests(int numOfGuests) {
		this.numOfGuests = numOfGuests;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String toString() {
		return String.format("Customer Id: %d%n"
						+ "Number of guests: %d%n"
						+ "Date: %s%n"
						+ "Time: %s:00hrs%n", custId, numOfGuests, date, time);
	}
	
	
}
