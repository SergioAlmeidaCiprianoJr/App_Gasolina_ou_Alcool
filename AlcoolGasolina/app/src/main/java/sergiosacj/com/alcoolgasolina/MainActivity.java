package sergiosacj.com.alcoolgasolina;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText alcool, gasolina;
    private TextView resposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alcool = findViewById( R.id.ansAlcool );
        gasolina = findViewById( R.id.ansGasolina );
        resposta= findViewById( R.id.tvResultado );

    }

    public void calculaPreco( View view ) {

        String precoAlcool = alcool.getText().toString();
        String precoGasolina = gasolina.getText().toString();
        final boolean imprimeResposta = true;

        if ( validarEntada( precoAlcool, precoGasolina ) ) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Confirmar Valores");
            dialog.setMessage("Valor alcool = " + precoAlcool + "\nValor gasolina = " + precoGasolina);
            dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    calculaMelhorPreco( alcool.getText().toString(), gasolina.getText().toString() );
                }
            });
            dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resposta.setText("");
                }
            });
            dialog.setCancelable(false);
            dialog.create();
            dialog.show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Preencha os campos primeiro!", Toast.LENGTH_SHORT).show();
            resposta.setText("");
        }

    }

    public void calculaMelhorPreco( String pAlcool, String pGasolina ){

        Double precoAlcool = Double.parseDouble( pAlcool );
        Double precoGasolina = Double.parseDouble( pGasolina );

        if( precoAlcool/precoGasolina >= 0.7 ){
            resposta.setText( "Melhor utilizar a Gasolina!" );
        }
        else resposta.setText( "Melhor utilizar o Álcool!" );

    }

    public Boolean validarEntada( String pAlcool, String pGasolina) {

        Boolean entradaValida = true;

        if(pAlcool == null || pAlcool.equals("")){
            entradaValida = false;
        }
        else if(pGasolina == null || pGasolina.equals("")){
            entradaValida = false;
        }

        return entradaValida;

    }
}
