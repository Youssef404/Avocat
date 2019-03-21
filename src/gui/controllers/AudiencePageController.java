package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import testjdbc.data.model.Audience;
import testjdbc.tools.TableAudienceUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AudiencePageController implements Initializable {

    @FXML
    TableView tAudience;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableAudienceUtil tAudienceUtil = new TableAudienceUtil();
        tAudience.getColumns().addAll(tAudienceUtil.getIdCol(),
                tAudienceUtil.getInstanceCol(),
                tAudienceUtil.getDateCol(),
                tAudienceUtil.getInstanceCol(),
                tAudienceUtil.getVerdictCol(),
                tAudienceUtil.getVilleCol());
    }
}
