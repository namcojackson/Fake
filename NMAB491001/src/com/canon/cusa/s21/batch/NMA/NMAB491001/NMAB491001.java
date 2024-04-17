/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NMA.NMAB491001;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.NMAI2070_01TMsg;
import business.db.NMAI2070_02TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 *  schedule batch
 *  NMAB4910 Load DPL by batch(Outbound)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/01   SRA             Takeyama        Create          N/A
 * 2009/11/23   SRA             Yuan            Update          N/A
 * 2009/12/22   SRA             Hiroki Koyama   Update          N/A
 * 2010/12/22   SRA             Y.Chen          Update          N/A
 *</pre>
 */

public class NMAB491001 extends S21BatchMain {

    /** message of processed record's count */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** error message(DB access error) */
    private static final String DB_ACCESS_ERR = "NMAM8010E";

    /** error message(InterfaceID no data) */
    private static final String INTFC_ID_ERR = "NMAM8009E";

    /** error message(argument error) */
    private static final String ARG_ERR = "NMAM8039E";

    /** Data Created */
    private static final String DATA_CREATED = "create";

    /** DB Table : NMAI2070_01 */
    private static final String NMAI2070_01 = "NMAI2070_01";

    /** DB Table : NMAI2070_02 */
    private static final String NMAI2070_02 = "NMAI2070_02";

    /** NMAI2070 value (SEGMENT_ID) */
    private static final BigDecimal NMAI2070_SEGMENT_ID = new BigDecimal(1);

    /** NMAI2070 value (UNIT_ID) */
    private static final BigDecimal NMAI2070_UINIT_ID = new BigDecimal(1);

    /** NMAI2070 value (SEQ_NUMBER) */
    private static final BigDecimal NMAI2070_SEQ_NUMBER = new BigDecimal(1);

    /** NMAI2070_01 value (SERVICE_TYPE) */
    private static final String NMAI2070_SERVICE_TYPE = "ECE";

    /** NMAI2070_01 value (SHIP_FROM_COUNTRY) */
    private static final String NMAI2070_SHIP_FROM_COUNTRY = "US";
    
    private static final String RECORD_ID_NMAI2070_01 = "RDPS70OBR1";
    private static final String RECORD_ID_NMAI2070_02 = "RDPS70OBR2";

    /** INTERFACE_TRANSACTION Not Value Zero */
    private static final String INTERFACE_TRANSACTION_NOT_VALUE = "0";

    /** termineted code */
    private TERM_CD termCd;

    /** operation date */
    private String opeDate;

    /** sales date */
    private String salesDate;

    /** insert count NMAI2070_02 */
    private int insCountAmai207002 = 0;

    /** commit insert count NMAI2070_02 */
    private int insCommitCountAmai207002 = 0;
    
    /** NMAI2070_02 (SKIP) */
    private int skip = -1;    

    /** Interface ID */
    private String interfaceId = null;

    /** Transaction ID */
    private BigDecimal transactionId;

    /** NMAI2070_02 (SEQ_NUMBER), Start from 2 */
    private int amai2070seqNumberId = 1;

    /** INTERFACE_TRANSACTION NMAI2070_01 Create Flag */
    private boolean creIfTranFlg = false;

    /** ArrayList for fast insert */
    private List<NMAI2070_02TMsg> insList = null;        
    
    /** DPL Inbound */
    private static final String DPL_INBOUND_BATCH = "NMAB492001";
    
    
    // -- Input parameters ----------------------
    /** Commit Count */
    private int commitCount = 1000;

    /** Fetch Size */
    private int fetchSize = 2000;    
    /**
     * Main method
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NMAB491001().executeBatch(NMAB491001.class.getSimpleName());

    }

    /**
     * Initialize process
     */
    protected void initRoutine() {

        // Initialize variable
        this.termCd = TERM_CD.NORMAL_END;

        // argument check(glblCmpyCd)
        if (!ZYPCommonFunc.hasValue(this.getGlobalCompanyCode())) {
            throw new S21AbendException(ARG_ERR, new String[] {"GlobalCompanyCode"});
        }

        // Get operation date
        this.opeDate = ZYPDateUtil.getBatProcDate();

        // Get sales date
        this.salesDate = ZYPDateUtil.getSalesDate();

        // get InterfaceId(NMAI2070)
        interfaceId = getInterfaceID();

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            // INTERFACE ID is no data
            throw new S21AbendException(INTFC_ID_ERR);
        }

