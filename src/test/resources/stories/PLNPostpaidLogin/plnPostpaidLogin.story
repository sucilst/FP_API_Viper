Meta:
@PLNPostpaid

Narrative:
Sebagai user
Saya ingin melakukan transaksi pembayaran PLN postpaid
Sehingga tagihan transaksi PLN postpaid terbayarkan

Scenario: Transaksi Pembayaran PLN Postpaid (Login)
Meta:
@PLNPostpaidSukses
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan>
Then Proses pembayaran tagihan PLN Postpaid terbayarkan dan mendapat <rescode> dan <pesan>

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                         |cekCC|rescode|pesan|
|farras@alterra.id     |greenday89      |512345610000  |614      |pln_postpaid|commerce_payment_atm_mandiri       |no   |00     |     |
|farras@alterra.id     |greenday89      |512345610000  |614      |pln_postpaid|rules_bca_virtual_account          |no   |00     |     |
|farras@alterra.id     |greenday89      |512345610000  |614      |pln_postpaid|rules_permata_virtual_account      |no   |00     |     |
|farras@alterra.id     |greenday89      |512345600003  |614      |pln_postpaid|commerce_payment_commerce_veritrans|cc   |00     |     |
|taratester02@gmail.com|testersepulsa123|512345610000  |614      |pln_postpaid|commerce_payment_payment_commerce_2|sc   |00     |     |

Scenario: Inquiry Gagal (invalid product id)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry dan mendapat <rescode> dan <pesan>
Then Inquiry gagal

Examples:
|email             |password        |customerNumber |productId|rescode|pesan                           |
|farras@alterra.id |greenday89      |512345610000   |         |63     |Product not found.              |
|farras@alterra.id |greenday89      |512345610000   |cobain   |63     |Product not found.              |
|farras@alterra.id |greenday89      |512345610000   |@@@      |63     |Product not found.              |
|farras@alterra.id |greenday89      |512345610000   |010      |63     |Product not found.              |
|farras@alterra.id |greenday89      |999999999      |614      |       |                                |
|farras@alterra.id |greenday89      |               |         |40     |No Pelanggan salah/ belum aktif.|
|farras@alterra.id |greenday89      |               |614      |40     |No Pelanggan salah/ belum aktif.|
|farras@alterra.id |greenday89      |hahahaha       |614      |       |                                |
|farras@alterra.id |greenday89      |*&alta&*       |614      |       |                                |

Scenario: Add Cart Gagal (invalid product id / type)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan customer number dan product id yang valid untuk proses inquiry
And Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add dan mendapat <rescode> dan <pesan>
Then Proses cart add gagal

Examples:
|email             |password        |customerNumber |productId|type          |rescode|pesan                                      |
|farras@alterra.id |greenday89      |512345610000   |155      |pln_postpaid  |63     |Product not found.                         |
|farras@alterra.id |greenday89      |512345610000   |         |pln_postpaid  |63     |Product not found.                         |
|farras@alterra.id |greenday89      |512345610000   |614      |              |63     |Product not found.                         |
|farras@alterra.id |greenday89      |512345610000   |614      |pln_prepaid   |63     |Product not found.                         |
|farras@alterra.id |greenday89      |11111          |614      |pln_postpaid  |41     |Server Cut-off.                            |
|farras@alterra.id |greenday89      |               |614      |pln_postpaid  |99     |Customer number is required for this order.|

Scenario: Process Gagal (invalid payment method)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Masukkan metode <pembayaran> invalid dan mendapat <rescode> dan <pesan>
Then Proses gagal

Examples:
|email             |password        |customerNumber|productId|type         |pembayaran                 |rescode|pesan|
|farras@alterra.id |greenday89      |512345610000  |614      |pln_postpaid |pembayaran_via_dompet_taryo|00     |     |
|farras@alterra.id |greenday89      |512345610000  |614      |pln_postpaid |#<dompetTebal>#            |00     |     |

Scenario: Complete Select Payment Method Gagal (invalid order id)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode1> and <pesan1>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1>
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1>
And Mendapat <rescode> dan <pesan> setelah memasukkan <orderId> invalid
Then Proses complete select payment method gagal

Examples:
|email             |password        |customerNumber |productId|type        |pembayaran                   |cekCC|orderId |rescode|pesan                 |rescode1|pesan1|
|farras@alterra.id |greenday89      |512345610000   |614      |pln_postpaid|commerce_payment_atm_mandiri |no   |12      |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |512345610000   |614      |pln_postpaid|commerce_payment_atm_mandiri |no   |        |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |512345610000   |614      |pln_postpaid|commerce_payment_atm_mandiri |no   |cobain  |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |512345610000   |614      |pln_postpaid|commerce_payment_atm_mandiri |no   |*#alta#*|81     |Order Tidak Ditemukan.|00     |       |


Scenario: Transaksi PLN Postpaid dengan menggunakan sepulsa credit (sepulsa credit <= harga transaksi)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Pilih metode <pembayaran>, <cekCC> yang diinginkan untuk split dengan sepulsa kredit yang dimiliki
Then Proses transaksi PLN Postpaid terbayar dengan mendapat <rescode> dan <pesan>

Examples:
|email                |password    |customerNumber|productId|type       |pembayaran               |cekCC|rescode|pesan|
|rakaditya@alterra.id |rakaganteng |512345610000  |614     |pln_postpaid|rules_bca_virtual_account|no   |00     |     |

Scenario: Transaksi PLN Postpaid dengan menggunakan sepulsa credit (sepulsa credit = 0)
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Cek metode pembayaran yang tersedia
Then Tidak dapat melakukan pembayaran menggunakan sepulsa credit, karena dana yang dimiliki 0 serta mendapat <rescode> dan <pesan>

Examples:
|email            |password   |customerNumber|productId|type        |rescode|pesan|
|farras@alterra.id|greenday89 |512345610000  |614      |pln_postpaid|00     |     |

Scenario: Transaksi Pembayaran PLN Postpaid Mandiri(Login)
Meta:
@PLNPostpaidSuksesMandiri
Given User sudah login dengan <email> dan <password>
And Sudah di halaman pembayaran PLN Postpaid
When Masukkan <customerNumber> dan <productId> untuk proses inquiry dan mendapat <rescode> dan <pesan>
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan>
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan>
Then Proses pembayaran tagihan PLN Postpaid terbayarkan dan mendapat <rescode> dan <pesan>

Examples:
|email                 |password        |customerNumber|productId|type        |pembayaran                         |cekCC|rescode|pesan|
|farras@alterra.id     |greenday89      |512345610000  |614      |pln_postpaid|commerce_payment_atm_mandiri       |no   |00     |     |

