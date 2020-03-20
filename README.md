# technest

hi Sara, Jaime,
<br>here you have the technical challenge you sent me this morning
<br>
<br>I've used eclipse
<br>I've done 4 unit tests
<br>
<br>For running this project follow these steps :
- clone the repo using `git clone https://github.com/manuelyt/technest.git`
- `cd technest`
- `mvn spring-boot:run`
- and now, using postman client for example you can access rest api in `http://localhost:8080/account/`

<br>in ACCOUNT you have 4 methods: get, post, put, delete
<br>in TRANSFER you have 1 methods: post
<br>
<br>you can add an account using ACCOUNT POST with this sample content :

`{"name":"ac99","currency":"EUR","balance":99.99,"treasury":false}`

<br>you can transfer money from one account to other using TRANSFER POST with this sample content :

`{"from":"ac5","to":"work","balance":30}`
