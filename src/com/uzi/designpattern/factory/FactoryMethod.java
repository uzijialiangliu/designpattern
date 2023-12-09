package com.uzi.designpattern.factory;

public class FactoryMethod {
    public static void main(String[] args) {
        Application application = new ConcreteProductA();
        Product product = application.getObject();
        product.method1();
    }
}

interface Product {
    void method1();
}

class ProductA implements Product {

    public void method1() {
        System.out.println("ProjectA.method1 executed. ");
    }

}

class ProductA1 implements Product {

    public void method1() {
        System.out.println("ProjectA1.method1 executed. ");
    }

}

class SimpleFactory {
    public static Product createProduct(String type) {
        if ("0".equals(type)) {
            return new ProductA();
        } else if ("1".equals(type)) {
            return new ProductA1();
        } else {
            return null;
        }
    }
}

abstract class Application {

    abstract Product createProduct();

    Product getObject() {
        Product product = createProduct();
        // ...
        // ...
        return product;
    }

}

class ConcreteProductA extends Application {

    @Override
    Product createProduct() {
        // ....
        return new ProductA();
    }
}

class ConcreteProductA1 extends Application {

    @Override
    Product createProduct() {
        // ....
        return new ProductA1();
    }
}
