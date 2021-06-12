package com.heavlimaginer.watchfilm.favorite

import android.content.Context
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.healvimaginer.watchfilm.R
import com.heavlimaginer.watchfilm.favorite.filmtvfavorite.film.FavoriteFilmFragment
import com.heavlimaginer.watchfilm.favorite.filmtvfavorite.tv.FavoriteTvFragment

class FavoriteSectionAdapter(private val mContext : Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StyleRes
        private val TAB_TITLES = intArrayOf(R.string.filmsection, R.string.tvsection)
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FavoriteFilmFragment()
            1 -> FavoriteTvFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}