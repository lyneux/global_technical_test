package global_technical_test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;  

/**
 * A domain object representing an RSS feed.
 * @author guymolyneux
 */
@XmlRootElement
public class Feed implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
    private String link;
    private String description;
    private List<FeedItem> items;

    public Feed(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.items = new ArrayList<FeedItem>();
    }

    public Feed() {
        this.items = new ArrayList<FeedItem>();
    }

    public void addFeedItem(FeedItem item) {
        this.items.add(item);
    }

    @XmlElement
    public String getTitle() {
        return this.title;
    }
    
    @XmlElement
    public String getLink() {
    	return this.link;
    }
    
    @XmlElement
    public String getDescription() {
    	return this.description;
    }

    @XmlElement(name="item") 
    public List<FeedItem> getItems() {
        return this.items;
    }

}