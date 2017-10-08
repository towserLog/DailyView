package io.piazi.quiz;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by towse on 2017/10/7.
 */

public class MusicService extends Service {

    private static final String TAG = "MusicService";

    private CopyOnWriteArrayList<Music> mMusicList = new CopyOnWriteArrayList<>();

    private String[] mCursorCol = new String[]{
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ARTIST
    };

    //
    private Binder mBinder = new IMusicManager.Stub() {
        @Override
        public List<Music> getMusicList() throws RemoteException {
            return mMusicList;
        }

        @Override
        public void addBook(Music music) throws RemoteException {
            mMusicList.add(music);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        queryMusic();
    }

    private void queryMusic() {
        Cursor cursor = this.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                ,mCursorCol, "duration>1000", null, null);
        if(cursor != null){
            for(int i=0; i<5; ){
                if(cursor.moveToNext()){
                    addMusic(cursor);
                }else{
                    i=6;
                }
            }
            cursor.close();
        }
    }

    private void addMusic(Cursor cursor) {
        Music music = new Music();
        music.setAlbum(cursor.getString( cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
        music.setArtist(cursor.getString( cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
        music.setData(cursor.getString( cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
        music.setTitle(cursor.getString( cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        mMusicList.add(music);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
