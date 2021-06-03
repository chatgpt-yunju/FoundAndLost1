package com.flj.latte.ec.main;

import android.graphics.Color;

import com.flj.latte.delegates.bottom.BaseBottomDelegate;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.delegates.bottom.BottomTabBean;
import com.flj.latte.delegates.bottom.ItemBuilder;
import com.flj.latte.ec.main.found.FoundDelegate;
import com.flj.latte.ec.main.lost.LostDelegate;
import com.flj.latte.ec.main.personal.PersonalDelegate;

import java.util.LinkedHashMap;

/**
 * Created by 傅令杰
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "FOUND"), new FoundDelegate());
//        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "LOST"), new LostDelegate());
//        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }


    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
