Meta: Transaksi PLN Postpaid

Narrative:
Sebagai user
Saya ingin melakukan transaksi pembayaran PLN postpaid
Sehingga tagihan transaksi PLN postpaid terbayarkan

Scenario: Transaksi Pembayaran PLN Postpaid (Login)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan>
Then Proses pembayaran tagihan PLN Postpaid terbayarkan dan mendapat <rescode> dan <pesan>

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                         |cekCC|rescode|pesan|
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|commerce_payment_atm_mandiri       |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|rules_bca_virtual_account          |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|rules_permata_virtual_account      |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |512345600003  |614      |pln_postpaid|commerce_payment_commerce_veritrans|cc   |00     |     |
|taratester02@gmail.com|testersepulsa123|512345610000  |614      |pln_postpaid|commerce_payment_payment_commerce_2|sc   |00     |     |

Scenario: Inquiry Gagal (invalid product id)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry dan mendapat <rescode> dan <pesan>
Then Inquiry gagal

Examples:
|email                 |password        |customerNumber|productId|rescode|pesan             |
|tester-tara@gmail.com |sepulsa123      |512345610000  |         |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |512345610000  |lolo     |63     |Product not found.|

Scenario: Add Cart Gagal (invalid product id / type)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add dan mendapat <rescode> dan <pesan>
Then Proses cart add gagal

Examples:
|email                 |password        |customerNumber|productId|type         |rescode|pesan             |
|tester-tara@gmail.com |sepulsa123      |512345610000  |123      |pln_postpaid |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |512345610000  |         |             |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |             |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |512345610000  |         |123124       |63     |Product not found.|

Scenario: Process Gagal (invalid payment method)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Masukkan metode <pembayaran> invalid dan mendapat <rescode> dan <pesan>
Then Proses gagal

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran       |rescode|pesan|
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|                 |00     |     |
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|pembayaran_tunai |00     |     |

Scenario: Complete Select Payment Method Gagal (invalid order id)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode1> and <pesan1>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1>
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1>
And Mendapat <rescode> dan <pesan> setelah memasukkan <orderId> invalid
Then Proses complete select payment method gagal

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                         |cekCC|orderId|rescode|pesan                 |rescode1|pesan1|
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|commerce_payment_atm_mandiri       |no   |12     |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |512345610000  |614      |pln_postpaid|commerce_payment_atm_mandiri       |no   |       |81     |Order Tidak Ditemukan.|00     |       |









