---
issue_key: ISSUE-2
repository: java-project
title: [zmpOps][HIGH] NullPointerException - console/zmp-core-api
status: in_progress
severity: high
created_at: 2026-05-13T02:15:58.380854
updated_at: 2026-05-13T02:20:50.665611
resolved_at: -
related_tickets:
  - zmpops:auto-1778638555-902dcbb8
  - zmpops:incident:sample-java-test-004
related_prs:[]
affected_files:
  - src/main/java/com/example/board/service/post_service.java
---

# ISSUE-2 — [zmpOps][HIGH] NullPointerException - console/zmp-core-api

## Root Cause
PostService.getPost(Long) calls postRepository.findById(id).get() which throws NoSuchElementException when Optional is empty, propagating as NullPointerException.

## Solution Guide
1. Replace postRepository.findById(id).get() with orElseThrow(() -> new PostNotFoundException(id)).\n2. Add PostNotFoundException extending RuntimeException mapped to HttpStatus.NOT_FOUND.\n3. Add @ExceptionHandler(PostNotFoundException.class) in GlobalExceptionHandler returning 404.

## Plan (LLM-generated)
1. PostService.getPost(Long)에서 postRepository.findById(id).get()을 orElseThrow(PostNotFoundException)으로 대체해야 하므로 post_service.java를 수정한다.
2. PostNotFoundException 커스텀 예외 클래스를 추가하거나 정의해야 하지만, 지정된 파일 트리에 해당 클래스 파일이 없으므로, 파일 추가가 아닌 post_service.java 내에서 내부 클래스로 우선 정의한다(파일 추가 불가 규칙상).
3. GlobalExceptionHandler에 PostNotFoundException 매핑 @ExceptionHandler를 추가해야 하지만, 파일 트리에 handler/exception 디렉터리나 GlobalExceptionHandler가 존재하지 않아 수정 불가.

따라서 실제로 반드시 수정이 필요한 파일은 post_service.java 뿐이다.

## Affected Files
- `src/main/java/com/example/board/service/post_service.java`

## History
- 2026-05-13T02:15:58.379699 created from ticket=zmpops:auto-1778638555-902dcbb8
- 2026-05-13T02:16:02.941255 plan generated files=0
- 2026-05-13T02:20:45.530918 additional ticket=zmpops:incident:sample-java-test-004
- 2026-05-13T02:20:50.665285 plan generated files=1
