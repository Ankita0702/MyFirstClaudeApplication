package com.example.myfirstclaudeapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstclaudeapplication.ui.theme.MyFirstClaudeApplicationTheme
import com.example.myfirstclaudeapplication.viewmodel.CounterViewModel

private val ButtonSpacing = 16.dp

@Composable
fun CounterScreen(modifier: Modifier = Modifier, viewModel: CounterViewModel = viewModel()) {
    val count by viewModel.count.collectAsStateWithLifecycle()
    CounterContent(
        count = count,
        onIncrement = viewModel::increment,
        onDecrement = viewModel::decrement,
        modifier = modifier
    )
}

@Composable
private fun CounterContent(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Button(
                onClick = onDecrement,
                enabled = count > 0,
                modifier = Modifier.semantics { contentDescription = "Decrement counter" }
            ) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(ButtonSpacing))
            Button(
                onClick = onIncrement,
                modifier = Modifier.semantics { contentDescription = "Increment counter" }
            ) {
                Text("+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CounterScreenPreview() {
    MyFirstClaudeApplicationTheme {
        CounterContent(count = 5, onIncrement = {}, onDecrement = {})
    }
}
