/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.jtf.aom.transaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Q05058
 */
public class TransactionUtil {
    
    public static String getArgs(Object[] args, int many){
        String a="";
        for(int i=0;args!=null && i<Math.min(many,args.length);i++){
            a=a+ " "+args[i];
        }
        if(args!=null && many<args.length){
            a=a+" ...";
        }
        return a;
    }
    
    public static String getArgs(Object[] args){
        return getArgs(args,Integer.MAX_VALUE);
    }

    public static void enableDbmsOutput(Connection c){
        // turn on support for dbms_output
        try {    
            CallableStatement cstmt = c.prepareCall("{call dbms_output.enable(32000) }");
            cstmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void printDbmsOutput(Connection c){
        // retrieve the messages written with dbms_output
        try {    
            CallableStatement cstmt = c.prepareCall("{call dbms_output.get_line(?,?)}");
            cstmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(2,java.sql.Types.NUMERIC);

            int status = 0;
            while (status == 0)
            {
                cstmt.execute();
                String line = cstmt.getString(1);
                status = cstmt.getInt(2);
                if (line != null && status == 0)
                {
                    System.out.println("dbms_output " + line);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
    }
    
    
}
