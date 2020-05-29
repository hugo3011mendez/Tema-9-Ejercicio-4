package ejercicio;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.*;
import java.text.DecimalFormat;

public class FrameEjercicio4 extends JFrame implements ActionListener, ItemListener {
    /**
     * Comprueba si hay números en las cajas de texto para poder realizar operaciones
     * 
     * @return Booleano indicando si está bien o no
     */
    public boolean comprobarNumeros(){
        try {
            // Dependiendo de los decimales que se hayan escogido en el ComboBox declaro las variables a int o a double
            if((Integer)cbDecimales.getSelectedItem() == 0){
                int numerostxf1, numerostxf2;
                numerostxf1 = Integer.parseInt(txf1.getText());
                numerostxf2 = Integer.parseInt(txf2.getText());
            }
            else{
                double numerostxf1, numerostxf2;
                numerostxf1 = Double.parseDouble(txf1.getText());
                numerostxf2 = Double.parseDouble(txf2.getText());
            }

            return true;
        } catch (Exception e) {
            lblError.setText("No hay números válidos en las cajas de texto");
            lblError.setSize(lblError.getPreferredSize());

            return false;
        }
    }

    JTextField txf1, txf2;
    JLabel lblSigno, lblResultado, lblError, lblDecimales;
    JRadioButton rbSuma, rbResta, rbMultiplicacion, rbDivision;
    ButtonGroup grupoOperacion;
    JButton btnOperacion;
    JComboBox cbDecimales;

    double num1, num2, resultado; // Declaro variables para los números de los TextField y el resultado
    String resultadoTexto;

    public FrameEjercicio4() {
        super("Tema 9 Ejercicio 4");
        setLayout(null);


        // Creo las cajas de texto
        txf1 = new JTextField(15);
        txf1.setLocation(30, 15);
        txf1.setSize(txf1.getPreferredSize());
        add(txf1);

        txf2 = new JTextField(15);
        txf2.setLocation(250, 15);
        txf2.setSize(txf2.getPreferredSize());
        add(txf2);


        // Creo las etiquetas
        lblSigno = new JLabel("+");
        lblSigno.setLocation(210, 15);
        lblSigno.setFont(new Font("Arial", Font.BOLD, 16));
        lblSigno.setSize(lblSigno.getPreferredSize());
        add(lblSigno);

        lblResultado = new JLabel("=");
        lblResultado.setLocation(425, 15);
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        lblResultado.setSize(lblResultado.getPreferredSize());
        add(lblResultado);

        lblError = new JLabel();
        lblError.setLocation(400, 85);
        lblError.setSize(lblError.getPreferredSize());
        lblError.setForeground(Color.RED);
        add(lblError);


        // Creo los RadioButtons con su grupo
        rbSuma = new JRadioButton("Suma");
        rbSuma.setLocation(100, 60);
        rbSuma.setSize(rbSuma.getPreferredSize());
        rbSuma.addItemListener(this);
        rbSuma.setSelected(true);
        add(rbSuma);

        rbResta = new JRadioButton("Resta");
        rbResta.setLocation(100, 80);
        rbResta.setSize(rbResta.getPreferredSize());
        rbResta.addItemListener(this);
        add(rbResta);

        rbMultiplicacion = new JRadioButton("Multiplicación");
        rbMultiplicacion.setLocation(100, 100);
        rbMultiplicacion.setSize(rbMultiplicacion.getPreferredSize());
        rbMultiplicacion.addItemListener(this);
        add(rbMultiplicacion);

        rbDivision = new JRadioButton("División");
        rbDivision.setLocation(100, 120);
        rbDivision.setSize(rbDivision.getPreferredSize());
        rbDivision.addItemListener(this);
        add(rbDivision);

        grupoOperacion=new ButtonGroup(); // Agrupo los RadioButtons de las operaciones
        grupoOperacion.add(rbSuma);
        grupoOperacion.add(rbResta);
        grupoOperacion.add(rbMultiplicacion);
        grupoOperacion.add(rbDivision);


        // Creo el botón de operación
        btnOperacion = new JButton("Operación");
        btnOperacion.setLocation(270, 80);
        btnOperacion.setSize(btnOperacion.getPreferredSize());
        btnOperacion.addActionListener(this);
        add(btnOperacion);

        
        // Creo el ComboBox y su etiqueta para elegir decimales
        cbDecimales = new JComboBox<Integer>();
        cbDecimales.setLocation(20, 75);
        cbDecimales.addItem(0);
        cbDecimales.addItem(1);
        cbDecimales.addItem(2);
        cbDecimales.addItem(3);
        cbDecimales.addItem(4);
        cbDecimales.addItem(5);
        cbDecimales.setSize(cbDecimales.getPreferredSize());
        cbDecimales.addItemListener(this);
        add(cbDecimales);

        lblDecimales = new JLabel("Elige los decimales : ");
        lblDecimales.setLocation(20, 40);
        lblDecimales.setSize(lblDecimales.getPreferredSize());
        add(lblDecimales);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnOperacion){
            if(comprobarNumeros()){
                num1 = Double.parseDouble(txf1.getText());
                num2 = Double.parseDouble(txf2.getText());

                // Le doy valor al resultado dependiendo de la operación que sea
                if(rbSuma.isSelected()){
                    resultado = num1 + num2;
                }
                else if(rbResta.isSelected()){
                    resultado = num1 - num2;
                }
                else if(rbMultiplicacion.isSelected()){
                    resultado = num1 * num2;
                }
                else if(rbDivision.isSelected()){
                    resultado = num1 / num2;
                }

                // Por cada caso hago que la variable resultado tenga menos o más decimales
                if((Integer)cbDecimales.getSelectedItem() == 0) {
                    lblResultado.setText("= " + (int)resultado);
                    lblResultado.setSize(lblResultado.getPreferredSize());
                }
                else{
                    DecimalFormat df = new DecimalFormat();
                    if((Integer)cbDecimales.getSelectedItem() == 1){
                        df = new DecimalFormat("0.0");
                    }
                    else if((Integer)cbDecimales.getSelectedItem() == 2){
                        df = new DecimalFormat("0.00");
                    }
                    else if((Integer)cbDecimales.getSelectedItem() == 3){
                        df = new DecimalFormat("0.000");
                    }
                    else if((Integer)cbDecimales.getSelectedItem() == 4){
                        df = new DecimalFormat("0.0000");
                    }
                    else if((Integer)cbDecimales.getSelectedItem() == 5){
                        df = new DecimalFormat("0.00000");
                    }

                    lblResultado.setText("= " + df.format(resultado));
                    lblResultado.setSize(lblResultado.getPreferredSize());
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Cuando se active cualquier RadioButton, el resultado se limpiará
        lblResultado.setText("=");
        lblResultado.setSize(lblResultado.getPreferredSize());

        if (e.getStateChange() == ItemEvent.SELECTED) { // Aquí compruebo cuando un RadioButton ha sido seleccionado
            // Acciones a realizar cuando se pulsan los RadioButton
            if(e.getSource() == rbSuma){ 
                lblSigno.setText("+");
                lblSigno.setSize(lblSigno.getPreferredSize());
            }
            else if(e.getSource() == rbResta){
                lblSigno.setText("-");
                lblSigno.setSize(lblSigno.getPreferredSize());
            }
            else if(e.getSource() == rbMultiplicacion){
                lblSigno.setText("x");
                lblSigno.setSize(lblSigno.getPreferredSize());
            }
            else if(e.getSource() == rbDivision){
                lblSigno.setText("/");
                lblSigno.setSize(lblSigno.getPreferredSize());
            }
        }
    }
}