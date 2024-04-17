/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2570.NMAL2570CMsg;
import business.servlet.NMAL2570.common.NMAL2570CommonLogic;
import business.servlet.NMAL2570.constant.NMAL2570Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/03   Fujitsu         C.Tanaka        Create          QC#4551
 * 2016/04/19   SRAA            Y.Chen          Update          QC#6919
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public class NMAL2570_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2570CMsg bizMsg = new NMAL2570CMsg();
        bizMsg.setBusinessID(NMAL2570Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2570Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;
        NMAL2570CMsg bizMsg = (NMAL2570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2570CommonLogic.initialControlScreen(this, scrnMsg);

        S21TableColorController tblColor = new S21TableColorController(NMAL2570Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        // QC#7781
        if (NMAL2570Constant.MULT_SEL_MODE_CD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
        
        S21SortColumnIMGController.clearIMG(NMAL2570Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.setFocusItem(scrnMsg.xxPsnNm_H1);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        // Header
        scrnMsg.xxPsnNm_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_RESOURCE_NAME);
        scrnMsg.psnCd_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_EMPLOYEE_ID);
        scrnMsg.jobTtlCd_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_JOB_CODE);
        scrnMsg.psnNum_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_RESOURCE_NUMBER);
        scrnMsg.orgFuncRoleTpCd_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_ROLE_NAME);
        scrnMsg.orgNm_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_TERRITORY_NAME);
        scrnMsg.effFromDt_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_START_DATE);
        scrnMsg.effThruDt_H1.setNameForMessage(NMAL2570Constant.NAME_FOR_MESSAGE_END_DATE);
    }

    private void setParams(Object[] params, NMAL2570BMsg scrnMsg) {

        // Input Parameter.
        // Resource#
        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_H1, param0);
        }

        // Employee ID
        EZDBStringItem param1 = (EZDBStringItem) params[1];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H1, param1);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, ZYPConstant.FLG_OFF_N);
// QC#6919
//        if (params.length == 3) {
//            EZDBStringItem param2 = (EZDBStringItem) params[2];
//            if (ZYPCommonFunc.hasValue(param2) && ZYPConstant.FLG_ON_Y.equals(param2.getValue())) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, ZYPConstant.FLG_ON_Y);
//            }
//        }
        // Show Sales Role assginment Resource
        if (params.length >= 3) {
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            if (ZYPCommonFunc.hasValue(param2) && ZYPConstant.FLG_ON_Y.equals(param2.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, ZYPConstant.FLG_ON_Y);
            }
        }
        
        // Resource Name
        if (params.length >= 4) {
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            if (ZYPCommonFunc.hasValue(param3)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H1, param3.getValue());
            }
        }
        
        // Role Name
        if (params.length >= 5) {
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            if (ZYPCommonFunc.hasValue(param4)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgFuncRoleTpCd_H1, param4.getValue());
            }
        }

        // Territory Name
        if (params.length >= 6) {
            EZDBStringItem param5 = (EZDBStringItem) params[5];
            if (ZYPCommonFunc.hasValue(param5)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, param5.getValue());
            }
        }
        
        // QC#7781
        // Mode
        if (params.length >= 7) {
            EZDBStringItem param = (EZDBStringItem) params[6];
            if (ZYPCommonFunc.hasValue(param)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_H1, param.getValue());
            }
        }
        
        if (params.length >= 8) {
            EZDBStringItem param = (EZDBStringItem) params[7];
            if (ZYPCommonFunc.hasValue(param) && ZYPConstant.CHKBOX_ON_Y.equals(param.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        
        // Selected List
        if (params.length >= 9) {
            // get distinct values
            List<String> list = new ArrayList<String>();
            EZDBStringItem[] param = (EZDBStringItem[]) params[8];
            for (int i = 0; i < param.length; i++) {
                String psnCd = param[i].getValue();
                if (ZYPCommonFunc.hasValue(psnCd)) {
                    if(!list.contains(psnCd)){
                        list.add(psnCd);
                    }
                }
            }
            // set values to bottom list
            for (int i = 0; i < list.size(); i++) {
                String psnCd = list.get(i);
                if (i < scrnMsg.B.length()) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).psnCd_B1, psnCd);
                    scrnMsg.B.setValidCount(i + 1);
                } else {
                    break;
                }
            }
        }
    }

}
