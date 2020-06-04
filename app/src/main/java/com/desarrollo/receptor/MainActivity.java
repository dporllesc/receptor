package com.desarrollo.receptor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView rnombre,rapellido,rtelefono,rsexo,rdireccion;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rnombre=(TextView)findViewById(R.id.txt_receptor);
        rapellido=(TextView)findViewById(R.id.txt_receptor2);
        rtelefono=(TextView)findViewById(R.id.txt_receptor3);
        rsexo=(TextView)findViewById(R.id.txt_receptor4);
        rdireccion=(TextView)findViewById(R.id.txt_receptor5);
        img=(ImageView)findViewById(R.id.img_prev);





        Intent receptorIntent = getIntent();
        String action = receptorIntent.getAction();
        String tipo = receptorIntent.getType();
        if(Intent.ACTION_SEND.equals(action) && tipo != null){
            if("text/plain".equals(tipo)){

                manipularTexto(receptorIntent);
            }
        }
    }
    private void manipularTexto(Intent intent){

        String sharedText=intent.getStringExtra(Intent.EXTRA_TEXT);
        if(sharedText!=null){
            String []datos =sharedText.split(",");
            String nombre =datos[0];
            String apellido =datos[1];
            String telefono =datos[2];
            String sexo =datos[3];
            String direccion =datos[4];
            //String imagen = datos[5];
            Bitmap imagen = (Bitmap) intent.getExtras().get("data");

            rnombre.setText(nombre);
            rapellido.setText(apellido);
            rtelefono.setText(telefono);
            rsexo.setText(sexo);
            rdireccion.setText(direccion);
            img.setImageBitmap(imagen);

        }





    }
    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            img.setImageURI(imageUri);
            // Update UI to reflect image being shared
        }
    }
}
