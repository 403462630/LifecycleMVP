package fc.com.lifecycle.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import fc.com.lifecycle.example.fragment.TestFragment1;
import fc.com.lifecycle.example.fragment.TestFragment2;
import fc.com.lifecycle.example.fragment.TestFragment3;
import fc.com.lifecycle.example.fragment.TestFragment4;


/**
 * Created by rjhy on 16-12-12
 */
public class TestFragmentActivity extends FragmentActivity {
    private TestFragment1 testFragment1 = new TestFragment1();
    private TestFragment2 testFragment2 = new TestFragment2();
    private TestFragment3 testFragment3 = new TestFragment3();
    private TestFragment4 testFragment4 = new TestFragment4();
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.pushFragment(getSupportFragmentManager(), testFragment1, true);
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.pushFragment(getSupportFragmentManager(), testFragment3, true);
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.pushFragment(getSupportFragmentManager(), testFragment2, true);
            }
        });
        findViewById(R.id.bt_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.pushFragment(getSupportFragmentManager(), testFragment4, true);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.view_page);
        viewPager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));
    }

    class TestFragmentAdapter extends FragmentStatePagerAdapter {

        public TestFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new TestFragment1();
            } else if (position == 1) {
                return new TestFragment2();
            } else if (position == 2) {
                return new TestFragment3();
            }
            return new TestFragment4();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
