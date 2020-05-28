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

public class FrameEjercicio4 extends JFrame implements ActionListener, ItemListener {
    JTextField txf1, txf2;
    JLabel lblSigno, lblResultado, lblError;
    JRadioButton rbSuma, rbResta, rbMultiplicacion, rbDivision;
    ButtonGroup grupoOperacion;
    JButton btnOperacion;
    JComboBox cbDecimales;

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
        lblSigno.setSize(lblSigno.getPreferredSize());
        add(lblSigno);

        lblResultado = new JLabel("=");
        lblResultado.setLocation(425, 15);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}