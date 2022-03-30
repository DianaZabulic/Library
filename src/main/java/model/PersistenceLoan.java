package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persistenceLoan")
public class PersistenceLoan {
    private final File fileName = new File("imprumuturi.xml");
    @XmlElement(name = "loansList")
    private List<Loan> loans;

    public File getFileName() {
        return fileName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Loan> readXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PersistenceLoan.class);
        PersistenceLoan persistenceLoan = (PersistenceLoan) context.createUnmarshaller().unmarshal(fileName);
        return persistenceLoan.getLoans();
    }

    public void writeXML(List<Loan> loans) {
        try {
            this.loans = loans;
            JAXBContext context = JAXBContext.newInstance(PersistenceLoan.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, fileName);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public boolean borrowBook(int bookID, int subscriberID) throws JAXBException {
        List<Loan> loans = readXML();
        loans.add(new Loan(true, bookID, subscriberID));
        writeXML(loans);
        return true;
    }

    public boolean returnBook(int bookID, int subscriberID) throws JAXBException {
        List<Loan> loans = readXML();
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getBookID() == bookID && loan.getSubscriberID() == subscriberID) {
                loan.setActive(false);
                writeXML(loans);
                return true;
            }
        }
        return false;
    }
}
