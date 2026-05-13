---
issue_key: ISSUE-2
repository: java-project
title: [zmpOps][HIGH] NullPointerException - console/zmp-core-api
status: in_progress
severity: high
created_at: 2026-05-13T02:15:58.380854
updated_at: 2026-05-13T09:04:03.442945
resolved_at: 2026-05-13T08:28:05.420053
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
  - zmpops:auto-1778657649-e06f2f27
  - zmpops:auto-1778658088-4b605def
  - zmpops:auto-1778658359-26be05e6
  - zmpops:auto-1778660484-9430efb0
  - zmpops:auto-1778660847-d4232a6e
  - zmpops:auto-1778663031-da2ec2a5
related_prs:
  - https://github.com/SangjunPark54/sample-java/pull/3
  - https://github.com/SangjunPark54/sample-java/pull/4
  - https://github.com/SangjunPark54/sample-java/pull/5
  - https://github.com/SangjunPark54/sample-java/pull/6
  - https://github.com/SangjunPark54/sample-java/pull/7
  - https://github.com/SangjunPark54/sample-java/pull/8
  - https://github.com/SangjunPark54/sample-java/pull/9
  - https://github.com/SangjunPark54/sample-java/pull/10
  - https://github.com/SangjunPark54/sample-java/pull/11
  - https://github.com/SangjunPark54/sample-java/pull/12
  - https://github.com/SangjunPark54/sample-java/pull/13
  - https://github.com/SangjunPark54/sample-java/pull/14
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
1. postRepository.findById(id).get() 패턴을 orElseThrow(() -> new PostNotFoundException(id))로 변경해야 함. 이는 PostService의 getPost 메서드 내에서 이뤄져야 하므로 post_service.java를 수정해야 함.
2. PostNotFoundException을 새로 추가해야 하지만, file_tree에 새 파일 경로가 없으므로, 생성 대신 post_service.java 내에 내부 클래스로 임시 구현 또는 기존 예외 처리 검토(기존 파일만 지정).
3. PostNotFoundException에 대한 @ExceptionHandler 추가는 GlobalExceptionHandler에서 해야 하나, 해당 파일이 없음. controller/PostController.java에서 직접 처리하도록 임시 조치해야 하므로 포함.

따라서, 반드시 수정해야 하는 소스 파일은 아래와 같음.

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
- 2026-05-13T07:27:53.065043 resolved pr=https://github.com/SangjunPark54/sample-java/pull/9
- 2026-05-13T07:34:12.219957 additional ticket=zmpops:auto-1778657649-e06f2f27
- 2026-05-13T07:34:20.657507 plan generated files=2
- 2026-05-13T07:34:35.959647 resolved pr=https://github.com/SangjunPark54/sample-java/pull/10
- 2026-05-13T07:41:30.884979 additional ticket=zmpops:auto-1778658088-4b605def
- 2026-05-13T07:41:39.044646 plan generated files=1
- 2026-05-13T07:41:52.683156 resolved pr=https://github.com/SangjunPark54/sample-java/pull/11
- 2026-05-13T07:46:02.212374 additional ticket=zmpops:auto-1778658359-26be05e6
- 2026-05-13T07:46:09.060822 plan generated files=1
- 2026-05-13T07:46:25.347362 resolved pr=https://github.com/SangjunPark54/sample-java/pull/12
- 2026-05-13T08:21:27.321190 additional ticket=zmpops:auto-1778660484-9430efb0
- 2026-05-13T08:21:35.419847 plan generated files=2
- 2026-05-13T08:21:50.088838 resolved pr=https://github.com/SangjunPark54/sample-java/pull/13
- 2026-05-13T08:27:29.763273 additional ticket=zmpops:auto-1778660847-d4232a6e
- 2026-05-13T08:27:36.295607 plan generated files=2
- 2026-05-13T08:28:05.419971 resolved pr=https://github.com/SangjunPark54/sample-java/pull/14
- 2026-05-13T09:03:54.466195 additional ticket=zmpops:auto-1778663031-da2ec2a5
- 2026-05-13T09:04:03.442762 plan generated files=2
