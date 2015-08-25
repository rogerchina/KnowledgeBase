package com.debuglife.codelabs.jaxb;


public class MainRun {
    public static void main(String[] args) {

        ClassB classB = new ClassB();
        classB.setClassBId(22);
        classB.setClassBName("B2");

        ClassA classA = new ClassA();
        classA.setClassAId(11);
        classA.setClassAName("A1");
        classA.setClassB(classB);

        System.out.println(XmlUtil.toXML(classA));
    }

}
