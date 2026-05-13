---
issue_key: ISSUE-2
repository: java-project
title: [zmpOps][HIGH] NullPointerException - console/zmp-core-api
status: in_progress
severity: high
created_at: 2026-05-13T02:15:58.380854
updated_at: 2026-05-13T07:27:40.491110
resolved_at: 2026-05-13T07:09:23.813889
related_tickets:
  - zmpops:auto-1778638555-902dcbb8
  - zmpops:incident:sample-java-test-004
  - zmpops:auto-1778650435-46f13ff0
  - zmpops:auto-1778650670-23d89267
  - zmpops:auto-1778651491-c6af1417
  - zmpops:auto-1778651824-36d1ffe3
  - zmpops:auto-1778652370-2800afe3
  - zmpops:auto-1778654115-3ee77bf0
  - zmpops:auto-1778654693-5e460660
  - zmpops:auto-1778656138-795c1a56
  - zmpops:auto-1778656416-5c12203d
  - zmpops:auto-1778656613-dbd1731b
  - zmpops:auto-1778657251-306e0cd4
related_prs:
  - https://github.com/SangjunPark54/sample-java/pull/3
  - https://github.com/SangjunPark54/sample-java/pull/4
  - https://github.com/SangjunPark54/sample-java/pull/5
  - https://github.com/SangjunPark54/sample-java/pull/6
  - https://github.com/SangjunPark54/sample-java/pull/7
  - https://github.com/SangjunPark54/sample-java/pull/8
affected_files:
  - src/main/java/com/example/board/service/post_service.java
  - src/main/java/com/example/board/controller/PostController.java
---

# ISSUE-2 — [zmpOps][HIGH] NullPointerException - console/zmp-core-api

## Root Cause
PostService.getPost(Long) calls postRepository.findById(id).get() which throws NoSuchElementException when Optional is empty, propagating as NullPointerException.

## Solution Guide
1. Replace postRepository.findById(id).get() with orElseThrow(() -> new PostNotFoundException(id)).\n2. Add PostNotFoundException extending RuntimeException mapped to HttpStatus.NOT_FOUND.\n3. Add @ExceptionHandler(PostNotFoundException.class) in GlobalExceptionHandler returning 404.

## Plan (LLM-generated)
1. postRepository.findById(id).get()를 orElseThrow(PostNotFoundException)으로 교체해야 하므로 src/main/java/com/example/board/service/post_service.java를 수정한다.
2. PostNotFoundException 예외 클래스를 src/main/java/com/example/board/service/post_service.java에 추가(최상위 예외 폴더가 따로 없으므로).
3. GlobalExceptionHandler 혹은 전역 예외 핸들러 처리 메서드(@ExceptionHandler)를 src/main/java/com/example/board/controller/PostController.java에 추가/수정한다.

이외 파일은 영향 없음.

## Affected Files
- `src/main/java/com/example/board/service/post_service.java`
- `src/main/java/com/example/board/controller/PostController.java`

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
- 2026-05-13T06:35:39.348413 resolved pr=https://github.com/SangjunPark54/sample-java/pull/6
- 2026-05-13T06:44:55.939635 additional ticket=zmpops:auto-1778654693-5e460660
- 2026-05-13T06:45:00.236040 plan generated files=1
- 2026-05-13T06:45:16.349618 resolved pr=https://github.com/SangjunPark54/sample-java/pull/7
- 2026-05-13T07:09:01.547440 additional ticket=zmpops:auto-1778656138-795c1a56
- 2026-05-13T07:09:05.758940 plan generated files=1
- 2026-05-13T07:09:23.813635 resolved pr=https://github.com/SangjunPark54/sample-java/pull/8
- 2026-05-13T07:13:38.386254 additional ticket=zmpops:auto-1778656416-5c12203d
- 2026-05-13T07:13:43.544607 plan generated files=0
- 2026-05-13T07:16:57.149290 additional ticket=zmpops:auto-1778656613-dbd1731b
- 2026-05-13T07:17:00.679273 plan generated files=0
- 2026-05-13T07:27:34.980188 additional ticket=zmpops:auto-1778657251-306e0cd4
- 2026-05-13T07:27:40.490935 plan generated files=2
