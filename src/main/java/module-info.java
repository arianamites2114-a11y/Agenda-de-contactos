module ec.edu.uce.agenda_contactos {
    requires javafx.controls;
    requires javafx.fxml;


    opens ec.edu.uce.agenda_contactos to javafx.fxml;
    exports ec.edu.uce.agenda_contactos;
}