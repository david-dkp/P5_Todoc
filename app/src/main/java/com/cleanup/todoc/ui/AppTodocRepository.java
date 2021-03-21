package com.cleanup.todoc.ui;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.cleanup.todoc.data.local.todocdatabase.TodocDao;
import com.cleanup.todoc.data.local.todocdatabase.entities.ProjectEntity;
import com.cleanup.todoc.data.local.todocdatabase.entities.TaskEntity;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

public class AppTodocRepository implements TodocRepository{

    private TodocDao todocDao;

    public AppTodocRepository(TodocDao todocDao) {
        this.todocDao = todocDao;
    }

    @Override
    public void insertProject(Project project) {
        todocDao.insertProject(ProjectEntity.fromProject(project));
    }

    @Override
    public void insertTask(Task task) {
        todocDao.insertTask(TaskEntity.fromTask(task));
    }

    @Override
    public void deleteProject(Project project) {
        todocDao.deleteProject(ProjectEntity.fromProject(project));
    }

    @Override
    public void deleteTask(Task task) {
        todocDao.deleteTask(TaskEntity.fromTask(task));
    }

    @Override
    public LiveData<List<Project>> getProjects() {
        return Transformations.map(todocDao.getProjects(), new Function<List<ProjectEntity>, List<Project>>() {
            @Override
            public List<Project> apply(List<ProjectEntity> input) {
                ArrayList<Project> projects = new ArrayList<Project>();
                for (int i = 0; i < input.size(); i++) {
                    ProjectEntity projectEntity = input.get(i);
                    projects.add(projectEntity.toProject());
                }
                return projects;
            }
        });
    }

    @Override
    public LiveData<List<Task>> getTasks() {
        return Transformations.map(todocDao.getTasks(), new Function<List<TaskEntity>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskEntity> input) {
                ArrayList<Task> tasks = new ArrayList<Task>();
                for (int i = 0; i < input.size(); i++) {
                    TaskEntity taskEntity = input.get(i);
                    tasks.add(taskEntity.toTask());
                }
                return tasks;
            }
        });
    }
}
