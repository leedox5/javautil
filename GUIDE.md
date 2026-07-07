Plan: JavaUtil 동작 가능 상태 만들기
현재 기준으로 프로젝트 구조와 IDE 진단상 컴파일 에러는 없습니다.
다만 실제로 “신뢰 가능한 동작 상태”가 되려면 아래 순서로 정리하는 것이 좋습니다.

핵심 이슈

기능 버그 수정 필요
CalendarUtil.java:83가 분이 아니라 월 값을 반환하고 있습니다.
반환식이 CalendarUtil.java:84이라 오동작 가능성이 큽니다.
테스트가 사실상 비어 있음
현재 테스트는 AppTest.java:16의 항상 참 테스트만 있어 회귀 방지가 어렵습니다.
빌드 기준이 오래됨
pom.xml:17과 pom.xml:18이 Java 1.7 기준이라 최신 환경 재현성/유지보수성이 떨어집니다.
실행 가능한 작업 순서

베이스라인 확인
mvn -v, mvn clean test로 현재 로컬 빌드/테스트 상태를 먼저 기록
즉시 버그 수정
CalendarUtil.java:83에서 분 반환 로직 수정
단위 테스트 보강
AppTest 확장 또는 테스트 클래스 추가해서 다음 최소 범위 검증
Util.repeat, Util.fillRight, Util.getFilledStrLen
CalendarUtil.getNowMinute 포함 시간/날짜 메서드 핵심 동작
Java 버전 정책 정리 후 pom 반영
pom.xml에서 Java 8 유지 또는 17 상향 결정 후 compiler 설정 통일
실행 진입점 정리
App.main에 실제 사용 예시/입출력 흐름 반영
문서화
실행 방법, 테스트 방법, 요구 JDK/Maven 버전을 README에 명시
검증 게이트 확정
최소 기준을 mvn clean test + mvn package 성공으로 고정
확인 기준(완료 정의)

mvn clean test 통과
mvn package 성공
CalendarUtil 분 반환 회귀 테스트 통과
신규 개발자가 문서만 보고 동일하게 실행 가능