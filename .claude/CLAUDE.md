# CLAUDE.md — spring-claude-code-mcp-demo

## Project context
인프런 강의 *"Spring 백엔드에 Claude Code · MCP 붙이기 — 60분 실습"* 의 데모 레포.
각 챕터에 대응하는 git tag(`ch01-*` ~ `ch08-*`)가 있고, 강의 진도와 1:1 매칭됨.

## Stack
- Java 17
- Spring Boot 3.4.x
- Gradle 8.10 (Kotlin DSL)
- Modules: `app/`, `mcp-server/` (ch04부터)
- Test: JUnit 5 + Spring Boot Test (MockMvc)
- API doc: springdoc-openapi 2.6.x → `/swagger-ui.html`

## Conventions
- 4-space indent, Google Java Style
- Lombok 금지 (강의 가시성 — getter/setter는 record로 우회)
- 모든 public REST endpoint:
  - 클래스에 `@Tag(name, description)`
  - 메서드에 `@Operation(summary, description)`
  - 4xx/5xx 응답이 있으면 `@ApiResponse` 명시
- 도메인은 record (불변), 서비스는 `@Service` + 생성자 주입
- Exception은 도메인별 sealed hierarchy (ch04부터 도입)

## What this repo demonstrates
- Spring 백엔드 ↔ Claude Code 통합 패턴
- MCP 서버 구현 (도구 · 자원 · 프롬프트)
- 엔터프라이즈 보안 3패턴: Redaction Proxy, Scoped Token, Audit Sink

## What NOT to do
- 네이티브 LLM SDK 직접 호출 추가 금지 — MCP만 사용
- Python 의존성 추가 금지 — Java 단독 유지
- 챕터 tag (`ch01-*` ~ `ch08-*`) 보존 — 강의 진도와 매칭됨
- `app/` 와 `mcp-server/` 간 직접 의존 금지 — MCP 프로토콜로만 통신
- `application.yml`에 비밀값 하드코딩 금지 — 환경변수 `${...}` 사용

## Quick commands
```bash
./gradlew :app:bootRun         # 실행
./gradlew :app:test            # 테스트
./gradlew :app:assemble        # JAR 빌드
```

## Demo tag plan
```
ch01-baseline       # 주문 조회 API 단독
ch02-claude-code    # OpenAPI 어노테이션 일괄 + .claude/commands
ch03-mcp-intro      # (코드 변경 없음)
ch04-mcp-server     # mcp-server 모듈 추가, 도구 3개
ch05-batch-as-tool  # app/batch + MCP 도구 래퍼
ch06-security       # Redaction/Scoped/Audit 3패턴 적용
ch07-rollout        # (코드 변경 없음)
ch08-wrapup         # README 정리 + 다음 단계 안내
```
