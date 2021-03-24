package com.cleanup.todoc.data.local.todocdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.data.local.todocdatabase.entities.ProjectEntity;
import com.cleanup.todoc.data.local.todocdatabase.entities.TaskEntity;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TodocDao {

    @Insert
    void insertTask(TaskEntity taskEntity);

    @Delete
    void deleteTask(TaskEntity taskEntity);

    @Update
    void updateTask(TaskEntity taskEntity);

    @Insert
    void insertProject(ProjectEntity projectEntity);

    @Delete
    void deleteProject(ProjectEntity projectEntity);

    @Update
    void updateProject(ProjectEntity projectEntity);



    @Query("SELECT * FROM task_table")
    LiveData<List<TaskEntity>> getTasks();

    @Query("SELECT * FROM project_table")
    LiveData<List<ProjectEntity>> getProjects();
}
