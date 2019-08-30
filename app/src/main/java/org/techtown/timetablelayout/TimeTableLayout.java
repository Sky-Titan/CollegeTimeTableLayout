package org.techtown.timetablelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TimeTableLayout extends GridLayout {

    TextView blank_cell, first_cell, column_cell,row_cell;

    public TimeTableLayout(Context context) {
        super(context);
    }

    public TimeTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TimeTableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.timetable_layout, this, false);
        addView(v);

        first_cell = (TextView) findViewById(R.id.first_cell);
        column_cell = (TextView) findViewById(R.id.column_cell);
        row_cell = (TextView) findViewById(R.id.row_cell);
        blank_cell = (TextView) findViewById(R.id.blank_cell);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TimeTableLayout);

        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TimeTableLayout, defStyle, 0);
        setTypeArray(typedArray);

    }


    private void setTypeArray(TypedArray typedArray) {


        int bg_resID = typedArray.getResourceId(R.styleable.TimeTableLayout_background, R.drawable.cell);


        int symbol_resID = typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.login_naver_symbol);
        symbol.setImageResource(symbol_resID);

        int textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0);
        text.setTextColor(textColor);

        String text_string = typedArray.getString(R.styleable.LoginButton_text);
        text.setText(text_string);


        typedArray.recycle();

    }


}
