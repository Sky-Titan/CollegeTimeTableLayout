package org.techtown.timetablelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Cell extends FrameLayout {

    private boolean isScheduled = false;
    private TextView textView;
    public Cell(Context context) {
        super(context);
        initView(context,null);
    }

    public Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public Cell(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public void setOnClickListener(OnClickListener l) {
        textView.setOnClickListener(l);
    }

    public String getText()
    {
        return textView.getText().toString();
    }
}
