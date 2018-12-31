package customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class textview_proxima_reg extends android.support.v7.widget.AppCompatTextView {

    public textview_proxima_reg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public textview_proxima_reg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public textview_proxima_reg(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proxima-nova-reg-two.otf");
            setTypeface(tf);
        }
    }

}
