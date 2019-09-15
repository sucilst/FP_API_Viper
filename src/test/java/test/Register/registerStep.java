package test.Register;

import net.serenitybdd.rest.SerenityRest;
import org.jbehave.core.steps.Steps;
import utils.Endpoint;

import java.util.HashMap;
import java.util.Map;

public class registerStep extends Steps {

    Endpoint endpoint;

    public void registerUser(String phoneNumber, String fullName, String email, String password, String agent, String serial){
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("phone_number",phoneNumber);
        reqBody.put("full_name",fullName);
        reqBody.put("email",email);
        reqBody.put("password",password);
        reqBody.put("serial",serial);
        reqBody.put("agent",agent);

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endpoint.register);
    }

    public void validateRegisterUser(){
        SerenityRest
                .then()
                    .statusCode(200);

        String resCode = SerenityRest.then().extract().path("rescode");


    }
}
