package global_technical_test;

import java.net.URL;
import java.util.List;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Data service that uses the Rome API to retrieve items from an RSS feed and presents
 * them as domain objects to be used in the Rest Service
 * @author guymolyneux
 */
public class FeedService {

	private SyndFeed feed;

	public FeedService(String url) {
		try {
    		SyndFeedInput input = new SyndFeedInput();
			feed = input.build(new XmlReader(new URL(url)));
		} catch (Exception e) {
			feed = null;
			// TODO: Add Proper Logging
			System.out.println(e);
		}
	}

	/**
	 * Gets the entire feed
	 * @return The entire feed containing all items
	 */
	public Feed getFeed() {
		Feed feed = new Feed(this.feed.getTitle(), this.feed.getLink(), this.feed.getDescription());
		if (this.feed != null) {
			List<SyndEntry> entries = this.feed.getEntries();
			for (SyndEntry entry : entries) {
				String enclosureURL = determineEnclosureURL(entry);
				FeedItem item = new FeedItem(entry.getAuthor(), entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate(), enclosureURL);
				feed.addFeedItem(item);
			}
		}
		return feed;
	}

	/**
	 * Gets a specific item from a feed by id
	 * @param id The id of the item to return from the feed
	 * @return The item or null if not found
	 */
	public FeedItem getFeedItem(int id) {
		FeedItem feedItem = null;
		if (this.feed != null && id <= this.feed.getEntries().size()) {
			List<SyndEntry> entries = this.feed.getEntries();
			SyndEntry entry = (SyndEntry)entries.get(entries.size() - id);
			String enclosureURL = determineEnclosureURL(entry);
			feedItem = new FeedItem(entry.getAuthor(), entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate(), enclosureURL);
		}
		return feedItem;
	}

	/**
	 * Gets even-numbered items from a feed
	 * @return A feed containing alternate items
	 */
	public Feed getAlternateItems() {
		Feed feed = new Feed(this.feed.getTitle(), this.feed.getLink(), this.feed.getDescription());
		if (this.feed != null) {
			List<SyndEntry> entries = this.feed.getEntries();
			for (int i = 0; i < entries.size(); i += 2) {
				SyndEntry entry = entries.get(i);
				String enclosureURL = determineEnclosureURL(entry);
				FeedItem item = new FeedItem(entry.getAuthor(), entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate(), enclosureURL);
				feed.addFeedItem(item);
			}
		}
		return feed;
	}

	/**
	 * Gets the latest item from a feed
	 * @return A feed containing the latest item
	 */
	public Feed getLatestItem() {
		Feed feed = new Feed(this.feed.getTitle(), this.feed.getLink(), this.feed.getDescription());
		if (this.feed != null && this.feed.getEntries() != null) {
			SyndEntry entry = (SyndEntry)this.feed.getEntries().iterator().next();
			String enclosureURL = determineEnclosureURL(entry);
			FeedItem item = new FeedItem(entry.getAuthor(), entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate(), enclosureURL);
			feed.addFeedItem(item);
		}
		return feed;
	}

	/**
	 * Determines the enclosure URL by inspecting the first enclosure found 
	 * @param entry The entry containing the enclosures
	 * @return The first enclosure URL found or an empty string if none found
	 */
	private String determineEnclosureURL(SyndEntry entry) {
		String url = "";
		if (entry.getEnclosures() != null && !entry.getEnclosures().isEmpty()) {
			SyndEnclosure enclosure = (SyndEnclosure)entry.getEnclosures().iterator().next();
			url = enclosure.getUrl();
		}
		return url;
	}

}