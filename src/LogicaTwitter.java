
import java.awt.Image;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LogicaTwitter {

    UsersTwit user=new UsersTwit();
    ArrayList<String[]> cargartwits=new ArrayList<>();
    private RandomAccessFile tweets,fotoarchivo;
    private Image fotoperfil;
    public LogicaTwitter(){
        try {
            tweets = new RandomAccessFile("Usertwit/" + user.getUserlog() + "/twits.twc", "rw");
            fotoarchivo = new RandomAccessFile("Usertwit/user.twc", "rw");
        } catch (IOException e) {
            System.out.println("NO SE ENCONTRO");
        }
    }
   
     public void guardarTwit(String txt) throws IOException {
        tweets.seek(tweets.length());
        Date fechaActual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    // Formatear la fecha como una cadena
    String fechaString = dateFormat.format(fechaActual);
        try {
            // Convierte la cadena a un objeto Date
            Date fechaDate = dateFormat.parse(fechaString);

            // Obtiene el valor long de la fecha
            long valorLong = fechaDate.getTime();

            // Imprime el valor long
            System.out.println("Valor long de la fecha: " + valorLong);
            tweets.writeUTF(user.getUserlog());
            tweets.writeUTF(txt);
            tweets.writeLong(valorLong);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
   
    public ArrayList<String[]> misTwits() throws IOException {
        ArrayList<String[]> mensajes = new ArrayList<>();
        tweets.seek(0);

        while (tweets.getFilePointer() < tweets.length()) {
            String[] temp = new String[3];
            temp[0] = tweets.readUTF();
            temp[1] = tweets.readUTF();
            temp[2] = tweets.readLong() + "";
            mensajes.add(temp);
        }

        return mensajes;
    }
    public ArrayList<String[]> cargarTwits() throws IOException{
        ArrayList<String[]> twits = new ArrayList<>();

        // Carga los propios twits
        if (misTwits() != null) {
            twits.addAll(misTwits());
        }
       
        return twits;
    }
     
  
    
}
