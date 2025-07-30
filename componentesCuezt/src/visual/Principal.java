package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame {

    private JPanel panelContenido;
    private Dimension dim = null;

    public Principal() {
        setTitle("Tienda Tecnológica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        dim = getToolkit().getScreenSize();
        setSize(dim.width / 2, dim.height / 2);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Panel lateral con botones
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(5, 1));
        panelMenu.setBackground(Color.LIGHT_GRAY);
        panelMenu.setPreferredSize(new Dimension(160, getHeight()));
        JButton btnFactura = new JButton("Generar Factura");
        btnFactura.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegFactura fact = new RegFactura();
        		fact.setModal(true);
        		fact.setVisible(true);
        	}
        });
        JButton btnClientes = new JButton("Clientes");
        btnClientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegClientes regCliente = new RegClientes();
        		regCliente.setModal(true);
        		regCliente.setVisible(true);
        	}
        });
        JButton btnComponentes = new JButton("Componentes");
        JButton btnCombos = new JButton("Combos");
        panelMenu.add(btnFactura);
        
        JButton btnVerFacturas = new JButton("Ver Facturas");
        btnVerFacturas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListarFactura listFact = new ListarFactura();
        		listFact.setModal(true);
        		listFact.setVisible(true);
        	}
        });
        panelMenu.add(btnVerFacturas);
        panelMenu.add(btnClientes);
        panelMenu.add(btnComponentes);
        panelMenu.add(btnCombos);

        // Panel de contenido
        panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        JLabel inicio = new JLabel("Bienvenido a la Tienda", SwingConstants.CENTER);
        inicio.setFont(new Font("Arial", Font.BOLD, 20));
        panelContenido.add(inicio, BorderLayout.CENTER);

        btnComponentes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelContenido.removeAll();
                panelContenido.setLayout(new FlowLayout());
                String[] componentes = {"", "Disco Duro",  "Microprocesador", "Memoria RAM", "Tarjeta Madre"};
                JComboBox<String> comboBox = new JComboBox<>(componentes);
                JLabel etiqueta = new JLabel("Seleccione un componente:");
                etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
                panelContenido.add(etiqueta);
                panelContenido.add(comboBox);

                comboBox.addActionListener(ev -> {
                    String seleccion = (String) comboBox.getSelectedItem();
                    if ("Disco Duro".equalsIgnoreCase(seleccion)) {
                        RegDiscoDuro regDiscoDuro = new RegDiscoDuro();
						regDiscoDuro.setModal(true);
						regDiscoDuro.setVisible(true);
                    } else if ("Microprocesador".equalsIgnoreCase(seleccion)) {
                        RegMicroprocesador regMicroprocesador = new RegMicroprocesador();
                        regMicroprocesador.setModal(true);
                        regMicroprocesador.setVisible(true);
                    	
                    } else if ("Memoria RAM".equalsIgnoreCase(seleccion)) {
                    	RegMemoriaRAM regMemoriaRAM = new RegMemoriaRAM();
						regMemoriaRAM.setModal(true);
						regMemoriaRAM.setVisible(true);
                    }
                      else if ("Tarjeta Madre".equalsIgnoreCase(seleccion)) {
						RegTarjetaMadre regTarjetaMadre = new RegTarjetaMadre();
						regTarjetaMadre.setModal(true);
						regTarjetaMadre.setVisible(true);
                    }});

                panelContenido.revalidate();
                panelContenido.repaint();
            }
        });


        getContentPane().add(panelMenu, BorderLayout.WEST);
        getContentPane().add(panelContenido, BorderLayout.CENTER);
    }

    private void cambiarVista(String texto) {
        panelContenido.removeAll();
        JLabel nuevo = new JLabel(texto, SwingConstants.CENTER);
        nuevo.setFont(new Font("Arial", Font.PLAIN, 18));
        panelContenido.add(nuevo, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Principal ventana = new Principal();
            ventana.setVisible(true);
        });
    }
}


