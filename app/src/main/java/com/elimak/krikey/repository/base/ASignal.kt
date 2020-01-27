package com.elimak.krikey.repository

import com.elimak.demo.krikey.repository.ISignal

abstract class ASignal<T>(override val data: T) : ISignal<T>
