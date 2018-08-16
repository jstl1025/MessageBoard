package com.prototype.messageboard;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class CustomizeTxtPopup implements View.OnClickListener{
    private Context context;
    private PopupWindow editTxtPopup;
    Button save,cancel, currentBtn;
    EditText txt;
    private static final String TAG = "Exception";

    public CustomizeTxtPopup(Context context) {
        this.context = context;
    }

    public void PopupWindow(Context context, Button customBtn){
        currentBtn = customBtn;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.customize_text_popup, null);
        txt=layout.findViewById(R.id.txt_message);

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
                String inputTxt = txt.getText().toString();
                try{
                    if(inputTxt.equals("")){
                        Toast.makeText(context,"No message entered, cannot save",Toast.LENGTH_SHORT).show();
                    }else{
                     Customize customizeTxt = new Customize(inputTxt);
                     currentBtn.setText(inputTxt);
                    }
                }catch (Exception e) {
                    Log.e(TAG,"Unable to save",e);
                }
                editTxtPopup.dismiss();
                break;

            case R.id.cancel:
                editTxtPopup.dismiss();
                break;
        }
    }
}
