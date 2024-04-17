package com.canon.cusa.s21.api.NWZ.NWZC042001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.LinkedList;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MSG_TXT_DTLTMsg;
import business.db.MSG_TXT_DTLTMsgArray;
import business.db.TXT_TPTMsg;
import business.parts.NWZC042001PMsg;
import business.parts.NWZC042001_APMsgArray;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * Add Text API
 * 
 * <pre>
 * Date          Company     Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009    Fujitsu     H.Sakamoto  Create          N/A
 * 10/11/2016    Fujitsu     S.Iidaka    Update          S21_NA#13022
 *</pre>
 */
public class NWZC042001 extends S21ApiCommonBase {

    // Message IDs
    /** Please fill out/select the required field. */
    public static final String NWZM0163E = "NWZM0163E";

    /** Please fill out/select the required field. */
    public static final String NWZM0002E = "NWZM0002E";

    /** Please fill out/select the required field. */
    public static final String NWZM0426E = "NWZM0426E";

    /** Please fill out/select the required field. */
    public static final String NWZM0427E = "NWZM0427E";

    // /** Please fill out/select the required field. */
    // public static final String NWZM0428E = "NWZM0428E";

    /** The code you entered cannot be found in the master. */
    public static final String NWZM0429E = "NWZM0429E";

    /** The value you entered is incorrect. */
    public static final String NWZM0430E = "NWZM0430E";

    /** Debug level. * */
    private static final int DEBUG_LEVEL = 1;

    /** Message max size. */
    private static final int MESSAGE_MAX_SIZE = 4;

    /** Message min size. */
    private static final int MESSAGE_MIN_SIZE = 1;

    /** SSM Batch Client. */
    private S21SsmBatchClient ssmBatchClient;

    /**
     * Initialize
     */
    public NWZC042001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Add Text API
     * @param param NWZC042001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC042001PMsg param, final ONBATCH_TYPE onBatchType) {

        debugln("--- START ---");
        debugln("InputData=" + param);

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.doProcess(msgMap, onBatchType);
        msgMap.flush();

        debugln("OutputData=" + param);
        debugln("--- END ---");
    }

    /**
     * This method executes following processes.
     * 
     * <pre>
     * (1) Check Input Parameter
     * (2) Get MSG_TXT_DTL data
     * (3) Delete MSG_TXT_DTL data Logically
     * (4) Insert new MSG_TXT_DTL data
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        if (!checkInputParameter(msgMap)) {
            return;
        }

        NWZC042001PMsg inMsg = (NWZC042001PMsg) msgMap.getPmsg();

        MSG_TXT_DTLTMsg keyTMsg = new MSG_TXT_DTLTMsg();
        keyTMsg.setSQLID("004");
        keyTMsg.setConditionValue("glblCmpyCd01", inMsg.glblCmpyCd.getValue());
        keyTMsg.setConditionValue("cpoOrdNum01", inMsg.cpoOrdNum.getValue());
        MSG_TXT_DTLTMsgArray tMsgs = (MSG_TXT_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(keyTMsg);

        // Update
        if (tMsgs.getValidCount() > 0) {

            for (int i = 0; i < tMsgs.getValidCount(); i++) {
                MSG_TXT_DTLTMsg tMsg = (MSG_TXT_DTLTMsg) tMsgs.get(i);
                EZDTBLAccessor.logicalRemove(tMsg);
                debugln("RESULT :EZDTBLAccessor.logicalRemove =" + tMsg.getReturnCode());
            }

        }

        // Insert
        List<String> txtTpCdList = new LinkedList<String>();
        List<Integer> countList = new LinkedList<Integer>();

        for (int i = 0; i < inMsg.A.getValidCount(); i++) {

            // 2016/10/11 S21_NA#13022 Add Start
            if (!hasValue(inMsg.A.no(0).msgTxtInfoTxt)) {
                break;
            }
            // 2016/10/11 S21_NA#13022 Add End

            MSG_TXT_DTLTMsg tMsg = new MSG_TXT_DTLTMsg();
            setValue(tMsg.glblCmpyCd, inMsg.glblCmpyCd);
            setValue(tMsg.msgTxtDtlSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.MSG_TXT_DTL_SQ));
            setValue(tMsg.cpoOrdNum, inMsg.cpoOrdNum);
            setValue(tMsg.txtTpCd, inMsg.A.no(i).txtTpCd);
            setValue(tMsg.msgTxtInfoTxt, inMsg.A.no(i).msgTxtInfoTxt);

            int index = txtTpCdList.indexOf(tMsg.txtTpCd.getValue());
            if (index == -1) {
                txtTpCdList.add(tMsg.txtTpCd.getValue());
                index = txtTpCdList.indexOf(tMsg.txtTpCd.getValue());
                countList.add(index, Integer.valueOf(MESSAGE_MIN_SIZE));
            }

            Integer count = (Integer) countList.get(index);
            setValue(tMsg.txtSqNum, String.valueOf(count));
            EZDTBLAccessor.insert(tMsg);

            debugln("RESULT :EZDTBLAccessor.insert =" + tMsg.getReturnCode());

            count++;
            countList.add(index, count);

        }
    }

    /**
     * Check input parameter.
     * @param msgMap S21ApiMessageMap
     * @return True is success, false is fault
     */
    private boolean checkInputParameter(S21ApiMessageMap msgMap) {

        NWZC042001PMsg pMsg = (NWZC042001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0163E);
            return false;
        }
        if (!hasValue(pMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }
        if (pMsg.A == null || pMsg.A.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0426E);
            return false;
        }

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            if (!hasValue(pMsg.A.no(i).txtTpCd)) {
                msgMap.addXxMsgId(NWZM0427E);
                return false;
            }

            TXT_TPTMsg tMsg = new TXT_TPTMsg();
            setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(tMsg.txtTpCd, pMsg.A.no(i).txtTpCd);

            Integer ssmRes = (Integer) ssmBatchClient.queryObject("isTxtTpCd", tMsg);
            if (ssmRes.intValue() == 0) {
                msgMap.addXxMsgId(NWZM0429E);
                return false;
            }

        }

        if (!checkInputACount(pMsg.A)) {
            msgMap.addXxMsgId(NWZM0430E);
            return false;
        }

        return true;
    }

    /**
     * Check count of Text Type Code. True is that count is less than
     * three, the other is false.
     * @param aPMsg NWZC042001_APMsgArray
     * @return True:Success False:Fault
     */
    private boolean checkInputACount(NWZC042001_APMsgArray aPMsg) {

        List<String> txtTpCdList = new LinkedList<String>();
        List<Integer> countList = new LinkedList<Integer>();

        for (int i = 0; i < aPMsg.getValidCount(); i++) {

            String txtTpCd = (String) aPMsg.no(i).txtTpCd.getValue();

            int index = txtTpCdList.indexOf(txtTpCd);

            if (index == -1) {
                txtTpCdList.add(txtTpCd);
                index = txtTpCdList.indexOf(txtTpCd);
                countList.add(index, Integer.valueOf(MESSAGE_MIN_SIZE));
                continue;
            }

            Integer count = (Integer) countList.get(index);
            if (count >= MESSAGE_MAX_SIZE) {
                return false;
            }

            countList.set(index, Integer.valueOf(count + 1));
        }

        return true;
    }

    /**
     * Print debug message.
     * @param msg message
     */
    private void debugln(String msg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(DEBUG_LEVEL, msg, this);
        }
    }
}
