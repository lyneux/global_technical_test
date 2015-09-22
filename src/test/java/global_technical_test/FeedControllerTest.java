package global_technical_test;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") 
public class FeedControllerTest {

	@Test
	public void canFetchAll() {
		when().
                get("/").
        then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("The Xfm Breakfast Show Podcast with Jon Holmes"));
	}

	@Test
	public void canFetchAlternate() {
		given().
       			param("alternate", "").
		when().
                get("/").
        then().
                statusCode(200);
	}

	@Test
	public void canFetchLatest() {
		given().
       			param("latest", "").
		when().
                get("/").
        then().
                statusCode(200);
	}

	@Test
	public void canFetchItem() {
		when().
                get("/:1").
        then().
                statusCode(200);
	}

}