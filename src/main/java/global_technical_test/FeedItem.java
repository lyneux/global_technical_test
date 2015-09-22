package global_technical_test;

import java.io.Serializable; 
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A domain object representing an RSS feed item 
 * @author guymolyneux
 *
 */
@XmlRootElement
public class FeedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String author;
    private String title;
    private String description;
    private Date publicationDate;
    private String enclosureURL;

    public FeedItem(String author, String title, String description, Date publicationDate, String enclosureURL) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.enclosureURL = enclosureURL;
    }

    public FeedItem() {
    }

    @XmlElement
    public String getAuthor() {
        return this.author;
    }

    @XmlElement
    public String getTitle() {
        return this.title;
    }

    @XmlElement
    public String getDescription() {
        return this.description;
    }

    @XmlElement
    public Date getPublicationDate() {
        return this.publicationDate;
    }

    @XmlElement
    public String getEnclosureURL() {
        return this.enclosureURL;
    }

}