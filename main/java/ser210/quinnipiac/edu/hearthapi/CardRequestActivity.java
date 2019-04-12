package ser210.quinnipiac.edu.hearthapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class CardRequestActivity extends AppCompatActivity implements FragmentSearch.searchListener{

    String value = "Value";
    String searchType = null;
    String textInput = null;
    EditText cardInput = null;
    Button search = null;
    FragmentSearch fragment = new FragmentSearch();
    Intent imageIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_request);
        imageIntent = new Intent(this,DisplayActivity.class);
        Intent searchIntent = getIntent();
        searchType = searchIntent.getStringExtra("Style");//Grabs which category is being searched
        cardInput = findViewById(R.id.Input);//Where user types search
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.searchQueryFragment, fragment);
        ft.commit();
        //These are not removed because for some reason their removal causes the JSON reader to break.
        search = findViewById(R.id.button);//Searches what is in the search box
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textConverter(cardInput.getText().toString());
                getFromAPI(textInput,imageIntent);
            }
        });
    }
    public String textConverter(String s){//Converts user input into the relevant URL text
        textInput = s.replaceAll(" ","%20");
        return textInput;
    }
    //Calls the search method through the fragment.
    public void searchMethod(View v){
        String input = fragment.searchField.getText().toString();
        textConverter(input);
        getFromAPI(textInput,imageIntent);
    }

    //Gets the image from the api and sends it to the display
        protected void getFromAPI(final String inputString,final Intent intent){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    URL cardSearch = null;
                    HttpURLConnection connection = null;
                    try {//Calls the API for the chosen type with the user input specification.
                        cardSearch = new URL("https://omgvamp-hearthstone-v1.p.rapidapi.com/"+searchType + inputString);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        connection = (HttpURLConnection) cardSearch.openConnection();//Connection to API
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
                        while (jReader.hasNext()) {//Singles out the image from the API
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
                    intent.putExtra("image",value);//Sends Image URL to Display activity
                    startActivity(intent);
                }
            });
        }

    @Override
    public void setSearchTerm(String s) {
        cardInput.setText(s);
    }
}