package test.Login;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class login extends SerenityStory {

    @Steps
    loginStep step;

    @Given("User sudah berada di halaman login")
    public void givenUserSudahBeradaDiHalamanLogin() {

    }

    @When("Mengisi field <username> dan <password> untuk login")
    public void whenMengisiFieldusernameDanpasswordUntukLogin(String username, String password) {
        step.loginUser(username, password);
    }

    @Then("Berhasil login dengan mendapat <rescode> dan <pesan>")
    public void thenBerhasilLoginDenganMendapatrescodeDanpesan(String rescode, String pesan) {
        step.validateLoginUser(rescode, pesan);
    }

    //SCENARIO 2
    @When("Mengisi field <username> dan <password> invalid untuk login")
    public void whenMengisiFieldusernameDanpasswordInvalidUntukLogin(String username, String password) {
        step.loginUser(username, password);
    }

    @Then("Gagal login dengan mendapat <rescode> dan <pesan>")
    public void thenGagalLoginDenganMendapatrescodeDanpesan(String rescode, String pesan) {
        step.validateLoginUser(rescode, pesan);
    }


}
