# ch03 — MCP가 무엇이고 왜 백엔드한테 중요한가

> 강의 5분 슬라이드용 노트. 코드 변경 없음.

## 1. MCP 한 줄
**Model Context Protocol** — Anthropic이 2024-2025에 표준화한, AI 클라이언트(에이전트) ↔ 컨텍스트·도구 제공자(서버) 간의 표준 프로토콜.

핵심 3요소:
- **Resources** — 읽을 수 있는 데이터 (파일·DB row·API 응답)
- **Tools** — 실행할 수 있는 동작 (검색·상태 변경·트리거)
- **Prompts** — 재사용 가능한 프롬프트 템플릿

## 2. HTTP API와의 차이

| HTTP API | MCP |
|---|---|
| 클라이언트가 무엇을 호출할지 미리 안다 | 에이전트가 동적으로 발견 |
| 응답 스키마 = OpenAPI 문서 | 자기서술적 (descriptions가 LLM 컨텍스트로 직접) |
| 호출 로그 = 우리가 수동 | 호출 로그 = 프로토콜 표준 audit |
| 권한 = 우리 코드 | 권한 = 도구 단위 스코프 (allowlist) |

MCP는 **HTTP API를 대체하지 않습니다**. 기존 백엔드 위에 얹는 *에이전트 친화* 레이어.

## 3. 왜 백엔드 개발자한테 중요한가

- LLM·에이전트 도구가 사내 데이터를 **안전하게** 만지려면 결국 백엔드가 진입점
- HTTP API를 그대로 노출하면 **권한 폭증·감사 부재** 위험 — MCP는 도구 단위 스코프·감사가 표준
- 운영팀·CISO 입장에서 **승인 가능한 형태**로 LLM 트래픽을 받기 위한 가장 짧은 길

## 4. 어떤 시나리오에 쓰는가 (강의 데모 도메인)

| 시나리오 | 노출 방식 |
|---|---|
| 콜센터 상담사가 "최근 30일 주문 요약" 자연어 질의 | MCP Resource: `orders/recent` |
| 상담사가 "주문 N번 상태 환불로 바꿔" 명령 | MCP Tool: `update_status(orderId, status)` (스코프: 환불 가능 등급만) |
| 야간 배치 재실행 트리거 | MCP Tool: `recompute_stats()` (스코프: 운영자) |

## 5. 생태계 상태 (2026-05 기준)

- **클라이언트**: Claude Code, Codex CLI, Cursor, Continue 등 빠르게 채택
- **서버 SDK**: TypeScript, Python 우선. Java용은 `spring-ai-mcp` (Spring AI 0.x), 별도 `mcp-spring-server` 등 활발
- **운영 도입**: 2025년 후반부터 엔터프라이즈 PoC 시작, 2026년 본격 도입 검토
- **표준 위치**: 아직 빠르게 변하므로 강의는 *원칙* 위주, 도구·라이브러리는 보충 자료에서 갱신

## 6. 다음 챕터 예고

- ch04: 위 3가지 도구를 실제 Spring Boot 모듈로 구현
- ch05: Spring Batch Job을 MCP 도구로 래핑
- ch06: 운영 안전성 3패턴 (Redaction · Scoped · Audit)
