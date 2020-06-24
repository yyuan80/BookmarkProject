package derek.bookmark.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import derek.bookmark.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidFriendlyEligible() {
		//Test 1: porn in url -- false
		WebLink webLink = BookmarkManager.getInstance().createWebLink((long)2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/porn-tiger--part-2.html", "http://www.javaworld.com");
		boolean isKidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertFalse("For porn in url - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		//Test 2: porn in title -- false
		webLink = BookmarkManager.getInstance().createWebLink((long)2000, "Porn Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertFalse("For porn in title - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		//Test 3: adult in host -- false
		webLink = BookmarkManager.getInstance().createWebLink((long)2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adultworld.com");
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertFalse("For adult in host - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		//Test 4: adult in url, but not in host -- true
		webLink = BookmarkManager.getInstance().createWebLink((long)2000, "Taming Tiger, Part 2", "http://www.adultworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertTrue("For adult in url, but not in host - isKidFriendlyEligible() must return true", isKidFriendlyEligible);
		
		//Test 5: adult in title only -- true
		webLink = BookmarkManager.getInstance().createWebLink((long)2000, "Adult Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertTrue("For adult in title - isKidFriendlyEligible() must return true", isKidFriendlyEligible);
	}

}
