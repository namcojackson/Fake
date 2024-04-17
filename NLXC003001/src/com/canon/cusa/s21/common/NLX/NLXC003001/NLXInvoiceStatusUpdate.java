/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC003001;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.IMPT_INVTMsg;
import business.db.IMPT_PACK_SLP_STSTMsg;
import business.db.IMPT_PACK_SLP_STSTMsgArray;

import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_INV_STS;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Fujitsu         M.Irisawa       Create          N/A
 * 12/22/2009   Fujitsu         T.Motoyama      Update          RQ571
 * 01/28/2010   Fujitsu         M.Irisawa       Update          RQ868
 *</pre>
 */
public class NLXInvoiceStatusUpdate {
    // public
    // ----------------------------------------------------------
    /**
     */
    public static final String GLBL_CMPY_CD = "glblCmpyCd";

    /**
     */
    public static final String IMPT_INV_PK = "imptInvPk";

    /**
     */
    public static final String IMPT_INV_STS_CD = "imptInvStsCd";

    /**
     */
    public static final String IMPT_INV_RWS_STS_CD = "imptInvRwsStsCd";

    // private
    // ----------------------------------------------------------
    /**
     * SQLID
     */
    private static final String SQLID01 = "001";

    /**
     */
    private static final String NLXM1001E = "NLXM1001E";

    /**
     */
    private static final String NLXM1002E = "NLXM1002E";

    /**
     */
    private static final String NLXM1003E = "NLXM1003E";

    /**
     * 
     */
    private static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     */
    private static final String IMPT_INV_STS_UPD_TS = "imptInvStsUpdTs";

    /**
     */
    private static final String RWS_STS_UPD_TS = "rwsStsUpdTs";

    /**
     */
    private ArrayList<String> codeList;

    /**
     */
    public NLXInvoiceStatusUpdate() {
        codeList = new ArrayList<String>();
        codeList.add(IMPT_INV_STS.RCV_ERR);
        codeList.add(IMPT_INV_STS.INVTY_REGD);
        codeList.add(IMPT_INV_STS.WH_CHNG);
        codeList.add(IMPT_INV_STS.WH_FIXED);
        codeList.add(IMPT_INV_STS.INV_REL);
        codeList.add(IMPT_INV_STS.RWS_CRTD);
        codeList.add(IMPT_INV_STS.RCVING);
        codeList.add(IMPT_INV_STS.RCVD);
        codeList.add(IMPT_INV_STS.CLOSED);
    }

    /**
     */
    public int updateInvoiceStatus(List<Map<String, Object>> invPkList) throws SQLException {
        int updCount = 0;
        for (Map<String, Object> paramMap : invPkList) {
            String glblCmpyCd = String.valueOf(paramMap.get(GLBL_CMPY_CD));
            BigDecimal invPk = (BigDecimal) paramMap.get(IMPT_INV_PK);
            String imptInvStsCd = String.valueOf(paramMap.get(IMPT_INV_STS_CD));
            String imptInvRwsStsCd = String.valueOf(paramMap.get(IMPT_INV_RWS_STS_CD));
            if (updateInvoiceStatus(glblCmpyCd, invPk, imptInvStsCd, imptInvRwsStsCd) > 0) {
                updCount++;
            }
        }
        return updCount;
    }

    /**
     * @param glblCmpyCd GlobalCompanyCode
     * @param invPk IMPT_INV_PK
     * @param imptInvStsCd imptInvStsCd
     * @param imptInvRwsStsCd imptInvRwsStsCd
     */
    public int updateInvoiceStatus(String glblCmpyCd, BigDecimal invPk, String imptInvStsCd, String imptInvRwsStsCd) throws SQLException {
        IMPT_PACK_SLP_STSTMsgArray returnList = getPackingSlip(glblCmpyCd, invPk);
        int updCount = updateInvoiceStatus(glblCmpyCd, invPk, imptInvStsCd, imptInvRwsStsCd, returnList);
        return updCount;
    }

