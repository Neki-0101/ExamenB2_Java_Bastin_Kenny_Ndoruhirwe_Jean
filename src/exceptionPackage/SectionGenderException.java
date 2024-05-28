package exceptionPackage;

import java.sql.SQLException;

public class SectionGenderException extends Throwable {
    public SectionGenderException(String s) {
        super(s);
    }
}
