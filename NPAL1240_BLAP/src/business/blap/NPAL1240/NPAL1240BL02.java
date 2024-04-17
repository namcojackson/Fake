/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1240;

import static business.blap.NPAL1240.common.NPAL1240CommonLogic.setErrorInfo;
import static business.blap.NPAL1240.common.NPAL1240CommonLogic.setMessageInfo;
import static business.blap.NPAL1240.constant.NPAL1240Constant.ASL_QLFY_TP_CUSTOMER_SPECIFIC;
import static business.blap.NPAL1240.constant.NPAL1240Constant.CHECK_BOX_ITEM_NM;
import static business.blap.NPAL1240.constant.NPAL1240Constant.EVENT_NM_CMN_CLOSE;
import static business.blap.NPAL1240.constant.NPAL1240Constant.EVENT_NM_INIT;
import static business.blap.NPAL1240.constant.NPAL1240Constant.EVENT_NM_NPAL1240_NWAL1130;
import static business.blap.NPAL1240.constant.NPAL1240Constant.EVENT_NM_ON_CLICK_DELETE_ROW;
import static business.blap.NPAL1240.constant.NPAL1240Constant.EVENT_NM_ON_CLICK_INSERT_ROW;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_CD_NPAM0076E;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_CD_NPAM1191E;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_CD_NPAM1193E;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_CD_NZZM0002I;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_CD_NZZM0011E;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_PARAM_ASL_QLFY_REF_NUM;
import static business.blap.NPAL1240.constant.NPAL1240Constant.MESSAGE_PARAM_INPUT_PARAMS;
import static business.blap.NPAL1240.constant.NPAL1240Constant.RGTN_STS_READY_FOR_ORDER_TAKING;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASL_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Business ID : NPAL1240 Qualifier Setup
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1240CMsg bizMsg = (NPAL1240CMsg) cMsg;

            if (EVENT_NM_INIT.equals(screenAplID)) {
                doProcessInit(bizMsg);
            } else if (EVENT_NM_ON_CLICK_INSERT_ROW.equals(screenAplID)) {
                doProcessInsertRow(bizMsg);
            } else if (EVENT_NM_ON_CLICK_DELETE_ROW.equals(screenAplID)) {
                doProcessDeleteRow(bizMsg);
            } else if (EVENT_NM_NPAL1240_NWAL1130.equals(screenAplID)) {
                doProcessNPAL1240_NWAL1130(bizMsg);
            } else if (EVENT_NM_CMN_CLOSE.equals(screenAplID)) {
                doProcessClose(bizMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID :" + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Initialization.
     * </p>
     * @param cMsg bizMsg
     */
    private void doProcessInit(NPAL1240CMsg bizMsg) {

        // creates the pull-down lists.
        ZYPCodeDataUtil.createPulldownList(ASL_QLFY_TP.class, bizMsg.aslQlfyTpCd_L, bizMsg.aslQlfyTpDescTxt_L);

        // parameter check.
        if (!ZYPCommonFunc.hasValue(bizMsg.glblCmpyCd) && !ZYPCommonFunc.hasValue(bizMsg.xxMd)) {
            setMessageInfo(bizMsg, MESSAGE_CD_NPAM1193E, MESSAGE_PARAM_INPUT_PARAMS);
            return;
        }
    }

    /**
     * <p>
     * Runs the onclick event of "Insert Row" button.
     * </p>
     * @param bizMsg CMsg
     */
    private void doProcessInsertRow(NPAL1240CMsg bizMsg) {
        // checks if the row counts exceeds the maximum counts.
        if (bizMsg.A.getValidCount() >= bizMsg.A.length()) {
            setMessageInfo(bizMsg, MESSAGE_CD_NPAM1191E, bizMsg.A.length());
            return;
        }
        // adds the valid count.
        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);

        setMessageInfo(bizMsg, MESSAGE_CD_NZZM0002I);
    }

    /**
     * <p>
     * Runs the onclick event of "Delete Row" button.
     * </p>
     * @param bizMsg CMsg
     */
    private void doProcessDeleteRow(NPAL1240CMsg bizMsg) {

        // gets the selected row index.
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHECK_BOX_ITEM_NM, ZYPConstant.CHKBOX_ON_Y);
        // checks if at least one check box is selected.
        if (selectedRows.isEmpty()) {
            setMessageInfo(bizMsg, MESSAGE_CD_NZZM0011E);
            return;
        }
        // deletes the selected rows.
        ZYPTableUtil.deleteRows(bizMsg.A, selectedRows);

        setMessageInfo(bizMsg, MESSAGE_CD_NZZM0002I);
    }

    /**
     * <p>
     * Runs the onclick event of "Ref#" button.
     * </p>
     * @param cMsg bizMsg
     */
    private void doProcessNPAL1240_NWAL1130(NPAL1240CMsg bizMsg) {
        // Do nothing.
    }

    /**
     * <p>
     * Runs the onclick event of "CLOSE" button.
     * </p>
     * @param cMsg bizMsg
     */
    private void doProcessClose(NPAL1240CMsg bizMsg) {
        // master check.
        for (int index = 0; index < bizMsg.A.getValidCount(); index++) {
            S21SsmEZDResult result = null;
            if (ASL_QLFY_TP_CUSTOMER_SPECIFIC.equals(bizMsg.A.no(index).aslQlfyTpCd_A.getValue())) {
                // searches by Ship To Customer Code.
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", bizMsg.glblCmpyCd);
                params.put("rgtnStsCd", RGTN_STS_READY_FOR_ORDER_TAKING);
                params.put("shipToCustCd", bizMsg.A.no(index).aslQlfyRefCd_A);
                result = NPAL1240Query.getInstance().searchByShipToCustCd(params);
            } else {
                // searches by Big Deal#.
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("glblCmpyCd", bizMsg.glblCmpyCd);
                params.put("rgtnStsCd", RGTN_STS_READY_FOR_ORDER_TAKING);
                params.put("shipToCustCd", bizMsg.A.no(index).aslQlfyRefCd_A);
                params.put("bigDealNum", bizMsg.A.no(index).aslQlfyRefCd_A);
                result = NPAL1240Query.getInstance().searchByBigDealNum(params);
            }

            // if the result is not found, sets the error message.
            if (result.isCodeNotFound()) {
                setErrorInfo(bizMsg.A.no(index).aslQlfyRefCd_A, MESSAGE_CD_NPAM0076E, MESSAGE_PARAM_ASL_QLFY_REF_NUM);
            }
        }
    }
}
