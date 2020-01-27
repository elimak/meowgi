package com.elimak.krikey.repository

import com.elimak.demo.krikey.repository.ISignal
import kotlinx.coroutines.channels.ReceiveChannel

interface IRepository {
    val broadcast: ReceiveChannel<ISignal<*>>
}