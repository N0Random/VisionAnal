package egorko.apivk;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import ReqMicrosoftVision.ResponseMV;
import ReqMicrosoftVision.SendRequest;
import ReqMicrosoftVision.Tags;
import RequestApiVk.Photo;
import RequestApiVk.ReqApiVk;

/**
 * Created by Brom on 04.01.2017.
 */
public class Fragment_Contetn extends Fragment {

    VKAccessToken Token;
    ResponseMV resp;
    Controler contr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsState)
    {
        if(Token==null)
        VKSdk.login(this, VKScope.PHOTOS);

        View view =inflater.inflate(R.layout.fragment_login,null);


        contr=new Controler();

        ((Button)view.findViewById(R.id.Next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            contr.nextItem();
                ((Button) getActivity().findViewById(R.id.ShowDetal)).setEnabled(false);
                (( TextView)getActivity().findViewById(R.id.Pos)).setText(String.valueOf(contr.getPosition()+1)+"/"+String.valueOf(contr.getCount()));
                Picasso.with(getActivity().getApplicationContext())
                        .load(contr.getNotNullUrl())
                        .into((ImageView) getActivity().findViewById(R.id.Photo));
            }
        });

        ((Button)view.findViewById(R.id.Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contr.previousItem();
                ((Button) getActivity().findViewById(R.id.ShowDetal)).setEnabled(false);
                (( TextView)getActivity().findViewById(R.id.Pos)).setText(String.valueOf(contr.getPosition()+1)+"/"+String.valueOf(contr.getCount()));
                Picasso.with(getActivity().getApplicationContext())
                        .load(contr.getNotNullUrl())
                        .into((ImageView) getActivity().findViewById(R.id.Photo));
            }
        });



        ((Button)view.findViewById(R.id.Analise)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMVQuery process=new SendMVQuery();
                process.execute();

            }
        });
        ((Button)view.findViewById(R.id.ShowDetal)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fragment_details det=((fragment_details)getActivity().getFragmentManager().findFragmentById(R.id.DetalsCont));
                getActivity().getFragmentManager().beginTransaction()
                       .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                       .show(det)
                       .commit();
                if(resp!=null)
                ((TextView)getActivity().findViewById(R.id.DetalText)).setText(resp.toString());

            }
        });
        ((Button)getActivity().findViewById(R.id.getPhotos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendVkQuery process=new SendVkQuery();
                process.execute();
            }
        });

        ((Button)view.findViewById(R.id.Next)).setEnabled(false);
        ((Button)view.findViewById(R.id.Back)).setEnabled(false);
        ((Button)view.findViewById(R.id.Analise)).setEnabled(false);
        ((Button)view.findViewById(R.id.ShowDetal)).setEnabled(false);

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
                    @Override
                    public void onResult(VKAccessToken res) {
                        Token =res;
                        Toast.makeText(getActivity(),"Successful ",Toast.LENGTH_LONG);
                    }
                    @Override
                    public void onError(VKError error) {
                        Toast.makeText(getActivity(),"Error: AccessToken not valid ",Toast.LENGTH_LONG);
                    }
                })) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class SendMVQuery extends AsyncTask<Void,Void, ResponseMV>{
        private  String UrlImgStr;
        SendRequest reqMV;
        @Override
        protected void  onPreExecute(){
            reqMV= new SendRequest(getActivity().getApplicationContext());
            UrlImgStr=contr.getNotNullUrl();
        }

        @Override
        protected ResponseMV doInBackground(Void... MVAnal) {
            return reqMV.Send(UrlImgStr);
        }

        @Override
        protected void onPostExecute(ResponseMV result) {
            resp = result;
            if(result!=null) {
                ((Button) getActivity().findViewById(R.id.ShowDetal)).setEnabled(true);

                for(Tags iter:resp.getTags())
                    if (getActivity().getString(R.string.Fruit).endsWith(iter.getName()))
                    {
                        ((TextView)getActivity().findViewById(R.id.outAnal)).setText("Есть фрукты на картинке \n с вероятностью :"+iter.getConfidence());
                        return;
                    }
                for (String tag : result.getDescription().getTags()) {

                    if (getActivity().getString(R.string.Fruit).endsWith(tag))
                    {
                        ((TextView)getActivity().findViewById(R.id.outAnal)).setText("Вероятно, есть фрукты на картинке");
                        return;
                    }
                }
                ((TextView)getActivity().findViewById(R.id.outAnal)).setText("Вероятно, на картинке  фруктов нет");
            }
            else
            {
                ((Button) getActivity().findViewById(R.id.ShowDetal)).setEnabled(false);
            }
        }
    }
    public class SendVkQuery extends AsyncTask<Void,Void, Photo> {
        ReqApiVk req;
        private  String idUser;
        @Override
        protected void  onPreExecute(){
            idUser=((EditText) getActivity().findViewById(R.id.IdUser)).getText().toString();
            req=new ReqApiVk(getActivity().getApplicationContext());
        }

        @Override
        protected Photo doInBackground(Void... gPhoto) {
            return  req.getPhoto(idUser,Token);
        }

        @Override
        protected void onPostExecute(Photo result) {
            if(result!=null) {
                contr.setData(result.getResponse().getItems());
                contr.setRespData(result);
                ((Button) getActivity().findViewById(R.id.Back)).setEnabled(true);
                ((Button) getActivity().findViewById(R.id.Next)).setEnabled(true);
                ((Button) getActivity().findViewById(R.id.Analise)).setEnabled(true);
                ((TextView) getActivity().findViewById(R.id.Pos)).setText(String.valueOf(contr.getPosition() + 1) + "/" + String.valueOf(contr.getCount()));
                Picasso.with(getActivity().getApplicationContext())
                        .load(contr.getNotNullUrl())
                        .into((ImageView) getActivity().findViewById(R.id.Photo));

            }
            else {
                ((Button) getActivity().findViewById(R.id.Analise)).setEnabled(false);
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage("Неверный id/ у пользователя нет фотографий")
                        .setNegativeButton("Ясно,понятно", null)
                        .create()
                        .show();
            }
        }
    }

    public Controler getContr() {
        return contr;
    }

    public void setContr(Controler contr) {
        this.contr = contr;
    }

    public VKAccessToken getToken() {
        return Token;
    }

    public void setToken(VKAccessToken token) {
        Token = token;
    }

    public ResponseMV getResp() {
        return resp;
    }

    public void setResp(ResponseMV resp) {
        this.resp = resp;
    }
}
