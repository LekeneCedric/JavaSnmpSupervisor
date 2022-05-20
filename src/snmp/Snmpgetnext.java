/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snmp;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

public class Snmpgetnext {
    Snmp snmp = null;
    String address = null;
/**
25
* Constructor
26
* @param add
27
*/
    public Snmpgetnext(String add){
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
        for(int i=1 ; i<=1 ; i++)
        {
            try
            {
                SNMPManager client = new SNMPManager("udp:192.168.43.46/161");
                SNMPManager client2 = new SNMPManager("192.168.43.46/161");
                client2.start();
                client.start();
                String Oid = ".1.3.6.1.4.1.2021.4 ";
                int n = 1;
                for(int j = 0; j<n; j++){

                    String sysDescr1 = client2.getAsString(new OID(".1.3.6.1.4.1.2021.4 "));

                    System.out.println(sysDescr1);
                }
            }
            catch (Exception e)
            {
                continue;
            }

        }

/**
 * System Time instance : 1.3.6.1.2.1.1.3.0
41
* OID - .1.3.6.1.2.1.1.1.0 => SysDec
42
* OID - .1.3.6.1.2.1.1.5.0 => SysName
43
* => MIB explorer will be usefull here, as discussed in previous article
44
*/

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
    private void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
// Do not forget this line!
        transport.listen();
    }
    public void setOID(String oid, int val){
        oid = ".1.3.6.1.4.1.2021.4 '"+val+"'.0";
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
        target.setVersion(SnmpConstants.version2c);
        return target;
}
}
