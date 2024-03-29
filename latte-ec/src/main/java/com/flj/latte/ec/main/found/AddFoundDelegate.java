package com.flj.latte.ec.main.found;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.diabin.latte.ec.R;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ec.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 傅令杰
 */

public class AddFoundDelegate extends BottomItemDelegate {

    private IconTextView iv_found_back,iv_found_add;
    private EditText et_found_title,et_found_num,et_found_desc;

    @Override
    public Object setLayout() {
        return R.layout.delegate_add_found;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        iv_found_back=$(R.id.iv_found_back);
        iv_found_add=$(R.id.iv_found_add);
        et_found_title=$(R.id.et_found_title);
        et_found_num=$(R.id.et_found_num);
        et_found_desc=$(R.id.et_found_desc);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        iv_found_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportDelegate().onKe(KeyEvent.KEYCODE_BACK, null);
                //                getSupportDelegate().startWithPop(new EcBottomDelegate());
            }
        });

        iv_found_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addFoundGood();
            }
        });
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    private void addFoundGood(){
        String titleName = et_found_title.getText().toString().trim();
        String Num = et_found_num.getText().toString().trim();
        String descridle = et_found_desc.getText().toString().trim();

        Boolean isPass =true;

        if (TextUtils.isEmpty(titleName)) {
            et_found_title.setError("标题不能为空");
            isPass=false;
        }

        if (Num.isEmpty() || Num.length() != 11) {
            et_found_num.setError("手机号码错误");
            isPass=false;
        }

        if (TextUtils.isEmpty(descridle)) {
            et_found_desc.setError("描述不能为空");
            isPass=false;
        }

        if (isPass){
            publishFoundGood(titleName, Num, descridle);
        }
    }

    /**
     * @param titleName 标题
     * @param num       电话号码
     * @param descridle 描述
     */
    private void publishFoundGood(String titleName, String num, String descridle) {
        FoundGood foundGood = new FoundGood();
        foundGood.setTitle(titleName);//titleName为用户输入的标题
        foundGood.setPhoneNum(num);//num为用户输入的号码
        foundGood.setDesc(descridle);//descridle为信息描述
        foundGood.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(getContext(), "捡到物品信息发布成功", Toast.LENGTH_LONG).show();
                    //成功后提示主界面刷新数据
                    getSupportDelegate().startWithPop(new EcBottomDelegate());
                } else {
                    Toast.makeText(getContext(), "捡到物品信息发布失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
