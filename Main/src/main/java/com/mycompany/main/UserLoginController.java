package com.mycompany.main;

import java.io.IOException;
import javafx.fxml.FXML;

public class UserLoginController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}