Scenario: Transaksi Pembayaran PLN Postpaid (Gagal)
Given User sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add
And Pilih metode <pembayaran> dan <cekCC>
Then Proses pembayaran tagihan PLN Postpaid terbayarkan

Examples:
|customerNumber|productId|type|pembayaran|cekCC|
|512345610000|614|pln_postpaid|commerce_payment_atm_mandiri|no|
|512345610000|614|pln_postpaid|rules_bca_virtual_account|no|
|512345610000|614|pln_postpaid|rules_permata_virtual_account|no|


Scenario: Transaksi Pembayaran PLN Postpaid dengan VA dan CC (Login)
Given User sudah login
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add
And Pilih metode <pembayaran> dan <cekCC>
Then Proses pembayaran tagihan PLN Postpaid terbayarkan

Examples:
|customerNumber|productId|type|pembayaran|cekCC|
|512345610000|614|pln_postpaid|payment_commerce_2|commerce_payment_payment_commerce_2