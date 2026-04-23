package com.example.myfirstclaudeapplication.viewmodel

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CounterViewModelTest {

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    // --- Initial state ---

    @Test
    fun `initial count is zero`() {
        assertEquals(0, viewModel.count.value)
    }

    // --- Increment ---

    @Test
    fun `increment increases count by 1`() = runTest {
        viewModel.increment()
        assertEquals(1, viewModel.count.value)
    }

    @Test
    fun `each increment adds exactly 1`() = runTest {
        val before = viewModel.count.value
        viewModel.increment()
        assertEquals(before + 1, viewModel.count.value)
    }

    @Test
    fun `multiple increments accumulate correctly`() = runTest {
        repeat(5) { viewModel.increment() }
        assertEquals(5, viewModel.count.value)
    }

    // --- Decrement ---

    @Test
    fun `decrement decreases count by 1`() = runTest {
        viewModel.increment()
        viewModel.increment()
        viewModel.decrement()
        assertEquals(1, viewModel.count.value)
    }

    @Test
    fun `each decrement subtracts exactly 1`() = runTest {
        repeat(3) { viewModel.increment() }
        val before = viewModel.count.value
        viewModel.decrement()
        assertEquals(before - 1, viewModel.count.value)
    }

    @Test
    fun `multiple decrements reduce count correctly`() = runTest {
        repeat(5) { viewModel.increment() }
        repeat(3) { viewModel.decrement() }
        assertEquals(2, viewModel.count.value)
    }

    @Test
    fun `increment then decrement same times returns to zero`() = runTest {
        repeat(4) { viewModel.increment() }
        repeat(4) { viewModel.decrement() }
        assertEquals(0, viewModel.count.value)
    }

    // --- Boundary: counter cannot go below 0 ---

    @Test
    fun `decrement at zero does not go below zero`() = runTest {
        viewModel.decrement()
        assertEquals(0, viewModel.count.value)
    }

    @Test
    fun `multiple decrements at zero stay at zero`() = runTest {
        repeat(5) { viewModel.decrement() }
        assertEquals(0, viewModel.count.value)
    }

    @Test
    fun `count is never negative after decrement`() = runTest {
        repeat(3) { viewModel.increment() }
        repeat(10) { viewModel.decrement() }
        assertTrue(viewModel.count.value >= 0)
    }

    @Test
    fun `decrement from one reaches zero`() = runTest {
        viewModel.increment()
        viewModel.decrement()
        assertEquals(0, viewModel.count.value)
    }

    @Test
    fun `decrement from one does not go below zero on second call`() = runTest {
        viewModel.increment()
        viewModel.decrement()
        viewModel.decrement()
        assertEquals(0, viewModel.count.value)
    }
}