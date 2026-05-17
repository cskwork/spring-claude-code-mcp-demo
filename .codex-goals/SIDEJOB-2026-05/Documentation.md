# Documentation — audit log + APPROVED token landing zone

> Codex `/goal` Ralph 루프가 매 마일스톤 완료 시 이 파일에 append 합니다.
> 사용자(채성국)는 HUMAN GATE 도달 시 `APPROVED:Gx — <한 줄 메모>` 토큰을 기록합니다.

## Approve / Reject 토큰 형식

```
APPROVED:G1 — diff 검토 완료, ch04 푸시 진행 OK
APPROVED:G2 — 보안 3패턴 충분, 진행
APPROVED:G3 — 최종 검수 완료, 원격 푸시 OK
```

거절 시:
```
REJECTED:G2 — RedactionProxy의 정규식이 한국어 주민번호 패턴 누락. plan 수정 후 재요청.
```

토큰은 한 줄로 적고, 그 다음 라인부터는 자유 코멘트 가능.

---

## 시작 상태 (2026-05-17)

- 리포: `~/projects/spring-claude-code-mcp-demo/`
- 초기 commit: `8d96e30 chore: initial scaffold — modules, CLAUDE.md, chapter plan`
- 원격: (생성 직후 설정)
- 모듈: `app/` (Spring Boot 메인), `mcp-server/` (MCP 서버)
- 챕터 태그: (없음 — M1부터 시작)

## 핸드오프 컨텍스트

- vault에 강의 outline: `[[inflearn-mini-course-outline-2026-05]]`
- 강의 부제: "Spring 백엔드에 Claude Code · MCP 붙이기 — 60분 실습"
- 가격대: 5-9만 원 (인프런 자동 책정)
- 데모 레포 매핑은 `README.md`의 챕터 표가 단일 진실 소스

## Audit entries

(Codex가 여기에 append. 초기는 비어 있음.)
