# Spring × Claude Code × MCP — Demo

> 인프런 강의 *"Spring 백엔드에 Claude Code · MCP 붙이기 — 60분 실습"* 의 데모 레포입니다.
> 강의 챕터별 git tag가 있어, 원하는 시점 코드 상태로 체크아웃해서 따라갈 수 있습니다.

## 강의 매핑

| Chapter | Tag | Topic | Status |
|---|---|---|---|
| 1 | `ch01-baseline` | Spring Boot 베이스라인 — 주문 조회 API | ✅ |
| 2 | `ch02-claude-code` | Claude Code 도입 · `CLAUDE.md` 컨벤션 · OpenAPI 어노테이션 | ✅ |
| 3 | `ch03-mcp-intro` | MCP 개념 정리 (코드 없음) | ✅ |
| 4 | `ch04-mcp-server` | MCP 서버 — 도구 3개 (사용자/주문/상태) | ⏳ |
| 5 | `ch05-batch-as-tool` | Spring Batch Job을 MCP 도구로 노출 | ⏳ |
| 6 | `ch06-security` | Redaction Proxy · Scoped Token · Audit Sink | ⏳ |
| 7 | `ch07-rollout` | 사내 도입 30일 로드맵 (코드 없음) | ⏳ |
| 8 | `ch08-wrapup` | 마무리 + 다음 단계 | ⏳ |

## 구조

```
.
├── .claude/
│   ├── CLAUDE.md          # 프로젝트 컨벤션 — Claude Code 자동 로드
│   └── commands/          # 슬래시 커맨드
├── app/                   # 메인 Spring Boot 앱
├── mcp-server/            # MCP 서버 (Spring Boot)
└── docs/                  # 챕터별 보충 자료
```

## Quick Start

```bash
./gradlew :app:bootRun         # http://localhost:8080/api/orders
./gradlew :app:test            # 3 cases
# mcp-server는 ch04부터
```

빠른 확인:

```bash
curl -s http://localhost:8080/api/orders | jq .
curl -s http://localhost:8080/api/orders/1 | jq .
curl -i http://localhost:8080/api/orders/9999      # 404
open http://localhost:8080/swagger-ui.html         # ch02부터
```

## 강의 / 강사

- 강의: https://inflearn.com/course/spring-claude-code-mcp (출시 예정)
- 강사: 채성국 — 7년차 백엔드, AI 통합 TF Lead
- GitHub: https://github.com/cskwork · Contact: csk917work@gmail.com

## License

MIT — 강의 수강 여부와 무관하게 자유롭게 fork 가능.
