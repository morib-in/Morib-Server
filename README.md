# Morib
_당신의 온전한 몰입을 도와줄 작업 공간, **Morib**_

<br>

할 일에 필요한 모립 세트를 등록하고, 그 이외의 방해 요소로부터 자유로워지세요.

### 1️⃣ 간편한 모립 세트
할 일에 몰입하는 데에 필요한 사이트 Url을 입력해서 모립 세트를 등록해보세요. 이미 열어놓은 웹사이트에서도 간편하게 모립 세트를 등록할 수 있어요.

### 2️⃣ 온전한 몰입을 위한 타이머
틀어놓기만 하고 시간이 흘러가는 타이머는 이제 그만! 당신의 몰입 여부에 따라 정확한 시간이 카운트되는 타이머가 여러분의 몰입을 도와줘요.

### 3️⃣ 몰입하되, 고립되지 않는
친구를 추가하고 몰입 현황을 공유하며 동기부여 받으세요. 친구의 온라인/오프라인 여부 뿐만 아니라 몰입 시간 및 작업 내용을 공유할 수 있어요.

<br>

## 🧑🏻‍💻 Contributors

|<img src="https://github.com/HanIpBoy/Documents/blob/master/%EC%9D%B4%EB%AA%A8%EC%A7%80/%EC%9C%A0%EC%98%81%EC%9E%AC%20%EC%9D%B4%EB%AA%A8%EC%A7%80.png" width=200>|<img src="https://github.com/morib-in/Jaksim-Server/assets/99001085/7a6cfa11-f599-4ac2-b499-ee6ecfbd43f4" width=200>|
|:--:|:--:|
|**영재**|**은서**|
|[@geniusYoo](https://github.com/geniusYoo)|[@eunseo5343](https://github.com/eunseo5343)|
|Lead, 인프라, 타이머 API 개발|홈 API 개발| 

<br>

## 🗂️ Foldering
```
├── JaksimApplication.java
├── auth
│   ├── PrincipalHandler.java
│   ├── SecurityConfig.java
│   ├── UserAuthentication.java
│   ├── WebSecurityConfig.java
│   ├── filter
│   │   ├── CustomAccessDeniedHandler.java
│   │   ├── CustomJwtAuthenticationEntryPoint.java
│   │   └── JwtAuthenticationFilter.java
│   └── jwt
│       ├── JwtTokenProvider.java
│       └── JwtValidationType.java
├── category
│   ├── api
│   │   ├── CategoryApi.java
│   │   └── CategoryApiController.java
│   ├── domain
│   │   ├── Category.java
│   │   └── CategoryTask.java
│   ├── dto
│   │   ├── CategoryCheckResponse.java
│   │   ├── CategoryCreateRequest.java
│   │   ├── CategoryMsetLinkResponse.java
│   │   ├── CategoryTaskLink.java
│   │   ├── FilteredResourceResponse.java
│   │   └── TaskWithTaskTimer.java
│   ├── facade
│   │   ├── CategoryMsetFacade.java
│   │   └── CategoryTaskFacade.java
│   ├── repository
│   │   ├── CategoryRepository.java
│   │   └── CategoryTaskRepository.java
│   └── service
│       ├── CategoryService.java
│       └── CategoryTaskService.java
├── global
│   ├── common
│   │   ├── ApiResponseUtil.java
│   │   ├── BaseResponse.java
│   │   ├── BaseTimeEntity.java
│   │   ├── Constants.java
│   │   ├── DateUtil.java
│   │   ├── HealthCheckController.java
│   │   ├── LoggingFilter.java
│   │   └── S3Service.java
│   ├── config
│   │   ├── AwsConfig.java
│   │   ├── JpaAuditingConfig.java
│   │   ├── RedisConfig.java
│   │   └── SwaggerConfig.java
│   ├── exception
│   │   ├── BusinessException.java
│   │   ├── DateTimeParseException.java
│   │   ├── ForbiddenException.java
│   │   ├── GlobalExceptionHandler.java
│   │   ├── IOException.java
│   │   ├── InvalidValueException.java
│   │   ├── NotFoundException.java
│   │   ├── OAuthException.java
│   │   └── UnauthorizedException.java
│   └── message
│       ├── ErrorMessage.java
│       └── SuccessMessage.java
├── mset
│   ├── api
│   │   ├── MsetApi.java
│   │   └── MsetApiController.java
│   ├── domain
│   │   ├── CategoryMset.java
│   │   ├── Mset.java
│   │   └── TaskMset.java
│   ├── repository
│   │   ├── CategoryMsetRepository.java
│   │   └── MsetRepository.java
│   └── service
│       ├── CategoryMsetService.java
│       └── MsetService.java
├── socket
│   ├── config
│   │   ├── SocketIoConfig.java
│   │   ├── SocketIoServerLifeCycle.java
│   │   └── SocketModule.java
│   ├── controller
│   │   ├── MessageController.java
│   │   └── SocketIOController.java
│   ├── dto
│   │   ├── WebSocketRequest.java
│   │   └── WebSocketResponse.java
│   ├── message
│   │   ├── Message.java
│   │   └── MessageType.java
│   ├── repository
│   │   └── RedisSocketMessageRepository.java
│   └── service
│       ├── MessageService.java
│       └── SocketService.java
├── task
│   ├── api
│   │   ├── TaskApi.java
│   │   ├── TaskApiController.java
│   │   ├── TimerApi.java
│   │   └── TimerApiController.java
│   ├── domain
│   │   ├── Task.java
│   │   ├── TaskTimer.java
│   │   ├── Todo.java
│   │   ├── TodoTask.java
│   │   └── UserTimer.java
│   ├── dto
│   │   ├── FetchTitleRequest.java
│   │   ├── FetchTitleResponse.java
│   │   ├── StartTimerRequest.java
│   │   ├── StopTimerRequest.java
│   │   ├── TaskCreateRequest.java
│   │   ├── TaskInTodoCard.java
│   │   ├── TodoCardResponse.java
│   │   └── TotalTimeTodayResponse.java
│   ├── repository
│   │   ├── TaskRepository.java
│   │   ├── TaskTimerRepository.java
│   │   ├── TodoRepository.java
│   │   ├── TodoTaskRepository.java
│   │   └── UserTimerRepository.java
│   └── service
│       ├── TaskService.java
│       ├── TaskTimerService.java
│       ├── TodoService.java
│       ├── TodoTaskService.java
│       └── UserTimerService.java
└── user
    ├── api
    │   ├── SocialLoginTempController.java
    │   ├── UserApi.java
    │   └── UserApiController.java
    ├── domain
    │   ├── Permission.java
    │   ├── PermissionLevel.java
    │   ├── Platform.java
    │   ├── RefreshToken.java
    │   └── User.java
    ├── dto
    │   ├── Tokens.java
    │   ├── UserInfo.java
    │   ├── request
    │   │   ├── UserReissueRequest.java
    │   │   ├── UserSignInRequest.java
    │   │   └── UserSignUpRequest.java
    │   └── response
    │       ├── UserSignInResponse.java
    │       └── UserSignUpResponse.java
    ├── facade
    │   └── UserFacade.java
    ├── repository
    │   ├── RedisTokenRepository.java
    │   └── UserRepository.java
    └── service
        └── UserService.java
```

<br>

## 📉 Architecture
|IDE|IntelliJ|
|:--|:--|
|Language|Spring Boot, Gradle|
|Database|MySQL|
|External|AWS EC2, AWS RDS, AWS S3, Nginx, Docker, Redis|
|CI/CD|Github Action|


![image](https://github.com/user-attachments/assets/eec8277f-5fb6-411c-8586-b29474f73532)



