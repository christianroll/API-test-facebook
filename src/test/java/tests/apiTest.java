package tests;


import com.jayway.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;

public class apiTest {

    private static final String accessToken = "EAACEdEose0cBADEqX4i18m5t0FfugCPhZCUnE3crZBAe5hVrM4S56fdYrAQ9oTx6gGkVkLwoQoYZB69vQmO5nJmyxCwnkWXwMJxijiJLpsMoxBIgeZAuhRiuY3BBClyQ5IYV14w2hLUnWJ7ZB6lgPjQmt7IE1ZAuZBkOk72JZBsD172AtwAUkQUWJ9hIUXId3CEZD";
    String idPost;
    @Test
    public void postInUserTimeline(){
        String urlPost = "https://graph.facebook.com/me/feed/?access_token=" + accessToken;
        Response res = given().
                body("{\"message\":\"tardesinha\"}").
                when().
                contentType(ContentType.JSON).
                post(urlPost);
        System.out.println(res.getStatusCode());
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(),200);
        if(res.getStatusCode()==200){
            System.out.println("api is working fine");
        }
        else{
            System.out.println("api is not working fine");
        }
         this.idPost = res.
                then().
                contentType(ContentType.JSON).extract().path("id");
                System.out.println(this.idPost);
    }
    @Test
    public void UpdatePost(){
        System.out.println(this.idPost);
        String updatedUrl = "https://graph.facebook.com/" + this.idPost + "/?access_token="+ accessToken;
        Response res = given().
                body("{\"message\":\"noite\"}").
                when().
                contentType(ContentType.JSON).
                post(updatedUrl);
        System.out.println(res.getStatusCode());
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(),200);
        if(res.getStatusCode()==200){
            System.out.println("api is working fine");
        }
        else{
            System.out.println("api is not working fine");
        }
    }
}
