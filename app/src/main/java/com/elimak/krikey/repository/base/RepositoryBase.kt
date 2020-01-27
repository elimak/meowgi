package com.elimak.krikey.repository.base

import com.elimak.demo.krikey.repository.ISignal
import com.elimak.krikey.repository.base.IRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
open class RepositoryBase : IRepository {
    protected val publisher = ConflatedBroadcastChannel<ISignal<*>>()
    override val broadcast: ReceiveChannel<ISignal<*>>
        get() = publisher.openSubscription()
}