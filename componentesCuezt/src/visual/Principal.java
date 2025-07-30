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
        dim = getToolkit().getScreenSize();
        setSize(dim.width / 2, dim.height / 2);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Panel lateral con botones
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(5, 1));
        panelMenu.setBackground(Color.LIGHT_GRAY);
        panelMenu.setPreferredSize(new Dimension(160, getHeight()));

        // Botones del menu
        JButton btnHome = new JButton("Home");
        JButton btnVentas = new JButton("Ventas");
        JButton btnClientes = new JButton("Clientes");
        JButton btnComponentes = new JButton("Componentes");
        JButton btnCombos = new JButton("Combos");

        // Agregar botones al panel del menu
        panelMenu.add(btnHome);
        panelMenu.add(btnVentas);
        panelMenu.add(btnClientes);
        panelMenu.add(btnComponentes);
        panelMenu.add(btnCombos);

        // Panel de contenido
        panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        JLabel inicio = new JLabel("Bienvenido a la Tienda", SwingConstants.CENTER);
        inicio.setFont(new Font("Arial", Font.BOLD, 20));
        panelContenido.add(inicio, BorderLayout.CENTER);

        // Acciones de los botones
        btnHome.addActionListener(e -> cambiarVista("Bienvenido a la Tienda"));
        btnVentas.addActionListener(e -> cambiarVista("Sección de Ventas"));
        btnClientes.addActionListener(e -> cambiarVista("Gestión de Clientes"));
        btnCombos.addActionListener(e -> cambiarVista("Sección de Combos"));

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
                    JOptionPane.showMessageDialog(Principal.this, "Seleccionaste: " + seleccion);
                });

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


