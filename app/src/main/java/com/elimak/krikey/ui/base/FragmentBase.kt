package com.elimak.krikey.ui.base

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class FragmentBase : Fragment() {

    protected val compositeJob = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + compositeJob)

    override fun onDestroy() {
        super.onDestroy()
        compositeJob.cancel()
    }
}