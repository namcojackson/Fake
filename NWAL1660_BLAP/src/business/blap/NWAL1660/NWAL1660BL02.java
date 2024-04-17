/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1660;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1660.common.NWAL1660CommonLogic;
import business.blap.NWAL1660.constant.NWAL1660Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1660BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 * 2017/08/24   Fujitsu         H.Ikeda         Update          QC#20655
 * 2019/07/29   Fujitsu         R.Nakamura      Update          S21_NA#51742
 *</pre>
 */
public class NWAL1660BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1660CMsg bizMsg = (NWAL1660CMsg) cMsg;
            NWAL1660SMsg glblMsg = (NWAL1660SMsg) sMsg;

            if ("NWAL1660_INIT".equals(screenAplID)) {
                doProcess_NWAL1660_INIT(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_AddCharges".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_AddCharges(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_AddSellPrice".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_AddSellPrice(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_Calculate".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_Calculate(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_DeleteCharges".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_DeleteCharges(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_DeleteSellPrice".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_DeleteSellPrice(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_Reset".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_Reset(bizMsg, glblMsg);

            } else if ("NWAL1660Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL1660Scrn00_CMN_Close(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1660Scrn00_CMN_Close(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        // Mod Start 2019/07/29 QC#51742
//        NWAL1660CommonLogic.checkForClose(getGlobalCompanyCode(), bizMsg);
        if (NWAL1660CommonLogic.checkForClose(getGlobalCompanyCode(), bizMsg)) {
            return;
        }
        // Mod End 2019/07/29 QC#51742
        
        if (bizMsg.getMessageInfo() == null) {
            // 2017/08/29 S21_NA#20655 MOD Start
            // QC#22965 2018/04/11 Add Start
            // List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "prcCondManDelFlg_B", ZYPConstant.CHKBOX_ON_Y);
            //List<BigDecimal> delIdRows = new ArrayList<BigDecimal>();
            //for (int i : selectRows) {
            //    NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
            //    if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.ovrdFlg_B.getValue())) {
            //        delIdRows.add(bBizMsg.specCondPrcPk_B.getValue());
            //    }
            //}
            // NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_ON_Y);
            //NWAL1660CommonLogic.getPriceInfoDel(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_ON_Y, delIdRows);
            // 2017/08/29 S21_NA#20655 MOD End
            if (NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) {
                NWAL1660CommonLogic.setBMsgToCalcPriceForHeader(getGlobalCompanyCode(), bizMsg);
            } else {
                List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "prcCondManDelFlg_A", ZYPConstant.CHKBOX_ON_Y);
                List<Integer> delRows = new ArrayList<Integer>();
                List<Integer> delLMsgRows = new ArrayList<Integer>();
                for (int i : selectRows) {
                    NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
                    int l = NWAL1660CommonLogic.getLBizMsgIdx(bizMsg.L, aBizMsg.specCondPrcPk_A.getValue());
                    if (l < 0) {
                        continue;
                    }
                    NWAL1660_LCMsg lBizMsg = bizMsg.L.no(l);
                    if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManAddFlg_PL.getValue())) {
                        delLMsgRows.add(l);
                    } else {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                    }
                    delRows.add(i);
                }

                if (delRows.size() > 0) {
                    ZYPTableUtil.deleteRows(bizMsg.A, delRows);
                }
                if (delLMsgRows.size() > 0) {
                    ZYPTableUtil.deleteRows(bizMsg.L, delLMsgRows);
                }
                delRows.clear();
                delLMsgRows.clear();

                selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "prcCondManDelFlg_B", ZYPConstant.CHKBOX_ON_Y);
                for (int i : selectRows) {
                    NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
                    int l = NWAL1660CommonLogic.getLBizMsgIdx(bizMsg.L, bBizMsg.specCondPrcPk_B.getValue());
                    if (l < 0) {
                        continue;
                    }
                    NWAL1660_LCMsg lBizMsg = bizMsg.L.no(l);
                    if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManAddFlg_PL.getValue())) {
                        delLMsgRows.add(l);
                    } else {
                        ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                    }
                    delRows.add(i);
                }
        
                if (delRows.size() > 0) {
                    ZYPTableUtil.deleteRows(bizMsg.B, delRows);
                }
                if (delLMsgRows.size() > 0) {
                    ZYPTableUtil.deleteRows(bizMsg.L, delLMsgRows);
                }
                NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_ON_Y);
            }
            // QC#22965 2018/04/11 Add End
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660_INIT(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        NWAL1660CommonLogic.createPullDownList(getGlobalCompanyCode(), bizMsg);
        // QC#22965 2018/04/11 Mod Start
        //NWAL1660CommonLogic.setParamToBizMsg(getGlobalCompanyCode(), bizMsg);
        //NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        if(NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())){
            NWAL1660CommonLogic.setParamToBizMsgForHeaderLevel(getGlobalCompanyCode(), bizMsg);
        }else{
            NWAL1660CommonLogic.setParamToBizMsg(getGlobalCompanyCode(), bizMsg);
            NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        }
        // QC#22965 2018/04/11 Mod End
    }

    /**
     * AddCharges Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660Scrn00_AddCharges(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        //
        NWAL1660CommonLogic.addCharges(getGlobalCompanyCode(), bizMsg);
        NWAL1660CommonLogic.deriveDefaultAmtForCharges(getGlobalCompanyCode(), bizMsg);
        if (!NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) { // QC#22965 2018/04/11 Add
            NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * AddSellPrice Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660Scrn00_AddSellPrice(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        //
        NWAL1660CommonLogic.addSellPrice(getGlobalCompanyCode(), bizMsg);
        NWAL1660CommonLogic.deriveDefaultAmtForSellPrice(getGlobalCompanyCode(), bizMsg);
        if (!NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) { // QC#22965 2018/04/11 Add
            NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * Calculate Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660Scrn00_Calculate(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        if (NWAL1660CommonLogic.checkEssential(getGlobalCompanyCode(), bizMsg)) {
            return;
        }
        // 2017/08/29 S21_NA#20655 MOD Start
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "prcCondManDelFlg_B", ZYPConstant.CHKBOX_ON_Y);
        //List<BigDecimal> delIdRows = new ArrayList<BigDecimal>();
        //for (int i : selectRows) {
        //    NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
        //    if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.ovrdFlg_B.getValue())) {
        //        delIdRows.add(bBizMsg.specCondPrcPk_B.getValue());
        //    }
        //}

        // NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        if (!NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) {
            NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        }
        // 2017/08/29 S21_NA#20655 MOD End
    }

    /**
     * DeleteCharges Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660Scrn00_DeleteCharges(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        //
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "prcCondManDelFlg_B", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> delRows = new ArrayList<Integer>();
        List<Integer> delLMsgRows = new ArrayList<Integer>();
        // 2017/08/24 S21_NA#20655 ADD Start
        // List<BigDecimal> delIdRows = new ArrayList<BigDecimal>();
        // 2017/08/24 S21_NA#20655 ADD End
        // QC#22965 2018/04/11 Add Start
        if (NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) {
            for (int i : selectRows) {
                NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
                int l = NWAL1660CommonLogic.getLBizMsgIdx(bizMsg.L, bBizMsg.specCondPrcPk_B.getValue());
                if (l < 0) {
                    delRows.add(i);
                }
            }
            if (delRows.size() > 0) {
                ZYPTableUtil.deleteRows(bizMsg.B, delRows);
            }
        } else {
        // QC#22965 2018/04/11 Add End
            for (int i : selectRows) {
                NWAL1660_BCMsg bBizMsg = bizMsg.B.no(i);
                int l = NWAL1660CommonLogic.getLBizMsgIdx(bizMsg.L, bBizMsg.specCondPrcPk_B.getValue());
                if (l < 0) {
                    continue;
                }
                NWAL1660_LCMsg lBizMsg = bizMsg.L.no(l);
                if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManAddFlg_PL.getValue())) {
                    delLMsgRows.add(l);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                }
                delRows.add(i);
                // 2017/08/24 S21_NA#20655 ADD Start
                //if (ZYPConstant.FLG_ON_Y.equals(bBizMsg.ovrdFlg_B.getValue())) {
                //    delIdRows.add(bBizMsg.specCondPrcPk_B.getValue());
                //}
                // 2017/08/24 S21_NA#20655 ADD End
            }
    
            if (delRows.size() > 0) {
                ZYPTableUtil.deleteRows(bizMsg.B, delRows);
            }
            if (delLMsgRows.size() > 0) {
                ZYPTableUtil.deleteRows(bizMsg.L, delLMsgRows);
            }
            // 2017/08/24 S21_NA#20655 MOD Start
            if (!NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) { // QC#22965 2018/04/11 Add 
                NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
            }
            // 2017/08/24 S21_NA#20655 MOD End
        }
    }

    /**
     * DeleteSellPrice Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660Scrn00_DeleteSellPrice(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        //
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "prcCondManDelFlg_A", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> delRows = new ArrayList<Integer>();
        List<Integer> delLMsgRows = new ArrayList<Integer>();
        // QC#22965 2018/04/11 Add Start
        if (NWAL1660Constant.PROCESS_LVL_HEADER.equals(bizMsg.xxModeCd_PM.getValue())) {
            for (int i : selectRows) {
                NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
                int l = NWAL1660CommonLogic.getLBizMsgIdx(bizMsg.L, aBizMsg.specCondPrcPk_A.getValue());
                if (l < 0) {
                    delRows.add(i);
                }
            }
            if (delRows.size() > 0) {
                ZYPTableUtil.deleteRows(bizMsg.A, delRows);
            }
        } else {
        // QC#22965 2018/04/11 Add End
            for (int i : selectRows) {
                NWAL1660_ACMsg aBizMsg = bizMsg.A.no(i);
                int l = NWAL1660CommonLogic.getLBizMsgIdx(bizMsg.L, aBizMsg.specCondPrcPk_A.getValue());
                if (l < 0) {
                    continue;
                }
                NWAL1660_LCMsg lBizMsg = bizMsg.L.no(l);
                if (ZYPConstant.FLG_ON_Y.equals(lBizMsg.prcCondManAddFlg_PL.getValue())) {
                    delLMsgRows.add(l);
                } else {
                    ZYPEZDItemValueSetter.setValue(lBizMsg.prcCondManDelFlg_PL, ZYPConstant.FLG_ON_Y);
                }
                delRows.add(i);
            }

            if (delRows.size() > 0) {
                ZYPTableUtil.deleteRows(bizMsg.A, delRows);
            }
            if (delLMsgRows.size() > 0) {
                ZYPTableUtil.deleteRows(bizMsg.L, delLMsgRows);
            }
            NWAL1660CommonLogic.getPriceInfo(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1660Scrn00_Reset(NWAL1660CMsg bizMsg, NWAL1660SMsg glblMsg) {
        //
        doProcess_NWAL1660_INIT(bizMsg, glblMsg);
    }
}
