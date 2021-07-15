package pattern;

/**
 * @author liuchun
 * @Package pattern
 * @date 2021/7/13 0:30
 * description: 工厂模式
 * 调用者只需知道产品的名称即可
 */
public class FactoryMethod {
    //对外暴露的生产方法
    public ProductA getA(){
        return new ProductA();
    }
    public ProductB getB(){
        return new ProductB();
    }
    public ProductC getC(){
        return new ProductC();
    }
}
