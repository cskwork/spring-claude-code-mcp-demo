# Goal — spring-claude-code-mcp-demo 챕터 1-8 코드 완성

## Scope

- 이 리포 한 곳: `~/projects/spring-claude-code-mcp-demo/`
- 변경 허용: `app/**`, `mcp-server/**`, `docs/**`, 루트의 `build.gradle.kts`, `settings.gradle.kts`, `gradle/**`, `gradlew`
- 변경 금지: `.claude/CLAUDE.md`(컨벤션 고정), `README.md` 챕터 매핑 표, `.codex-goals/**`
- 외부 시스템 변경 금지: GitHub 리포 visibility, 원격 브랜치 강제 푸시, 협업자 추가

## Behavior (achieved 상태)

- 8개 챕터 코드가 각각 빌드·테스트 통과
- 챕터별 git tag 8개 생성: `ch01-baseline`, `ch02-claude-code`, `ch03-mcp-intro`, `ch04-mcp-server`, `ch05-batch-as-tool`, `ch06-security`, `ch07-rollout`, `ch08-wrapup`
- 각 챕터는 README.md 챕터 매핑 표와 1:1 일치
- 데모 도메인: 주문(Order) + 사용자(User) + 일별 통계(DailyStats)
- MCP 서버는 stdio 트랜스포트로 동작, Claude Code에서 `/mcp` 명령으로 핸드셰이크 성공
- 모든 public API에 OpenAPI(`@Operation`, `@Schema`) 어노테이션
- 보안 3패턴(Redaction Proxy · Scoped Token · Audit Sink)이 ch06 코드에 명확히 분리된 클래스로 존재

## Non-goals (refuse even if convenient)

- 영상 녹화·편집·인프런 제출 (사용자 영역)
- 운영 인프라 배포 (Kubernetes manifests · Helm 차트 등 — 부록 영역)
- Python·LangChain·LlamaIndex 등 비-Java 의존성 추가
- Lombok 추가 (강의 가시성 위해 금지)
- 네이티브 LLM SDK 직접 호출 추가 (MCP만 사용)

## Verification (objective-level)

다음 명령들의 출력이 전부 expected와 일치할 때 goal 완료:

```bash
# 1. 빌드 + 모든 테스트 통과
cd ~/projects/spring-claude-code-mcp-demo && ./gradlew clean build
# expected: BUILD SUCCESSFUL

# 2. 챕터 태그 8개
git tag --list 'ch??-*' | wc -l
# expected: 8

# 3. MCP 서버 핸드셰이크
./gradlew :mcp-server:run &
sleep 5
echo '{"jsonrpc":"2.0","method":"initialize","id":1}' | nc -U /tmp/mcp.sock | head -1
# expected: {"jsonrpc":"2.0","id":1,"result":{"protocolVersion":"2024-11-05",...}}

# 4. 보안 3패턴 클래스 존재
test -f mcp-server/src/main/java/com/example/mcp/security/RedactionProxy.java
test -f mcp-server/src/main/java/com/example/mcp/security/ScopedTokenFilter.java
test -f mcp-server/src/main/java/com/example/mcp/audit/AuditSink.java
# expected: 모두 exit 0
```

## Done-when checklist

- [ ] `./gradlew clean build` BUILD SUCCESSFUL
- [ ] `git tag --list 'ch??-*' | wc -l` 결과가 8
- [ ] MCP 서버 핸드셰이크 성공
- [ ] 보안 3 클래스(`RedactionProxy`, `ScopedTokenFilter`, `AuditSink`) 존재 + 단위 테스트
- [ ] `docs/chapter-01-overview.md` ~ `docs/chapter-08-wrapup.md` 모두 존재
- [ ] `Documentation.md` audit log에 8개 챕터 각각 SHA + tag 기록
- [ ] G1·G2·G3 HUMAN GATE 모두 `APPROVED:Gx` 토큰 받음
