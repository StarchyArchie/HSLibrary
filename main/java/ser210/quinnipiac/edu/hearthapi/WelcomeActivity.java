package ser210.quinnipiac.edu.hearthapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button searchSet,searchSingle,searchClass,searchRarity;
    Intent intent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        searchSet = findViewById(R.id.set);
        searchSingle = findViewById(R.id.single);
        searchClass = findViewById(R.id.cardClass);
        searchRarity = findViewById(R.id.rarity);
        toolbar = findViewById(R.id.toolbar);
        final Intent intent = new Intent(this,CardRequestActivity.class);
        setSupportActionBar(toolbar);


        searchSet.setOnClickListener(new View.OnClickListener() {
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
                return false;
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:

        }
        return true;
    }
}
