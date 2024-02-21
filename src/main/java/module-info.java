module YourModuleName {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.lode_dub.sample to javafx.graphics;
    exports com.example.lode_dub.sample;
}

/*
module YourModuleName {
    requires javafx.controls;
    requires javafx.fxml;

    opens main.java.sample to javafx.graphics;
    exports com.example.lode_dub;
}*/

/*
module com.example.lode_dub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lode_dub to javafx.fxml;
    exports com.example.lode_dub;
}*/

