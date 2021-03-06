package com.cleanup.todoc.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.others.SortMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private TodocRepository todocRepository;
    private Executor executor;

    private MutableLiveData<SortMethod> _sortMethod = new MutableLiveData<>();
    private MediatorLiveData<List<Task>> _tasks = new MediatorLiveData<>();
    private LiveData<List<Project>> _projects;

    public MainViewModel(@NonNull TodocRepository todocRepository) {
        this.todocRepository = todocRepository;
        this.executor = Executors.newSingleThreadExecutor();

        _projects = todocRepository.getProjects();

        //Default values
        _tasks.setValue(Collections.emptyList());
        _sortMethod.setValue(SortMethod.NONE);

        setupTasks();
    }

    private void setupTasks() {
        _tasks.addSource(_projects, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                List<Task> tasks = linkProjectToTasks(_tasks.getValue(), projects);
                _tasks.setValue(tasks);
            }
        });

        _tasks.addSource(todocRepository.getTasks(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> currentTasks) {
                List<Task> tasks = linkProjectToTasks(currentTasks, _projects.getValue());
                _tasks.setValue(tasks);
            }
        });

    }

    private List<Task> linkProjectToTasks(List<Task> tasks, List<Project> projects) {
        if (projects == null) return tasks;

        for (Task task : tasks) {
            task.setProject(findProjectForTask(task, projects));
        }

        return tasks;
    }

    private Project findProjectForTask(Task task, List<Project> projects) {
        for (Project project : projects) {
            if (task.getProjectId() == project.getId()) {
                return project;
            }
        }

        return null;
    }

    public void setSortMethod(SortMethod sortMethod) {
        _sortMethod.setValue(sortMethod);
    }

    public LiveData<List<Project>> getProjects() {
        return _projects;
    }

    public LiveData<List<Task>> getTasks() {
        return _tasks;
    }

    public LiveData<SortMethod> getSortMethod() { return _sortMethod; }

    public void insertTask(Task task) {
        executor.execute(() -> {
            todocRepository.insertTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {
            todocRepository.deleteTask(task);
        });
    }
}
