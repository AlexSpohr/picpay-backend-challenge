Challenge - PicPay backend
-------------------------------------------------------------------------------------------------------


### Developer - Alex Spohr

<table>
  <tr>
    <td>E-mail</td>
    <td>GitHub</td>
  </tr>
   <tr>
    <td>alexspohr436@gmail.com</td>
    <td>AlexSpohr</td>
  </tr>
</table>

-------------------------------------------------------------------------------------------------------

### Technologies Used
<table>
  <tr>
    <td>Java</td>
    <td>Spring</td>
    <td>MySql</td>
  </tr>
  <tr>
    <td>21.*</td>
    <td>3.3.1</td>
    <td>8.0</td>
  </tr>
</table>

-------------------------------------------------------------------------------------------------------


### Endpoints MS User
#### Transfer:
```
POST /transfer
Content-Type: application/json

{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
```
#### Create Wallet:
```
POST /wallets
Content-Type: application/json
{
    "fullName": "name",
    "cpfCnpj": "123456789",
    "email": "email@teste.com",
    "password": "123",
    "walletType": "USER" or "MERCHANT"
}
```




