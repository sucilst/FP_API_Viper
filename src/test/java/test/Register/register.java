package test.Register;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class register extends SerenityStory {

    @Steps
    registerStep step;

    @Given("user sudah di halaman register")
    public void givenUserSudahDiHalamanRegister() {
        System.out.println("Di Halaman Login");
    }

    @When("input <phoneNumber> yang valid dan belum terdaftar dan input <fullName> dan input <email> yang valid dan belum terdaftar dan input <password> dan input <serial> dan input <agent>")
    public void whenInputphoneNumberYangValidDanBelumTerdaftarDanInputfullNameDanInputemailYangValidDanBelumTerdaftarDanInputpasswordDanInputserialDanInputagent(String phoneNumber, String fullName, String email, String password, String serial, String agent) {
        step.registerUser(phoneNumber, fullName, email, password, serial, agent);
    }

    @Then("mendapat status code 200, response code 00 dan response body data access token untuk proses request OTP")
    public void thenMendapatStatusCode200ResponseCode00DanResponseBodyDataAccessTokenUntukProsesRequestOTP() {
       step.validateRegisterUser();
    }
}
