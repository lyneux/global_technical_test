package global_technical_test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    private static final String feedUrl = "http://mediaweb.musicradio.com/RSSFeed.xml?Channel=9172";

	@RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Feed getFeed(@RequestParam(value="alternate", required=false) String alternate,
    								  @RequestParam(value="latest", required=false) String latest) {
		FeedService feedService = new FeedService(feedUrl);
		if (alternate != null) {
			return feedService.getAlternateItems();
		} else if (latest != null) {
			return feedService.getLatestItem();
		} else {
			return feedService.getFeed();	
		}
    }

    @RequestMapping(value="/:{id}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody FeedItem getFeedItem(@PathVariable(value="id") int id) {
		FeedService feedService = new FeedService(feedUrl);
        return feedService.getFeedItem(id);
    }

}