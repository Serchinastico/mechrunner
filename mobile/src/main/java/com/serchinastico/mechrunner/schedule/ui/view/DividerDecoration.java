package com.serchinastico.mechrunner.schedule.ui.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerDecoration extends RecyclerView.ItemDecoration {

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);

		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + 2;
			Paint paint = new Paint();
			paint.setARGB(20, 0, 0, 0);
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			c.drawRect(left, top, right, bottom, paint);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);

		outRect.left = 10;
		outRect.right = 10;
		outRect.bottom = 10;
		if (parent.getChildPosition(view) == 0) {
			outRect.top = 10;
		}
	}
}
