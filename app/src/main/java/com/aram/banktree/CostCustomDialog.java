package com.aram.banktree;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CostCustomDialog {
    private Context context;
    public CostCustomDialog(Context context){
        this.context=context;
    }

    public void callFunction(final Button costset_button){
        final Dialog dlg=new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.cost_custom_dialog);

        dlg.show();

        final EditText cost=(EditText)dlg.findViewById(R.id.cost);
        final Button cost_ok=(Button)dlg.findViewById(R.id.cost_ok);
        final Button cost_cancel=(Button)dlg.findViewById(R.id.cost_cancel);
        final String beforecost=((Newbook)Newbook.newbookcontext).cst;
        if(beforecost!=null&&beforecost.length()>0){
            cost.setText(beforecost);
        }

        if(((Newbook)Newbook.newbookcontext).costset==0){
            cost_ok.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    String aftercost=cost.getText().toString();
                    if(aftercost.length()==0){
                        Toast.makeText(context, "가격을 입력해주세요", Toast.LENGTH_LONG).show();
                    }
                    else if(aftercost.length()>0){
                        ((Newbook)Newbook.newbookcontext).cst=aftercost;
                        ((Newbook)Newbook.newbookcontext).costset=1;
                        costset_button.setText(aftercost+"원");
                        dlg.dismiss();
                    }
                }
            });
            cost_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Newbook)Newbook.newbookcontext).cst=null;
                    ((Newbook)Newbook.newbookcontext).costset=0;
                    costset_button.setText("가격설정");
                    dlg.dismiss();
                }
            });
        }
        else if(((Newbook)Newbook.newbookcontext).costset==1){
            cost_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String aftercost = cost.getText().toString();
                    if (aftercost.length() == 0) {
                        Toast.makeText(context, "가격을 입력해주세요", Toast.LENGTH_LONG).show();
                    } else {
                        ((Newbook) Newbook.newbookcontext).cst = aftercost;
                        ((Newbook) Newbook.newbookcontext).costset = 1;
                        costset_button.setText(aftercost+"원");
                        dlg.dismiss();
                    }
                }
            });
            cost_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Newbook) Newbook.newbookcontext).cst = beforecost;
                    ((Newbook) Newbook.newbookcontext).costset = 1;
                    costset_button.setText(beforecost+"원");
                    dlg.dismiss();
                }
            });
        }
    }
}
