Meta: Login Akun

Narrative:
Sebagai user
Saya ingin login ke website sepulsa
Sehingga saya bisa melakukan transaksi di website sepulsa

Scenario: Buat Akun Valid
Given User sudah berada di halaman awal
When Mengisi field <username> dan <password> untuk login
Then Berhasil masuk ke website sepulsa

Examples:
|username|password|
|farras@alterra.id|greenday89|
||greenday89|
|farras@alterra.id||
|||
|rakaditya@gmail.com|ganteng|
|rakacakep@gmail.com|alterra1997|
|rakacakep@gmail.com|ganteng|