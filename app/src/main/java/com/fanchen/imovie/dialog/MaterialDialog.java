package com.fanchen.imovie.dialog;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.fanchen.imovie.util.CornerUtils;
import com.fanchen.imovie.util.DisplayUtil;


/**
 * Material Design Dialog
 * @author fanchen
 */
public class MaterialDialog extends BaseAlertDialog<MaterialDialog> {
	private int vi1;
	private int vi2;

	public MaterialDialog(Context context) {
		super(context);
		/** default value */
		titleTextColor = Color.parseColor("#DE000000");
		titleTextSize_SP = 22f;
		contentTextColor = Color.parseColor("#8a000000");
		contentTextSize_SP = 16f;
		leftBtnTextColor = Color.parseColor("#383838");
		rightBtnTextColor = Color.parseColor("#468ED0");
		middleBtnTextColor = Color.parseColor("#00796B");
		/** default value */
	}

	public MaterialDialog(Context context, View view) {
		this(context);
		this.view = view;
	}

	public MaterialDialog(Context context, int viewid) {
		this(context);
		View inflate = LayoutInflater.from(context).inflate(viewid, null);
		this.view = inflate;
	}

	@Override
	public View onCreateView() {

		/** title */
		tv_title.setGravity(Gravity.CENTER_VERTICAL);
		tv_title.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
		tv_title.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		ll_container.addView(tv_title);

		if (view != null) {
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
			layoutParams.weight = 1;
			layoutParams.leftMargin = DisplayUtil.dip2px(context,10);
			layoutParams.rightMargin = DisplayUtil.dip2px(context,10);
			view.setLayoutParams(layoutParams);
			ll_container.addView(view);
		} else {
			/** content */
			
			tv_content.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(20));
			tv_content.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			ll_container.addView(tv_content);
		}

		/** btns */
		ll_btns.setGravity(Gravity.RIGHT);
		ll_btns.addView(tv_btn_left);
		ll_btns.addView(tv_btn_middle);
		ll_btns.addView(tv_btn_right);

		tv_btn_left.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
		tv_btn_right.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
		tv_btn_middle.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
		ll_btns.setPadding(dp2px(20), dp2px(0), dp2px(10), dp2px(10));

		ll_container.addView(ll_btns);

		return ll_container;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setUiBeforShow() {
		super.setUiBeforShow();
		/** set background color and corner radius */
		float radius = dp2px(cornerRadius_DP);
		ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor,
				radius));
		if (vi1 == View.GONE) {
			ll_container.removeView(tv_title);
			
		}
		if(vi2 == View.GONE){
			ll_container.removeView(ll_btns);
		}
		tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(radius,
				bgColor, btnPressColor, -2));
		tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(radius,
				bgColor, btnPressColor, -2));
		tv_btn_middle.setBackgroundDrawable(CornerUtils.btnSelector(radius,
				bgColor, btnPressColor, -2));
	}

	public void setTitleVisble(int vis) {
		vi1 = vis;
	}
	
	public void setButtonVisble(int vis) {
		vi2 = vis;
	}

	@Override
	public void dismiss() {
		if (!cancel) {
			super.dismiss();
		} else {
			if (touchView != null)
				super.dismiss();
		}
	}
}
