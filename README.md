# FAKE Store API With Spring Boot

<a href="https://fakestoreapi.com/">FakeStoreAPI</a>

- 이 프로젝트는 FakeStoreAPI를 스프링 부트를 통해 제작합니다.
- 미국 주소를 한국 주소로 변경하는 등의 수정이 이루어질 계획입니다.

# 📌 사용 기술

- Spring Boot
- Spring Data JPA
- MariaDB
- Spring Security
- JWT
- Swagger-UI

# 📌 DB 설계

![img.png](img.png)

# 📌 자원 식별(URI 정의)

## 상품 전체 조회

### GET /Products

```json
[
  {
    "pid": 1,
    "title": "mansclothing0",
    "price": 0,
    "description": "mansclothing description",
    "category": "mansclothing",
    "image": "mansclothing0.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  {
    "pid": 2,
    "title": "womansclothing1",
    "price": 1000,
    "description": "womansclothing description",
    "category": "womansclothing",
    "image": "womansclothing1.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  ...

```

## 상품별 조회

### GET /product/{pid}

```json
{
  "pid": 1,
  "title": "mansclothing0",
  "price": 0,
  "description": "mansclothing description",
  "category": "mansclothing",
  "image": "mansclothing0.jpg",
  "rating": {
    "rate": 0.0,
    "count": 0
  }
}
```

## 제한된 개수별 상품 조회

### GET /product?limit={number}

```json
[
  {
    "pid": 1,
    "title": "mansclothing0",
    "price": 0,
    "description": "mansclothing description",
    "category": "mansclothing",
    "image": "mansclothing0.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  {
    "pid": 2,
    "title": "womansclothing1",
    "price": 1000,
    "description": "womansclothing description",
    "category": "womansclothing",
    "image": "womansclothing1.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  {
    "pid": 3,
    "title": "backpack2",
    "price": 2000,
    "description": "backpack description",
    "category": "backpack",
    "image": "backpack2.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  }
]
```

## 상품 내림차순 정렬 조회

### GET /products?sort=desc

```json
[
  {
    "pid": 100,
    "title": "etc99",
    "price": 99000,
    "description": "etc description",
    "category": "etc",
    "image": "etc99.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  ...
]
```

## 카테고리 조회

### GET /products/categories

```json
[
  "mansclothing",
  "womansclothing",
  "backpack",
  "cap",
  "etc"
]
```

## 카테고리별 품목 조회

### GET /products/category/{category}

```json
[
  {
    "pid": 1,
    "title": "mansclothing0",
    "price": 0,
    "description": "mansclothing description",
    "category": "mansclothing",
    "image": "mansclothing0.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  {
    "pid": 6,
    "title": "mansclothing5",
    "price": 5000,
    "description": "mansclothing description",
    "category": "mansclothing",
    "image": "mansclothing5.jpg",
    "rating": {
      "rate": 0.0,
      "count": 0
    }
  },
  ...
```

## 품목 입력

### POST /products

- RequestBody

```json
{
  "category": "mansclothing",
  "description": "mansclothing description",
  "image": "mansclothing.jpg",
  "price": 2000,
  "title": "new mansclothing"
}
```

- ResponseBody

```json
{
  "pid": 102,
  "title": "new mansclothing",
  "price": 2000,
  "description": "mansclothing description",
  "category": "mansclothing",
  "image": "mansclothing.jpg",
  "rating": null
}
```

## 품목 변경

### PUT /products

- RequestBody

```json
{
  "pid": 102,
  "title": "new mansclothing",
  "price": 2000,
  "description": "mansclothing description",
  "category": "mansclothing",
  "image": "mansclothing.jpg",
  "rating": {
    "rate": 5.0,
    "count": 1
  }
}
```

- ResponseBody

```json
{
  "pid": 102,
  "title": "new mansclothing",
  "price": 2000,
  "description": "mansclothing description",
  "category": "mansclothing",
  "image": "mansclothing.jpg",
  "rating": {
    "rate": 5,
    "count": 1
  }
}
```

## 품목 삭제

### DELETE /products/{pid}

```json
{
  "pid": 2,
  "title": "womansclothing1",
  "price": 1000,
  "description": "womansclothing description",
  "category": "womansclothing",
  "image": "womansclothing1.jpg",
  "rating": {
    "rate": 0,
    "count": 0
  }
}
```

# 📌 User

