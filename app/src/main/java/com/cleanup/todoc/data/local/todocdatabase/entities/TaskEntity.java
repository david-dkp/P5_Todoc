package com.cleanup.todoc.data.local.todocdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cleanup.todoc.model.Task;

import java.util.Objects;

@Entity(tableName = "task_table")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long projectId;

    @NonNull
    private String name;

    private long creationTimestamp;

    public TaskEntity(long id, long projectId, String name, long creationTimestamp) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    public static TaskEntity fromTask(Task task) {
        return new TaskEntity(task.getId(), task.getProjectId(), task.getName(), task.getCreationTimestamp());
    }

    public Task toTask() {
        return new Task(id, projectId, name, creationTimestamp);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return id == that.id &&
                projectId == that.projectId &&
                creationTimestamp == that.creationTimestamp &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, name, creationTimestamp);
    }
}
