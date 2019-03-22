package ser210.quinnipiac.edu.hearthapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CardRequestActivity extends AppCompatActivity {

    String value = "Value";
    String searchType = null;
    String textInput = null;
    EditText cardInput = null;
    Button search = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_request);
        final Intent imageIntent = new Intent(this,DisplayActivity.class);
        Intent searchIntent = getIntent();
        searchType = searchIntent.getStringExtra("Style");
        cardInput = findViewById(R.id.Input);
        search = findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textConverter(cardInput.getText().toString());
                getFromAPI(textInput,imageIntent);
            }
        });
    }
    public String textConverter(String s){
        textInput = s.replaceAll(" ","%20");
        return textInput;
    }

        protected void getFromAPI(final String inputString,final Intent intent){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    URL cardSearch = null;
                    HttpURLConnection connection = null;
                    try {
                        cardSearch = new URL("https://omgvamp-hearthstone-v1.p.rapidapi.com/"+searchType + inputString);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        connection = (HttpURLConnection) cardSearch.openConnection();
                        connection.setRequestProperty("X-RapidAPI-Key", "40e38fed97msh80150cda84f7d31p1eec5ajsn84880a9477bd");
                        if (connection.getResponseCode() == 200) {
                            //success
                            System.out.println("connection established.");
                        } else {
                            //failure
                            System.out.println("Connection Failed.");
                        }

                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
                        JsonReader jReader = new JsonReader(reader);
                        jReader.beginArray();
                        jReader.beginObject();
                        while (jReader.hasNext()) {
                            String key = jReader.nextName();
                            if (key.equals("img")) {
                                value = jReader.nextString();
                                System.out.println(value);
                                break;
                            } else {
                                jReader.skipValue();
                            }
                        }
                        jReader.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("image",value);
                    startActivity(intent);
                }
            });
        }
    }