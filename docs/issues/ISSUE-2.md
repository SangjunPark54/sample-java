---
issue_key: ISSUE-2
repository: java-project
title: [zmpOps][HIGH] NullPointerException - console/zmp-core-api
status: in_progress
severity: high
created_at: 2026-05-13T02:15:58.380854
updated_at: 2026-05-13T06:35:24.326221
resolved_at: 2026-05-13T06:06:34.309243
related_tickets:
  - zmpops:auto-1778638555-902dcbb8
  - zmpops:incident:sample-java-test-004
  - zmpops:auto-1778650435-46f13ff0
  - zmpops:auto-1778650670-23d89267
  - zmpops:auto-1778651491-c6af1417
  - zmpops:auto-1778651824-36d1ffe3
  - zmpops:auto-1778652370-2800afe3
  - zmpops:auto-1778654115-3ee77bf0
related_prs:
  - https://github.com/SangjunPark54/sample-java/pull/3
  - https://github.com/SangjunPark54/sample-java/pull/4
  - https://github.com/SangjunPark54/sample-java/pull/5
affected_files:
  - src/main/java/com/example/board/service/post_service.java
---

# ISSUE-2 — [zmpOps][HIGH] NullPointerException - console/zmp-core-api

## Root Cause
PostService.getPost(Long) calls postRepository.findById(id).get() which throws NoSuchElementException when Optional is empty, propagating as NullPointerException.

## Solution Guide
1. Replace postRepository.findById(id).get() with orElseThrow(() -> new PostNotFoundException(id)).\n2. Add PostNotFoundException extending RuntimeException mapped to HttpStatus.NOT_FOUND.\n3. Add @ExceptionHandler(PostNotFoundException.class) in GlobalExceptionHandler returning 404.

## Plan (LLM-generated)
1. PostService.getPost(Long)에서 postRepository.findById(id).get() 사용을 orElseThrow(PostNotFoundException)으로 변경해야 합니다. 이를 위해 해당 메서드를 포함한 소스 파일인 post_service.java를 수정합니다.
2. PostNotFoundException 클래스를 생성하거나 추가 정의해야 합니다. 만약 별도 파일이 아니라면, post_service.java에 같이 정의합니다.
3. GlobalExceptionHandler에 @ExceptionHandler(PostNotFoundException.class)를 추가하여 404를 반환하도록 수정해야 합니다. 만약 글로벌 핸들러가 없거나 post_service.java에 포함되어 있다면 해당 파일을 수정합니다. 하지만, file_tree 상 handler 전용 파일이 없으므로, 서비스 계층에서 처리하거나 post_service.java에 추가하는 것이 적합합니다.

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
- 2026-05-13T05:57:27.727316 resolved pr=https://github.com/SangjunPark54/sample-java/pull/4
- 2026-05-13T06:06:14.462658 additional ticket=zmpops:auto-1778652370-2800afe3
- 2026-05-13T06:06:18.537554 plan generated files=1
- 2026-05-13T06:06:34.309016 resolved pr=https://github.com/SangjunPark54/sample-java/pull/5
- 2026-05-13T06:35:19.005051 additional ticket=zmpops:auto-1778654115-3ee77bf0
- 2026-05-13T06:35:24.326039 plan generated files=1
