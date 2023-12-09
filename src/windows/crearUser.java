/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package windows;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ferna
 */
public class crearUser {
   
    private static File ROOT_DIRECTORY;
    public RandomAccessFile r_users;
    public crearUser() {
    try{    
    ROOT_DIRECTORY = new File("Z");
    ROOT_DIRECTORY.mkdir();
    r_users= new RandomAccessFile("Z/r_users.ur","rw");
    }catch(IOException e){
        e.printStackTrace();
    }

   
}
   

public void createUser(String name,String password){
    String[] info= new String[3];
    info[0]="Mis Documentos";
    info[1]="Mis Imagenes";
    info[2]="Musica";
    
    
    if(checkUniqueUser(name,password)){
        try{
         r_users.seek(r_users.length());
         r_users.writeUTF(name);
         r_users.writeUTF(password);
     }catch(IOException e){
         e.printStackTrace();
     }
    for(int i=0;i<info.length;i++){
     File file= new File(ROOT_DIRECTORY.getPath()+"/usuarios/"+name+"/"+info[i]);
     if(!file.exists()){
        file.mkdirs();
     
     }}
    
    
    }
    
}
public boolean checkUniqueUser(String username, String password){
    if(searchDir(username,"Z/usuarios")==null){
       
       return true;
       }
    return false;
}
public String userList(){
    String userList="";
    File file= new File("Z");
    if(file.exists()&&searchDir("usuarios",file.getPath()).exists()){
   File[] users= searchDir("usuarios",file.getPath()).listFiles();
   for(File user:users){
       userList+=user.getName()+"\n";
   }
   return userList;}
      return userList;  
    }

public static File searchDir(String nombre, String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().equals(nombre)) {
                        return f;
                    } else if (f.isDirectory()) {
                        // Recursive call and return the result
                        File result = searchDir(nombre, f.getPath());
                        if (result != null) {
                            return result; // Return the result if found in the subdirectory
                        }
                    }
                }
            }
        }
        return null;
    }
public String getuserPassword(String user)throws IOException{
    r_users.seek(0);
    try {
        while (true) {
            String username = r_users.readUTF();
            String password = r_users.readUTF();
            System.out.println(password);
            if (username.equals(user)) {
                return password;
            }
        }
    } catch (EOFException e) {
        // End of file reached
        return null;
    }
}
    
}
    
    
    

