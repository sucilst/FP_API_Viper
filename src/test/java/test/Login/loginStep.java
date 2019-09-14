package test.Login;

import net.serenitybdd.rest.SerenityRest;
import org.jbehave.core.steps.Steps;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.Assert;
import utils.Endpoint;

public class loginStep extends Steps {

    Endpoint endPoint;

    public void loginUser(String username, String password){
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("email_or_phone_number",username);
        reqBody.put("password",password);

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.login);
        }

    public void validateLoginUser(String rescode, String pesan){
        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        if (resCodeActual.equals("00")) {
            SerenityRest
                    .then()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("JSONSchema/Login/loginSukses.json"));
        }
        else {
            SerenityRest
                    .then()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("JSONSchema/Login/loginGagal.json"));

        }

        Assert.assertTrue(resCodeActual.equals(rescode));
        Assert.assertTrue(pesanActual.equals(pesan));
    }

}

