package com.diabin.fastec.example.generators;

import com.flj.latte.annotations.PayEntryGenerator;
import com.flj.latte.wechat.templates.WXPayEntryTemplate;

/**
 *  2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.diabin.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
