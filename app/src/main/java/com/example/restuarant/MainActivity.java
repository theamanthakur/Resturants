package com.example.restuarant;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private String[] urls = new String[]{"https://www.wallpaperup.com/uploads/wallpapers/2013/03/05/48315/81cb97cc17ce477e86ce291ce15cf903-700.jpg","https://img.traveltriangle.com/blog/wp-content/tr:w-700,h-400/uploads/2018/04/fort-house-restaurant.jpg", "https://www.tripsavvy.com/thmb/-547ZaJlqNCj-H-GIlidPSl9CaE=/950x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/26-5adafa56c6733500373c3cf5.jpg", "https://assets.traveltriangle.com/blog/wp-content/uploads/2018/04/the-rice-boat.jpg"
            , "https://assets.discoverlosangeles.com/sites/default/files/styles/listography_image/public/Restaurants/wilshire-restaurant-patio.jpg", "https://images.unsplash.com/photo-1456743625079-86a97ff8bc86?ixlib=rb-1.2.1&auto=format&fit=crop&w=889&q=80","https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, urls));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = urls.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }
}
