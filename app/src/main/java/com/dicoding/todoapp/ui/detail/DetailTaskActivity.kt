package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailTaskViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val taskId = intent.getIntExtra(TASK_ID, 0)
        viewModel.setTaskId(taskId)

        val title: TextView = findViewById(R.id.detail_ed_title)
        val desc: TextView = findViewById(R.id.detail_ed_description)
        val dueDate: TextView = findViewById(R.id.detail_ed_due_date)

        viewModel.task.observe(this) {
            title.text = it.title
            desc.text = it.description
            dueDate.text = DateConverter.convertMillisToString(it.dueDateMillis)
        }

        findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
            viewModel.deleteTask()
        }
    }
}