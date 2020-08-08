package my.awesome.yppschoolapp;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {


    private Context myContext;
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm ,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               // Toast.makeText(myContext, "hello", Toast.LENGTH_SHORT).show();
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
               // Toast.makeText(myContext, "hello", Toast.LENGTH_SHORT).show();

            TransportFragment transportFragment = new TransportFragment();
            return transportFragment;
//                SportFragment sportFragment = new SportFragment();
//                return sportFragment;
            case 2:
               // Toast.makeText(myContext, "hello", Toast.LENGTH_SHORT).show();
                HostelFragment hostelFragment = new HostelFragment();
                return hostelFragment;
//                MovieFragment movieFragment = new MovieFragment();
//                return movieFragment;
            case 3:
                // Toast.makeText(myContext, "hello", Toast.LENGTH_SHORT).show();
                HostelFragment hostelFragment1 = new HostelFragment();
                return hostelFragment1;
            default:
                return null;
        }
   }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
