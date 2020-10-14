# CollegeTimeTableLayout
*대학시간표 레이아웃 라이브러리*
> CollegeTimeTable Layout Library

<img src=./img/1_mockup.png width="500">

## About
------------------
- **Blog :** https://skytitan.tistory.com/54?category=940117
- **Developer E-mail :** er65119@naver.com


## Project
--------------------
대학교 시간표 형태의 time table layout을 편하게 구성하게 해주는 안드로이드 커스텀 뷰 라이브러리 프로젝트입니다.  
(개발 중)  

> This is an Android Custom View Library project that allows you to conveniently configure time table layout in the form of a university timetable.
(Developing)

## Develop Environment
--------------------
### 1. OS
- Android  

### 2. Language
- Java  

### 3. Super Class
  - GridLayout

  
## Functions
--------------------
### 1. Schedule Functions
#### Schedule add  
  ```java
  pulic void addSchedule(String text, String row_name, String column_name, int blocks)
  ```
- text : 스케줄 내용
- row_name : 행 이름
- column_name : 열 이름
- blocks : 차지하는 칸 수 (행 기준)
- 예시 : addSchedule("운영체제", "1교시", "수", 2);
#### Schedule delete  
```java
public void deleteSchedule(String row_name, String column_name)
```
- **row_name :** 행 이름
- **column_name :** 열 이름
- **예시 :** deleteSchedule("1교시", "수");
#### Schedule Modify  
```java
public void modifySchedule(String row_name, String column_name, String change_text, int blocks)
```
- **row_name :** 행 이름
- **column_name :** 열 이름
- **change_text :** 수정 스케줄 내용
- **blocks :** 차지하는 칸 수
- **예시 :** modifySchedule("1교시", "수", "컴퓨터 구조", 3);
        
### 2. Cell Functions
#### Get All Cells
```java
public Cell[][] getAllCell()
```
#### Change Cell Background Color
```java 
public void setCellColor(int cellColor)
```
#### Configure Cell Margin
```java
public void setCellsMargin(int left, int top, int right, int bottom)
```
#### Find Specific Cell
```java
public Cell findCell(String row_name, String column_name)
```
#### Find Specific Cell using Schedule content
```java
public Cell findCellWithText(String text)
```


## xml Examples
--------------
>
```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- activity_main.xml -->
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    >

    <org.techtown.timetablelayout.CollegeTimeTableLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:cellColor="@color/white"
        android:background="@color/cell_backgroud_color"
        android:rowCount="5"
        android:columnCount="3"/>

</LinearLayout>
```
> <img src=./img/screenshot_2.png width="250">


## Destributed Package Manual
---------------
rootProject의 build.gradle
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" } //추가
    }
}
```
depedencies 추가
```gradle
dependencies {
    implementation 'com.github.Sky-Titan:CollegeTimeTableLayout:{최신 태그 버전}' 
    //현재 릴리즈 버전 태그 : 0.3-test
}
```
