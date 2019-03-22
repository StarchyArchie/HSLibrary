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
        imageURL = intent.getStringExtra("image");
        imageView = findViewById(R.id.imageView);
        //cardImage = LoadImageFromWebOperations(imageURL);
        //cardBitmap = makeBitmap(imageURL);
        //System.out.println("Card image Generator Called");
        //imageView.setImageDrawable(cardImage);
        //imageView.setImageBitmap(cardBitmap);
        new CardCreate(imageView).execute(imageURL);
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream input = (InputStream) new URL(url).getContent();
            System.out.println("InputStream Created.");
            Drawable display = Drawable.createFromStream(input, "src name");
            System.out.println("Image Created!");
            return display;
        } catch (Exception e) {
            return null;
        }
    }
    protected Bitmap makeBitmap(String url){
        try{
            URL imgurl = new URL(url);
            System.out.println("URL Created. "+url);
            Bitmap cardbmp = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream());
            System.out.println("Bitmap Created.");
            return cardbmp;
        }
        catch(Exception e){
            System.out.println("Bitmap Exception: "+e);
            return null;
        }
    }
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