## 회원 전체 조회

### GET /users

- ResponseBody

```json
[
  {
    "uid": 1,
    "email": "user0@naver.com",
    "username": "user0",
    "password": "$2a$10$rxgx/YPSfoRVYFMl3Y69K.n6ioJtQc/LJJhXeZJYZf0lJ33qcF/BK",
    "joindate": "2023-03-18",
    "phone": "010-2904-0311",
    "name": {
      "firstname": "용",
      "lastname": "춘나"
    },
    "address": {
      "city": "대구광역시",
      "zipcode": "79944",
      "longitude": 0.2356067923714087,
      "latitude": 0.9567497751650677
    }
  },
  ...
```

## 개별 회원 조회

### GET /users/{uid}

- ResponseBody

```json
{
  "uid": 1,
  "email": "user0@naver.com",
  "username": "user0",
  "password": "$2a$10$rxgx/YPSfoRVYFMl3Y69K.n6ioJtQc/LJJhXeZJYZf0lJ33qcF/BK",
  "joindate": "2023-03-18",
  "phone": "010-2904-0311",
  "name": {
    "firstname": "용",
    "lastname": "춘나"
  },
  "address": {
    "city": "대구광역시",
    "zipcode": "79944",
    "longitude": 0.2356067923714087,
    "latitude": 0.9567497751650677
  }
}
```

## 회원수 별 조회

### GET /users?limit={number}

- ResponseBody

```json
[
  {
    "uid": 1,
    "email": "user0@naver.com",
    "username": "user0",
    "password": "$2a$10$rxgx/YPSfoRVYFMl3Y69K.n6ioJtQc/LJJhXeZJYZf0lJ33qcF/BK",
    "joindate": "2023-03-18",
    "phone": "010-2904-0311",
    ...
  },
  {
    "uid": 2,
    "email": "user1@naver.com",
    "username": "user1",
    "password": "$2a$10$kSC6UGHYW3eBsdXSeSJflO6Gr3/OHog.DOqzuImMmNp/KaYa77jTu",
    "joindate": "2023-03-18",
    "phone": "010-4017-9560",
    ...
  }
]

```

## 회원 정렬 후 조회

### GET /users?sort=desc

- ResponseBody

```json
[
  {
    "uid": 200,
    "email": "user99@naver.com",
    "username": "user99",
    "password": "$2a$10$Y/WBOk.UmFixDto9H6nbHO.KBIng7biN82u7fVl1YkqHNYen81gSW",
    "joindate": "2023-03-18",
    "phone": "010-9003-9791",
    "name": {
      "firstname": "편",
      "lastname": "박울"
    },
    "address": {
      "city": "광주광역시",
      "zipcode": "93042",
      "longitude": 0.3883513991620904,
      "latitude": 0.2876636584205424
    }
  },
  {
    "uid": 199,
    "email": "user98@naver.com",
    "username": "user98",
    "password": "$2a$10$AUImB.IdJdjX3FPCFl74K.V5jCJywT0zB5JhrA0ysFxT8F003dQgC",
    "joindate": "2023-03-18",
    "phone": "010-8351-4215",
    ...
  },
  ...
```

## 회원 등록

### POST /users

- RequestBody

```json
{
  "address": {
    "city": "인천광역시",
    "latitude": 0.345,
    "longitude": 3.241,
    "zipcode": "34536"
  },
  "email": "newUser@naver.com",
  "joindate": "2023-03-20",
  "name": {
    "firstname": "강",
    "lastname": "동원"
  },
  "password": <PASSWORD>,
  "phone": "010-0000-0000",
  "username": "user1011"
}

```

- ResponseBody

```json

{
  "uid": 202,
  "email": "newUser@naver.com",
  "username": "user1011",
  "password": "$2a$10$Jf8V0kEEw3pkVVblbaAOFeL3rY2oMKM4o2sMSXgO6sVPTHhNd722O",
  "joindate": "2023-03-20",
  "phone": "010-0000-0000",
  "name": {
    "firstname": "강",
    "lastname": "동원"
  },
  "address": {
    "city": "인천광역시",
    "zipcode": "34536",
    "longitude": 3.241,
    "latitude": 0.345
  }
}


```

## 회원 정보 수정

### PUT /users

- RequestBody

