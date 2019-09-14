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
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescodeDanpesan(String pembayaran, String cekCC, String rescode, String pesan ) {
        step.selectPayment(pembayaran, rescode, pesan);
        step.prosesPembayaran(pembayaran, cekCC, rescode, pesan);
    }

    @Then("Proses pembayaran tagihan PLN Postpaid terbayarkan dan mendapat <rescode> dan <pesan>")
    public void thenProsesPembayaranTagihanPLNPostpaidTerbayarkanDanMendapatrescodeDanpesan(String rescode, String pesan) {
        step.completePembayaran(rescode, pesan);
    }

    //SCENARIO 2

    @When("Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry")
    public void whenMasukkancustomerNumberDanproductIdinvalidUntukProsesInquiry(String customerNumber, String productId) {
        step.inquiryGagal(customerNumber, productId);
    }

    @Then("Inquiry gagal dan mendapat <rescode> dan <pesan>")
    public void thenInquiryGagalDanMendapatrescodeDanpesan(String rescode, String pesan) {
        step.validateInquiryGagal(rescode, pesan);
    }

    //SCENARIO 3

    @When("Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add")
    public void whenMasukkancustomerNumberDanproductIdDantypeinvalidUntukProsesCartAdd(String customerNumber, String productId, String type) {
        step.addCartGagal(customerNumber, productId, type);
    }

    @Then("Proses cart add gagal dan mendapat <rescode> dan <pesan>")
    public void thenProsesCartAddGagalDanMendapatrescodeDanpesan(String rescode, String pesan) {
        step.validateAddCartGagal(rescode, pesan);
    }

    //SCENARIO 4

    @When("Masukkan metode <pembayaran> invalid")
    public void whenMasukkanMetodepembayaranInvalid(String pembayaran) {
        step.selectPaymentGagal(pembayaran);
    }

    @Then("Proses gagal dan mendapat <rescode> dan <pesan>")
    public void thenProsesGagalDanMendapatrescodeDanpesan(String rescode, String pesan) {
        step.validateSelectPaymentGagal(rescode, pesan);
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

    @When("Masukkan <orderId> invalid")
    public void whenMasukkanorderIdInvalid(String orderId) {
        step.completeSelectPaymentMethodGagal(orderId);
    }

    @Then("Proses complete select payment method gagal dan mendapat <rescode> dan <pesan>")
    public void thenProsesCompleteSelectPaymentMethodGagalDanMendapatrescodeDanpesan(String rescode, String pesan) {
        step.validateCompleteSelectPaymentMethodGagal(rescode, pesan);
    }



}
