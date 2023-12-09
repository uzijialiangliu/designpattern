package com.uzi.designpattern.prototype;

import java.io.*;

public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        BaseInfo baseInfo = new BaseInfo("xxx");

        Product product = new Product("part1", "part2", 3, 4, baseInfo);

        Product clone = product.clone();
        System.out.println("original: " + product);
        System.out.println("clone: " + clone);

        product.getBaseInfo().setCompanyName("yyyy");
        System.out.println("original: " + product);
        System.out.println("clone: " + clone);
    }

}

class BaseInfo implements Cloneable, Serializable {

    private static final long serialVersionUID = 42L;

    private String companyName;

    public BaseInfo(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return super.hashCode() + " BaseInfo{" +
                "companyName='" + companyName + '\'' +
                '}';
    }

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return (BaseInfo) super.clone();
    }
}

class Product implements Cloneable, Serializable {
    private static final long serialVersionUID = 42L;
    private String part1;
    private String part2;
    private Integer part3;
    private Integer part4;
    private Integer part5;
    private BaseInfo baseInfo;

    public Product(){}

    public Product(String part1, String part2, Integer part3, Integer part4, BaseInfo baseInfo) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
        this.baseInfo = baseInfo;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public Integer getPart3() {
        return part3;
    }

    public void setPart3(Integer part3) {
        this.part3 = part3;
    }

    public Integer getPart4() {
        return part4;
    }

    public void setPart4(Integer part4) {
        this.part4 = part4;
    }

    public Integer getPart5() {
        return part5;
    }

    public void setPart5(Integer part5) {
        this.part5 = part5;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    @Override
    public String toString() {
        return super.hashCode() + " Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3=" + part3 +
                ", part4=" + part4 +
                ", part5=" + part5 +
                ", baseInfo=" + baseInfo +
                '}';
    }

    @Override
    protected Product clone() throws CloneNotSupportedException {
        // v1
//        Product clone = (Product) super.clone();
//        BaseInfo clone1 = this.baseInfo.clone();
//        clone.setBaseInfo(clone1);
//        return clone;

        // v2
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try(ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream)) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        try(ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream)) {
            return (Product) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return this;
    }
}
