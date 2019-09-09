package design_23.mediator;

public abstract class User {
    // 用于维护具有依赖关系的类
    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    // 具体的工作类需要操作的方法
    public abstract void work();
}
