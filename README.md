# Sample Board Project — Lint/Security Test Fixture

의도된 보안/컨벤션 위반을 포함한 테스트용 Spring Boot 샘플 프로젝트입니다.
`java_SecureCoding_ast1` (사용자 정의 컨벤션) + `lint_java` (기본 제공)의 검출 여부 및 **5회 이상 반복 패턴 → AI 리뷰 패턴 자동 승격** 메커니즘을 검증하기 위해 제작되었습니다.

> ⚠️ **프로덕션 사용 금지**. 실제 취약점을 의도적으로 포함합니다.

---

## 확인 방법

1. `http://localhost:3000` 접속 → **저장소 추가**
2. `tmp/sample-project/` 로컬 경로 또는 Git URL로 등록
3. 저장소 동기화 → **코드 리뷰** 실행
4. 에이전트: `code-reviewer` + `security-reviewer` (컨벤션 활성화 상태)
5. 결과 리포트에서 아래 매핑표의 라인 번호와 비교 검증

확인할 URL:
- `http://localhost:3000/inspection-settings/conventions` — 검출 룰 확인
- `http://localhost:3000/inspection-settings/agents` — 에이전트 스킬 확인

---

## 🔁 반복 패턴 (6회): `catch_generic_exception`

`catch (Exception e)` / `catch (Throwable t)` 패턴이 **6개 파일에 분산**되어 있습니다.
5회 임계치를 초과하므로 **AI 리뷰 패턴 자동 승격** 메커니즘이 발동해야 합니다.

| # | 파일 | 메서드 | 라인(근사) |
|---|------|--------|------------|
| 🔁 1/6 | `controller/PostController.java` | `createPost()` | ~62 |
| 🔁 2/6 | `controller/PostController.java` | `deletePost()` | ~72 |
| 🔁 3/6 | `controller/AuthController.java` | `login()` — `catch(Throwable t)` | ~46 |
| 🔁 4/6 | `service/CommentService.java` | `loadComments()` | ~36 |
| 🔁 5/6 | `repository/PostRepository.java` | `deleteByAuthor()` | ~27 |
| 🔁 6/6 | `util/FileUtil.java` | `readFile()` — 빈 catch 중복 | ~24 |

---

## 의도된 위반 전체 매핑

### `java_SecureCoding_ast1` (보안 AST)

| 파일 | 규칙 | 심각도 |
|------|------|--------|
| `config/SecurityConfig.java` | `hardcoded_secret_string` × 2 (dbPassword, jwtSecret) | WARNING |
| `config/SecurityConfig.java` | `use_of_weak_crypto_algorithm` (DES) | WARNING |
| `util/CryptoUtil.java` | `use_of_weak_crypto_algorithm` × 2 (MD5, SHA-1) | WARNING |
| `util/CryptoUtil.java` | `insecure_random_usage` (`new Random()`) | WARNING |
| `util/CryptoUtil.java` | `hardcoded_secret_string` (apiKey) | WARNING |
| `util/FileUtil.java` | `path_traversal_risk` × 2 (`new File(getParameter)`) | WARNING |
| `util/FileUtil.java` | `empty_catch_block` + `catch_generic_exception` 🔁 6/6 | WARNING |
| `controller/PostController.java` | `xss_unescaped_output` × 2 (`print/println`) | WARNING |
| `controller/PostController.java` | `path_traversal_risk` (`new File(getParameter)`) | WARNING |
| `controller/PostController.java` | `catch_generic_exception` 🔁 1/6, 2/6 | WARNING |
| `controller/AuthController.java` | `runtime_exec_with_external_input` | WARNING |
| `controller/AuthController.java` | `empty_catch_block` | WARNING |
| `controller/AuthController.java` | `printstacktrace_exposure` | WARNING |
| `controller/AuthController.java` | `catch_generic_exception` (Throwable) 🔁 3/6 | WARNING |
| `service/CommentService.java` | `sql_injection_use_prepared_statement` | WARNING |
| `service/CommentService.java` | `printstacktrace_exposure` | WARNING |
| `service/CommentService.java` | `catch_generic_exception` 🔁 4/6 | WARNING |
| `repository/PostRepository.java` | `sql_injection_use_prepared_statement` × 2 | WARNING |
| `repository/PostRepository.java` | `catch_generic_exception` 🔁 5/6 | WARNING |
| `model/User.java` | `hardcoded_secret_string` (apiToken) | WARNING |

### `lint_java` (Spring/Java 컨벤션 — active)

| 파일 | 규칙 | 심각도 |
|------|------|--------|
| `service/post_service.java` | `class_name_pascal_case` | WARNING |

---

## 정상 코드 (대조군 — 오탐 검증)

| 파일 | 역할 |
|------|------|
| `BoardApplication.java` | 표준 Spring Boot 엔트리포인트 |
| `model/Post.java` | 일반 POJO (게터만) |

이 두 파일에서 **어떤 위반도 검출되지 않아야** 합니다.

---

## 예상 총 검출 건수

- `java_SecureCoding_ast1`: **24건 이상**
- `lint_java` (active): **1건** (`class_name_pascal_case`)
- **AI 리뷰 패턴 자동 승격**: `catch_generic_exception` **6건 누적** → 패턴 섹션에 노출

---

## 검증 체크리스트

- [ ] `catch_generic_exception` 6건 전부 검출
- [ ] 검출 결과가 AI 리뷰 패턴(자동 규칙 후보) 섹션에 자동 승격
- [ ] 모든 보안 규칙이 매핑표와 일치
- [ ] `BoardApplication.java`, `model/Post.java`에서 오탐 0건
- [ ] `post_service` 클래스에서 `class_name_pascal_case` 검출

---

## 구조 요약

```
tmp/sample-project/
├── pom.xml
├── README.md                                       # 본 문서
├── src/main/java/com/example/board/
│   ├── BoardApplication.java                       # 정상 (대조군)
│   ├── config/SecurityConfig.java                  # hardcoded, weak_crypto
│   ├── controller/
│   │   ├── AuthController.java                     # exec, empty_catch, printStack, generic_catch 🔁 3/6
│   │   └── PostController.java                     # xss, path_traversal, generic_catch 🔁 1/6, 2/6
│   ├── service/
│   │   ├── CommentService.java                     # SQL injection, printStack, generic_catch 🔁 4/6
│   │   └── post_service.java                       # class_name_pascal_case (lint_java)
│   ├── repository/
│   │   └── PostRepository.java                     # SQL injection × 2, generic_catch 🔁 5/6
│   ├── util/
│   │   ├── CryptoUtil.java                         # MD5, SHA-1, new Random, apiKey
│   │   └── FileUtil.java                           # path_traversal × 2, empty_catch + generic 🔁 6/6
│   └── model/
│       ├── Post.java                               # 정상 (대조군)
│       └── User.java                               # apiToken 하드코딩
└── src/main/resources/application.properties
```
