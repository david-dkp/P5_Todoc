package com.cleanup.todoc.ui;

import android.graphics.Color;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FakeTodocRepository fakeTodocRepository;
    private MainViewModel mainViewModel;

    @Before
    public void setup() {
        fakeTodocRepository = new FakeTodocRepository();
        mainViewModel = new MainViewModel(fakeTodocRepository);
    }

    @Test
    public void changeTaskProjectId_linkWithSuccess() throws InterruptedException {
        Project firstProject = new Project(1, "firstProject", Color.GREEN);
        Project secondProject = new Project(2, "secondProject", Color.RED);

        fakeTodocRepository.insertProject(firstProject);
        fakeTodocRepository.insertProject(secondProject);

        Task task = new Task(1, 1, "taskName", System.currentTimeMillis());

        fakeTodocRepository.insertTask(task);

        LiveDataTestUtil.getOrAwaitValue(mainViewModel.getTasks());

        assert task.getProject().equals(firstProject);

        task.setProjectId(2);

        fakeTodocRepository.updateTask(task);

        LiveDataTestUtil.getOrAwaitValue(mainViewModel.getTasks());

        assert task.getProject().equals(secondProject);
    }

    @Test
    public void updateProject_linkToTask() throws InterruptedException {
        Project project = new Project(1, "FirstName", Color.GREEN);

        fakeTodocRepository.insertProject(project);

        Task task = new Task(1, 1, "TaskName", System.currentTimeMillis());

        fakeTodocRepository.insertTask(task);

        LiveDataTestUtil.getOrAwaitValue(mainViewModel.getTasks());

        assert task.getProject() == project;

        Project updatedProject = new Project(1, "SecondName", Color.RED);

        fakeTodocRepository.updateProject(updatedProject);

        LiveDataTestUtil.getOrAwaitValue(mainViewModel.getTasks());

        assert task.getProject() == updatedProject;
    }

}
