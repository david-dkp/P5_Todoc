package com.cleanup.todoc.data.local.todocdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.data.local.todocdatabase.entities.ProjectEntity;
import com.cleanup.todoc.data.local.todocdatabase.entities.TaskEntity;

import static com.cleanup.todoc.others.Constants.TODOC_DATABASE_NAME;
import static com.cleanup.todoc.others.Constants.TODOC_DATABASE_VERSION;

@Database(entities = {TaskEntity.class, ProjectEntity.class}, version = TODOC_DATABASE_VERSION)
public abstract class TodocDatabase extends RoomDatabase {
    private static TodocDatabase instance;

    public abstract TodocDao getTodocDao();

    public static TodocDatabase getInstance(Context context) {
        if (instance == null) {
            return Room.databaseBuilder(
                    context,
                    TodocDatabase.class,
                    TODOC_DATABASE_NAME
            ).build();
        }

        return instance;
    }
}
