package org.t_robop.kido.temperature2;

/**
 * Created by black ehu on 2016/07/05.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.net.Uri;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//追加
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskHttpRequest extends AsyncTask<Uri.Builder, Void, Bitmap> {
    private ImageView imageView;

    public AsyncTaskHttpRequest(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Uri.Builder... builder) {

        //ここから
        // 受け取ったbuilderでインターネット通信する
        // 後で入力する
        @Override
        protected Bitmap doInBackground(Uri.Builder... builder){
            // 受け取ったbuilderでインターネット通信する
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            Bitmap bitmap = null;

            try{

                URL url = new URL(builder[0].toString());
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                inputStream = connection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            }catch (MalformedURLException exception){

            }catch (IOException exception){

            }finally {
                if (connection != null){
                    connection.disconnect();
                }
                try{
                    if (inputStream != null){
                        inputStream.close();
                    }
                }catch (IOException exception){
                }
            }

            return bitmap;
        }
        //ここまで





        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        // インターネット通信して取得した画像をImageViewにセットする
        this.imageView.setImageBitmap(result);
    }
}
}
