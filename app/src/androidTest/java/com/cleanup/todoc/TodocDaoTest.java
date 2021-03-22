package com.cleanup.todoc;

import android.graphics.Color;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.data.local.todocdatabase.TodocDao;
import com.cleanup.todoc.data.local.todocdatabase.TodocDatabase;
import com.cleanup.todoc.data.local.todocdatabase.entities.ProjectEntity;
import com.cleanup.todoc.data.local.todocdatabase.entities.TaskEntity;
import com.cleanup.todoc.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TodocDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TodocDao todocDao;

    @Before
    public void setup() {
        todocDao = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                TodocDatabase.class
        )
                .allowMainThreadQueries()
                .build()
                .getTodocDao();
    }

    @Test
    public void insertTask_withSuccess() throws InterruptedException {
        TaskEntity taskEntity = new TaskEntity(1, 45, "Task_name", System.currentTimeMillis());

        todocDao.insertTask(taskEntity);

        List<TaskEntity> taskEntityList = LiveDataTestUtil.getOrAwaitValue(todocDao.getTasks());

        assert taskEntityList.contains(taskEntity);
    }

    @Test
    public void insertProject_withSuccess() throws InterruptedException {
        ProjectEntity projectEntity = new ProjectEntity(1, "Project Name", Color.GRAY);

        todocDao.insertProject(projectEntity);

        List<ProjectEntity> projectEntities = LiveDataTestUtil.getOrAwaitValue(todocDao.getProjects());

        assert projectEntities.contains(projectEntity);
    }

    @Test
    public void deleteTask_withSuccess() throws InterruptedException {
        TaskEntity taskToDelete = new TaskEntity(1, 45, "Salle de bain", System.currentTimeMillis());

        todocDao.insertTask(taskToDelete);

        todocDao.deleteTask(taskToDelete);

        List<TaskEntity> taskEntities = LiveDataTestUtil.getOrAwaitValue(todocDao.getTasks());

        assert !taskEntities.contains(taskToDelete);
    }

    @Test
    public void deleteProject_withSuccess() throws InterruptedException {
        ProjectEntity projectToDelete = new ProjectEntity(1, "Project name", Color.BLACK);

        todocDao.insertProject(projectToDelete);

        todocDao.deleteProject(projectToDelete);

        List<ProjectEntity> projectEntities = LiveDataTestUtil.getOrAwaitValue(todocDao.getProjects());

        assert !projectEntities.contains(projectToDelete);
    }

}
