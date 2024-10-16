package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textResultado;
    private String entradaActual = "";
    private String operador = "";
    private double primerValor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResultado = findViewById(R.id.textView);

        configurarEscuchadoresBotones();

    }
    private void configurarEscuchadoresBotones() {
        configurarEscuchadorBotonNumero(R.id.button_0, "0");
        configurarEscuchadorBotonNumero(R.id.button_1, "1");
        configurarEscuchadorBotonNumero(R.id.button_2, "2");
        configurarEscuchadorBotonNumero(R.id.button_3, "3");
        configurarEscuchadorBotonNumero(R.id.button_4, "4");
        configurarEscuchadorBotonNumero(R.id.button_5, "5");
        configurarEscuchadorBotonNumero(R.id.button_6, "6");
        configurarEscuchadorBotonNumero(R.id.button_7, "7");
        configurarEscuchadorBotonNumero(R.id.button_8, "8");
        configurarEscuchadorBotonNumero(R.id.button_9, "9");
        configurarEscuchadorBotonNumero(R.id.button_dot, ".");

        configurarEscuchadorBotonOperador(R.id.button_add, "+");
        configurarEscuchadorBotonOperador(R.id.button_subtract, "-");
        configurarEscuchadorBotonOperador(R.id.button_multiply, "x");
        configurarEscuchadorBotonOperador(R.id.button_divide, "/");

        Button botonIgual = findViewById(R.id.button_equal);
        botonIgual.setOnClickListener(v -> realizarCalculo());

        Button botonBorrar = findViewById(R.id.button_clear);
        botonBorrar.setOnClickListener(v -> borrarTodo());
    }

    private void configurarEscuchadorBotonNumero(int idBoton, String valor) {
        Button boton = findViewById(idBoton);
        boton.setOnClickListener(v -> {
            entradaActual += valor;
            textResultado.setText(entradaActual);
        });
    }

    private void configurarEscuchadorBotonOperador(int idBoton, String op) {
        Button boton = findViewById(idBoton);
        boton.setOnClickListener(v -> {
            if (!entradaActual.equals("")) {
                primerValor = Double.parseDouble(entradaActual);
                operador = op;
                entradaActual = "";
            }
        });
    }

    private void realizarCalculo() {
        if (!entradaActual.equals("") && !operador.equals("")) {
            double segundoValor = Double.parseDouble(entradaActual);
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = primerValor + segundoValor;
                    break;
                case "-":
                    resultado = primerValor - segundoValor;
                    break;
                case "x":
                    resultado = primerValor * segundoValor;
                    break;
                case "/":
                    if (segundoValor != 0) {
                        resultado = primerValor / segundoValor;
                    } else {
                        textResultado.setText("Error");
                        return;
                    }
                    break;
            }

            textResultado.setText(String.valueOf(resultado));
            entradaActual = "";
            operador = "";
            primerValor = resultado;
        }
    }

    private void borrarTodo() {
        entradaActual = "";
        operador = "";
        primerValor = 0;
        textResultado.setText("0");
    }
}
