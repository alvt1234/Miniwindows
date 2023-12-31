
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoginTwitter extends javax.swing.JFrame {
       int anchoPantalla,altoPantalla ;
       JLabel labelfondo=new JLabel();
    public LoginTwitter() {
       initComponents();
       this.setLocationRelativeTo(null);
       this.setExtendedState(MAXIMIZED_BOTH);
       
        anchoPantalla = getContentPane().getSize().width;
        altoPantalla = getContentPane().getSize().height;

         
         labelfondo.setSize(anchoPantalla, altoPantalla);
         labelfondo.setHorizontalAlignment(JLabel.CENTER);
         labelfondo.setVerticalAlignment(JLabel.CENTER);
         aggimagen(labelfondo, "src/imagentwitter/twitter.png");
         panelfondo.add(labelfondo,BorderLayout.CENTER);

    }

    private ImageIcon crearImagenSemitransparente(String ruta) {
    ImageIcon image = new ImageIcon(ruta);
    Image imagenOriginal = image.getImage();
    
    // Crear una imagen con transparencia
    BufferedImage imagenTransparente = new BufferedImage(imagenOriginal.getWidth(null), imagenOriginal.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics g = imagenTransparente.getGraphics();
    g.drawImage(imagenOriginal, 0, 0, null);

    for (int y = 0; y < imagenTransparente.getHeight(); y++) {
        for (int x = 0; x < imagenTransparente.getWidth(); x++) {
            int rgb = imagenTransparente.getRGB(x, y);
            rgb = (rgb & 0x00FFFFFF) | (128 << 24); 
            imagenTransparente.setRGB(x, y, rgb);
        }
    }

    // Crear un ImageIcon con la imagen semitransparente
    ImageIcon iconoTransparente = new ImageIcon(imagenTransparente);
    g.dispose();

    return iconoTransparente;
}
    private void aggimagen(JLabel label, String ruta){
        ImageIcon image=new ImageIcon(ruta);
        Icon icono= new ImageIcon(image.getImage().getScaledInstance(labelfondo.getWidth(), labelfondo.getHeight(), Image.SCALE_DEFAULT));
          label.setIcon(icono);
          this.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelfondo = new javax.swing.JPanel();
        btregistrar = new javax.swing.JButton();
        btiniciarsesion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        btregistrar.setBorderPainted(false);
        btregistrar.setContentAreaFilled(false);
        btregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btregistrarActionPerformed(evt);
            }
        });

        btiniciarsesion.setBorderPainted(false);
        btiniciarsesion.setContentAreaFilled(false);
        btiniciarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btiniciarsesionActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jButton1.setText("x");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelfondoLayout = new javax.swing.GroupLayout(panelfondo);
        panelfondo.setLayout(panelfondoLayout);
        panelfondoLayout.setHorizontalGroup(
            panelfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelfondoLayout.createSequentialGroup()
                .addContainerGap(1043, Short.MAX_VALUE)
                .addGroup(panelfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btiniciarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelfondoLayout.createSequentialGroup()
                            .addComponent(btregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(159, 159, 159)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelfondoLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelfondoLayout.setVerticalGroup(
            panelfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelfondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
                .addComponent(btregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(btiniciarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelfondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelfondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btregistrarActionPerformed
        CrearCuenta cuenta=new CrearCuenta(this);
        crearimage();
        cuenta.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            image();
        }
    });
        cuenta.setVisible(true);
       
    }//GEN-LAST:event_btregistrarActionPerformed

    private void btiniciarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btiniciarsesionActionPerformed
        IniciarSesion sesion=new IniciarSesion(this);
        crearimage();
        sesion.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
           image();
        }
    });
     sesion.setVisible(true);
     
    }//GEN-LAST:event_btiniciarsesionActionPerformed

    public void crearimage(){
      ImageIcon imagenTransparente = crearImagenSemitransparente("src/imagentwitter/twitter.png");
      Icon icono= new ImageIcon(imagenTransparente.getImage().getScaledInstance(labelfondo.getWidth(), labelfondo.getHeight(), Image.SCALE_DEFAULT));
      labelfondo.setIcon(icono);   
    }
    public void image(){
         aggimagen(labelfondo, "src/imagentwitter/twitter.png");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginTwitter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btiniciarsesion;
    private javax.swing.JButton btregistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelfondo;
    // End of variables declaration//GEN-END:variables
}
