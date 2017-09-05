package com.prcymy.ymy.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.prcymy.ymy.delegates.MallDelegate;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.ec.main.personal.list.ListBean;
import com.prcymy.ymy.utils.Date.DateDialogUtil;

/**
 * Created by Administrator on 2017/9/4.
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final MallDelegate DELEGATE;
    private String[] mGender = new String[]{"男","女","保密"};

    public UserProfileClickListener(MallDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, final int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                //拍照或选择图片

                break;
            case 2:
                //姓名
                final MallDelegate nameDelegate = bean.getmDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                //性别
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGender[which]);
                        dialogInterface.cancel();
                    }
                });
                break;
            case 4:

                //年龄日期
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListner(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });

                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGender,0,listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
