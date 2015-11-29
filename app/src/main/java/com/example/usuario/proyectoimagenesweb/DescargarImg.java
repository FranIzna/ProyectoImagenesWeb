package com.example.usuario.proyectoimagenesweb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DescargarImg extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_urls);

        inicio();
    }
    private String url;
    private Adaptador ad;
    private ListView lv;
    private List<String> etiquetaImg=new ArrayList();
    private Context c=DescargarImg.this;
    private ProgressBar pb;

    public void inicio(){
        pb=(ProgressBar) findViewById(R.id.pb);
        lv=(ListView) findViewById(R.id.lv);
        Intent i=getIntent();
            Bundle b=i.getExtras();
            url=b.getString("url");
        Descargar d=new Descargar();
        d.execute(url);
    }
    public class Descargar extends  AsyncTask<String, Integer, ArrayList<String>> {
        ArrayList<Bitmap> bmps = new ArrayList<>();

        @Override protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> urls=descarga(params[0]);
            return urls;
        }
        public ArrayList<String> descarga(String s){
            ArrayList<String> imagenes=new ArrayList<>();
            try {
                URL url;
                    try{
                        url = new URL("http://"+s);
                    }catch (Exception e) {
                        url = new URL("https://" + s);
                    }
                Log.v("**","3---"+url);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                Log.v("**","4----");
                String linea, img;
                Log.v("**","-2");
                int i=0;
                while ((linea=in.readLine())!=null){
                    int pos=1,pos1,pos2;
//                    i+=4;
//                    publishProgress(i);
                    if((linea.startsWith("<img") || linea.contains("<img")) /*&& !linea.contains(".gif")*/){
                        pos=linea.indexOf("<img",pos);
                        pos1 = linea.indexOf("src=\"", pos );
                        pos2 = linea.indexOf("\"", pos1 + 5);
                        img = linea.substring(pos1 + 5, pos2);
                        Log.v("***IMG***","-: "+img);
//                        i+=4;
//                        publishProgress(i);
                        if(!img.contains(".gif")) {
                            etiquetaImg.add(img);
                            URL urlimgage = new URL(img);
//                            imagenes.add(""+urlimgage);
                            bmps.add(BitmapFactory.decodeStream(urlimgage.openConnection().getInputStream()));
                            i+=4;
//                            publishProgress(i);
                        }
//                        i+=4;
//                        publishProgress(i);
                    }
//                    i+=4;
                    publishProgress();
//                    System.out.println("*****: "+i);
                }
//                return imagenes;
            }catch (Exception e){
                Log.v("**ERROR--","asdf");
            }
            return null;
        }
//        @Override
//        protected void onPreExecute() {
//            pb.setProgress(10);
//            pb.setMax(2000);
//        }

//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            pb.setProgress(values[values.length - 1]);
//        }
        @Override
        protected void onPostExecute(ArrayList<String> imgs) {
            pb.setVisibility(View.GONE);
            ad = new Adaptador(c,R.layout.item, etiquetaImg);
            lv.setAdapter(ad);
            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(DescargarImg.this, MuestraImg.class);
                            i.putExtra("img", bmps.get(position));
                            startActivity(i);
                        }
                    }
            );
        }
    }

}
