package com.example.supervisionnetworkInterface;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

public class Result {
    private String oid ;
    private String variable;
    public Result(String oid,String variable)
    {
        this.oid = oid;
        this.variable=variable;
    }
    public String getOid()
    {
        return this.oid;
    }
    public  String getVariable()
    {
        return this.variable;
    }

}
