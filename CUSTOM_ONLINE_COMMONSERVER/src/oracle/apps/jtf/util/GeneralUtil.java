/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.jtf.util;

import java.sql.SQLException;
import java.util.Hashtable;
import java.sql.Connection;
import oracle.sql.ArrayDescriptor;

public class GeneralUtil {

    private static Hashtable connToHt = new Hashtable();

    public static ArrayDescriptor getArrayDescriptor(String paramString, Connection paramOracleConnection) {
        Hashtable localHashtable = (Hashtable) connToHt.get(paramOracleConnection);
        if (localHashtable == null) {
            localHashtable = new Hashtable();
            connToHt.put(paramOracleConnection, localHashtable);
        }

        ArrayDescriptor localArrayDescriptor = (ArrayDescriptor) localHashtable.get(paramString);
        if (localArrayDescriptor == null) {
            try {
                localArrayDescriptor = ArrayDescriptor.createDescriptor(paramString, paramOracleConnection);
            } catch (SQLException localSQLException) {
            }
            localHashtable.put(paramString, localArrayDescriptor);
        }
        return localArrayDescriptor;
    }
}
