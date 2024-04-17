// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160728033738000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4500Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL4500;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL4500 is data bean class.
 */
public class NMAL4500Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** VND_CD_01*/
	public static final String vndCd_01 = "vndCd_01";

	/** LOC_NM_01*/
	public static final String locNm_01 = "locNm_01";

	/** DBA_NM_01*/
	public static final String dbaNm_01 = "dbaNm_01";

	/** CMPY_PK_01*/
	public static final String cmpyPk_01 = "cmpyPk_01";

	/** PTY_LOC_PK_01*/
	public static final String ptyLocPk_01 = "ptyLocPk_01";

	/** FIRST_LINE_ADDR_01*/
	public static final String firstLineAddr_01 = "firstLineAddr_01";

	/** SCD_LINE_ADDR_01*/
	public static final String scdLineAddr_01 = "scdLineAddr_01";

	/** THIRD_LINE_ADDR_01*/
	public static final String thirdLineAddr_01 = "thirdLineAddr_01";

	/** FRTH_LINE_ADDR_01*/
	public static final String frthLineAddr_01 = "frthLineAddr_01";

	/** CTRY_CD_01*/
	public static final String ctryCd_01 = "ctryCd_01";

	/** CTRY_NM_02*/
	public static final String ctryNm_02 = "ctryNm_02";

	/** CTRY_CD_03*/
	public static final String ctryCd_03 = "ctryCd_03";

	/** POST_CD_01*/
	public static final String postCd_01 = "postCd_01";

	/** CTY_ADDR_01*/
	public static final String ctyAddr_01 = "ctyAddr_01";

	/** CNTY_PK_01*/
	public static final String cntyPk_01 = "cntyPk_01";

	/** CNTY_NM_02*/
	public static final String cntyNm_02 = "cntyNm_02";

	/** CNTY_PK_03*/
	public static final String cntyPk_03 = "cntyPk_03";

	/** ST_CD_01*/
	public static final String stCd_01 = "stCd_01";

	/** PROV_NM_01*/
	public static final String provNm_01 = "provNm_01";

	/** FIRST_REF_CMNT_TXT_01*/
	public static final String firstRefCmntTxt_01 = "firstRefCmntTxt_01";

	/** SCD_REF_CMNT_TXT_01*/
	public static final String scdRefCmntTxt_01 = "scdRefCmntTxt_01";

	/** TEL_NUM_01*/
	public static final String telNum_01 = "telNum_01";

	/** FAX_NUM_01*/
	public static final String faxNum_01 = "faxNum_01";

	/** ATTN_NM_01*/
	public static final String attnNm_01 = "attnNm_01";

	/** CARR_TP_CD_01*/
	public static final String carrTpCd_01 = "carrTpCd_01";

	/** CARR_TP_DESC_TXT_02*/
	public static final String carrTpDescTxt_02 = "carrTpDescTxt_02";

	/** CARR_TP_CD_03*/
	public static final String carrTpCd_03 = "carrTpCd_03";

	/** TAX_PAYER_ID_01*/
	public static final String taxPayerId_01 = "taxPayerId_01";

	/** COA_AFFL_CD_01*/
	public static final String coaAfflCd_01 = "coaAfflCd_01";

	/** INTL_VND_FLG_01*/
	public static final String intlVndFlg_01 = "intlVndFlg_01";

	/** PAYEE_FLG_01*/
	public static final String payeeFlg_01 = "payeeFlg_01";

	/** ASN_REQ_FLG_01*/
	public static final String asnReqFlg_01 = "asnReqFlg_01";

	/** SHPG_SVC_LVL_CD_01*/
	public static final String shpgSvcLvlCd_01 = "shpgSvcLvlCd_01";

	/** SHPG_SVC_LVL_NM_02*/
	public static final String shpgSvcLvlNm_02 = "shpgSvcLvlNm_02";

	/** SHPG_SVC_LVL_CD_03*/
	public static final String shpgSvcLvlCd_03 = "shpgSvcLvlCd_03";

	/** FRT_CHRG_TO_CD_01*/
	public static final String frtChrgToCd_01 = "frtChrgToCd_01";

	/** FRT_CHRG_TO_NM_02*/
	public static final String frtChrgToNm_02 = "frtChrgToNm_02";

	/** FRT_CHRG_TO_CD_03*/
	public static final String frtChrgToCd_03 = "frtChrgToCd_03";

	/** FRT_CHRG_METH_CD_01*/
	public static final String frtChrgMethCd_01 = "frtChrgMethCd_01";

	/** FRT_CHRG_METH_NM_02*/
	public static final String frtChrgMethNm_02 = "frtChrgMethNm_02";

	/** FRT_CHRG_METH_CD_03*/
	public static final String frtChrgMethCd_03 = "frtChrgMethCd_03";

	/** TRSMT_METH_TP_CD_01*/
	public static final String trsmtMethTpCd_01 = "trsmtMethTpCd_01";

	/** TRSMT_METH_TP_NM_02*/
	public static final String trsmtMethTpNm_02 = "trsmtMethTpNm_02";

	/** TRSMT_METH_TP_CD_03*/
	public static final String trsmtMethTpCd_03 = "trsmtMethTpCd_03";

	/** SEND_PO_EML_ADDR_01*/
	public static final String sendPoEmlAddr_01 = "sendPoEmlAddr_01";

	/** INV_RCV_METH_TP_CD_01*/
	public static final String invRcvMethTpCd_01 = "invRcvMethTpCd_01";

	/** INV_RCV_METH_TP_NM_02*/
	public static final String invRcvMethTpNm_02 = "invRcvMethTpNm_02";

	/** INV_RCV_METH_TP_CD_03*/
	public static final String invRcvMethTpCd_03 = "invRcvMethTpCd_03";

	/** THIRD_PTY_VND_FLG_01*/
	public static final String thirdPtyVndFlg_01 = "thirdPtyVndFlg_01";

	/** XX_SCR_EVENT_NM_01*/
	public static final String xxScrEventNm_01 = "xxScrEventNm_01";

	/** CHRG_RATE_VND_GRP_CD_01*/
	public static final String chrgRateVndGrpCd_01 = "chrgRateVndGrpCd_01";

	/** CHRG_RATE_VND_GRP_CD_02*/
	public static final String chrgRateVndGrpCd_02 = "chrgRateVndGrpCd_02";

	/** CHRG_RATE_VND_GRP_CD_03*/
	public static final String chrgRateVndGrpCd_03 = "chrgRateVndGrpCd_03";

	/** WH_PK_01*/
	public static final String whPk_01 = "whPk_01";

	/** WH_CD_02*/
	public static final String whCd_02 = "whCd_02";

	/** WH_PK_03*/
	public static final String whPk_03 = "whPk_03";

	/** DEAL_CCY_CD_01*/
	public static final String dealCcyCd_01 = "dealCcyCd_01";

	/** INV_VND_CD_01*/
	public static final String invVndCd_01 = "invVndCd_01";

	/** LOC_NM_02*/
	public static final String locNm_02 = "locNm_02";

	/** _EZUpdateDateTime_01*/
	public static final String ezUpTime_01 = "ezUpTime_01";

	/** _EZUpTimeZone_01*/
	public static final String ezUpTimeZone_01 = "ezUpTimeZone_01";

	/** _EZUpdateDateTime_02*/
	public static final String ezUpTime_02 = "ezUpTime_02";

	/** _EZUpTimeZone_02*/
	public static final String ezUpTimeZone_02 = "ezUpTimeZone_02";

	/** _EZUpdateDateTime_03*/
	public static final String ezUpTime_03 = "ezUpTime_03";

	/** _EZUpTimeZone_03*/
	public static final String ezUpTimeZone_03 = "ezUpTimeZone_03";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** VND_TP_CD_A1*/
	public static final String vndTpCd_A1 = "vndTpCd_A1";

	/** VND_TP_NM_A1*/
	public static final String vndTpNm_A1 = "vndTpNm_A1";

	/** _EZUpdateDateTime_A1*/
	public static final String ezUpTime_A1 = "ezUpTime_A1";

	/** _EZUpTimeZone_A1*/
	public static final String ezUpTimeZone_A1 = "ezUpTimeZone_A1";

	/** B*/
	public static final String B = "B";

	/** ST_CD_B1*/
	public static final String stCd_B1 = "stCd_B1";

	/** ST_NM_B1*/
	public static final String stNm_B1 = "stNm_B1";

	/** C*/
	public static final String C = "C";

	/** CNTY_PK_C1*/
	public static final String cntyPk_C1 = "cntyPk_C1";

	/** CNTY_NM_C1*/
	public static final String cntyNm_C1 = "cntyNm_C1";

	/** XX_TBL_NM_Z1*/
	public static final String xxTblNm_Z1 = "xxTblNm_Z1";

	/** XX_TBL_CD_COL_NM_Z1*/
	public static final String xxTblCdColNm_Z1 = "xxTblCdColNm_Z1";

	/** XX_TBL_NM_COL_NM_Z1*/
	public static final String xxTblNmColNm_Z1 = "xxTblNmColNm_Z1";

	/** XX_TBL_SORT_COL_NM_Z1*/
	public static final String xxTblSortColNm_Z1 = "xxTblSortColNm_Z1";

	/** XX_SCR_NM_Z1*/
	public static final String xxScrNm_Z1 = "xxScrNm_Z1";

	/** XX_HDR_CD_LB_NM_Z1*/
	public static final String xxHdrCdLbNm_Z1 = "xxHdrCdLbNm_Z1";

	/** XX_HDR_NM_LB_NM_Z1*/
	public static final String xxHdrNmLbNm_Z1 = "xxHdrNmLbNm_Z1";

	/** XX_DTL_CD_LB_NM_Z1*/
	public static final String xxDtlCdLbNm_Z1 = "xxDtlCdLbNm_Z1";

	/** XX_DTL_NM_LB_NM_Z1*/
	public static final String xxDtlNmLbNm_Z1 = "xxDtlNmLbNm_Z1";

	/** XX_COND_CD_Z1*/
	public static final String xxCondCd_Z1 = "xxCondCd_Z1";

	/** XX_COND_NM_Z1*/
	public static final String xxCondNm_Z1 = "xxCondNm_Z1";

	/** P*/
	public static final String P = "P";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/** XX_LINK_PROT_01*/
	public static final String xxLinkProt_01 = "xxLinkProt_01";

	/** XX_LINK_PROT_02*/
	public static final String xxLinkProt_02 = "xxLinkProt_02";

	/** XX_LINK_PROT_03*/
	public static final String xxLinkProt_03 = "xxLinkProt_03";

	/** XX_LINK_PROT_04*/
	public static final String xxLinkProt_04 = "xxLinkProt_04";

	/** XX_LINK_PROT_05*/
	public static final String xxLinkProt_05 = "xxLinkProt_05";

	/** XX_LINK_PROT_06*/
	public static final String xxLinkProt_06 = "xxLinkProt_06";

	/** XX_LINK_ANCR_AL*/
	public static final String xxLinkAncr_AL = "xxLinkAncr_AL";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/**
	 * Method name:NMAL4500 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL4500Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL4500Bean() {
		super("business.servlet.NMAL4500.NMAL4500BMsg");
	}

	/**
	 * get  VND_CD_01.
	 * @return VND_CD_01
	 */
	public String getVndCd_01() {
		return outputValue(vndCd_01);
	}

	/**
	 * set  VND_CD_01.
	 * @param data VND_CD_01
	 */
	public void setVndCd_01(String data) {
		inputValue(vndCd_01,data);
	}

	/**
	 * get  LOC_NM_01.
	 * @return LOC_NM_01
	 */
	public String getLocNm_01() {
		return outputValue(locNm_01);
	}

	/**
	 * set  LOC_NM_01.
	 * @param data LOC_NM_01
	 */
	public void setLocNm_01(String data) {
		inputValue(locNm_01,data);
	}

	/**
	 * get  DBA_NM_01.
	 * @return DBA_NM_01
	 */
	public String getDbaNm_01() {
		return outputValue(dbaNm_01);
	}

	/**
	 * set  DBA_NM_01.
	 * @param data DBA_NM_01
	 */
	public void setDbaNm_01(String data) {
		inputValue(dbaNm_01,data);
	}

	/**
	 * get  CMPY_PK_01.
	 * @return CMPY_PK_01
	 */
	public String getCmpyPk_01() {
		return outputValue(cmpyPk_01);
	}

	/**
	 * set  CMPY_PK_01.
	 * @param data CMPY_PK_01
	 */
	public void setCmpyPk_01(String data) {
		inputValue(cmpyPk_01,data);
	}

	/**
	 * get  PTY_LOC_PK_01.
	 * @return PTY_LOC_PK_01
	 */
	public String getPtyLocPk_01() {
		return outputValue(ptyLocPk_01);
	}

	/**
	 * set  PTY_LOC_PK_01.
	 * @param data PTY_LOC_PK_01
	 */
	public void setPtyLocPk_01(String data) {
		inputValue(ptyLocPk_01,data);
	}

	/**
	 * get  FIRST_LINE_ADDR_01.
	 * @return FIRST_LINE_ADDR_01
	 */
	public String getFirstLineAddr_01() {
		return outputValue(firstLineAddr_01);
	}

	/**
	 * set  FIRST_LINE_ADDR_01.
	 * @param data FIRST_LINE_ADDR_01
	 */
	public void setFirstLineAddr_01(String data) {
		inputValue(firstLineAddr_01,data);
	}

	/**
	 * get  SCD_LINE_ADDR_01.
	 * @return SCD_LINE_ADDR_01
	 */
	public String getScdLineAddr_01() {
		return outputValue(scdLineAddr_01);
	}

	/**
	 * set  SCD_LINE_ADDR_01.
	 * @param data SCD_LINE_ADDR_01
	 */
	public void setScdLineAddr_01(String data) {
		inputValue(scdLineAddr_01,data);
	}

	/**
	 * get  THIRD_LINE_ADDR_01.
	 * @return THIRD_LINE_ADDR_01
	 */
	public String getThirdLineAddr_01() {
		return outputValue(thirdLineAddr_01);
	}

	/**
	 * set  THIRD_LINE_ADDR_01.
	 * @param data THIRD_LINE_ADDR_01
	 */
	public void setThirdLineAddr_01(String data) {
		inputValue(thirdLineAddr_01,data);
	}

	/**
	 * get  FRTH_LINE_ADDR_01.
	 * @return FRTH_LINE_ADDR_01
	 */
	public String getFrthLineAddr_01() {
		return outputValue(frthLineAddr_01);
	}

	/**
	 * set  FRTH_LINE_ADDR_01.
	 * @param data FRTH_LINE_ADDR_01
	 */
	public void setFrthLineAddr_01(String data) {
		inputValue(frthLineAddr_01,data);
	}

	/**
	 * get  CTRY_CD_01.
	 * @param idx1 Index Number
	 * @return CTRY_CD_01
	 */
	public String getCtryCd_01(int idx1) {
	 	 return outputValue(ctryCd_01, idx1);
	}

	/**
	 * get  CTRY_NM_02.
	 * @param idx1 Index Number
	 * @return CTRY_NM_02
	 */
	public String getCtryNm_02(int idx1) {
	 	 return outputValue(ctryNm_02, idx1);
	}

	/**
	 * get  CTRY_CD_03.
	 * @return CTRY_CD_03
	 */
	public String getCtryCd_03() {
		return outputValue(ctryCd_03);
	}

	/**
	 * set  CTRY_CD_03.
	 * @param data CTRY_CD_03
	 */
	public void setCtryCd_03(String data) {
		inputValue(ctryCd_03,data);
	}

	/**
	 * get  POST_CD_01.
	 * @return POST_CD_01
	 */
	public String getPostCd_01() {
		return outputValue(postCd_01);
	}

	/**
	 * set  POST_CD_01.
	 * @param data POST_CD_01
	 */
	public void setPostCd_01(String data) {
		inputValue(postCd_01,data);
	}

	/**
	 * get  CTY_ADDR_01.
	 * @return CTY_ADDR_01
	 */
	public String getCtyAddr_01() {
		return outputValue(ctyAddr_01);
	}

	/**
	 * set  CTY_ADDR_01.
	 * @param data CTY_ADDR_01
	 */
	public void setCtyAddr_01(String data) {
		inputValue(ctyAddr_01,data);
	}

	/**
	 * get  CNTY_PK_01.
	 * @param idx1 Index Number
	 * @return CNTY_PK_01
	 */
	public String getCntyPk_01(int idx1) {
	 	 return outputValue(cntyPk_01, idx1);
	}

	/**
	 * get  CNTY_NM_02.
	 * @param idx1 Index Number
	 * @return CNTY_NM_02
	 */
	public String getCntyNm_02(int idx1) {
	 	 return outputValue(cntyNm_02, idx1);
	}

	/**
	 * get  CNTY_PK_03.
	 * @return CNTY_PK_03
	 */
	public String getCntyPk_03() {
		return outputValue(cntyPk_03);
	}

	/**
	 * set  CNTY_PK_03.
	 * @param data CNTY_PK_03
	 */
	public void setCntyPk_03(String data) {
		inputValue(cntyPk_03,data);
	}

	/**
	 * get  ST_CD_01.
	 * @return ST_CD_01
	 */
	public String getStCd_01() {
		return outputValue(stCd_01);
	}

	/**
	 * set  ST_CD_01.
	 * @param data ST_CD_01
	 */
	public void setStCd_01(String data) {
		inputValue(stCd_01,data);
	}

	/**
	 * get  PROV_NM_01.
	 * @return PROV_NM_01
	 */
	public String getProvNm_01() {
		return outputValue(provNm_01);
	}

	/**
	 * set  PROV_NM_01.
	 * @param data PROV_NM_01
	 */
	public void setProvNm_01(String data) {
		inputValue(provNm_01,data);
	}

	/**
	 * get  FIRST_REF_CMNT_TXT_01.
	 * @return FIRST_REF_CMNT_TXT_01
	 */
	public String getFirstRefCmntTxt_01() {
		return outputValue(firstRefCmntTxt_01);
	}

	/**
	 * set  FIRST_REF_CMNT_TXT_01.
	 * @param data FIRST_REF_CMNT_TXT_01
	 */
	public void setFirstRefCmntTxt_01(String data) {
		inputValue(firstRefCmntTxt_01,data);
	}

	/**
	 * get  SCD_REF_CMNT_TXT_01.
	 * @return SCD_REF_CMNT_TXT_01
	 */
	public String getScdRefCmntTxt_01() {
		return outputValue(scdRefCmntTxt_01);
	}

	/**
	 * set  SCD_REF_CMNT_TXT_01.
	 * @param data SCD_REF_CMNT_TXT_01
	 */
	public void setScdRefCmntTxt_01(String data) {
		inputValue(scdRefCmntTxt_01,data);
	}

	/**
	 * get  TEL_NUM_01.
	 * @return TEL_NUM_01
	 */
	public String getTelNum_01() {
		return outputValue(telNum_01);
	}

	/**
	 * set  TEL_NUM_01.
	 * @param data TEL_NUM_01
	 */
	public void setTelNum_01(String data) {
		inputValue(telNum_01,data);
	}

	/**
	 * get  FAX_NUM_01.
	 * @return FAX_NUM_01
	 */
	public String getFaxNum_01() {
		return outputValue(faxNum_01);
	}

	/**
	 * set  FAX_NUM_01.
	 * @param data FAX_NUM_01
	 */
	public void setFaxNum_01(String data) {
		inputValue(faxNum_01,data);
	}

	/**
	 * get  ATTN_NM_01.
	 * @return ATTN_NM_01
	 */
	public String getAttnNm_01() {
		return outputValue(attnNm_01);
	}

	/**
	 * set  ATTN_NM_01.
	 * @param data ATTN_NM_01
	 */
	public void setAttnNm_01(String data) {
		inputValue(attnNm_01,data);
	}

	/**
	 * get  CARR_TP_CD_01.
	 * @param idx1 Index Number
	 * @return CARR_TP_CD_01
	 */
	public String getCarrTpCd_01(int idx1) {
	 	 return outputValue(carrTpCd_01, idx1);
	}

	/**
	 * get  CARR_TP_DESC_TXT_02.
	 * @param idx1 Index Number
	 * @return CARR_TP_DESC_TXT_02
	 */
	public String getCarrTpDescTxt_02(int idx1) {
	 	 return outputValue(carrTpDescTxt_02, idx1);
	}

	/**
	 * get  CARR_TP_CD_03.
	 * @return CARR_TP_CD_03
	 */
	public String getCarrTpCd_03() {
		return outputValue(carrTpCd_03);
	}

	/**
	 * set  CARR_TP_CD_03.
	 * @param data CARR_TP_CD_03
	 */
	public void setCarrTpCd_03(String data) {
		inputValue(carrTpCd_03,data);
	}

	/**
	 * get  TAX_PAYER_ID_01.
	 * @return TAX_PAYER_ID_01
	 */
	public String getTaxPayerId_01() {
		return outputValue(taxPayerId_01);
	}

	/**
	 * set  TAX_PAYER_ID_01.
	 * @param data TAX_PAYER_ID_01
	 */
	public void setTaxPayerId_01(String data) {
		inputValue(taxPayerId_01,data);
	}

	/**
	 * get  COA_AFFL_CD_01.
	 * @return COA_AFFL_CD_01
	 */
	public String getCoaAfflCd_01() {
		return outputValue(coaAfflCd_01);
	}

	/**
	 * set  COA_AFFL_CD_01.
	 * @param data COA_AFFL_CD_01
	 */
	public void setCoaAfflCd_01(String data) {
		inputValue(coaAfflCd_01,data);
	}

	/**
	 * get  INTL_VND_FLG_01.
	 * @return INTL_VND_FLG_01
	 */
	public String getIntlVndFlg_01() {
		return outputValue(intlVndFlg_01);
	}

	/**
	 * set  INTL_VND_FLG_01.
	 * @param data INTL_VND_FLG_01
	 */
	public void setIntlVndFlg_01(String data) {
		inputValue(intlVndFlg_01,data);
	}

	/**
	 * get  PAYEE_FLG_01.
	 * @return PAYEE_FLG_01
	 */
	public String getPayeeFlg_01() {
		return outputValue(payeeFlg_01);
	}

	/**
	 * set  PAYEE_FLG_01.
	 * @param data PAYEE_FLG_01
	 */
	public void setPayeeFlg_01(String data) {
		inputValue(payeeFlg_01,data);
	}

	/**
	 * get  ASN_REQ_FLG_01.
	 * @return ASN_REQ_FLG_01
	 */
	public String getAsnReqFlg_01() {
		return outputValue(asnReqFlg_01);
	}

	/**
	 * set  ASN_REQ_FLG_01.
	 * @param data ASN_REQ_FLG_01
	 */
	public void setAsnReqFlg_01(String data) {
		inputValue(asnReqFlg_01,data);
	}

	/**
	 * get  SHPG_SVC_LVL_CD_01.
	 * @param idx1 Index Number
	 * @return SHPG_SVC_LVL_CD_01
	 */
	public String getShpgSvcLvlCd_01(int idx1) {
	 	 return outputValue(shpgSvcLvlCd_01, idx1);
	}

	/**
	 * get  SHPG_SVC_LVL_NM_02.
	 * @param idx1 Index Number
	 * @return SHPG_SVC_LVL_NM_02
	 */
	public String getShpgSvcLvlNm_02(int idx1) {
	 	 return outputValue(shpgSvcLvlNm_02, idx1);
	}

	/**
	 * get  SHPG_SVC_LVL_CD_03.
	 * @return SHPG_SVC_LVL_CD_03
	 */
	public String getShpgSvcLvlCd_03() {
		return outputValue(shpgSvcLvlCd_03);
	}

	/**
	 * set  SHPG_SVC_LVL_CD_03.
	 * @param data SHPG_SVC_LVL_CD_03
	 */
	public void setShpgSvcLvlCd_03(String data) {
		inputValue(shpgSvcLvlCd_03,data);
	}

	/**
	 * get  FRT_CHRG_TO_CD_01.
	 * @param idx1 Index Number
	 * @return FRT_CHRG_TO_CD_01
	 */
	public String getFrtChrgToCd_01(int idx1) {
	 	 return outputValue(frtChrgToCd_01, idx1);
	}

	/**
	 * get  FRT_CHRG_TO_NM_02.
	 * @param idx1 Index Number
	 * @return FRT_CHRG_TO_NM_02
	 */
	public String getFrtChrgToNm_02(int idx1) {
	 	 return outputValue(frtChrgToNm_02, idx1);
	}

	/**
	 * get  FRT_CHRG_TO_CD_03.
	 * @return FRT_CHRG_TO_CD_03
	 */
	public String getFrtChrgToCd_03() {
		return outputValue(frtChrgToCd_03);
	}

	/**
	 * set  FRT_CHRG_TO_CD_03.
	 * @param data FRT_CHRG_TO_CD_03
	 */
	public void setFrtChrgToCd_03(String data) {
		inputValue(frtChrgToCd_03,data);
	}

	/**
	 * get  FRT_CHRG_METH_CD_01.
	 * @param idx1 Index Number
	 * @return FRT_CHRG_METH_CD_01
	 */
	public String getFrtChrgMethCd_01(int idx1) {
	 	 return outputValue(frtChrgMethCd_01, idx1);
	}

	/**
	 * get  FRT_CHRG_METH_NM_02.
	 * @param idx1 Index Number
	 * @return FRT_CHRG_METH_NM_02
	 */
	public String getFrtChrgMethNm_02(int idx1) {
	 	 return outputValue(frtChrgMethNm_02, idx1);
	}

	/**
	 * get  FRT_CHRG_METH_CD_03.
	 * @return FRT_CHRG_METH_CD_03
	 */
	public String getFrtChrgMethCd_03() {
		return outputValue(frtChrgMethCd_03);
	}

	/**
	 * set  FRT_CHRG_METH_CD_03.
	 * @param data FRT_CHRG_METH_CD_03
	 */
	public void setFrtChrgMethCd_03(String data) {
		inputValue(frtChrgMethCd_03,data);
	}

	/**
	 * get  TRSMT_METH_TP_CD_01.
	 * @param idx1 Index Number
	 * @return TRSMT_METH_TP_CD_01
	 */
	public String getTrsmtMethTpCd_01(int idx1) {
	 	 return outputValue(trsmtMethTpCd_01, idx1);
	}

	/**
	 * get  TRSMT_METH_TP_NM_02.
	 * @param idx1 Index Number
	 * @return TRSMT_METH_TP_NM_02
	 */
	public String getTrsmtMethTpNm_02(int idx1) {
	 	 return outputValue(trsmtMethTpNm_02, idx1);
	}

	/**
	 * get  TRSMT_METH_TP_CD_03.
	 * @return TRSMT_METH_TP_CD_03
	 */
	public String getTrsmtMethTpCd_03() {
		return outputValue(trsmtMethTpCd_03);
	}

	/**
	 * set  TRSMT_METH_TP_CD_03.
	 * @param data TRSMT_METH_TP_CD_03
	 */
	public void setTrsmtMethTpCd_03(String data) {
		inputValue(trsmtMethTpCd_03,data);
	}

	/**
	 * get  SEND_PO_EML_ADDR_01.
	 * @return SEND_PO_EML_ADDR_01
	 */
	public String getSendPoEmlAddr_01() {
		return outputValue(sendPoEmlAddr_01);
	}

	/**
	 * set  SEND_PO_EML_ADDR_01.
	 * @param data SEND_PO_EML_ADDR_01
	 */
	public void setSendPoEmlAddr_01(String data) {
		inputValue(sendPoEmlAddr_01,data);
	}

	/**
	 * get  INV_RCV_METH_TP_CD_01.
	 * @param idx1 Index Number
	 * @return INV_RCV_METH_TP_CD_01
	 */
	public String getInvRcvMethTpCd_01(int idx1) {
	 	 return outputValue(invRcvMethTpCd_01, idx1);
	}

	/**
	 * get  INV_RCV_METH_TP_NM_02.
	 * @param idx1 Index Number
	 * @return INV_RCV_METH_TP_NM_02
	 */
	public String getInvRcvMethTpNm_02(int idx1) {
	 	 return outputValue(invRcvMethTpNm_02, idx1);
	}

	/**
	 * get  INV_RCV_METH_TP_CD_03.
	 * @return INV_RCV_METH_TP_CD_03
	 */
	public String getInvRcvMethTpCd_03() {
		return outputValue(invRcvMethTpCd_03);
	}

	/**
	 * set  INV_RCV_METH_TP_CD_03.
	 * @param data INV_RCV_METH_TP_CD_03
	 */
	public void setInvRcvMethTpCd_03(String data) {
		inputValue(invRcvMethTpCd_03,data);
	}

	/**
	 * get  THIRD_PTY_VND_FLG_01.
	 * @return THIRD_PTY_VND_FLG_01
	 */
	public String getThirdPtyVndFlg_01() {
		return outputValue(thirdPtyVndFlg_01);
	}

	/**
	 * set  THIRD_PTY_VND_FLG_01.
	 * @param data THIRD_PTY_VND_FLG_01
	 */
	public void setThirdPtyVndFlg_01(String data) {
		inputValue(thirdPtyVndFlg_01,data);
	}

	/**
	 * get  XX_SCR_EVENT_NM_01.
	 * @return XX_SCR_EVENT_NM_01
	 */
	public String getXxScrEventNm_01() {
		return outputValue(xxScrEventNm_01);
	}

	/**
	 * set  XX_SCR_EVENT_NM_01.
	 * @param data XX_SCR_EVENT_NM_01
	 */
	public void setXxScrEventNm_01(String data) {
		inputValue(xxScrEventNm_01,data);
	}

	/**
	 * get  CHRG_RATE_VND_GRP_CD_01.
	 * @param idx1 Index Number
	 * @return CHRG_RATE_VND_GRP_CD_01
	 */
	public String getChrgRateVndGrpCd_01(int idx1) {
	 	 return outputValue(chrgRateVndGrpCd_01, idx1);
	}

	/**
	 * get  CHRG_RATE_VND_GRP_CD_02.
	 * @param idx1 Index Number
	 * @return CHRG_RATE_VND_GRP_CD_02
	 */
	public String getChrgRateVndGrpCd_02(int idx1) {
	 	 return outputValue(chrgRateVndGrpCd_02, idx1);
	}

	/**
	 * get  CHRG_RATE_VND_GRP_CD_03.
	 * @return CHRG_RATE_VND_GRP_CD_03
	 */
	public String getChrgRateVndGrpCd_03() {
		return outputValue(chrgRateVndGrpCd_03);
	}

	/**
	 * set  CHRG_RATE_VND_GRP_CD_03.
	 * @param data CHRG_RATE_VND_GRP_CD_03
	 */
	public void setChrgRateVndGrpCd_03(String data) {
		inputValue(chrgRateVndGrpCd_03,data);
	}

	/**
	 * get  WH_PK_01.
	 * @param idx1 Index Number
	 * @return WH_PK_01
	 */
	public String getWhPk_01(int idx1) {
	 	 return outputValue(whPk_01, idx1);
	}

	/**
	 * get  WH_CD_02.
	 * @param idx1 Index Number
	 * @return WH_CD_02
	 */
	public String getWhCd_02(int idx1) {
	 	 return outputValue(whCd_02, idx1);
	}

	/**
	 * get  WH_PK_03.
	 * @return WH_PK_03
	 */
	public String getWhPk_03() {
		return outputValue(whPk_03);
	}

	/**
	 * set  WH_PK_03.
	 * @param data WH_PK_03
	 */
	public void setWhPk_03(String data) {
		inputValue(whPk_03,data);
	}

	/**
	 * get  DEAL_CCY_CD_01.
	 * @return DEAL_CCY_CD_01
	 */
	public String getDealCcyCd_01() {
		return outputValue(dealCcyCd_01);
	}

	/**
	 * set  DEAL_CCY_CD_01.
	 * @param data DEAL_CCY_CD_01
	 */
	public void setDealCcyCd_01(String data) {
		inputValue(dealCcyCd_01,data);
	}

	/**
	 * get  INV_VND_CD_01.
	 * @return INV_VND_CD_01
	 */
	public String getInvVndCd_01() {
		return outputValue(invVndCd_01);
	}

	/**
	 * set  INV_VND_CD_01.
	 * @param data INV_VND_CD_01
	 */
	public void setInvVndCd_01(String data) {
		inputValue(invVndCd_01,data);
	}

	/**
	 * get  LOC_NM_02.
	 * @return LOC_NM_02
	 */
	public String getLocNm_02() {
		return outputValue(locNm_02);
	}

	/**
	 * set  LOC_NM_02.
	 * @param data LOC_NM_02
	 */
	public void setLocNm_02(String data) {
		inputValue(locNm_02,data);
	}

	/**
	 * get  _EZUpdateDateTime_01.
	 * @return _EZUpdateDateTime_01
	 */
	public String getEzUpTime_01() {
		return outputValue(ezUpTime_01);
	}

	/**
	 * set  _EZUpdateDateTime_01.
	 * @param data _EZUpdateDateTime_01
	 */
	public void setEzUpTime_01(String data) {
		inputValue(ezUpTime_01,data);
	}

	/**
	 * get  _EZUpTimeZone_01.
	 * @return _EZUpTimeZone_01
	 */
	public String getEzUpTimeZone_01() {
		return outputValue(ezUpTimeZone_01);
	}

	/**
	 * set  _EZUpTimeZone_01.
	 * @param data _EZUpTimeZone_01
	 */
	public void setEzUpTimeZone_01(String data) {
		inputValue(ezUpTimeZone_01,data);
	}

	/**
	 * get  _EZUpdateDateTime_02.
	 * @return _EZUpdateDateTime_02
	 */
	public String getEzUpTime_02() {
		return outputValue(ezUpTime_02);
	}

	/**
	 * set  _EZUpdateDateTime_02.
	 * @param data _EZUpdateDateTime_02
	 */
	public void setEzUpTime_02(String data) {
		inputValue(ezUpTime_02,data);
	}

	/**
	 * get  _EZUpTimeZone_02.
	 * @return _EZUpTimeZone_02
	 */
	public String getEzUpTimeZone_02() {
		return outputValue(ezUpTimeZone_02);
	}

	/**
	 * set  _EZUpTimeZone_02.
	 * @param data _EZUpTimeZone_02
	 */
	public void setEzUpTimeZone_02(String data) {
		inputValue(ezUpTimeZone_02,data);
	}

	/**
	 * get  _EZUpdateDateTime_03.
	 * @return _EZUpdateDateTime_03
	 */
	public String getEzUpTime_03() {
		return outputValue(ezUpTime_03);
	}

	/**
	 * set  _EZUpdateDateTime_03.
	 * @param data _EZUpdateDateTime_03
	 */
	public void setEzUpTime_03(String data) {
		inputValue(ezUpTime_03,data);
	}

	/**
	 * get  _EZUpTimeZone_03.
	 * @return _EZUpTimeZone_03
	 */
	public String getEzUpTimeZone_03() {
		return outputValue(ezUpTimeZone_03);
	}

	/**
	 * set  _EZUpTimeZone_03.
	 * @param data _EZUpTimeZone_03
	 */
	public void setEzUpTimeZone_03(String data) {
		inputValue(ezUpTimeZone_03,data);
	}

	/**
	 * get  XX_CHK_BOX_A1.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A1
	 */
	public String getXxChkBox_A1(int idx1) {
		return outputValue(A, idx1, xxChkBox_A1);
	}

	/**
	 * set  XX_CHK_BOX_A1.
	 * @param data XX_CHK_BOX_A1Array
	 */
	public void setXxChkBox_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A1, data[j]);
		}
	}

	/**
	 * get  VND_TP_CD_A1.
	 * @param idx1 List Number
	 * @return VND_TP_CD_A1
	 */
	public String getVndTpCd_A1(int idx1) {
		return outputValue(A, idx1, vndTpCd_A1);
	}

	/**
	 * set  VND_TP_CD_A1.
	 * @param data VND_TP_CD_A1Array
	 */
	public void setVndTpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vndTpCd_A1, data[j]);
		}
	}

	/**
	 * get  VND_TP_NM_A1.
	 * @param idx1 List Number
	 * @return VND_TP_NM_A1
	 */
	public String getVndTpNm_A1(int idx1) {
		return outputValue(A, idx1, vndTpNm_A1);
	}

	/**
	 * set  VND_TP_NM_A1.
	 * @param data VND_TP_NM_A1Array
	 */
	public void setVndTpNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vndTpNm_A1, data[j]);
		}
	}

	/**
	 * get  _EZUpdateDateTime_A1.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_A1
	 */
	public String getEzUpTime_A1(int idx1) {
		return outputValue(A, idx1, ezUpTime_A1);
	}

	/**
	 * set  _EZUpdateDateTime_A1.
	 * @param data _EZUpdateDateTime_A1Array
	 */
	public void setEzUpTime_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTime_A1, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_A1.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_A1
	 */
	public String getEzUpTimeZone_A1(int idx1) {
		return outputValue(A, idx1, ezUpTimeZone_A1);
	}

	/**
	 * set  _EZUpTimeZone_A1.
	 * @param data _EZUpTimeZone_A1Array
	 */
	public void setEzUpTimeZone_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTimeZone_A1, data[j]);
		}
	}

	/**
	 * get  ST_CD_B1.
	 * @param idx1 List Number
	 * @return ST_CD_B1
	 */
	public String getStCd_B1(int idx1) {
		return outputValue(B, idx1, stCd_B1);
	}

	/**
	 * set  ST_CD_B1.
	 * @param data ST_CD_B1Array
	 */
	public void setStCd_B1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, stCd_B1, data[j]);
		}
	}

	/**
	 * get  ST_NM_B1.
	 * @param idx1 List Number
	 * @return ST_NM_B1
	 */
	public String getStNm_B1(int idx1) {
		return outputValue(B, idx1, stNm_B1);
	}

	/**
	 * set  ST_NM_B1.
	 * @param data ST_NM_B1Array
	 */
	public void setStNm_B1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, stNm_B1, data[j]);
		}
	}

	/**
	 * get  CNTY_PK_C1.
	 * @param idx1 List Number
	 * @return CNTY_PK_C1
	 */
	public String getCntyPk_C1(int idx1) {
		return outputValue(C, idx1, cntyPk_C1);
	}

	/**
	 * set  CNTY_PK_C1.
	 * @param data CNTY_PK_C1Array
	 */
	public void setCntyPk_C1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(C);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(C, i, cntyPk_C1, data[j]);
		}
	}

	/**
	 * get  CNTY_NM_C1.
	 * @param idx1 List Number
	 * @return CNTY_NM_C1
	 */
	public String getCntyNm_C1(int idx1) {
		return outputValue(C, idx1, cntyNm_C1);
	}

	/**
	 * set  CNTY_NM_C1.
	 * @param data CNTY_NM_C1Array
	 */
	public void setCntyNm_C1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(C);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(C, i, cntyNm_C1, data[j]);
		}
	}

	/**
	 * get  XX_TBL_NM_Z1.
	 * @return XX_TBL_NM_Z1
	 */
	public String getXxTblNm_Z1() {
		return outputValue(xxTblNm_Z1);
	}

	/**
	 * set  XX_TBL_NM_Z1.
	 * @param data XX_TBL_NM_Z1
	 */
	public void setXxTblNm_Z1(String data) {
		inputValue(xxTblNm_Z1,data);
	}

	/**
	 * get  XX_TBL_CD_COL_NM_Z1.
	 * @return XX_TBL_CD_COL_NM_Z1
	 */
	public String getXxTblCdColNm_Z1() {
		return outputValue(xxTblCdColNm_Z1);
	}

	/**
	 * set  XX_TBL_CD_COL_NM_Z1.
	 * @param data XX_TBL_CD_COL_NM_Z1
	 */
	public void setXxTblCdColNm_Z1(String data) {
		inputValue(xxTblCdColNm_Z1,data);
	}

	/**
	 * get  XX_TBL_NM_COL_NM_Z1.
	 * @return XX_TBL_NM_COL_NM_Z1
	 */
	public String getXxTblNmColNm_Z1() {
		return outputValue(xxTblNmColNm_Z1);
	}

	/**
	 * set  XX_TBL_NM_COL_NM_Z1.
	 * @param data XX_TBL_NM_COL_NM_Z1
	 */
	public void setXxTblNmColNm_Z1(String data) {
		inputValue(xxTblNmColNm_Z1,data);
	}

	/**
	 * get  XX_TBL_SORT_COL_NM_Z1.
	 * @return XX_TBL_SORT_COL_NM_Z1
	 */
	public String getXxTblSortColNm_Z1() {
		return outputValue(xxTblSortColNm_Z1);
	}

	/**
	 * set  XX_TBL_SORT_COL_NM_Z1.
	 * @param data XX_TBL_SORT_COL_NM_Z1
	 */
	public void setXxTblSortColNm_Z1(String data) {
		inputValue(xxTblSortColNm_Z1,data);
	}

	/**
	 * get  XX_SCR_NM_Z1.
	 * @return XX_SCR_NM_Z1
	 */
	public String getXxScrNm_Z1() {
		return outputValue(xxScrNm_Z1);
	}

	/**
	 * set  XX_SCR_NM_Z1.
	 * @param data XX_SCR_NM_Z1
	 */
	public void setXxScrNm_Z1(String data) {
		inputValue(xxScrNm_Z1,data);
	}

	/**
	 * get  XX_HDR_CD_LB_NM_Z1.
	 * @return XX_HDR_CD_LB_NM_Z1
	 */
	public String getXxHdrCdLbNm_Z1() {
		return outputValue(xxHdrCdLbNm_Z1);
	}

	/**
	 * set  XX_HDR_CD_LB_NM_Z1.
	 * @param data XX_HDR_CD_LB_NM_Z1
	 */
	public void setXxHdrCdLbNm_Z1(String data) {
		inputValue(xxHdrCdLbNm_Z1,data);
	}

	/**
	 * get  XX_HDR_NM_LB_NM_Z1.
	 * @return XX_HDR_NM_LB_NM_Z1
	 */
	public String getXxHdrNmLbNm_Z1() {
		return outputValue(xxHdrNmLbNm_Z1);
	}

	/**
	 * set  XX_HDR_NM_LB_NM_Z1.
	 * @param data XX_HDR_NM_LB_NM_Z1
	 */
	public void setXxHdrNmLbNm_Z1(String data) {
		inputValue(xxHdrNmLbNm_Z1,data);
	}

	/**
	 * get  XX_DTL_CD_LB_NM_Z1.
	 * @return XX_DTL_CD_LB_NM_Z1
	 */
	public String getXxDtlCdLbNm_Z1() {
		return outputValue(xxDtlCdLbNm_Z1);
	}

	/**
	 * set  XX_DTL_CD_LB_NM_Z1.
	 * @param data XX_DTL_CD_LB_NM_Z1
	 */
	public void setXxDtlCdLbNm_Z1(String data) {
		inputValue(xxDtlCdLbNm_Z1,data);
	}

	/**
	 * get  XX_DTL_NM_LB_NM_Z1.
	 * @return XX_DTL_NM_LB_NM_Z1
	 */
	public String getXxDtlNmLbNm_Z1() {
		return outputValue(xxDtlNmLbNm_Z1);
	}

	/**
	 * set  XX_DTL_NM_LB_NM_Z1.
	 * @param data XX_DTL_NM_LB_NM_Z1
	 */
	public void setXxDtlNmLbNm_Z1(String data) {
		inputValue(xxDtlNmLbNm_Z1,data);
	}

	/**
	 * get  XX_COND_CD_Z1.
	 * @return XX_COND_CD_Z1
	 */
	public String getXxCondCd_Z1() {
		return outputValue(xxCondCd_Z1);
	}

	/**
	 * set  XX_COND_CD_Z1.
	 * @param data XX_COND_CD_Z1
	 */
	public void setXxCondCd_Z1(String data) {
		inputValue(xxCondCd_Z1,data);
	}

	/**
	 * get  XX_COND_NM_Z1.
	 * @return XX_COND_NM_Z1
	 */
	public String getXxCondNm_Z1() {
		return outputValue(xxCondNm_Z1);
	}

	/**
	 * set  XX_COND_NM_Z1.
	 * @param data XX_COND_NM_Z1
	 */
	public void setXxCondNm_Z1(String data) {
		inputValue(xxCondNm_Z1,data);
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(P, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(P, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxSelFlg, data[j]);
		}
	}

	/**
	 * get  XX_LINK_PROT_01.
	 * @return XX_LINK_PROT_01
	 */
	public String getXxLinkProt_01() {
		return outputValue(xxLinkProt_01);
	}

	/**
	 * set  XX_LINK_PROT_01.
	 * @param data XX_LINK_PROT_01
	 */
	public void setXxLinkProt_01(String data) {
		inputValue(xxLinkProt_01,data);
	}

	/**
	 * get  XX_LINK_PROT_02.
	 * @return XX_LINK_PROT_02
	 */
	public String getXxLinkProt_02() {
		return outputValue(xxLinkProt_02);
	}

	/**
	 * set  XX_LINK_PROT_02.
	 * @param data XX_LINK_PROT_02
	 */
	public void setXxLinkProt_02(String data) {
		inputValue(xxLinkProt_02,data);
	}

	/**
	 * get  XX_LINK_PROT_03.
	 * @return XX_LINK_PROT_03
	 */
	public String getXxLinkProt_03() {
		return outputValue(xxLinkProt_03);
	}

	/**
	 * set  XX_LINK_PROT_03.
	 * @param data XX_LINK_PROT_03
	 */
	public void setXxLinkProt_03(String data) {
		inputValue(xxLinkProt_03,data);
	}

	/**
	 * get  XX_LINK_PROT_04.
	 * @return XX_LINK_PROT_04
	 */
	public String getXxLinkProt_04() {
		return outputValue(xxLinkProt_04);
	}

	/**
	 * set  XX_LINK_PROT_04.
	 * @param data XX_LINK_PROT_04
	 */
	public void setXxLinkProt_04(String data) {
		inputValue(xxLinkProt_04,data);
	}

	/**
	 * get  XX_LINK_PROT_05.
	 * @return XX_LINK_PROT_05
	 */
	public String getXxLinkProt_05() {
		return outputValue(xxLinkProt_05);
	}

	/**
	 * set  XX_LINK_PROT_05.
	 * @param data XX_LINK_PROT_05
	 */
	public void setXxLinkProt_05(String data) {
		inputValue(xxLinkProt_05,data);
	}

	/**
	 * get  XX_LINK_PROT_06.
	 * @return XX_LINK_PROT_06
	 */
	public String getXxLinkProt_06() {
		return outputValue(xxLinkProt_06);
	}

	/**
	 * set  XX_LINK_PROT_06.
	 * @param data XX_LINK_PROT_06
	 */
	public void setXxLinkProt_06(String data) {
		inputValue(xxLinkProt_06,data);
	}

	/**
	 * get  XX_LINK_ANCR_AL.
	 * @return XX_LINK_ANCR_AL
	 */
	public String getXxLinkAncr_AL() {
		return outputValue(xxLinkAncr_AL);
	}

	/**
	 * set  XX_LINK_ANCR_AL.
	 * @param data XX_LINK_ANCR_AL
	 */
	public void setXxLinkAncr_AL(String data) {
		inputValue(xxLinkAncr_AL,data);
	}

	/**
	 * get  XX_POP_PRM.
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm() {
		return outputValue(xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRM
	 */
	public void setXxPopPrm(String data) {
		inputValue(xxPopPrm,data);
	}

}
