Meta:
@PLNPrepaid

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
|email                 |password        |customerNumber|productId|type       |pembayaran                          |cekCC|rescode|pesan|
|farras@alterra.id     |greenday89      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri        |no   |00     |     |
|farras@alterra.id     |greenday89      |01428800700   |286      |pln_prepaid|rules_bca_virtual_account           |no   |00     |     |
|farras@alterra.id     |greenday89      |01428800700   |286      |pln_prepaid|rules_permata_virtual_account       |no   |00     |     |
|farras@alterra.id     |greenday89      |01428800700   |286      |pln_prepaid|commerce_payment_commerce_veritrans |cc   |00     |     |
|taratester02@gmail.com|testersepulsa123|01428800700   |286      |pln_prepaid|commerce_payment_payment_commerce_2 |sc   |00     |     |

Scenario: Inquiry Gagal (invalid customer number / product id)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry (prepaid)
Then Inquiry gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email             |password        |customerNumber|productId|rescode|pesan                           |
|farras@alterra.id |greenday89      |01428800700   |         |63     |Product not found.              |
|farras@alterra.id |greenday89      |01428800700   |cobain   |63     |Product not found.              |
|farras@alterra.id |greenday89      |01428800700   |@@@      |63     |Product not found.              |
|farras@alterra.id |greenday89      |01428800700   |010      |63     |Product not found.              |
|farras@alterra.id |greenday89      |999999999     |286      |       |                                |
|farras@alterra.id |greenday89      |              |         |40     |No Pelanggan salah/ belum aktif.|
|farras@alterra.id |greenday89      |              |286      |40     |No Pelanggan salah/ belum aktif.|
|farras@alterra.id |greenday89      |hahahaha      |286      |       |                                |
|farras@alterra.id |greenday89      |*&alta&*      |286      |       |                                |


Scenario: Add Cart Gagal (invalid product id / type)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan customer number dan product id yang valid untuk proses inquiry (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add (prepaid)
Then Proses cart add gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email             |password        |customerNumber|productId|type         |rescode|pesan              |
|farras@alterra.id |greenday89      |01428800700   |155      |pln_prepaid  |63     |Product not found. |
|farras@alterra.id |greenday89      |01428800700   |         |pln_prepaid  |63     |Product not found. |
|farras@alterra.id |greenday89      |01428800700   |286      |             |63     |Product not found. |
|farras@alterra.id |greenday89      |01428800700   |286      |pln_postpaid |63     |Product not found. |
|farras@alterra.id |greenday89      |11111         |286      |pln_prepaid  |41     |Server PLN Cut-off.|
|farras@alterra.id |greenday89      |              |286      |pln_prepaid  |41     |Server PLN Cut-off.|

Scenario: Process Gagal (invalid payment method)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan metode <pembayaran> invalid (prepaid)
Then Proses gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email             |password        |customerNumber|productId|type        |pembayaran                  |rescode|pesan|
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid |                            |00     |     |
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid |pembayaran_via_dompet_taryo |00     |     |
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid |#<dompetTebal>#             |00     |     |


Scenario: Complete Select Payment Method Gagal (invalid order id)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode1> and <pesan1> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1> (prepaid)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1> (prepaid)
And Memasukkan <orderId> invalid (prepaid)
Then Proses complete select payment method gagal dan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email             |password        |customerNumber|productId|type        |pembayaran                  |cekCC|orderId  |rescode|pesan                 |rescode1|pesan1|
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |12       |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |         |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |cobain   |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |01428800700   |286      |pln_prepaid|commerce_payment_atm_mandiri |no   |*#alta#* |81     |Order Tidak Ditemukan.|00     |       |

Scenario: Transaksi PLN Postpaid dengan menggunakan sepulsa credit (sepulsa credit <= harga transaksi)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (prepaid)
And Pilih metode <pembayaran>, <cekCC> yang diinginkan untuk split dengan sepulsa kredit yang dimiliki (prepaid)
Then Proses transaksi PLN Postpaid terbayar dengan mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email                |password    |customerNumber|productId|type       |pembayaran               |cekCC|rescode|pesan|
|rakaditya@alterra.id |rakaganteng |01428800700   |286      |pln_prepaid|rules_bca_virtual_account|no   |00     |     |

Scenario: Transaksi PLN Postpaid dengan menggunakan sepulsa credit (sepulsa credit = 0)
Given User sudah login dengan <email> dan <password> (prepaid)
And Sudah di halaman pembayaran PLN Prepaid (prepaid)
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan> (prepaid)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (prepaid)
And Cek metode pembayaran yang tersedia (prepaid)
Then Tidak dapat melakukan pembayaran menggunakan sepulsa credit, karena dana yang dimiliki 0 serta mendapat <rescode> dan <pesan> (prepaid)

Examples:
|email            |password   |customerNumber|productId|type       |rescode|pesan|
|farras@alterra.id|greenday89 |01428800700   |286      |pln_prepaid|00     |     |
