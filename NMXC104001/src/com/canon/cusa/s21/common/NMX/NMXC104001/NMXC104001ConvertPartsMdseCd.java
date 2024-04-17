/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC104001;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.MDSETMsg;
import business.db.PRT_MDSE_CONVTMsg;
import business.db.PRT_MDSE_CONVTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 * <pre>
 * Convert Parts MDSE Code
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/09/25   Hitachi         K.Kishimoto     Create          MEX-LC019
 * 2013/09/30   Hitachi         T.Tomita        Update          MEX-LC019
 * 2013/10/24   Fujitsu         N.Sugiura       Update          WDS Defect#2280
 * 2016/04/18   CITS            R.Shimamoto     Update          V1.1(QC#7083)
 *</pre>
 */
public class NMXC104001ConvertPartsMdseCd {

    /** Mode : Convert to MDSE Code from Parts Code */
    public static final String MODE_PARTS = "11";

    /** Mode : Convert to MDSE Code from Parts Code and Size */
    public static final String MODE_PARTS_AND_SIZE = "12";

    /** Mode : Convert to Parts Code and Size from MDSE Code */
    public static final String MODE_MDSE = "21";

    // 2013/10/24 ADD START WDS Defect#2280
    /** Mode : Convert to Parts Convert Code from Parts Code */
    public static final String MODE_PARTS_CONV = "31";

    // 2013/10/24 ADD END WDS Defect#2280

    /** Error Message ID : Mode is invalid */
    public static final String MSG_ID_INVALID_MODE = "NMZM0024E";

    /** Error Message ID : MDSE Code does not exist in MDSE Master. */
    public static final String MSG_ID_NOT_EXIST_MDSE = "NMZM0023E";

    /** Default Size */
    private static final String DEFAULT_SIZE = "000";

    /** Hyphen */
    private static final String HYPHEN = "-";

    // 2013/10/24 ADD START WDS Defect#2280
    /**
     * VAR_CHAR_CONST_NM(NMAL0010_CHECK_EXPORT)
     */
    private static final String VAR_CHAR_CONST_NM_NMAL0010_CHECK_EXPORT = "NMAL0010_CHECK_EXPORT";

    /**
     * For Domestic: Import
     */
    private static final String NMAL0010_CHECK_EXPORT_FOR_DOMESTIC = "DOMESTIC";

    /**
     * For Domestic: Import
     */
    private static final String NMAL0010_CHECK_EXPORT_FOR_IMPORT = "IMPORT";

    // 2013/10/24 ADD START WDS Defect#2280

    // 2016/04/19 ADD START R.Shimamoto V1.1
    /**
     * DS_COND_CONST_GRP_ID(NMXC1040_OCE_VND_CD)
     */
    private static final String DS_COND_CONST_GRP_ID_NMXC1040_OCE_VND_CD = "NMXC1040_OCE_VND_CD";

    /** MDSE Code padding */
    private static final String MDSE_CD_PADDING = "0";

    // 2016/04/19 ADD START R.Shimamoto V1.1

    /**
     * Convert PartsMdseCd
     * @param bean NMXC104001ConvertPartsMdseCdBean
     */
    public static void convertPartsMdseCd(NMXC104001ConvertPartsMdseCdBean bean) {
        if (MODE_PARTS.equals(bean.getMode())) {
            checkMdseCdFromParts(bean);
        } else if (MODE_PARTS_AND_SIZE.equals(bean.getMode())) {
            checkMdseCdFromPartsAndSize(bean);
        } else if (MODE_MDSE.equals(bean.getMode())) {
            splitPartsAndSize(bean);
            // 2013/10/24 ADD START WDS Defect#2280
        } else if (MODE_PARTS_CONV.equals(bean.getMode())) {
            checkMdseCdFromPartsConv(bean);
            // 2013/10/24 ADD END WDS Defect#2280
        } else {
            bean.setErrCd(MSG_ID_INVALID_MODE);
        }
    }

    private static void checkMdseCdFromParts(NMXC104001ConvertPartsMdseCdBean bean) {
        String targetMdseCd = bean.getXtrnlSysPrtCd();
        if (targetMdseCd.length() > 16) {
            bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
            return;
        }

        if (getMdseCd(bean, targetMdseCd)) {
            return;
        }

        if (targetMdseCd.length() == 12) {
            targetMdseCd = targetMdseCd + DEFAULT_SIZE;
            if (getMdseCd(bean, targetMdseCd)) {
                return;
            }
        } else if (targetMdseCd.length() == 16 && HYPHEN.equals(targetMdseCd.substring(12, 13))) {
            targetMdseCd = targetMdseCd.substring(0, 12) + targetMdseCd.substring(13);
            if (getMdseCd(bean, targetMdseCd)) {
                return;
            }
        }
        bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
    }

    private static void checkMdseCdFromPartsAndSize(NMXC104001ConvertPartsMdseCdBean bean) {
        String targetMdseCd = bean.getXtrnlSysPrtCd();
        if (DEFAULT_SIZE.equals(bean.getXtrnlSysSize())) {
            checkMdseCdFromParts(bean);
            return;
        }

        targetMdseCd = targetMdseCd + bean.getXtrnlSysSize();
        if (targetMdseCd.length() > 16) {
            bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
            return;
        }

        if (getMdseCd(bean, targetMdseCd)) {
            return;
        }
        bean.setErrCd(MSG_ID_NOT_EXIST_MDSE);
    }

    private static boolean getMdseCd(NMXC104001ConvertPartsMdseCdBean bean, String targetMdseCd) {
        String rtnMdseCd = selectMdseFindByKey(bean.getGlblCmpyCd(), targetMdseCd);
        if (ZYPCommonFunc.hasValue(rtnMdseCd)) {
            bean.setMdseCd(rtnMdseCd);
            return true;
        }
        return false;
    }

    private static String selectMdseFindByKey(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);

        MDSETMsg result = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
        String rtnMdseCd = null;
        if (result != null) {
            rtnMdseCd = result.mdseCd.getValue();
        }
        return rtnMdseCd;
    }

    private static void splitPartsAndSize(NMXC104001ConvertPartsMdseCdBean bean) {
        // 2016/04/19 MOD START R.Shimamoto V1.1
        DS_COND_CONSTTMsgArray resultList = selectVenderCodeList(bean.getGlblCmpyCd());

        boolean ocePartsFlg = false;
        for (int i = 0; i < resultList.length(); i++) {
            if (ZYPCommonFunc.hasValue(resultList.no(i).dsCondConstValTxt_01)) {
                if (resultList.no(i).dsCondConstValTxt_01.getValue().equals(bean.getCusaVndCd())) {
                    ocePartsFlg = true;
                    break;
                }
            }
        }

        if (ocePartsFlg) {
            // Oce Parts
            if (bean.getMdseCd().length() <= 10) {
                bean.setXtrnlSysPrtCd((ZYPCommonFunc.leftPad(bean.getMdseCd(), 10, MDSE_CD_PADDING)));
            } else if (bean.getMdseCd().length() > 12) {
                bean.setXtrnlSysPrtCd(ZYPCommonFunc.subByteString(bean.getMdseCd(), 12));
            } else {
                bean.setXtrnlSysPrtCd(bean.getMdseCd());
            }
            bean.setXtrnlSysSize(DEFAULT_SIZE);

        } else {
            // Other
            if (bean.getMdseCd().length() <= 12) {
                bean.setXtrnlSysPrtCd(bean.getMdseCd());
                bean.setXtrnlSysSize(DEFAULT_SIZE);
                return;
            }
            bean.setXtrnlSysPrtCd(bean.getMdseCd().substring(0, 12));
            bean.setXtrnlSysSize(formatSize(bean.getMdseCd().substring(12)));
        }
        // 2016/04/19 MOD END R.Shimamoto V1.1

    }

    private static String formatSize(String size) {
        String rtnSize = size;
        if (HYPHEN.equals(rtnSize.substring(0, 1))) {
            rtnSize = rtnSize.substring(1);
        }

        if (rtnSize.length() > 3) {
            rtnSize = rtnSize.substring(0, 3);
        }

        if (rtnSize.length() == 0) {
            rtnSize = DEFAULT_SIZE;
        }
        return rtnSize;
    }

    // 2013/10/24 ADD START WDS Defect#2280
    /**
     * Check PartsCode From PRT_MDSE_CONV.
     * @param bean NMXC104001ConvertPartsMdseCdBean
     * @return true/false
     */
    private static void checkMdseCdFromPartsConv(NMXC104001ConvertPartsMdseCdBean bean) {
        String targetMdseCd = bean.getMdseCd();
        if (targetMdseCd.length() > 16) {
            return;
        }

        if (getPrtMdseConv(bean, targetMdseCd)) {
            return;
        }

        bean.setMdseCd(null);

    }

    private static boolean getPrtMdseConv(NMXC104001ConvertPartsMdseCdBean bean, String targetMdseCd) {
        String rtnMdseCd = selectPrtMdseConvFindByKey(bean.getGlblCmpyCd(), targetMdseCd);
        if (ZYPCommonFunc.hasValue(rtnMdseCd)) {
            bean.setMdseCd(rtnMdseCd);
            return true;
        }
        return false;
    }

    private static String selectPrtMdseConvFindByKey(String glblCmpyCd, String mdseCd) {
        String importFlg = checkExportFlg(glblCmpyCd);
        PRT_MDSE_CONVTMsg inMsg = new PRT_MDSE_CONVTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);
        inMsg.setConditionValue("exptReqFlg01", importFlg);
        inMsg.setSQLID("001");

        PRT_MDSE_CONVTMsgArray resultPrtMdseConv = (PRT_MDSE_CONVTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        String rtnMdseCd = null;
        if (resultPrtMdseConv == null || resultPrtMdseConv.length() <= 0) {
            rtnMdseCd = null;

        } else {
            rtnMdseCd = resultPrtMdseConv.no(0).vndMdseCd.getValue();
        }

        return rtnMdseCd;
    }

    /**
     * checkExportFlg.
     * @param glblCmpyCd String
     * @return importFlg
     */
    public static String checkExportFlg(String glblCmpyCd) {

        String importCode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_NMAL0010_CHECK_EXPORT, glblCmpyCd);
        String importFlg = new String();
        if (NMAL0010_CHECK_EXPORT_FOR_IMPORT.equals(importCode)) {
            importFlg = ZYPConstant.FLG_ON_Y;
        } else if (NMAL0010_CHECK_EXPORT_FOR_DOMESTIC.equals(importCode)) {
            importFlg = ZYPConstant.FLG_OFF_N;
        }

        return importFlg;
    }

    // 2013/10/24 ADD END WDS Defect#2280

    // 2016/04/18 ADD START R.Shimamoto V1.1
    /**
     * selectVenderCodeList
     */
    private static DS_COND_CONSTTMsgArray selectVenderCodeList(String glblCmpyCd) {

        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", DS_COND_CONST_GRP_ID_NMXC1040_OCE_VND_CD);
        inMsg.setSQLID("002");

        DS_COND_CONSTTMsgArray resultMsgArray = (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return resultMsgArray;
    }

    // 2016/04/18 ADD END R.Shimamoto V1.1
}
