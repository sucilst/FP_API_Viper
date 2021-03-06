package utils;

public interface Endpoint {
    String base = "https://altaqeproject.sepulsa.id/bumi";

    String login = base + "/account/login";

    String register = base + "/account/register";
    String inquiryPLN = base + "/pln_services/inquiry";
    String addCart = base + "/bumi_cart/add";
    String selectPayment = base + "/bumi_checkout/cart_update";
    String prosesPembayaran = base + "/bumi_checkout/process";
    String completePembayaran = base + "/bumi_checkout/complete";
    String getAnonimToken = base + "/get_token";
    String inquiryBPJS = base + "/bpjs/inquiry";
    String getAvailablePayment = base + "/checkout_payment_list";
    String reqOTP = base + "/otp/request";
    String validateOTP = base + "/otp/verification";
    String cekPaymentList = base + "/checkout_payment_list";

}
