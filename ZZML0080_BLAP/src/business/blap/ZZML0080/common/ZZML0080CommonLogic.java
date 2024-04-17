package business.blap.ZZML0080.common;

import parts.common.EZDMsg;
import business.blap.ZZML0080.ZZML0080CMsg;
import business.blap.ZZML0080.ZZML0080Query;
import business.blap.ZZML0080.ZZML0080SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * @author Q02673
 */
public class ZZML0080CommonLogic {

    /**
     * @param cMsg ZZML0080CMsg
     * @param sMsg ZZML0080SMsg
     */
    public static void searchZZML0080(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg) {

        sMsg.clear();
        sMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());

        S21SsmEZDResult ssmResult = ZZML0080Query.getInstance().getMlGrpAddr(cMsg, sMsg);

        if (!ssmResult.isCodeNormal()) {
            if (!"E".equals(cMsg.getMessageKind())) {
                cMsg.setMessageInfo("ZZZM9005W", null);
            }
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        } else {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W"); //DZZM0001W
                queryResCnt = sMsg.A.length();
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxNum_A.setValue(i + 1);
            }

            int i = 0;
            for (; i < cMsg.A.length() && i < sMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        }
    }

    /**
     * @param cMsg ZZML0080CMsg
     * @param sMsg ZZML0080SMsg
     * @param pagenationFrom pagenationFrom(0 origin)
     * @param pagenationTo   pagenationTo
     */
    public static void storeEventSource(ZZML0080CMsg cMsg, ZZML0080SMsg sMsg, int pagenationFrom, int pagenationTo) {
        /*
        for (int i = 0; i < pagenationTo - pagenationFrom; i++) {
            //EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pagenationFrom + i), null);
            sMsg.A.no(pagenationFrom + i).xxChkBox_A.setValue(cMsg.A.no(i).xxChkBox_A.getValue());
        }
        */
    }
}
