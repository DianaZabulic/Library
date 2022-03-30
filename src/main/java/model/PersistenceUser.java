package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persistenceUser")
public class PersistenceUser {
    File fileName = new File("utilizatori.xml");
    @XmlElement(name = "usersList")
    List<User> users;

    public List<User> readXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PersistenceUser.class);
        PersistenceUser persistenceUser = (PersistenceUser) context.createUnmarshaller().unmarshal(fileName);
        return persistenceUser.users;
    }

    public void writeXML(List<User> users) {
        try {
            this.users = users;
            JAXBContext context = JAXBContext.newInstance(PersistenceUser.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, fileName);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        try {
            users = readXML();
            users.add(user);
            writeXML(users);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int CNP) throws JAXBException {
        List<User> users = readXML();
        users.removeIf(user -> user.getCNP() == CNP);
        writeXML(users);
        return true;
    }

    public boolean updateUser(User user) throws JAXBException {
        deleteUser(user.getCNP());
        addUser(user);
        return true;
    }

    public User validateUser(String username, String password) throws JAXBException {
        List<User> users = readXML();
        for (User user : users) {
            if (user.getUsername() != null && user.getPassword() != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
