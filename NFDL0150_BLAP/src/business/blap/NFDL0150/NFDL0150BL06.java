/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0150;

import static business.blap.NFDL0150.constant.NFDL0150Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0150.common.NFDL0150CommonLogic;
import business.db.CLT_STRGYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0150BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0150Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0150Scrn00_CMN_Submit((NFDL0150CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NFDL0150Scrn00_CMN_Submit(NFDL0150CMsg cMsg) {

        boolean checkResult = true;
        HashMap<String, Integer> cdCheck = new HashMap<String, Integer>();
        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            String cltStrgyCd = cMsg.A.no(num).cltStrgyCd_A.getValue();
            if (cdCheck.containsKey(cltStrgyCd)) {
                cMsg.A.no(num).cltStrgyCd_A.setErrorInfo(1, NFDM0019E, new String[] {"Code" });
                if (cdCheck.get(cltStrgyCd).intValue() != -1) {
                    cMsg.A.no(cdCheck.get(cltStrgyCd).intValue()).cltStrgyCd_A.setErrorInfo(1, NFDM0019E, new String[] {"Code" });
                }
                checkResult = false;
            } else {
                if (XX_TP_CD_UPD.equals(cMsg.A.no(num).xxTpCd_A.getValue())) {
                    cdCheck.put(cltStrgyCd, Integer.valueOf(-1));
                } else {
                    cdCheck.put(cltStrgyCd, Integer.valueOf(num));
                }
            }
        }

        if (checkResult == false) {
            return;
        }

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        for (int num = 0; num < cMsg.D.getValidCount(); num++) {
            NFDL0150_DCMsg dcMsg = cMsg.D.no(num);
            String cltStrgyRelnCustTp = NFDL0150Query.getInstance().getCltStrgyRelnCustTp(glblCmpyCd, dcMsg.cltStrgyCd_D.getValue());
            if (cltStrgyRelnCustTp != null) {
                cMsg.setMessageInfo(NFDM0020E);
                return;
            }
            String cltStrgyRelnWrkItem = NFDL0150Query.getInstance().getCltStrgyRelnWrkItem(glblCmpyCd, dcMsg.cltStrgyCd_D.getValue());
            if (cltStrgyRelnWrkItem != null) {
                cMsg.setMessageInfo(NFDM0020E);
                return;
            }
        }

        List<CLT_STRGYTMsg> deleteList = new ArrayList<CLT_STRGYTMsg>(cMsg.D.length());

        for (int num = 0; num < cMsg.D.getValidCount(); num++) {
            NFDL0150_DCMsg dcMsg = cMsg.D.no(num);
            CLT_STRGYTMsg tMsg = NFDL0150CommonLogic.getCltStrgy(glblCmpyCd, dcMsg.cltStrgyCd_D.getValue());
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

        for (CLT_STRGYTMsg tMsg : deleteList) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0021E, new String[] {"CLT_STRGY" });
                return;
            }
        }

        List<CLT_STRGYTMsg> insertList = new ArrayList<CLT_STRGYTMsg>(cMsg.A.length());
        List<CLT_STRGYTMsg> updateList = new ArrayList<CLT_STRGYTMsg>(cMsg.A.length());

        for (int num = 0; num < cMsg.A.getValidCount(); num++) {
            NFDL0150_ACMsg acMsg = cMsg.A.no(num);
            CLT_STRGYTMsg tMsg = NFDL0150CommonLogic.getCltStrgy(glblCmpyCd, acMsg.cltStrgyCd_A.getValue());
            if (tMsg == null) {
                tMsg = new CLT_STRGYTMsg();
                setValue(tMsg.glblCmpyCd, glblCmpyCd);
                setValue(tMsg.cltStrgyCd, acMsg.cltStrgyCd_A);
                setValue(tMsg.cltStrgyNm, acMsg.cltStrgyNm_A);
                insertList.add(tMsg);
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                setValue(tMsg.cltStrgyNm, acMsg.cltStrgyNm_A);
                updateList.add(tMsg);
            }
        }

        for (CLT_STRGYTMsg msg : updateList) {
            EZDTBLAccessor.update(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0004E, new String[] {"CLT_STRGY" });
                return;
            }
        }

        for (CLT_STRGYTMsg msg : insertList) {
            EZDTBLAccessor.create(msg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(msg.getReturnCode())) {
                cMsg.setMessageInfo(NFDM0013E, new String[] {"CLT_STRGY" });
                return;
            }
        }
    }

}
