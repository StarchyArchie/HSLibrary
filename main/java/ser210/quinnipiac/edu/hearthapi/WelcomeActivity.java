package ser210.quinnipiac.edu.hearthapi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class WelcomeActivity extends AppCompatActivity {

    Button searchSet,searchSingle,searchClass,searchRarity;
    Intent intent;
    Toolbar toolbar;
    int baseColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        searchSet = findViewById(R.id.set);
        searchSingle = findViewById(R.id.single);
        searchClass = findViewById(R.id.cardClass);
        searchRarity = findViewById(R.id.rarity);
        toolbar = findViewById(R.id.toolbar);
        baseColor=Color.WHITE;
        final Intent intent = new Intent(this,CardRequestActivity.class);
        setSupportActionBar(toolbar);


        searchSet.setOnClickListener(new View.OnClickListener() {//The API changes results based on an ending, each option sends that ending to the CardRequest class.
            @Override
            public void onClick(View v) {
                intent.putExtra("Style","cards/sets/");
                startActivity(intent);
            }
        });
        searchSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Style","cards/");
                startActivity(intent);
            }
        });
        searchClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Style","cards/classes/");
                startActivity(intent);
            }
        });
        searchRarity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Style","cards/qualities/");
                startActivity(intent);
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_help:
                        helpMenu(WelcomeActivity.this);
                        break;

                    case R.id.action_settings:
                        RelativeLayout current = (RelativeLayout) findViewById(R.id.WelcomeLayout);

                        if(baseColor==Color.WHITE){//Checks background color then switches it.
                            current.setBackgroundColor(Color.GREEN);
                            baseColor=Color.GREEN;
                        }
                        else {
                            current.setBackgroundColor(Color.WHITE);
                            baseColor=Color.WHITE;
                        }

                        break;

                    case R.id.action_share:

                        break;

                    default: return false;
                }
                return true;
            }
        });
    }
    public void helpMenu(Context context){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);//Displays a temporary window with help text in it.
        builder1.setMessage("This app offers four different search options for cards from the virtual card game Hearthstone. \n Choose to search directly by card name, the set it came in, it's rarity, or the character class it belongs to.\n This will grab the image of the card from the API and display it in the app.");
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "Dismiss",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
}
