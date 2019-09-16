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
        System.out.println("Di Halaman Register");
    }

    @When("input <phoneNumber> yang valid dan belum terdaftar dan input <fullName> dan input <email> yang valid dan belum terdaftar dan input <password> dan input <serial> dan input <agent> lalu mendapat <rescode> dan <pesan>")
    public void whenInputphoneNumberYangValidDanBelumTerdaftarDanInputfullNameDanInputemailYangValidDanBelumTerdaftarDanInputpasswordDanInputserialDanInputagentLaluMendapatrescodeDanpesan(String phoneNumber, String fullName, String email, String password, String serial, String agent, String rescode, String pesan) {
        step.registerUser(phoneNumber, fullName, email, password, serial, agent, rescode, pesan);
    }

    @When("tunggu kode OTP terkirim setelah input <phoneNumber>, <serial> dan <agent> ke user lalu mendapat <rescode> dan <pesan>")
    public void whenTungguKodeOTPTerkirimSetelahInputphoneNumberserialDanagentKeUserLaluMendapatrescodeDanpesan(String phoneNumber, String serial, String agent, String rescode, String pesan) {
        step.requestOTP(phoneNumber, serial, agent, rescode, pesan);
    }

    @When("input kode OTP")
    public void whenInputKodeOTP() {

    }

    @Then("registrasi akun baru berhasil, serta mendapat <rescode> dan <pesan>")
    public void thenRegistrasiAkunBaruBerhasilSertaMendapatrescodeDanpesan() {

    }
}
