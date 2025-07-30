package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame {

    private JPanel panelContenido;
    private Dimension dim=null;

    public Principal() {
        setTitle("Tienda Tecnológica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        dim = getToolkit().getScreenSize();
		setSize(dim.width/2,dim.height/2);
		setLocationRelativeTo(null);

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
        btnComponentes.addActionListener(e -> cambiarVista("Gestión de Componentes"));
        btnCombos.addActionListener(e -> cambiarVista("Sección de Combos"));

        // Agregar paneles a la ventana
        add(panelMenu, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    // M�todo para cambiar el texto del centro
    private void cambiarVista(String texto) {
        panelContenido.removeAll();
        JLabel nuevo = new JLabel(texto, SwingConstants.CENTER);
        nuevo.setFont(new Font("Arial", Font.PLAIN, 18));
        panelContenido.add(nuevo, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Principal ventana = new Principal();
//            ventana.setVisible(true);
//        });
//    }
}

