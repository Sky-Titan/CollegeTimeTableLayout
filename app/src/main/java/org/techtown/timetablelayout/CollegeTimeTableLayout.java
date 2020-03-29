package org.techtown.timetablelayout;

import android.content.Context;

import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class CollegeTimeTableLayout extends GridLayout {

    private static String TAG = "CollegeTimeTableLayout";
    private ArrayList<TextView> cells = new ArrayList<>();

    private String[] row_names,column_names;

    protected int cellMarginTop, cellMarginBottm, cellMarginLeft, cellMarginRight;
    private int cellTextColor;
    private int cellColor;//cell 배경 색

    public CollegeTimeTableLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public CollegeTimeTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CollegeTimeTableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public CollegeTimeTableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.collegetimetable_layout, this);

        if (attrs != null) {
            //attrs.xml에 정의한 스타일을 가져온다
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CollegeTimeTableLayout);
            cellColor = a.getColor(R.styleable.CollegeTimeTableLayout_cellColor, getResources().getColor(R.color.white,null));
            cellTextColor = a.getColor(R.styleable.CollegeTimeTableLayout_cellTextColor, getResources().getColor(R.color.black,null));
            cellMarginTop = a.getInt(R.styleable.CollegeTimeTableLayout_cellMarginTop, 5);
            cellMarginBottm = a.getInt(R.styleable.CollegeTimeTableLayout_cellMarginBottom, 5);
            cellMarginRight = a.getInt(R.styleable.CollegeTimeTableLayout_cellMarginRight, 5);
            cellMarginLeft = a.getInt(R.styleable.CollegeTimeTableLayout_cellMarginLeft, 5);
            a.recycle(); // 이용이 끝났으면 recycle() 호출
        }

        initRowColumnNames();

        //setBackgroundColor(getResources().getColor(R.color.cell_backgroud_color,null));
        //setOrientation(GridLayout.VERTICAL);

        addCells();
    }

    //row, column 이름 초기화
    private void initRowColumnNames()
    {
        row_names = new String[getRowCount()];
        column_names = new String[getColumnCount()];

        for(int i=0;i<getRowCount();i++)
            row_names[i] = i+"";
        for(int i=0;i<getColumnCount();i++)
            column_names[i] = i+"";
    }

    //row, column만큼 cell 추가
    private void addCells()
    {
        for(int i=0;i<getRowCount();i++)
        {
            for(int j=0;j<getColumnCount();j++)
            {
                Cell cell = new Cell(getContext());
                cell.setTag(row_names[i]+"-"+column_names[j]);//행열로 태그 설정

                if(i!=0 && j!=0)
                {
                    cell.setClickable(true);
                    cell.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG,"click cell");
                            if(((TextView)view).getText().toString().equals(""))//스케줄 없음
                            {
                                Toast.makeText(getContext(),"추가",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(),"삭제",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


                cell.setBackgroundColor(cellColor);//cell 배경색 설정
                cell.setTextColor(cellTextColor);//cell text 색 설정
                cell.setGravity(Gravity.CENTER);
                cell.setText("");

                if(i==0 && j!=0)
                    cell.setText(column_names[j]);
                if(j==0 && i!=0)
                    cell.setText(row_names[i]);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(i,1.0f), GridLayout.spec(j,1.0f));
                layoutParams.setGravity(Gravity.FILL);
                layoutParams.setMargins(cellMarginLeft,cellMarginTop,cellMarginRight,cellMarginBottm);
                cell.setLayoutParams(layoutParams);

                addView(cell);
            }
        }
    }

    //스케줄 추가
    public void addSchedule(String text, String row_name, String column_name, int blocks)
    {
        ArrayList<String> row_list = new ArrayList<>(Arrays.asList(row_names));
        int origin_index = row_list.indexOf(row_name);

        //cell 삭제
        for(int i=1;i<blocks;i++)
        {
            Cell cell= findCell(row_names[origin_index+i],column_name);
            cell.setVisibility(View.GONE);
        }

        //스케줄 추가 cell
        Cell schedule_cell = findCell(row_name,column_name);
        schedule_cell.setText(text);
        schedule_cell.setScheduled(true);
        //병합
        GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams)schedule_cell.getLayoutParams();
        layoutParams.rowSpec = GridLayout.spec(origin_index, blocks,1.0f);
        schedule_cell.setLayoutParams(layoutParams);
    }

    //스케줄 추가
    public void addSchedule(String text, String row_name, String column_name, int blocks, int backgroundColor)
    {
        ArrayList<String> row_list = new ArrayList<>(Arrays.asList(row_names));
        int origin_index = row_list.indexOf(row_name);

        //삭제 cell
        for(int i=1;i<blocks;i++)
        {
            Cell cell= findCell(row_names[origin_index+i],column_name);
            cell.setVisibility(View.GONE);
        }

        //스케줄 추가 cell
        Cell schedule_cell = findCell(row_name,column_name);
        schedule_cell.setText(text);
        schedule_cell.setScheduled(true);
        //cell 색깔 지정
        schedule_cell.setBackgroundColor(backgroundColor);

        //병합
        GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams)schedule_cell.getLayoutParams();
        layoutParams.rowSpec = GridLayout.spec(origin_index, blocks,1.0f);
        schedule_cell.setLayoutParams(layoutParams);
    }

    //스케줄 추가
    public void addSchedule(String text, String row_name, String column_name, int blocks, int backgroundColor, int textColor)
    {
        ArrayList<String> row_list = new ArrayList<>(Arrays.asList(row_names));
        int origin_index = row_list.indexOf(row_name);

        //삭제 cell
        for(int i=1;i<blocks;i++)
        {
            Cell cell= findCell(row_names[origin_index+i],column_name);
            cell.setVisibility(View.GONE);
        }

        //스케줄 추가 cell
        Cell schedule_cell = findCell(row_name,column_name);
        schedule_cell.setText(text);
        schedule_cell.setScheduled(true);
        //색지정
        schedule_cell.setBackgroundColor(backgroundColor);
        schedule_cell.setTextColor(textColor);

        //병합
        GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams)schedule_cell.getLayoutParams();
        layoutParams.rowSpec = GridLayout.spec(origin_index, blocks,1.0f);
        schedule_cell.setLayoutParams(layoutParams);
    }

    //모든 cell 가져옴
    public Cell[][] getAllCell()
    {
        Cell[][] list = new Cell[getRowCount()] [getColumnCount()];

        for(int i=0;i<getRowCount();i++)
        {
            for(int j=0;j<getColumnCount();j++)
                list[i][j] = findCell(row_names[i],column_names[j]);
        }
        return list;
    }

    public int getCellTextColor() {
        return cellTextColor;
    }

    public void setCellTextColor(int cellTextColor) {
        this.cellTextColor = cellTextColor;

        for(int i=0;i<getRowCount();i++)
        {
            for(int j=0;j<getColumnCount();j++)
            {
                Cell cell = (Cell)findViewWithTag(row_names[i]+"-"+column_names[j]);
                cell.setTextColor(cellTextColor);
            }
        }
    }

    //cell 배경 색
    public int getCellColor() {
        return cellColor;
    }

    //cell 배경 색
    public void setCellColor(int cellColor) {
        this.cellColor = cellColor;

        for(int i=0;i<getRowCount();i++)
        {
            for(int j=0;j<getColumnCount();j++)
            {
                Cell cell = (Cell) findViewWithTag(row_names[i]+"-"+column_names[j]);
                cell.setBackgroundColor(cellColor);//배경설정
            }
        }
    }

    //처음 세로줄
    public void setRowNames(String[] row_names)
    {
        for(int i=0;i<getRowCount();i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                Cell cell = (Cell) findViewWithTag(this.row_names[i] + "-" + this.column_names[j]);
                cell.setTag(row_names[i] + "-" + this.column_names[j]);

                if(j==0 && i!=0)//첫줄
                    cell.setText(row_names[i]);
            }
        }

        this.row_names = row_names.clone();

    }

    //처음 가로줄
    public void setColumnNames(String[] column_names)
    {
        for(int i=0;i<getRowCount();i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                Cell cell = (Cell) findViewWithTag(this.row_names[i] + "-" + this.column_names[j]);
                cell.setTag(this.row_names[i] + "-" + column_names[j]);

                if(i==0 && j!=0)//첫줄
                    cell.setText(column_names[j]);
            }
        }

        this.column_names = column_names;
    }
    //특정 row, column의 cell 반환
    public Cell findCell(String row, String column)
    {
        Cell cell = (Cell)findViewWithTag(row+"-"+column);
        return cell;
    }

    //해당 cell의 text를 보고 찾음
    public Cell findCellWithText(String text)
    {
        for(int i=0;i<getRowCount();i++)
        {
            for(int j=0;j<getColumnCount();j++)
            {
                Cell cell = (Cell)findViewWithTag(row_names[i]+"-"+column_names[j]);
                if(cell.getText().equals(text))
                    return cell;
            }
        }
        return null;
    }


    //마진 값
    public void setCellsMargin(int left, int top, int right, int bottom)
    {
        cellMarginLeft = left;
        cellMarginRight = right;
        cellMarginTop = top;
        cellMarginBottm = bottom;

        for(int i=0;i<getRowCount();i++)
        {
            for(int j=0;j<getColumnCount();j++)
            {
                Cell cell = (Cell)findViewWithTag(row_names[i]+"-"+column_names[j]);
                GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams)cell.getLayoutParams();

                layoutParams.setMargins(left,top,right,bottom);
                cell.setLayoutParams(layoutParams);

            }
        }
    }


    @Override
    public void setRowCount(int rowCount) {
        removeAllViews();

        super.setRowCount(rowCount);

        initRowColumnNames();
        addCells();
    }

    @Override
    public void setColumnCount(int columnCount) {
        removeAllViews();

        super.setColumnCount(columnCount);
        initRowColumnNames();
        addCells();
    }

    //Cell 객체 클래스
    private class Cell extends FrameLayout{

        private boolean isScheduled = false;
        private TextView textView;
        public Cell(Context context) {
            super(context);
            initView(context,null);
        }

        public Cell(Context context,AttributeSet attrs) {
            super(context, attrs);
            initView(context,attrs);
        }

        public Cell(Context context,AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView(context,attrs);
        }

        private void initView(Context context, AttributeSet attrs) {

            textView = new TextView(context);

            //ripple effect 적용
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue,true);
            int resId = typedValue.resourceId;
            textView.setBackgroundResource(resId);

            addView(textView);
        }

        public boolean isScheduled()
        {
            return isScheduled;
        }
        public void setScheduled(boolean isScheduled)
        {
            this.isScheduled = isScheduled;
        }
        public TextView getTextView() {
            return textView;
        }


        public void setGravity(int gravity)
        {
            textView.setGravity(gravity);
        }

        public void setTextColor(int color)
        {
            textView.setTextColor(color);
        }

        public void setText(String text)
        {
            textView.setText(text);
        }

        @Override
        public void setClickable(boolean clickable) {
            super.setClickable(clickable);
            textView.setClickable(clickable);
        }

        @Override
        public void setVisibility(int visibility) {
            super.setVisibility(visibility);
            textView.setVisibility(visibility);
        }

        @Override
        public void setOnClickListener(View.OnClickListener l) {
            textView.setOnClickListener(l);
        }

        public String getText()
        {
            return textView.getText().toString();
        }
    }

}
