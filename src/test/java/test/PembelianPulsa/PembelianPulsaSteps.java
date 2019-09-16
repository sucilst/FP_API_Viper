package test.PembelianPulsa;

import net.serenitybdd.rest.SerenityRest;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import utils.Endpoint;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PembelianPulsaSteps extends Steps {
    String token;
    String orderId;

    public void postLogin() {
        Map<String, Object> loginToken = new HashMap<>();
        loginToken.put("email_or_phone_number", "farras@alterra.id");
        loginToken.put("password", "greenday89");

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .body(loginToken)
                .when()
                    .post(Endpoint.login)
                .then()
                    .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        //Input Nilai
        token = SerenityRest.then().extract().path("data.access_token");
    }

    public void postLoginSepulsaKredit() {
        Map<String, Object> loginToken = new HashMap<>();
        loginToken.put("email_or_phone_number", "taratester02@gmail.com");
        loginToken.put("password", "testersepulsa123");

        SerenityRest
                .given()
                .contentType("application/json")
                .header("Source", "phoenix")
                .header("User-Agent", "elang")
                .body(loginToken)
                .when()
                .post(Endpoint.login)
                .then()
                .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        //Input Nilai
        token = SerenityRest.then().extract().path("data.access_token");
    }

    public void postAddCart00(String no_hp, String product_id) {
        Map<String, Object> addCart = new HashMap<>();
        addCart.put("phone_number", no_hp);
        addCart.put("product_id", product_id);
        addCart.put("type", "mobile_prepaid");

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                    .body(addCart)
                .when()
                    .post(Endpoint.addCart)
                .then()
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartAdd_00.json"))
                    .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals(""));

        String product = SerenityRest.then().extract().path("data.pane[0].product_id");
        Assert.assertTrue(product.equals(product_id));
    }

    public void updateCart00(String pembayaran) {
        Map<String, Object> upCart = new HashMap<>();
        if(pembayaran.equals("sk")) {
            upCart.put("payment_method", "payment_commerce_2|commerce_payment_payment_commerce_2");
        }
        else if(pembayaran.equals("cc")) {
            upCart.put("payment_method", "commerce_veritrans|commerce_payment_commerce_veritrans");
        }
        else {
            upCart.put("payment_method", "commerce_veritrans|" + pembayaran);
        }
        upCart.put("promo_code", "");
        upCart.put("use_credit", "true");

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                    .body(upCart)
                .when()
                    .post(Endpoint.selectPayment)
                .then()
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartUpdate.json"))
                    .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals(""));

        String body = SerenityRest.then().extract().path("message.body");
        Assert.assertTrue(body.equals(""));
    }

    public void processCart00(String pembayaran) {
        Map<String, Object> processCart = new HashMap<>();
        if(pembayaran.equals("sk")) {
            processCart.put("payment_method", "payment_commerce_2|commerce_payment_payment_commerce_2");
        }
        else if(pembayaran.equals("cc")) {
            processCart.put("payment_details", new HashMap<String, Object>(){
                {
                    put("token", "481111GhNOfTWSddNvEneriQijnB1114");
                }
            });
            processCart.put("payment_method", "commerce_veritrans|commerce_payment_commerce_veritrans");
        }
        else {
            processCart.put("payment_method", "commerce_veritrans|" + pembayaran);
        }
        processCart.put("promo_code", "");
        processCart.put("use_credit", "true");
        processCart.put("usermail", "farras@alterra.id");

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                    .body(processCart)
                .when()
                    .post(Endpoint.prosesPembayaran);

        if(pembayaran.equals("sk")) {
            SerenityRest
                    .then()
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartProcess_SK.json"))
                        .statusCode(200);
        }
        else if(pembayaran.equals("cc")) {
            SerenityRest
                    .then()
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartProcess_CC.json"))
                        .statusCode(200);

            //Validasi
            String status = SerenityRest.then().extract().path("sepulsa_messages.status[0]");
            Assert.assertTrue(status.equals("Kartu Kredit / Debit: Success, Credit Card transaction is successful"));
        }
        else {
            SerenityRest
                    .then()
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartProcess_00.json"))
                        .statusCode(200);

            //Validasi
            String warning = SerenityRest.then().extract().path("sepulsa_messages.warning[0]");

            switch (pembayaran) {
                case ("rules_bca_virtual_account"):
                    Assert.assertTrue(warning.equals("Virtual Account: Success, Bank Transfer transaction is created"));
                    break;
                case ("commerce_payment_atm_mandiri"):
                    Assert.assertTrue(warning.equals("Mandiri Bill Payment: OK, Mandiri Bill transaction is successful"));
                    break;
                case ("rules_permata_virtual_account"):
                    Assert.assertTrue(warning.equals("Virtual Account: Success, PERMATA VA transaction is successful"));
                    break;
            }
        }

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals(""));

        String body = SerenityRest.then().extract().path("message.body");
        Assert.assertTrue(body.equals(""));

        //Input Nilai
        orderId = SerenityRest.then().extract().path("data.order_id");
    }

    public void completeCart00(String pembayaran) {
        Map<String, Object> ctCart = new HashMap<>();
        ctCart.put("order_id", orderId);

        if(pembayaran.equals("sk")) {
            SerenityRest
                    .given()
                        .contentType("application/json")
                        .header("Source", "phoenix")
                        .header("User-Agent", "elang")
                        .header("Authorization", "Bearer " + token)
                        .body(ctCart)
                    .when()
                        .post(Endpoint.completePembayaran)
                    .then()
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartComplete_SK.json"))
                        .statusCode(200);
        }
        else {
            SerenityRest
                    .given()
                        .contentType("application/json")
                        .header("Source", "phoenix")
                        .header("User-Agent", "elang")
                        .header("Authorization", "Bearer " + token)
                        .body(ctCart)
                    .when()
                        .post(Endpoint.completePembayaran)
                    .then()
                        .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartComplete_00.json"))
                        .statusCode(200);
        }

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals(""));

        String body = SerenityRest.then().extract().path("message.body");
        Assert.assertTrue(body.equals(""));

        String order = SerenityRest.then().extract().path("data.order_id");
        Assert.assertTrue(order.equals(orderId));

        String payment = SerenityRest.then().extract().path("data.cart.payment_title");

        switch (pembayaran) {
            case ("rules_bca_virtual_account"):
                Assert.assertTrue(payment.equals("BCA Virtual Account"));
                break;
            case ("commerce_payment_atm_mandiri"):
                Assert.assertTrue(payment.equals("Mandiri Virtual Account"));
                break;
            case ("rules_permata_virtual_account"):
                Assert.assertTrue(payment.equals("Virtual Account Semua Bank"));
                break;
            case ("sk"):
                Assert.assertTrue(payment.equals("Free Order"));
                break;
        }
    }

    public void postAddCart63(String no_hp, String product_id) {
        Map<String, Object> addCart = new HashMap<>();
        addCart.put("phone_number", no_hp);
        addCart.put("product_id", product_id);
        addCart.put("type", "mobile_prepaid");

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                    .body(addCart)
                .when()
                    .post(Endpoint.addCart)
                .then()
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartAdd_63.json"))
                    .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("63"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals("Product not found."));
    }

    public void completeCart81() {
        Map<String, Object> ctCart = new HashMap<>();
        ctCart.put("order_id", "0001928");

        SerenityRest
                .given()
                .contentType("application/json")
                .header("Source", "phoenix")
                .header("User-Agent", "elang")
                .header("Authorization", "Bearer " + token)
                .body(ctCart)
                .when()
                .post(Endpoint.completePembayaran)
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartComplete_81.json"))
                .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("81"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals("General Error"));

        String body = SerenityRest.then().extract().path("message.body");
        Assert.assertTrue(body.equals("Order Tidak Ditemukan."));
    }

    public void processCartNullPayment(String pembayaran) {
        Map<String, Object> processCart = new HashMap<>();
        processCart.put("payment_method", "commerce_veritrans|" + pembayaran);
        processCart.put("promo_code", "");
        processCart.put("use_credit", "true");
        processCart.put("usermail", "apa@gmail.com");

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                    .body(processCart)
                .when()
                    .post(Endpoint.prosesPembayaran)
                .then()
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartProcess_NullPayment.json"))
                    .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals(""));

        String body = SerenityRest.then().extract().path("message.body");
        Assert.assertTrue(body.equals(""));

        //Input Nilai
        orderId = SerenityRest.then().extract().path("data.order_id");
    }

    public void completeCartNullPayment() {
        Map<String, Object> ctCart = new HashMap<>();
        ctCart.put("order_id", orderId);

        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                    .body(ctCart)
                .when()
                    .post(Endpoint.completePembayaran)
                .then()
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/CartComplete_NullPayment.json"))
                    .statusCode(200);

        //Validasi
        String rs = SerenityRest.then().extract().path("rescode");
        Assert.assertTrue(rs.equals("00"));

        String title = SerenityRest.then().extract().path("message.title");
        Assert.assertTrue(title.equals(""));

        String body = SerenityRest.then().extract().path("message.body");
        Assert.assertTrue(body.equals(""));

        String payment = SerenityRest.then().extract().path("data.cart.no_rek");
        Assert.assertTrue(payment.equals(""));
    }

    public void validateSepulsaKredit0() {
        SerenityRest
                .given()
                    .contentType("application/json")
                    .header("Source", "phoenix")
                    .header("User-Agent", "elang")
                    .header("Authorization", "Bearer " + token)
                .when()
                    .get(Endpoint.getAvailablePayment)
                .then()
                    .body(matchesJsonSchemaInClasspath("JSONSchema/PembelianPulsa/GetPaymentList.json"))
                    .statusCode(200);

        //Validasi
        String payment = SerenityRest.then().extract().path("data[0].payment_title");
        Assert.assertFalse(payment.equals("Sepulsa Kredit"));
    }
}
