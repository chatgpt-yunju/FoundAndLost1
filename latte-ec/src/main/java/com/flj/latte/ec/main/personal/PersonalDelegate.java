package com.flj.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.diabin.latte.ec.R;
import com.flj.latte.app.AccountManager;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ec.launcher.LauncherDelegate;
import com.flj.latte.ec.main.index.IndexDelegate;
import com.flj.latte.ec.main.personal.address.AddressDelegate;
import com.flj.latte.ec.main.personal.list.ListAdapter;
import com.flj.latte.ec.main.personal.list.ListBean;
import com.flj.latte.ec.main.personal.list.ListItemType;
import com.flj.latte.ec.main.personal.order.OrderListDelegate;
import com.flj.latte.ec.main.personal.password.PasswordDelegate;
import com.flj.latte.ec.main.personal.profile.UserProfileDelegate;
import com.flj.latte.ec.main.personal.settings.SettingsDelegate;
import com.flj.latte.ec.sign.SignInDelegate;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by 傅令杰
 */

public class PersonalDelegate extends BottomItemDelegate {

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;
    private Button loginout=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    private void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    private void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        loginout=$(R.id.login_out);
        loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();   //清除缓存用户对象
                if (BmobUser.isLogin()) {
                    BmobUser user = BmobUser.getCurrentUser(BmobUser.class);
                    Toast.makeText(getContext(), "仍在登录：" + user.getUsername(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "已退出登录", Toast.LENGTH_LONG).show();
                    AccountManager.setSignState(false);
                    getParentDelegate().start(new LauncherDelegate());
                }
            }
        });

        final RecyclerView rvSettings = $(R.id.rv_personal_setting);
        $(R.id.tv_all_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAllOrder();
            }
        });
        $(R.id.img_user_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAvatar();
            }
        });

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new UserProfileDelegate())
                .setText("编辑个人信息")
                .build();

        final ListBean password = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new PasswordDelegate())
                .setText("密码修改")
                .build();


        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();


//        final ListBean help = new ListBean.Builder()
//                .setItemType(ListItemType.ITEM_NORMAL)
//                .setId(3)
//                .setDelegate(new SettingsDelegate())
//                .setText("系统帮助")
//                .build();

//        final ListBean loginout = new ListBean.Builder()
//                .setItemType(ListItemType.ITEM_NORMAL)
//                .setId(3)
////                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////                    @Override
////                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                        AccountManager.setSignState(false);
////                    }
////                })
//                .setDelegate(new SettingsDelegate())
//                .setText("系统帮助")
//                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(password);
        data.add(system);
//        data.add(loginout);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        rvSettings.setAdapter(adapter);
        rvSettings.addOnItemTouchListener(new PersonalClickListener(this));


    }
}
