package stepdefs;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.testng.Assert;
import tests.ResponseHolder;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class StepDefinitions {

    ResponseHolder responseHolder;
    Response response;
    String url;
    String token;
    String idPost;
    Map<String, String> body;

    @Given("^User needs a token$")
    public void user_needs_a_token() throws Throwable {

        this.token = "EAACEdEose0cBAIO5GKzfUqUrxEHzDbZC28Sb9pioGk9ZCTRgBOyg08HAvzaG1oAVFBdcva5tZAiiCmT6U3mPc6r5fcPVoIN3sp26jK2X3OqUMySkWjSkQpfiI7CivN78JsgATHi4ClkX4gZB8Si1mU0nIeoXE5sKZC7VHjqGn5RsxLs4TcHG4e0jbuPLZCfp0ZD";


    }

    @When("^User wants a request to \"([^\"]*)\"$")
    public void user_wants_a_request_to(String url) throws Throwable {

        this.url = url + "/?access_token=" + token;

    }

    @When("^User performs a get request$")
    public void user_performs_a_get_request() throws Throwable {

        response = given().
                when().
                get(this.url);


        ResponseHolder.setResponse(response);

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());


    }

    @Then("^The response code should be (\\d+)$")
    public void the_response_code_should_be(int responseCode) throws Throwable {
        Assert.assertEquals(responseCode,ResponseHolder.getResponseCode());
        if(responseHolder.getResponseCode()==200){
            System.out.println("api is working fine");
        }
        else{
            System.out.println("api is not working fine");
        }
    }


    @And("^User performs a post request$")
    public void userPerformsAPostRequest() throws Throwable {
        response = given().
                body(this.body).
                when().
                contentType(ContentType.JSON).
                post(this.url);

        ResponseHolder.setResponse(response);

        System.out.println(ResponseHolder.getResponseCode());
        System.out.println(ResponseHolder.getResponseBody());

    }

    @And("^Passing the body message$")
    public void passingTheBodyMessage(DataTable dataTable) throws Throwable {
        this.body = new LinkedHashMap<String, String>();
        for (DataTableRow row : dataTable.getGherkinRows()) {
            this.body.put(row.getCells().get(0),row.getCells().get(1));


        }
    }

    @Then("^Saves the id from post response$")
    public void savesTheIdFromPostResponse() throws Throwable {
        this.idPost = response.
                then().
                contentType(ContentType.JSON).extract().path("id");
                System.out.println(this.idPost);
    }


    @When("^User wants a request to \"([^\"]*)\" for update previous post$")
    public void userWantsARequestToForUpdatePreviousPost(String url) throws Throwable {
        this.url = url + this.idPost + "/?access_token=" + token;

    }
}
