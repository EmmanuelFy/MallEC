package com.prcymy.mall.generators;

import com.example.annotations.PayEntryGenerator;
import com.prcymy.ymy.wechat.template.WXPayEntryTemplate;

/**
 * Created by Administrator on 2017/8/4.
 */

@PayEntryGenerator(
        packageName = "com.prcymy.mall.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {



}
