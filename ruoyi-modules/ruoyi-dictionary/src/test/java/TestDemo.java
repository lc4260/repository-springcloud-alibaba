import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuchun
 * @Package PACKAGE_NAME
 * @date 2021/7/5 17:05
 * description:
 */
public class TestDemo {
    public static void main(String [] args){
        UserTest userTest1 = new UserTest();

        UserTest userTest2 = (UserTest) userTest1.clone();

        System.out.println("userTest1"+userTest1);
        System.out.println("userTest2"+userTest2);
        userTest1.setUsername("admin");
        userTest1.setPassword("admin");
        System.out.println("userTest1"+userTest1);
        System.out.println("userTest2"+userTest2);


    }
}
