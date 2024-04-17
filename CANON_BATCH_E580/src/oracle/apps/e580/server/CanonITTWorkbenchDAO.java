package oracle.apps.e580.server;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import business.parts.NPZC104001PMsg;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

public class CanonITTWorkbenchDAO {

    public static final String DERIVE_PO_CANCEL_LINES= "{call CANON_E580_ITT_PROCESS_PKG.derive_po_cancel_lines(?,?,?,?)}";
    
    public CanonITTWorkbenchDAO() {
    }

    public static Object[] cancelPoPrc(String p_ord_num){
		   
		   NPZC104001PMsg  pmsgValuesFromTables = new NPZC104001PMsg();
		   List<NPZC104001PMsg>  pmsgValuesFromTablesList = new ArrayList<NPZC104001PMsg>();
		  
	        System.out.println("in cancelPoPrc "+p_ord_num+"|");
	        CallableStatement statement = null;
	        Connection connection = null;
	        Object[] result=new Object[3];
	        String status="";
	        String status_msg="";
	        try {
	            connection = TransactionScope.getConnection();
	            if (connection != null) {
	                statement = (CallableStatement) connection.prepareCall(DERIVE_PO_CANCEL_LINES);
	                if (statement != null) {
	                    statement.setObject(1, p_ord_num, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(2, OracleTypes.ARRAY, "CANON_E580_CREATE_PO_TBL_TYP");
	                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
	                    statement.execute();
	                    status=statement.getString(3);
	                    status_msg=statement.getString(4);
	                    Array canonE580CraetePoTblTypArray = statement.getArray(2);
	                    if("S".equals(status)){
	                    
	                    String[] pmsgArrayElements={"xxModeCd",
	                    	    "eventId",
	                    	    "procDt",
	                    	    "xxRqstTs",
	                    	    "poOrdNum",
	                    	    "dsPoTpCd",
	                    	    "dsPoTpNm",
	                    	    "poQlfyCd",
	                    	    "poSubmtPsnCd",
	                    	    "poSubmtTs",
	                    	    "poApvlStsCd",
	                    	    "poApvlPsnCd",
	                    	    "poApvlDt",
	                    	    "poApvlTs",
	                    	    "destRtlWhCd",
	                    	    "srcRtlWhCd",
	                    	    "poOrdSrcCd",
	                    	    "prntVndCd",
	                    	    "prntVndNm",
	                    	    "vndCd",
	                    	    "vndNm",
	                    	    "dealCcyCd",
	                    	    "vndDropShipFlg",
	                    	    "prchGrpCd",
	                    	    "vndPmtTermCd",
	                    	    "vndPmtTermDescTxt",
	                    	    "rqstTechTocCd",
	                    	    "rqstRcvDt",
	                    	    "rqstRcvTm",
	                    	    "shipToCustCd",
	                    	    "shipToLocNm",
	                    	    "shipToAcctNm",
	                    	    "shipToAddlLocNm",
	                    	    "shipToFirstLineAddr",
	                    	    "shipToScdLineAddr",
	                    	    "shipToThirdLineAddr",
	                    	    "shipToFrthLineAddr",
	                    	    "shipToFirstRefCmntTxt",
	                    	    "shipToScdRefCmntTxt",
	                    	    "shipToCtyAddr",
	                    	    "shipToStCd",
	                    	    "shipToProvNm",
	                    	    "shipToCntyNm",
	                    	    "shipToPostCd",
	                    	    "shipToCtryCd",
	                    	    "ctacPsnNm",
	                    	    "rtrnShipToRtlWhCd",
	                    	    "shipFromSoNumListTxt",
	                    	    "carrCd",
	                    	    "carrAcctNum",
	                    	    "shpgSvcLvlCd",
	                    	    "frtChrgToCd",
	                    	    "frtChrgMethCd",
	                    	    "lineBizTpCd",
	                    	    "poOrdCmntTxt",
	                    	    "trsmtMethTpCd",
	                    	    "sendPoFaxNum",
	                    	    "sendPoEmlAddr",
	                    	    "poSendFlg",
	                    	    "poSendTs",
	                    	    "poPrintFlg",
	                    	    "dsctnInd",
	                    	    "wfFlg",
	                    	    "vndIssOrdNum",
	                    	    "eipRptRqstPk"};	                   
	                    Object[] objcanonE580CraetePoTblTypArray = (Object[]) canonE580CraetePoTblTypArray.getArray();
	                    int attrCountcanonE580CraetePoObj=objcanonE580CraetePoTblTypArray.length;
	                    for(int i=0; i<attrCountcanonE580CraetePoObj;i++){
	                    	pmsgValuesFromTables = new NPZC104001PMsg();
	                    	 pmsgValuesFromTables.glblCmpyCd.setValue("ADB");
	        	            STRUCT structCanonE580CraetePoTblTypArray = (STRUCT)objcanonE580CraetePoTblTypArray[i];
	        	            
	        	            Object[] obj = structCanonE580CraetePoTblTypArray.getAttributes();
	        	            int attributePriorToFirstTblType=66;
	        	            int getAttributesTillFirstTableType=0;
	        	            
	        	            for(int pmsgArrayElementsCount=0;pmsgArrayElementsCount<pmsgArrayElements.length;pmsgArrayElementsCount++)
	        	            {
	        	            	 
	        	            	//pmsgValuesFromTables.xxModeCd.setValue((String) obj[pmsgArrayElementsCount++]);
		        	            //pmsgValuesFromTables.eventId.setValue((String) obj[pmsgArrayElementsCount++]);
	        	            	getAttributesTillFirstTableType=0;
	        	            	String valueFromDb="";
	        	            	valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	        	            	 pmsgValuesFromTables.xxModeCd.setValue(valueFromDb==null?"":valueFromDb);
	        	            	 valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.eventId.setValue(valueFromDb==null?"":valueFromDb);
	             	           valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.procDt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.xxRqstTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	           pmsgValuesFromTables.poOrdNum.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];	             	            
	             	            //getAttributesTillFirstTableType++;
	             	          	
	             	          	//valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.dsPoTpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];	             	            
	             	            pmsgValuesFromTables.dsPoTpNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            
	             	            pmsgValuesFromTables.poQlfyCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSubmtPsnCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSubmtTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlStsCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlPsnCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlDt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poApvlTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.destRtlWhCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.srcRtlWhCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poOrdSrcCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.prntVndCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.prntVndNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.dealCcyCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndDropShipFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.prchGrpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndPmtTermCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndPmtTermDescTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rqstTechTocCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rqstRcvDt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rqstRcvTm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCustCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToLocNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToAcctNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToAddlLocNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToFirstLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToScdLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToThirdLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToFrthLineAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToFirstRefCmntTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToScdRefCmntTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCtyAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToStCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToProvNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCntyNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToPostCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipToCtryCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.ctacPsnNm.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.rtrnShipToRtlWhCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shipFromSoNumListTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.carrCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.carrAcctNum.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.shpgSvcLvlCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.frtChrgToCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.frtChrgMethCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.lineBizTpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poOrdCmntTxt.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.trsmtMethTpCd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.sendPoFaxNum.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.sendPoEmlAddr.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSendFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poSendTs.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.poPrintFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.dsctnInd.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.wfFlg.setValue(valueFromDb==null?"":valueFromDb);valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            pmsgValuesFromTables.vndIssOrdNum.setValue(valueFromDb==null?"":valueFromDb);
	             	            valueFromDb=(String) obj[getAttributesTillFirstTableType++];
	             	            if(valueFromDb!=null)
	             	            pmsgValuesFromTables.eipRptRqstPk.setValue(new BigDecimal(valueFromDb));
	             	            int arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	           if(obj[arrayIndexToProcess] instanceof ARRAY)
	       	            	{
		       	            		 ARRAY canonE580PohMsgTypArray=(ARRAY)obj[arrayIndexToProcess];
		       	            		 Datum[] datumCanonE580PohMsgTyp=canonE580PohMsgTypArray.getOracleArray();
		       	            		pmsgValuesFromTables.poInfo.setValidCount(datumCanonE580PohMsgTyp.length);
		       	            		 for(int canonE580PohMsgTypArrayElementCount=0;canonE580PohMsgTypArrayElementCount<datumCanonE580PohMsgTyp.length;canonE580PohMsgTypArrayElementCount++)
		       	            		 {
		       	     	            STRUCT structcanonE580PohMsgTlbTypArray = (STRUCT)datumCanonE580PohMsgTyp[canonE580PohMsgTypArrayElementCount];
		       	     	           Object[] objcanonE580PohMsgTlbTyp = structcanonE580PohMsgTlbTypArray.getAttributes();
		       	     	           
		       	     	       String poHdrMsgLineValueFromDb="";
		       	     	       BigDecimal poHdrMsgBigDecimalValueFromDb=null;
		       	     	           for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<objcanonE580PohMsgTlbTyp.length;canonE580PohMsgTlbCount++)
		       	     	           {
		       	     	        	poHdrMsgBigDecimalValueFromDb=(BigDecimal) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        //pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).poMsgPk.setValue(poHdrMsgBigDecimalValueFromDb);
		       	     	        poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).poMsgTpCd.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).poMsgSubmtPsnCd.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).xxDsMultMsgDplyTxt.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb); poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).prchReqNum.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);poHdrMsgLineValueFromDb=(String) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).prchReqLineNum.setValue(poHdrMsgLineValueFromDb==null?"":poHdrMsgLineValueFromDb);
		       	     	        poHdrMsgBigDecimalValueFromDb=(BigDecimal) objcanonE580PohMsgTlbTyp[canonE580PohMsgTlbCount++];
		       	     	        pmsgValuesFromTables.poInfo.no(canonE580PohMsgTypArrayElementCount).prchReqLineSubNum.setValue(poHdrMsgBigDecimalValueFromDb);
		       	     	        		       	     	        	 
		       	     	           }
		       	            		 }
		       	            	}
	             	          arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	         if(obj[arrayIndexToProcess] instanceof ARRAY)
		       	            	{	             	        	
		       	            		 ARRAY canonE580PolTypArray=(ARRAY)obj[arrayIndexToProcess];
		       	            		 Datum[] datumcanonE580PolTyp=canonE580PolTypArray.getOracleArray();
		       	            		pmsgValuesFromTables.poLineInfo.setValidCount(datumcanonE580PolTyp.length);
		       	            		 for(int canonE580PohMsgTypArrayElementCount=0;canonE580PohMsgTypArrayElementCount<datumcanonE580PolTyp.length;canonE580PohMsgTypArrayElementCount++)
		       	            		 {
		       	     	            STRUCT structcanonE580PohMsgTlbTypArray = (STRUCT)datumcanonE580PolTyp[canonE580PohMsgTypArrayElementCount];
		       	     	           Object[] objcanonE580PolTlbTyp = structcanonE580PohMsgTlbTypArray.getAttributes();
		       	     	       String poLineValueFromDb="";
		       	     	       BigDecimal poLineBigDecimalValueFromDb=null;
		       	     	       
		       	     	   
		       	     	           for(int canonE580PohMsgTlbCount=0;canonE580PohMsgTlbCount<objcanonE580PolTlbTyp.length;canonE580PohMsgTlbCount++)
		       	     	           {
		       	     	        	poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poOrdDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).dispPoDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poLineTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poMdseCmpsnTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).setPoOrdDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).mdseCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).mdseNm.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).mdseDescShortTxt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poQty.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];				       	     	
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poDispQty.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poInvQty.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poDispUomCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).thisMthFobCostAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entDealNetUnitPrcAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entPoDtlDealNetAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entFuncNetUnitPrcAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).entPoDtlFuncNetAmt.setValue(poLineBigDecimalValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).exchRate.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).custUomCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).destRtlSwhCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).srcRtlSwhCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).invtyLocCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). rqstRcvDt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).rqstRcvTm.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).frtCondCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origMdseCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).fromStkStsCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).toStkStsCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).adminPsnCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poMatchTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). ordQty.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoOrdNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoDtlLineSubNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).custIssPoNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). custIssPoDt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).cpoOrdTpCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).billToCustCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).sellToCustCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).shpgPlnNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).prchReqNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).prchReqLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). prchReqLineSubNum.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).trxRefNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).trxRefLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).trxRefLineSubNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).aslDtlPk.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).aslMdseCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).aslUnitPrcAmt.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).shipFromSoNumListTxt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).vndInvtyLocCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).vndIssPoOrdNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).proNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).vndPoAckStsCd.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origPoOrdNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origPoOrdDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).origDispPoDtlLineNum.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	poLineBigDecimalValueFromDb=(BigDecimal) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	   pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount). svcConfigMstrPk.setValue(poLineBigDecimalValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poSendTs.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).poOrdDtlCmntTxt.setValue(poLineValueFromDb==null?"":poLineValueFromDb);poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];
				       	     	    pmsgValuesFromTables.poLineInfo.no(canonE580PohMsgTypArrayElementCount).xxMsgId.setValue(poLineValueFromDb==null?"":poLineValueFromDb);
				       	     	    //poLineValueFromDb=(String) objcanonE580PolTlbTyp[canonE580PohMsgTlbCount++];		       	     	        	  
		       	     	           }
		       	            		 }
		       	            	}
	             	         
	             	        arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	           if(obj[arrayIndexToProcess] instanceof ARRAY)
	       	            	{
	       	            		 ARRAY canonE580PosTblTypArray=(ARRAY)obj[arrayIndexToProcess];
	       	            		 Datum[] datumCanonE580PosTblTyp=canonE580PosTblTypArray.getOracleArray();
	       	            		pmsgValuesFromTables.serNumList.setValidCount(datumCanonE580PosTblTyp.length);
	       	            		 for(int canonE580PohMsgTypArrayElementCount=0;canonE580PohMsgTypArrayElementCount<datumCanonE580PosTblTyp.length;canonE580PohMsgTypArrayElementCount++)
	       	            		 {
	       	     	            STRUCT structCanonE580PosTblTypArray = (STRUCT)datumCanonE580PosTblTyp[canonE580PohMsgTypArrayElementCount];
	       	     	           Object[] objCanonE580PosTblTyp = structCanonE580PosTblTypArray.getAttributes();
	       	     	       String posLineValueFromDb="";
	       	     	       BigDecimal posLineBigDecimalValueFromDb=null;
	       	     	           for(int canonE580PosTlbCount=0;canonE580PosTlbCount<objCanonE580PosTblTyp.length;canonE580PosTlbCount++)
	       	     	           {	       	     	           
	       	     	        	posLineValueFromDb=(String) objCanonE580PosTblTyp[canonE580PosTlbCount++];
			       	     	    pmsgValuesFromTables.serNumList.no(canonE580PohMsgTypArrayElementCount).poOrdDtlLineNum.setValue(posLineValueFromDb==null?"":posLineValueFromDb);
			       	     	posLineBigDecimalValueFromDb=(BigDecimal) objCanonE580PosTblTyp[canonE580PosTlbCount++];
			       	     	    pmsgValuesFromTables.serNumList.no(canonE580PohMsgTypArrayElementCount).poSerNumPk.setValue(posLineBigDecimalValueFromDb);posLineValueFromDb=(String) objCanonE580PosTblTyp[canonE580PosTlbCount++];
			       	     	    pmsgValuesFromTables.serNumList.no(canonE580PohMsgTypArrayElementCount).serNum.setValue(posLineValueFromDb==null?"":posLineValueFromDb);			       	     	    
	       	     	           }	       	     	        	   
	       	            		 }
	       	            	}
	             	           
	             	          arrayIndexToProcess=getAttributesTillFirstTableType++;
	             	           if(obj[arrayIndexToProcess] instanceof ARRAY)
	       	            	{
	       	            		 ARRAY canoNE580PoaTypArray=(ARRAY)obj[arrayIndexToProcess];
	       	            		 Datum[] datumCanonE580PoaTyp=canoNE580PoaTypArray.getOracleArray();
	       	            		pmsgValuesFromTables.poAcctInfo.setValidCount(datumCanonE580PoaTyp.length);
	       	            		 for(int canonE580PoaTypArrayElementCount=0;canonE580PoaTypArrayElementCount<datumCanonE580PoaTyp.length;canonE580PoaTypArrayElementCount++)
	       	            		 {
	       	     	            STRUCT structCanonE580PoaTypArray = (STRUCT)datumCanonE580PoaTyp[canonE580PoaTypArrayElementCount];
	       	     	           Object[] objCanonE580PoaTblTyp = structCanonE580PoaTypArray.getAttributes();
	       	     	       String posLineValueFromDb="";
	       	     	           for(int canonE580PoaTlbCount=0;canonE580PoaTlbCount<objCanonE580PoaTblTyp.length;canonE580PoaTlbCount++)
	       	     	           {	       	     	           
	       	     	        	posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).poOrdDtlLineNum.setValue(posLineValueFromDb==null?"":posLineValueFromDb);
			       	     	    posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).poAcctTpCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaCmpyCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaAfflCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaBrCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaChCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaCcCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaAcctCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaProdCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaProjCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);posLineValueFromDb=(String) objCanonE580PoaTblTyp[canonE580PoaTlbCount++];
			       	     	    pmsgValuesFromTables.poAcctInfo.no(canonE580PoaTypArrayElementCount).coaExtnCd.setValue(posLineValueFromDb==null?"":posLineValueFromDb);
	       	     	           }	       	     	        	   
	       	            		 }
	       	            	}
	             	           
	             	         //  System.out.println("pmsgValuesFromTables "+pmsgValuesFromTables);
	        	            }
	        	            
	        	            pmsgValuesFromTablesList.add(pmsgValuesFromTables);
	                    }// for(int i=0; i<attrCountcanonE580CraetePoObj;i++)
	                    		
	                    }else {// if("S".equals(status))
	                    	System.out.println(status+"|"+status_msg);
	                    }
	                    	result[0]=pmsgValuesFromTablesList;
	                    	result[1]=status;
	                    	result[2]=status_msg;
	                    	
	                    
	                    
	                    System.out.println("pmsgValuesFromTablesList size in DAO:"+pmsgValuesFromTablesList.size());   
	                    
	                    
	                } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (statement != null) {
	                try {
	                    statement.close();
	                    statement = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
	            if (connection != null) {
	                try {
	                    TransactionScope.releaseConnection(connection);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }

	        }
	        return result;
	   }
   
   
}
