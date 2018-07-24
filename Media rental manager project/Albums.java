package mediaRentalManager;

public class Albums extends Media 
{
	String artist, songs;

	
	/**
	 * parameterized constructor to initialize the title, copies available and
	 * rating of a movie.
	 * @param title
	 * @param CopiesAvailable
	 * @param rating
	 */
	public Albums(String title, int CopiesAvailable, String artist, 
		     String songs) 
	{
		super(title, CopiesAvailable);
		this.artist = artist;
		this.songs = songs;
	}

	
	/**
	 * return the artist of the album
	 * @return
	 */
	public String getArtist() 
	{
		return artist;
	}

	
	/**
	 * Copy constructor.<br>
	 * @param album
	 */
	public Albums(Albums album) 
	{
		this(album.getTitle(), album.getCopiesAvailable(), album.getArtist(), 
			   album.getSongs());
	}
	
	
	/**
	 * return the song of the album
	 * @return
	 */
	public String getSongs() 
	{
		return songs;
	}

	
	/**
	 * equals method to return true if 2 album class instance variables are
	 * equal, i.e, title, copies available,songs and artist are equal, else 
	 * return false.
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
		Albums albums = (Albums) obj;
		return super.getTitle().equals(albums.getTitle()) && 
				getCopiesAvailable() == (albums.getCopiesAvailable())
				&& artist.equals(albums.getArtist()) && songs.equals
				(albums.getSongs());
	}

	
	/**
	 * Returns the Title, copies available,artist and song of album class  
	 * variable, in String form. <br>
	 */
	public String toString() {
		return "Title: " + getTitle() + ", Copies Available: " + 
	            getCopiesAvailable() + ", Artist: " + getArtist() + ", Songs: " 
				+ getSongs();

	}
}
