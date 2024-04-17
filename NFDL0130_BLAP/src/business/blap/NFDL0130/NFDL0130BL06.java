/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0130;

import static business.blap.NFDL0130.constant.NFDL0130Constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFDL0130.common.NFDL0130CommonLogic;
import business.db.CLT_WRK_ITEMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 * 2022/06/03   CITS            K.Suzuki        Update          QC#60145
 *</pre>
 */
public class NFDL0130BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0130Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0130Scrn00_CMN_Submit((NFDL0130CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFDL0130Scrn00_CMN_Submit(NFDL0130CMsg cMsg) {

        boolean checkResult = true;
        HashMap<String, Integer> cdCheck = new HashMap<String, Integer>();
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            String cltWrkItemCd = cMsg.A.no(num).cltWrkItemCd_A.getValue();
            if (cdCheck.containsKey(cltWrkItemCd)) {
                cMsg.A.no(num).cltWrkItemCd_A.setErrorInfo(1, NFDM0019E, new String[] {"Work Item Code" });
                if (cdCheck.get(cltWrkItemCd).intValue() != -1) {
                    cMsg.A.no(cdCheck.get(cltWrkItemCd).intValue()).cltWrkItemCd_A.setErrorInfo(1, NFDM0019E, new String[] {"Work Item Code" });
                }
                checkResult = false;
            } else {
                if (XX_TP_CD_UPD.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
                    cdCheck.put(cltWrkItemCd, Integer.valueOf(-1));
                } else {
                    cdCheck.put(cltWrkItemCd, Integer.valueOf(num));
                }
            }
        }

        if (checkResult == false) {
            return;
        }

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        for (int num = 0; num < cMsg.D.getValidCount(); num++) {
            NFDL0130_DCMsg dcMsg = cMsg.D.no(num);
            String cltStrgyRelnWrkItem = NFDL0130Query.getInstance().getCltStrgyRelnWrkItem(glblCmpyCd, dcMsg.cltWrkItemCd_D.getValue());
            if (cltStrgyRelnWrkItem != null) {
                cMsg.setMessageInfo(NFDM0020E);
                return;
            }
        }

        List<CLT_WRK_ITEMTMsg> deleteList = new ArrayList<CLT_WRK_ITEMTMsg>(cMsg.D.length());

        for (int num = 0; num < cMsg.D.getValidCount(); num++) {
            NFDL0130_DCMsg dcMsg = cMsg.D.no(num);
            CLT_WRK_ITEMTMsg tMsg = NFDL0130CommonLogic.getCltWrkItemForUpdateNoWait(glblCmpyCd, dcMsg.cltWrkItemCd_D.getValue());
            if (tMsg == null) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }
            if (!ZYPDateUtil.isSameTimeStamp(dcMsg.ezUpTime_D.getValue(), dcMsg.ezUpTimeZone_D.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }
            deleteList.add(tMsg);
        }

        for (CLT_WRK_ITEMTMsg tMsg : deleteList) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0021E, new String[] {"CLT_WRK_ITEM" });
                return;
            }
        }

        List<CLT_WRK_ITEMTMsg> insertList = new ArrayList<CLT_WRK_ITEMTMsg>(cMsg.A.length());
        List<CLT_WRK_ITEMTMsg> updateList = new ArrayList<CLT_WRK_ITEMTMsg>(cMsg.A.length());

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0130_ACMsg acMsg = cMsg.A.no(num);
            CLT_WRK_ITEMTMsg tMsg = NFDL0130CommonLogic.getCltWrkItemForUpdateNoWait(glblCmpyCd, acMsg.cltWrkItemCd_A.getValue());
            if (tMsg == null) {
                tMsg = new CLT_WRK_ITEMTMsg();
                NFDL0130CommonLogic.setTmsgValue(tMsg, acMsg, glblCmpyCd);
                insertList.add(tMsg);
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                NFDL0130CommonLogic.setTmsgValue(tMsg, acMsg, glblCmpyCd);
                updateList.add(tMsg);
            }
        }

        for (CLT_WRK_ITEMTMsg msg : updateList) {
            EZDTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0004E, new String[] {"CLT_WRK_ITEM" });
                return;
            }
        }

        for (CLT_WRK_ITEMTMsg msg : insertList) {
            EZDTBLAccessor.create(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0013E, new String[] {"CLT_WRK_ITEM" });
                return;
            }
        }

    }

}
