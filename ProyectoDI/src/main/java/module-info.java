module com.example.proyectodi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires javahelp;


    opens com.example.proyectodi to javafx.fxml;
    exports com.example.proyectodi;
}