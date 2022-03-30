package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "usertype")
@XmlEnum
public enum UserType {
    LIBRARIAN, ADMINISTRATOR;
}
