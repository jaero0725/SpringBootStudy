
## :clipboard: Spring Data JPA
### 1. Creating DAO 
#### 문제
- 다른 entity 를 만들때 유사한 코드의 반복. 
- DAO 패턴이 같다는 점. 
- 코드의 반복
![image](https://user-images.githubusercontent.com/55049159/120432099-628efe00-c3b4-11eb-8f71-55ed44fcf39d.png)
- entity type , primary key만 바꿔주면 다른 entity 도 다를 것이 없다.

### 2. Spring Data JPA
#### 장점 
- 틀에 박혀있는 DAO code를 최소화, 
- DAO를 실제로 만들어줄 필요가 없다

#### 제공되는 인터페이스

![image](https://user-images.githubusercontent.com/55049159/120432353-c9141c00-c3b4-11eb-9875-c28ba09bfb8d.png)
- Spring DATA JPA 는 repository interfaces 를 제공한다.
- 1) CrudRepository
- 2) PagingAndSortingRepository
- 3) JpaRepository

### 4. Query Method

- id를 기반으로 해서 조회하기 보다는, 검색을 통해서 조회할 경우가 있다.
- 그 부분에 대해서는 추가적으로 구현해야 한다.
- Spring Data JPA 는 CRUD operations를 제공한다.
- method names에 따라 동적으로 쿼리를 제공한다. 

ex) 
- User findByEmail(String email), 이메일을 기준으로 조회하는 메서드 
 내부적으로 SQL 자동적으로 만들어진다. 이름을 바탕으로 메서드를 자동으로 만들어준다는점. 
- User findByEmailAndPassword(String email, String password) 도 마찬가지로 만들어준다. 
- return 타입이 하나 이상이면, List <T> or Page <T>

<br>

![image](https://user-images.githubusercontent.com/55049159/120432522-01b3f580-c3b5-11eb-9775-4f08c5160947.png)
- find By + 변수이름  으로 넣어 주면 자동생성된다.

<br>

#### 참조 
![image](https://user-images.githubusercontent.com/55049159/120432603-2019f100-c3b5-11eb-98ed-cf0ca71ac172.png)
- 키워드를 사용, 검색할때 참조  

#### JPA vs Spring Data JPA 
![image](https://user-images.githubusercontent.com/55049159/120432925-93236780-c3b5-11eb-9a54-f46a7afbab60.png)

### 예시
#### 1) 다수개의 조건 기술, 게시판에서 title 혹은 content에서 특정 단어가 포함된 글 목록 조회
~~~java

public interface BoardRepository extends JpaRepositoty<Board, Long> {

    List<Board> findByTitleContainingOrContentContaining(String title, String content);
}

~~~

~~~java

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private BoardRepository repo;

    @Test
    public void testFindByTitleContainingOrContentContaining() {

       List<Board> boardList = repo.findByTitleContainingOrContentContaining(“17”, “17”);

       for(Board board: boardList) {  System.out.println(“” + board.toString() )}; 
    }
}
~~~

#### 2) Pagination, 게시판에서 title변수에 “한성대” 라는 검색어가 포함된 게시글 목록 검색 페이지 단위로 조회
~~~java

public interface BoardRepository  extends JpaRepositoty<Board, Long> {

    List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
}

~~~

~~~java

@Test
public void testFindByTitleContaining() {
    
      Pageable paging = PageRequest.of(0, 5);
      List<Board> boardList= repo.findByTitleContaining(“한성대”, paging);

      for(Board board: boardList) {
	System.out.println(“->” + board.toString() );
      }
}
~~~

#### 3) Pagination and Sort, 게시판에서 title변수에 “한성대”이라는 검색어가 포함된 게시글 목록 검색 “seq” 변수에 따라 내림 차순으로 정렬
~~~java

public interface BoardRepository extends JpaRepositoty<Board, Long> {

    List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
}

~~~

~~~java

@Test
public void testFindByTitleContaining( ) {

     Pageable paging = PageRequest.of(0,5, Sort.Direction.DESC, “seq”);
     List<Board> boardList= repo.findByTitleContaining(“한성대”, paging);

    for(Board board: boardList) {
	System.out.println(“->” + board.toString() );
}

~~~

#### 5) Return Type: List<T> -> Page<T> , Page<T> 객체는 페이징 처리할 때 다양한 정보를 추가로 제공

~~~java

public interface BoardRepository  extends CrudRepositoty<Board, Long> {

    Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
}

~~~

~~~java

@Test
public void testFindByTitleContaining() {

      Pageable paging = PageRequest.of(0,5, Sort.Direction.DESC, “seq”);
      Page<Board> pageInfo= repo.findByTitleContaining(“한성대”, paging);

      System.out.println(“Page size: ”    + pageInfo.getSize() );
      System.out.println(“Total Pages: ” + pageInfo.getTotalPages() );
      System.out.println(“Total Count: ” + pageInfo.getTotalElements() );

      List<Board> boardList=pageInfo.getContent();

      for(Board board: boardList) {
	     System.out.println(“->” + board.toString() );
      }
}
~~~

### 5. @Query Annotation 
- method 이름이 너무 복잡하거나, 직접 쿼리를 명시적으로 설정하고자 할때 사용한다. 

~~~java
@Query("select u from User u where u.email=?1 and u.password=?2 and u.enabled=true") 
User findByEmailAndPassword(String email, String password); 
~~~
### @Query : JPQL 
#### 1) Positional parameter binding 

~~~java
public interface BoardRepository extends JpaRepositoty<Board, Long> {
    //entity와 필드네임 대소문자를 신경쓴다.
    @Query(“SELECT b From Board b “
                + “WHERE b.title like %?1% ORDER BY b.seq DESC”)
    List<Board> queryAnnotationTest1(String searchKeyword);
}
~~~

~~~java
@Test
Public void QueryAnnotationTest() {

     List<Board> boardList=repo.queryAnnotationTest1(“한성대”);

     for(Board board: boardList) {
	System.out.println(“->” + board.toString() );
     }
}
~~~

#### 2) Named Parameter binding 

~~~java
//이름을 기반해서 넣는다.
public interface BoardRepository extends CrudRepositoty<Board, Long> {

    @Query(“SELECT b From Board b WHERE b.title like %:searchKeyWord% “
         + “ORDER BY b.seq DESC”)
    List<Board> queryAnnotationTest1(@Param(“searchKeyword”)String searchKeyword);
}
~~~

### @Query : native

~~~java

public interface BoardRepository extends JpaRepositoty<Board, Long> {

    @Query(value=“select seq, title from board “
                + “where title like ‘%’ || ?1 ‘%’ “
 	   + “order by seq dec”, nativeQuery=true);

    List<Object[]> queryAnnotationTest2(String searchKeyword);
}
~~~
