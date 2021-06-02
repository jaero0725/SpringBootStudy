## :clipboard: Spring Boot 관련 개념 
### 1. Spring Boot 를 왜사용하는지?
>  1) 생산성
>  2) Time to Market - 적시에 출시하는게 중요하기 때문.

### 2. Spring Boot 의 장점?
> #### 1) 수동적인 설정의 양을 최소화 <br>
> auto-configuration - 관련된 클래스가 있음. <br>
> props files & JAR classpath을 기반으로해서 자동으로 설정함.  <br>
> xml 파일을 만들어 줄 필요가 없다. <br>
> #### 2) Dependency conflicts를 해결 해준다. <br>
> 라이브러리간의 버전 충돌이 발생 할 수 있는데 관련된걸 세팅 <br>
> #### 3) embedded server 제공 <br>
> 애플리케이션 안에 내장 서버를 가지고 있어서 별도 설치 없이 빠르게 시작 할 수 있다. <br>

### 3. Spring Boot 와 Spring MVC, Spring Rest를 대체하는 것인가? 
> #### NO, Spring Boot가 Spring technologies를 활용 하는 것이다. 
![1](https://user-images.githubusercontent.com/55049159/119787099-ce341f80-bf0b-11eb-9b5c-30218740cbaf.PNG)

##  :clipboard: Spring Boot 4가지 특징
###  1. Auto Configuration 
> 동일한 작업을 반복하는것을 자동화 한다. <br>
> idea : Spring 이 자동으로 bean을 설정 <br> 
> idea : customize 하게 설정이 필요할 수 도 있기 때문에, properties을 통해 cusomize한다. <br> 
> #### EX) <br>
> spring mvc jar가 애플리케이션에 추가될때, auto configure 하도록 판단. <br>
>  -> spring mvc jar 가 classpath 가 있다면, Dispatcher Servlet을 자동 설정한다. (조건에 따라 자동 설정함)<br>

~~~java

//@SpringBootApplication : Annotation의 조합 
@Configuration
@EnabledAutoConfiguration // 자동 설정 기능 활성화
@ComponentScan
public class Application {
 ~~ 
} 
~~~
> #### @Conditional :조건을 체크해서 설정함. 
![image](https://user-images.githubusercontent.com/55049159/119791031-68499700-bf0f-11eb-85ed-fa4a6ad6c2f5.png)
> #### EX) 
![image](https://user-images.githubusercontent.com/55049159/119791612-ee65dd80-bf0f-11eb-80fc-5634d5a6538d.png)
![image](https://user-images.githubusercontent.com/55049159/119792151-6e8c4300-bf10-11eb-9a65-b741ff517060.png)

###  2. Easy DependencyManagement
> #### Spring Boot Starter 

![image](https://user-images.githubusercontent.com/55049159/119793418-887a5580-bf11-11eb-8075-e8850e23a9be.png)

~~~ xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
~~~
> #### Added Dependencies : 자동적으로 Dependencies 에 추가 
>Spring - core, beans, context, aop <br>
>Web MVC - (Spring MVC) <br>
>Jackson - for JSON Binding <br>
>Validation - Hibernate Validator, Validation API <br>
>Embedded Servlet Container - Tomcat <br>
>Logging - logback, slf4j <br>

###  3. EmbeddedServer 
> main() 메서드가 시작되면, 내장서버를 실행시킨다. <br> 
> 외부에 톰켓 서버에서 애플리케이션을 배포할 필요가 없다. <br>
> 'war' 파일이 아닌 'jar' 로 패키징이 이루어진다.<br>

![image](https://user-images.githubusercontent.com/55049159/119793646-beb7d500-bf11-11eb-907e-a960f883a720.png)

###  4. SpringBoot Actuator
> 애플리케이션  monitoring , tracing <br> 
> 어플리케이션을 서비스할때 중요하다.  <br>
> REST endpoints 를 자동적으로 추가해준다. <br>
> EX) /beans, /mappings <br>

~~~ xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
~~~

> default : /heath and /info 가 노출되어 있음. <br>
> Endpoints are prefixed with : /actuator  -> /acutator/health <br>
