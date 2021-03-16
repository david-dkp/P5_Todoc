package com.cleanup.todoc.data.local.todocdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Dao
public interface TodocDao {

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Insert
    void insertProject(Project project);

    @Delete
    void deleteProject(Project project);

}
