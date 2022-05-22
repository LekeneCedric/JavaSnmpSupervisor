package com.example.supervisionnetworkInterface;

import com.example.supervisionNetwork.SnmpUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RParAdd  implements Initializable {

    @FXML
    private TextField addressIP;
    @FXML
    private  TableView <Result> hostInfo = new TableView<>();
    @FXML
    private  TableColumn<Result, String> colinfo ;

   @FXML
    private  TableColumn <Result , String> coldetail ;
    ObservableList<Result> Patientlist = FXCollections.observableArrayList();

    public void scanparaddress(ActionEvent actionEvent) {
        String ip = addressIP.getText();
        String community = "public";
        List<String> oidList = new ArrayList<String>();
        oidList.add(".1.3.6.1.2.1.1.1.0");
        oidList.add(".1.3.6.1.2.1.1.3.0");
        oidList.add(".1.3.6.1.2.1.1.5.0");
       // ObservableList<Result> list =
        getDetailInfo(ip, community, oidList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadform();
    }

    public  void getDetailInfo(String ip, String community,
                                     List<String> oidList)
    {
        Patientlist.clear();
        CommunityTarget target = SnmpUtil.createDefault(ip, community);
        Result result[] = new Result[3];
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for (String oid : oidList) {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            snmp = new Snmp(transport);

            System.out.println("------->Start Scanning <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                System.out.println("response is null, request time out");
            } else {

                System.out.println("response pdu size is " + response.size());
                for (int i = 0; i < response.size(); i++) {

                    VariableBinding vb = response.get(i);
                    if(i==0)
                    {
                        Patientlist.add(new Result("OS / Machine / Kernel version",vb.getVariable().toString()));
                        hostInfo.setItems(Patientlist);
                    } else if (i==1) {
                        Patientlist.add(new Result("Duree d'activite",vb.getVariable().toString()));
                        hostInfo.setItems(Patientlist);
                    } else if (i==2) {
                        Patientlist.add(new Result("Nom Machine",vb.getVariable().toString()));
                        hostInfo.setItems(Patientlist);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP GetList Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
//        ObservableList<Result> list = FXCollections.observableArrayList(result[0],result[1],result[2]);
//        System.out.println(result[2]);
//      return list ;
    }

    private  void loadform()
    {
        colinfo.setCellValueFactory(new PropertyValueFactory<>("oid"));
        coldetail.setCellValueFactory(new PropertyValueFactory<>("variable"));
    }
}