package com.akarbowy.epoxyexample.ui.helpers;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.akarbowy.epoxyexample.R;

public class ImagePreviewer {

    public void show(Context context, ImageView source) {
        BitmapDrawable background = ImagePreviewerUtils.getBlurredScreenDrawable(context, source.getRootView());

        View dialogView = LayoutInflater.from(context).inflate(R.layout.view_image_previewer, null);
        ImageView imageView = (ImageView) dialogView.findViewById(R.id.previewer_image);

        Drawable copy = source.getDrawable().getConstantState().newDrawable();
        imageView.setImageDrawable(copy);

        final Dialog dialog = new Dialog(context, R.style.ImagePreviewerTheme);
        dialog.getWindow().setBackgroundDrawable(background);
        dialog.setContentView(dialogView);
        dialog.show();

        source.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                if (dialog.isShowing()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    int action = event.getActionMasked();
                    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        dialog.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
