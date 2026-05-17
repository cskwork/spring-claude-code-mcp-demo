---
description: 변경된 코드를 강의 기준으로 리뷰 (Spring + OpenAPI + 보안)
---

방금 변경한 파일들을 다음 체크리스트로 리뷰해 주세요.

## 체크리스트

### Spring
- [ ] 생성자 주입을 쓰고 있는가 (필드 주입 금지)
- [ ] `@Service`·`@RestController`가 적절한가
- [ ] 도메인이 record로 표현 가능한가

### OpenAPI
- [ ] 모든 public REST endpoint에 `@Tag` + `@Operation` 있는가
- [ ] 4xx/5xx 응답이 있는 메서드에 `@ApiResponse` 명시됐는가
- [ ] record 필드에 `@Schema(description, example)` 채워졌는가

### 보안 (ch06부터 강화)
- [ ] 비밀값 하드코딩 없음 (`${...}` 환경변수 사용)
- [ ] PII 가능 필드가 응답에 노출되는가
- [ ] 로그에 비밀값/PII 흘리지 않는가

### 테스트
- [ ] 새 endpoint마다 MockMvc 테스트가 최소 happy + error 2건 있는가

문제 발견 시 구체적 위치(파일:라인)와 함께 짧게 제안해 주세요.
