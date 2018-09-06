package com.laugracianool.conversordemoneda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;


public class ConversorActivity extends AppCompatActivity {

    final String[] datos = new String[]{"Pesos Colombianos", "Pesos Uruguayos", "Pesos Chilenos"};

    private Spinner Actual;
    private Spinner Cambio;
    private EditText ValoraCambiar;
    private TextView Resultado;

    final private double PesosCo_PesosU = 0.010;
    final private double PesosU_PesosCh = 21.28;
    final private double PesosCo_PesosCh = 0.22;
    final private double PesosU_PesosCo = 95.73;
    final private double PesosCh_PesosU = 0.046;
    final private double PesosCh_PesosCo = 4.49;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        Actual = findViewById(R.id.Actual);
        Cambio = findViewById(R.id.Cambio);
        ValoraCambiar = findViewById(R.id.ValoraCambiar);
        Resultado = findViewById(R.id.Resultado);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, datos);
        Actual.setAdapter(adapter);
    }

    public void Resultadof(View view) {
        String monedaActual = Actual.getSelectedItem().toString();
        String monedaCambio = Cambio.getSelectedItem().toString();
        double ValorCambio = Double.parseDouble(ValoraCambiar.getText().toString());
        double Resultadof = cambio(monedaActual, monedaCambio, ValorCambio);

        if (Resultadof > 0) {
            Resultado.setText(String.format("Por %5.2f%s, usted recibirá %5.2f %s", ValorCambio, monedaActual, Resultadof, monedaCambio));
            ValoraCambiar.setText("");
        } else {
            Resultado.setText(String.format("Usted recibirá"));
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
    private double cambio(String monedaActual, String monedaCambio, double ValorCambio) {
        double total = 0;
        switch (monedaActual) {
            case "PesosCo":
                if (monedaCambio.equals("PesosU")) {
                    total = PesosCo_PesosU * ValorCambio;
                }
                if (monedaCambio.equals("PesosCh")) {
                    total = PesosCo_PesosCh * ValorCambio;
                }
                break;
            case "PesosU":
                if (monedaCambio.equals("PesosCo")) {
                    total = PesosU_PesosCo * ValorCambio;
                }
                if (monedaCambio.equals("PesosCh")) {
                    total = PesosU_PesosCh * ValorCambio;
                }
                break;
            case "PesosCh":
                if (monedaCambio.equals("PesosCo"))
                    total = PesosCh_PesosCo * ValorCambio;
                if (monedaCambio.equals("PesosU"))
                    total = PesosCh_PesosU * ValorCambio;
                break;
        }
        return total;
    }
}

