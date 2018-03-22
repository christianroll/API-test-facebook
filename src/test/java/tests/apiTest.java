package tests;


import com.jayway.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;




public class apiTest {

    private static final String accessToken = "EAACEdEose0cBADG5UCvALZB18kTDTNHNMLIaUgLJjjQnyktoafJcUsqxrBT1XTHQdPsi3F8ZB3uGN8CZAAM9Rq6v9y0f9biVCo9CiAjywCZAFVF41BvyL5bjHRkkZBnXpTcI8sRlV2eTZBThrqVC5vZBHlVjZA1XPR9fefRFAG5yW7WQ7jyqCSImJiSd0Szi2VIZD";


    @Test
    public void postAndUpdate(){
        String urlPost = "https://graph.facebook.com/me/feed/?access_token=" + accessToken;

        Response res = given().

                body("{\"message\":\"alopa\"}").
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

         String idPost = res.
                then().
                contentType(ContentType.JSON).extract().path("id");
                System.out.println(idPost);


        String updatedUrl = "https://graph.facebook.com/" + idPost + "/?access_token="+ accessToken;
        Response res2 = given().

                body("{\"message\":\"noite\"}").
                when().
                contentType(ContentType.JSON).
                post(updatedUrl);
        System.out.println(res2.getStatusCode());
        System.out.println(res2.asString());
        Assert.assertEquals(res2.getStatusCode(),200);
        if(res.getStatusCode()==200){
            System.out.println("api is working fine");
        }
        else{
            System.out.println("api is not working fine");
        }




    }


}
