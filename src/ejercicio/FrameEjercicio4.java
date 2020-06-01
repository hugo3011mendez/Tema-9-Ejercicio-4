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
            double numerostxf1, numerostxf2;
            numerostxf1 = Double.parseDouble(txf1.getText());
            numerostxf2 = Double.parseDouble(txf2.getText());
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
    File archivoOperaciones = new File(System.getProperty("user.home") + System.getProperty("file.separator") +  ".T9Ejercicio4.txt"); // Creo una variable para el archivo de operaciones

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

        String[] nums = { "0","1","2","3","4","5" }; // Declaro un array para llenar el ComboBox 
        // Creo el ComboBox y su etiqueta para elegir decimales
        cbDecimales = new JComboBox<String>(nums);
        cbDecimales.setLocation(20, 75);
        cbDecimales.setSize(cbDecimales.getPreferredSize());
        cbDecimales.addItemListener(this);
        add(cbDecimales);

        lblDecimales = new JLabel("Elige los decimales : ");
        lblDecimales.setLocation(20, 40);
        lblDecimales.setSize(lblDecimales.getPreferredSize());
        add(lblDecimales);

        if(archivoOperaciones.exists()){ // Acciones a realizar cuando se inicia la aplicación y existe el archivo de las operaciones
            String texto;
            try (Scanner mostrarArchivo = new Scanner(archivoOperaciones)) {  // Hago un try-with-resources para leer el archivo
                while (mostrarArchivo.hasNext()) {
                    texto = mostrarArchivo.nextLine();

                    try {
                        String num1 = texto.split(":")[0], num2 = texto.split(":")[2], signo = texto.split(":")[1];
    
                        txf1.setText(num1);
                        txf2.setText(num2);
                        lblSigno.setText(signo);
    
                        if(signo.equals("-")){ // Si el signo de la operación guardada y que se cargó en el programa es el de resta, se selecciona el RadioButton de Resta en vez del de Suma
                            rbSuma.setSelected(false);
                            rbResta.setSelected(true);
                        }
                        else if(signo.equals("x")){
                            rbSuma.setSelected(false);
                            rbMultiplicacion.setSelected(true);
                        }
                        else if(signo.equals("/")){
                            rbSuma.setSelected(false);
                            rbDivision.setSelected(true);
                        }
                    } catch (Exception e) {
                        
                    }
                }
            }
            catch (IOException e3) {
                System.err.println("Error de acceso al archivo de operaciones");
            }
        }

        addWindowListener(new WindowAdapter() { // Aquí programo la confirmación al salir de esta ventana usando el adaptador de WindowListener
            public void windowClosing(WindowEvent e) {
                if(!archivoOperaciones.exists()){ // Acciones a realizar si el archivo no existe
                    try (PrintWriter f = new PrintWriter(archivoOperaciones)){ // Creo el archivo
                        
                    } catch (Exception e1) {
                        System.err.println("Error al crear el archivo de operaciones");
                    }
                }
                
                try (PrintWriter escribir = new PrintWriter(new FileWriter(archivoOperaciones, false))){ // Guardo los datos de la operación en el archivo sobreescribiendo los que ya estaban antes si había
                    escribir.println(txf1.getText() + ":" + lblSigno.getText() + ":" + txf2.getText());
                } catch (IOException e2) {
                    System.err.println("Error de acceso al archivo de operaciones");
                }    

                e.getWindow().dispose();
            }
        });
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
                    if(num2 == 0){
                        lblError.setText("No se puede dividir entre 0");
                        lblError.setSize(lblError.getPreferredSize());
                    }
                    else{
                        resultado = num1 / num2;
                    }
                }

                if(rbDivision.isSelected()){
                    if(num2 != 0){ // Compruebo si no es una división entre 0
                        lblResultado.setText(String.format("%." + cbDecimales.getSelectedIndex() + "f", resultado)); // Actualizo la etiqueta para mostrar el resultado
                        lblResultado.setSize(lblResultado.getPreferredSize());
        
                        lblError.setText(""); // Y actualizo la etiqueta de error para que no lo muestre si ha salido bien la operación    
                    }
                }
                else{
                    lblResultado.setText(String.format("%." + cbDecimales.getSelectedIndex() + "f", resultado)); // Actualizo la etiqueta para mostrar el resultado
                    lblResultado.setSize(lblResultado.getPreferredSize());
    
                    lblError.setText(""); // Y actualizo la etiqueta de error para que no lo muestre si ha salido bien la operación    
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