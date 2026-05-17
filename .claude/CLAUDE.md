# CLAUDE.md — spring-claude-code-mcp-demo

## Project context
인프런 강의 *"Spring 백엔드에 Claude Code · MCP 붙이기 — 60분 실습"* 의 데모 레포.
각 챕터에 대응하는 git tag(`ch01-*` ~ `ch08-*`)가 있고, 강의 진도와 1:1 매칭됨.

## Stack
- Java 21
- Spring Boot 3.x
- Gradle (Kotlin DSL)
- Modules: `app/`, `mcp-server/`
- Test: JUnit 5 + Spring Boot Test

## Conventions
- Google Java Style, 4-space indent
- Lombok 금지 (강의 가시성 위해 명시적 getter/setter)
- 모든 public API에 OpenAPI 어노테이션
- Exception은 도메인별 sealed hierarchy

## What this repo demonstrates
- Spring 백엔드 ↔ Claude Code 통합 패턴
- MCP 서버 구현 (도구 · 자원 · 프롬프트)
- 엔터프라이즈 보안 3패턴: Redaction Proxy, Scoped Token, Audit Sink

## What NOT to do
- 네이티브 LLM SDK 직접 호출 추가 금지 — MCP만 사용
- Python 의존성 추가 금지 — Java 단독 유지
- 챕터 tag (`ch01-*` ~ `ch08-*`) 보존 — 강의 진도와 매칭됨
- `app/` 와 `mcp-server/` 간 직접 의존 금지 — MCP 프로토콜로만 통신

## Demo tag plan
```
ch01-baseline       # 주문 조회 API 단독
ch02-claude-code    # .claude/CLAUDE.md 추가 + OpenAPI 어노테이션 일괄 적용
ch03-mcp-intro      # (코드 변경 없음)
ch04-mcp-server     # mcp-server 모듈 추가, 도구 3개
ch05-batch-as-tool  # app/batch + MCP 도구 래퍼
ch06-security       # Redaction/Scoped/Audit 3패턴 적용
ch07-rollout        # (코드 변경 없음)
ch08-wrapup         # README 정리 + 다음 단계 안내
```
