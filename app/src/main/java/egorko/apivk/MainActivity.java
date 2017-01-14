package egorko.apivk;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vk.sdk.*;

import ReqMicrosoftVision.ResponseMV;


public class MainActivity extends AppCompatActivity  {
FragmentManager FragMen;
Fragment_Contetn  log;
fragment_details detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragMen=getFragmentManager();
        log=(Fragment_Contetn)FragMen.findFragmentById(R.id.login);
        detail=(fragment_details)FragMen.findFragmentById(R.id.DetalsCont);
        if(log==null&&detail==null) {
            log = new Fragment_Contetn();
            detail = new fragment_details();
            FragMen.beginTransaction()
                    .add(R.id.login, log)
                    .add(R.id.DetalsCont, detail)
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .hide(detail)
                    .commit();
        }


String str="hgfhgfg";

    }

}
