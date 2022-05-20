/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp;
import java.io.IOException;
import java.util.*;
import org.snmp4j.*;
import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
public class SNMPManager {
    Snmp snmp = null;
    String address = null;
/**
25
* Constructor
26
* @param add
27
*/
    public SNMPManager(String add){
        address = add;
    }
    public static void main(String[] args) throws IOException {
/**
35
* Port 161 is used for Read and Other operations
36
* Port 162 is used for the trap generation
37
*/
        SNMPManager client = new SNMPManager("udp:192.168.173.65/161");
        client.start();
/**
41
* OID - .1.3.6.1.2.1.1.1.0 => SysDec
42
* OID - .1.3.6.1.2.1.1.5.0 => SysName
43
* => MIB explorer will be usefull here, as discussed in previous article
44
*/
        String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
        System.out.println(sysDescr);
    }
/**
50
* Start the Snmp session. If you forget the listen() method you will not
51
* get any answers because the communication is asynchronous
52
* and the listen() method listens for answers.
53
* @throws IOException
54
*/
    void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
// Do not forget this line!
        transport.listen();
    }
/**
63
* Method which takes a single OID and returns the response from the agent as a String.
64
* @param oid
65
* @return
66
* @throws IOException
67
*/
    public String getAsString(OID oid) throws IOException {
        ResponseEvent event = get(new OID[] { oid });
        return event.getResponse().get(0).getVariable().toString();
    }
/**
74
* This method is capable of handling multiple OIDs
75
* @param oids
76
* @return
77
* @throws IOException
78
*/
    public ResponseEvent get(OID oids[]) throws IOException {
        PDU pdu = new PDU();
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, getTarget(), null);
        if(event != null) {
        return event;
        }
        throw new RuntimeException("GET timed out");
    }
/**
93
* This method returns a Target, which contains information about
94
* where the data should be fetched and how.
95
* @return
96
*/
    private Target getTarget() {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version1);
        return target;
}
}
