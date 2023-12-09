package com.uzi.designpattern.innerclasssingleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class InnerClassSingletonTest {
    public static void main(String[] args) throws Exception {

//        Constructor<InnerClassSingleton> declaredConstructor = InnerClassSingleton.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        InnerClassSingleton innerClassSingleton = declaredConstructor.newInstance();
//
//        InnerClassSingleton instance = InnerClassSingleton.getInstance();
//        System.out.println(innerClassSingleton == instance);

        InnerClassSingleton instance = InnerClassSingleton.getInstance();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testSerializable"));
        oos.writeObject(instance);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testSerializable"));
        InnerClassSingleton object = (InnerClassSingleton) ois.readObject();
        ois.close();
        System.out.println(instance == object);

    }
}

class InnerClassSingleton implements Serializable {

    static final long serialVersionUID = 42L;

    private static class InnerClassHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
        if (InnerClassHolder.instance != null) {
            throw new RuntimeException("单例不允许多个实例");
        }
    }

    public static InnerClassSingleton getInstance() {
        return InnerClassHolder.instance;
    }

    @Serial
    Object readResolve() throws ObjectStreamException {
        return InnerClassHolder.instance;
    }

}
