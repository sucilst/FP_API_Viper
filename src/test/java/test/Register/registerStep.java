package test.Register;

import net.serenitybdd.rest.SerenityRest;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import utils.Endpoint;

import java.util.HashMap;
import java.util.Map;

public class registerStep extends Steps {

    Endpoint endpoint;

    String accessToken;

    public void registerUser(String phoneNumber, String fullName, String email, String password, String agent, String serial, String rescode, String pesan){
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("phone_number",phoneNumber);
        reqBody.put("full_name",fullName);
        reqBody.put("email",email);
        reqBody.put("password",password);
        reqBody.put("serial",serial);
        reqBody.put("agent",agent);
        reqBody.put("referral_code", "");

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endpoint.register);

        SerenityRest
                .then()
                .statusCode(200);

        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        Assert.assertTrue(resCodeActual.equals(rescode));
        Assert.assertTrue(pesanActual.equals(pesan));

        accessToken = SerenityRest.then().extract().path("data.access_token");
        System.out.println(accessToken);
    }

    public void requestOTP(String phoneNumber, String serial, String agent, String rescode, String pesan){
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("phone_number",phoneNumber);
        reqBody.put("serial",serial);
        reqBody.put("agent",agent);

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .header("Authorization", "Bearer " + accessToken)
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endpoint.reqOTP);

        SerenityRest
                .then()
                .statusCode(200);

        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        Assert.assertTrue(resCodeActual.equals(rescode));
        Assert.assertTrue(pesanActual.equals(pesan));

        accessToken = SerenityRest.then().extract().path("data.access_token");
    }


}
