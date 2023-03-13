# FAKE Store API

<a href="https://fakestoreapi.com/">FakeStoreAPI</a>

- 이 프로젝트는 FakeStoreAPI를 스프링 부트를 통해 제작합니다.
- 완벽하게 똑같진 않으며, 미국 주소를 한국 주소로 변경하는 등의 수정이 이루어질 계획입니다.

# 📌 사용 기술

- Spring Boot
- Spring Data JPA
- MariaDB
- Spring Security (적용 예정)
- JWT (적용 예정)
- Swagger-UI


# 📌 DB 설계

![image](https://user-images.githubusercontent.com/64225078/224736049-21d91c8b-dd97-4232-b303-6ddffafcb946.png)



# 📌 자원 식별(URI 정의)


## 상품 전체 조회 

### GET /Products

```json
[
{
"pid":1,
"title":"shirt",
"price":10000,
"description":"man's clothing description",
"category":"MAN_CLOTHING",
"image":"shirt.jpg",
"rating":{
"rate":3.2,
"count":100
}
},
{
"pid":2,
"title":"MAN_CLOTHING0",
"price":0,
"description":"MAN_CLOTHINGdescription",
"category":"MAN_CLOTHING",
"image":"MAN_CLOTHING0.jpg",
"rating":{
"rate":0.0,
"count":0
}
},
....

```

## 상품별 조회

### GET /product/{pid}

```json
{
"pid":1,
"title":"shirt",
"price":10000,
"description":"man's clothing description",
"category":"MAN_CLOTHING",
"image":"shirt.jpg",
"rating":{
"rate":3.2,
"count":100
}
}
```

## 제한된 개수별 상품 조회

### GET /product?limit={number}

```json
[
{
"pid":1,
"title":"shirt",
"price":10000,
"description":"man's clothing description",
"category":"MAN_CLOTHING",
"image":"shirt.jpg",
"rating":{
"rate":3.2,
"count":100
}
},
{
"pid":2,
"title":"MAN_CLOTHING0",
"price":0,
"description":"MAN_CLOTHINGdescription",
"category":"MAN_CLOTHING",
"image":"MAN_CLOTHING0.jpg",
"rating":{
"rate":0.0,
"count":0
}
},
{
"pid":3,
"title":"WOMAN_CLOTHING1",
"price":1000,
"description":"WOMAN_CLOTHINGdescription",
"category":"WOMAN_CLOTHING",
"image":"WOMAN_CLOTHING1.jpg",
"rating":{
"rate":1.0,
"count":1
}
}
]
```

## 상품 정렬 후 조회

### GET /products?sort=desc

```json
[
{
"pid":101,
"title":"CAP99",
"price":99000,
"description":"CAPdescription",
"category":"CAP",
"image":"CAP99.jpg",
"rating":{
"rate":9.0,
"count":99
}
},
{
"pid":100,
"title":"BACKPACK98",
"price":98000,
"description":"BACKPACKdescription",
"category":"BACKPACK",
"image":"BACKPACK98.jpg",
"rating":{
"rate":8.0,
"count":98
}
},
....
```

## 카테고리 조회

### GET /products/categories

```json
["mansclothing","womansclothing","backpack","cap"]
```

## 카테고리별 품목 조회

### GET /products/category/{category}

```json
[
{
"pid":1,
"title":"shirt",
"price":10000,
"description":"man's clothing description",
"category":"MAN_CLOTHING",
"image":"shirt.jpg",
"rating":{
"rate":3.2,
"count":100
}
},
{
"pid":2,
"title":"MAN_CLOTHING0",
"price":0,
"description":"MAN_CLOTHINGdescription",
"category":"MAN_CLOTHING",
"image":"MAN_CLOTHING0.jpg",
"rating":{
"rate":0.0,
"count":0
}
},

...
```

## 품목 입력

### POST /products

RequestBody

```json
{
  "category": "mansclothing",
  "description": "mansclothing update description",
  "image": "mansclothing.jpg",
  "price": 10000,
  "title": "mansclothing update"
}
```

ResponseBody

```json
{
  "pid": null,
  "title": "mansclothing update",
  "price": 10000,
  "description": "mansclothing update description",
  "category": "mansclothing",
  "image": "mansclothing.jpg",
  "rating": {
    "rate": null,
    "count": null
  }
}
```

## 품목 변경

### PUT /products

```json
{
  "pid" : 3,
  "category": "mansclothing",
  "description": "mansclothing update description",
  "image": "mansclothing.jpg",
  "price": 10000,
  "title": "mansclothing update"
}
```

```json
{
  "pid": 3,
  "title": "mansclothing update",
  "price": 10000,
  "description": "mansclothing update description",
  "category": "mansclothing",
  "image": "mansclothing.jpg",
  "rating": {
    "rate": null,
    "count": null
  }
}
```

## 품목 삭제

### DELETE /products/{pid}

```json
{
  "pid": 100,
  "title": "BACKPACK98",
  "price": 98000,
  "description": "BACKPACKdescription",
  "category": null,
  "image": "BACKPACK98.jpg",
  "rating": {
    "rate": 8,
    "count": 98
  }
}
```

# 📌 User

## 회원 전체 조회

### GET /users

ResponseBody

```json
[
  {
    "uid": 2,
    "email": "user1@naver.com",
    "username": "user1",
    "password": "$2a$10$4smbkzsHB3FpAOmJzERebuSAuBn3ebWTrLjKYFRcxbOMEW8WR6Kcu",
    "joindate": [
      2023,
      3,
      13
    ],
    "phone": "010-4450-9299",
    "name": {
      "firstname": "편",
      "lastname": "도월"
    },
    "address": {
      "zipcode": "30703",
      "address": "인천광역시",
      "latitude": 0.15274496147532424,
      "longitude": 0.6167202568436756
    }
  },
...
```

## 개별 회원 조회

### GET /users/{uid}

ResponseBody

```json
{
  "uid": 2,
  "email": "user1@naver.com",
  "username": "user1",
  "password": "$2a$10$4smbkzsHB3FpAOmJzERebuSAuBn3ebWTrLjKYFRcxbOMEW8WR6Kcu",
  "joindate": [
    2023,
    3,
    13
  ],
  "phone": "010-4450-9299",
  "name": {
    "firstname": "편",
    "lastname": "도월"
  },
  "address": {
    "zipcode": "30703",
    "address": "인천광역시",
    "latitude": 0.15274496147532424,
    "longitude": 0.6167202568436756
  }
}
```

## 회원수 별 조회

### GET /users?limit={number}

ResponseBody

```json
[
  {
    "uid": 2,
    "email": "user1@naver.com",
    "username": "user1",
    "password": "$2a$10$4smbkzsHB3FpAOmJzERebuSAuBn3ebWTrLjKYFRcxbOMEW8WR6Kcu",
    "joindate": [
      2023,
      3,
      13
    ],
    "phone": "010-4450-9299",
    "name": {
      "firstname": "편",
      "lastname": "도월"
    },
    "address": {
      "zipcode": "30703",
      "address": "인천광역시",
      "latitude": 0.15274496147532424,
      "longitude": 0.6167202568436756
    }
  },
  {
    "uid": 3,
    "email": "user2@naver.com",
    "username": "user2",
    "password": "$2a$10$tXXejpGE2f8t48930mKKoeyYFcEPdWIC0b2Y2.NtBpYsUMTBA5/96",
    "joindate": [
      2023,
      3,
      13
    ],
    "phone": "010-5623-0312",
    "name": {
      "firstname": "금",
      "lastname": "린오"
    },
    "address": {
      "zipcode": "11965",
      "address": "울산광역시",
      "latitude": 0.615849859140715,
      "longitude": 0.9337544027188417
    }
  }

...
```

## 회원 정렬 후 조회

### GET /users?sort=desc

ResponseBody

```json
[
  {
    "uid": 102,
    "email": "123@123.com",
    "username": "username00",
    "password": "1234",
    "joindate": [
      2023,
      3,
      13
    ],
    "phone": "010-0000-0000",
    "name": {
      "firstname": null,
      "lastname": null
    },
    "address": {
      "zipcode": null,
      "address": null,
      "latitude": null,
      "longitude": null
    }
  },
  {
    "uid": 101,
    "email": "user0@naver.com",
    "username": "user0",
    "password": "$2a$10$I50jBgMF6.4mpI6OEYaiN.F0oWRjnJXZNKGrBpZpnSz9vzoN.IIfG",
    "joindate": [
      2023,
      3,
      13
    ],
    "phone": "010-1069-0170",
    "name": {
      "firstname": "홍",
      "lastname": "령혼"
    },
    "address": {
      "zipcode": "81947",
      "address": "광주광역시",
      "latitude": 0.841411071415164,
      "longitude": 0.6525690452565338
    }
  },
  ...
```

## 회원 등록 (수정중)

### POST /users

```json

```

## 회원 삭제

### DELETE /users/{uid}

ResponseBody

```json
{
  "uid": 2,
  "email": "user1@naver.com",
  "username": "user1",
  "password": "$2a$10$4smbkzsHB3FpAOmJzERebuSAuBn3ebWTrLjKYFRcxbOMEW8WR6Kcu",
  "joindate": [
    2023,
    3,
    13
  ],
  "phone": "010-4450-9299",
  "name": {
    "firstname": "편",
    "lastname": "도월"
  },
  "address": {
    "zipcode": "30703",
    "address": "인천광역시",
    "latitude": 0.15274496147532424,
    "longitude": 0.6167202568436756
  }
}
```


...진행중(22.3.14)
