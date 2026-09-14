---
issue_key: ISSUE-1
repository: sample-java
title: [verify] NullPointerException - sample-java 게시판
status: in_progress
severity: high
created_at: 2026-09-14T06:21:29.083239+00:00
updated_at: 2026-09-14T06:21:35.412872+00:00
resolved_at: -
related_tickets:
  - manual:verify-sample-java-001
related_prs:[]
affected_files:
  - src/main/java/com/example/board/util/CryptoUtil.py
---

# ISSUE-1 — [verify] NullPointerException - sample-java 게시판

## Root Cause
CryptoUtil 에서 null 키 참조

## Solution Guide
키 null 검사 추가

## Plan (LLM-generated)
1. CryptoUtil에서 null 키 참조로 인한 NullPointerException이 발생하므로, 키에 대한 null 검사를 추가해야 한다. 2. file_tree에 src/main/java/com/example/board/util/CryptoUtil.py만이 실제 해당 코드 파일로 존재한다. 3. 이 파일 외에는 CryptoUtil에 해당되는 다른 소스 파일이 없으므로, 해당 파일만 수정하면 된다.

## Affected Files
- `src/main/java/com/example/board/util/CryptoUtil.py`

## History
- 2026-09-14T06:21:29.081629+00:00 created from ticket=manual:verify-sample-java-001
- 2026-09-14T06:21:35.412858+00:00 plan generated files=1
