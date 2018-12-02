/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.AllStores;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class UserViewController implements Initializable {

    @FXML
    Button itemB;
    @FXML
    Button logoutB;
    @FXML
    ChoiceBox storeCB;

    Stage stage;
    Scene scene;

    private AllStores stores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initialize(AllStores stores) {
        this.stores = stores;

        this.storeCB.getItems().clear();
        String s = "";
        for (int i = 0; i < this.stores.getAllstores().size(); i++) {
            s = this.stores.getAllstores().get(i).getName();
            this.storeCB.getItems().add(s);
        }
    }

    public void logout(ActionEvent event) {
        if (stage != null) {
            stage.close();
        }

        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public void clickOnItems() throws IOException {

        int index = this.storeCB.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            goNext(index);
        } else {
            System.out.println("SELECT ANY STORE FIRST");
        }
    }

    public void goNext(int i) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserOrder.fxml"));
        Parent root = (Pane) loader.load();
        loader.<UserOrderController>getController().initialize(this.stores.getAllstores().get(i));
        scene = new Scene(root, 600, 600);
        stage = new Stage();

        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