```json
{
  "uid": 202,
  "email": "updateUser@naver.com",
  "username": "user1011",
  "password": <PASSWORD>,
  "joindate": "2023-03-20",
  "phone": "010-0000-0000",
  "name": {
    "firstname": "강",
    "lastname": "동원"
  },
  "address": {
    "city": "인천광역시",
    "zipcode": "34536",
    "longitude": 3.241,
    "latitude": 0.345
  }
}
```

- ResponseBody

```json

{
  "uid": 202,
  "email": "updateUser@naver.com",
  "username": "user1011",
  "password": "$2a$10$hLwDEeByBviryRqdERm6uObdm8JJUODThLly23TZoXx67ZZtdp6L6",
  "joindate": "2023-03-20",
  "phone": "010-0000-0000",
  "name": {
    "firstname": "강",
    "lastname": "동원"
  },
  "address": {
    "city": "인천광역시",
    "zipcode": "34536",
    "longitude": 3.241,
    "latitude": 0.345
  }
}

```

## 회원 삭제

### DELETE /users/{uid}

- ResponseBody

```json
{
  "uid": 1,
  "email": "user0@naver.com",
  "username": "user0",
  "password": "$2a$10$rxgx/YPSfoRVYFMl3Y69K.n6ioJtQc/LJJhXeZJYZf0lJ33qcF/BK",
  "joindate": "2023-03-18",
  "phone": "010-2904-0311",
  "name": {
    "firstname": "용",
    "lastname": "춘나"
  },
  "address": {
    "city": "대구광역시",
    "zipcode": "79944",
    "longitude": 0.2356067923714087,
    "latitude": 0.9567497751650677
  }
}

```

# 📌 Cart

## 장바구니 전체 조회

### GET /carts

- ResponseBody

```json
[
  {
    "cid": 3,
    "userid": 3,
    "date": "2023-03-03",
    "products": [
      {
        "productid": 3,
        "quantity": 4
      }
    ]
  },
  {
    "cid": 4,
    "userid": 4,
    "date": "2023-03-04",
    "products": []
  },
  ...
```

## 장바구니 개별 조회

### GET /carts/{cid}

- ResponseBody

```json

{
  "cid": 3,
  "userid": 3,
  "date": "2023-03-03",
  "products": [
    {
      "productid": 3,
      "quantity": 4
    }
  ]
}

```

## 유저별 장바구니 조회

### GET /carts/user/{uid}

- ResponseBody

```json

{
  "cid": 3,
  "userid": 3,
  "date": "2023-03-03",
  "products": [
    {
      "productid": 3,
      "quantity": 4
    }
  ]
}

```

## 장바구니에 제품 추가

### POST /carts

- RequestBody

```json
{
  "cid": 3,
  "date": "2022-03-20",
  "products": [
    {
      "productid": 3,
      "quantity": 5
    },
    {
      "productid": 4,
      "quantity": 2
    }
  ],
  "userid": 3
}
```

- ResponseBody

```json
{
  "cid": 3,
  "userid": 3,
  "date": "2023-03-03",
  "products": [
    {
      "productid": 3,
      "quantity": 5
    },
    {
      "productid": 4,
      "quantity": 2
    }
  ]
}
```

## 장바구니 제품 수량 변경

- 필수 파라미터
    - 해당 장바구니를 소유한 userId
    - 변경할 productId와 수량

### PUT or PATCH /users

- RequestBody

```json
{
  "userid": 3,
  "date": "2023-03-03",
  "products": [
    {
      "productid": 3,
      "quantity": 3
    }
  ]
}
```

- ResponseBody

```json

{
  "cid": 3,
  "userid": 3,
  "date": "2023-03-03",
  "products": [
    {
      "productid": 3,
      "quantity": 3
    },
    {
      "productid": 4,
      "quantity": 2
    }
  ]
}
```

# Login

- RequestBody

```json
{
"username" : "user1",
"password" : "<PASSWORD>"
}

```


- ResponseBody

```json
{
"refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzZXIxIiwiaWF0IjoxNjc5NTg0MjgzLCJleHAiOjE2Nzk1ODYwODN9.d6uTE14CBXqZ6_x7x7_hGevGP0PFnX27IjpoAN4b5OA",
"accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzZXIxIiwiaWF0IjoxNjc5NTg0MjgzLCJleHAiOjE2Nzk1ODQ4ODN9.IWPtPjXd_oSzGuuqlXDbau3k9wMvC33q_ZTmVYw2Rd0"
}


```
