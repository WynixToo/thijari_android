package com.example.android.thijari.customTab;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.thijari.fragment.BeritaTerkiniFragment;
import com.example.android.thijari.fragment.ContactFragment;
import com.example.android.thijari.fragment.MagazineFragment;
import com.example.android.thijari.fragment.MyQFragment;
import com.example.android.thijari.fragment.PromosiFragment;
import com.example.android.thijari.fragment.TransaksiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinichi Nishimura on 2015/07/24.
 */
public class DemoImitationLoopPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_LOOPS = 10000;

    private List<Integer> mItems = new ArrayList<>();
    //    private final SparseArrayCompat<WeakReference<Fragment>> holder;
    private ContactFragment contactFragment;
    private MagazineFragment magazineFragment;
    private BeritaTerkiniFragment beritaTerkiniFragment;
    private PromosiFragment promosiFragment;
    private MyQFragment myQFragment;
    private TransaksiFragment transaksiFragment;

    public DemoImitationLoopPagerAdapter(FragmentManager fm) {
        super(fm);
//        this.holder = new SparseArrayCompat<>(mItems.size());
    }

    public ContactFragment getContactFragment(){
        return this.contactFragment;
    }

    @Override
    public Fragment getItem(int position) {
        int index = position % mItems.size();

        System.out.println("PAGE !!!! " + index);

        switch (index) {
            case 0:
                contactFragment = ContactFragment.newInstance();
                return contactFragment;
            case 1:
                return MagazineFragment.newInstance();
            case 2:
                return BeritaTerkiniFragment.newInstance();
            case 3:
                return PromosiFragment.newInstance();
            case 4:
                return MyQFragment.newInstance();
            case 5:
                return TransaksiFragment.newInstance();
            default:
                return null;
        }
//        if(index == 1){
//            if (recyclerViewFragment == null) {
//                return RecyclerViewFragment.newInstance();
//            } else {
//                return recyclerViewFragment;
//            }
//        }
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        int index = position % mItems.size();
//        Fragment createdFragment = (Fragment) super.instantiateItem(container, index);
//
//        // save the appropriate reference depending on position
//        switch (index) {
//            case 0:
//                contactFragment = (ContactFragment) createdFragment;
//                break;
//            case 1:
//                magazineFragment = (MagazineFragment) createdFragment;
//                break;
//            case 2:
//                beritaTerkiniFragment = (BeritaTerkiniFragment) createdFragment;
//                break;
//            case 3:
//                promosiFragment = (PromosiFragment) createdFragment;
//                break;
//            case 4:
//                myQFragment = (MyQFragment) createdFragment;
//                break;
//            case 5:
//                transaksiFragment = (TransaksiFragment) createdFragment;
//                break;
//        }
//        return createdFragment;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        int index = mItems.get(position % mItems.size());
//        if (index >= getCount()) {
//            try {
//                if (object != null && object instanceof Fragment) {
//                    FragmentManager manager = ((Fragment) object).getChildFragmentManager();
//                    FragmentTransaction trans = manager.beginTransaction();
//                    trans.remove((Fragment) object);
//                    trans.commitAllowingStateLoss();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        switch (index) {
//            case 0:
//                contactFragment = null;
//                break;
//            case 1:
//                magazineFragment = null;
//                break;
//            case 2:
//                beritaTerkiniFragment = null;
//                break;
//            case 3:
//                promosiFragment = null;
//                break;
//            case 4:
//                myQFragment = null;
//                break;
//            case 5:
//                transaksiFragment = null;
//                break;
//        }
////        recyclerViewFragment = null;
////        container.removeView((View) object);
//    }

    @Override
    public int getCount() {
        return mItems.size() * NUMBER_OF_LOOPS;
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }

//    @Override
//    public String getPageTitle(int position) {
//        return getValueAt(position);
//    }

    public void addAll(List<Integer> items) {
        mItems = new ArrayList<>(items);
    }

    public int getCenterPosition(int position) {
        return mItems.size() * NUMBER_OF_LOOPS / 2 + position;
    }

    private List<Integer> selectedIcons = new ArrayList<>();

    public void setSelectedIcons(List<Integer> icons) {
        selectedIcons = icons;
    }

    @DrawableRes
    public int getImageResourceId(int position) {
        if (mItems.size() == 0) {
            return 0;
        }
        return mItems.get(position % mItems.size());
    }

    @DrawableRes
    public int getSelectedImageResourceId(int position) {
        if (selectedIcons.size() == 0) {
            return 0;
        }
        return selectedIcons.get(position % selectedIcons.size());
    }

//    public Integer getValueAt(int position) {
//        if (mItems.size() == 0) {
//            return null;
//        }
//        return mItems.get(position % mItems.size());
//    }
}
