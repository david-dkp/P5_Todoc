package com.cleanup.todoc.data.local.todocdatabase;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
            ).addCallback(PREPOPULATE_CALLBACK).build();
        }

        return instance;
    }

    private final static Callback PREPOPULATE_CALLBACK = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //Insert default Projects
            db.execSQL("INSERT INTO project_table VALUES ('1', 'Projet Tartampion', '"+ 0xFFEADAD1+"')");
            db.execSQL("INSERT INTO project_table VALUES ('2', 'Projet Lucidia', '"+ 0xFFB4CDBA+"')");
            db.execSQL("INSERT INTO project_table VALUES ('3', 'Projet Circus', '"+ 0xFFA3CED2+"')");
        }
    };
}
