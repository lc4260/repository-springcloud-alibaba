import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author liuchun
 * @Package PACKAGE_NAME
 * @date 2021/7/11 13:20
 * description:
 */

public class SpringBootTest {

    /**
     * 反射
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //反射获取类1    Class.forName(“全限类名”);
        Class<?> userTestClass = Class.forName("UserTest");
        System.out.println(userTestClass);
        //反射获取类2    类名.class;
        Class<UserTest> userTestClass2 = UserTest.class;
        System.out.println(userTestClass2);
        //反射获取类3    对象.getClass();
        UserTest userTest1 = new UserTest();
        Class<? extends UserTest> userTestClass3 = userTest1.getClass();
        System.out.println(userTestClass3);
        /**
         *
         */
        String name = userTestClass3.getName();
        System.out.println(name);
        Field[] fields = userTestClass3.getFields();
        System.out.println(fields);
        Method[] methods = userTestClass3.getMethods();
        System.out.println(methods);
        UserTest userTest2 = userTestClass3.newInstance();
        System.out.println(userTest2);
    }

    /**
     * 队列
     */
    @Test
    public void test2(){
        Deque<Integer> integerDeque = new LinkedList<>();
        //尾部入队，区别在于如果失败了
        // add方法会抛出一个IllegalStateException异常，而offer方法返回false
        integerDeque.offer(122);
        integerDeque.offer(123);
        System.out.println("offer:"+integerDeque);
        integerDeque.add(124);
        integerDeque.add(125);
        System.out.println("add:"+integerDeque);
        //头部出队，区别在于如果失败了
        // remove方法抛出一个NosuchElementException异常，而poll方法返回false
        int head=integerDeque.poll();//返回第一个元素，并在队列中删
        System.out.println("poll:"+head);
        head=integerDeque.remove();//返回第一个元素，并在队列中删除
        System.out.println("remove:"+head);
        //头部出队，区别在于如果失败了
        // element方法抛出一个NosuchElementException异常，而peek方法返回null。
        head =integerDeque.peek();//返回第一个元素，不删除
        System.out.println("peek:"+head);
        head=integerDeque.element();//返回第一个元素，不删除
        System.out.println("element:"+head);
    }

    /**
     * 栈
     */
    @Test
    public void test3(){
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(12);//尾部入栈
        stack.push(16);//尾部入栈
        int tail = stack.pop();//尾部出栈，并删除该元素
        System.out.println("pop:"+tail);
        System.out.println("pop:"+stack);
        tail = stack.peek();//尾部出栈，不删除该元素
        System.out.println("peek:"+tail);
        System.out.println("peek:"+stack);
    }

    /**
     * 二叉树
     */
    @Test
    public void test4(){
         class BST<Key extends Comparable<Key>,Value> {
             private Node root;
             class Node {
                 //排序的键
                 private Key key;
                 //相应的值
                 private Value value;
                 //左子树，右子树
                 private Node left, right;
                 //以该节点为根的树包含节点数量
                 private int size;
                 //初始化树，一般size初始值为1
                 public Node(Key key, Value value, int size) {
                     this.key = key;
                     this.value = value;
                     this.size = size;
                 }
             }

             public BST() {
             }
             public int size() {
                 //获得该二叉树节点数量
                 return size(root);
             }

             //获得以该节点为根的树包含节点数量
             private int size(Node x) {
                 return  x == null ? 0 : x.size;
             }

             /**
              * 查找方法
              * @param key
              * @return
              */
             //对外暴露的get方法
             public Value get(Key key){
                 System.out.println(root==null);
                 System.out.println("要查找的key： "+key);
                 return get(root,key);
             }
             private Value get(Node x,Key key){
                 if(x == null){
                     System.out.println("没有节点？"+key);
                     return null;
                 }
                 int cmp = key.compareTo(x.key);
                 if(cmp < 0){
                     return get(x.left,x.key);
                 }else if(cmp > 0){
                     return get(x.right,x.key);
                 }else {
                     return x.value;
                 }
             }
             /**
              * 插入方法
              */
             //提供给外界使用的插入方法
             public void put(Key key,Value value){
                 //调动下方的put方法
                 root = put(root,key,value);

             }
             //执行插入的代码
             private Node put(Node x,Key key,Value value){
                 if(x == null){
                     //如果此时还未有树，则创建一个,注意：包括根节点树和左右子树
                    return new Node(key,value,1);
                 }
                 //和上级节点进行比较
                 int cmp = key.compareTo(x.key);
                 System.out.println("key:" + key + "    cmp:" + cmp);
                 if (cmp < 0){
                     x.left = put(x.left, key, value);
                 }else if(cmp > 0){
                     x.right = put(x.right, key, value);
                 }else {
                     x.value = value;
                 }
                 x.size = 1 + size(x.left) + size(x.right);
                 return x;
             }
        }
        //测试

        BST bst = new BST<>();
        bst.put(5,5);
        bst.put(3,3);
        bst.put(4,4);
        bst.put(10,10);
        System.out.println(bst.size());
        System.out.println(bst.get(4));

    }

    @Test
    public void test5(){
        // byte
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println("默认值：0");
        // short
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println("默认值：0");
        // int
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println("默认值：0");
        // long
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println("默认值：0L");
        // float
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println("默认值：0.0f");
        // double
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println("默认值：0.0d");
        // char
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE="
                + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE="
                + (int) Character.MAX_VALUE);
        System.out.println("默认值：0");
        // boolean
        System.out.println("基本类型：boolean 二进制位数：1");
        System.out.println("默认值：false");



    }
}
