# Implement — Ralph loop runbook

매 루프마다 이 순서대로 진행. 절차 변경 금지.

## 1. Read state
```
cat .codex-goals/SIDEJOB-2026-05/Prompt.md       # objective + verification (frozen)
cat .codex-goals/SIDEJOB-2026-05/Plan.md         # current milestone status
tail -50 .codex-goals/SIDEJOB-2026-05/Documentation.md   # last audit entry
git log --oneline -5
git tag --list 'ch??-*'
```

## 2. Pick one milestone
- Plan.md의 첫 번째 `pending` 또는 `in_progress` 행 한 개만.
- HUMAN_GATE 행이라면 → `grep -F 'APPROVED:Gx' Documentation.md`로 토큰 확인.
  - 토큰 있으면 → 상태 `completed`로 표시 후 다음 행
  - 토큰 없으면 → Documentation.md에 "Waiting for APPROVED:Gx (diff summary 첨부)" 적고 stop

## 3. Plan (그 마일스톤만)
- Acceptance criteria를 다시 읽음.
- 변경할 파일 목록을 3-7개로 적어둠.
- Non-goals와 충돌하는 변경이 있으면 즉시 stop, Documentation.md에 보고.

## 4. Act
- 변경 적용.
- 같은 챕터 안에서만 작업 — 이전 챕터 코드 수정 금지.

## 5. Validate
- Plan.md의 Validation command 그대로 실행. 출력을 캡처해서 Documentation.md에 그대로 첨부.
- 실패면 Stop-and-fix 룰 따름:
  - 1회만 재시도. 또 실패면 stop — 사용자에게 보고.

## 6. Commit + tag
```
git add -A
git commit -m "<type>(<scope>): <chXX> <one-line summary>"
git tag chXX-<name>
```
- type: feat (M1·M4·M5·M6·M9), refactor (M2 어노테이션 일괄), docs (M3·M7·M8), chore (M9 push)
- scope: `app`, `mcp-server`, `docs`, `build`

## 7. Update Plan + Documentation
- Plan.md 그 마일스톤의 Status를 `completed`로.
- Documentation.md에 append:
  ```
  ## M{N} — chXX-name — {YYYY-MM-DD HH:MM}
  - commit: {short SHA}
  - tag: chXX-name
  - validation:
    {validation command output, last 20 lines}
  - notes: {중요한 결정 한 줄}
  ```

## 8. Decide next
- 다음 행이 HUMAN_GATE → Documentation.md에 gate 요약 적고 stop.
- 다음 행이 일반 마일스톤 → loop 1번부터 다시.
- Plan.md가 모두 `completed` → Prompt.md `Done-when checklist` 다시 읽고, 모든 박스 체크되었는지 자체 검증. 한 항목이라도 실패 → 즉시 stop, 보고.

## 9. Stop conditions

다음 중 하나라도 → 즉시 stop:
- 토큰 예산 80% 도달
- 같은 마일스톤에서 2회 연속 validation 실패
- Non-goals 위반 시도
- 사용자가 `REJECTED:Gx` 토큰 기록
- `.claude/CLAUDE.md` 또는 README 챕터 매핑 표 수정 요구 발생
