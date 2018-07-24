package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<Media> mediaList = new ArrayList<Media>();
	int limitedPlan = DEFAULT_CAP;
	static final int DEFAULT_CAP = 2;

	public void addCustomer(String name, String address, String plan) {
		customerList.add(new Customer(name, address, plan));
	}

	public void addMovie(String title, int copiesAvailable, String rating) {
		mediaList.add(new Movies(title, copiesAvailable, rating));
	}

	public void addAlbum(String title, int copiesAvailable, String artist, 
			String songs) {
		mediaList.add(new Albums(title, copiesAvailable, artist, songs));
	}

	public void setLimitedPlanLimit(int value) {
		if (value > 0) {
			limitedPlan = value;
		}
	}

	public String getAllCustomersInfo() {
		Collections.sort(customerList);
		StringBuffer customerInfo = new StringBuffer("***** Customers' " + 
		"Information *****\n");
		for (int i = 0; i < customerList.size(); i++) {
			customerInfo.append(customerList.get(i) + "\n");
		}
		return customerInfo.toString();
	}

	public String getAllMediaInfo() {
		Collections.sort(mediaList);
		StringBuffer mediaInfo = new StringBuffer("***** Media " + 
		"Information *****\n");
		for (int i = 0; i < mediaList.size(); i++) {
			mediaInfo.append(mediaList.get(i) + "\n");
		}
		return mediaInfo.toString();
	}

	public boolean addToQueue(String customerName, String mediaTitle) {
		int count = 0;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(customerName)) {
				for (int j = 0; j < mediaList.size(); j++) {
					if (mediaList.get(j).getTitle().equals(mediaTitle)
							&& mediaCheck(customerList.get(i).queue,mediaTitle)
							==false) {
						count++;
						customerList.get(i).queue.add(mediaList.get(j));
					}
				}
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean removeFromQueue(String customerName, String mediaTitle) {
		int count = 0;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(customerName)) {
				for (int j = 0; j < customerList.get(i).queue.size(); j++) {
					if (customerList.get(i).queue.get(j).getTitle().equals
							(mediaTitle)) {
						customerList.get(i).queue.remove(mediaList.get(j));
						j--;
						count++;
					}
				}
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String processRequests() {
		Collections.sort(customerList);
		Collections.sort(mediaList);
		String renting = new String("");
		for (int i = 0; i < customerList.size(); i++) {
			for (int j = 0; j < customerList.get(i).queue.size(); j++) {
				if ((customerList.get(i).getPlan().equals("UNLIMITED")
						|| (customerList.get(i).rented.size() < 
								this.limitedPlan))
						&& customerList.get(i).queue.get(j).getCopiesAvailable()
						> 0) {
					renting = renting + "Sending " + customerList.get(i).queue.
							get(j).getTitle() + " to "+ customerList.get(i).
							getName() + "\n";
					customerList.get(i).rented.add(customerList.get(i).queue.
							get(j));
					int numberCopies = customerList.get(i).queue.get(j).
							getCopiesAvailable();
					customerList.get(i).queue.get(j).setCopiesAvailable
					(numberCopies - 1);
					customerList.get(i).queue.remove(j);
					--j;

				}
			}
		}
		return renting;
	}

	public boolean returnMedia(String customerName, String mediaTitle) {
		int count = 0;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(customerName)) {
				for (int j = 0; j < customerList.get(i).rented.size(); j++) {
					if (customerList.get(i).rented.get(j).getTitle().equals
							(mediaTitle)) {
						int numberCopies = customerList.get(i).rented.get(j).
								getCopiesAvailable();
						customerList.get(i).rented.get(j).setCopiesAvailable
						(numberCopies + 1);
						customerList.get(i).rented.remove(j);
						j--;
						count++;
					}
				}
			}
		}
		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<String> searchMedia(String title, String rating, 
			String artist, String songs) {
		ArrayList<String> AllMedia = new ArrayList<String>();
		if (title == null && rating == null && artist == null && songs == null){
			for (int i = 0; i < mediaList.size(); i++) {
				AllMedia.add(mediaList.get(i).getTitle());
			}

		} else if (title != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getTitle().equals(title)) {
					AllMedia.add(mediaList.get(i).getTitle());

				}
			}
		} else if (rating != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getClass() == Movies.class) {
					if (((Movies) mediaList.get(i)).getRating().equals
							(rating)) {
						AllMedia.add(mediaList.get(i).getTitle());
					}
				}
			}
		} else if (artist != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getClass() == Albums.class) {
					if (((Albums) mediaList.get(i)).getArtist().equals(artist)){
						AllMedia.add(mediaList.get(i).getTitle());
					}
				}
			}
		} else {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getClass() == Albums.class) {

					if (((Albums) mediaList.get(i)).getSongs().contains(songs)){
						AllMedia.add(mediaList.get(i).getTitle());
					}
				}
			}
		}
		Collections.sort(AllMedia);
		return AllMedia;
	}
	
	private boolean mediaCheck(ArrayList<Media> queue , String mediaTitle){
		// method to check media already in the queue
		for(int i=0;i<queue.size();i++){
			if(queue.get(i).getTitle().equals(mediaTitle)){
				return true;
			}
		}
		return false;
	} 
}
