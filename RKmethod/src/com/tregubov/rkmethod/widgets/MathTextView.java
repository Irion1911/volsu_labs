package com.tregubov.rkmethod.widgets;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

public class MathTextView extends TextView {

	public MathTextView(Context context) {
		super(context);
	}

	public MathTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MathTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setTextMath (String s) {
		String[][] reg = {{"\\^(\\(.+?\\)|[^\\s)]+)", "<sup><small>$1</small></sup>"}};
//				{"(\\(.+?\\)|[^\\s<]+)/(\\(.+?\\)|\\S+)", "<sup><u>$1</u></sup><sub>$2</sub>"}};
		
		String text = s
				.replace(" ", "")
				.replace("+", " + ")
				.replace("-", " - ")
				.replace("=", " = ")
				.replace("*", " * ")
				.replaceAll("'+", "<I>$0</I>")
				.replaceAll("([a-z])([^a-z]+)", "<I>$1</I>$2");
		
		for(String[] i:reg)
			while (text.matches(".*" + i[0] + ".*"))
				text = text.replaceAll(i[0], i[1]);
		
		text = text.replace(" * ", "");

		setText(Html.fromHtml(text));
	}

}
