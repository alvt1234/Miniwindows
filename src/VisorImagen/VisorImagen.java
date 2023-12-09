package VisorImagen;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VisorImagen extends JFrame {
    private List<String> imagePaths;
    private int currentIndex;
    private JLabel mainImageLabel;
    private JLabel previousImageLabel;
    private JLabel nextImageLabel;

    public VisorImagen() {
        imagePaths = new ArrayList<>();
        currentIndex = 0;

        setTitle("Visor de Imágenes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30)); // Fondo gris oscuro

        JLabel titleLabel = new JLabel("Visor de Imágenes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(255, 255, 255)); // Color del título blanco
        add(titleLabel, BorderLayout.NORTH);

        mainImageLabel = new JLabel();
        previousImageLabel = new JLabel();
        nextImageLabel = new JLabel();

        updateImages();

        JButton prevButton = new JButton("Anterior");
        JButton nextButton = new JButton("Siguiente");

        JButton selectFolderButton = new JButton("Seleccionar Carpeta");
        selectFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarCarpeta();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anteriorImagen();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguienteImagen();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(selectFolderButton);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(30, 30, 30)); // Fondo gris oscuro
        imagePanel.add(previousImageLabel, BorderLayout.WEST);
        imagePanel.add(mainImageLabel, BorderLayout.CENTER);
        imagePanel.add(nextImageLabel, BorderLayout.EAST);

        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void seleccionarCarpeta() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle("Seleccionar Carpeta de Imágenes");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String folderPath = chooser.getSelectedFile().getAbsolutePath();
            imagePaths = loadImages(folderPath);
            currentIndex = 0;
            updateImages();
        }
    }

    private List<String> loadImages(String folderPath) {
        List<String> paths = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImageFile(file.getName())) {
                        paths.add(file.getAbsolutePath());
                    }
                }
            }
        }

        return paths;
    }

    private boolean isImageFile(String fileName) {
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        for (String extension : allowedExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

   private void updateImages() {
    if (!imagePaths.isEmpty() && currentIndex >= 0 && currentIndex < imagePaths.size()) {
        String currentImagePath = imagePaths.get(currentIndex);

        // Obtener las dimensiones del contenedor principal
        int mainImageWidth = mainImageLabel.getWidth();
        int mainImageHeight = mainImageLabel.getHeight();

        // Escalar la imagen principal proporcionalmente
        ImageIcon currentIcon = new ImageIcon(currentImagePath);
        mainImageLabel.setIcon(scaleImage(currentIcon, mainImageWidth, mainImageHeight));

        // Miniatura anterior
        int previousIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
        ImageIcon previousIcon = new ImageIcon(imagePaths.get(previousIndex));
        previousImageLabel.setIcon(scaleImage(previousIcon, 100, 100));

        // Miniatura siguiente
        int nextIndex = (currentIndex + 1) % imagePaths.size();
        ImageIcon nextIcon = new ImageIcon(imagePaths.get(nextIndex));
        nextImageLabel.setIcon(scaleImage(nextIcon, 100, 100));
    } else {
        mainImageLabel.setIcon(null);
        previousImageLabel.setIcon(null);
        nextImageLabel.setIcon(null);
    }
}

    private void anteriorImagen() {
        if (!imagePaths.isEmpty()) {
            currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
            updateImages();
        }
    }

    private void siguienteImagen() {
        if (!imagePaths.isEmpty()) {
            currentIndex = (currentIndex + 1) % imagePaths.size();
            updateImages();
        }
    }

    private ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        if (width <= 0 || height <= 0) {
            // Evitar dimensiones no válidas
            return icon;
        }
        

        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VisorImagen().setVisible(true);
            }
        });
    }
}