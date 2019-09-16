Meta:
@TransaksiBPJS

Narrative:
Sebagai user
Saya ingin melakukan transaksi pembayaran BPJS
Sehingga tagihan transaksi BJS terbayarkan

Meta:
@TransaksiBPJSSukses
Scenario: Transaksi Pembayaran BPJS (Login)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode> dan <pesan> (BPJS)
Then Proses pembayaran tagihan BPJS terbayarkan dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email                 |password        |customerNumber   |productId|paymentPeriod|type        |pembayaran                         |cekCC|rescode|pesan|
|tester-tara@gmail.com |sepulsa123      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri        |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |0000001430071807 |383      |01           |bpjs_kesehatan|rules_bca_virtual_account           |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |0000001430071807 |383      |01           |bpjs_kesehatan|rules_permata_virtual_account       |no   |00     |     |
|tester-tara@gmail.com |sepulsa123      |0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_commerce_veritrans |cc   |00     |     |
|taratester02@gmail.com|testersepulsa123|0000001430071807 |383      |01           |bpjs_kesehatan|commerce_payment_payment_commerce_2 |sc   |00     |     |

Scenario: Inquiry Gagal (invalid customer number / product id)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> dan <productId> (invalid) untuk proses inquiry (BPJS)
Then Inquiry gagal dan mendapat <rescode> dan <pesan1> atau <pesan2> (BPJS)

Examples:
|email                 |password        |customerNumber  |productId|rescode|pesan1                                       |pesan2                     |
|tester-tara@gmail.com |sepulsa123      |1234            |383      |00     |                                             |406 Invalid parameter value|
|tester-tara@gmail.com |sepulsa123      |                |383      |00     |                                             |406 Invalid parameter value|
|tester-tara@gmail.com |sepulsa123      |###alta***      |383      |42     |Nomor tidak terdaftar dalam database billing.|                           |
|tester-tara@gmail.com |sepulsa123      |0000001430071802|09       |99     |Product not found.                           |                           |
|tester-tara@gmail.com |sepulsa123      |0000001430071802|<alta>   |99     |Product not found.                           |                           |
|tester-tara@gmail.com |sepulsa123      |0000001430071802|         |99     |Product not found.                           |                           |
|tester-tara@gmail.com |sepulsa123      |                |         |99     |Product not found.                           |                           |

Scenario: Add Cart Gagal (invalid product id, customer number / type)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> dan <productId> dan <type> (invalid) untuk proses cart add (BPJS)
Then Proses cart add gagal dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email                 |password        |customerNumber  |productId|type           |rescode|pesan                                      |
|tester-tara@gmail.com |sepulsa123      |0000001430071802|55       |bpjs_kesehatan |63     |Product not found.                         |
|tester-tara@gmail.com |sepulsa123      |0000001430071802|         |bpjs_kesehatan |63     |Product not found.                         |
|tester-tara@gmail.com |sepulsa123      |11111111111     |383      |               |63     |Product not found.                         |
|tester-tara@gmail.com |sepulsa123      |                |383      |bpjs_kesehatan |99     |Customer number is required for this order.|
|tester-tara@gmail.com |sepulsa123      |25252525252     |383      |pln_prepaid    |63     |Product not found.                         |
|tester-tara@gmail.com |sepulsa123      |                |155      |bpjs_kesehatan |63     |Product not found.                         |

Scenario: Process Gagal (invalid payment method)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode> dan <pesan> (BPJS)
And Masukkan metode <pembayaran> invalid (BPJS)
Then Proses gagal dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email                 |password        |customerNumber     |productId|paymentPeriod|type           |pembayaran                 |rescode|pesan|
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan |                           |00     |     |
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan |pembayaran_via_dompet_taryo|00     |     |
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan |#<dompetTebal>#            |00     |     |

Scenario: Complete Select Payment Method Gagal (invalid order id)
Given User sudah login dengan <email> dan <password> (BPJS)
And Sudah di halaman pembayaran BPJS (BPJS)
When Masukkan <customerNumber> , <productId> dan <paymentPeriod> untuk proses inquiry dan mendapat <rescode1> dan <pesan1> (BPJS)
And Masukkan <customerNumber> dan <productId> dan <type> untuk proses cart add dan mendapat <rescode1> and <pesan1> (BPJS)
And Pilih metode <pembayaran> dan <cekCC> dan mendapat <rescode1> and <pesan1> (BPJS)
And Memasukkan <orderId> invalid (BPJS)
Then Proses complete select payment method gagal dan mendapat <rescode> dan <pesan> (BPJS)

Examples:
|email                 |password        |customerNumber     |productId|paymentPeriod|type          |pembayaran                  |cekCC|orderId |rescode|pesan                 |rescode1|pesan1|
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |12      |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |        |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |cobain  |81     |Order Tidak Ditemukan.|00     |       |
|tester-tara@gmail.com |sepulsa123      |0000001430071807   |383      |01           |bpjs_kesehatan|commerce_payment_atm_mandiri |no   |*#alta#*|81     |Order Tidak Ditemukan.|00     |       |
