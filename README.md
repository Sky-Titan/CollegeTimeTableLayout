# CollegeTimeTableLayout
TimeTableLayout library

## 1. 프로젝트 설명
--------------------
> 대학교 시간표 형태의 time table을 편하게 짤 수 있게 하는 안드로이드 커스텀 뷰 라이브러리 프로젝트입니다.
> GridLayout을 기반으로 만들어졌습니다.
> 개발 중 단계

## 2. 개발 환경
--------------------
- OS
  - Android
- Language
  - Java
  
## 3. 기능 설명
--------------------
- 스케줄 관련 기능
  - 스케줄 추가 addSchedule(String text, String row_name, String column_name, int blocks)
    - text : 스케줄 내용
    - row_name : 행 이름
    - column_name : 열 이름
    - blocks : 차지하는 칸 수 (행 기준)
    - 예시 : addSchedule("운영체제", "1교시", "수", 2);
