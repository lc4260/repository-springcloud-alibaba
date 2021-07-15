package pattern;

/**
 * @author liuchun
 * @Package pattern
 * @date 2021/7/13 0:39
 * description:抽象工厂的产品类，实现抽象工厂，然后重写生产方法，编写具体的生产逻辑
 */
public class FactoryForA implements AbstractFactoryPattern {
    @Override
    public Food get() {
        return new ProductA();
    }
}
