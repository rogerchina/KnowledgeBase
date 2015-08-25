package com.debuglife.codelabs.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="com.debuglife.codelabs.jaxb")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassB {
    private int classBId;
    private String classBName;

    public int getClassBId() {
        return classBId;
    }

    public void setClassBId(int classBId) {
        this.classBId = classBId;
    }

    public String getClassBName() {
        return classBName;
    }

    public void setClassBName(String classBName) {
        this.classBName = classBName;
    }

}
