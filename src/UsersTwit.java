
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class UsersTwit {
    private RandomAccessFile registro;
    private static String userlog;
    public UsersTwit(){
    try{
    File file=new File("Usertwit");
    file.mkdirs();
    registro=new RandomAccessFile("Usertwit/user.twc","rw");
    }catch(IOException e){
        System.out.println("Erros");
    }
    }
 public boolean Existeuser(String user) throws IOException {
     
        while (registro.getFilePointer() < registro.length()) {
            registro.readUTF();
            registro.readChar();
            String usuario = registro.readUTF();
            registro.readUTF(); 
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if (user.equals(usuario)) {
                return true;
            }
        }
    return false;
}

    private String carpetauser(String user){
        return"Usertwit/"+user;
    }
    private void carpetausers(String user) throws IOException{
       File file=new File(carpetauser(user));
        file.mkdirs();
        try{
            new File("Usertwit/" + user + "/following.twc").createNewFile();
            new File("Usertwit/" + user + "/followers.twc").createNewFile();
            new File("Usertwit/" + user + "/twits.twc").createNewFile();
            }catch(IOException e){
            System.out.println("No se pudo crear");
            } 
    }
     public void agguser(String nombre, char genero, String user, String contra, int edad,String fecha,boolean activa) throws IOException {
        if (!Existeuser(user)) {
                registro.seek(registro.length());
                registro.writeUTF(nombre);
                registro.writeChar(genero);
                registro.writeUTF(user);
                registro.writeUTF(contra);
                registro.writeInt(edad);
                registro.writeUTF(fecha);
                registro.writeBoolean(activa);
                carpetausers(user);
                System.out.println("agg user");
                userlog=user;
        }
    }
     
     public boolean iniciosesion(String user, String contra) throws IOException{
         while(registro.getFilePointer() < registro.length()){
            registro.readUTF();
            registro.readChar();
            String usuario=registro.readUTF();
            String password=registro.readUTF();
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if(user.equals(usuario.trim()) && contra.equals(password.trim())){
                userlog=user;
                return true;
            }
                 
         }
           
         return false;
     }
  
    public void fotoperfil(String user, File fotoperfil) throws IOException {
    // Obtener la ruta de la foto
    String rutafoto = fotoperfil.getAbsolutePath();

    // Crear un archivo separado para las fotos de perfil
    String rutaArchivoFoto = "Usertwit/" + user + "/foto.twc";
    try (RandomAccessFile archivoFotoPerfil = new RandomAccessFile(rutaArchivoFoto, "rw")) {
        archivoFotoPerfil.writeUTF(rutafoto);
    }
}
    public String enviarfoto(String user) throws IOException{
      String rutaArchivoFoto = "Usertwit/" + user + "/foto.twc";  
      RandomAccessFile archivoFotoPerfil = new RandomAccessFile(rutaArchivoFoto, "rw");
       String foto= archivoFotoPerfil.readUTF();
       return foto;
    }

    public String getUserlog() {
        return userlog;
    }
    public boolean desaccuenta()throws IOException{
       while(registro.getFilePointer() < registro.length()){
            registro.readUTF();
            registro.readChar();
            String usuario=registro.readUTF();
            registro.readUTF();
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if(usuario.equals(userlog)){
                registro.writeBoolean(false);
                return true;
            }
                 
         } 
       return false;
    }
    
    
    public String[] obtenerUsuarios() throws IOException{
        ArrayList<String> usuarios = new ArrayList<>();
        registro.seek(0);

        while (registro.getFilePointer() < registro.length()){
            registro.readUTF(); 
            registro.readChar(); 
            String usuario = registro.readUTF(); 
            registro.readUTF(); 
            registro.readInt(); 
            registro.readUTF(); 
            registro.readBoolean(); 
            usuarios.add(usuario);
        }
        String[] usuariosArray = new String[usuarios.size()];
        usuariosArray = usuarios.toArray(usuariosArray);
        return usuariosArray;
    }

   
// public long buscarUsuario(String user) throws IOException {
//  // registro.seek(0);
//    while (registro.getFilePointer() < registro.length()) {
//        long posActual = registro.getFilePointer();
//        registro.readUTF();
//        registro.readChar();
//        String usuario = registro.readUTF();
//        registro.readUTF();
//        registro.readInt();
//        registro.readUTF();
//        registro.readBoolean();
//        if (usuario.equals(user)) {
//            return posActual;
//        }
//    }
//    return -1; // Usuario no encontrado
//}
//
//    
//
//public ImageIcon obtenerFotoPerfil(String user) throws IOException {
//    long posUsuario = buscarUsuario(user);
//
//    if (posUsuario != -1) {
//        registro.seek(posUsuario);
//        registro.readUTF();
//        registro.readChar();
//        registro.readUTF();
//        registro.readUTF();
//        registro.readInt();
//
//        // Leer la ruta de la foto del usuario
//        String rutaFoto = registro.readUTF();
//
//        // Crear un ImageIcon a partir de la ruta de la foto
//        ImageIcon fotoIcon = new ImageIcon(rutaFoto);
//
//        return fotoIcon;
//    } else {
//        System.out.println("El usuario no existe");
//        return null;
//    }
//}
}
