/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.math.BigDecimal;

import business.db.DS_IMPT_ORD_DELY_INFOTMsg;

import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant;
import com.canon.cusa.s21.batch.NWA.NWAB412001.constant.NWAB412001Constant.FLG;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * SomDsImptOrdDelyInfoBean
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/09/2016   FUJITSU         K.Sato          CREATE          NEW
 * 12/01/2016   SRAA            K.Aratani       Update          QC#15539
 * 07/21/2017   SRAA            K.Aratani       Update          QC#19993
 * 08/22/2017   SRAA            K.Aratani       Update          QC#20739
 *</pre>
 */
public class SomDsImptOrdDelyInfoBean extends DS_IMPT_ORD_DELY_INFOTMsg implements ISomDsImptOrd {

    private static final long serialVersionUID = 1L;

    public final NWAB412001Constant.DELY_INFO_TYPE delyInfoType;

    public final SomDsImptOrdBean dsImptOrdBean;
    
    public final SomDsImptOrdConfigBean dsImptOrdConfigBean;

    public SomDsImptOrdDelyInfoBean(NWAB412001Constant.DELY_INFO_TYPE delyInfoType, SomDsImptOrdConfigBean dsImptOrdConfigBean) {
        super();

        this.delyInfoType = delyInfoType;
        this.dsImptOrdBean = dsImptOrdConfigBean.dsImptOrdBean;
        this.dsImptOrdConfigBean = dsImptOrdConfigBean;
        dsImptOrdConfigBean.dsImptOrdDelyInfoBean = this;
    }

    public SomDsImptOrdDelyInfoBean(NWAB412001Constant.DELY_INFO_TYPE delyInfoType, SomDsImptOrdBean dsImptOrdBean) {
        super();

        this.delyInfoType = delyInfoType;
        this.dsImptOrdBean = dsImptOrdBean;
        this.dsImptOrdConfigBean = null;
        dsImptOrdBean.dsImptOrdDelyInfoBean = this;
    }

