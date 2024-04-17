
/*
Select * from CANON_E479_SMPL_INV_DTL_TBL
where INV_ID = 1
and CONTROL_TYPE = 'REMITTANCESTUB'
(
INV_ID NUMBER,
SEQ_NO NUMBER,
CONTROL_TYPE VARCHAR2,
TAB_NAME VARCHAR2,
FIELD1 VARCHAR2,
FIELD2 VARCHAR2,
FIELD3 VARCHAR2,
FIELD4 VARCHAR2,
FIELD5 VARCHAR2,
FIELD6 VARCHAR2,
FIELD7 VARCHAR2,
FIELD8 VARCHAR2,
FIELD9 VARCHAR2,
FIELD10 VARCHAR2,
FIELD11 VARCHAR2,
FIELD12 VARCHAR2,
FIELD13 VARCHAR2,
FIELD14 VARCHAR2,
FIELD15 VARCHAR2,
FIELD16 VARCHAR2,
FIELD17 VARCHAR2,
FIELD18 VARCHAR2,
FIELD19 VARCHAR2,
FIELD20 VARCHAR2,
FIELD21 VARCHAR2,
FIELD22 VARCHAR2,
FIELD23 VARCHAR2,
FIELD24 VARCHAR2,
FIELD25 VARCHAR2,
FIELD26 VARCHAR2,
FIELD27 VARCHAR2,
FIELD28 VARCHAR2,
FIELD29 VARCHAR2,
FIELD30 VARCHAR2,
FIELD31 VARCHAR2,
FIELD32 VARCHAR2,
FIELD33 VARCHAR2,
FIELD34 VARCHAR2,
FIELD35 VARCHAR2,
FIELD36 VARCHAR2,
FIELD37 VARCHAR2,
FIELD38 VARCHAR2,
FIELD39 VARCHAR2,
FIELD40 VARCHAR2,
FIELD41 VARCHAR2,
FIELD42 VARCHAR2,
FIELD43 VARCHAR2,
FIELD44 VARCHAR2,
FIELD45 VARCHAR2,
FIELD46 VARCHAR2,
FIELD47 VARCHAR2,
FIELD48 VARCHAR2,
FIELD49 VARCHAR2,
FIELD50 VARCHAR2,
FIELD51 VARCHAR2,
FIELD52 VARCHAR2,
FIELD53 VARCHAR2,
FIELD54 VARCHAR2,
FIELD55 VARCHAR2,
FIELD56 VARCHAR2,
FIELD57 VARCHAR2,
FIELD58 VARCHAR2,
FIELD59 VARCHAR2,
FIELD60 VARCHAR2,
FIELD61 VARCHAR2,
FIELD62 VARCHAR2,
FIELD63 VARCHAR2,
FIELD64 VARCHAR2,
FIELD65 VARCHAR2,
FIELD66 VARCHAR2,
FIELD67 VARCHAR2,
FIELD68 VARCHAR2,
FIELD69 VARCHAR2,
FIELD70 VARCHAR2,
FIELD71 VARCHAR2,
FIELD72 VARCHAR2,
FIELD73 VARCHAR2,
FIELD74 VARCHAR2,
FIELD75 VARCHAR2,
FIELD76 VARCHAR2,
FIELD77 VARCHAR2,
FIELD78 VARCHAR2,
FIELD79 VARCHAR2,
FIELD80 VARCHAR2,
FIELD81 VARCHAR2,
FIELD82 VARCHAR2,
FIELD83 VARCHAR2,
FIELD84 VARCHAR2,
FIELD85 VARCHAR2,
FIELD86 VARCHAR2,
FIELD87 VARCHAR2,
FIELD88 VARCHAR2,
FIELD89 VARCHAR2,
FIELD90 VARCHAR2,
FIELD91 VARCHAR2,
FIELD92 VARCHAR2,
FIELD93 VARCHAR2,
FIELD94 VARCHAR2,
FIELD95 VARCHAR2,
FIELD96 VARCHAR2,
FIELD97 VARCHAR2,
FIELD98 VARCHAR2,
FIELD99 VARCHAR2,
FIELD100 VARCHAR2,
CREATED_BY NUMBER,
CREATION_DATE DATE,
LAST_UPDATED_BY NUMBER,
LAST_UPDATE_DATE DATE
)
*/
package oracle.apps.custombilling.server;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
public class CanonCustomBillingServerRemittanceStubBean {
   private BigDecimal invId;
   private BigDecimal seqNo;
   private String controlType;
   private String tabName;
   private String field1;
   private String field2;
   private String field3;
   private String field4;
   private String field5;
   private String field6;
   private String field7;
   private String field8;
   private String field9;
   private String field10;
   private String field11;
   private String field12;
   private String field13;
   private String field14;
   private String field15;
   private String field16;
   private String field17;
   private String field18;
   private String field19;
   private String field20;
   private String field21;
   private String field22;
   private String field23;
   private String field24;
   private String field25;
   private String field26;
   private String field27;
   private String field28;
   private String field29;
   private String field30;
   private String field31;
   private String field32;
   private String field33;
   private String field34;
   private String field35;
   private String field36;
   private String field37;
   private String field38;
   private String field39;
   private String field40;
   private String field41;
   private String field42;
   private String field43;
   private String field44;
   private String field45;
   private String field46;
   private String field47;
   private String field48;
   private String field49;
   private String field50;
   private String field51;
   private String field52;
   private String field53;
   private String field54;
   private String field55;
   private String field56;
   private String field57;
   private String field58;
   private String field59;
   private String field60;
   private String field61;
   private String field62;
   private String field63;
   private String field64;
   private String field65;
   private String field66;
   private String field67;
   private String field68;
   private String field69;
   private String field70;
   private String field71;
   private String field72;
   private String field73;
   private String field74;
   private String field75;
   private String field76;
   private String field77;
   private String field78;
   private String field79;
   private String field80;
   private String field81;
   private String field82;
   private String field83;
   private String field84;
   private String field85;
   private String field86;
   private String field87;
   private String field88;
   private String field89;
   private String field90;
   private String field91;
   private String field92;
   private String field93;
   private String field94;
   private String field95;
   private String field96;
   private String field97;
   private String field98;
   private String field99;
   private String field100;
   private BigDecimal createdBy;
   private Timestamp creationDate;
   private BigDecimal lastUpdatedBy;
   private Timestamp lastUpdateDate;

