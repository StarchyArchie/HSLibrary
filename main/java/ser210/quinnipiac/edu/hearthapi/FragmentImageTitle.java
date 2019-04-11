package ser210.quinnipiac.edu.hearthapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentImageTitle extends Fragment {

    TextView textView;
    String titleString = "";
    public FragmentImageTitle() {

    }




    /*
     FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
   */
    public void setFragString(String s){
        titleString = s;
        System.out.println(titleString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_title, container, false);
        textView = view.findViewById(R.id.textView);
        textView.setText(titleString);

        return view;
    }
}
