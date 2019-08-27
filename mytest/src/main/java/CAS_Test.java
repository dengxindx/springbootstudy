import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CAS_Test {

    // CAS处理，不解决ABA问题
    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    // CAS处理，带版本号处理ABA问题
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread thread_atomicInteger_1 = new Thread(() ->{
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        });

        Thread thread_atomicInteger_2 = new Thread(() ->{
            // 等待thread_atomicInteger_1线程完成再进行CAS， 保证ABA问题的出现
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicInteger.compareAndSet(100, 101));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread_atomicInteger_1.start();
        thread_atomicInteger_2.start();
        thread_atomicInteger_1.join();
        thread_atomicInteger_2.join();

        // 分割线
        System.out.println("====================================================");
        System.out.println("获取版本号：" + atomicStampedReference.getStamp());

        Thread thread_atomicStampedReference_1 = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("线程1----版本号开始：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("线程1----版本号结束：" + atomicStampedReference.getStamp());
        });

        Thread thread_atomicStampedReference_2 = new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("线程2----版本号开始：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1));
            System.out.println("线程2----版本号结束：" + atomicStampedReference.getStamp());
        });

        thread_atomicStampedReference_1.start();
        thread_atomicStampedReference_2.start();
    }
}
