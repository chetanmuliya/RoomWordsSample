package in.ecommerce.roomwordssample.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import in.ecommerce.roomwordssample.RoomDatabases.Word;
import in.ecommerce.roomwordssample.RoomDatabases.WordRoomDatabase;
import in.ecommerce.roomwordssample.dao.WordDao;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        this.wordDao = db.wordDao();
        this.mAllWords = wordDao.getAllWords();
    }

     public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
