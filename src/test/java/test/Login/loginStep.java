package test.Login;

import net.serenitybdd.rest.SerenityRest;
import org.jbehave.core.steps.Steps;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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

    public void validateLoginUser(){
        String resCode = SerenityRest.then().extract().path("rescode");

        if (resCode.equals("00")) {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/Login/loginSukses.json"));

            //Assert.assertTrue(resCode.equals("00"));
        }
        else {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/Login/loginGagal.json"));

            //Assert.assertTrue(resCode.equals("24")||resCode.equals("86"));
        }
    }

}

