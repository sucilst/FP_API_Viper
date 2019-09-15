Meta: Transaksi PLN Prepaid

Narrative:
Sebagai user
Saya ingin melakukan transaksi pembayaran PLN prepaid
Sehingga tagihan transaksi PLN prepaid terbayarkan

Scenario: Transaksi Pembayaran PLN Prepaid (Login)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (prepaid)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan> (prepaid)
Then Proses pembayaran tagihan PLN Prepaid terbayarkan dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                         |cekCC|rescode|pesan|
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri        |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|rules_bca_virtual_account           |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|rules_permata_virtual_account       |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|commerce_payment_commerce_veritrans |cc   |00     |     |
|taratester02@gmail.com|testersepulsa123|01428800700   |286      |pln_prepaid|commerce_payment_payment_commerce_2 |sc   |00     |     |

Scenario: Inquiry Gagal (invalid customer number / product id)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry (prepaid)
Then Inquiry gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email                 |password        |customerNumber|productId|rescode|pesan             |
|tester-tara@gmail.com |sepulsa123      |01428800700   |         |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |01428800700   |cobain   |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |01428800700   |@@@      |63     |Product not found.|
|tester-tara@gmail.com |sepulsa123      |1234          |286      |       |                  |

Scenario: Add Cart Gagal (invalid product id / type)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add (prepaid)
Then Proses cart add gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email                 |password        |customerNumber|productId|type         |rescode|pesan              |
|tester-tara@gmail.com |sepulsa123      |01428800700   |155      |pln_prepaid  |63     |Product not found. |
|tester-tara@gmail.com |sepulsa123      |01428800700   |         |pln_prepaid  |63     |Product not found. |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |             |63     |Product not found. |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_postpaid |63     |Product not found. |
|tester-tara@gmail.com |sepulsa123      |11111         |286      |pln_prepaid  |41     |Server PLN Cut-off.|
|tester-tara@gmail.com |sepulsa123      |              |286      |pln_prepaid  |41     |Server PLN Cut-off.|

Scenario: Process Gagal (invalid payment method)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan metode <pembayaran> invalid (prepaid)
Then Proses gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                 |rescode|pesan|
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid |                           |00     |     |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid |pembayaran_via_dompet_taryo|00     |     |

Scenario: Complete Select Payment Method Gagal (invalid order id)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode1> and <pesan1> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1> (prepaid)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1> (prepaid)
And Memasukkan <orderId> invalid (prepaid)
Then Proses complete select payment method gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                  |cekCC|orderId |rescode|pesan                 |rescode1|pesan1|
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |12      |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |        |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |cobain  |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |*#alta#*|81     |Order Tidak Ditemukan.|00     |       |