    public CanonCustomBillingServerRemittanceStubBean(){
    }
    public CanonCustomBillingServerRemittanceStubBean(BigDecimal invId, 
            BigDecimal seqNo, 
            String controlType, 
            String tabName, 
            String field1, 
            String field2, 
            String field3, 
            String field4, 
            String field5, 
            String field6, 
            String field7, 
            String field8, 
            String field9, 
            String field10, 
            String field11, 
            String field12, 
            String field13, 
            String field14, 
            String field15, 
            String field16, 
            String field17, 
            String field18, 
            String field19, 
            String field20, 
            String field21, 
            String field22, 
            String field23, 
            String field24, 
            String field25, 
            String field26, 
            String field27, 
            String field28, 
            String field29, 
            String field30, 
            String field31, 
            String field32, 
            String field33, 
            String field34, 
            String field35, 
            String field36, 
            String field37, 
            String field38, 
            String field39, 
            String field40, 
            String field41, 
            String field42, 
            String field43, 
            String field44, 
            String field45, 
            String field46, 
            String field47, 
            String field48, 
            String field49, 
            String field50, 
            String field51, 
            String field52, 
            String field53, 
            String field54, 
            String field55, 
            String field56, 
            String field57, 
            String field58, 
            String field59, 
            String field60, 
            String field61, 
            String field62, 
            String field63, 
            String field64, 
            String field65, 
            String field66, 
            String field67, 
            String field68, 
            String field69, 
            String field70, 
            String field71, 
            String field72, 
            String field73, 
            String field74, 
            String field75, 
            String field76, 
            String field77, 
            String field78, 
            String field79, 
            String field80, 
            String field81, 
            String field82, 
            String field83, 
            String field84, 
            String field85, 
            String field86, 
            String field87, 
            String field88, 
            String field89, 
            String field90, 
            String field91, 
            String field92, 
            String field93, 
            String field94, 
            String field95, 
            String field96, 
            String field97, 
            String field98, 
            String field99, 
            String field100, 
            BigDecimal createdBy, 
            Timestamp creationDate, 
            BigDecimal lastUpdatedBy, 
            Timestamp lastUpdateDate){
        this.invId=invId;
        this.seqNo=seqNo;
        this.controlType=controlType;
        this.tabName=tabName;
        this.field1=field1;
        this.field2=field2;
        this.field3=field3;
        this.field4=field4;
        this.field5=field5;
        this.field6=field6;
        this.field7=field7;
        this.field8=field8;
        this.field9=field9;
        this.field10=field10;
        this.field11=field11;
        this.field12=field12;
        this.field13=field13;
        this.field14=field14;
        this.field15=field15;
        this.field16=field16;
        this.field17=field17;
        this.field18=field18;
        this.field19=field19;
        this.field20=field20;
        this.field21=field21;
        this.field22=field22;
        this.field23=field23;
        this.field24=field24;
        this.field25=field25;
        this.field26=field26;
        this.field27=field27;
        this.field28=field28;
        this.field29=field29;
        this.field30=field30;
        this.field31=field31;
        this.field32=field32;
        this.field33=field33;
        this.field34=field34;
        this.field35=field35;
        this.field36=field36;
        this.field37=field37;
        this.field38=field38;
        this.field39=field39;
        this.field40=field40;
        this.field41=field41;
        this.field42=field42;
        this.field43=field43;
        this.field44=field44;
        this.field45=field45;
        this.field46=field46;
        this.field47=field47;
        this.field48=field48;
        this.field49=field49;
        this.field50=field50;
        this.field51=field51;
        this.field52=field52;
        this.field53=field53;
        this.field54=field54;
        this.field55=field55;
        this.field56=field56;
        this.field57=field57;
        this.field58=field58;
        this.field59=field59;
        this.field60=field60;
        this.field61=field61;
        this.field62=field62;
        this.field63=field63;
        this.field64=field64;
        this.field65=field65;
        this.field66=field66;
        this.field67=field67;
        this.field68=field68;
        this.field69=field69;
        this.field70=field70;
        this.field71=field71;
        this.field72=field72;
        this.field73=field73;
        this.field74=field74;
        this.field75=field75;
        this.field76=field76;
        this.field77=field77;
        this.field78=field78;
        this.field79=field79;
        this.field80=field80;
        this.field81=field81;
        this.field82=field82;
        this.field83=field83;
        this.field84=field84;
        this.field85=field85;
        this.field86=field86;
        this.field87=field87;
        this.field88=field88;
        this.field89=field89;
        this.field90=field90;
        this.field91=field91;
        this.field92=field92;
        this.field93=field93;
        this.field94=field94;
        this.field95=field95;
        this.field96=field96;
        this.field97=field97;
        this.field98=field98;
        this.field99=field99;
        this.field100=field100;
        this.createdBy=createdBy;
        this.creationDate=creationDate;
        this.lastUpdatedBy=lastUpdatedBy;
        this.lastUpdateDate=lastUpdateDate;
    }
    public BigDecimal getInvId() {
        return invId;
    }
    public void setInvId(BigDecimal invId) {
        this.invId=invId;
    }
    public BigDecimal getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(BigDecimal seqNo) {
        this.seqNo=seqNo;
    }
    public String getControlType() {
        return controlType;
    }
    public void setControlType(String controlType) {
        this.controlType=controlType;
    }
    public String getTabName() {
        return tabName;
    }
    public void setTabName(String tabName) {
        this.tabName=tabName;
    }
    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1=field1;
    }
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2=field2;
    }
    public String getField3() {
        return field3;
    }
    public void setField3(String field3) {
        this.field3=field3;
    }
    public String getField4() {
        return field4;
    }
    public void setField4(String field4) {
        this.field4=field4;
    }
    public String getField5() {
        return field5;
    }
    public void setField5(String field5) {
        this.field5=field5;
    }
    public String getField6() {
        return field6;
    }
    public void setField6(String field6) {
        this.field6=field6;
    }
    public String getField7() {
        return field7;
    }
    public void setField7(String field7) {
        this.field7=field7;
    }
    public String getField8() {
        return field8;
    }
    public void setField8(String field8) {
        this.field8=field8;
    }
    public String getField9() {
        return field9;
    }
    public void setField9(String field9) {
        this.field9=field9;
    }
    public String getField10() {
        return field10;
    }
    public void setField10(String field10) {
        this.field10=field10;
    }
    public String getField11() {
        return field11;
    }
    public void setField11(String field11) {
        this.field11=field11;
    }
    public String getField12() {
        return field12;
    }
    public void setField12(String field12) {
        this.field12=field12;
    }
    public String getField13() {
        return field13;
    }
    public void setField13(String field13) {
        this.field13=field13;
    }
    public String getField14() {
        return field14;
    }
    public void setField14(String field14) {
        this.field14=field14;
    }
    public String getField15() {
        return field15;
    }
    public void setField15(String field15) {
        this.field15=field15;
    }
    public String getField16() {
        return field16;
    }
    public void setField16(String field16) {
        this.field16=field16;
    }
    public String getField17() {
        return field17;
    }
    public void setField17(String field17) {
        this.field17=field17;
    }
    public String getField18() {
        return field18;
    }
    public void setField18(String field18) {
        this.field18=field18;
    }
    public String getField19() {
        return field19;
    }
    public void setField19(String field19) {
        this.field19=field19;
    }
    public String getField20() {
        return field20;
    }
    public void setField20(String field20) {
        this.field20=field20;
    }
    public String getField21() {
        return field21;
    }
    public void setField21(String field21) {
        this.field21=field21;
    }
    public String getField22() {
        return field22;
    }
    public void setField22(String field22) {
        this.field22=field22;
    }
    public String getField23() {
        return field23;
    }
    public void setField23(String field23) {
        this.field23=field23;
    }
    public String getField24() {
        return field24;
    }
    public void setField24(String field24) {
        this.field24=field24;
    }
    public String getField25() {
        return field25;
    }
    public void setField25(String field25) {
        this.field25=field25;
    }
    public String getField26() {
        return field26;
    }
    public void setField26(String field26) {
        this.field26=field26;
    }
    public String getField27() {
        return field27;
    }
    public void setField27(String field27) {
        this.field27=field27;
    }
    public String getField28() {
        return field28;
    }
    public void setField28(String field28) {
        this.field28=field28;
    }
    public String getField29() {
        return field29;
    }
    public void setField29(String field29) {
        this.field29=field29;
    }
    public String getField30() {
        return field30;
    }
    public void setField30(String field30) {
        this.field30=field30;
    }
    public String getField31() {
        return field31;
    }
    public void setField31(String field31) {
        this.field31=field31;
    }
    public String getField32() {
        return field32;
    }
    public void setField32(String field32) {
        this.field32=field32;
    }
    public String getField33() {
        return field33;
    }
    public void setField33(String field33) {
        this.field33=field33;
    }
    public String getField34() {
        return field34;
    }
    public void setField34(String field34) {
        this.field34=field34;
    }
    public String getField35() {
        return field35;
    }
    public void setField35(String field35) {
        this.field35=field35;
    }
    public String getField36() {
        return field36;
    }
    public void setField36(String field36) {
        this.field36=field36;
    }
    public String getField37() {
        return field37;
    }
    public void setField37(String field37) {
        this.field37=field37;
    }
    public String getField38() {
        return field38;
    }
    public void setField38(String field38) {
        this.field38=field38;
    }
    public String getField39() {
        return field39;
    }
    public void setField39(String field39) {
        this.field39=field39;
    }
    public String getField40() {
        return field40;
    }
    public void setField40(String field40) {
        this.field40=field40;
    }
    public String getField41() {
        return field41;
    }
    public void setField41(String field41) {
        this.field41=field41;
    }
    public String getField42() {
        return field42;
    }
    public void setField42(String field42) {
        this.field42=field42;
    }
    public String getField43() {
        return field43;
    }
    public void setField43(String field43) {
        this.field43=field43;
    }
    public String getField44() {
        return field44;
    }
    public void setField44(String field44) {
        this.field44=field44;
    }
    public String getField45() {
        return field45;
    }
    public void setField45(String field45) {
        this.field45=field45;
    }
    public String getField46() {
        return field46;
    }
    public void setField46(String field46) {
        this.field46=field46;
    }
    public String getField47() {
        return field47;
    }
    public void setField47(String field47) {
        this.field47=field47;
    }
    public String getField48() {
        return field48;
    }
    public void setField48(String field48) {
        this.field48=field48;
    }
    public String getField49() {
        return field49;
    }
    public void setField49(String field49) {
        this.field49=field49;
    }
    public String getField50() {
        return field50;
    }
    public void setField50(String field50) {
        this.field50=field50;
    }
    public String getField51() {
        return field51;
    }
    public void setField51(String field51) {
        this.field51=field51;
    }
    public String getField52() {
        return field52;
    }
    public void setField52(String field52) {
        this.field52=field52;
    }
    public String getField53() {
        return field53;
    }
    public void setField53(String field53) {
        this.field53=field53;
    }
    public String getField54() {
        return field54;
    }
    public void setField54(String field54) {
        this.field54=field54;
    }
    public String getField55() {
        return field55;
    }
    public void setField55(String field55) {
        this.field55=field55;
    }
    public String getField56() {
        return field56;
    }
    public void setField56(String field56) {
        this.field56=field56;
    }
    public String getField57() {
        return field57;
    }
    public void setField57(String field57) {
        this.field57=field57;
    }
    public String getField58() {
        return field58;
    }
    public void setField58(String field58) {
        this.field58=field58;
    }
    public String getField59() {
        return field59;
    }
    public void setField59(String field59) {
        this.field59=field59;
    }
    public String getField60() {
        return field60;
    }
    public void setField60(String field60) {
        this.field60=field60;
    }
    public String getField61() {
        return field61;
    }
    public void setField61(String field61) {
        this.field61=field61;
    }
    public String getField62() {
        return field62;
    }
    public void setField62(String field62) {
        this.field62=field62;
    }
    public String getField63() {
        return field63;
    }
    public void setField63(String field63) {
        this.field63=field63;
    }
    public String getField64() {
        return field64;
    }
    public void setField64(String field64) {
        this.field64=field64;
    }
    public String getField65() {
        return field65;
    }
    public void setField65(String field65) {
        this.field65=field65;
    }
    public String getField66() {
        return field66;
    }
    public void setField66(String field66) {
        this.field66=field66;
    }
    public String getField67() {
        return field67;
    }
    public void setField67(String field67) {
        this.field67=field67;
    }
    public String getField68() {
        return field68;
    }
    public void setField68(String field68) {
        this.field68=field68;
    }
    public String getField69() {
        return field69;
    }
    public void setField69(String field69) {
        this.field69=field69;
    }
    public String getField70() {
        return field70;
    }
    public void setField70(String field70) {
        this.field70=field70;
    }
    public String getField71() {
        return field71;
    }
    public void setField71(String field71) {
        this.field71=field71;
    }
    public String getField72() {
        return field72;
    }
    public void setField72(String field72) {
        this.field72=field72;
    }
    public String getField73() {
        return field73;
    }
    public void setField73(String field73) {
        this.field73=field73;
    }
    public String getField74() {
        return field74;
    }
    public void setField74(String field74) {
        this.field74=field74;
    }
    public String getField75() {
        return field75;
    }
    public void setField75(String field75) {
        this.field75=field75;
    }
    public String getField76() {
        return field76;
    }
    public void setField76(String field76) {
        this.field76=field76;
    }
    public String getField77() {
        return field77;
    }
    public void setField77(String field77) {
        this.field77=field77;
    }
    public String getField78() {
        return field78;
    }
    public void setField78(String field78) {
        this.field78=field78;
    }
    public String getField79() {
        return field79;
    }
    public void setField79(String field79) {
        this.field79=field79;
    }
    public String getField80() {
        return field80;
    }
    public void setField80(String field80) {
        this.field80=field80;
    }
    public String getField81() {
        return field81;
    }
    public void setField81(String field81) {
        this.field81=field81;
    }
    public String getField82() {
        return field82;
    }
    public void setField82(String field82) {
        this.field82=field82;
    }
    public String getField83() {
        return field83;
    }
    public void setField83(String field83) {
        this.field83=field83;
    }
    public String getField84() {
        return field84;
    }
    public void setField84(String field84) {
        this.field84=field84;
    }
    public String getField85() {
        return field85;
    }
    public void setField85(String field85) {
        this.field85=field85;
    }
    public String getField86() {
        return field86;
    }
    public void setField86(String field86) {
        this.field86=field86;
    }
    public String getField87() {
        return field87;
    }
    public void setField87(String field87) {
        this.field87=field87;
    }
    public String getField88() {
        return field88;
    }
    public void setField88(String field88) {
        this.field88=field88;
    }
    public String getField89() {
        return field89;
    }
    public void setField89(String field89) {
        this.field89=field89;
    }
    public String getField90() {
        return field90;
    }
    public void setField90(String field90) {
        this.field90=field90;
    }
    public String getField91() {
        return field91;
    }
    public void setField91(String field91) {
        this.field91=field91;
    }
    public String getField92() {
        return field92;
    }
    public void setField92(String field92) {
        this.field92=field92;
    }
    public String getField93() {
        return field93;
    }
    public void setField93(String field93) {
        this.field93=field93;
    }
    public String getField94() {
        return field94;
    }
    public void setField94(String field94) {
        this.field94=field94;
    }
    public String getField95() {
        return field95;
    }
    public void setField95(String field95) {
        this.field95=field95;
    }
    public String getField96() {
        return field96;
    }
    public void setField96(String field96) {
        this.field96=field96;
    }
    public String getField97() {
        return field97;
    }
    public void setField97(String field97) {
        this.field97=field97;
    }
    public String getField98() {
        return field98;
    }
    public void setField98(String field98) {
        this.field98=field98;
    }
    public String getField99() {
        return field99;
    }
    public void setField99(String field99) {
        this.field99=field99;
    }
    public String getField100() {
        return field100;
    }
    public void setField100(String field100) {
        this.field100=field100;
    }
    public BigDecimal getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy=createdBy;
    }
    public Timestamp getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate=creationDate;
    }
    public BigDecimal getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
        this.lastUpdatedBy=lastUpdatedBy;
    }
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate=lastUpdateDate;
    }
    public static CanonCustomBillingServerRowMapper getRowMapper(){
        return new CanonCustomBillingServerRowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CanonCustomBillingServerRemittanceStubBean(
                    rs.getBigDecimal("INV_ID"),
                    rs.getBigDecimal("SEQ_NO"),
                    rs.getString("CONTROL_TYPE"),
                    rs.getString("TAB_NAME"),
                    rs.getString("FIELD1"),
                    rs.getString("FIELD2"),
                    rs.getString("FIELD3"),
                    rs.getString("FIELD4"),
                    rs.getString("FIELD5"),
                    rs.getString("FIELD6"),
                    rs.getString("FIELD7"),
                    rs.getString("FIELD8"),
                    rs.getString("FIELD9"),
                    rs.getString("FIELD10"),
                    rs.getString("FIELD11"),
                    rs.getString("FIELD12"),
                    rs.getString("FIELD13"),
                    rs.getString("FIELD14"),
                    rs.getString("FIELD15"),
                    rs.getString("FIELD16"),
                    rs.getString("FIELD17"),
                    rs.getString("FIELD18"),
                    rs.getString("FIELD19"),
                    rs.getString("FIELD20"),
                    rs.getString("FIELD21"),
                    rs.getString("FIELD22"),
                    rs.getString("FIELD23"),
                    rs.getString("FIELD24"),
                    rs.getString("FIELD25"),
                    rs.getString("FIELD26"),
                    rs.getString("FIELD27"),
                    rs.getString("FIELD28"),
                    rs.getString("FIELD29"),
                    rs.getString("FIELD30"),
                    rs.getString("FIELD31"),
                    rs.getString("FIELD32"),
                    rs.getString("FIELD33"),
                    rs.getString("FIELD34"),
                    rs.getString("FIELD35"),
                    rs.getString("FIELD36"),
                    rs.getString("FIELD37"),
                    rs.getString("FIELD38"),
                    rs.getString("FIELD39"),
                    rs.getString("FIELD40"),
                    rs.getString("FIELD41"),
                    rs.getString("FIELD42"),
                    rs.getString("FIELD43"),
                    rs.getString("FIELD44"),
                    rs.getString("FIELD45"),
                    rs.getString("FIELD46"),
                    rs.getString("FIELD47"),
                    rs.getString("FIELD48"),
                    rs.getString("FIELD49"),
                    rs.getString("FIELD50"),
                    rs.getString("FIELD51"),
                    rs.getString("FIELD52"),
                    rs.getString("FIELD53"),
                    rs.getString("FIELD54"),
                    rs.getString("FIELD55"),
                    rs.getString("FIELD56"),
                    rs.getString("FIELD57"),
                    rs.getString("FIELD58"),
                    rs.getString("FIELD59"),
                    rs.getString("FIELD60"),
                    rs.getString("FIELD61"),
                    rs.getString("FIELD62"),
                    rs.getString("FIELD63"),
                    rs.getString("FIELD64"),
                    rs.getString("FIELD65"),
                    rs.getString("FIELD66"),
                    rs.getString("FIELD67"),
                    rs.getString("FIELD68"),
                    rs.getString("FIELD69"),
                    rs.getString("FIELD70"),
                    rs.getString("FIELD71"),
                    rs.getString("FIELD72"),
                    rs.getString("FIELD73"),
                    rs.getString("FIELD74"),
                    rs.getString("FIELD75"),
                    rs.getString("FIELD76"),
                    rs.getString("FIELD77"),
                    rs.getString("FIELD78"),
                    rs.getString("FIELD79"),
                    rs.getString("FIELD80"),
                    rs.getString("FIELD81"),
                    rs.getString("FIELD82"),
                    rs.getString("FIELD83"),
                    rs.getString("FIELD84"),
                    rs.getString("FIELD85"),
                    rs.getString("FIELD86"),
                    rs.getString("FIELD87"),
                    rs.getString("FIELD88"),
                    rs.getString("FIELD89"),
                    rs.getString("FIELD90"),
                    rs.getString("FIELD91"),
                    rs.getString("FIELD92"),
                    rs.getString("FIELD93"),
                    rs.getString("FIELD94"),
                    rs.getString("FIELD95"),
                    rs.getString("FIELD96"),
                    rs.getString("FIELD97"),
                    rs.getString("FIELD98"),
                    rs.getString("FIELD99"),
                    rs.getString("FIELD100"),
                    rs.getBigDecimal("CREATED_BY"),
                    rs.getTimestamp("CREATION_DATE"),
                    rs.getBigDecimal("LAST_UPDATED_BY"),
                    rs.getTimestamp("LAST_UPDATE_DATE")
                );
            }
        };
    }
    public String toString() {
        return "CanonE479RemittanceStub{" + "invId="+invId+", seqNo="+seqNo+", controlType="+controlType+", tabName="+tabName+", field1="+field1+", field2="+field2+", field3="+field3+", field4="+field4+", field5="+field5+", field6="+field6+", field7="+field7+", field8="+field8+", field9="+field9+", field10="+field10+", field11="+field11+", field12="+field12+", field13="+field13+", field14="+field14+", field15="+field15+", field16="+field16+", field17="+field17+", field18="+field18+", field19="+field19+", field20="+field20+", field21="+field21+", field22="+field22+", field23="+field23+", field24="+field24+", field25="+field25+", field26="+field26+", field27="+field27+", field28="+field28+", field29="+field29+", field30="+field30+", field31="+field31+", field32="+field32+", field33="+field33+", field34="+field34+", field35="+field35+", field36="+field36+", field37="+field37+", field38="+field38+", field39="+field39+", field40="+field40+", field41="+field41+", field42="+field42+", field43="+field43+", field44="+field44+", field45="+field45+", field46="+field46+", field47="+field47+", field48="+field48+", field49="+field49+", field50="+field50+", field51="+field51+", field52="+field52+", field53="+field53+", field54="+field54+", field55="+field55+", field56="+field56+", field57="+field57+", field58="+field58+", field59="+field59+", field60="+field60+", field61="+field61+", field62="+field62+", field63="+field63+", field64="+field64+", field65="+field65+", field66="+field66+", field67="+field67+", field68="+field68+", field69="+field69+", field70="+field70+", field71="+field71+", field72="+field72+", field73="+field73+", field74="+field74+", field75="+field75+", field76="+field76+", field77="+field77+", field78="+field78+", field79="+field79+", field80="+field80+", field81="+field81+", field82="+field82+", field83="+field83+", field84="+field84+", field85="+field85+", field86="+field86+", field87="+field87+", field88="+field88+", field89="+field89+", field90="+field90+", field91="+field91+", field92="+field92+", field93="+field93+", field94="+field94+", field95="+field95+", field96="+field96+", field97="+field97+", field98="+field98+", field99="+field99+", field100="+field100+", createdBy="+createdBy+", creationDate="+creationDate+", lastUpdatedBy="+lastUpdatedBy+", lastUpdateDate="+lastUpdateDate+'}';
    }
}


