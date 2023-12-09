
import java.awt.Image;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;


/**
 *
 * @author villa
 */
public class Perfil extends javax.swing.JPanel {

    private RandomAccessFile siguiendo,seguidores,cantseguidores,cantsiguiendo;
    LogicaTwitter logica=new LogicaTwitter();
    UsersTwit userstwit=new UsersTwit();
    Buscarusuarios buscar=new Buscarusuarios(userstwit);
    private String name,use,fec;
    private char ge;
    private int age,contar=0;
    //private RandomAccessFile twits;
    private ArrayList<String[]> twits;
    public Perfil(String nombre,char genero,int edad,String fecha) {
        initComponents();
        this.name=nombre;
        this.ge=genero;
        this.age=edad;
        this.fec=fecha;
        lbusuario.setText("@"+userstwit.getUserlog());
        lbname1.setText(name);
        lbgenero.setText("Genero "+ge);
        lbedad.setText(age+" Años");
        lbfecha.setText("Se unio el "+fec);
        try{
        cantseguidores=new RandomAccessFile("Usertwit/"+userstwit.getUserlog()+"/cantseguidores.xd", "rw");
        cantsiguiendo=new RandomAccessFile("Usertwit/"+userstwit.getUserlog()+"/cantsiguiendo.xd", "rw");
        lbseguidores1.setText(getseguidores()+" Seguidores");
        lbsiguiendo.setText(getsiguiendo()+" Siguiendo");
        subirtweets();
        }catch(IOException e){
            System.out.println("No se pudo subir los tweets"); 
        }
    }
    private int getsiguiendo() throws IOException{
    cantsiguiendo.seek(0);
    while(cantsiguiendo.getFilePointer()<cantsiguiendo.length()){
    int cantSiguiendo= cantsiguiendo.readInt();
     return cantSiguiendo;
    }
    return 0;
    }
    //numero de seguidores
    private int getseguidores()throws IOException{
        System.out.println("entra al get seguidores");
        cantseguidores.seek(0);
        while(cantseguidores.getFilePointer()<cantseguidores.length()){
         int cantseguidore= cantseguidores.readInt();
            System.out.println("cantseguidores: "+cantseguidore);
             return cantseguidore;
        }
        return 0;
    }
private void subirtweets() throws IOException {
    paneltweets.removeAll();
    paneltweets.setLayout(new BoxLayout(paneltweets, BoxLayout.Y_AXIS));

    twits = logica.cargarTwits();

    if (!twits.isEmpty()) {
        // Iterar en orden inverso para agregar los nuevos tweets al principio
        for (int i = twits.size() - 1; i >= 0; i--) {
            String[] tweet = twits.get(i);
            String usuario = tweet[0];
            String texto = tweet[1];
            String fecha = tweet[2];

            long tiempoEnMilisegundos = Long.parseLong(fecha);
            Date fechadate = new Date(tiempoEnMilisegundos);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fechaFormateada = formatoFecha.format(fechadate);

            if(usuario.equals(userstwit.getUserlog())){
            Tweets twee = new Tweets(usuario, texto, fechaFormateada);
            paneltweets.add(twee);
            }
        }

        paneltweets.revalidate();
        paneltweets.repaint();
        scrolltweets.revalidate();
        scrolltweets.repaint();

        // Mover la barra de desplazamiento al final
        JScrollBar barra = scrolltweets.getVerticalScrollBar();
        barra.setValue(barra.getMaximum());
    }
}
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbfoto = new javax.swing.JLabel();
        lbusuario = new javax.swing.JLabel();
        lbname1 = new javax.swing.JLabel();
        lbfecha = new javax.swing.JLabel();
        lbgenero = new javax.swing.JLabel();
        lbedad = new javax.swing.JLabel();
        lbsiguiendo = new javax.swing.JLabel();
        lbseguidores = new javax.swing.JLabel();
        lbname2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        scrolltweets = new javax.swing.JScrollPane();
        paneltweets = new javax.swing.JPanel();
        lbseguidores1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(683, 840));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lbfoto.setText("Foto");
        lbfoto.setOpaque(true);
        add(lbfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 140));

        lbusuario.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lbusuario.setText("Usuario");
        add(lbusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 180, 30));

        lbname1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbname1.setText("Nombre");
        add(lbname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 160, 30));

        lbfecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbfecha.setForeground(new java.awt.Color(102, 102, 102));
        lbfecha.setText("Fecha en que se unio");
        add(lbfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 160, 30));

        lbgenero.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbgenero.setForeground(new java.awt.Color(102, 102, 102));
        lbgenero.setText("genero");
        add(lbgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 160, 30));

        lbedad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbedad.setForeground(new java.awt.Color(102, 102, 102));
        lbedad.setText("Edad");
        add(lbedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 160, 30));

        lbsiguiendo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbsiguiendo.setText("0 Siguiendo");
        add(lbsiguiendo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 90, 30));

        lbseguidores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbseguidores.setForeground(new java.awt.Color(255, 0, 51));
        lbseguidores.setText("Desactivar cuenta");
        lbseguidores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbseguidoresMouseClicked(evt);
            }
        });
        add(lbseguidores, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 130, 30));

        lbname2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lbname2.setText("Mis Tweets");
        add(lbname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 180, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 690, 10));

        scrolltweets.setBorder(null);

        paneltweets.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout paneltweetsLayout = new javax.swing.GroupLayout(paneltweets);
        paneltweets.setLayout(paneltweetsLayout);
        paneltweetsLayout.setHorizontalGroup(
            paneltweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        paneltweetsLayout.setVerticalGroup(
            paneltweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        scrolltweets.setViewportView(paneltweets);

        add(scrolltweets, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 690, 570));

        lbseguidores1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbseguidores1.setText("0 Seguidores");
        add(lbseguidores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 110, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void lbseguidoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbseguidoresMouseClicked
       /* try{
        int opcion=JOptionPane.showInternalConfirmDialog(null, "Esta segura de desactivar su cuenta? ");
         if (opcion == JOptionPane.YES_OPTION) {
         JOptionPane.showMessageDialog(null, "Su cuenta ha sido desactivada");
             userstwit.desaccuenta();
             LoginTwitter login=new LoginTwitter();
        login.setVisible(true);
        } else if (opcion == JOptionPane.NO_OPTION || opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
             System.out.println("no cerro xd");
        } 
        
        }catch(IOException e){
            
        }*/
    }//GEN-LAST:event_lbseguidoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbedad;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JLabel lbfoto;
    private javax.swing.JLabel lbgenero;
    private javax.swing.JLabel lbname1;
    private javax.swing.JLabel lbname2;
    private javax.swing.JLabel lbseguidores;
    private javax.swing.JLabel lbseguidores1;
    private javax.swing.JLabel lbsiguiendo;
    private javax.swing.JLabel lbusuario;
    private javax.swing.JPanel paneltweets;
    private javax.swing.JScrollPane scrolltweets;
    // End of variables declaration//GEN-END:variables
}
