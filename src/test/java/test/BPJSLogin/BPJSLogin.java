package test.BPJSLogin;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BPJSLogin extends SerenityStory {

    @Steps
    BPJSLoginStep step;

    @Given("User sudah login dengan <email> dan <password> (BPJS)")
    public void givenUserSudahLoginDenganemailDanpasswordBPJS(String email, String password) {
        step.loginForGetToken(email, password);
    }

    @Given("Sudah di halaman pembayaran BPJS (BPJS)")
    public void givenSudahDiHalamanPembayaranBPJSBPJS() {

    }

    @When("Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)")
    public void whenMasukkancustomerNumberproductIdDanpaymentPeriodUntukProsesInquiryDanMendapatrescodeDanpesanBPJS(String customerNumber, String productId, String paymentPeriod, String rescode, String pesan) {
        step.inquiry(customerNumber, productId, paymentPeriod, rescode, pesan);
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)")
    public void whenMasukkancustomerNumberDanproductIdDantypeUntukProsesCartAddDanMendapatrescodeDanpesanBPJS(String customerNumber, String productId, String type, String rescode, String pesan) {
        step.addCart(customerNumber, productId, type, rescode, pesan);
    }

    @When("Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan> (BPJS)")
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescodeDanpesanBPJS(String pembayaran, String cekCC, String rescode, String pesan) {
        step.selectPayment(pembayaran, rescode, pesan);
        step.prosesPembayaran(pembayaran, cekCC, rescode, pesan);
    }

    @Then("Proses pembayaran tagihan BPJS terbayarkan dan mendapat <rescode> dan <pesan> (BPJS)")
    public void thenProsesPembayaranTagihanBPJSTerbayarkanDanMendapatrescodeDanpesanBPJS(String rescode, String pesan) {
        step.completePembayaran(rescode, pesan);
    }

    //Scenario 2
    @When("Masukkan <customerNumber> dan <productId> dan <paymentPeriod> (invalid) untuk proses inquiry (BPJS)")
    public void whenMasukkancustomerNumberDanproductIdDanpaymentPeriodinvalidUntukProsesInquiryBPJS(String customerNumber, String productId, String paymentPeriod) {
        step.inquiryGagal(customerNumber, productId, paymentPeriod);
    }

    @Then("Inquiry gagal dan mendapat <rescode> dan <pesan1> atau <pesan2> (BPJS)")
    public void thenInquiryGagalDanMendapatrescodeDanpesan1Ataupesan2BPJS(String rescode, String pesan1, String pesan2) {
        step.validateInquiryGagal(rescode, pesan1, pesan2);
    }

    //Scenario 3
    @When("Masukkan customer number, payment period dan product id yang valid untuk proses inquiry")
    public void whenMasukkanCustomerNumberPaymentPeriodDanProductIdYangValidUntukProsesInquiry() {
        step.inquiry("0000001430071807", "383", "01", "00", "");
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add (BPJS)")
    public void whenMasukkancustomerNumberDanproductIdDantypeinvalidUntukProsesCartAddBPJS(String customerNumber, String productId, String type) {
        step.addCartGagal(customerNumber, productId, type);
    }

    @Then("Proses cart add gagal dan mendapat <rescode> dan <pesan> (BPJS)")
    public void thenProsesCartAddGagalDanMendapatrescodeDanpesanBPJS(String rescode, String pesan) {
        step.validateAddCartGagal(rescode, pesan);
    }

    //Scenario 4
    @When("Masukkan metode <pembayaran> invalid (BPJS)")
    public void whenMasukkanMetodepembayaranInvalidBPJS(String pembayaran) {
        step.prosesPembayaranGagal(pembayaran);
    }

    @Then("Proses gagal dan mendapat <rescode> dan <pesan> (BPJS)")
    public void thenProsesGagalDanMendapatrescodeDanpesanBPJS(String rescode, String pesan) {
        step.validateProsesPembayaranGagal(rescode, pesan);
    }

    //Scenario 5
    @When("Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode1> dan <pesan1> (BPJS)")
    public void whenMasukkancustomerNumberproductIdDanpaymentPeriodUntukProsesInquiryDanMendapatrescode1Danpesan1BPJS(String customerNumber, String productId, String paymentPeriod, String rescode1, String pesan1) {
        step.inquiry(customerNumber, productId, paymentPeriod, rescode1, pesan1);
    }

    @When("Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1> (BPJS)")
    public void whenMasukkancustomerNumberDanproductIdDantypeUntukProsesCartAddDanMendapatrescode1Andpesan1BPJS(String customerNumber, String productId, String type, String rescode1, String pesan1) {
        step.addCart(customerNumber, productId, type, rescode1, pesan1);
    }

    @When("Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1> (BPJS)")
    public void whenPilihMetodepembayaranDancekCCDanMendapatrescode1Andpesan1BPJS(String pembayaran, String cekCC, String rescode1, String pesan1) {
        step.prosesPembayaran(pembayaran, cekCC, rescode1, pesan1);
    }

    @When("Memasukkan <orderId> invalid (BPJS)")
    public void whenMemasukkanorderIdInvalidBPJS(String orderId) {
        step.completePembayaranGagal(orderId);
    }

    @Then("Proses complete select payment method gagal dan mendapat <rescode> dan <pesan> (BPJS)")
    public void thenProsesCompleteSelectPaymentMethodGagalDanMendapatrescodeDanpesanBPJS(String rescode, String pesan) {
        step.validateCompletePembayaranGagal(rescode, pesan);
    }

    //SCENARIO 6
    @When("Pilih metode <pembayaran>, <cekCC> yang diinginkan untuk split dengan sepulsa kredit yang dimiliki (BPJS)")
    public void whenPilihMetodepembayarancekCCYangDiinginkanUntukSplitDenganSepulsaKreditYangDimilikiBPJS(String pembayaran, String cekCC) {
        step.selectPayment(pembayaran, "00", "");
        step.prosesPembayaran(pembayaran, cekCC, "00", "");
    }

    @Then("Proses transaksi PLN Postpaid terbayar dengan mendapat <rescode> dan <pesan> (BPJS)")
    public void thenProsesTransaksiPLNPostpaidTerbayarDenganMendapatrescodeDanpesanBPJS(String rescode, String pesan) {
        step.completePembayaran(rescode, pesan);
    }

    //Scenario 7
    @When("Cek metode pembayaran yang tersedia (BPJS)")
    public void whenCekMetodePembayaranYangTersediaBPJS() {
        step.cekPaymentList();
    }

    @Then("Tidak dapat melakukan pembayaran menggunakan sepulsa credit, karena dana yang dimiliki 0 serta mendapat <rescode> dan <pesan> (BPJS)")
    public void thenTidakDapatMelakukanPembayaranMenggunakanSepulsaCreditKarenaDanaYangDimiliki0SertaMendapatrescodeDanpesanBPJS(String rescode, String pesan) {
        step.validateCekPaymentList(rescode, pesan);
    }




}
