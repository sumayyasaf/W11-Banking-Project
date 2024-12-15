
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRW {

    public static void writeUsersToFile(List<User> users, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(users);// Serialize the object
           // out.flush();
            System.out.println("Object written to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<User> readUsersFromFile(String filename) {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
             users = (List<User>) in.readObject();// Serialize the object
            System.out.println("Object Read from file: " + filename);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;


    }

}



