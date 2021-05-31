package com.flj.latte.ec.main.personal.password;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.diabin.latte.ec.R;
import com.flj.latte.app.AccountManager;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ec.launcher.LauncherDelegate;
import com.flj.latte.ec.main.EcBottomDelegate;
import com.flj.latte.ec.main.found.FoundGood;
import com.joanzapata.iconify.widget.IconTextView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 傅令杰
 */

public class PasswordDelegate extends BottomItemDelegate {

    private IconTextView iv_found_back,iv_found_add;
    private EditText et_password,et_password2;

    @Override
    public Object setLayout() {
        return R.layout.delegate_password;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        et_password=$(R.id.et_newpassword);
        et_password2=$(R.id.et_newpassword2);
        iv_found_back=$(R.id.iv_found_back);
        iv_found_add=$(R.id.iv_found_add);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        iv_found_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().startWithPop(new EcBottomDelegate());
            }
        });

        iv_found_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    NewPassword();
            }
        });
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    private void NewPassword(){
        String password1 = et_password.getText().toString().trim();
        String password2 = et_password2.getText().toString().trim();

        Boolean isPass =true;

        if (TextUtils.isEmpty(password1)) {
            et_password.setError("新密码不能为空");
            isPass=false;
        }

        if (TextUtils.isEmpty(password2)) {
            et_password2.setError("新密码不能为空");
            isPass=false;
        }

        if (password1.equals(password2)){
            updatePassword(password1);
            Toast.makeText(getContext(), "密码一致" , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(), "密码不一致" , Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param titleName 标题
     * @param num       电话号码
     * @param descridle 描述
     */
    private void updatePassword(String password) {

        if (BmobUser.isLogin()) {
            BmobUser user = BmobUser.getCurrentUser(BmobUser.class);
            user.setPassword(password);
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(getContext(), "密码修改成功" , Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(), "密码修改失败"+e, Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "请先登录", Toast.LENGTH_LONG).show();
            AccountManager.setSignState(false);
            getSupportDelegate().startWithPop(new LauncherDelegate());
        }
    }

}
