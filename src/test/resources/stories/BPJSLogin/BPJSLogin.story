Meta:
@bpjs

Narrative:
Sebagai user
Saya ingin melakukan transaksi pembayaran BPJS
Sehingga tagihan transaksi BJS terbayarkan

Scenario: Transaksi Pembayaran BPJS (Login)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan> (BPJS)
Then Proses pembayaran tagihan BPJS terbayarkan dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email                  |password        |customerNumber   |productId|paymentPeriod|type        |pembayaran                            |cekCC|rescode|pesan|
|farras@alterra.id      |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri        |no   |00     |     |
|farras@alterra.id      |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|rules_bca_virtual_account           |no   |00     |     |
|farras@alterra.id      |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|rules_permata_virtual_account       |no   |00     |     |
|farras@alterra.id      |greenday89      |0000001430071803 |383      |01           |bpjs_kesehatan|commerce_payment_commerce_veritrans |cc   |00     |     |
|taratester02@gmail.com |testersepulsa123|0000001430071804 |383      |01           |bpjs_kesehatan|commerce_payment_payment_commerce_2 |sc   |00     |     |

Scenario: Inquiry Gagal (invalid customer number / product id)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> dan <productId> dan <paymentPeriod> (invalid) untuk proses inquiry (BPJS)
Then Inquiry gagal dan mendapat <rescode> dan <pesan1> atau <pesan2> (BPJS)

Examples:
|email             |password        |customerNumber  |productId|paymentPeriod|rescode|pesan1            |pesan2                     |
|farras@alterra.id |greenday89      |0000001430071807|         |01           |99     |Product not found.|                           |
|farras@alterra.id |greenday89      |0000001430071807|cobain   |01           |99     |Product not found.|                           |
|farras@alterra.id |greenday89      |0000001430071807|@@@      |01           |99     |Product not found.|                           |
|farras@alterra.id |greenday89      |0000001430071807|010      |01           |99     |Product not found.|                           |
|farras@alterra.id |greenday89      |999999999       |383      |01           |00     |                  |406 Invalid parameter value|
|farras@alterra.id |greenday89      |                |         |01           |99     |Product not found.|                           |
|farras@alterra.id |greenday89      |                |383      |01           |00     |                  |406 Invalid parameter value|
|farras@alterra.id |greenday89      |hahahaha        |383      |01           |00     |                  |406 Invalid parameter value|
|farras@alterra.id |greenday89      |*&alta&*        |383      |01           |00     |                  |406 Invalid parameter value|
|farras@alterra.id |greenday89      |0000001430071807|383      |             |00     |                  |406 Invalid parameter value|
|farras@alterra.id |greenday89      |0000001430071807|383      |coba         |00     |                  |406 Invalid parameter value|
|farras@alterra.id |greenday89      |0000001430071807|383      |$coba$       |00     |                  |406 Invalid parameter value|

Scenario: Add Cart Gagal (invalid product id, customer number / type)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan customer number, payment period dan product id yang valid untuk proses inquiry (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add (BPJS)
Then Proses cart add gagal dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email             |password        |customerNumber  |productId|type           |rescode|pesan                                      |
|farras@alterra.id |greenday89      |0000001430071807|155      |bpjs_kesehatan |63     |Product not found.                         |
|farras@alterra.id |greenday89      |0000001430071807|         |bpjs_kesehatan |63     |Product not found.                         |
|farras@alterra.id |greenday89      |0000001430071807|383      |               |63     |Product not found.                         |
|farras@alterra.id |greenday89      |0000001430071807|383      |pln_prepaid    |63     |Product not found.                         |
|farras@alterra.id |greenday89      |11111           |383      |bpjs_kesehatan |41     |Server BPJS Cut-off.                       |
|farras@alterra.id |greenday89      |                |383      |bpjs_kesehatan |99     |Customer number is required for this order.|

Scenario: Process Gagal (invalid payment method)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan metode <pembayaran> invalid (BPJS)
Then Proses gagal dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email             |password        |customerNumber   |productId|paymentPeriod|type           |pembayaran                 |rescode|pesan|
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan |                           |00     |     |
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan |pembayaran_via_dompet_taryo|00     |     |
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan |#<dompetTebal>#            |00     |     |

Scenario: Complete Select Payment Method Gagal (invalid order id)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode1> dan <pesan1> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1> (BPJS)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1> (BPJS)
And Memasukkan <orderId> invalid (BPJS)
Then Proses complete select payment method gagal dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email             |password        |customerNumber   |productId|paymentPeriod|type          |pembayaran                  |cekCC|orderId |rescode|pesan                  |rescode1|pesan1|
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |12      |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |        |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |cobain  |81     |Order Tidak Ditemukan.|00     |       |
|farras@alterra.id |greenday89      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |*#alta#*|81     |Order Tidak Ditemukan.|00     |       |

Scenario: Transaki BPJS dengan menggunakan sepulsa credit (sepulsa credit <= harga transaksi)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)
And Pilih metode <pembayaran>, <cekCC> yang diinginkan untuk split dengan sepulsa kredit yang dimiliki (BPJS)
Then Proses transaksi PLN Postpaid terbayar dengan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email                |password    |customerNumber   |productId|paymentPeriod|type          |pembayaran               |cekCC|rescode|pesan|
|rakaditya@alterra.id |rakaganteng |0000001430071807 |383      |01           |bpjs_kesehatan|rules_bca_virtual_account|no   |00     |     |

Scenario: Transaki BPJS dengan menggunakan sepulsa credit (sepulsa credit = 0)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)
And Cek metode pembayaran yang tersedia (BPJS)
Then Tidak dapat melakukan pembayaran menggunakan sepulsa credit, karena dana yang dimiliki 0 serta mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email            |password   |customerNumber   |productId|paymentPeriod|type          |rescode|pesan|
|farras@alterra.id|greenday89 |0000001430071807 |383      |01           |bpjs_kesehatan|00     |     |


