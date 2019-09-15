Meta: Register Akun

Narrative:
Given user sudah di halaman register
When input <phoneNumber> yang valid dan belum terdaftar dan input <fullName> dan input <email> yang valid dan belum terdaftar dan input <password> dan input <serial> dan input <agent>
And tunggu kode OTP terkirim ke user
And input kode OTP
Then mendapat status code 200, response code 00 dan response body data access token untuk proses request OTP

Examples:
|phoneNumber|fullName|email|password|serial|agent|
|081259104837|Farras Herdicho0|farras@alterraa.id|greenday89|abc12345|android|