package com.flj.latte.ec.main.found;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.diabin.latte.ec.R;
import com.flj.latte.delegates.bottom.BottomItemDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 傅令杰
 */

public class AddFoundDelegate extends BottomItemDelegate {

    private ImageView iv_found_back,iv_found_add;

    @Override
    public Object setLayout() {
        return R.layout.delegate_add_found;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        iv_found_back=$(R.id.iv_found_back);
        iv_found_add=$(R.id.iv_found_add);
        iv_found_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getSupportDelegate().start(new FoundDelegate());
            }
        });

        iv_found_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().start(new FoundDelegate());
            }
        });
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
    }
