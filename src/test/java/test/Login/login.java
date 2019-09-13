package test.Login;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class login extends SerenityStory {

    @Steps
    loginStep step;

    @Given("User sudah berada di halaman awal")
    public void givenUserSudahBeradaDiHalamanAwal() {

    }

    @When("Mengisi field <username> dan <password> untuk login")
    public void whenMengisiFieldusernameDanpasswordUntukLogin(String username, String password) {
        step.loginUser(username, password);
    }

    @Then("Berhasil masuk ke website sepulsa")
    public void thenBerhasilMasukKeWebsiteSepulsa() {
        step.validateLoginUser();
    }

}
