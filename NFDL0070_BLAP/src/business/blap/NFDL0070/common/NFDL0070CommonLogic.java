/**
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0070.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import business.blap.NFDL0070.NFDL0070CMsg;
import business.blap.NFDL0070.NFDL0070Query;
import business.blap.NFDL0070.NFDL0070SMsg;

/**
 * NFDL0070CommonLogic.
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2021/03/25   CITS            G.Delgado       Update          QC#58587
 * 2022/04/21   CITS            K.Suzuki        Update          QC#59333
 * </pre>
 */
public class NFDL0070CommonLogic {

    /**
     * getName
     * @param bizMsg NFDL0070CMsg
     */
    public static void getAcctNm(NFDL0070CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            if (!NFDL0070Query.getInstance().getAcctNm(bizMsg)) {
                bizMsg.dsAcctNm.clear();
            }
        } else {
            bizMsg.dsAcctNm.clear();
        }
    }

    /**
     * @param bizMsg NFDL0070CMsg
     * @param globalMsg NFDL0070SMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> setSsmMap(NFDL0070CMsg bizMsg, NFDL0070SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        createCond_TrxType(ssmParam, bizMsg);

        ssmParam.put("rowNum", globalMsg.A.length());
        ssmParam.put("cMsg", bizMsg);
        ssmParam.put("unapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        ssmParam.put("receipt", AR_TRX_TP.RECEIPT);

        // START 2021/03/25 G.Delgado [QC#58587,ADD]
        // AR work flow status that are excluded
        // START 2022/04/22 K.Suzuki [QC#59660-3,DEL]
        //List<String> arDsWfStsCdList = new ArrayList<String>();
        //arDsWfStsCdList.add(AR_DS_WF_STS.PENDING);
        //arDsWfStsCdList.add(AR_DS_WF_STS.APPROVED);
        //arDsWfStsCdList.add(AR_DS_WF_STS.NOAPPROVER);

        //ssmParam.put("exclArDsWfStsCd", (String[]) arDsWfStsCdList.toArray(new String[arDsWfStsCdList.size()]));
        // END   2022/04/22 K.Suzuki [QC#59660-3,DEL]
        // END 2021/03/25 G.Delgado [QC#58587,ADD]

        return ssmParam;
    }

    /**
     * @param cMsg EZDCMsg
     */
    private static void createCond_TrxType(Map<String, Object> ssmParam, NFDL0070CMsg cMsg) {

        List<String> condList = new ArrayList<String>();

        condList.add(AR_TRX_TP.CREDIT_MEMO);
        condList.add(AR_TRX_TP.ON_ACCOUNT);
        condList.add(AR_TRX_TP.RECEIPT);

        ssmParam.put("arTrxTpCd", (String[]) condList.toArray(new String[condList.size()]));

    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     */
    public static void setPage(NFDL0070SMsg globalMsg, NFDL0070CMsg bizMsg) {

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     */
    public static void setPageJump(NFDL0070SMsg globalMsg, NFDL0070CMsg bizMsg) {

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum_H1.getValueInt());

        setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param pageFromNum int
     * @return int
     */
    public static int getPagenationFrom(int pageFromNum) {

        int pagenationFrom = pageFromNum;

        if (0 != pagenationFrom) {
            pagenationFrom = pagenationFrom - 1;
        }
        return pagenationFrom;
    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setBizMsgToGlobalMsg(NFDL0070SMsg globalMsg, NFDL0070CMsg bizMsg, int index, int pagenationFrom) {

        for (int i = index; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
            } else {
                break;
            }
        }
    }

    /**
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param dataCount int
     */
    public static void setPageData(NFDL0070SMsg globalMsg, NFDL0070CMsg bizMsg, int dataCount) {

        if (0 == dataCount) {
            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

        int page = dataCount / bizMsg.A.length();
        int rest = dataCount % bizMsg.A.length();
        int fromNum = 0;

        if (0 == rest) {
            fromNum = ((page - 1) * bizMsg.A.length());
        } else {
            fromNum = (page * bizMsg.A.length());
        }

        setGlobalMsgToBizMsg(globalMsg, bizMsg, fromNum, fromNum);

        bizMsg.xxPageShowFromNum.setValue(fromNum + 1);
        bizMsg.xxPageShowToNum.setValue(fromNum + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

    }

    /**
     * <dd>The method explanation: The business peculiarity processing
     * is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setGlobalMsgToBizMsg(NFDL0070SMsg globalMsg, NFDL0070CMsg bizMsg, int index, int pagenationFrom) {

        ZYPTableUtil.clear(bizMsg.A);
        int i = index;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(i - pagenationFrom);
    }
}
