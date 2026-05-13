---
issue_key: ISSUE-2
repository: java-project
title: [zmpOps][HIGH] NullPointerException - console/zmp-core-api
status: in_progress
severity: high
created_at: 2026-05-13T02:15:58.380854
updated_at: 2026-05-13T05:57:11.407694
resolved_at: 2026-05-13T02:21:06.395638
related_tickets:
  - zmpops:auto-1778638555-902dcbb8
  - zmpops:incident:sample-java-test-004
  - zmpops:auto-1778650435-46f13ff0
  - zmpops:auto-1778650670-23d89267
  - zmpops:auto-1778651491-c6af1417
  - zmpops:auto-1778651824-36d1ffe3
related_prs:
  - https://github.com/SangjunPark54/sample-java/pull/3
affected_files:
  - src/main/java/com/example/board/service/post_service.java
---

# ISSUE-2 — [zmpOps][HIGH] NullPointerException - console/zmp-core-api

## Root Cause
PostService.getPost(Long) calls postRepository.findById(id).get() which throws NoSuchElementException when Optional is empty, propagating as NullPointerException.

## Solution Guide
1. Replace postRepository.findById(id).get() with orElseThrow(() -> new PostNotFoundException(id)).\n2. Add PostNotFoundException extending RuntimeException mapped to HttpStatus.NOT_FOUND.\n3. Add @ExceptionHandler(PostNotFoundException.class) in GlobalExceptionHandler returning 404.

## Plan (LLM-generated)
1. PostService.getPost(Long)의 findById(id).get() 부분을 orElseThrow(() -> new PostNotFoundException(id))로 변경해야 합니다.
2. PostNotFoundException 클래스를 생성(권장 위치: service 패키지)하여 RuntimeException을 상속하도록 합니다.
3. GlobalExceptionHandler에서 PostNotFoundException 발생 시 404 응답을 내리도록 @ExceptionHandler를 추가해야 합니다.
file_tree 상에 PostService의 구현이 post_service.java 파일에 있다고 판단되어 해당 소스를 수정해야 하며, 예외 클래스를 동일 패키지에 추가해야 합니다. GlobalExceptionHandler 소스는 file_tree에 없으므로 추가, 수정할 수 없습니다.

## Affected Files
- `src/main/java/com/example/board/service/post_service.java`

## History
- 2026-05-13T02:15:58.379699 created from ticket=zmpops:auto-1778638555-902dcbb8
- 2026-05-13T02:16:02.941255 plan generated files=0
- 2026-05-13T02:20:45.530918 additional ticket=zmpops:incident:sample-java-test-004
- 2026-05-13T02:20:50.665285 plan generated files=1
- 2026-05-13T02:21:06.395452 resolved pr=https://github.com/SangjunPark54/sample-java/pull/3
- 2026-05-13T05:33:58.360188 additional ticket=zmpops:auto-1778650435-46f13ff0
- 2026-05-13T05:34:03.395060 plan generated files=1
- 2026-05-13T05:37:52.800208 additional ticket=zmpops:auto-1778650670-23d89267
- 2026-05-13T05:37:56.861893 plan generated files=1
- 2026-05-13T05:51:33.553083 additional ticket=zmpops:auto-1778651491-c6af1417
- 2026-05-13T05:51:37.985764 plan generated files=1
- 2026-05-13T05:57:06.425932 additional ticket=zmpops:auto-1778651824-36d1ffe3
- 2026-05-13T05:57:11.407457 plan generated files=1
