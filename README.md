# CollegeTimeTableLayout
TimeTableLayout library

## 1. 프로젝트 설명
--------------------
> 대학교 시간표 형태의 time table을 편하게 짤 수 있게 하는 안드로이드 커스텀 뷰 라이브러리 프로젝트입니다.  
(개발 중)

## 2. 개발 환경
--------------------
- OS
  - Android
- Language
  - Java
- Super Class
  - GridLayout
  
## 3. 기능 설명
--------------------
- 스케줄 관련 기능
  - 스케줄 추가  
    ```java
    pulic void addSchedule(String text, String row_name, String column_name, int blocks)
    ```
    - text : 스케줄 내용
    - row_name : 행 이름
    - column_name : 열 이름
    - blocks : 차지하는 칸 수 (행 기준)
    - 예시 : addSchedule("운영체제", "1교시", "수", 2);
    - <img src=./img/screenshot_1.png width="250">
  - 스케줄 삭제  
    ```java
    public void deleteSchedule(String row_name, String column_name)
    ```
    - row_name : 행 이름
    - column_name : 열 이름
    - 예시 : deleteSchedule("1교시", "수");
  - 스케줄 수정  
    ```java
    public void modifySchedule(String row_name, String column_name, String change_text, int blocks)
    ```
    - row_name : 행 이름
    - column_name : 열 이름
    - change_text : 수정 스케줄 내용
    - blocks : 차지하는 칸 수
    - 예시 : modifySchedule("1교시", "수", "컴퓨터 구조", 3);
    
    
- cell 관련 기능
  - 모든 cell 객체 가져오기
    ```java
    public Cell[][] getAllCell()
    ```
  - cell 배경색 변경
    ```java 
    public void setCellColor(int cellColor)
    ```
  - cell 마진값 조정
    ```java
    public void setCellsMargin(int left, int top, int right, int bottom)
    ```
  - 특정 cell 찾기
    ```java
    public Cell findCell(String row_name, String column_name)
    ```
  - schedule 내용으로 cell 찾기
    ```java
    public Cell findCellWithText(String text)
    ```
