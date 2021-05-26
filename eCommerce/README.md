# Simple eCommerce Application

## :clipboard: 개발환경
* Eclipse 
* Postman
* MySQL Workbench
* Sourcetree
* GitHub

## :clipboard: 개요 
* Product와 Category를 관리하는 Rest API를 구현한다.

## :clipboard:  가정
* 1) Product는 다수개의 Category에 속할 수 있다. 즉, 전동 칫솔은 "Electronics" 와
"Beauty & Personal Care" 카테고리로 분류될 수 있다.
* 2) Category는 다수개의 SubCategory를 가질 수 있다. 예를 들어 “Electronics”
category는 'Audio & Video Components', 'Camera & Photo', ‘Computers’
SubCategory를 가질 수 있다.

## :clipboard:  Rest API
### Products
#### 1. Product CRUD ( URL: /api/products), ProductController.java
|HTTP|URL|설명|
|----|------------|---|
|GET| /api/products |모든 product를 조회한다|
|GET| /api/products/{id}|특정 id를 가진 product를 조회한다|
|POST| /api/products <br> Body { "name": "P1", "price": 100.00 }|하나의 product을 생성한다|
|PUT| /api/products/{id} <br> Body { "name": "P1", "price": 100.00 }|하나의 product을 수정한다|
|DELETE| /api/products/{id} |특정 id를 가진 product를 삭제한다|

### Categories
#### 1.  category CRUD ( URL: /api/categories), CategoryController.java
|HTTP|URL|설명|
|----|------------|---|
|GET| /api/categories |모든 category를 조회한다|
|GET| /api/categories/{id}|특정 id를 가진 category를 조회한다|
|POST| /api/categories <br> Body { "name": "C1", "price": 100.00 }|하나의 category을 생성한다|
|PUT| /api/categories/{id} <br> Body { "name": "C1", "price": 100.00 }|하나의 category을 수정한다|
|DELETE| /api/categories/{id} |특정 id를 가진 category를 삭제한다|

#### 2.  Add / Remove child categories CategorySubcategoriesController.java
|HTTP|URL|설명|
|----|------------|---|
|GET| /api/categories/{parentid}/subcategories |특정 parentid를 가진 카테고리에 속한 자식카테고리를 조회한다.|
|POST| /api/categories/{parentid}/subcategories/{childid}|Parent category와 child category를 연결한다|
|DELETE| /api/categories/{parentid}/subcategories/{childid}|Parent category에서 child category를 제거한다|

#### 3.   Link / Unlink products, CategoryProductsController.java
|HTTP|URL|설명|
|----|------------|---|
|GET| /api/categories/{categoryid}/products |특정 id를 가진 category에 속한 모든 product를 조회한다|
|POST| /api/categories/{categoryid}/products/{productid}|Product를 category에 넣는다|
|DELETE| /api/categories/{categoryid}/products/{productid}|Product를 category에서 제거한다|
