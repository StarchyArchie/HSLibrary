package ser210.quinnipiac.edu.hearthapi;



import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentSearch extends Fragment {


    public FragmentSearch() {

    }

    public interface cardListener{
        public void card(Bitmap image);

    }

    cardListener cardListener;

    public void onAttach(Activity activity){
        super.onAttach(activity);
        cardListener = (cardListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }
}

