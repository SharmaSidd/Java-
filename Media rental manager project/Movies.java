package mediaRentalManager;

public class Movies extends Media 
{
	String rating;

	
	/**
	 * parameterized constructor to initialize the title, copies available and
	 * rating of a movie.
	 * @param title
	 * @param CopiesAvailable
	 * @param rating
	 */
	public Movies(String title, int CopiesAvailable, String rating) 
	{
		super(title, CopiesAvailable);
		this.rating = rating;
	}

	/**
	 * copy constructor
	 * @param movies
	 */
	public Movies(Movies movies) 
	{
		this(movies.getTitle(), movies.getCopiesAvailable(), 
				movies.getRating());
	}

	/**
	 * return the rating of a movie
	 * @return
	 */
	public String getRating() 
	{
		return rating;
	}
	
	/**
	 * equals method to return true if 2 movie class instance variables are
	 * equal, i.e, title, copies available and rating are equal, else return 
	 * false.
	 * @param obj
	 */
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movies movies = (Movies) obj;
		return super.getTitle().equals(movies.getTitle()) && 
			   getCopiesAvailable() == (movies.getCopiesAvailable())
			   && rating.equals(movies.getRating());
	}

	/**
	 * return the title, rating and copies available of a movie in string form
	 */
	public String toString() 
	{
		return "Title: " + getTitle() + ", Copies Available: " + 
	            getCopiesAvailable() + ", Rating: " + getRating();

	}
}
