package derek.bookmark;

import derek.bookmark.constants.BookGenre;
import derek.bookmark.constants.Gender;
import derek.bookmark.constants.MovieGenre;
import derek.bookmark.constants.UserType;
import derek.bookmark.entities.Bookmark;
import derek.bookmark.entities.User;
import derek.bookmark.entities.UserBookmark;
import derek.bookmark.managers.BookmarkManager;
import derek.bookmark.managers.UserManager;
import derek.bookmark.util.IOUtil;

public class DataStore {
	public static final int USER_BOOKMARK_LIMIT = 5;
	public static final int BOOKMARK_COUNT_PER_TYPE = 5;
	public static final int BOOKMARK_TYPES_COUNT = 3;
	public static final int TOTAL_USER_COUNT = 5;

	private static User[] users = new User[TOTAL_USER_COUNT];
	public static User[] getUsers() {
		return users;
	}

	private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPES_COUNT][BOOKMARK_COUNT_PER_TYPE];
	public static Bookmark[][] getBookmarks() {
		return bookmarks;
	}

	private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];
	private static int bookmarkIndex;
	
	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {
		String[] data = new String[TOTAL_USER_COUNT];
    	IOUtil.read(data, "User");
    	int rowNum = 0;
    	for (String row : data) {
    		String[] values = row.split("\t");
    		
    		int gender = Gender.MALE;
    		if (values[5].equals("f")) {
    			gender = Gender.FEMALE;
    		} else if (values[5].equals("t")) {
    			gender = Gender.TRANSGENDER;
    		}
    			
    		users[rowNum++] = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
    	}	
		
	}
	
	private static void loadWebLinks() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
    	IOUtil.read(data, "WebLink");
    	int colNum = 0;
    	for (String row : data) {
    		String[] values = row.split("\t");
    		bookmarks[0][colNum++] = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
    	}
	}
	
	private static void loadMovies() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
    	IOUtil.read(data, "Movie");
    	int colNum = 0;
    	for (String row : data) {
    		String[] values = row.split("\t");
    		String[] cast = values[3].split(",");
    		String[] directors = values[4].split(",");
    		bookmarks[1][colNum++] = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
    	}
	}
	
	private static void loadBooks() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
    	IOUtil.read(data, "Book");
    	int colNum = 0;
    	for (String row : data) {
    		String[] values = row.split("\t");
    		String[] authors = values[4].split(",");
    		bookmarks[2][colNum++] = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
    	}
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks[bookmarkIndex] = userBookmark;
		bookmarkIndex++;
		
	}
	
	
}