    @Override
    public boolean doImptMapping(String glblCmpyCd, String salesDate) {

        String rddDt = null;
        String opsHourMn = null;
        String opsFromHourMn = null;
        String opsToHourMn = null;
        String loadDockAvalFlg = null;
        String elevAvalFlg = null;
        //String delyAddlCmntTxt = "";
        
        String firstNm = null;
        //String lastNm = null;
        //String pho = null;
        //String phoExt = null;
        //String fax = null;
        //String eml = null;
        String instnTxt = null;
        BigDecimal stepCnt = null;
        if (NWAB412001Constant.DELY_INFO_TYPE.HEADER_DELY_INFO.equals(this.delyInfoType)) {

            ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsDelyTpCd, DS_DELY_TP.INSTALLATION);
            
            rddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
            opsHourMn = this.dsImptOrdBean.nwai4120_01.hrsOpTxt.getValue();
            String[] opsHourMnFromTo = NWXC412001.divideTimeFromTo(opsHourMn);
            if (ZYPCommonFunc.hasValue(opsHourMnFromTo[0])) {
                opsFromHourMn = opsHourMnFromTo[0];
            }
            if (ZYPCommonFunc.hasValue(opsHourMnFromTo[1])) {
                opsToHourMn = opsHourMnFromTo[1];
            }
            if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.elevIndSomTxt)
                    && FLG.YES.name().equals(this.dsImptOrdBean.nwai4120_01.elevIndSomTxt.getValue())) {
                elevAvalFlg = ZYPConstant.FLG_ON_Y;
            } else {
                elevAvalFlg = ZYPConstant.FLG_OFF_N;
            }
            if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.loadDockIndSomTxt)
                    && FLG.YES.name().equals(this.dsImptOrdBean.nwai4120_01.loadDockIndSomTxt.getValue())) {
                loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
            } else {
                loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
            }
            firstNm = this.dsImptOrdBean.nwai4120_01.delyFirstNm.getValue();
            //lastNm = this.dsImptOrdBean.nwai4120_01.delyLastNm.getValue();
            //pho = this.dsImptOrdBean.nwai4120_01.delyPhoTxt.getValue();
            //phoExt = this.dsImptOrdBean.nwai4120_01.delyPhoExtTxt.getValue();
            //fax = null;
            //eml = this.dsImptOrdBean.nwai4120_01.delyEmlTxt.getValue();
            instnTxt = this.dsImptOrdBean.nwai4120_01.delyInstnTxt.getValue();
            stepCnt = this.dsImptOrdBean.nwai4120_01.somStepCnt.getValue();
            
            ZYPEZDItemValueSetter.setValue(this.rddDt, rddDt);
            ZYPEZDItemValueSetter.setValue(this.opsFromHourMn, opsFromHourMn);
            ZYPEZDItemValueSetter.setValue(this.opsToHourMn, opsToHourMn);
            ZYPEZDItemValueSetter.setValue(this.loadDockAvalFlg, loadDockAvalFlg);
            if (ZYPCommonFunc.hasValue(stepCnt) && BigDecimal.ZERO.compareTo(stepCnt) < 0) {
                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(this.stairCrawNum, stepCnt.toString());
            } else {
                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
                this.stairCrawNum.clear();
            }
            ZYPEZDItemValueSetter.setValue(this.elevAvalFlg, elevAvalFlg);
            //delyAddlCmntTxt = getDelyAddlCmntTxt(dsImptOrdBean.getVarCharConstMap(), firstNm, lastNm, pho, phoExt, fax, eml, opsHourMn, opsFromHourMn, opsToHourMn, instnTxt, stepCnt, elevAvalFlg, loadDockAvalFlg);
            //ZYPEZDItemValueSetter.setValue(this.delyAddlCmntTxt, delyAddlCmntTxt);
            if (ZYPCommonFunc.hasValue(instnTxt)) {
                ZYPEZDItemValueSetter.setValue(this.delyAddlCmntTxt, instnTxt);
            } else {
                this.delyAddlCmntTxt.clear();
            }
            
        } else {
            ZYPEZDItemValueSetter.setValue(this.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdPk, this.dsImptOrdConfigBean.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(this.dsImptOrdConfigPk, this.dsImptOrdConfigBean.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(this.dsDelyTpCd, DS_DELY_TP.INSTALLATION);
            
            if (NWAB412001Constant.CONFIG_TYPE.REGULAR_SHIP_CONFIG.equals(dsImptOrdConfigBean.configType)) {
                
                rddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_12.erlstDelyTs.getValue());
                if (!ZYPCommonFunc.hasValue(rddDt)) {
                    rddDt = NWXC412001.getValidDate(this.dsImptOrdBean.nwai4120_01.erlstDelyTs.getValue());
                }
                opsHourMn = dsImptOrdConfigBean.nwai4120_12.hrsOpTxt.getValue();
                if (!ZYPCommonFunc.hasValue(opsHourMn)) {
                    opsHourMn = this.dsImptOrdBean.nwai4120_01.hrsOpTxt.getValue();
                }
                String[] opsHourMnFromTo = NWXC412001.divideTimeFromTo(opsHourMn);
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[0])) {
                    opsFromHourMn = opsHourMnFromTo[0];
                }
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[1])) {
                    opsToHourMn = opsHourMnFromTo[1];
                }
                BigDecimal somElevCnt = dsImptOrdConfigBean.nwai4120_12.somElevCnt.getValue();
                if (somElevCnt == null) {
                    if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.elevIndSomTxt.getValue()) 
                            && FLG.YES.name().equals(this.dsImptOrdBean.nwai4120_01.elevIndSomTxt.getValue())) {
                        elevAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        elevAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai4120_12.somElevCnt) 
                            && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai4120_12.somElevCnt.getValue()) == 0) {
                        elevAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        elevAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                }
                BigDecimal loadDockCnt = dsImptOrdConfigBean.nwai4120_12.loadDockCnt.getValue();
                if (loadDockCnt == null) {
                    if (ZYPCommonFunc.hasValue(this.dsImptOrdBean.nwai4120_01.loadDockIndSomTxt)
                            && FLG.YES.name().equals(this.dsImptOrdBean.nwai4120_01.loadDockIndSomTxt.getValue())) {
                        loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai4120_12.loadDockCnt) 
                            && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai4120_12.loadDockCnt.getValue()) == 0) {
                        loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
                    } else {
                        loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                    }
                }
                firstNm = dsImptOrdConfigBean.nwai4120_12.primCtacFirstNm.getValue();
                if (!ZYPCommonFunc.hasValue(firstNm)) {
                    firstNm = this.dsImptOrdBean.nwai4120_01.delyFirstNm.getValue();
                    //lastNm = this.dsImptOrdBean.nwai4120_01.delyLastNm.getValue();
                    //pho = this.dsImptOrdBean.nwai4120_01.delyPhoTxt.getValue();
                    //phoExt = this.dsImptOrdBean.nwai4120_01.delyPhoExtTxt.getValue();
                    //fax = null;
                    //eml = this.dsImptOrdBean.nwai4120_01.delyEmlTxt.getValue();
                } else {
                    //lastNm = dsImptOrdConfigBean.nwai4120_12.primCtacLastNm.getValue();
                    //pho = dsImptOrdConfigBean.nwai4120_12.primPhoTxt.getValue();
                    //phoExt = dsImptOrdConfigBean.nwai4120_12.primPhoExtTxt.getValue();
                    //fax = null;
                    //eml = dsImptOrdConfigBean.nwai4120_12.primEmlTxt.getValue();
                }
                instnTxt = dsImptOrdConfigBean.nwai4120_12.somInstnTxt.getValue();
                stepCnt = dsImptOrdConfigBean.nwai4120_12.somStepCnt.getValue();
                if (stepCnt == null || BigDecimal.ZERO.compareTo(stepCnt) == 0) {
                    stepCnt = this.dsImptOrdBean.nwai4120_01.somStepCnt.getValue();
                }
            } else if (NWAB412001Constant.CONFIG_TYPE.OCE_PROMOTION_RMA.equals(dsImptOrdConfigBean.configType)) {
                //QC#20739
                String pickUpDtOthDescTxt = dsImptOrdConfigBean.nwai4120_14List.get(0).pickUpDtOthDescTxt.getValue();
                if (ZYPCommonFunc.hasValue(pickUpDtOthDescTxt)) {
                    if (pickUpDtOthDescTxt.length() > 8) {
                        pickUpDtOthDescTxt = pickUpDtOthDescTxt.substring(0, 8);
                    }
                }
                //rddDt =  NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_14List.get(0).pickUpDtOthDescTxt.getValue());
                rddDt =  NWXC412001.getValidDate(pickUpDtOthDescTxt);
                if (NWAB412001Constant.INVALID_DATE.equals(rddDt)) {
                    rddDt = null;
                }
                opsHourMn = dsImptOrdConfigBean.dsImptOrdBean.nwai4120_01.hrsOpTxt.getValue();
                loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                elevAvalFlg = ZYPConstant.FLG_OFF_N;
                //delyAddlCmntTxt = null;
            } else {
                //QC#20739
                //rddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.nwai4120_05.erlstDelyTs.getValue());
                rddDt = NWXC412001.getValidDate(dsImptOrdConfigBean.getPickUpDtOthDescTxt());
                opsHourMn = dsImptOrdConfigBean.nwai4120_05.hrsOpTxt.getValue();
                String[] opsHourMnFromTo = NWXC412001.divideTimeFromTo(opsHourMn);
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[0])) {
                    opsFromHourMn = opsHourMnFromTo[0];
                }
                if (ZYPCommonFunc.hasValue(opsHourMnFromTo[1])) {
                    opsToHourMn = opsHourMnFromTo[1];
                }
                if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai4120_05.loadDockCnt) 
                        && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai4120_05.loadDockCnt.getValue()) == 0) {
                    loadDockAvalFlg = ZYPConstant.FLG_ON_Y;
                } else if (BigDecimal.ZERO.compareTo(dsImptOrdConfigBean.nwai4120_05.loadDockCnt.getValue()) == 0) {
                    loadDockAvalFlg = ZYPConstant.FLG_OFF_N;
                }
                if (ZYPCommonFunc.hasValue(dsImptOrdConfigBean.nwai4120_05.somElevCnt) 
                        && BigDecimal.ONE.compareTo(dsImptOrdConfigBean.nwai4120_05.somElevCnt.getValue()) == 0) {
                    elevAvalFlg = ZYPConstant.FLG_ON_Y;
                } else if (BigDecimal.ZERO.compareTo(dsImptOrdConfigBean.nwai4120_05.somElevCnt.getValue()) == 0) {
                    elevAvalFlg = ZYPConstant.FLG_OFF_N;
                }
                firstNm = dsImptOrdConfigBean.nwai4120_05.primCtacFirstNm.getValue();
                //lastNm = dsImptOrdConfigBean.nwai4120_05.primCtacLastNm.getValue();
                //pho = dsImptOrdConfigBean.nwai4120_05.primPhoTxt.getValue();
                //phoExt = dsImptOrdConfigBean.nwai4120_05.primPhoExtTxt.getValue();
                //fax = null;
                //eml = dsImptOrdConfigBean.nwai4120_05.primEmlTxt.getValue();
                stepCnt = dsImptOrdConfigBean.nwai4120_05.somStepCnt.getValue();

            }

            ZYPEZDItemValueSetter.setValue(this.rddDt, rddDt);
            ZYPEZDItemValueSetter.setValue(this.opsFromHourMn, opsFromHourMn);
            ZYPEZDItemValueSetter.setValue(this.opsToHourMn, opsToHourMn);
            ZYPEZDItemValueSetter.setValue(this.loadDockAvalFlg, loadDockAvalFlg);
            if (ZYPCommonFunc.hasValue(stepCnt) && BigDecimal.ZERO.compareTo(stepCnt) < 0) {
                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(this.stairCrawNum, stepCnt.toString());
            } else {
                ZYPEZDItemValueSetter.setValue(this.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
                this.stairCrawNum.clear();
            }
            ZYPEZDItemValueSetter.setValue(this.elevAvalFlg, elevAvalFlg);
            //delyAddlCmntTxt = getDelyAddlCmntTxt(dsImptOrdConfigBean.dsImptOrdBean.getVarCharConstMap(), firstNm, lastNm, pho, phoExt, fax, eml, opsHourMn, opsFromHourMn, opsToHourMn, instnTxt, stepCnt, elevAvalFlg, loadDockAvalFlg);
            //ZYPEZDItemValueSetter.setValue(this.delyAddlCmntTxt, delyAddlCmntTxt);
            if (ZYPCommonFunc.hasValue(instnTxt)) {
                ZYPEZDItemValueSetter.setValue(this.delyAddlCmntTxt, instnTxt);
            } else {
                this.delyAddlCmntTxt.clear();
            }
        }

        return true;
    }
    
