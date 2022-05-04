module edu.miracosta.cs112.amurphy.ic14_tipcalculatorfxreal {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.amurphy.ic14_tipcalculatorfxreal to javafx.fxml;
    exports edu.miracosta.cs112.amurphy.ic14_tipcalculatorfxreal;
}