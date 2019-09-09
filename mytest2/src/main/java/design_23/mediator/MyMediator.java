package design_23.mediator;

/**
 * 中介者，维护与之有关系的类，用于解耦依赖类
 */
public class MyMediator implements Mediator{

    private User user1;
    private User user2;

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    @Override
    public void createMediator() {
        user1 = new User1(this);
        user2 = new User2(this);
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();
    }
}
