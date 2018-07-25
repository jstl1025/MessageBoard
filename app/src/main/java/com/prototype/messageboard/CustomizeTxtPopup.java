package com.prototype.messageboard;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class CustomizeTxtPopup implements View.OnClickListener{
    private Context context;
    private PopupWindow editTxtPopup;
    Button save,cancel;

    public CustomizeTxtPopup(Context context) {
        this.context = context;
    }

    public void PopupWindow(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.customize_text_popup, null);

        editTxtPopup = new PopupWindow(context);
        editTxtPopup.setContentView(layout);
        editTxtPopup.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        editTxtPopup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        editTxtPopup.setFocusable(true);
        editTxtPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        save = layout.findViewById(R.id.save);
        cancel = layout.findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                editTxtPopup.dismiss();
                break;

            case R.id.cancel:
                editTxtPopup.dismiss();
                break;
        }
    }
}
