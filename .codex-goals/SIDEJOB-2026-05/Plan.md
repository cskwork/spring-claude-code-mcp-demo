# Plan — chapter milestones + HUMAN GATEs

각 행의 **Validation command** 출력이 expected와 일치할 때 advance.

| # | Milestone | Deliverable | Acceptance | Validation command | Stop-and-fix | Status |
|---|---|---|---|---|---|---|
| M1 | ch01-baseline | Spring Boot 베이스라인 (`app/` 단독, Order REST API GET `/orders/{id}`, GET `/orders?...`) + 단위 테스트 | `./gradlew :app:test` PASS | `cd ~/projects/spring-claude-code-mcp-demo && ./gradlew :app:test && git tag -l ch01-baseline` | 실패 시 controller·service·repo 계층 재점검; 1회 재시도 후 stop | pending |
| M2 | ch02-claude-code | OpenAPI 어노테이션 일괄 추가 + `.claude/commands/review.md` 슬래시 커맨드 + 변경 diff 200줄 이내 | `./gradlew :app:test` PASS, OpenAPI yaml 생성 확인 | `./gradlew :app:openapi && test -f app/build/openapi/openapi.yaml` | 어노테이션 누락 클래스를 grep으로 찾아 일괄 추가 | pending |
| M3 | ch03-mcp-intro | `docs/chapter-03-mcp-intro.md` 만 추가 (코드 변경 없음) | 문서 존재 + README 매핑과 일치 | `test -f docs/chapter-03-mcp-intro.md && git diff ch02-claude-code..HEAD --stat` | (해당 없음 — 문서 단독) | pending |
| **G1** | **HUMAN GATE — ch04 푸시 전 검토** | 사용자가 `git diff ch03-mcp-intro..HEAD` 검토 후 `APPROVED:G1` 기록 | `APPROVED:G1` 토큰이 Documentation.md에 존재 | `grep -F 'APPROVED:G1' .codex-goals/SIDEJOB-2026-05/Documentation.md` | reject 시 작업 중단; 이유에 따라 plan 수정 후 재요청 | pending |
| M4 | ch04-mcp-server | `mcp-server/` 모듈 + 도구 3개 (`get_user`, `get_order`, `update_order_status`) + stdio 트랜스포트 | MCP 핸드셰이크 성공 + 도구 3개 응답 | `./gradlew :mcp-server:test` | jsonrpc 메시지 형식, schema 검증 실패 시 official MCP spec 참조 | pending |
| M5 | ch05-batch-as-tool | `app/batch/DailyStatsJob` + `BatchJobLauncherTool` MCP 래퍼 + 파라미터 화이트리스트 | `./gradlew test` PASS + Job 실행 결과 MCP 도구로 반환 | `./gradlew :app:test --tests '*DailyStatsJob*' && ./gradlew :mcp-server:test --tests '*BatchJobLauncher*'` | 화이트리스트 누락 시 보안 risk — 즉시 stop, 리뷰 후 재시작 | pending |
| **G2** | **HUMAN GATE — 보안 코드 푸시 전 검토** | `git diff ch04..HEAD` + 보안 3패턴 클래스 코드 사용자 검토 후 `APPROVED:G2` | `APPROVED:G2` 토큰 존재 | `grep -F 'APPROVED:G2' .codex-goals/SIDEJOB-2026-05/Documentation.md` | reject 시 보안 패턴 재설계 — 외부 자료(OWASP) 참조 | pending |
| M6 | ch06-security | `RedactionProxy`, `ScopedTokenFilter`, `AuditSink` 3 클래스 + 각각 단위 테스트 ≥ 3 케이스 | 3 클래스 + 9 테스트 PASS | `./gradlew :mcp-server:test --tests '*Redaction*' '*ScopedToken*' '*Audit*'` | 테스트 누락 시 신규 케이스 추가 — refactor 금지 | pending |
| M7 | ch07-rollout | `docs/chapter-07-rollout.md` — Week 1~4 도입 로드맵 + KPI 3개 (코드 변경 없음) | 문서 존재 + 챕터 7 README 매핑과 일치 | `test -f docs/chapter-07-rollout.md` | (해당 없음) | pending |
| M8 | ch08-wrapup | README 최종 정리 + 다음 단계 안내 섹션 + 전체 빌드 통과 | `./gradlew clean build` BUILD SUCCESSFUL | `cd ~/projects/spring-claude-code-mcp-demo && ./gradlew clean build` | 빌드 실패 시 stop — 이전 챕터 회귀 의심 | pending |
| **G3** | **HUMAN GATE — 최종 push 전** | 사용자가 모든 챕터 태그·문서·빌드 결과 종합 검토 후 `APPROVED:G3` | `APPROVED:G3` 토큰 존재 | `grep -F 'APPROVED:G3' .codex-goals/SIDEJOB-2026-05/Documentation.md` | reject 시 지적된 챕터 재작업 | pending |
| M9 | push to remote | `git push origin main --follow-tags` | 원격 main + 8개 태그 모두 푸시됨 | `git ls-remote origin 'refs/tags/ch*' | wc -l` → 8 | push 거절 시 충돌 확인 → `git pull --rebase` 후 재시도 | pending |

## Token budget
2,000,000 (medium goal — 약 1-3시간 codex 실행 예상)

## 핵심 규칙

- 한 챕터 안에서 변경은 **그 챕터에서 새로 등장하는 것만**. 이전 챕터의 코드 수정은 회귀로 간주 — stop-and-fix.
- 각 M 끝에 `git tag chXX-name` + `Documentation.md`에 SHA·tag·테스트 결과 추가.
- HUMAN GATE 도달 시 그 시점의 `git diff` 요약을 `Documentation.md`에 적고 사용자 알림.
