package test.PLNPrepaidLogin;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class plnPrepaidLogin extends SerenityStory {
    @Steps
    plnPrepaidLoginStep step;

    @Given("User sudah login dengan <email> dan <password> (prepaid)")
    public void givenUserSudahLoginDenganemailDanpasswordprepaid(String email, String password) {
        step.loginForGetToken(email, password);
    }

    @Given("Sudah di halaman pembayaran PLN Prepaid (prepaid)")
    public void givenSudahDiHalamanPembayaranPLNPrepaidprepaid() {

    }

    @When("Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan> (prepaid)")
    public void whenMasukkancustomerNumberDanproductIdUntukProsesInquiryDanMendapatrescodeDanpesanprepaid(String customerNumber, String productId, String rescode, String pesan) {
        step.inquiry(customerNumber, productId, rescode, pesan);
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (prepaid)")
    public void whenMasukkancustomerNumberDanproductIdDantypeUntukProsesCartAddDanMendapatrescodeDanpesanprepaid(String customerNumber, String productId, String type, String rescode, String pesan) {
        step.addCart(customerNumber, productId, type, rescode, pesan);
    }

    @When("Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan> (prepaid)")
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescodeDanpesanprepaid(String pembayaran, String cekCC, String rescode, String pesan) {
        step.selectPayment(pembayaran, rescode, pesan);
        step.prosesPembayaran(pembayaran, cekCC, rescode, pesan);
    }

    @Then("Proses pembayaran tagihan PLN Prepaid terbayarkan dan mendapat <rescode> dan <pesan> (prepaid)")
    public void thenProsesPembayaranTagihanPLNPrepaidTerbayarkanDanMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.completePembayaran(rescode, pesan);
    }

    //SCENARIO 2
    @When("Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry (prepaid)")
    public void whenMasukkancustomerNumberDanproductIdinvalidUntukProsesInquiryprepaid(String customerNumber, String productId) {
        step.inquiryGagal(customerNumber, productId);
    }

    @Then("Inquiry gagal dan mendapat <rescode> dan <pesan> (prepaid)")
    public void thenInquiryGagalDanMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.validateInquiryGagal(rescode, pesan);
    }

    //SCENARIO 3
    @When("Masukkan customer number dan product id yang valid untuk proses inquiry")
    public void whenMasukkanCustomerNumberDanProductIdYangValidUntukProsesInquiry() {
        step.inquiry("01428800700", "286", "00", "");
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add (prepaid)")
    public void whenMasukkancustomerNumberDanproductIdDantypeinvalidUntukProsesCartAddPrepaid(String customerNumber, String productId, String type) {
        step.addCartGagal(customerNumber, productId, type);
    }

    @Then("Proses cart add gagal dan mendapat <rescode> dan <pesan> (prepaid)")
    public void thenProsesCartAddGagalDanMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.validateAddCartGagal(rescode, pesan);
    }

    //SCENARIO 4
    @When("Masukkan metode <pembayaran> invalid (prepaid)")
    public void whenMasukkanMetodepembayaranInvalidprepaid(String pembayaran) {
        step.prosesPembayaranGagal(pembayaran);
    }

    @Then("Proses gagal dan mendapat <rescode> dan <pesan> (prepaid)")
    public void thenProsesGagalDanMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.validateProsesPembayaranGagal(rescode, pesan);
    }

    //SCENARIO 5
    @When("Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode1> and <pesan1> (prepaid)")
    public void whenMasukkancustomerNumberDanproductIdUntukProsesInquiryDanMendapatrescode1Andpesan1prepaid(String customerNumber, String productId, String rescode1, String pesan1) {
        step.inquiry(customerNumber, productId, rescode1, pesan1);
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1> (prepaid)")
    public void whenMasukkancustomerNumberDanproductIdDantypeUntukProsesCartAddDanMendapatrescode1Andpesan1prepaid(String customerNumber, String productId, String type, String rescode1, String pesan1) {
        step.addCart(customerNumber, productId, type, rescode1, pesan1);
    }

    @When("Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1> (prepaid)")
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescode1Andpesan1prepaid(String pembayaran, String cekCC, String rescode1, String pesan1) {
        step.selectPayment(pembayaran, rescode1, pesan1);
        step.prosesPembayaran(pembayaran, cekCC, rescode1, pesan1);
    }

    @When("Memasukkan <orderId> invalid (prepaid)")
    public void whenMemasukkanorderIdInvalidprepaid(String orderId) {
        step.completePembayaranGagal(orderId);
    }

    @Then("Proses complete select payment method gagal dan mendapat <rescode> dan <pesan> (prepaid)")
    public void thenProsesCompleteSelectPaymentMethodGagalDanMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.validateCompletePembayaranGagal(rescode, pesan);
    }

    //SCENARIO 6
    @When("Pilih metode <pembayaran>, <cekCC> yang diinginkan untuk split dengan sepulsa kredit yang dimiliki (prepaid)")
    public void whenPilihMetodepembayarancekCCYangDiinginkanUntukSplitDenganSepulsaKreditYangDimilikiprepaid(String pembayaran, String cekCC) {
        step.selectPayment(pembayaran, "00", "");
        step.prosesPembayaran(pembayaran, cekCC, "00", "");
    }

    @Then("Proses transaksi PLN Postpaid terbayar dengan mendapat <rescode> dan <pesan> (prepaid)")
    public void thenProsesTransaksiPLNPostpaidTerbayarDenganMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.completePembayaran(rescode, pesan);
    }

    //SCENARIO 7
    @When("Cek metode pembayaran yang tersedia (prepaid)")
    public void whenCekMetodePembayaranYangTersediaprepaid() {
        step.cekPaymentList();
    }

    @Then("Tidak dapat melakukan pembayaran menggunakan sepulsa credit, karena dana yang dimiliki 0 serta mendapat <rescode> dan <pesan> (prepaid)")
    public void thenTidakDapatMelakukanPembayaranMenggunakanSepulsaCreditKarenaDanaYangDimiliki0SertaMendapatrescodeDanpesanprepaid(String rescode, String pesan) {
        step.validateCekPaymentList(rescode, pesan);
    }


}
