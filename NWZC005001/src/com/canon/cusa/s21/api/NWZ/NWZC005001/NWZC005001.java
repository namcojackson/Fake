/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/24/2009   Fujitsu         K.Kato          Create          N/A
 * 10/26/2009   Fujitsu         S.Sugino        Update          N/A
 * 11/18/2009   Fujitsu         N.Mitsuishi     Update          N/A
 * 12/11/2009   Fujitsu         S.Sugino        Update          N/A
 * 02/08/2010   Fujitsu         T.Nagase        Update          N/A (Dealer profile)
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC005001;

import static com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ContractInfo.chkContractInfo;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDDebugOutput.isDebug;

import java.util.List;

import parts.common.EZDDebugOutput;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.HLDTMsg;
import business.db.HLD_RSNTMsg;
import business.db.LB_REQ_INFOTMsg;
import business.db.MDSETMsg;
import business.db.VND_DROP_PO_ATTRBTMsg;
import business.db.VND_DROP_PO_ATTRBTMsgArray;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXHldRsnTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 * Third Party Product Hold API
 */
public class NWZC005001 extends S21ApiCommonBase implements HLD_RSN {

    public static final String NWZM0001E = "NWZM0001E";
    public static final String NWZM0002E = "NWZM0002E";
    public static final String NWZM0003E = "NWZM0003E";
    public static final String NWZM0004E = "NWZM0004E";
    public static final String NWZM0077E = "NWZM0077E";
    
    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;
    
    private static final String SET_ITEM_PARENT_NUM = "000";
    
    private S21ApiMessageIdMgr msgMgr;

    public NWZC005001() {
        super();
    }

    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        this.msgMgr = new S21ApiMessageIdMgr();
        
        NWXC005001PMsg pMsg = param.getInputPMsg();

        if (!checkInput(pMsg)) {
            updateMessage(pMsg, msgMgr);
            return;
        }

        if (SET_ITEM_PARENT_NUM.equals(pMsg.cpoDtlLineSubNum_I.getValue())) {
            debug("Parents of the set are off the subject.");
            return;
        }