//    private String getDelyAddlCmntTxt(
//            java.util.Map<String, Object> varCharConstMap,
//            String firstNm, 
//            String lastNm, 
//            String pho, 
//            String phoExt, 
//            String fax,
//            String eml,
//            String hrsOp, 
//            String opsFromHourMn,
//            String opsToHourMn,
//            String instnTxt, 
//            BigDecimal stepCnt, 
//            String somElevInd, 
//            String loadDockInd) {
//        
//        String delyAddlCmntTxt = "";
//
//        //FIRST_NAME
//        if (ZYPCommonFunc.hasValue(firstNm)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_FIRST_NAME_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = firstNm;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + firstNm;
//                }
//            }
//        }
//        //LAST_NAME
//        if (ZYPCommonFunc.hasValue(lastNm)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_LAST_NAME_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = lastNm;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + lastNm;
//                }
//            }
//        }
//        //PHONE
//        if (ZYPCommonFunc.hasValue(pho)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_PHONE_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = pho;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + pho;
//                }
//            }
//        }
//        //PHONE_EXTENSION
//        if (ZYPCommonFunc.hasValue(phoExt)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_PHONE_EXTN_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = phoExt;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + phoExt;
//                }
//            }
//        }
//        //FAX
//        if (ZYPCommonFunc.hasValue(fax)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_FAX_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = fax;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + fax;
//                }
//            }
//        }
//        //EMAIL
//        if (ZYPCommonFunc.hasValue(eml)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_EMAIL_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = eml;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + eml;
//                }
//            }
//        }
//        //NOTES
//        String notes = "";
//        if (ZYPCommonFunc.hasValue(hrsOp)) {
//
//            if (ZYPCommonFunc.hasValue(instnTxt)) {
//                notes = String.format(NWAB412001Constant.FORMAT_CTAC_PSN_CMNT_TXT, instnTxt, hrsOp);
//            } else {
//                notes = String.format(NWAB412001Constant.FORMAT_CTAC_PSN_CMNT_TXT_2, hrsOp);
//            }
//
//        }
//        if (ZYPCommonFunc.hasValue(notes)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_NOTE_FLG"))) {
//                if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                    delyAddlCmntTxt = notes;
//                } else {
//                    delyAddlCmntTxt = delyAddlCmntTxt + " " + notes;
//                }
//            }
//        }
//        //HOURS FROM
//        if (ZYPCommonFunc.hasValue(opsFromHourMn)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_HRS_FROM_FLG"))) {
//                String label = (String)varCharConstMap.get("DELY_CMNT_HRS_FROM_NM");
//                if (ZYPCommonFunc.hasValue(label)) {
//                    if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = label + ":" + opsFromHourMn;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + label + ":" + opsFromHourMn;
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = opsFromHourMn;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + opsFromHourMn;
//                    }
//                }
//            }
//        }
//        //HOURS TO
//        if (ZYPCommonFunc.hasValue(opsToHourMn)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_HRS_TO_FLG"))) {
//                String label = (String)varCharConstMap.get("DELY_CMNT_HRS_TO_NM");
//                if (ZYPCommonFunc.hasValue(label)) {
//                    if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = label + ":" + opsToHourMn;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + label + ":" + opsToHourMn;
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = opsToHourMn;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + opsToHourMn;
//                    }
//                }
//            }
//        }
//        
//        //STEPS
//        if (ZYPCommonFunc.hasValue(stepCnt)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_STEPS_FLG"))) {
//                String label = (String)varCharConstMap.get("DELY_CMNT_STEPS_NM");
//                if (ZYPCommonFunc.hasValue(label)) {
//                    if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = label + ":" + stepCnt.toString();
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + label + ":" + stepCnt.toString();
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = stepCnt.toString();
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + stepCnt.toString();
//                    }
//                }
//            }
//        }
//        //ELEVATOR
//        if (ZYPCommonFunc.hasValue(somElevInd)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_ELEV_FLG"))) {
//                String label = (String)varCharConstMap.get("DELY_CMNT_ELEV_NM");
//                if (ZYPCommonFunc.hasValue(label)) {
//                    if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = label + ":" + somElevInd;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + label + ":" + somElevInd;
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = somElevInd;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + somElevInd;
//                    }
//                }
//            }
//        }
//        //LDOCK
//        if (ZYPCommonFunc.hasValue(loadDockInd)) {
//            if("Y".equals((String)varCharConstMap.get("DELY_CMNT_LDOCK_FLG"))) {
//                String label = (String)varCharConstMap.get("DELY_CMNT_LDOCK_NM");
//                if (ZYPCommonFunc.hasValue(label)) {
//                    if (!ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = label + ":" + loadDockInd;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + label + ":" + loadDockInd;
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(delyAddlCmntTxt)) {
//                        delyAddlCmntTxt = loadDockInd;
//                    } else {
//                        delyAddlCmntTxt = delyAddlCmntTxt + " " + loadDockInd;
//                    }
//                }
//            }
//        }
//        if (ZYPCommonFunc.hasValue(delyAddlCmntTxt) && delyAddlCmntTxt.length() > 240) {
//            delyAddlCmntTxt = delyAddlCmntTxt.substring(0, 240);
//        }
//        return delyAddlCmntTxt;
//    }
}
