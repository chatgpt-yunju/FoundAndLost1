package com.flj.latte.ec.main.found;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.diabin.latte.ec.R;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.delegates.web.WebDelegateImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 傅令杰
 */

public class FoundDelegate extends BottomItemDelegate {

    private ListView lv_found=null ;
//    private List<FoundGood> list_FoundGood =new ArrayList<>();


    @Override
    public Object setLayout() {
        return R.layout.delegate_found;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        lv_found=$(R.id.lv_found);
        //初始化数据
        initData();
        //设置适配器
//        FoundAdapter foundAdapter=new FoundAdapter();
//        lv_found.setAdapter(foundAdapter);

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    private void  initData(){
//        list_FoundGood =new ArrayList<>();
        BmobQuery<FoundGood> foodBmobQuery = new BmobQuery<>();
        foodBmobQuery.order("-updatedAt");//排序
        foodBmobQuery.findObjects(new FindListener<FoundGood>() {

            @Override
            public void done(List<FoundGood> list, BmobException e) {
                if (e == null) {
                   FoundAdapter foundAdapter=new FoundAdapter(list,getSupportDelegate().getActivity());//创建适配器
                   lv_found.setAdapter(foundAdapter);
                    Toast.makeText(getContext(), "查询成功" , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "查询数据失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
