module proggroup.advprogmt {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
    requires com.jfoenix;

    opens proggroup.advprogmt to javafx.fxml;
    exports proggroup.advprogmt;
}