    /**
     * @param glblCmpyCd GlobalCompanyCode
     * @param invPk IMPT_INV_PK
     * @param imptInvStsCd imptInvStsCd
     * @param imptInvRwsStsCd imptInvRwsStsCd
     */
    public int updateInvoiceStatus(String glblCmpyCd, BigDecimal invPk, String imptInvStsCd, String imptInvRwsStsCd, IMPT_PACK_SLP_STSTMsgArray packingslipList) throws SQLException {
        int updCount = 0;
        List<String> packSlpStsCdList = new ArrayList<String>();
        String resultStsInvoice = null;
        String resultStsRWS = null;
        String systemDate = null;

        if (packingslipList == null || packingslipList.length() == 0) {
            S21InfoLogOutput.println(NLXM1003E, new String[] {invPk.toString() });
            throw new S21AbendException(NLXM1003E, new String[] {invPk.toString() });
        }

        for (int i = 0; i < packingslipList.length(); i++) {
            IMPT_PACK_SLP_STSTMsg wrkTMsg = (IMPT_PACK_SLP_STSTMsg) packingslipList.get(i);
            String wrkStsCdStr = wrkTMsg.imptPackSlpStsCd.getValue();
            String wrkStsPkStr = wrkTMsg.imptPackSlpStsPk.getValue().toString();
            if (!checkPSStatus(wrkStsCdStr)) {
                S21InfoLogOutput.println(NLXM1001E, new String[] {wrkStsPkStr, wrkStsCdStr });
                throw new S21AbendException(NLXM1001E, new String[] {wrkStsPkStr, wrkStsCdStr });
            }
            if (!packSlpStsCdList.contains(wrkStsCdStr)) {
                packSlpStsCdList.add(wrkStsCdStr);
            }
        }

        if (existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCV_ERR)
                && !existsPSStatus(packSlpStsCdList, IMPT_INV_STS.INVTY_REGD, IMPT_INV_STS.RCVD)) {
            resultStsInvoice = IMPT_INV_STS.RCV_ERR;
            resultStsRWS = IMPT_INV_STS.UN_PRINTED;

        } else if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCV_ERR)) {
            if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCVD)
                    && existsPSStatus(packSlpStsCdList, IMPT_INV_STS.CLOSED, IMPT_INV_STS.CLOSED)) {
                resultStsInvoice = IMPT_INV_STS.CLOSED;
                resultStsRWS = IMPT_INV_STS.CLOSED;
            } else if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCVD)
                    && !existsPSStatus(packSlpStsCdList, IMPT_INV_STS.CLOSED, IMPT_INV_STS.CLOSED)) {
                S21InfoLogOutput.println(NLXM1002E, new String[] {invPk.toString() });
                throw new S21AbendException(NLXM1002E, new String[] {invPk.toString() });

            } else {
                resultStsInvoice = setInvoiceStatus(packSlpStsCdList);
                resultStsRWS = setRWSStatus(packSlpStsCdList, resultStsInvoice);
                if (resultStsInvoice == null || resultStsRWS == null) {
                    S21InfoLogOutput.println(NLXM1002E, new String[] {invPk.toString() });
                    throw new S21AbendException(NLXM1002E, new String[] {invPk.toString() });
                }
            }
        } else {
            S21InfoLogOutput.println(NLXM1002E, new String[] {invPk.toString() });
            throw new S21AbendException(NLXM1002E, new String[] {invPk.toString() });
        }

        systemDate = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);

        IMPT_INVTMsg updMsg = new IMPT_INVTMsg();
        IMPT_INVTMsg conMsg = new IMPT_INVTMsg();
        ArrayList<String> updateItemList = new ArrayList<String>();
        String[] conItem = new String[] {GLBL_CMPY_CD, IMPT_INV_PK };
        if (resultStsInvoice.equals(imptInvStsCd)
                && resultStsRWS.equals(imptInvRwsStsCd)) {
            return updCount;
        }
        if (!resultStsInvoice.equals(imptInvStsCd)) {
            updateItemList.add(IMPT_INV_STS_CD);
            updateItemList.add(IMPT_INV_STS_UPD_TS);
            updMsg.imptInvStsCd.setValue(resultStsInvoice);
            updMsg.imptInvStsUpdTs.setValue(systemDate);
        }
        if (!resultStsRWS.equals(imptInvRwsStsCd)) {
            updateItemList.add(IMPT_INV_RWS_STS_CD);
            updateItemList.add(RWS_STS_UPD_TS);
            updMsg.imptInvRwsStsCd.setValue(resultStsRWS);
            updMsg.rwsStsUpdTs.setValue(systemDate);
        }
        String[] updateItemStringList = new String[updateItemList.size()];
        for (int i = 0; i < updateItemStringList.length; i++) {
            updateItemStringList[i] = updateItemList.get(i);
        }
        conMsg.glblCmpyCd.setValue(glblCmpyCd);
        conMsg.imptInvPk.setValue(invPk);
        S21ApiTBLAccessor.updateByPartialValue(conMsg, conItem, updMsg, updateItemStringList);
        updCount = 1;
        return updCount;
    }

    /**
     * @param glblCmpyCd String
     * @param invPk BigDecimal
     * @return Map
     * 
     */
    public Map<String, String> getInvoiceStatus(String glblCmpyCd, BigDecimal invPk) throws SQLException {
        IMPT_PACK_SLP_STSTMsgArray returnList = getPackingSlip(glblCmpyCd, invPk);
        return getInvoiceStatus(glblCmpyCd, invPk, returnList);
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param invPk BigDecimal
     * @param packingslipList IMPT_PACK_SLP_STSTMsgArray
     * @return Map
     * @throws SQLException
     */
    public Map<String, String> getInvoiceStatus(String glblCmpyCd, BigDecimal invPk, IMPT_PACK_SLP_STSTMsgArray packingslipList) throws SQLException {

        List<String> packSlpStsCdList = new ArrayList<String>();
        String resultStsInvoice = null;
        String resultStsRWS = null;

        if (packingslipList == null || packingslipList.length() == 0) {
            S21InfoLogOutput.println(NLXM1003E, new String[] {invPk.toString() });
            throw new S21AbendException(NLXM1003E, new String[] {invPk.toString() });
        }

        for (int i = 0; i < packingslipList.length(); i++) {
            IMPT_PACK_SLP_STSTMsg wrkTMsg = (IMPT_PACK_SLP_STSTMsg) packingslipList.get(i);
            String wrkStsCdStr = wrkTMsg.imptPackSlpStsCd.getValue();
            String wrkStsPkStr = wrkTMsg.imptPackSlpStsPk.getValue().toString();
            if (!checkPSStatus(wrkStsCdStr)) {
                S21InfoLogOutput.println(NLXM1001E, new String[] {wrkStsPkStr, wrkStsCdStr });
                throw new S21AbendException(NLXM1001E, new String[] {wrkStsPkStr, wrkStsCdStr });
            }
            if (!packSlpStsCdList.contains(wrkStsCdStr)) {
                packSlpStsCdList.add(wrkStsCdStr);
            }
        }

        if (existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCV_ERR) && !existsPSStatus(packSlpStsCdList, IMPT_INV_STS.INVTY_REGD, IMPT_INV_STS.RCVD)) {
            resultStsInvoice = IMPT_INV_STS.RCV_ERR;
            resultStsRWS = IMPT_INV_STS.UN_PRINTED;

        } else if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCV_ERR)) {
            if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCVD) && existsPSStatus(packSlpStsCdList, IMPT_INV_STS.CLOSED, IMPT_INV_STS.CLOSED)) {
                resultStsInvoice = IMPT_INV_STS.CLOSED;
                resultStsRWS = IMPT_INV_STS.CLOSED;
            } else if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.RCVD) && !existsPSStatus(packSlpStsCdList, IMPT_INV_STS.CLOSED, IMPT_INV_STS.CLOSED)) {
                S21InfoLogOutput.println(NLXM1002E, new String[] {invPk.toString() });
                throw new S21AbendException(NLXM1002E, new String[] {invPk.toString() });

            } else {
                resultStsInvoice = setInvoiceStatus(packSlpStsCdList);
                resultStsRWS = setRWSStatus(packSlpStsCdList, resultStsInvoice);
                if (resultStsInvoice == null || resultStsRWS == null) {
                    S21InfoLogOutput.println(NLXM1002E, new String[] {invPk.toString() });
                    throw new S21AbendException(NLXM1002E, new String[] {invPk.toString() });
                }
            }
        } else {
            S21InfoLogOutput.println(NLXM1002E, new String[] {invPk.toString() });
            throw new S21AbendException(NLXM1002E, new String[] {invPk.toString() });
        }

        Map<String, String> stsInfo = new HashMap<String, String>();
        stsInfo.put(IMPT_INV_STS_CD, resultStsInvoice);
        stsInfo.put(IMPT_INV_RWS_STS_CD, resultStsRWS);

        return stsInfo;

    }

    /**
     */
    private String setInvoiceStatus(List packSlpStsCdList) {
        String resultStsInvoice = null;

        if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.INVTY_REGD, IMPT_INV_STS.WH_FIXED)) {
            resultStsInvoice = IMPT_INV_STS.INV_REL;
            return resultStsInvoice;
        }

        if (existsPSStatus(packSlpStsCdList, IMPT_INV_STS.INVTY_REGD, IMPT_INV_STS.INVTY_REGD)
                && !existsPSStatus(packSlpStsCdList, IMPT_INV_STS.WH_CHNG, IMPT_INV_STS.WH_FIXED)) {
            resultStsInvoice = IMPT_INV_STS.INVTY_REGD;
            return resultStsInvoice;
        }

        if (existsPSStatus(packSlpStsCdList, IMPT_INV_STS.WH_CHNG, IMPT_INV_STS.WH_CHNG)) {
            resultStsInvoice = IMPT_INV_STS.WH_CHNG;
            return resultStsInvoice;
        }

        if (!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.WH_CHNG, IMPT_INV_STS.WH_CHNG)
                && existsPSStatus(packSlpStsCdList, IMPT_INV_STS.WH_FIXED, IMPT_INV_STS.WH_FIXED)) {
            resultStsInvoice = IMPT_INV_STS.WH_FIXED;
            return resultStsInvoice;
        }
        return resultStsInvoice;
    }

    /**
     */
    private String setRWSStatus(List packSlpStsCdList, String resultStsInvoice) {
        String resultStsRWS = null;
        boolean isRwsCreated = false;
        boolean isReceiving  = false;
        boolean isReceived   = false;

        if (resultStsInvoice == null) {
            return resultStsRWS;
        }

        if ((!existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCV_ERR, IMPT_INV_STS.WH_FIXED)
                && existsPSStatus(packSlpStsCdList, IMPT_INV_STS.INV_REL, IMPT_INV_STS.INV_REL))
                || (Integer.parseInt(resultStsInvoice) >= Integer.parseInt(IMPT_INV_STS.INVTY_REGD)
                        && Integer.parseInt(IMPT_INV_STS.WH_FIXED) >= Integer.parseInt(resultStsInvoice))) {
            if (existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RWS_CRTD, IMPT_INV_STS.RCVD)) {
                resultStsRWS = IMPT_INV_STS.RWS_CRTNG;
                return resultStsRWS;
            } else {
                resultStsRWS = IMPT_INV_STS.UN_PRINTED;
                return resultStsRWS;
            }
        }

        isRwsCreated = existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RWS_CRTD, IMPT_INV_STS.RWS_CRTD);
        isReceiving  = existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCVING,   IMPT_INV_STS.RCVING);
        isReceived   = existsPSStatus(packSlpStsCdList, IMPT_INV_STS.RCVD,     IMPT_INV_STS.RCVD);
        if (isRwsCreated && !isReceiving && !isReceived) {
            resultStsRWS = IMPT_INV_STS.RWS_CRTD;
            return resultStsRWS;
        } else if ((isRwsCreated && !isReceiving && isReceived) || isReceiving) {
            resultStsRWS = IMPT_INV_STS.RCVING;
            return resultStsRWS;
        } else if (!isRwsCreated && !isReceiving && isReceived) {
            resultStsRWS = IMPT_INV_STS.RCVD;
            return resultStsRWS;
        }
        return resultStsRWS;
    }

    /**
     * @param glblCmpyCd GlobalCompanyCode
     * @param invPk IMPT_INV_PK
     */
    public IMPT_PACK_SLP_STSTMsgArray getPackingSlip(String glblCmpyCd, BigDecimal invPk) {
        IMPT_PACK_SLP_STSTMsg conMsg = new IMPT_PACK_SLP_STSTMsg();
        IMPT_PACK_SLP_STSTMsgArray returnList = null;
        conMsg.setSQLID(SQLID01);
        conMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        conMsg.setConditionValue("imptInvPk01", invPk);
        returnList = (IMPT_PACK_SLP_STSTMsgArray) S21ApiTBLAccessor.findByCondition(conMsg);
        return returnList;
    }

    /**
     * @return boolean
     */
    private boolean existsPSStatus(List packSlpStsCdList, String stsFrom, String stsTo) {
        boolean returnCode = false;
        for (int i = this.codeList.indexOf(stsFrom); i <= this.codeList.indexOf(stsTo); i++) {
            if (packSlpStsCdList.contains(this.codeList.get(i))) {
                returnCode = true;
                return returnCode;
            }
        }
        return returnCode;
    }

    /**
     * @param statusCd
     * @return boolean
     * @throws SQLException
     */
    private boolean checkPSStatus(String statusCd) throws SQLException {

        if (statusCd == null) {
            return false;
        }

        if (IMPT_INV_STS.RWS_CRTNG.equals(statusCd)) {
            return false;
        } else if (this.codeList.contains(statusCd)) {
            return true;
        }
        return false;
    }
}
