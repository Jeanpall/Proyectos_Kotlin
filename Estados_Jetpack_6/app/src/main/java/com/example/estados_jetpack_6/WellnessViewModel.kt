package com.example.estados_jetpack_6

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val task: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }
    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        task.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
}
private fun getWellnessTasks() = List(30) {i -> WellnessTask(i, "Task # $i") }