        CPO_DTLTMsg cdTMsg = param.getCdTMsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        
        final MDSETMsg mdseMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, cdTMsg.mdseCd.getValue());
        if (mdseMsg == null) {
            msgMgr.addXxMsgId(NWZM0077E, pMsg);
            updateMessage(pMsg, msgMgr);
            return;
        }

        if (!hasValue(mdseMsg.vndDropPoGrpCd)) {
            debug("There is no value of vndDropPoGrpCd.");
            return;
        }

        final VND_DROP_PO_ATTRBTMsgArray vndDropPoAttrbMsgArray = findVndDropPoAttrbQty(pMsg, mdseMsg);
        if (vndDropPoAttrbMsgArray.getValidCount() == 0) {
            debug("There are not data VND_DROP_PO_ATTRB");
            return;
        }

        if (findLbReqInfoQty(pMsg, cdTMsg, vndDropPoAttrbMsgArray)) {

            if (existsHld(pMsg, cdTMsg)) {
                return;
            }

            final HLD_RSNTMsg hldRsnTMsg = NWXHldRsnTMsgThreadLocalCache.getInstance().get(glblCmpyCd, THIRD_PARTY_SHIPPING_LAVEL);
            if (Y.equals(hldRsnTMsg.exReqFlg.getValue())) {
                if (findCustExToOrdRstQty(pMsg, param.getCTMsg(), cdTMsg, mdseMsg)) {
                    return;
                }
            }

            // HLD
            setValue(pMsg.cpoOrdNum_O,        cdTMsg.cpoOrdNum);
            setValue(pMsg.cpoDtlLineNum_O,    cdTMsg.cpoDtlLineNum);
            setValue(pMsg.cpoDtlLineSubNum_O, cdTMsg.cpoDtlLineSubNum);
            setValue(pMsg.hldRsnCd,           THIRD_PARTY_SHIPPING_LAVEL);
        }
    }

    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    private boolean checkInput(final NWXC005001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMgr.addXxMsgId(NWZM0001E, pMsg);
            return false;
        }

        if (!hasValue(pMsg.cpoOrdNum_I)) {
            msgMgr.addXxMsgId(NWZM0002E, pMsg);
            return false;
        }

        if (!hasValue(pMsg.cpoDtlLineNum_I)) {
            msgMgr.addXxMsgId(NWZM0003E, pMsg);
            return false;
        }

        if (!hasValue(pMsg.cpoDtlLineSubNum_I)) {
            msgMgr.addXxMsgId(NWZM0004E, pMsg);
            return false;
        }

        return true;
    }

    private void debug(String str) {
        if (isDebug()) {
            EZDDebugOutput.println(1, str, this);
        }
    }

    private static boolean existsHld(NWXC005001PMsg pMsg, CPO_DTLTMsg cdTMsg) {

        final HLDTMsg hldTMsg = new HLDTMsg();
        hldTMsg.setSQLID("015");
        hldTMsg.setConditionValue("glblCmpyCd01",       pMsg.glblCmpyCd.getValue());
        hldTMsg.setConditionValue("cpoOrdNum01",        cdTMsg.cpoOrdNum.getValue());
        hldTMsg.setConditionValue("cpoDtlLineNum01",    cdTMsg.cpoDtlLineNum.getValue());
        hldTMsg.setConditionValue("cpoDtlLineSubNum01", cdTMsg.cpoDtlLineSubNum.getValue());
        hldTMsg.setConditionValue("hldRsnCd01",         THIRD_PARTY_SHIPPING_LAVEL);
        hldTMsg.setConditionValue("relFlg01",           N);

        return S21ApiTBLAccessor.count(hldTMsg) > 0;
    }

    private static boolean findCustExToOrdRstQty(NWXC005001PMsg pMsg, CPOTMsg cTMsg, CPO_DTLTMsg cdTMsg, MDSETMsg mdseMsg) {

        return chkContractInfo(pMsg.glblCmpyCd.getValue(), mdseMsg.mdseCd.getValue(), cdTMsg.shipToCustCd.getValue(), cdTMsg.shipToCustCd.getValue(), cTMsg.billToCustCd.getValue(), THIRD_PARTY_SHIPPING_LAVEL, null);
    }

    private static boolean findLbReqInfoQty(NWXC005001PMsg pMsg, CPO_DTLTMsg cdTMsg, VND_DROP_PO_ATTRBTMsgArray vndDropPoAttrbMsgArray) {

        boolean holdFlg = false;

        for (int i = 0; i < vndDropPoAttrbMsgArray.getValidCount(); i++) {

            final VND_DROP_PO_ATTRBTMsg vndDropPoAttrbMsg = vndDropPoAttrbMsgArray.no(i);

            LB_REQ_INFOTMsg lbReqInfoTMsg = new LB_REQ_INFOTMsg();
            setValue(lbReqInfoTMsg.glblCmpyCd,          pMsg.glblCmpyCd);
            setValue(lbReqInfoTMsg.cpoOrdNum,           cdTMsg.cpoOrdNum);
            setValue(lbReqInfoTMsg.cpoDtlLineNum,       cdTMsg.cpoDtlLineNum);
            setValue(lbReqInfoTMsg.cpoDtlLineSubNum,    cdTMsg.cpoDtlLineSubNum);
            setValue(lbReqInfoTMsg.vndDropPoGrpCd,      vndDropPoAttrbMsg.vndDropPoGrpCd);
            setValue(lbReqInfoTMsg.vndDropPoGrpLineNum, vndDropPoAttrbMsg.vndDropPoGrpLineNum);

            lbReqInfoTMsg= (LB_REQ_INFOTMsg) S21CacheTBLAccessor.findByKey(lbReqInfoTMsg);

            if (lbReqInfoTMsg == null) {
                holdFlg = true;
                break;
            }

            if (!hasValue(lbReqInfoTMsg.lbReqInfoTxt.getValue())) {
                holdFlg = true;
                break;
            }
        }

        return holdFlg;
    }

    private static VND_DROP_PO_ATTRBTMsgArray findVndDropPoAttrbQty(NWXC005001PMsg pMsg, MDSETMsg mdseMsg) {

        final VND_DROP_PO_ATTRBTMsg inMsg = new VND_DROP_PO_ATTRBTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01",           pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("vndDropPoGrpCd01",       mdseMsg.vndDropPoGrpCd.getValue());
        inMsg.setConditionValue("vndDropPoAttrbMndFlg01", Y);

        return (VND_DROP_PO_ATTRBTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

}
