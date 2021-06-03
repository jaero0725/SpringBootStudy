## :clipboard: Spring Security in Spring Boot Application
#### Introduction
- SpringSecurity is a framework for securing java-based applications with great flexibility and customizability
- Spring Security provides authentication and authorization support

#### Set up Spring security

~~~xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
~~~ 

- 기본적인 설정이 깔림
- AuthenticationManager bean을 등록 -> in-memory 에 user라는 single user를 자동으로 생성 
- static resources를 ignore 한다. ex) /css** , /js/**, ...
- CSRF, XSS, caching 등을 활성화 시킨다. 

#### Defaut user 변경
~~~
spring.security.user.name=alice
spring.security.user.password=alicepw
spring.security.user.roles=USER, ADMIN
~~~

### Configure Spring MVC
- compact 하게 넣어줄 수 있다. 

~~~java

@Configuration
public class WebConfig implements WebMvcConfigurer{
 @Override
 public void addViewControllers(ViewControllerRegistry registry){
   registry.addViewController("/").setViewName("home");
 }

 @Bean
 public SpringSecurityDialect securityDialect() {
   return new SpringSecurityDialect();
 }
}

~~~

### Configure Spring Security 
#### 1) Role-base access control using a database
 - User JPA Entity, Role JPA Entity (M:N mapping)
 - Spring Data JPA repository for the User, Role entities

#### 2) UserDetailsService to get UserDetails from database
 - loadUserByUsername()

#### 3) Cusomized Spring Security Configuration 
 - Authentication, Authorization 

###
![image](https://user-images.githubusercontent.com/55049159/120483214-d947ed80-c3ec-11eb-8a49-70d62695ee14.png)


### Security 동작방식
![image](https://user-images.githubusercontent.com/55049159/120506988-64cb7980-c401-11eb-9f7f-db3787ee0c41.png)
- username, password request를 보내면, AuthenticationFilter가 받는다.
- AuthenticationFilter가 토큰을 만들고, 토큰 값을 AuthenticationManager로 보냄.
- AuthenticationManager의 구현체라고 볼 수 있는 AuthenticationProvider에게 온다. 
- AuthenticationProvider는 PasswordEncoder를 통해 Hashed password 값을 만든다.
- UserDetailsService에서 loadUserByUsername()을 호출하여 UserDetails를 가져온다. 
- 모든 정보를 SecurtiyContext안의 Authentication에 저장한다. 

#### UserDetailService Interface
- The AuthenticationProvider uses UserDetailsService interface to load details about the user during authentication

- The UserDetailsService interface has one method named loadUserByUsername() which can be overridden to customize the process of finding the user

- The loadUserByUsername method returns a UserDetail object, which is also an interface and contains some methods for describing user information

- Spring Security provides an out-of-the box implementation of org.springframework.security.core.userdetails.User

~~~java

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + userName + " not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), getAuthorities(user));
    }
    …
}

~~~

### Spring Security Configuration

~~~java

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}

~~~
