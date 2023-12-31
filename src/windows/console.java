/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package windows;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ferna
 */
public class console extends javax.swing.JInternalFrame {
FuncionesCMD cmd;
    /**
     * Creates new form console
     */
    public console() {
        initComponents();
        cmd = new FuncionesCMD("");
        console.setText( cmd.getCurrentPath() + ">");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();

        setClosable(true);

        console.setEditable(false);
        console.setBackground(new java.awt.Color(0, 0, 0));
        console.setColumns(20);
        console.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        console.setForeground(new java.awt.Color(255, 255, 255));
        console.setLineWrap(true);
        console.setRows(5);
        console.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                consoleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(console);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void print(String msg) {
        String newText = console.getText();
        newText += "\n" + msg;
        console.setText(newText);
    }
    private String parseLine() {
        String lineNormal = "";
        String lineReverse = "";
        String consoleText = console.getText();         
        
        // Tomar de la consola la linea
        for (int i = consoleText.length() - 1; i >= 0; i--) {
            if (consoleText.charAt(i) == '>') break;
            lineReverse += consoleText.charAt(i);
        }
        
        // Invertir y guardar
        for (int i = lineReverse.length()-1;i >= 0; i--) {
            lineNormal += lineReverse.charAt(i);
        }
        
        return lineNormal;
        
    }
    
    private void parseCommand() {
        String line = parseLine();
        String[] args = line.trim().split(" ");
        System.out.println(args.length);
        
        switch (args[0].toLowerCase()) {
            case "cd":
                System.out.println("Se ejecuto comando CD");
                if (args.length == 1) {
                    print("Ingrese un directorio para cambiar.");
                    break;
                }
                cmd.CHDIR(args[1]);
                break;
                
            case "...":
                cmd.CHDIR("..");
                break;
                
            case "dir" :
                System.out.println("Se ejecuto comando dir");
                print(cmd.listarFiles(cmd.getCurrentPath()));
                break;
                
            case "mkdir":
                System.out.println("Se Ejucuto Comando Mkdir");
                if (args.length == 1) {
                    print("Ingrese un directorio para crear.");
                    break;
                }
                print(cmd.MKDIR(cmd.getCurrentPath() + "/" + args[1]));
                break;
                
            case "exit":
                dispose();
                break;
                
            case "mfile":
                if (args.length == 1) {
                    print("Ingrese el nombre del archivo.");
                    break;
                }
                print(cmd.MFILE(cmd.getCurrentPath() + "/" + args[1]));
                break;
                
            case "rm":
                if (args.length == 1) {
                    print("Ingrese la carpeta/archivo que desea eliminar.");
                    break;
                }
                
                File destino = new File(cmd.getCurrentPath() + "/" + args[1]);
                print(cmd.Eliminar(destino));
                break;
            case "date":
                LocalDate date = LocalDate.now();
                print(date.toString());
                break;
                
            case "time":
                LocalTime time = LocalTime.now();
                print(time.toString());
                break;
                
            case "rd":
                if (args.length == 1) {
                    print("Ingrese el archivo que desea leer.");
                    break;
                }
                
                print(cmd.leer(cmd.getCurrentPath() + "/" + args[1].trim()));
                break;
                
                
            case "wr":
                if (args.length < 3) {
                    print("Ingrese el archivo y contenido que desea escribir\nEjemplo: wr <archivo> <mensaje>");
                    break;
                }
                
                String mensaje = "";
                
                for (int i = 2; i<args.length;i++){
                    mensaje += args[i] + " ";
                }
                
                System.out.println(cmd.getCurrentPath() + "/" + args[1]);
                print(cmd.Escribir(mensaje, cmd.getCurrentPath() + "/" + args[1]));
                break;
        }
    }
    private void consoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consoleKeyPressed
        // TODO add your handling code here:

        int[] codigosProhibidos = {27, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 19, 155, 127, 36, 33, 34, 35, 16, 38, 37, 40, 38, 39, 17, 18};

        for (int codigo:codigosProhibidos) {
            if (evt.getKeyCode() == codigo) return;
        }

        if (evt.getKeyCode() == 8) {
            String newText = console.getText();

            if (newText.charAt(newText.length() - 1) == '>') {
            } else {
                newText = newText.substring(0, newText.length()-1);
                console.setText(newText);
            }

            return;

        } else if (evt.getKeyCode() == 10) {
            parseCommand();
            console.setText(console.getText() + "\n" + cmd.getCurrentPath() + ">");
            return;
        }

        String newText = console.getText();
        newText += evt.getKeyChar();
        console.setText(newText);
    }//GEN-LAST:event_consoleKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea console;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
