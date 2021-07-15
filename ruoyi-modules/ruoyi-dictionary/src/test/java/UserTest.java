import lombok.Data;

/**
 * @author liuchun
 * @Package PACKAGE_NAME
 * @date 2021/7/8 17:06
 * description:
 */
@Data
public class UserTest implements Cloneable{
    private String username;
    private String password;
    private int age;
    private int sex;
    public int a;
    UserTest(){

    }
    UserTest(String username,String password){
        this.username = username;
        this.password = password;
    }
    public Object clone(){
        UserTest userTest = null;
        try{
            userTest = (UserTest)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return userTest;
    }

}
