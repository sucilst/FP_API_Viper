Meta: Register Akun

Narrative:
Given user sudah di halaman register
When input <phoneNumber> yang valid dan belum terdaftar
And input <fullName>
And input <email> yang valid dan belum terdaftar
And input <password>
And input <serial>
And input <agent>
Then mendapat status code 200, response code 00 dan response body data access token untuk proses request OTP

Examples:
|phoneNumber|fullName|email|password|serial|agent|
|081259104836|Farras Herdicho|farras@alterra.id|greenday89|abc12345|android|