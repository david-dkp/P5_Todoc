package com.cleanup.todoc.data.local.todocdatabase.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cleanup.todoc.model.Project;

@Entity(tableName = "project_table")
public class ProjectEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

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
}
