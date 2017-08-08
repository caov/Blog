package serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializeDemo {
    
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("van");
        user.setAge(18);
        
        try {
            FileOutputStream fileOut = new FileOutputStream("E:/user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in E:/user.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
