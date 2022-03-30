package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "loan")
public class Loan {
    @XmlElement(required = true)
    private boolean active;
    @XmlElement(name = "bookID", required = true)
    private int bookID;
    @XmlElement(name = "subscriberID", required = true)
    private int subscriberID;

    public Loan(){}

    public Loan(boolean active, int bookID, int subscriberID) {
        this.active = active;
        this.bookID = bookID;
        this.subscriberID = subscriberID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(int subscriberID) {
        this.subscriberID = subscriberID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
