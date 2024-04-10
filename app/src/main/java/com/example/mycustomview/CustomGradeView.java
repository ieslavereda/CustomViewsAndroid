package com.example.mycustomview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomGradeView extends ConstraintLayout {

    private ProgressBar progressBar;
    private TextView textView;

    public CustomGradeView(Context context) {
        super(context);

        init();
    }

    public CustomGradeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomGradeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public CustomGradeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_grade_view,this,true);
        progressBar = view.findViewById(R.id.puntuacionGraf);
        textView = view.findViewById(R.id.puntuacionNum);
    }
    public void init(AttributeSet attrs){
        init();

       if(attrs!=null) {
           int grade = getContext()
                   .obtainStyledAttributes(attrs, R.styleable.CustomGradeView)
                   .getInt(R.styleable.CustomGradeView_grade, 100);
           int barColor = getContext()
                   .obtainStyledAttributes(attrs, R.styleable.CustomGradeView)
                   .getColor(R.styleable.CustomGradeView_barColor, getContext().getTheme().getResources().getColor(com.google.android.material.R.color.design_default_color_primary,getContext().getTheme()));
           int textColor = getContext()
                   .obtainStyledAttributes(attrs, R.styleable.CustomGradeView)
                   .getColor(R.styleable.CustomGradeView_textColor, getContext().getTheme().getResources().getColor(com.google.android.material.R.color.design_default_color_primary,getContext().getTheme()));
           float textSize = getContext()
                   .obtainStyledAttributes(attrs, R.styleable.CustomGradeView)
                   .getDimensionPixelSize(R.styleable.CustomGradeView_textSize, 0);

           setGrade(grade);
           progressBar.getProgressDrawable().setColorFilter(barColor, PorterDuff.Mode.SRC_IN );
           textView.setTextColor(textColor);

           if(textSize>0)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
       }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(widthMeasureSpec!=0 || heightMeasureSpec!=0) {
            int size = Math.max(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(size, size);
        }
    }

    public void setGrade(int grade){

        if(grade>100) grade=100;
        if(grade<0) grade = 0;

        progressBar.setProgress(grade);
        textView.setText(String.valueOf(grade));
    }

}
