package me.hsky.androidshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import me.hsky.androidshop.fragment.MainBuy;
import me.hsky.androidshop.fragment.MainHome;
import me.hsky.androidshop.fragment.MainMe;
import me.hsky.androidshop.fragment.MainProject;
import me.hsky.androidshop.utils.SharedUtils;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.group_bottom_btn)
    private RadioGroup group_bottom_btn;
    @ViewInject(R.id.main_home)
    private RadioButton main_home;

    private FragmentManager fm;
    Fragment[] cacheFragment = new Fragment[4];

    private static  final String TAG ="tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

        fm = getSupportFragmentManager();

        main_home.setChecked(true);

        group_bottom_btn.setOnCheckedChangeListener(this);

        changeFragment(new MainHome(), false);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_home:

                changeFragment(new MainHome(), true);
                break;
            case R.id.main_project:
                changeFragment(new MainProject(), true);
                break;
            case R.id.main_buy:
                if(!SharedUtils.getUserLoginStatus(getBaseContext())){
                    startActivityForResult(new Intent(getBaseContext(), UserLogin.class), 2);
                }else{
                    changeFragment(new MainBuy(), true);
                }
                break;
            case R.id.main_me:
                changeFragment(new MainMe(), true);
                break;

        }
    }

    public void changeFragment(Fragment fragment, boolean isInit){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_page, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: " + requestCode);
        Log.i(TAG, "onActivityResult: " + resultCode);
        Log.i(TAG, "onActivityResult: " + data);

        Log.i(TAG, "onActivityResult: " + SharedUtils.getCityName(getBaseContext()));
        if(requestCode == 2){
            changeFragment(new MainBuy(), true);
        }
        if(requestCode == 1){
            changeFragment(new MainHome(), true);
        }

        if(requestCode == 3){
            changeFragment(new MainHome(), true);
        }

        if(requestCode == 4){
            changeFragment(new MainHome(), true);
        }
    }
}
