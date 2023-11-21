package com.brandon.mbti_app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
/**
 * ViewPager2 adapter responsible for handling fragment creation and management.
 * @param fragmentHolderActivity The activity that holds the ViewPager2.
 */
class ViewPagerAdapter(fragmentHolderActivity: FragmentActivity) : FragmentStateAdapter(fragmentHolderActivity) {
    // number of total pages
    override fun getItemCount(): Int = 4

    /**
     * Creates a new fragment instance when TestActivity sets the currentItem with a new position.
     * @param position The position for which to create the fragment.
     * @return The newly created fragment instance.
     */
    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.nextInstance(position)
    }
}