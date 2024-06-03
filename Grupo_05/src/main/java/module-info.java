module principal {
    requires javafx.controls;
    requires javafx.fxml;

    opens principal to javafx.fxml;
    exports principal;
}
