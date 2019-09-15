package test.PLNPostpaidLogin;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class plnPostpaidLogin extends SerenityStory {
    @Steps
    plnPostpaidLoginStep step;

    @Given("User sudah login dengan <email> dan <password>")
    public void givenUserSudahLoginDenganemailDanpassword(String email, String password) {
        step.loginForGetToken(email, password);
    }

    @Given("User sudah di halaman pembayaran PLN Postpaid")
    public void givenUserSudahDiHalamanPembayaranPLNPostpaid() {
        step.getAnonimToken();
    }

    @Given("Sudah di halaman pembayaran PLN Postpaid")
    public void givenSudahDiHalamanPembayaranPLNPostpaid() {

    }

    @When("Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>")
    public void whenMasukkancustomerNumberDanproductIdUntukProsesInquiryDanMendapatrescodeDanpesan(String customerNumber, String productId, String rescode, String pesan) {
        step.inquiry(customerNumber, productId, rescode, pesan);
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>")
    public void whenMasukkancustomerNumberDanproductIdDantypeUntukProsesCartAddDanMendapatrescodeDanpesan(String customerNumber, String productId, String type, String rescode, String pesan) {
        step.addCart(customerNumber, productId, type, rescode, pesan);
    }

    @When("Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan>")
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescodeDanpesan(String pembayaran, String cekCC, String rescode, String pesan) {
        step.selectPayment(pembayaran, rescode, pesan);
        step.prosesPembayaran(pembayaran, cekCC, rescode, pesan);
    }

    @Then("Proses pembayaran tagihan PLN Postpaid terbayarkan dan mendapat <rescode> dan <pesan>")
    public void thenProsesPembayaranTagihanPLNPostpaidTerbayarkanDanMendapatrescodeDanpesan(String rescode, String pesan) {
        step.completePembayaran(rescode, pesan, "");
    }

    //SCENARIO 2

    @When("Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry dan mendapat <rescode> dan <pesan>")
    public void whenMasukkancustomerNumberDanproductIdinvalidUntukProsesInquiryDanMendapatrescodeDanpesan(String customerNumber, String productId, String rescode, String pesan) {
        step.inquiry(customerNumber, productId, rescode, pesan);
    }

    @Then("Inquiry gagal")
    public void thenInquiryGagal() {

    }

    //SCENARIO 3

    @When("Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add dan mendapat <rescode> dan <pesan>")
    public void whenMasukkancustomerNumberDanproductIdDantypeinvalidUntukProsesCartAddDanMendapatrescodeDanpesan(String customerNumber, String productId, String type, String rescode, String pesan) {
        step.addCart(customerNumber, productId, type, rescode, pesan);
    }

    @Then("Proses cart add gagal")
    public void thenProsesCartAddGagal() {

    }

    //SCENARIO 4

    @When("Masukkan metode <pembayaran> invalid dan mendapat <rescode> dan <pesan>")
    public void whenMasukkanMetodepembayaranInvalidDanMendapatrescodeDanpesan(String pembayaran, String rescode, String pesan) {
        step.selectPayment(pembayaran, rescode, pesan);
        step.prosesPembayaran(pembayaran, "sc", rescode, pesan);
    }

    @Then("Proses gagal")
    public void thenProsesGagal() {

    }


    //SCENARIO 5
    @When("Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode1> and <pesan1>")
    public void whenMasukkancustomerNumberDanproductIdUntukProsesInquiryDanMendapatrescode1Andpesan1(String customerNumber, String productId, String rescode1, String pesan1) {
        step.inquiry(customerNumber, productId, rescode1, pesan1);
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1>")
    public void whenMasukkancustomerNumberDanproductIdDantypeUntukProsesCartAddDanMendapatrescode1Andpesan1(String customerNumber, String productId, String type, String rescode1, String pesan1) {
        step.addCart(customerNumber, productId, type, rescode1, pesan1);
    }

    @When("Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1>")
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescodeAndpesan(String pembayaran, String cekCC, String rescode1, String pesan1) {
        step.selectPayment(pembayaran, rescode1, pesan1);
        step.prosesPembayaran(pembayaran, cekCC, rescode1, pesan1);
    }

    @When("Masukkan <orderId> invalid dan mendapat <rescode> dan <pesan>")
    public void whenMasukkanorderIdInvalidDanMendapatrescodeDanpesan(String orderId, String rescode, String pesan) {
        step.completePembayaran(rescode, pesan, orderId);
    }

    @Then("Proses complete select payment method gagal")
    public void thenProsesCompleteSelectPaymentMethodGagal() {

    }

}
