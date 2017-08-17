package com.prcymy.mall.generators;

import com.example.annotations.EntryGenertor;
import com.prcymy.ymy.wechat.template.WXEntryTemplate;

/**
 * Created by Administrator on 2017/8/4.
 */
@EntryGenertor(
        packageName = "com.prcymy.mall.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {


}
