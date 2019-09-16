Meta:
@Register

Narrative:
Sebagai user
Saya ingin melakukan registrasi akun baru di website sepulsa
Sehingga saya berhasil mendaftar dan melakukan berbagai transaksi di website sepulsa

Scenario: Registrasi Sukses
Given user sudah di halaman register
When input <phoneNumber> yang valid dan belum terdaftar dan input <fullName> dan input <email> yang valid dan belum terdaftar dan input <password> dan input <serial> dan input <agent> lalu mendapat <rescode> dan <pesan>
And tunggu kode OTP terkirim setelah input <phoneNumber>, <serial> dan <agent> ke user lalu mendapat <rescode> dan <pesan>
And input kode OTP
Then registrasi akun baru berhasil, serta mendapat <rescode> dan <pesan>

Examples:
|phoneNumber |fullName |email               |password   |serial  |agent  |rescode|pesan|
|081242504777|Difa Raka|rakaditya@alterra.id|rakaganteng|abc12345|android|00     |     |