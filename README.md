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

![image](https://user-images.githubusercontent.com/64225078/224550844-ad21f411-c7c8-4d8d-91e2-e6fd1e654b7d.png)


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

...진행중(22.3.12)
