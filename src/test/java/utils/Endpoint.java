package utils;

public interface Endpoint {
    String base = "https://altaqeproject.sepulsa.id/bumi";

    String login = base + "/account/login";
    String guest_token = base + "/get_token";
    String add_cart = base + "/bumi_cart/add";
    String update_cart = base + "/bumi_checkout/cart_update";
    String process_cart = base + "/bumi_checkout/process";
    String complete_cart = base + "/bumi_checkout/complete";
}
