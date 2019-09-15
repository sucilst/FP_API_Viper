package test.PLNPostpaidLogin;

import net.serenitybdd.rest.SerenityRest;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import utils.Endpoint;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class plnPostpaidLoginStep extends Steps {

    Endpoint endPoint;
    String accessToken, noOrder, prodId, metodePembayaran;

    public void loginForGetToken(String username, String password){
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("email_or_phone_number",username);
        reqBody.put("password",password);

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.login);
        SerenityRest
                .then()
                    .statusCode(200);

        String resCode = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(resCode.equals("00"));

        accessToken = SerenityRest.then().extract().path("data.access_token");

    }

    public void getAnonimToken(){
        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .contentType("application/json")
                .when()
                    .get(endPoint.getAnonimToken);
        SerenityRest
                .then()
                    .statusCode(200);

        String resCode = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(resCode.equals("00"));

        accessToken = SerenityRest.then().extract().path("data.access_token");

    }

    public void inquiry (String customerNumber, String productId, String rescode, String pesan){
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("customer_number",customerNumber);
        reqBody.put("product_id",productId);
        reqBody.put("phone_number","");

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.inquiryPLN);

        if (rescode.equals("00")) {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/inquirySukses.json"));
        }
        else if (rescode.equals("63")){
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/inquiryGagal.json"));
        }

        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        Assert.assertTrue(resCodeActual.equals(rescode));
        Assert.assertTrue(pesanActual.equals(pesan));
    }

    public void addCart (String customerNumber, String productId, String type, String rescode, String pesan){
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("type",type);
        reqBody.put("product_id",productId);
        reqBody.put("phone_number","081234000001");
        reqBody.put("customer_number",customerNumber);
        reqBody.put("provider","");

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .header("Authorization","Bearer " + accessToken)
                .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.addCart);

        if (rescode.equals("00")) {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/addCartSukses.json"));

            String prodIdActual = SerenityRest.then().extract().path("data.pane[0].product_id");
            Assert.assertTrue(productId.equals(prodIdActual));
        }
        else if (rescode.equals("63")) {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/addCartGagal.json"));
        }

        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        Assert.assertTrue(rescode.equals(resCodeActual));
        Assert.assertTrue(pesan.equals(pesanActual));

        prodId = productId;
    }

    public void selectPayment (String payment, String rescode, String pesan){
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("payment_method","commerce_veritrans|" + payment);
        reqBody.put("promo_code","");
        reqBody.put("use_credit",true);

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .header("Authorization","Bearer " + accessToken)
                .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.selectPayment);

        SerenityRest
                .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/selectPaymentSukses.json"));

        String prodIdActual = SerenityRest.then().extract().path("data.pane[0].product_id");
        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        Assert.assertTrue(prodId.equals(prodIdActual));
        Assert.assertTrue(rescode.equals(resCodeActual));
        Assert.assertTrue(pesan.equals(pesanActual));
    }

    public void prosesPembayaran (String payment, String cekCC, String rescode, String pesan){
        Map<String, Object> reqBody =new HashMap<>();
        if (cekCC.equals("no")) {
            reqBody.put("payment_method", "commerce_veritrans|" + payment);
            reqBody.put("promo_code", "");
            reqBody.put("use_credit", true);
            reqBody.put("user_mail", "coba@alterra.id");
        }
        else if (cekCC.equals("cc")){
            reqBody.put("token","481111NGBfpYvCeaLxCPEBiYAJhf1114");
            reqBody.put("payment_method", "commerce_veritrans|" + payment);
            reqBody.put("promo_code", "");
            reqBody.put("use_credit", true);
            reqBody.put("user_mail", "coba@alterra.id");
            //KURANG METODE PEMBAYARAN KARTU KREDIT
        }
        else if (cekCC.equals("sc")){
            reqBody.put("payment_method", "payment_commerce_2|" + payment);
            reqBody.put("promo_code", "");
            reqBody.put("use_credit", true);
            reqBody.put("user_mail", "coba@alterra.id");

            metodePembayaran = "Free Order";
        }

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .header("Authorization","Bearer " + accessToken)
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.prosesPembayaran);

        if (cekCC.equals("no") || cekCC.equals("cc")) {
            SerenityRest
                    .then()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/prosesPembayaranSukses.json"));
        }
        else {
            SerenityRest
                    .then()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/prosesPembayaranGagal.json"));   //JSON SUKSES DENGAN SC sama dengan JSON GAGAL
        }

        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");
        String pembayaranActual = SerenityRest.then().extract().path("sepulsa_messages.warning[0]");

        Assert.assertTrue(rescode.equals(resCodeActual));
        Assert.assertTrue(pesan.equals(pesanActual));

        if (payment.equals("commerce_payment_atm_mandiri")){
            Assert.assertTrue(pembayaranActual.equals("Mandiri Bill Payment: OK, Mandiri Bill transaction is successful"));
            metodePembayaran = "Mandiri Virtual Account";
        }
        else if (payment.equals("rules_bca_virtual_account")){
            Assert.assertTrue(pembayaranActual.equals("Virtual Account: Success, Bank Transfer transaction is created"));
            metodePembayaran = "BCA Virtual Account";
        }
        else if (payment.equals("rules_permata_virtual_account")){
            Assert.assertTrue(pembayaranActual.equals("Virtual Account: Success, PERMATA VA transaction is successful"));
            metodePembayaran = "Virtual Account Semua Bank";
        }
        //KURANG KARTU KREDIT

        noOrder = SerenityRest.then().extract().path("data.order_id");
    }

    public void completePembayaran(String rescode, String pesan, String orderId){
        Map<String, Object> reqBody =new HashMap<>();
        if (rescode.equals("00")) {
            reqBody.put("order_id", noOrder);
        }
        else if (rescode.equals("81")){
            reqBody.put("order_id", orderId);
        }

        SerenityRest
                .given()
                    .header("User-Agent", "alta")
                    .header("Source","phoenix")
                    .header("Authorization","Bearer " + accessToken)
                    .contentType("application/json")
                    .body(reqBody)
                .when()
                    .post(endPoint.completePembayaran);

        if (rescode.equals("00") && (metodePembayaran.equals("Mandiri Virtual Account") || metodePembayaran.equals("BCA Virtual Account") || metodePembayaran.equals("Virtual Account Semua Bank"))) {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/completePembayaranSukses.json"));

            String cekOrder = SerenityRest.then().extract().path("data.order_id");
            String pembayaranActual = SerenityRest.then().extract().path("data.cart.payment_title");

            Assert.assertTrue(metodePembayaran.equals(pembayaranActual));
            Assert.assertTrue(cekOrder.equals(noOrder));
        }
        else if (rescode.equals("00") && (metodePembayaran.equals("Free Order"))) {
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/completePembayaranSCSukses.json"));

            String cekOrder = SerenityRest.then().extract().path("data.order_id");
            String pembayaranActual = SerenityRest.then().extract().path("data.cart.payment_title");

            Assert.assertTrue(metodePembayaran.equals(pembayaranActual));
            Assert.assertTrue(cekOrder.equals(noOrder));
        }
        else if (rescode.equals("81")){
            SerenityRest
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PLNPostpaidLogin/completePembayaranGagal.json"));
        }

        String resCodeActual = SerenityRest.then().extract().path("rescode");
        String pesanActual = SerenityRest.then().extract().path("message.body");

        Assert.assertTrue(rescode.equals(resCodeActual));
        Assert.assertTrue(pesan.equals(pesanActual));
        }
}
