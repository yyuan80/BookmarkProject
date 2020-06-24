package derek.bookmark.dao;

import derek.bookmark.DataStore;
import derek.bookmark.entities.Bookmark;
import derek.bookmark.entities.UserBookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}
}
