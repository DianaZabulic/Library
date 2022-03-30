package presenter;

import model.PersistenceUser;
import model.User;
import model.UserType;
import view.IAdminView;

import javax.xml.bind.JAXBException;

public class AdminPresenter {
    IAdminView iAdminView;
    PersistenceUser persistenceUser;

    public AdminPresenter(view.IAdminView IAdminView) {
        this.iAdminView = IAdminView;
        persistenceUser = new PersistenceUser();
    }

    public void addUser() {
        UserType userType = null;
        if(iAdminView.getTypeField().equals("administrator")){
            userType = UserType.ADMINISTRATOR;
        }
        if(iAdminView.getTypeField().equals("bibliotecar")) {
            userType = UserType.LIBRARIAN;
        }
        persistenceUser.addUser(new User(Integer.parseInt(iAdminView.getCNPField()), iAdminView.getNameField(),
                iAdminView.getUsernameField(), iAdminView.getPasswordField(), userType));
    }

    public void editUser() throws JAXBException {
        UserType userType = null;
        if(iAdminView.getTypeField().equals("administrator")){
            userType = UserType.ADMINISTRATOR;
        }
        if(iAdminView.getTypeField().equals("bibliotecar")) {
            userType = UserType.LIBRARIAN;
        }
        persistenceUser.updateUser(new User(Integer.parseInt(iAdminView.getCNPField()), iAdminView.getNameField(),
                iAdminView.getUsernameField(), iAdminView.getPasswordField(), userType));
    }

    public void deleteUser() throws JAXBException {
        persistenceUser.deleteUser(Integer.parseInt(iAdminView.getCNPField()));
    }

}
