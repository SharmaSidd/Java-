package mediaRentalManager;

import java.util.*;
/**
 * Class that defines the functionality for customers.Customer has a name
 * address,plan. Customer has 2 array lists-rented and queue.
 * @author raghavgupta
 */

public class Customer implements Comparable<Customer> 
{
	String name, address, plan;
	ArrayList<Media> queue = new ArrayList<Media>();
	ArrayList<Media> rented = new ArrayList<Media>();
	
	
	/**
	 * Constructor to initialize the name, address and plan
	 * @param name
	 * @param address
	 * @param plan
	 */
	public Customer(String name, String address, String plan) 
	{
		this.name = name;
		this.address = address;
		this.plan = plan;
	}
	
	
	/**
	 * copy constructor 
	 * @param customer
	 */
	public Customer(Customer customer) 
	{
		this(customer.getName(), customer.getAddress(), customer.getPlan());
	}

	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer customer = (Customer) obj;
		return name.equals(customer.name) && address.equals(customer.address)&&
			   plan.equals(customer.plan);

	}
	
	
/**
 * Returns the name,plan,address with rented & queued media for the customer
 * in the form of a string <br>
 */
	public String toString() 
	{
		StringBuffer queuedMediaList = new StringBuffer("");
		StringBuffer rentedMediaList = new StringBuffer("");
		for (int i = 0; i < this.rented.size(); i++) 
		{
			if (i == (this.rented.size() - 1)) 
			{
				rentedMediaList.append(this.rented.get(i).getTitle());
			} else {
				rentedMediaList.append(this.rented.get(i).getTitle() + ", ");
			}
		}
		for (int i = 0; i < this.queue.size(); i++) 
		{
			if (i == (this.queue.size() - 1)) 
			{
				queuedMediaList.append(this.queue.get(i).getTitle());
			} else 
			{
				queuedMediaList.append(this.queue.get(i).getTitle() + ", ");
			}
		}
		return "Name: " + name + " , Address: " + address + ", Plan: " + plan +
				"\n" + "Rented: ["+ rentedMediaList.toString() + "]" + 
		        "\nQueue: [" + queuedMediaList.toString() + "]";
	}
	
/**
 * returns customer's name <br>
 * @return
 */
	public String getName() 
	{
		return name;
	}
	
	
	/**
	 * returns customer's address <br>
	 * @return
	 */
	public String getAddress() 
	{
		return address;
	}
	
	
	/**
	 * returns customer's plan <br>
	 * @return
	 */
	public String getPlan() 
	{
		return plan;
	}
	
	
	/**
	 * Compare to method to return 0 if they have different names, and 1 if the
	 * names are same.
	 * @param customer
	 */
	public int compareTo(Customer customer) 
	{
		return this.getName().compareTo(customer.getName());

	}

}
