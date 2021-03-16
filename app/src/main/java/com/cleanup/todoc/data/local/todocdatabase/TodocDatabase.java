package com.cleanup.todoc.data.local.todocdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import static com.cleanup.todoc.others.Constants.TODOC_DATABASE_VERSION;

@Database(entities = {}, version = TODOC_DATABASE_VERSION)
public abstract class TodocDatabase extends RoomDatabase {

}
