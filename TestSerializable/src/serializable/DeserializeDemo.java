package serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializeDemo {
    
    public static void main(String[] args) {
        User user = null;
        
        try {
            FileInputStream fileInputStream = new FileInputStream("E:/user.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            user = (User) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
            
            System.out.println("Deserialize User : ");
            System.out.println("id : " + user.getId());
            System.out.println("name : " + user.getName());
            System.out.println("age : " + user.getAge());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
