package com.tregubov.rkmethod.adapters;

import android.content.Context;
import android.view.View;

import com.tregubov.rkmethod.math.Target;
import com.tregubov.rkmethod.widgets.MathTextView;

public class TargetAdapter extends AbstractListAdapter<Target> {

	public TargetAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView (int position, View convertView, Target bean) {
		if(convertView == null)
			convertView = new MathTextView(getContext());
		
		((MathTextView)convertView).setTextMath(bean.getName());
		
		return convertView;
	}

}
