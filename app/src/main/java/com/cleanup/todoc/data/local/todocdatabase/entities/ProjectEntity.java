package com.cleanup.todoc.data.local.todocdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cleanup.todoc.model.Project;

import java.util.Objects;

@Entity(tableName = "project_table")
public class ProjectEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String name;

    private int color;

    public ProjectEntity(long id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public static ProjectEntity fromProject(Project project) {
        return new ProjectEntity(project.getId(), project.getName(), project.getColor());
    }

    public Project toProject() {
        return new Project(id, name, color);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return id == that.id &&
                color == that.color &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
