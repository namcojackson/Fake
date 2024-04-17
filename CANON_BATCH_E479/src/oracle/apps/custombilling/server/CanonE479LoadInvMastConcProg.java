package oracle.apps.custombilling.server;

import static canon.batch.server.AutosysConcurrentProgram.cpContext;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import oracle.apps.fnd.cp.request.CpContext;
import oracle.apps.fnd.cp.request.JavaConcurrentProgram;
import oracle.apps.fnd.util.NameValueType;
import oracle.apps.fnd.util.ParameterList;

public class CanonE479LoadInvMastConcProg  implements JavaConcurrentProgram {

	@Override
	public void runProgram(CpContext ctx) {
        Map params = toMap(ctx.getParameterList());
        System.out.println("request paramaters: " + params);
        String p_parent_customer = (String) params.get("parent_customer");
        String p_customer_group = (String) params.get("customer_group");
        String p_customer = (String) params.get("customer");
        String p_bill_to_location = (String) params.get("bill_to_location");
        String p_from_date = (String) params.get("from_date");
        String p_to_date = (String) params.get("to_date");
        CanonE479LoadInvMastDAO.loadInvMast(
    			p_customer_group, 
    			p_parent_customer, 
    			p_customer, 
    			p_bill_to_location, 
    			toTimestamp(p_from_date), 
    			toTimestamp(p_to_date));
	}
	
    static Map toMap(ParameterList pl) {
        Map m = new HashMap();
        while (pl.hasMoreElements()) {
            NameValueType e = (NameValueType) pl.nextElement();
            m.put(e.getName(), e.getValue());
        }
        return m;
    }
	
    static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    public static Timestamp toTimestamp(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    
    public static void main(String args[]) throws Exception {
		String parameters =args.length>0? args[0] : null; // program parameter pass to
		// concurrent program, example
		// customer_group=NA:parent_customer=Canon:bill_to_location=0:from_date=10-01-2015:to_date=11-01-2015
    	JavaConcurrentProgram c=new CanonE479LoadInvMastConcProg();
    	c.runProgram(cpContext());
    }
    

}
