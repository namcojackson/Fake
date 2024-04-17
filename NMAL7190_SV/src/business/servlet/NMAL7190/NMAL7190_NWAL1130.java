/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_BTN_FLG_FROM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7190_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7190_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        int index = getButtonSelectNumber();

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                }
            } else if (PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                }
            } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(2).xxComnScrColValTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(2).xxComnScrColValTxt);
                }
            } else if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(3).xxComnScrColValTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(3).xxComnScrColValTxt);
                }
            } else if (PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                }
            } else if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                }
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                }
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                }
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                }
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                }
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(1).xxComnScrColValTxt);
                    }
                } else {
                    if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpFromTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpThruTxt_A1, scrnMsg.R.no(0).xxComnScrColValTxt);
                    }
                }
            }
        }

        NMAL7190CommonLogic.controlScreen(this, scrnMsg);
        if (POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcGrpFromTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcGrpThruTxt_A1);
        }
    }
}
