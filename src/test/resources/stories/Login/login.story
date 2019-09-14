Meta: Login Akun

Narrative:
Sebagai user
Saya ingin login ke website sepulsa
Sehingga saya bisa melakukan transaksi di website sepulsa

Scenario: Login Akun
Given User sudah berada di halaman awal
When Mengisi field <username> dan <password> untuk login
Then Berhasil login dengan mendapat <rescode> dan <pesan>

Examples:
|username             |password   |rescode|pesan                             |
|tester-tara@gmail.com|sepulsa123 |00     |                                  |
|                     |greenday89 |86     |Akun di block. Silahkan kontak CS.|
|farras@alterra.id    |           |24     |Email atau password salah.        |
|                     |           |86     |Akun di block. Silahkan kontak CS.|
|rakaditya@gmail.com  |ganteng    |24     |Email atau password salah.        |
|rakacakep@gmail.com  |alterra1997|24     |Email atau password salah.        |
|rakacakep@gmail.com  |ganteng    |24     |Email atau password salah.        |
