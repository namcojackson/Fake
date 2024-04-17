/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1700;

import static business.servlet.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.FUNCTION_INSERT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.IN_BOUND;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.MODE_EDIT;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NWAM8445E;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NWAM8446E;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NWAM8447E;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.NZZM0002I;
import static business.servlet.NWAL1700.constant.NWAL1700Constant.OUT_BOUND;

import java.util.ArrayList;
import java.util.HashMap;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.servlet.NWAL1700.common.NWAL1700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1700Scrn00_CMN_Save
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/05   Fujitsu         M.Suzuki        Update          NA#5919
 * 2016/04/05   Fujitsu         M.Suzuki        Update          NA#6367
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWAL1700Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        checkDateFromTo(scrnMsg);
        checkWorkFlow(scrnMsg);
        checkPrimary(scrnMsg);
        NWAL1700CommonLogic.addCheckItemHeader(scrnMsg);
        NWAL1700CommonLogic.addCheckItemWorkFlow(scrnMsg);
        NWAL1700CommonLogic.addCheckItemAccounting(scrnMsg);
        NWAL1700CommonLogic.addCheckItemDefaults(scrnMsg);
        NWAL1700CommonLogic.addCheckItemLineCategory(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    private void checkPrimary(NWAL1700BMsg scrnMsg) {
        ArrayList<Integer> outList = new ArrayList<Integer>();
        ArrayList<Integer> inList = new ArrayList<Integer>();
        ArrayList<String> categoryList = new ArrayList<String>();
        ArrayList<String> errCdList = new ArrayList<String>();
        // 2016/04/04 S21_NA#5919 MOD Start --------------
        HashMap<String, ArrayList<Integer>> errCdMap = new HashMap<String, ArrayList<Integer>>();
        // 2016/04/04 S21_NA#5919 MOD End --------------
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).primLineCatgFlg_A.getValue())) {
                String type = scrnMsg.A.no(i).dsOrdLineDrctnNm_A.getValue();
                if (OUT_BOUND.equals(type)) {
                    outList.add(i);
                } else if (IN_BOUND.equals(type)) {
                    inList.add(i);
                }
            }

            String categoryCd = scrnMsg.A.no(i).dsOrdLineCatgCd_A.getValue();
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            if (!categoryList.contains(categoryCd)) {
                categoryList.add(categoryCd);
                ArrayList<Integer> errIndexList = new ArrayList<Integer>();
                errIndexList.add(i);
                errCdMap.put(categoryCd, errIndexList);
            } else {
                if (!errCdList.contains(categoryCd)) {
                    errCdList.add(categoryCd);
                }
                ArrayList<Integer> errIndexList = errCdMap.get(categoryCd);
                errIndexList.add(i);

            }
            // 2016/04/04 S21_NA#5919 MOD End ----------
            checkFromTo(scrnMsg, i);
        }
        // 2016/04/04 S21_NA#5919 MOD Start ------------
        for (String errCd : errCdList) {
            ArrayList<Integer> errIndexList = errCdMap.get(errCd);
            for (int i : errIndexList) {
                scrnMsg.A.no(i).primLineCatgFlg_A.setErrorInfo(1, NWAM8447E);
            }
        }
        // 2016/04/04 S21_NA#5919 MOD End --------------

        if (outList.size() >= 2) {
            for (int i : outList) {
                scrnMsg.A.no(i).primLineCatgFlg_A.setErrorInfo(1, NWAM8447E);
            }
        }

        if (inList.size() >= 2) {
            for (int i : inList) {
                scrnMsg.A.no(i).primLineCatgFlg_A.setErrorInfo(1, NWAM8447E);
            }
        }
    }

    private void checkFromTo(NWAL1700BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A)) {

            if (ZYPDateUtil.compare(scrnMsg.A.no(i).effThruDt_A.getValue(), scrnMsg.A.no(i).effFromDt_A.getValue()) < 0) {
                scrnMsg.A.no(i).effFromDt_A.setErrorInfo(1, NWAM8445E);
                scrnMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM8445E);
            }
        }
    }

    private void checkWorkFlow(NWAL1700BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.vldApvlNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.vldApvlNodePrflCd)) {
                scrnMsg.vldApvlNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.vldApvlNodePrflCd.getNameForMessage() });
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.diChkNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.diChkNodePrflCd)) {
                scrnMsg.diChkNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.diChkNodePrflCd.getNameForMessage() });
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prftApvlNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prftApvlNodePrflCd)) {
                scrnMsg.prftApvlNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.prftApvlNodePrflCd.getNameForMessage() });
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.crApvlNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.crApvlNodePrflCd)) {
                scrnMsg.crApvlNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.crApvlNodePrflCd.getNameForMessage() });
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.assetNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.assetNodePrflCd)) {
                scrnMsg.assetNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.assetNodePrflCd.getNameForMessage() });
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.outOfWhNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.outOfWhNodePrflCd)) {
                scrnMsg.outOfWhNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.outOfWhNodePrflCd.getNameForMessage() });
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.splyAbuseNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.splyAbuseNodePrflCd)) {
                scrnMsg.splyAbuseNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.splyAbuseNodePrflCd.getNameForMessage() });
            }
        }

        // add start 2023/04/25 QC#61337
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manPrcNodeUsgFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.manPrcNodePrflCd)) {
                scrnMsg.manPrcNodePrflCd.setErrorInfo(1, NWAM8446E, new String[] {scrnMsg.manPrcNodePrflCd.getNameForMessage() });
            }
        }
        // add end 2023/04/25 QC#61337
    }

    private void checkDateFromTo(NWAL1700BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NWAM8445E);
                scrnMsg.effThruDt.setErrorInfo(1, NWAM8445E);
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = new NWAL1700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1700BMsg scrnMsg = (NWAL1700BMsg) bMsg;
        NWAL1700CMsg bizMsg = (NWAL1700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1700CommonLogic.addCheckItemHeader(scrnMsg);
        NWAL1700CommonLogic.addCheckItemAccounting(scrnMsg);
        NWAL1700CommonLogic.addCheckItemDefaults(scrnMsg);
        NWAL1700CommonLogic.addCheckItemLineCategory(scrnMsg);
        scrnMsg.putErrorScreen();

        if (NWAL1700CommonLogic.isFullUser(getUserProfileService())) {
            NWAL1700CommonLogic.setEditScreenFullandInsert(scrnMsg, FUNCTION_FULL, this);
        } else if (NWAL1700CommonLogic.isInsertUser(getUserProfileService())) {
            NWAL1700CommonLogic.setEditScreenFullandInsert(scrnMsg, FUNCTION_INSERT, this);
        } else {
            NWAL1700CommonLogic.setInitScreenRead(scrnMsg, this);
        }
        scrnMsg.xxModeCd.setValue(MODE_EDIT);
        // 2016/04/05 S21_NA#6367 Add Start --------------
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        // 2016/04/05 S21_NA#6367 Add Endt --------------
    }
}
