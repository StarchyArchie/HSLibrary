package ser210.quinnipiac.edu.hearthapi;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FragmentSearch extends Fragment {

    EditText searchField;
    Button searchButton;

    public FragmentSearch() {

    }

    public interface searchListener{
        void setSearchTerm(String s);
    }

    private searchListener searchQuery;


    public void sendSearchTerm(String s){
        searchQuery.setSearchTerm(s);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        searchQuery = (searchListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        searchField = view.findViewById(R.id.Fragedit);
        searchButton = view.findViewById(R.id.Button);
        return view;
    }
}

