# Spring Boot 3 With ChatGPT

The idea is to implement ChatGPT in a Spring Boot project, and it is up to your discretion or your knowledge of java and spring boot.

### What will we be doing?

We will create 2 basic endpoints that consume a dependency that in my opinion is the easiest to integrate https://github.com/flashvayne/chatgpt-spring-boot-starter

* Chat
  
* Image generation

## Guide

The following guide illustrates how to create methods with the chatGPT integration.
This is one of the simplest forms that I have implemented in my case.

### 1. Create project Spring Boot 3.
  We create a spring boot 3 project, in my case I use 3.1.0, we add Spring Web and Lombok for the help it gives us.

### 2. Add dependency chatgpt-spring-boot-starter
  We add the dependency to our project, in my case I use maven, but you can use gradle, the dependency is the following:

    ```pom
    <dependency>
        <groupId>com.github.flashvayne</groupId>
        <artifactId>chatgpt-spring-boot-starter</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```
### 3. Set chatgpt properties in your application properties o application.yml
  You have to create an account at https://platform.openai.com/account/api-keys and bring the generated key to your application.
  We add the following properties to our application.properties or application.yml file, in my case I use application.properties.
    
    #application.properties
    chatgpt.api-key=${API_KEY}
    
##
    #yml
    chatgpt:
      api-key: ${API_KEY}

 The API_KEY variables are the ones we will use to consume the chatgpt API. Under no circumstances should you pass your api_key to another user.

### 4. Create a controller
  We create a controller with the following endpoints:

    @RestController
    @RequestMapping("/question")
    @RequiredArgsConstructor
    public class QuestionController {

    private final ChatgptService chatgptService;

    @GetMapping("/send")
    public String sendQuestion(@RequestParam String question) {
        String responseQuestion = chatgptService.sendMessage(question);
        return responseQuestion;
      }
    }
    
  in this endpoint http://localhost:8080/question/send?question=que es un String

  Now to make it a little scalable...
### 5. Create a DTO
   Next we create the class QuestionDTO for generic use
   
        @Data
        @AllArgsConstructor
        public class ResponseDTO<T> {
    
          private Integer code;
          private String message;
          private T data;
    
          public static <T> ResponseDTO<T> success(T data) {
              return new ResponseDTO<>(200, "success", data);
          }
    
          public static <T> ResponseDTO<T> fail(T data) {
              return new ResponseDTO<>(500, "fail", data);
          }
    
         }
### 6. Modified controller
   We modify the controller to use the DTO and add the annotation @Slf4j who log utilization. In other level add a method who generate image.
     
        @Slf4j
        @RestController
        @RequestMapping("/question")
        @RequiredArgsConstructor
        public class QuestionController {

          private final ChatgptService chatgptService;
      
          @GetMapping("/send")
          public ResponseDTO<String> sendQuestion(@RequestParam String question) {
              log.info("question is: {}", question);
              String responseQuestion = chatgptService.sendMessage(question);
              log.info("responseQuestion is: {}", responseQuestion);
              return ResponseDTO.success(responseQuestion);
          }
      
          @PostMapping("/imageGenerate")
          public String imageGenerate(@RequestParam String var1 ){
              return chatgptService.imageGenerate(var1);
          }
        }
### Conclusion
   We have a basic implementation of chatgpt in a spring boot project, we can improve it by adding a database to save the conversations, or adding a front-end to make it more user-friendly.
   For more information on how to use the chatgpt-spring-boot-starter dependency, you can go to the following link https://github.com/flashvayne/chatgpt-spring-boot-starter/tree/master to see the documentation and actually The dependence we use is on him.
   
