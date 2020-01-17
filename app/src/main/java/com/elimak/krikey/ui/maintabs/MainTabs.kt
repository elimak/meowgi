package com.elimak.krikey.ui.maintabs

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.elimak.krikey.R
import com.elimak.krikey.ui.base.FragmentBase
import com.elimak.krikey.databinding.FragmentMainTabsBinding
import com.elimak.krikey.ui.categories.ListFragment
import com.elimak.krikey.ui.favorite.Favorite
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_tabs.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainTabs : FragmentBase() {
    private lateinit var viewModel: MainTabsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainTabsViewModel::class.java)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainTabsBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }

    private fun openFragment(fragment: Fragment, transaction: FragmentTransaction?) {
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var frag: FragmentBase = ListFragment()
        openFragment(frag, fragmentManager?.beginTransaction())

        bottonNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener {
                item ->
                    when (item.itemId) {
                        R.id.navigation_category -> {
                            frag = ListFragment()
                            openFragment(frag, fragmentManager?.beginTransaction())
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_favorites -> {
                            frag = Favorite()
                            openFragment(frag, fragmentManager?.beginTransaction())
                            return@OnNavigationItemSelectedListener true
                        }
                    }
            false
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.view?.let{viewModel.navigate(item, this.view!!)}
        return super.onOptionsItemSelected(item)
    }
}
