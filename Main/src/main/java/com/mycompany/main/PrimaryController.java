package com.mycompany.main;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToUserLogin() throws IOException {
        App.setRoot("user_login");
    }
    @FXML
    private void switchToAdminLogin() throws IOException {
        App.setRoot("admin_login");
    }
}
