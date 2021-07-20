package com.example.neaplus.core.resource;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;

import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.model.database.Article;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class NeaDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile NeaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NeaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NeaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NeaDatabase.class, "nea")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ArrayList<Articles> listArticle = ArticlesData.getListData();
                ArticleDao articleDao = INSTANCE.articleDao();

                articleDao.deleteAll();
                for (int counter = 0; counter < listArticle.size(); counter++) {
                    Article article = new Article(
                            listArticle.get(counter).getAuthor(),
                            listArticle.get(counter).getTitle(),
                            listArticle.get(counter).getUrl(),
                            listArticle.get(counter).getUrlToImage(),
                            listArticle.get(counter).getPublishedAt(),
                            listArticle.get(counter).getContent(),
                            listArticle.get(counter).getSource().getName()
                    );
                    articleDao.insert(article);
                }
            });
        }
    };
}
