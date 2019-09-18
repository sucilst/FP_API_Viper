Meta:

Narrative:
As a user
I want to membeli pulsa dengan VA
So that saya bisa mendapatkan pulsa dengan menggunakan VA saya

Scenario: sukses membeli pulsa (Sign In)
Given user berada pada halaman utama sepulsa (Sign In)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And memilih <pembayaran> yang diinginkan
And melakukan pembayaran
Then user mendapatkan informasi No VA <pembayaran> yang sesuai untuk membayar

Examples:
|product_id|no_hp|pembayaran|
|5|081234000001|commerce_payment_atm_mandiri|
|6|081234000001|rules_bca_virtual_account|
|483|081234000001|rules_permata_virtual_account|

Scenario: sukses membeli pulsa (Sign In) (Sepulsa Kredit Cukup)
Given user berada pada halaman utama sepulsa (Sign In) (Sepulsa Kredit)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And memilih <pembayaran> dengan Sepulsa Kredit
And melakukan pembayaran
Then user mendapatkan informasi <pembayaran> sukses (Sepulsa Kredit)

Examples:
|product_id|no_hp|pembayaran|
|6|081234000001|sk|

Scenario: sukses membeli pulsa (Sign In) (Sepulsa Kredit Kurang)
Given user berada pada halaman utama sepulsa (Sign In) (Sepulsa Kredit)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And memilih <pembayaran> dengan Sepulsa Kredit
And melakukan pembayaran
Then user mendapatkan informasi <pembayaran> sukses (Sepulsa Kredit)

Examples:
|product_id|no_hp|pembayaran|
|516|081234000001|commerce_payment_atm_mandiri|

Scenario: gagal membeli pulsa (Sign In) (Sepulsa Kredit 0)
Given user berada pada halaman utama sepulsa (Sign In)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
Then tidak ada opsi pembayaran dengan Sepulsa Kredit (Sepulsa Kredit)

Examples:
|product_id|no_hp|
|5|081234000001|

Scenario: sukses membeli pulsa (Sign In) (Credit Card)
Given user berada pada halaman utama sepulsa (Sign In)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And memilih <pembayaran> dengan Credit Card
And melakukan pembayaran
Then user mendapatkan informasi <pembayaran> sukses (Credit Card)

Examples:
|product_id|no_hp|pembayaran|
|6|081234000001|cc|

Scenario: gagal membeli pulsa (Sign in) (rescode 63)
Given user berada pada halaman utama sepulsa (Sign In)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli (63)
Then user mendapatkan notifikasi product not found

Examples:
|product_id|no_hp|pembayaran|
|0000|081234000001|commerce_payment_atm_mandiri|

Scenario: gagal membeli pulsa (Sign in) (rescode 81)
Given user berada pada halaman utama sepulsa (Sign In)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And memilih <pembayaran> yang diinginkan
And melakukan pembayaran
Then user mendapatkan notifikasi Order Tidak Ditemukan (81)

Examples:
|product_id|no_hp|pembayaran|
|6|081234000001|commerce_payment_atm_mandiri|

Scenario: gagal membeli pulsa (Sign in) (rescode 83)
Given user berada pada halaman utama sepulsa (Sign In) (Sepulsa Kredit)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And melakukan pembayaran
Then user mendapatkan notifikasi <pembayaran> is Invalid (83)

Examples:
|product_id|no_hp|pembayaran|
|6|081234000001|commerce_payment_atm_mandiri|

Scenario: gagal membeli pulsa (Sign in) (payment_method invalid)
Given user berada pada halaman utama sepulsa (Sign In)
And membuka halaman pembelian pre-paid
When user input type pulsa, <no_hp>, dan <product_id> yang ingin dibeli
And memilih <pembayaran> yang diinginkan (payment_method invalid)
And melakukan pembayaran
Then metode pembayaran tidak terbaca

Examples:
|product_id|no_hp|pembayaran|
|6|081234000001|bayarpakeapaya|