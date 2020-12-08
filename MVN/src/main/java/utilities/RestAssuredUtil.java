package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings({"rawtypes","unchecked"}) @Log4j
public class RestAssuredUtil {




    private static Header setAuthorizationHeader(String authKey) {

        return new Header("Authorization",authKey);
    }

    public static Response getRequest(String strEndpoint, String authKey) {

        LoggerUtil.logTrace("Hitting GET request for endpoint:"+strEndpoint,log);
        Header authorizationHeader = setAuthorizationHeader(authKey);
        Response response = given()
                .header(authorizationHeader)
                .when()
                .get(strEndpoint);
        return response; //returns response
    }

    public static Response postRequest(ContentType contentType, String strEndpoint, String payloadBody) {

        Response response = given()
                .accept(contentType)
                .contentType(contentType)
                .body(payloadBody)
                .when()
                .post(strEndpoint);
        return response;
    }



}
