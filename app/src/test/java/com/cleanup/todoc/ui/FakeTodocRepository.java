package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FakeTodocRepository implements TodocRepository{

    private MutableLiveData<List<Project>> projectsLiveData;
    private MutableLiveData<List<Task>> tasksLiveData;

    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;

    public FakeTodocRepository() {
        projects = new ArrayList<>();
        tasks = new ArrayList<>();
        projectsLiveData = new MutableLiveData<>();
        projectsLiveData.setValue(projects);
        tasksLiveData = new MutableLiveData<>();
        tasksLiveData.setValue(tasks);
    }

    @Override
    public void insertProject(Project project) {
        projects.add(project);
        projectsLiveData.setValue(projects);
    }

    @Override
    public void insertTask(Task task) {
        tasks.add(task);
        tasksLiveData.setValue(tasks);
    }

    @Override
    public void deleteProject(Project project) {
        projects.remove(project);
        projectsLiveData.setValue(projects);
    }

    @Override
    public void deleteTask(Task task) {
        tasks.remove(task);
        tasksLiveData.setValue(tasks);
    }

    @Override
    public void updateProject(Project project) {
        for (Project currentProject : projects) {
            if (project.getId() == currentProject.getId()) {
                projects.set(projects.indexOf(currentProject), project);
                projectsLiveData.setValue(projects);
                return;
            }
        }
    }

    @Override
    public void updateTask(Task task) {
        for (Task currentTask : tasks) {
            if (task.getId() == currentTask.getId()) {
                tasks.set(tasks.indexOf(currentTask), task);
                tasksLiveData.setValue(tasks);
                return;
            }
        }
    }

    @Override
    public LiveData<List<Project>> getProjects() {
        return projectsLiveData;
    }

    @Override
    public LiveData<List<Task>> getTasks() {
        return tasksLiveData;
    }
}
