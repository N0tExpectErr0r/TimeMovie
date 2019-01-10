package cn.n0texpecterr0r.timemovie.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * 吸附顶部的ItemDecoration，用于位置列表
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public class StickyDecoration extends RecyclerView.ItemDecoration {
    private ISticky mISticky;
    // 分隔条高度
    private int mRectHeight;
    // 文字TextSize
    private int mTextSize;
    private Paint mTextPaint;
    private Paint mRectPaint;
    private int mTextLeft;
    // 分隔条画笔
    private Paint mDividerPaint;

    public StickyDecoration(Context context, ISticky iSticky) {
        mISticky = iSticky;
        mRectHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                context.getResources().getDisplayMetrics());

        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15,
                context.getResources().getDisplayMetrics());
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#484848"));
        mTextPaint.setTextSize(mTextSize);
        mTextLeft = dpToPx(10,context);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(Color.parseColor("#F0F0F0"));

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setStyle(Paint.Style.FILL);
        mDividerPaint.setColor(Color.parseColor("#DDDDDD"));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float left = parent.getPaddingLeft();
            float right = parent.getWidth() - parent.getPaddingRight();
            float top = view.getTop() - 1F;
            float bottom = view.getTop();
            // Item分割线
            c.drawRect(left, top, right, bottom, mDividerPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();
        int itemCount = state.getItemCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        String preGroupTitle;
        String groupTitle = "";
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int pos = parent.getChildLayoutPosition(child);
            preGroupTitle = groupTitle;
            groupTitle = mISticky.getGroupTitle(pos);
            // 如果当前分组名和之前分组名一样，跳过此次循环
            if (groupTitle.equals(preGroupTitle)) {
                continue;
            }

            // 文字的基线，保证显示完全
            int textBaseLine = Math.max(mRectHeight, child.getTop());

            // 分组标题
            String title = mISticky.getGroupTitle(pos);

            int viewBottom = child.getBottom();
            // 防止数组越界
            if (pos + 1 < itemCount) {
                String nextGroupTitle = mISticky.getGroupTitle(pos + 1);
                // 当分组不一样  并且该组要向上移动时候
                if (!nextGroupTitle.equals(groupTitle) && viewBottom < textBaseLine) {
                    // 将上一个往上移动
                    textBaseLine = viewBottom;
                }
            }
            // 绘制边框
            c.drawRect(left, textBaseLine - mRectHeight, right, textBaseLine, mRectPaint);

            // 绘制文字并且实现文字居中
            int value = (int) Math.abs(mTextPaint.getFontMetrics().descent
                    + mTextPaint.getFontMetrics().ascent);
            c.drawText(title, left+mTextLeft, textBaseLine - (mRectHeight-value)/2, mTextPaint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildLayoutPosition(view);
        if (mISticky.isFirstPosition(pos)) {
            outRect.top = mRectHeight;
            outRect.bottom = 1;
        } else {
            outRect.bottom = 1;
        }
    }


    public interface ISticky {
        // 判断是否为第一个
        boolean isFirstPosition(int pos);

        // 返回当前组别的Title名称
        String getGroupTitle(int pos);
    }

    private int dpToPx(float dpValue, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
