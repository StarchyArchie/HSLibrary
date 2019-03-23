package ser210.quinnipiac.edu.hearthapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayActivity extends AppCompatActivity {

    ImageView imageView;
    String imageURL = null;
    Drawable cardImage;
    Bitmap cardBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        System.out.println("DisplayActivity Launched.");
        Intent intent = getIntent();
        imageURL = intent.getStringExtra("image");//URL for image from API
        imageView = findViewById(R.id.imageView);
        new CardCreate(imageView).execute(imageURL);
    }
    //Uses given String url to generate a bitmap to display in an AsyncTask.
    private class CardCreate extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public CardCreate(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap cardIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                cardIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return cardIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
