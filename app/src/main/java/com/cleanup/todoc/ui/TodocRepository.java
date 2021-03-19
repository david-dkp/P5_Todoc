package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TodocRepository {

    void insertProject(Project project);

    void insertTask(Task task);

    void deleteProject(Project project);

    void deleteTask(Task task);

    LiveData<List<Project>> getProjects();

    LiveData<List<Task>> getTasks();
}
