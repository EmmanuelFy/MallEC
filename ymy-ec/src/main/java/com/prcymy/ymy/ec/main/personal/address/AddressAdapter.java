package com.prcymy.ymy.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.prcymy.ymy.ec.R;
import com.prcymy.ymy.net.RestClient;
import com.prcymy.ymy.net.callback.ISuccess;
import com.prcymy.ymy.ui.recycler.MultipleFields;
import com.prcymy.ymy.ui.recycler.MultipleRecyclerAdapter;
import com.prcymy.ymy.ui.recycler.MultipleViewHolder;
import com.prcymy.ymy.ui.recycler.MultipleltemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class AddressAdapter extends MultipleRecyclerAdapter{

    protected AddressAdapter(List<MultipleltemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleltemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()){
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteText = holder.getView(R.id.tv_address_delete);

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);


                //删除图标
                deleteText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //删除收货地址,将ID上传至服务器操作
                        remove(holder.getLayoutPosition());

                    }
                });



                break;

            default:
                break;
        }

    }
}
