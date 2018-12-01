/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 *
 * @author PD
 */
public class SaveData {
    
    public static User_Login_Database readUserDatabase(User_Login_Database database){
        
        String path = "user.ap";
        ObjectInputStream in = null;
        
        try {
            in = new ObjectInputStream(new FileInputStream(path));
            database = (User_Login_Database) in.readObject();
            System.out.println("DATA READ");
        } catch (FileNotFoundException ex) {
            System.out.println("File \"" + path + "\" not found.");
            database = new User_Login_Database();
        } catch (IOException ex) {
            System.out.println("IOException :- " + Arrays.toString(ex.getStackTrace()));
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException :- " + Arrays.toString(ex.getStackTrace()));
        }
        finally{
            if(in != null)
                try {
                    in.close();
            } catch (IOException ex) {
                System.out.println("IOException :- " + Arrays.toString(ex.getStackTrace()));
            }
        }
        
        return database;

    }
    
    public static User_Login_Database writeUserDatabase(User_Login_Database database){
        
        String path = "user.ap";
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(database);
            System.out.println("DATA WRITTEN");
        } catch (FileNotFoundException ex) {
            System.out.println("File \"" + path + "\" not found.");
        } catch (IOException ex) {
            System.out.println("IOException :- " + Arrays.toString(ex.getStackTrace()));
        }
        finally{
            
            if(out != null)
                try {
                    out.close();
            } catch (IOException ex) {
                System.out.println("IOException :- " + Arrays.toString(ex.getStackTrace()));
            }
        }
        return database;
    }
    
}
