---
issue_key: ISSUE-1
repository: sample-java
title: [verify] NullPointerException - sample-java 게시판
status: in_progress
severity: high
created_at: 2026-09-14T06:21:29.083239+00:00
updated_at: 2026-09-14T06:40:43.979388+00:00
resolved_at: -
related_tickets:
  - manual:verify-sample-java-001
  - manual:verify-e2e-002
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
1. root_cause에 따라 null 키 참조가 발생하는 CryptoUtil에서 null 검사 로직을 추가해야 합니다. 2. 다른 파일은 직접적으로 연관없으므로 수정할 필요 없음.

## Affected Files
- `src/main/java/com/example/board/util/CryptoUtil.py`

## History
- 2026-09-14T06:21:29.081629+00:00 created from ticket=manual:verify-sample-java-001
- 2026-09-14T06:21:35.412858+00:00 plan generated files=1
- 2026-09-14T06:40:38.268147+00:00 additional ticket=manual:verify-e2e-002
- 2026-09-14T06:40:43.979373+00:00 plan generated files=1
