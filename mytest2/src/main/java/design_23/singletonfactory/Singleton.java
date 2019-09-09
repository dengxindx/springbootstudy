package design_23.singletonfactory;

/**
 * 单例设计模式
 */
public class Singleton {
    // 懒汉模式(还有饿汉式，直接实例化)
    private static volatile Singleton instance = null;

    // 构造代码私有化，防止对象实例化
    private Singleton(){
    }

    /* 静态工程方法，创建实例 */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return instance;
    }

}
