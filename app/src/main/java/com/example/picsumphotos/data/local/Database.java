package com.example.picsumphotos.data.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.picsumphotos.data.model.PictureItemEntity;

@androidx.room.Database(entities = {PictureItemEntity.class}, version = 2)
public abstract class Database extends RoomDatabase {

    //al ser abstracta no se necesita un private constructor

    //singleton
    private static Database databaseInstance;

    public abstract PicturesItemLocalDAO getDAO();

    public synchronized static Database getInstance(Context context) {
        if (databaseInstance == null) {

            databaseInstance = Room
                    .databaseBuilder(context, Database.class, "picture_items_database")
                    .allowMainThreadQueries() //solo para pruebas para que no de error
                    .fallbackToDestructiveMigration() //para que no truene al presionar el boton load.
                    .build();
        }
        return databaseInstance;
    }
}

//synchronized significa que solamente un hilo podra acceder a este metodo
