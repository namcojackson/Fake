/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parts.common.EZDMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_rtnDtlPMsg;

/**
 * <pre>
 * Common Logic for Sort DS CPO Update API line PMsg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Fujitsu         S.Takami        Create          S21_NA#12637
 * </pre>
 */
public class NWXC150001SortDetailPMsg {

    /**
     * <pre>
     * Sort NWZC150001PMsg APMsgArray and rtnDtlPMsgArray
     * by CPO_DTL_LINE_NUM & CPO_DTL_LINE_SUB_NUM.
     * @param cpoUpdApiReqPMsg NWZC150001PMsg DS CPO Update API PMsg.
     * </pre>
     */
    public static void sortLinePmsgAndRtnPmsgByLineNum(NWZC150001PMsg cpoUpdApiReqPMsg) {

        List<NWZC150001_APMsg> linePmsgList = new ArrayList<NWZC150001_APMsg>(0);
        for (int i = 0; i < cpoUpdApiReqPMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = new NWZC150001_APMsg();
            EZDMsg.copy(cpoUpdApiReqPMsg.A.no(i), null, aPMsg, null);
            linePmsgList.add(aPMsg);
        }
        Collections.sort(linePmsgList, new NWXC150001CompareAPMsg());
        int listCnt = 0;
        for (NWZC150001_APMsg aPMsg : linePmsgList) {
            EZDMsg.copy(aPMsg, null, cpoUpdApiReqPMsg.A.no(listCnt), null);
            listCnt++;
        }
        cpoUpdApiReqPMsg.A.setValidCount(listCnt);

        List<NWZC150001_rtnDtlPMsg> rtnDtlPmsgList = new ArrayList<NWZC150001_rtnDtlPMsg>(0);
        for (int i = 0; i < cpoUpdApiReqPMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = new NWZC150001_rtnDtlPMsg();
            EZDMsg.copy(cpoUpdApiReqPMsg.rtnDtl.no(i), null, rtnDtlPMsg, null);
            rtnDtlPmsgList.add(rtnDtlPMsg);
        }
        Collections.sort(rtnDtlPmsgList, new NWXC150001CompareRtnDtlPMsg());
        listCnt = 0;
        for (NWZC150001_rtnDtlPMsg rtnDtlPMsg : rtnDtlPmsgList) {
            EZDMsg.copy(rtnDtlPMsg, null, cpoUpdApiReqPMsg.rtnDtl.no(listCnt), null);
            listCnt++;
        }
        cpoUpdApiReqPMsg.rtnDtl.setValidCount(listCnt);
    }
}
