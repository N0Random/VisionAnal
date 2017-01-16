package egorko.apivk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

/**
 * Created by Brom on 06.01.2017.
 */
public class fragment_details extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsState)
    {
        View view =inflater.inflate(R.layout.fragment_showdetails,null);

       ((Button)view.findViewById(R.id.CloseDetals)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_details det=((fragment_details)getActivity().getFragmentManager().findFragmentById(R.id.DetalsCont));
                getActivity().getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(det)
                        .commit();
           }
        });
        return view;
    }
}
