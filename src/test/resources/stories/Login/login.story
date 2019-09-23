Meta:
@Login

Narrative:
Sebagai user
Saya ingin login ke website sepulsa
Sehingga saya bisa melakukan transaksi di website sepulsa

Scenario: Login Akun Sukses
Given User sudah berada di halaman login
When Mengisi field <username> dan <password> untuk login
Then Berhasil login dengan mendapat <rescode> dan <pesan>

Examples:
|username             |password   |rescode|pesan|
|farras@alterra.id    |greenday89 |00     |     |
|081259104836         |greenday89 |00     |     |


Scenario: Login Akun Gagal (invalid username / password)
Given User sudah berada di halaman login
When Mengisi field <username> dan <password> invalid untuk login
Then Gagal login dengan mendapat <rescode> dan <pesan>

Examples:
|username             |password   |rescode|pesan                     |
|                     |greenday89 |24     |Email atau password salah.|
|farras@alterra.id    |           |24     |Email atau password salah.|
|                     |           |24     |Email atau password salah.|
|rakaditya@alterra.id |ganteng    |24     |Email atau password salah.|
|rakaditya@gmail.id   |rakaganteng|24     |Email atau password salah.|
|rakaditya@gmail.id   |ganteng    |24     |Email atau password salah.|
|081259104836         |ganteng    |24     |Email atau password salah.|
|081111111111         |greenday89 |24     |Email atau password salah.|
|081111111111         |ganteng    |24     |Email atau password salah.|