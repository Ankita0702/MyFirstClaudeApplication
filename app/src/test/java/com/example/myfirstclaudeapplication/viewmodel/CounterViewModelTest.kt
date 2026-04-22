package com.example.myfirstclaudeapplication.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CounterViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CounterViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial count is zero`() {
        assertEquals(0, viewModel.count.value)
    }

    @Test
    fun `increment increases count by 1`() = runTest {
        viewModel.increment()
        assertEquals(1, viewModel.count.value)
    }

    @Test
    fun `multiple increments accumulate correctly`() = runTest {
        repeat(5) { viewModel.increment() }
        assertEquals(5, viewModel.count.value)
    }

    @Test
    fun `each increment adds exactly 1`() = runTest {
        val before = viewModel.count.value
        viewModel.increment()
        assertEquals(before + 1, viewModel.count.value)
    }
}
