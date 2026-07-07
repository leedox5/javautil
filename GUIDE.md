# JavaUtil 동작 가능 상태 만들기

현재 기준으로 프로젝트 구조와 IDE 진단상 컴파일 에러는 없습니다.
다만 실제로 신뢰 가능한 동작 상태가 되려면 아래 순서로 정리하는 것이 좋습니다.

## 현재 상태 요약

- `CalendarUtil.getNowMinute()` 오동작은 수정되어 분(minute) 값을 반환합니다.
- 테스트가 `AppTest`, `UtilTest`, `CalendarUtilTest`로 확장되어 핵심 유틸 동작을 검증합니다.
- 빌드 기준은 Java 8 타깃, JUnit 4.13.2로 정리되어 있습니다.

## 실행 가능한 작업 순서

1. 베이스라인 확인
   - `mvn -v`
   - `mvn clean test`
   - 현재 로컬 빌드/테스트 상태를 먼저 기록합니다.

2. 즉시 버그 수정
   - `CalendarUtil.java:83`에서 분 반환 로직을 수정합니다.

3. 단위 테스트 보강
   - `AppTest`를 확장하거나 테스트 클래스를 추가합니다.
   - 최소 검증 범위:
     - `Util.repeat`
     - `Util.fillRight`
     - `Util.getFilledStrLen`
     - `CalendarUtil.getNowMinute`
     - 시간/날짜 메서드 핵심 동작

4. Java 버전 정책 정리 후 `pom.xml` 반영
   - Java 8 유지 또는 Java 17 상향을 결정합니다.
   - Maven compiler 설정을 통일합니다.

5. 실행 진입점 정리
   - `App.main`에 실제 사용 예시나 입출력 흐름을 반영합니다.

6. 문서화
   - 실행 방법, 테스트 방법, 요구 JDK/Maven 버전을 `README.md`에 명시합니다.

7. 검증 게이트 확정
   - 최소 기준을 `mvn clean test`와 `mvn package` 성공으로 고정합니다.

## 완료 정의

- [x] 로컬에서 `mvn clean test` 통과 확인
- [x] 로컬에서 `mvn package` 성공 확인
- [x] `CalendarUtil` 분 반환 회귀 테스트 추가 및 정합성 확인
- [x] 실행/설치 문서(`README.md`) 정리

## 다음 단계 로드맵

기본 셋업이 끝났으므로 이제는 유틸 라이브러리로서의 안정성과 유지보수성을 높이는 작업을 진행합니다.

1. CI 추가
   - GitHub Actions로 `mvn clean test`와 `mvn package`를 자동 실행합니다.
   - `main` 브랜치 push와 pull request에서 회귀를 확인합니다.
   - 용어 설명:
     - GitHub Actions: GitHub 저장소에서 특정 이벤트가 발생했을 때 정해진 작업을 자동으로 실행해주는 기능입니다. 이 프로젝트에서는 push나 pull request가 생기면 Maven 테스트와 패키징을 실행합니다.
     - CI: Continuous Integration의 약자로, 변경사항을 자주 합치면서 자동 빌드와 테스트로 문제가 없는지 확인하는 개발 방식입니다.
     - 회귀: 기존에 잘 동작하던 기능이 새 변경 때문에 다시 깨지는 현상입니다. 예를 들어 `CalendarUtil.getNowMinute()`를 고친 뒤 나중에 실수로 다시 월 값을 반환하게 되면 회귀입니다.

2. `Util` 입력 방어 정책 정리
   - `repeat(null, 3)` 같은 `null` 입력 처리 방식을 정합니다.
   - `fillRight(null, 5)`, `getMaxLen(emptyList, 0)` 같은 경계값 정책을 테스트로 고정합니다.
   - 예외를 던질지 안전한 기본값을 반환할지 명확히 합니다.
   - 결정한 정책:
     - `null` 입력은 `NullPointerException`으로 즉시 실패합니다.
     - 음수 크기나 음수 반복 횟수는 `IllegalArgumentException`으로 실패합니다.
     - 빈 목록이나 범위를 벗어난 인덱스도 `IllegalArgumentException`으로 실패합니다.

3. `CalendarUtil` 날짜 파싱 정책 정리
   - `SimpleDateFormat`의 lenient 파싱 동작을 유지할지 엄격하게 막을지 결정합니다.
   - 잘못된 날짜 입력을 `null`로 처리할지 예외로 처리할지 테스트로 고정합니다.

4. Java 버전 정책 최종 결정
   - 라이브러리 호환성을 우선하면 Java 8 target을 유지합니다.
   - 최신 문법과 런타임을 우선하면 Java 17로 상향합니다.

5. 패키지와 아티팩트 이름 정리
   - `artifactId`와 프로젝트 이름이 일관되도록 정리합니다.
   - 배포 또는 외부 사용을 고려해 `javautil` 또는 `java-util` 같은 이름으로 맞춥니다.

## 현재 진행 항목

- [x] GitHub Actions CI 추가
- [x] `Util` 입력 방어 정책 정리 (`null`, 음수/빈 입력 경계값 정책 반영)
- [ ] `CalendarUtil` 날짜 파싱 정책 정리
- [ ] Java 버전 정책 최종 결정 (8 유지 vs 17 상향)
- [ ] 패키지와 아티팩트 이름 정리
