# humor-community-web
vue.js + SpringBoot 기반 단단한 커뮤니티 사이트

게시판 개발은 웹의 시작과 끝이라고 생각합니다. 웹 게시판은 어떻게 구현하느냐에 따를 수 있지만
데이터베이스, 인증, 트래픽처리, 알고리즘, 각종 라이브러리의 적용과 사용에 대해 가장 잘 보여줄 수 있는 방법이라고 생각했습니다.
옥션과 같은 쇼핑몰, 페이스북과 같은 대규모 커뮤니티 사이트, 네이버와 같은 포털사이트도 
결국 모두 웹에서 게시판을 얼마나 유연하게 확장시키고 그 안에 여러 기능을 잘 녹여 냈는가의 차이라고 생각합니다.
그래서 저는 단단하고 잘 짜여진 게시판이 나의 개발수준을 가장 잘 보여줄 수 있는 방법이라 생각하고 
'단단한 커뮤니티 사이트'를 계획하고 개발하였습니다.

목표
- 간결하지만 짜임새있고 구조적으로 단단한 커뮤니티 사이트를 개발

기술스택
BackEnd
FrameWork : SpringBoot, Spring Security
Language : Java 8
DataBase : MySql
ORM : Spring Data JPA
WAS : Tomcat 9

FrontEnd
Vue.js
BootStrap
axios
JavaScript

구현목표
Vue.js + Spring을 이용한 SPA 방식의 개발
Spring Security + Redis기반의 JWT 인증 구현
HATEOAS를 적용한 RESTful API 구현
계층형 댓글 구현
외부 라이브러리 사용 (네이버 SmartEditor 사용)
Swagger를 이용한 API 문서화