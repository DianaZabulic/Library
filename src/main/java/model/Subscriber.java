package model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "subscriber")
public class Subscriber {
    @XmlElement(name = "cnp", required = true)
    private int CNP;
    @XmlElement(required = true)
    private String name;

    public Subscriber() {
    }

    public Subscriber(int ID, String name) {
        this.CNP = ID;
        this.name = name;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int ID) {
        this.CNP = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "CNP=" + CNP +
                ", name='" + name + '\'' +
                '}';
    }
}
