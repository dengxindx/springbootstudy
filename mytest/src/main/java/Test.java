public class Test {
    public static void main(String[] args) {
//        SysData.values();
//        SysData he = SysData.valueOf("he");
        ClassLoader loader = Test.class.getClassLoader();
        while (loader != null){
            System.out.println(loader);
            loader = loader.getParent();
        }
    }
}

enum SysData{
    taobao("淘宝", "1"),
    taobao_app("淘宝app", "2");

    String name;
    String type;

    SysData(String name, String type){
        this.name = name;
        this.type = type;
    }
}