        // Get the Commit Count.If an error occurs, throw Exception.
        if (null != getUserVariable3() && getUserVariable3().matches("[0-9]+")) {
            commitCount = Integer.valueOf(getUserVariable3()).intValue();
        }
        // Get the Fetch Count.If an error occurs, throw Exception.
        if (null != getUserVariable4() && getUserVariable4().matches("[0-9]+")) {
            fetchSize = Integer.valueOf(getUserVariable4()).intValue();
        }
        
        // init ArrayList for fast insert
        this.insList = new ArrayList<NMAI2070_02TMsg>(commitCount);
        

        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- opeDate : " + this.opeDate, this);
        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- salesDate : " + this.salesDate, this);
        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- interfaceId : " + this.interfaceId, this);

    }

    /**
     * Main process
     */
    protected void mainRoutine() {

        // Execute application process
        doProcess();

    }

    /**
     * Application process
     */
    private void doProcess() {
    	S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    	String queryId = "getLocation";
        String lastCompletedRequestTime = getLastCompletedRequestTime(this.interfaceId);
    	Map<String, String> queryParam = new HashMap<String, String>();
    	
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        if( ZYPCommonFunc.hasValue(lastCompletedRequestTime) ){
            queryParam.put("lastCompletedRequestTime", lastCompletedRequestTime);
        }
        queryParam.put("ezupaplid", DPL_INBOUND_BATCH);

        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- lastCompletedRequestTime : " + lastCompletedRequestTime, this);
        
    	List<Map> locList = (List<Map>) ssmBatchClient.queryObjectList(queryId, queryParam, skip, fetchSize);
    	while (!locList.isEmpty()) {
    		if (!creIfTranFlg) {
    			// create Integration Record
            	S21TransactionTableAccessor s21tta = new S21TransactionTableAccessor();
            	this.transactionId = s21tta.createIntegrationRecord(this.interfaceId);

            	// create NMAI2070_01
            	createAmai2070_01();
            	commit();
            	creIfTranFlg = true;
    		}
    		
        	for (Map map : locList) {
        		// create NMAI2070_02
        		createAmai2070_02(map);
        		insCommitCountAmai207002++;
        		if (this.insCommitCountAmai207002  >= commitCount) {
                    this.insertNMAI2070_02();
                }
        	}
            if (this.insCommitCountAmai207002 > 0) {
                this.insertNMAI2070_02();
            }
        	skip = skip + fetchSize;
        	locList = (List<Map>) ssmBatchClient.queryObjectList(queryId, queryParam, skip, fetchSize);
    	}
    	
    }
    
    private String getLastCompletedRequestTime( String interfaceId ){
        S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("interfaceId", interfaceId);
        List<Map> ssmResultList = (List<Map>) ssmBatchClient.queryObjectList("getLastCompletedRequestTime", queryParam);
        if( ssmResultList != null && ssmResultList.size() > 0 ){
            // 20110810
//            String lastCompletedRequestTime = (String)ssmResultList.get(0).get("EZUPTIME");
            String lastCompletedRequestTime = (String)ssmResultList.get(0).get("EZINTIME");
            if( ZYPCommonFunc.hasValue( lastCompletedRequestTime ) ){
                return lastCompletedRequestTime;
            }
        }
        return INTERFACE_TRANSACTION_NOT_VALUE;
    }
    
    /**
     * create NMAI2070_01
     */
    private void createAmai2070_01() {

        NMAI2070_01TMsg creRec = new NMAI2070_01TMsg();
        String salesDateEdit = getFormattedSaleDate("dd-MMM-yy");

        ZYPEZDItemValueSetter.setValue(creRec.rdps2070RecIdL10If, RECORD_ID_NMAI2070_01);
        ZYPEZDItemValueSetter.setValue(creRec.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(creRec.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(creRec.segmentId, NMAI2070_SEGMENT_ID);
        ZYPEZDItemValueSetter.setValue(creRec.unitId, NMAI2070_UINIT_ID);
        ZYPEZDItemValueSetter.setValue(creRec.seqNumber, NMAI2070_SEQ_NUMBER);
        ZYPEZDItemValueSetter.setValue(creRec.salesordidL32If, salesDateEdit);
        ZYPEZDItemValueSetter.setValue(creRec.groupbyidL32If, salesDateEdit);
        ZYPEZDItemValueSetter.setValue(creRec.shipfromcountryL20If, NMAI2070_SHIP_FROM_COUNTRY);
        ZYPEZDItemValueSetter.setValue(creRec.serviceprtnrtypeL15If, NMAI2070_SERVICE_TYPE);
        
        S21FastTBLAccessor.insert(creRec);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(creRec.getReturnCode())) {
            throw new S21AbendException(DB_ACCESS_ERR, new String[] {NMAI2070_01, DATA_CREATED});
        }
    }
    
    /**
     * create NMAI2070_02
     */
    private void createAmai2070_02(Map map) {

        NMAI2070_02TMsg creRec = new NMAI2070_02TMsg();

        ZYPEZDItemValueSetter.setValue(creRec.rdps2070RecIdL10If, RECORD_ID_NMAI2070_02);
        ZYPEZDItemValueSetter.setValue(creRec.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(creRec.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(creRec.segmentId, NMAI2070_SEGMENT_ID);
        ZYPEZDItemValueSetter.setValue(creRec.unitId, NMAI2070_UINIT_ID);
        this.amai2070seqNumberId++;
        ZYPEZDItemValueSetter.setValue(creRec.seqNumber, new BigDecimal(this.amai2070seqNumberId));

        ZYPEZDItemValueSetter.setValue(creRec.name1L1000If, (String) map.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(creRec.nameindex1L1If, "1");
        if( ZYPCommonFunc.hasValue( (String)map.get("ADDL_LOC_NM") ) ){
            ZYPEZDItemValueSetter.setValue(creRec.name2L1000If, (String) map.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(creRec.nameindex2L1If, "2");
        }
        ZYPEZDItemValueSetter.setValue(creRec.partnrtypeL100If, (String) map.get("PTY_CD"));
        ZYPEZDItemValueSetter.setValue(creRec.addrline1L3000If, (String) map.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(creRec.addrline1IndL1If, "1");
        if( ZYPCommonFunc.hasValue( (String)map.get("SCD_LINE_ADDR") ) ){
            ZYPEZDItemValueSetter.setValue(creRec.addrline2L3000If, (String) map.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(creRec.addrline2IndL1If, "2");
        }
        if( ZYPCommonFunc.hasValue( (String)map.get("THIRD_LINE_ADDR") ) ){
            ZYPEZDItemValueSetter.setValue(creRec.addrline3L3000If, (String) map.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(creRec.addrline3IndL1If, "3");
        }
        if( ZYPCommonFunc.hasValue( (String)map.get("FRTH_LINE_ADDR") ) ){
            ZYPEZDItemValueSetter.setValue(creRec.addrline4L3000If, (String) map.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(creRec.addrline4IndL1If, "4");
        }
        ZYPEZDItemValueSetter.setValue(creRec.cityL1000If, (String) map.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(creRec.countryL60If, (String) map.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(creRec.postalcodeL60If, (String)map.get("POST_CD"));
        
        this.insList.add(creRec);

        this.insCountAmai207002++;
    }

    /**
     * insert NMAI2070_02TMsg at once
     */
    private boolean insertNMAI2070_02() {

        if (this.insList.isEmpty() == false) {
            if (S21FastTBLAccessor.insert(this.insList.toArray(new NMAI2070_02TMsg[]{})) != insList.size()) {
                this.insCountAmai207002 = this.insCountAmai207002 - insList.size();
                // processed record's count output
                outputProcessCnt();
                // ABEND
                throw new S21AbendException(DB_ACCESS_ERR, new String[] {NMAI2070_02, DATA_CREATED});
            }
            commit();
            this.insCommitCountAmai207002  = 0;
        }
        this.insList.clear();
        
        return true;
        
    }

    private String getFormattedSaleDate( String formatString ){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
            Date date1 = sdf.parse(this.salesDate);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            sdf.applyPattern(formatString);
            String formattedDate = sdf.format(date1);
            EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- salesDateEdit:" + formattedDate, this);
            return formattedDate;

        } catch (Exception ex) {
            outputProcessCnt();
            throw new S21AbendException(ex);
        }
    }

    private void outputProcessCnt() {

        // processed record's count output
        S21InfoLogOutput.printlnv(ZZBM0009I, NMAI2070_02, DATA_CREATED, this.insCountAmai207002);

    }

    protected void termRoutine() {

        outputProcessCnt();

        setTermState(this.termCd);
    }

}
