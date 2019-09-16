package test.PembelianPulsa;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;


public class PembelianPulsa extends SerenityStory {
    @Steps
    PembelianPulsaSteps pembelianPulsaSteps;

    //-----------------------------------------------SCENARIO 1---------------------------------------------------------

    @Given("user berada pada halaman utama sepulsa (Sign In)")
    public void givenUserBeradaPadaHalamanUtamaSepulsaSignIn() {
        pembelianPulsaSteps.postLogin();
    }

    @Given("membuka halaman pembelian pre-paid")
    public void givenMembukaHalamanPembelianPrepaid() {
        //Intentionally left blank
    }

    @When("user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli")
    public void whenUserInputTypePulsano_hpDanproduct_idYangInginDibeli(String no_hp, String product_id) {
        pembelianPulsaSteps.postAddCart00(no_hp, product_id);
    }

    @When("memilih <pembayaran> yang diinginkan")
    public void whenMemilihpembayaranYangDiinginkan(String pembayaran) {
        pembelianPulsaSteps.updateCart00(pembayaran);
        pembelianPulsaSteps.processCart00(pembayaran);
    }

    @When("melakukan pembayaran")
    public void whenMelakukanPembayaran() {
        //Intentionally left blank
    }

    @Then("user mendapatkan informasi No VA <pembayaran> yang sesuai untuk membayar")
    public void thenUserMendapatkanInformasiNoVAYangSesuaiUntukMembayar(String pembayaran) {
        pembelianPulsaSteps.completeCart00(pembayaran);
    }

    //-----------------------------------------------SCENARIO 2---------------------------------------------------------

    @When("user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli (63)")
    public void whenUserInputTypePulsano_hpDanproduct_idYangInginDibeli63(String no_hp, String product_id) {
        pembelianPulsaSteps.postAddCart63(no_hp, product_id);
    }

    @Then("user mendapatkan notifikasi product not found")
    public void thenUserMendapatkanNotifikasiProductNotFound() {

    }

    //-----------------------------------------------SCENARIO 3---------------------------------------------------------

    @Then("user mendapatkan notifikasi Order Tidak Ditemukan (81)")
    public void thenUserMendapatkanNotifikasiOrderTidakDitemukan81() {
        pembelianPulsaSteps.completeCart81();
    }

    //-----------------------------------------------SCENARIO 4---------------------------------------------------------

    @When("memilih <pembayaran> yang diinginkan (payment_method invalid)")
    public void whenMemilihpembayaranYangDiinginkanpayment_methodInvalid(String pembayaran) {
        pembelianPulsaSteps.processCartNullPayment(pembayaran);
    }

    @Then("metode pembayaran tidak terbaca")
    public void thenMetodePembayaranTidakTerbaca() {
        pembelianPulsaSteps.completeCartNullPayment();
    }
}
