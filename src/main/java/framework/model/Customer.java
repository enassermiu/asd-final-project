package framework.model;

import java.util.List;

public abstract class Customer {

	private String name;
	private String email;

	private Address address;

	private List<Account> accounts;

	public Customer(String name, String email, Address address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public abstract String getCustomerTpeCode();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
