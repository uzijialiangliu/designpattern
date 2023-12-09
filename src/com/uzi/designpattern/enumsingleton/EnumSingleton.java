package com.uzi.designpattern.enumsingleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingleton {
    INSTANCE;

    public void print() {
        System.out.println(this.hashCode());
    }
}

class EnumTest {
    public static void main(String[] args) throws Exception {

//        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class, Integer.class);
//        declaredConstructor.setAccessible(true);
//        EnumSingleton instance = declaredConstructor.newInstance("INSTANCE", 0);

        EnumSingleton instance = EnumSingleton.INSTANCE;

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("enumSingleton"));
//        oos.writeObject(instance);
//        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("enumSingleton"));
        EnumSingleton object = (EnumSingleton) ois.readObject();
        ois.close();
        System.out.println(instance == object);

    }
}
