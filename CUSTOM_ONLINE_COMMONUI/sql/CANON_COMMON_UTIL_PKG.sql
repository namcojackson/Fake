CREATE OR REPLACE PACKAGE CANON_COMMON_UTIL_PKG
AS
   /****************************************************************************
      *                                                                        *
      * File NAME       : CANON_COMMON_UTIL_PKG.sql                            *
      * Package Name    : CANON_COMMON_UTIL_PKG                                *
      * DESCRIPTION     :                                                      *
      *   This common package returns data from code table for various         *
      * source types.                                                          *
      *                                                                        *
      * REVISION HISTORY:                                                      *
      *                                                                        *
      * DEVELOPER         DATE           DESCRIPTION                           *
      * -------------     -----------    ---------------------------           *
      * Lakshmi Gopalsami 10/26/2015     Initial Creation                      *
	  *                                                                        *
	  * Lakshmi Gopalsami 01/15/2016     Added new function to check for       *
	  *                                  code table, prompt and value          *
	  * Lakshmi Gopalsami 01/19/2017     Added functions for i108 oks dtls view *
	  * Lakshmi Gopalsami 06/07/2017     DB2 changes *
      **************************************************************************/


   TYPE result_cursor IS REF CURSOR;
   lv_package_name           VARCHAR2 (30) := 'CANON_COMMON_UTIL_PKG';

   /* For profiles */

   FUNCTION profile_by_char (p_profile_name        IN     VARCHAR2,
                             p_validation_status      OUT VARCHAR2,
                             p_error_msg              OUT VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION profile_by_number (p_profile_name        IN     VARCHAR2,
                               p_validation_status      OUT VARCHAR2,
                               p_error_msg              OUT VARCHAR2)
      RETURN NUMBER;


   FUNCTION profile_by_date (p_profile_name        IN     VARCHAR2,
                             p_validation_status      OUT VARCHAR2,
                             p_error_msg              OUT VARCHAR2)
      RETURN DATE;

   PROCEDURE get_profile_value (p_profile_name        IN     VARCHAR2,
                                p_profile_type        IN     VARCHAR2 DEFAULT 'CHAR',
                                p_profile_by_char        OUT VARCHAR2,
                                p_profile_by_num         OUT NUMBER,
                                p_profile_by_date        OUT DATE,
                                p_validation_status      OUT VARCHAR2,
                                p_error_msg              OUT VARCHAR2);
   /* For Valuesets/QP/Lookups/Messages or any other static list which will be defined in Custom Code Table UI*/
   PROCEDURE get_code_value (p_cd_name             IN     VARCHAR2,
							  p_column_name1         IN     VARCHAR2 default null,
							  p_column_name2         IN     VARCHAR2 default null,
							  p_column_name3         IN     VARCHAR2 default null,
							  p_column_name4         IN     VARCHAR2 default null,
							  p_column_name5         IN     VARCHAR2 default null,
							  p_column_name6         IN     VARCHAR2 default null,
							  p_column_name7         IN     VARCHAR2 default null,
							  p_column_name8         IN     VARCHAR2 default null,
							  p_column_name9         IN     VARCHAR2 default null,
							  p_column_name10        IN     VARCHAR2 default null,
							  p_column_name11        IN     VARCHAR2 default null,
							  p_column_name12        IN     VARCHAR2 default null,
							  p_column_name13        IN     VARCHAR2 default null,
							  p_column_name14        IN     VARCHAR2 default null,
							  p_column_name15        IN     VARCHAR2 default null,
							  p_column_name16        IN     VARCHAR2 default null,
							  p_column_name17        IN     VARCHAR2 default null,
							  p_column_name18        IN     VARCHAR2 default null,
							  p_column_name19        IN     VARCHAR2 default null,
							  p_column_name20        IN     VARCHAR2 default null,
							  p_column_name21        IN     VARCHAR2 default null,
							  p_column_name22        IN     VARCHAR2 default null,
							  p_column_name23        IN     VARCHAR2 default null,
							  p_column_name24        IN     VARCHAR2 default null,
							  p_column_name25        IN     VARCHAR2 default null,
							  p_column_name26        IN     VARCHAR2 default null,
							  p_column_name27        IN     VARCHAR2 default null,
							  p_column_name28        IN     VARCHAR2 default null,
							  p_column_name29        IN     VARCHAR2 default null,
							  p_column_name30        IN     VARCHAR2 default null,
							  p_column_name31        IN     VARCHAR2 default null,
							  p_column_name32        IN     VARCHAR2 default null,
							  p_column_name33        IN     VARCHAR2 default null,
							  p_column_name34        IN     VARCHAR2 default null,
							  p_column_name35        IN     VARCHAR2 default null,
							  p_column_name36        IN     VARCHAR2 default null,
							  p_column_name37        IN     VARCHAR2 default null,
							  p_column_name38        IN     VARCHAR2 default null,
							  p_column_name39        IN     VARCHAR2 default null,
							  p_column_name40        IN     VARCHAR2 default null,
							  p_column_name41        IN     VARCHAR2 default null,
							  p_column_name42        IN     VARCHAR2 default null,
							  p_column_name43        IN     VARCHAR2 default null,
							  p_column_name44        IN     VARCHAR2 default null,
							  p_column_name45        IN     VARCHAR2 default null,
							  p_column_name46        IN     VARCHAR2 default null,
							  p_column_name47        IN     VARCHAR2 default null,
							  p_column_name48        IN     VARCHAR2 default null,
							  p_column_name49        IN     VARCHAR2 default null,
							  p_column_name50        IN     VARCHAR2 default null,
							  p_column_name51        IN     VARCHAR2 default null,
							  p_column_name52        IN     VARCHAR2 default null,
							  p_column_name53        IN     VARCHAR2 default null,
							  p_column_name54        IN     VARCHAR2 default null,
							  p_column_name55        IN     VARCHAR2 default null,
							  p_column_name56        IN     VARCHAR2 default null,
							  p_column_name57        IN     VARCHAR2 default null,
							  p_column_name58        IN     VARCHAR2 default null,
							  p_column_name59        IN     VARCHAR2 default null,
							  p_column_name60        IN     VARCHAR2 default null,
							  p_column_name61        IN     VARCHAR2 default null,
							  p_column_name62        IN     VARCHAR2 default null,
							  p_column_name63        IN     VARCHAR2 default null,
							  p_column_name64        IN     VARCHAR2 default null,
							  p_column_name65        IN     VARCHAR2 default null,
							  p_column_name66        IN     VARCHAR2 default null,
							  p_column_name67        IN     VARCHAR2 default null,
							  p_column_name68        IN     VARCHAR2 default null,
							  p_column_name69        IN     VARCHAR2 default null,
							  p_column_name70        IN     VARCHAR2 default null,
							  p_column_name71        IN     VARCHAR2 default null,
							  p_column_name72        IN     VARCHAR2 default null,
							  p_column_name73        IN     VARCHAR2 default null,
							  p_column_name74        IN     VARCHAR2 default null,
							  p_column_name75        IN     VARCHAR2 default null,
							  p_column_name76        IN     VARCHAR2 default null,
							  p_column_name77        IN     VARCHAR2 default null,
							  p_column_name78        IN     VARCHAR2 default null,
							  p_column_name79        IN     VARCHAR2 default null,
							  p_column_name80        IN     VARCHAR2 default null,
							  p_column_name81        IN     VARCHAR2 default null,
							  p_column_name82        IN     VARCHAR2 default null,
							  p_column_name83        IN     VARCHAR2 default null,
							  p_column_name84        IN     VARCHAR2 default null,
							  p_column_name85        IN     VARCHAR2 default null,
							  p_column_name86        IN     VARCHAR2 default null,
							  p_column_name87        IN     VARCHAR2 default null,
							  p_column_name88        IN     VARCHAR2 default null,
							  p_column_name89        IN     VARCHAR2 default null,
							  p_column_name90        IN     VARCHAR2 default null,
							  p_column_name91        IN     VARCHAR2 default null,
							  p_column_name92        IN     VARCHAR2 default null,
							  p_column_name93        IN     VARCHAR2 default null,
							  p_column_name94        IN     VARCHAR2 default null,
							  p_column_name95        IN     VARCHAR2 default null,
							  p_column_name96        IN     VARCHAR2 default null,
							  p_column_name97        IN     VARCHAR2 default null,
							  p_column_name98        IN     VARCHAR2 default null,
							  p_column_name99        IN     VARCHAR2 default null,
							  p_column_name100       IN     VARCHAR2 default null,
							  p_result_cursor          OUT RESULT_CURSOR,
							  p_result_count           OUT NUMBER,
							  p_validation_status      OUT VARCHAR2,
							  p_error_msg              OUT VARCHAR2);
							  
    FUNCTION  check_code_values(p_cd_name             IN     VARCHAR2,
                                p_colume_prompt       IN     VARCHAR2,
	                            p_value               IN     VARCHAR2)
    RETURN VARCHAR2;
   TYPE profile_details IS RECORD
   (
      char_val     VARCHAR2 (4000),
      number_val   NUMBER,
      date_val     DATE
   );

   TYPE profile_details_tab IS TABLE OF profile_details
      INDEX BY VARCHAR2 (4000);

   g_profile_details_tab   profile_details_tab;

   TYPE code_details IS RECORD
   (
      VAL1     VARCHAR2 (2000),
      VAL2     VARCHAR2 (2000),
      VAL3     VARCHAR2 (2000),
      VAL4     VARCHAR2 (2000),
      VAL5     VARCHAR2 (2000),
      VAL6     VARCHAR2 (2000),
      VAL7     VARCHAR2 (2000),
      VAL8     VARCHAR2 (2000),
      VAL9     VARCHAR2 (2000),
      VAL10    VARCHAR2 (2000),
      VAL11    VARCHAR2 (2000),
      VAL12    VARCHAR2 (2000),
      VAL13    VARCHAR2 (2000),
      VAL14    VARCHAR2 (2000),
      VAL15    VARCHAR2 (2000),
      VAL16    VARCHAR2 (2000),
      VAL17    VARCHAR2 (2000),
      VAL18    VARCHAR2 (2000),
      VAL19    VARCHAR2 (2000),
      VAL20    VARCHAR2 (2000),
      VAL21    VARCHAR2 (2000),
      VAL22    VARCHAR2 (2000),
      VAL23    VARCHAR2 (2000),
      VAL24    VARCHAR2 (2000),
      VAL25    VARCHAR2 (2000),
      VAL26    VARCHAR2 (2000),
      VAL27    VARCHAR2 (2000),
      VAL28    VARCHAR2 (2000),
      VAL29    VARCHAR2 (2000),
      VAL30    VARCHAR2 (2000),
      VAL31    VARCHAR2 (2000),
      VAL32    VARCHAR2 (2000),
      VAL33    VARCHAR2 (2000),
      VAL34    VARCHAR2 (2000),
      VAL35    VARCHAR2 (2000),
      VAL36    VARCHAR2 (2000),
      VAL37    VARCHAR2 (2000),
      VAL38    VARCHAR2 (2000),
      VAL39    VARCHAR2 (2000),
      VAL40    VARCHAR2 (2000),
      VAL41    VARCHAR2 (2000),
      VAL42    VARCHAR2 (2000),
      VAL43    VARCHAR2 (2000),
      VAL44    VARCHAR2 (2000),
      VAL45    VARCHAR2 (2000),
      VAL46    VARCHAR2 (2000),
      VAL47    VARCHAR2 (2000),
      VAL48    VARCHAR2 (2000),
      VAL49    VARCHAR2 (2000),
      VAL50    VARCHAR2 (2000),
      VAL51    NUMBER,
      VAL52    NUMBER,
      VAL53    NUMBER,
      VAL54    NUMBER,
      VAL55    NUMBER,
      VAL56    NUMBER,
      VAL57    NUMBER,
      VAL58    NUMBER,
      VAL59    NUMBER,
      VAL60    NUMBER,
      VAL61    NUMBER,
      VAL62    NUMBER,
      VAL63    NUMBER,
      VAL64    NUMBER,
      VAL65    NUMBER,
      VAL66    NUMBER,
      VAL67    NUMBER,
      VAL68    NUMBER,
      VAL69    NUMBER,
      VAL70    NUMBER,
      VAL71    NUMBER,
      VAL72    NUMBER,
      VAL73    NUMBER,
      VAL74    NUMBER,
      VAL75    NUMBER,
      VAL76    DATE,
      VAL77    DATE,
      VAL78    DATE,
      VAL79    DATE,
      VAL80    DATE,
      VAL81    DATE,
      VAL82    DATE,
      VAL83    DATE,
      VAL84    DATE,
      VAL85    DATE,
      VAL86    DATE,
      VAL87    DATE,
      VAL88    DATE,
      VAL89    DATE,
      VAL90    DATE,
      VAL91    DATE,
      VAL92    DATE,
      VAL93    DATE,
      VAL94    DATE,
      VAL95    DATE,
      VAL96    DATE,
      VAL97    DATE,
      VAL98    DATE,
      VAL99    DATE,
      VAL100   DATE
   );

   TYPE code_details_tab IS TABLE OF code_details
      INDEX BY VARCHAR2 (4000);

   g_code_details_tab      code_details_tab;
   
   TYPE line_type_rec IS RECORD
   (
      line_type      VARCHAR2 (50),
      product_type   VARCHAR2 (50),
      counter_type   VARCHAR2 (50),
      is_rental      VARCHAR2 (1)
   );

   TYPE line_type_tbl IS TABLE OF line_type_rec
      INDEX BY VARCHAR2 (50);

   g_line_type_tbl           line_type_tbl;
   
   FUNCTION item_type (p_source             IN VARCHAR2,
                       p_return_type         IN VARCHAR2,
					   p_invoice_charge_type IN VARCHAR2,
					   p_intg_mdse_cd        IN VARCHAR2,
                       p_item_cd            IN VARCHAR2)
   RETURN VARCHAR2;

   FUNCTION get_tier_rate_volume
        (p_return_type     IN VARCHAR2,					    
		 p_tier_number     IN VARCHAR2,			
         p_mtr_lb_cd       IN VARCHAR2,		 
		 p_inv_number      IN VARCHAR2,					    
		 p_inv_line_number IN VARCHAR2)
   RETURN NUMBER;   
   
    TYPE tier_qty_vol IS RECORD
   (
      XS_MTR_FROM_COPY_QTY NUMBER,
      XS_MTR_AMT_RATE NUMBER
   );

   TYPE tier_qty_vol_tab IS TABLE OF tier_qty_vol
      INDEX BY PLS_INTEGER;

   g_tier_qty_vol_tab   tier_qty_vol_tab;
   
   -- All tier_rate list for the equipment
   TYPE tier_list_rec IS RECORD
   (
	  inv_tier_qty_vol_tab   tier_qty_vol_tab
   );
   
   TYPE tier_list_tab IS TABLE OF tier_list_rec
	  INDEX BY VARCHAR2 (25); -- (Invoice_num(13)-line_number(5)-mtr_lb_cd(2))

   g_tier_list_tab        tier_list_tab;
   
    FUNCTION get_hard_reads(
         p_return_type               IN VARCHAR2,		 -- 'START'/'END'/'TOTAL' Reads		    
   		 p_start_phys_rd_grp_seq     IN NUMBER,
		 p_end_phys_rd_grp_seq       IN NUMBER,
		 p_mtr_lb_cd                 IN VARCHAR2,
		 p_test_copies               IN NUMBER,
		 p_ds_contr_bllg_mtr_pk      IN NUMBER
   ) RETURN NUMBER;
   
   TYPE phys_mtr_read_rec IS RECORD
   (
      phys_meter_read NUMBER      
   );

   TYPE phys_mtr_read_tab IS TABLE OF phys_mtr_read_rec
      INDEX BY VARCHAR2 (25); -- start/end physical meter read pk-mtr_lb_cd

   g_phys_mtr_read_tab   phys_mtr_read_tab;
   
   TYPE contr_phys_mtr_bllg_rec IS RECORD
   (
	  inv_tier_qty_vol_tab   phys_mtr_read_tab,
	  start_phys_rd_grp_seq  NUMBER,
	  end_phys_rd_grp_seq    NUMBER,
	  test_copies            NUMBER,
	  mtr_lb_cd              VARCHAR2(2)
   );
   
   TYPE contr_phys_mtr_bllg_rec_tab IS TABLE OF contr_phys_mtr_bllg_rec
	  INDEX BY VARCHAR2 (25); -- (Invoice_num(13)-line_number(5)-mtr_lb_cd(2))

   g_contr_phys_mtr_bllg_rec_tab        contr_phys_mtr_bllg_rec_tab;
   
   FUNCTION get_reading_type_source(
        -- 'READING_TYPE_CD'/'READING_TYPE_NM'/'READING_SOURCE_CD'/'READING_SOURCE_NM'
        p_return_type    IN      VARCHAR2, 
        p_inv_number     IN      VARCHAR2,
        p_contract_line  IN      NUMBER)
   RETURN VARCHAR2;
		
   TYPE reading_type_source_rec IS RECORD
   (
	  DS_MTR_READ_TP_CD  VARCHAR2(10),
	  DS_MTR_READ_TP_NM  VARCHAR2(30),
	  MTR_READ_SRC_TP_CD  VARCHAR2(5),
	  MTR_READ_SRC_TP_NM VARCHAR2(30)
   );
   
   TYPE reading_type_source_tab IS TABLE OF reading_type_source_rec
	  INDEX BY VARCHAR2 (50); -- (Invoice_num(13)-contract_line_pk(28))

   g_reading_type_source_tab        reading_type_source_tab;
   
   FUNCTION get_regular_mtr_lb_cd(
       --'AGG'/'FLT'/'REG'
	   p_contract_type         IN VARCHAR2,	
	   p_ds_contr_bllg_mtr_pk  IN NUMBER,
	   p_start_phys_rd_grp_seq IN NUMBER,
       p_bllg_mtr_lb_cd        IN VARCHAR2)
   RETURN VARCHAR2;
		
   TYPE regular_mtr_lb_cd_rec IS RECORD
   (
	  mtr_lb_cd    DS_MDL_MTR.MDL_MTR_LB_CD%TYPE,
	  mtr_lb_nm    DS_MDL_MTR.MDL_MTR_LB_NOTE_TXT%TYPE
   );
   
   TYPE regular_mtr_lb_cd_tab IS TABLE OF regular_mtr_lb_cd_rec
	  INDEX BY VARCHAR2 (2); -- (Invoice_num(13)-contract_line_pk(28))

   g_regular_mtr_lb_cd_tab        regular_mtr_lb_cd_tab;
   
   PROCEDURE log_error(
      p_package_name      IN        VARCHAR2,
	  p_procedure_name    IN        VARCHAR2,
	  p_key1              IN        VARCHAR2, 
	  p_key2              IN        VARCHAR2,
	  p_key3              IN        VARCHAR2,
	  p_key4              IN        VARCHAR2, 
	  p_key5              IN        VARCHAR2,
	  p_sqlerrm            IN        VARCHAR2 
   );
   
   
END CANON_COMMON_UTIL_PKG;
/

show errors;

CREATE OR REPLACE PACKAGE BODY CANON_COMMON_UTIL_PKG
AS
   /****************************************************************************
      *                                                                        *
      * File NAME       : CANON_COMMON_UTIL_PKG.sql                            *
      * Package Name    : CANON_COMMON_UTIL_PKG                                *
      * DESCRIPTION     :                                                      *
      *   This common package returns data from code table for various         *
      * source types.                                                          *
      *                                                                        *
      * REVISION HISTORY:                                                      *
      *                                                                        *
      * DEVELOPER         DATE           DESCRIPTION                           *
      * -------------     -----------    ---------------------------           *
      * Lakshmi Gopalsami 10/26/2015     Initial Creation                      *
	  *                                                                        *
	  * Lakshmi Gopalsami 01/15/2016     Added new function to check for       *
	  *                                  code table, prompt and value          *
	  * Lakshmi Gopalsami 01/19/2017     Added functions for i108 oks dtls view *
	  * Lakshmi Gopalsami 06/07/2017     DB2 changes *
      **************************************************************************/
   FUNCTION profile_by_char (p_profile_name        IN     VARCHAR2,
                             p_validation_status      OUT VARCHAR2,
                             p_error_msg              OUT VARCHAR2)
      RETURN VARCHAR2
   IS
      lv_profile_value   VARCHAR2 (4000);
   BEGIN
      p_validation_status := 'S';
      p_error_msg := NULL;

      SELECT val2
        INTO lv_profile_value
        FROM CANON_S21_CD_VAL_TBL val, CANON_S21_CD_TBL code
       WHERE     val.val1 = p_profile_name
             AND val.cd_id = code.cd_id
             AND code.cd_name = 'PROFILES_BY_CHAR'
			 AND sysdate between nvl(val.start_date_active,sysdate-1) AND nvl(val.end_date_active,sysdate+1);

      RETURN lv_profile_value;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Profile not set';
         RETURN NULL;
      WHEN OTHERS
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Error while fetching profile by character';
         RETURN NULL;
   END profile_by_char;

   FUNCTION profile_by_number (p_profile_name        IN     VARCHAR2,
                               p_validation_status      OUT VARCHAR2,
                               p_error_msg              OUT VARCHAR2)
      RETURN NUMBER
   IS
      lv_profile_value   VARCHAR2 (4000);
   BEGIN
      p_validation_status := 'S';
      p_error_msg := NULL;

      SELECT val51
        INTO lv_profile_value
        FROM CANON_S21_CD_VAL_TBL val, CANON_S21_CD_TBL code
       WHERE     val.val1 = p_profile_name
             AND val.cd_id = code.cd_id
             AND code.cd_name = 'PROFILES_BY_NUMBER'
			 AND sysdate between nvl(val.start_date_active,sysdate-1) AND nvl(val.end_date_active,sysdate+1);

      RETURN lv_profile_value;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Profile not set';
         RETURN NULL;
      WHEN OTHERS
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Error while fetching profile by Number';
         RETURN NULL;
   END profile_by_number;

   FUNCTION profile_by_date (p_profile_name        IN     VARCHAR2,
                             p_validation_status      OUT VARCHAR2,
                             p_error_msg              OUT VARCHAR2)
      RETURN DATE
   IS
      lv_profile_value   VARCHAR2 (4000);
   BEGIN
      p_validation_status := 'S';
      p_error_msg := NULL;

      SELECT val76
        INTO lv_profile_value
        FROM CANON_S21_CD_VAL_TBL val, CANON_S21_CD_TBL code
       WHERE     val.val1 = p_profile_name
             AND val.cd_id = code.cd_id
             AND code.cd_name = 'PROFILES_BY_DATE'
			 AND sysdate between nvl(val.start_date_active,sysdate-1) AND nvl(val.end_date_active,sysdate+1);

      RETURN lv_profile_value;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Profile not set';
         RETURN NULL;
      WHEN OTHERS
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Error while fetching profile by Date';
         RETURN NULL;
   END profile_by_date;


   PROCEDURE get_profile_value (p_profile_name        IN     VARCHAR2,
                                p_profile_type        IN     VARCHAR2 default 'CHAR',
                                p_profile_by_char        OUT VARCHAR2,
                                p_profile_by_num         OUT NUMBER,
                                p_profile_by_date        OUT DATE,
                                p_validation_status      OUT VARCHAR2,
                                p_error_msg              OUT VARCHAR2)
   IS
      lv_profile_type   VARCHAR2 (100);
   BEGIN
      lv_profile_type := NVL (p_profile_type, 'CHAR');
      p_validation_status := 'S';
      p_error_msg := NULL;

      IF lv_profile_type = 'CHAR'
      THEN
         p_profile_by_char :=
            CANON_COMMON_UTIL_PKG.profile_by_char (
               p_profile_name        => p_profile_name,
               p_validation_status   => p_validation_status,
               p_error_msg           => p_error_msg);
      ELSIF lv_profile_type = 'NUMBER'
      THEN
         p_profile_by_num :=
            CANON_COMMON_UTIL_PKG.profile_by_number (
               p_profile_name        => p_profile_name,
               p_validation_status   => p_validation_status,
               p_error_msg           => p_error_msg);
      ELSIF lv_profile_type = 'DATE'
      THEN
         p_profile_by_date :=
            CANON_COMMON_UTIL_PKG.profile_by_date (
               p_profile_name        => p_profile_name,
               p_validation_status   => p_validation_status,
               p_error_msg           => p_error_msg);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_validation_status := 'E';
         p_error_msg := 'Error while fetching profiles';
   END get_profile_value;

   PROCEDURE get_code_value (p_cd_name             IN     VARCHAR2,
							  p_column_name1         IN     VARCHAR2 default null,
							  p_column_name2         IN     VARCHAR2 default null,
							  p_column_name3         IN     VARCHAR2 default null,
							  p_column_name4         IN     VARCHAR2 default null,
							  p_column_name5         IN     VARCHAR2 default null,
							  p_column_name6         IN     VARCHAR2 default null,
							  p_column_name7         IN     VARCHAR2 default null,
							  p_column_name8         IN     VARCHAR2 default null,
							  p_column_name9         IN     VARCHAR2 default null,
							  p_column_name10        IN     VARCHAR2 default null,
							  p_column_name11        IN     VARCHAR2 default null,
							  p_column_name12        IN     VARCHAR2 default null,
							  p_column_name13        IN     VARCHAR2 default null,
							  p_column_name14        IN     VARCHAR2 default null,
							  p_column_name15        IN     VARCHAR2 default null,
							  p_column_name16        IN     VARCHAR2 default null,
							  p_column_name17        IN     VARCHAR2 default null,
							  p_column_name18        IN     VARCHAR2 default null,
							  p_column_name19        IN     VARCHAR2 default null,
							  p_column_name20        IN     VARCHAR2 default null,
							  p_column_name21        IN     VARCHAR2 default null,
							  p_column_name22        IN     VARCHAR2 default null,
							  p_column_name23        IN     VARCHAR2 default null,
							  p_column_name24        IN     VARCHAR2 default null,
							  p_column_name25        IN     VARCHAR2 default null,
							  p_column_name26        IN     VARCHAR2 default null,
							  p_column_name27        IN     VARCHAR2 default null,
							  p_column_name28        IN     VARCHAR2 default null,
							  p_column_name29        IN     VARCHAR2 default null,
							  p_column_name30        IN     VARCHAR2 default null,
							  p_column_name31        IN     VARCHAR2 default null,
							  p_column_name32        IN     VARCHAR2 default null,
							  p_column_name33        IN     VARCHAR2 default null,
							  p_column_name34        IN     VARCHAR2 default null,
							  p_column_name35        IN     VARCHAR2 default null,
							  p_column_name36        IN     VARCHAR2 default null,
							  p_column_name37        IN     VARCHAR2 default null,
							  p_column_name38        IN     VARCHAR2 default null,
							  p_column_name39        IN     VARCHAR2 default null,
							  p_column_name40        IN     VARCHAR2 default null,
							  p_column_name41        IN     VARCHAR2 default null,
							  p_column_name42        IN     VARCHAR2 default null,
							  p_column_name43        IN     VARCHAR2 default null,
							  p_column_name44        IN     VARCHAR2 default null,
							  p_column_name45        IN     VARCHAR2 default null,
							  p_column_name46        IN     VARCHAR2 default null,
							  p_column_name47        IN     VARCHAR2 default null,
							  p_column_name48        IN     VARCHAR2 default null,
							  p_column_name49        IN     VARCHAR2 default null,
							  p_column_name50        IN     VARCHAR2 default null,
							  p_column_name51        IN     VARCHAR2 default null,
							  p_column_name52        IN     VARCHAR2 default null,
							  p_column_name53        IN     VARCHAR2 default null,
							  p_column_name54        IN     VARCHAR2 default null,
							  p_column_name55        IN     VARCHAR2 default null,
							  p_column_name56        IN     VARCHAR2 default null,
							  p_column_name57        IN     VARCHAR2 default null,
							  p_column_name58        IN     VARCHAR2 default null,
							  p_column_name59        IN     VARCHAR2 default null,
							  p_column_name60        IN     VARCHAR2 default null,
							  p_column_name61        IN     VARCHAR2 default null,
							  p_column_name62        IN     VARCHAR2 default null,
							  p_column_name63        IN     VARCHAR2 default null,
							  p_column_name64        IN     VARCHAR2 default null,
							  p_column_name65        IN     VARCHAR2 default null,
							  p_column_name66        IN     VARCHAR2 default null,
							  p_column_name67        IN     VARCHAR2 default null,
							  p_column_name68        IN     VARCHAR2 default null,
							  p_column_name69        IN     VARCHAR2 default null,
							  p_column_name70        IN     VARCHAR2 default null,
							  p_column_name71        IN     VARCHAR2 default null,
							  p_column_name72        IN     VARCHAR2 default null,
							  p_column_name73        IN     VARCHAR2 default null,
							  p_column_name74        IN     VARCHAR2 default null,
							  p_column_name75        IN     VARCHAR2 default null,
							  p_column_name76        IN     VARCHAR2 default null,
							  p_column_name77        IN     VARCHAR2 default null,
							  p_column_name78        IN     VARCHAR2 default null,
							  p_column_name79        IN     VARCHAR2 default null,
							  p_column_name80        IN     VARCHAR2 default null,
							  p_column_name81        IN     VARCHAR2 default null,
							  p_column_name82        IN     VARCHAR2 default null,
							  p_column_name83        IN     VARCHAR2 default null,
							  p_column_name84        IN     VARCHAR2 default null,
							  p_column_name85        IN     VARCHAR2 default null,
							  p_column_name86        IN     VARCHAR2 default null,
							  p_column_name87        IN     VARCHAR2 default null,
							  p_column_name88        IN     VARCHAR2 default null,
							  p_column_name89        IN     VARCHAR2 default null,
							  p_column_name90        IN     VARCHAR2 default null,
							  p_column_name91        IN     VARCHAR2 default null,
							  p_column_name92        IN     VARCHAR2 default null,
							  p_column_name93        IN     VARCHAR2 default null,
							  p_column_name94        IN     VARCHAR2 default null,
							  p_column_name95        IN     VARCHAR2 default null,
							  p_column_name96        IN     VARCHAR2 default null,
							  p_column_name97        IN     VARCHAR2 default null,
							  p_column_name98        IN     VARCHAR2 default null,
							  p_column_name99        IN     VARCHAR2 default null,
							  p_column_name100       IN     VARCHAR2 default null,
							  p_result_cursor          OUT RESULT_CURSOR,
							  p_result_count           OUT NUMBER,
							  p_validation_status      OUT VARCHAR2,
							  p_error_msg              OUT VARCHAR2)
   IS
      lv_where         VARCHAR2 (32767);
      lv_dynamic_sql   VARCHAR2 (32767);
      lv_from_where    VARCHAR2 (32767);
      lv_col_list      VARCHAR2 (32767);
	  lv_order_by      VARCHAR2(500);
      ln_count         NUMBER := 0;
	  lv_col_exists    VARCHAR2(1);
   BEGIN
      p_validation_status := 'S';
      p_error_msg := NULL;
      lv_where := 'WHERE 1=1 ';

      FOR cd_list IN (SELECT *
                        FROM CANON_S21_CD_TBL
                       WHERE cd_name = p_cd_name)
      LOOP
	     DBMS_OUTPUT.put_line ( 'cd_list.cd_id '||cd_list.cd_id);
         lv_where := lv_where 
		            || ' AND cd_id = ' 
					|| cd_list.cd_id 
					|| ' AND sysdate between nvl(start_date_active,sysdate-1) AND nvl(end_date_active,sysdate+1) ';
         lv_dynamic_sql := 'select ';
         lv_col_exists := 'N';
		 lv_col_list := NULL;

            FOR cd_col_list IN (  SELECT *
                                    FROM CANON_S21_CD_COL_TBL
                                   WHERE cd_id = cd_list.cd_id
								     AND UPPER(col_prompt) in ( 
										UPPER(p_column_name1 ),
										UPPER(p_column_name2 ),
										UPPER(p_column_name3 ),
										UPPER(p_column_name4 ),
										UPPER(p_column_name5 ),
										UPPER(p_column_name6 ),
										UPPER(p_column_name7 ),
										UPPER(p_column_name8 ),
										UPPER(p_column_name9 ),
										UPPER(p_column_name10),
										UPPER(p_column_name11),
										UPPER(p_column_name12),
										UPPER(p_column_name13),
										UPPER(p_column_name14),
										UPPER(p_column_name15),
										UPPER(p_column_name16),
										UPPER(p_column_name17),
										UPPER(p_column_name18),
										UPPER(p_column_name19),
										UPPER(p_column_name20),
										UPPER(p_column_name21),
										UPPER(p_column_name22),
										UPPER(p_column_name23),
										UPPER(p_column_name24),
										UPPER(p_column_name25),
										UPPER(p_column_name26),
										UPPER(p_column_name27),
										UPPER(p_column_name28),
										UPPER(p_column_name29),
										UPPER(p_column_name30),
										UPPER(p_column_name31),
										UPPER(p_column_name32),
										UPPER(p_column_name33),
										UPPER(p_column_name34),
										UPPER(p_column_name35),
										UPPER(p_column_name36),
										UPPER(p_column_name37),
										UPPER(p_column_name38),
										UPPER(p_column_name39),
										UPPER(p_column_name40),
										UPPER(p_column_name41),
										UPPER(p_column_name42),
										UPPER(p_column_name43),
										UPPER(p_column_name44),
										UPPER(p_column_name45),
										UPPER(p_column_name46),
										UPPER(p_column_name47),
										UPPER(p_column_name48),
										UPPER(p_column_name49),
										UPPER(p_column_name50),
										UPPER(p_column_name51),
										UPPER(p_column_name52),
										UPPER(p_column_name53),
										UPPER(p_column_name54),
										UPPER(p_column_name55),
										UPPER(p_column_name56),
										UPPER(p_column_name57),
										UPPER(p_column_name58),
										UPPER(p_column_name59),
										UPPER(p_column_name60),
										UPPER(p_column_name61),
										UPPER(p_column_name62),
										UPPER(p_column_name63),
										UPPER(p_column_name64),
										UPPER(p_column_name65),
										UPPER(p_column_name66),
										UPPER(p_column_name67),
										UPPER(p_column_name68),
										UPPER(p_column_name69),
										UPPER(p_column_name70),
										UPPER(p_column_name71),
										UPPER(p_column_name72),
										UPPER(p_column_name73),
										UPPER(p_column_name74),
										UPPER(p_column_name75),
										UPPER(p_column_name76),
										UPPER(p_column_name77),
										UPPER(p_column_name78),
										UPPER(p_column_name79),
										UPPER(p_column_name80),
										UPPER(p_column_name81),
										UPPER(p_column_name82),
										UPPER(p_column_name83),
										UPPER(p_column_name84),
										UPPER(p_column_name85),
										UPPER(p_column_name86),
										UPPER(p_column_name87),
										UPPER(p_column_name88),
										UPPER(p_column_name89),
										UPPER(p_column_name90),
										UPPER(p_column_name91),
										UPPER(p_column_name92),
										UPPER(p_column_name93),
										UPPER(p_column_name94),
										UPPER(p_column_name95),
										UPPER(p_column_name96),
										UPPER(p_column_name97),
										UPPER(p_column_name98),
										UPPER(p_column_name99),
										UPPER(p_column_name100))
								and nvl(is_active,'Y')='Y'
                                ORDER BY col_seq)
            LOOP
               DBMS_OUTPUT.put_line (
                     cd_col_list.RESULT_COL
                  || ' "'
                  || cd_col_list.col_prompt
                  || '" ,');
               lv_col_list :=
                     lv_col_list
                  || cd_col_list.RESULT_COL
                  || ' "'
                  || cd_col_list.col_prompt
                  || '" ,';
               lv_col_exists := 'Y';
            END LOOP;
			DBMS_OUTPUT.put_line ( 'select all columns');
			DBMS_OUTPUT.put_line ( 'lv_col_exists'||lv_col_exists);
			
            IF nvl(lv_col_exists,'N') = 'N' THEN 
				FOR cd_col_list IN (  SELECT *
										FROM CANON_S21_CD_COL_TBL
									   WHERE cd_id = cd_list.cd_id
									ORDER BY col_seq)
				LOOP
				   DBMS_OUTPUT.put_line ( 'select all columns--1');
				   DBMS_OUTPUT.put_line (
						 cd_col_list.RESULT_COL
					  || ' "'
					  || cd_col_list.col_prompt
					  || '" ,');
				   lv_col_list :=
						 lv_col_list
					  || cd_col_list.RESULT_COL
					  || ' "'
					  || cd_col_list.col_prompt
					  || '" ,';
				   lv_col_exists := 'Y';
				END LOOP;
            END IF;
      END LOOP;

      DBMS_OUTPUT.put_line ('lv_col_list  ' || lv_col_list);

      lv_from_where := ' FROM CANON_S21_CD_VAL_TBL ' || lv_where;
	  lv_order_by   := ' ORDER by val_id';

      IF lv_col_list IS NOT NULL
      THEN
         lv_col_list := SUBSTR (lv_col_list, 1, LENGTH (lv_col_list) - 1);
         DBMS_OUTPUT.put_line ('lv_col_list after replace' || lv_col_list);
         lv_dynamic_sql := lv_dynamic_sql || lv_col_list || lv_from_where||lv_order_by;
      END IF;

      DBMS_OUTPUT.put_line (
         'lv_dynamic_sql after where : ' || lv_dynamic_sql);

      /* Column Exists. Execute the query */
      IF lv_col_exists = 'Y'
      THEN
         EXECUTE IMMEDIATE 'select count(*) ' || lv_from_where INTO ln_count;

         IF ln_count > 0
         THEN
            OPEN p_result_cursor FOR lv_dynamic_sql;
         ELSE
            OPEN p_result_cursor FOR SELECT NULL FROM DUAL;
         END IF;
      ELSE
         OPEN p_result_cursor FOR SELECT NULL FROM DUAL;
      END IF;

      p_result_count := ln_count;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_validation_status := 'E';
         p_error_msg :=
            'Error while fetching code table details for ' || p_cd_name;
         p_result_count := 0;

         OPEN p_result_cursor FOR SELECT NULL FROM DUAL;
   END get_code_value;
   
   FUNCTION  check_code_values(p_cd_name             IN     VARCHAR2,
                                p_colume_prompt       IN     VARCHAR2,
	                            p_value               IN     VARCHAR2)
   RETURN VARCHAR2 IS 	
        lv_value_column   VARCHAR2(50);
		lv_query          VARCHAR2(32767);
		ln_cd_id          NUMBER;
		lv_value_exists   VARCHAR2(1) := 'N';
   BEGIN    
        /* Get the result column for the prompt */
		BEGIN
		    SELECT result_col, col.cd_id
			   INTO lv_value_column, ln_cd_id 
			   FROM CANON_S21_CD_COL_TBL col, CANON_S21_CD_TBL code
			  WHERE col.cd_id      = code.cd_id
			    AND code.CD_NAME   = p_cd_name
				AND col.col_prompt = p_colume_prompt;
		EXCEPTION 
		    WHEN OTHERS THEN
			lv_value_column := NULL;
		END;
		
		IF lv_value_column IS NOT NULL AND p_value IS NOT NULL THEN 
		   lv_query := 'SELECT ''Y'' FROM CANON_S21_CD_VAL_TBL WHERE CD_ID = '|| ln_cd_id || ' AND '|| lv_value_column||' = '''||p_value||'''';
		   dbms_output.put_line(' Query to be executed '|| lv_query);
		   EXECUTE IMMEDIATE lv_query INTO lv_value_exists;
		   RETURN lv_value_exists;
		    
		ELSE 
		   return 'N';
		END IF;
		
       
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line(' Code table '|| p_cd_name || ' and column prompt '|| p_colume_prompt || ' not identified from code table setup');
	     return 'N';
         
   END check_code_values; 
   
   FUNCTION item_type (p_source             IN VARCHAR2,
                       p_return_type         IN VARCHAR2,
					   p_invoice_charge_type IN VARCHAR2,
					   p_intg_mdse_cd        IN VARCHAR2,
                       p_item_cd            IN VARCHAR2)
      RETURN VARCHAR2
   IS
      CURSOR get_item_details
      IS
         SELECT item.mdse_nm,
                item.coa_prod_cd prod_code,
                item.coa_mdse_tp_cd merch_code
           FROM mdse item--, ds_mdse_info ds_item Removing ds_mdse_info as part of DB2 changes by S21
          WHERE     item.mdse_cd = p_item_cd
                --AND item.mdse_cd = ds_item.mdse_cd
                AND item.glbl_cmpy_cd = 'ADB'
				AND item.ezcancelflag ='0';

      lv_procedure_name   VARCHAR2 (30) := 'item_type';
      lv_prod_code        VARCHAR2 (50);
      lv_merch_code       VARCHAR2 (50);
      lv_line_type        VARCHAR2 (50);
      lv_product_type     VARCHAR2 (50);
      lv_counter_type     VARCHAR2 (50);
      lv_item_name        VARCHAR2 (30);
      lv_is_rental        VARCHAR2 (1);
      l_attribute9        VARCHAR2 (50);
      l_desc              VARCHAR2 (50);
   BEGIN
      dbms_output.put_line('+++ Inside get_header_amounts +++');	
	  
      IF g_line_type_tbl.EXISTS (p_item_cd)
      THEN
         IF p_return_type = 'LINE_TYPE'
         THEN
            RETURN g_line_type_tbl (p_item_cd).line_type;
         ELSIF p_return_type = 'PRODUCT_TYPE'
         THEN
            RETURN g_line_type_tbl (p_item_cd).product_type;
         END IF;
      ELSE
         OPEN get_item_details;

         FETCH get_item_details
            INTO lv_item_name, lv_prod_code, lv_merch_code;

         CLOSE get_item_details;

         IF UPPER (lv_prod_code) LIKE 'K%'
         THEN
            lv_line_type := 'NON-COPIER';
         ELSIF lv_merch_code = '53'
         THEN
            lv_line_type := 'SOFTWARE';
         ELSIF lv_item_name LIKE '%SHIPPING%'               -- S21 Replacement
         THEN
            lv_line_type := 'FREIGHT';
         ELSE
            lv_line_type := 'COPIER';
         END IF;
		 
		 IF p_source = 'OM' THEN            -- Order Management source
		 
			 IF lv_merch_code IN ('10', '11')
			 THEN                            
				lv_product_type := 'E-BASEUNIT';
		     ELSE
			 	lv_product_type := NULL;
             END IF;			 
         ELSIF p_source = 'OKS'
         THEN                                              -- contracts source
            IF UPPER (lv_item_name) LIKE '%RENTAL%'
            THEN
               lv_is_rental := 'Y';
            END IF;
			
			IF p_invoice_charge_type <> 'MC' THEN 

				IF (lv_is_rental = 'Y' )
				THEN
				   lv_product_type := 'C-RENTAL';
				ELSE 
				   lv_product_type := 'C-MAINTENANCE';
				END IF;
            ELSIF (p_invoice_charge_type = 'MC')
            THEN
              IF (UPPER(p_intg_mdse_cd) LIKE '%BW%' OR 
			       UPPER(p_intg_mdse_cd) LIKE '%BLACK%' OR
				   UPPER(p_intg_mdse_cd) LIKE '%BLACK%ONLY%' )
               THEN
                  lv_product_type := 'U-BW';
               ELSIF (UPPER(p_intg_mdse_cd) LIKE '%CLR%' OR UPPER(p_intg_mdse_cd) LIKE '%COLOR%') THEN
                  lv_product_type := 'U-COLOR';
               END IF;
            END IF;
			
			IF lv_product_type IS NULL THEN 
               lv_product_type := 'C-MAINTENANCE';
            END IF;

            IF lv_product_type LIKE 'U-%'
            THEN
               lv_counter_type := 'FORMULA';
            ELSE
               lv_counter_type := NULL;
            END IF;
         END IF;



         g_line_type_tbl (p_item_cd).line_type := lv_line_type;
         g_line_type_tbl (p_item_cd).is_rental := lv_is_rental;
         g_line_type_tbl (p_item_cd).product_type := lv_product_type;
         g_line_type_tbl (p_item_cd).counter_type := lv_counter_type;

         IF p_return_type = 'LINE_TYPE'
         THEN
            RETURN g_line_type_tbl (p_item_cd).line_type;
         ELSIF p_return_type = 'PRODUCT_TYPE'
         THEN
            RETURN g_line_type_tbl (p_item_cd).product_type;
         END IF;
      END IF;
	  dbms_output.put_line('+++ Exit get_header_amounts +++');	
	  
   EXCEPTION
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception item_type +++'|| SQLERRM);	
		CANON_COMMON_UTIL_PKG.log_error (lv_package_name,
										lv_procedure_name,
										p_source             ,
										p_return_type        ,
										p_invoice_charge_type,
										p_intg_mdse_cd       ,
										p_item_cd            ,
										SQLERRM
                                             );
         RETURN NULL;												  
   END item_type;
   
   FUNCTION get_tier_rate_volume
        (p_return_type     IN VARCHAR2,					    
		 p_tier_number     IN VARCHAR2,
   		 p_mtr_lb_cd       IN VARCHAR2,
		 p_inv_number      IN VARCHAR2,					    
		 p_inv_line_number IN VARCHAR2)
   RETURN NUMBER
   IS 
   lv_procedure_name   VARCHAR2 (30) := 'get_tier_rate_volume';
  
   BEGIN
   
    IF g_tier_list_tab.EXISTS(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd) THEN 
	
	   IF p_return_type='VOLUME' THEN 
	      RETURN g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(p_tier_number).XS_MTR_FROM_COPY_QTY;
	   ELSIF p_return_type='RATE' THEN 
	      RETURN  g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(p_tier_number).XS_MTR_AMT_RATE;
	   END IF;
	   
	ELSE 
	
	  /* Initialize null to 4 tiers */
	  FOR tier_cnt in 1..6 
	  LOOP 
		  g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(tier_cnt).XS_MTR_FROM_COPY_QTY := NULL;
		  
		  g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(tier_cnt).XS_MTR_AMT_RATE := NULL;
	  END LOOP;
	  
	  FOR get_tier_rate_vol IN 
	   (
			SELECT si.svc_inv_num, 
				 si.DS_CONTR_NUM,
				 smm.SVC_MACH_MSTR_PK,
				 smm.SER_NUM,
				 smm.MDSE_CD,
				 silm.MTR_LB_CD,
				 silm.MTR_LB_DESC_TXT,
				 silxm.XS_MTR_FROM_COPY_QTY,  -- Tier allowance is tier copy volume
				 silxm.XS_MTR_AMT_RATE,
				 ROW_NUMBER ()
                  OVER (
                     PARTITION BY 
						 si.svc_inv_num, 
						 si.DS_CONTR_NUM,
						 smm.SER_NUM,
						 smm.MDSE_CD,
						 silm.MTR_LB_CD
                     ORDER BY
                        silxm.XS_MTR_COPY_QTY)
                     tier_number
			FROM SVC_INV si,
				 SVC_INV_LINE sil,
				 SVC_MACH_MSTR smm,
				 svc_inv_line_mtr silm,         
				 svc_inv_line_xs_mtr silxm
		   WHERE     1 = 1
				 AND si.EZCANCELFLAG = '0'
				 AND sil.EZCANCELFLAG = '0'
				 AND smm.EZCANCELFLAG(+) = '0'
				 AND silm.EZCANCELFLAG(+) = '0'
				 AND silxm.EZCANCELFLAG(+) = '0'
				 AND si.GLBL_CMPY_CD = 'ADB'
				 AND si.GLBL_CMPY_CD = sil.GLBL_CMPY_CD
				 AND sil.GLBL_CMPY_CD = smm.GLBL_CMPY_CD(+)
				 AND sil.GLBL_CMPY_CD = silm.GLBL_CMPY_CD(+)
				 AND sil.GLBL_CMPY_CD = silxm.GLBL_CMPY_CD(+)
				 AND si.SVC_INV_SRC_TP_CD='CNTR'
				 AND si.svc_inv_num = sil.svc_inv_num
				 AND NVL(sil.SVC_MACH_MSTR_PK,si.SVC_MACH_MSTR_PK) = smm.SVC_MACH_MSTR_PK(+)
				 AND sil.svc_inv_num = silm.svc_inv_num(+)
				 AND sil.svc_inv_line_pk = silm.svc_inv_line_pk(+)
				 --AND silm.XS_CHRG_TP_CD(+) ='P' -- Pickup only point type as range type is for sliding/usage step pricing
				-- AND si.EZINUSERID NOT LIKE 'S21_CSA_CONV%' this is only for AP3
				 AND sil.svc_inv_line_pk = silxm.svc_inv_line_pk(+)
				 AND sil.svc_inv_num = silxm.svc_inv_num(+)
				 AND si.svc_inv_num = p_inv_number
				 AND sil.svc_inv_line_num = p_inv_line_number
				 AND silm.mtr_lb_cd = p_mtr_lb_cd
		ORDER BY si.svc_inv_num, 
				 si.DS_CONTR_NUM,
				 smm.SER_NUM,
				 smm.MDSE_CD,
				 silm.MTR_LB_CD,
				 silxm.XS_MTR_COPY_QTY
		)
		LOOP   
		    g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(get_tier_rate_vol.tier_number).XS_MTR_FROM_COPY_QTY := get_tier_rate_vol.XS_MTR_FROM_COPY_QTY;
		  
		  g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(get_tier_rate_vol.tier_number).XS_MTR_AMT_RATE := get_tier_rate_vol.XS_MTR_AMT_RATE;
		END LOOP;
		
     END IF;			 
	 IF p_return_type='VOLUME' THEN 
	      RETURN  g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(p_tier_number).XS_MTR_FROM_COPY_QTY;
	 ELSIF p_return_type='RATE' THEN 
	      RETURN  g_tier_list_tab(p_inv_number||'-'||p_inv_line_number||'-'||p_mtr_lb_cd).inv_tier_qty_vol_tab(p_tier_number).XS_MTR_AMT_RATE;
	 END IF;
	   
   EXCEPTION
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception get_tier_rate_volume +++');	
			CANON_COMMON_UTIL_PKG.log_error (lv_package_name,
										lv_procedure_name,
										p_return_type     ,					    
										p_tier_number     ,
										p_mtr_lb_cd       ,
										p_inv_number      ,					    
										p_inv_line_number ,
										SQLERRM
                                             );
        RETURN NULL;												  
   END get_tier_rate_volume;
   
   FUNCTION get_hard_reads(
         p_return_type               IN VARCHAR2,		 -- 'START'/'END'/'TOTAL' Reads		    
   		 p_start_phys_rd_grp_seq     IN NUMBER,
		 p_end_phys_rd_grp_seq       IN NUMBER,
		 p_mtr_lb_cd                 IN VARCHAR2,
		 p_test_copies               IN NUMBER,
		 p_ds_contr_bllg_mtr_pk      IN NUMBER
   ) RETURN NUMBER
   IS
   lv_procedure_name   VARCHAR2 (30) := 'get_tier_rate_volume';
   ln_hard_read NUMBER;
   ln_start_read  NUMBER;
   ln_end_read    NUMBER;
   lv_mtr_lb_cd   VARCHAR2(2);
   BEGIN
   
    lv_mtr_lb_cd := p_mtr_lb_cd;
   
     IF NOT(g_contr_phys_mtr_bllg_rec_tab.EXISTS(p_ds_contr_bllg_mtr_pk)) THEN 
	   g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).start_phys_rd_grp_seq := p_start_phys_rd_grp_seq;
	   g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).end_phys_rd_grp_seq := p_end_phys_rd_grp_seq;
	   g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).test_copies := p_test_copies;
	   g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).mtr_lb_cd := lv_mtr_lb_cd;
	 END IF;
   
     IF p_return_type ='START' THEN 
	   IF g_phys_mtr_read_tab.EXISTS(p_start_phys_rd_grp_seq||'-'||lv_mtr_lb_cd) THEN 
	      ln_hard_read := g_phys_mtr_read_tab(p_start_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read;
	   ELSE 
	     BEGIN
		 
	      SELECT spmr.read_mtr_cnt
		   INTO ln_hard_read
		   FROM svc_phys_mtr_read spmr,
				contr_phys_bllg_mtr_reln cpbmr,
				mtr_lb ml
		  WHERE     1 = 1
                AND spmr.svc_phys_mtr_read_grp_sq = p_start_phys_rd_grp_seq
				AND spmr.ezcancelflag ='0'
				AND spmr.glbl_cmpy_cd ='ADB'
				AND cpbmr.ds_contr_bllg_mtr_pk =p_ds_contr_bllg_mtr_pk
				AND spmr.ezcancelflag = cpbmr.ezcancelflag
				AND spmr.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
				AND cpbmr.svc_phys_mtr_pk =spmr.svc_phys_mtr_pk
				AND spmr.mtr_lb_cd = ml.mtr_lb_cd
				AND spmr.ezcancelflag = ml.ezcancelflag
				AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
				AND ml.mtr_lb_tp_cd ='R';
             g_phys_mtr_read_tab(p_start_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read := ln_hard_read;
			 g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).inv_tier_qty_vol_tab(p_start_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read := ln_hard_read;
		 EXCEPTION
		    WHEN OTHERS THEN 
			   ln_hard_read := NULL;
		 END;
       END IF;				
	 ELSIF p_return_type ='END' THEN 
	    IF g_phys_mtr_read_tab.EXISTS(p_end_phys_rd_grp_seq||'-'||lv_mtr_lb_cd) THEN 
	      ln_hard_read := g_phys_mtr_read_tab(p_end_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read;
	   ELSE 
	    BEGIN
         SELECT spmr.read_mtr_cnt
		  INTO ln_hard_read
		   FROM svc_phys_mtr_read spmr,
				contr_phys_bllg_mtr_reln cpbmr,
				mtr_lb ml
		  WHERE     1 = 1
                AND spmr.svc_phys_mtr_read_grp_sq = p_end_phys_rd_grp_seq
				AND spmr.ezcancelflag ='0'
				AND spmr.glbl_cmpy_cd ='ADB'
				AND cpbmr.ds_contr_bllg_mtr_pk =p_ds_contr_bllg_mtr_pk
				AND spmr.ezcancelflag = cpbmr.ezcancelflag
				AND spmr.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
				AND cpbmr.svc_phys_mtr_pk =spmr.svc_phys_mtr_pk
				AND spmr.mtr_lb_cd = ml.mtr_lb_cd
				AND spmr.ezcancelflag = ml.ezcancelflag
				AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
				AND ml.mtr_lb_tp_cd ='R';
			g_phys_mtr_read_tab(p_end_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read := ln_hard_read;
            g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).inv_tier_qty_vol_tab(p_end_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read := ln_hard_read;			
		EXCEPTION
		    WHEN OTHERS THEN 
			   ln_hard_read := NULL;
		 END;		
	   END IF;
	 ELSIF p_return_type ='TOTAL' THEN   -- Total volume deducting test copies
	    /* Call Start and end in case if PL/SQL is not initialized. */
		ln_start_read := CANON_COMMON_UTIL_PKG.get_hard_reads(
         p_return_type               => 'START',
   		 p_start_phys_rd_grp_seq     => p_start_phys_rd_grp_seq,
		 p_end_phys_rd_grp_seq       => p_end_phys_rd_grp_seq  ,
		 p_mtr_lb_cd                 => lv_mtr_lb_cd            ,
		 p_test_copies               => p_test_copies          ,
		 p_ds_contr_bllg_mtr_pk      => p_ds_contr_bllg_mtr_pk 
		 );
		 
		ln_end_read :=  CANON_COMMON_UTIL_PKG.get_hard_reads(
         p_return_type               => 'END',
   		 p_start_phys_rd_grp_seq     => p_start_phys_rd_grp_seq,
		 p_end_phys_rd_grp_seq       => p_end_phys_rd_grp_seq  ,
		 p_mtr_lb_cd                 => lv_mtr_lb_cd            ,
		 p_test_copies               => p_test_copies          ,
		 p_ds_contr_bllg_mtr_pk      => p_ds_contr_bllg_mtr_pk 
		 );
		 
	    IF g_phys_mtr_read_tab.EXISTS(p_start_phys_rd_grp_seq) AND g_phys_mtr_read_tab.EXISTS(p_end_phys_rd_grp_seq||'-'||lv_mtr_lb_cd) THEN 
		  ln_hard_read:= 
		     NVL(g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).inv_tier_qty_vol_tab(p_end_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read,0)- 
			 NVL(g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).inv_tier_qty_vol_tab(p_start_phys_rd_grp_seq||'-'||lv_mtr_lb_cd).phys_meter_read,0) - 
			 NVL(g_contr_phys_mtr_bllg_rec_tab(p_ds_contr_bllg_mtr_pk).test_copies,0);
		ELSE 
		  ln_hard_read := NULL;
		END IF;
	 END IF;
	 RETURN ln_hard_read;
   EXCEPTION 
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception get_hard_reads +++');	
		CANON_COMMON_UTIL_PKG.log_error (lv_package_name,
										lv_procedure_name,
										p_return_type              ,    
										p_start_phys_rd_grp_seq    ,
										p_end_phys_rd_grp_seq      ,
										p_ds_contr_bllg_mtr_pk     ,
										null,
										SQLERRM
                                             );
        RETURN NULL;	
   END get_hard_reads;
   
    FUNCTION get_reading_type_source(
	    -- 'READING_TYPE_CD'/'READING_TYPE_NM'/'READING_SOURCE_CD'/'READING_SOURCE_NM'
        p_return_type    IN      VARCHAR2, 
        p_inv_number    IN      VARCHAR2,
        p_contract_line  IN      NUMBER)
	RETURN VARCHAR2
	IS
	lv_procedure_name   VARCHAR2 (30) := 'get_reading_type_source';
	lv_reading_type_cd  ds_mtr_read_tp.ds_mtr_read_tp_cd%TYPE;
	lv_reading_type_nm  ds_mtr_read_tp.ds_mtr_read_tp_nm%TYPE;
	lv_reading_source_cd  mtr_read_src_tp.mtr_read_src_tp_cd%TYPE;
	lv_reading_source_nm  mtr_read_src_tp.mtr_read_src_tp_nm%TYPE;
	BEGIN
	
	IF g_reading_type_source_tab.EXISTS(p_inv_number||'-'||p_contract_line) THEN 
	   	 IF p_return_type='READING_TYPE_CD' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).DS_MTR_READ_TP_CD;
         ELSIF p_return_type='READING_TYPE_NM' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).DS_MTR_READ_TP_NM;
		 ELSIF p_return_type='READING_SOURCE_CD' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).MTR_READ_SRC_TP_CD;
	     ELSIF p_return_type='READING_SOURCE_NM' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).MTR_READ_SRC_TP_NM;
		 END IF;
	ELSE
	
		  SELECT mtr_read.ds_mtr_read_tp_cd, 
		          mtr_read.ds_mtr_read_tp_nm,
                  read_src.mtr_read_src_tp_cd,
		          read_src.mtr_read_src_tp_nm
		    INTO lv_reading_type_cd,
			      lv_reading_type_nm, 
				  lv_reading_source_cd,
			      lv_reading_source_nm
		  FROM svc_phys_mtr_read pmr, contr_phys_bllg_mtr_reln pbmr,
				ds_mtr_read_tp mtr_read, mtr_read_src_tp read_src
		 WHERE     svc_phys_mtr_read_grp_sq IN
					  (SELECT                                                --pmr.*--
							 MAX (svc_phys_mtr_read_grp_sq)
						 FROM svc_phys_mtr_read pmr,
							  contr_phys_bllg_mtr_reln pbmr
						WHERE     1 = 1
							  AND pbmr.ds_contr_dtl_pk = p_contract_line
							  AND pmr.svc_phys_mtr_pk = pbmr.svc_phys_mtr_pk
							  AND pbmr.bllbl_flg = 'Y'
							  AND pmr.ezcancelflag = '0'
							  AND pmr.glbl_cmpy_cd ='ADB'
							  AND pbmr.ezcancelflag = '0'
							  AND pbmr.glbl_cmpy_cd ='ADB'
							  AND pmr.svc_inv_num = p_inv_number)
			   AND pbmr.ds_contr_dtl_pk = p_contract_line
			   AND pmr.svc_phys_mtr_pk = pbmr.svc_phys_mtr_pk
			   AND pbmr.bllbl_flg = 'Y'
			   AND pmr.svc_inv_num = p_inv_number
			   AND pmr.ezcancelflag = '0'
			   AND pmr.glbl_cmpy_cd ='ADB'
			   AND pbmr.ezcancelflag = '0'
			   AND pbmr.glbl_cmpy_cd ='ADB'
			   AND mtr_read.ds_mtr_read_tp_cd = pmr.ds_mtr_read_tp_cd
			   AND mtr_read.ezcancelflag = '0'
			   AND mtr_read.glbl_cmpy_cd ='ADB'
			   AND read_src.mtr_read_src_tp_cd = pmr.mtr_read_src_tp_cd
			   AND read_src.ezcancelflag = '0'
			   AND read_src.glbl_cmpy_cd ='ADB'
			   AND ROWNUM = 1;
			   
			   g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).DS_MTR_READ_TP_CD   :=lv_reading_type_cd;
			   g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).DS_MTR_READ_TP_NM   :=lv_reading_type_nm;
			   g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).MTR_READ_SRC_TP_CD  :=lv_reading_source_cd;
			   g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).MTR_READ_SRC_TP_NM  :=lv_reading_source_nm;

		 IF p_return_type='READING_TYPE_CD' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).DS_MTR_READ_TP_CD;
         ELSIF p_return_type='READING_TYPE_NM' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).DS_MTR_READ_TP_NM;
		 ELSIF p_return_type='READING_SOURCE_CD' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).MTR_READ_SRC_TP_CD;
	     ELSIF p_return_type='READING_SOURCE_NM' THEN 
			  RETURN  g_reading_type_source_tab(p_inv_number||'-'||p_contract_line).MTR_READ_SRC_TP_NM;
		 END IF;
		 
	 END IF;
	   
   EXCEPTION
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception get_reading_type_source +++');	
		   IF  p_return_type='READING_TYPE_NM' THEN RETURN 'Actual'; END IF;
		   IF  p_return_type='READING_TYPE_CD' THEN RETURN 'AC'; END IF;
           IF  p_return_type='READING_SOURCE_NM' THEN RETURN 'S21';  END IF;
		   IF  p_return_type='READING_SOURCE_CD' THEN RETURN 'S21';  END IF;
			CANON_COMMON_UTIL_PKG.log_error (lv_package_name,
										lv_procedure_name,
										p_return_type     ,					    
										p_inv_number     ,
										p_contract_line       ,
										NULL      ,					    
										NULL ,
										SQLERRM
                                             );
        RETURN NULL;												  
   END get_reading_type_source;
   
   
   
   FUNCTION get_regular_mtr_lb_cd(
       --'AGG'/'FLT'/'REG'
	   p_contract_type         IN VARCHAR2,	
	   p_ds_contr_bllg_mtr_pk  IN NUMBER,
	   p_start_phys_rd_grp_seq IN NUMBER,
       p_bllg_mtr_lb_cd        IN VARCHAR2)
	RETURN VARCHAR2
	IS
	lv_procedure_name   VARCHAR2 (30) := 'get_regular_mtr_lb_cd';
	lv_mtr_lb_cd        mtr_lb.mtr_lb_cd%TYPE;
	
	BEGIN
	
	 
	  IF p_bllg_mtr_lb_cd IS NOT NULL THEN 
	
		IF g_regular_mtr_lb_cd_tab.exists(p_bllg_mtr_lb_cd) THEN 
		   RETURN g_regular_mtr_lb_cd_tab(p_bllg_mtr_lb_cd).mtr_lb_cd ;
		
	   ELSE
	   /* Modified query as chat discussion from Taizo-X-Nakamura on 01/27/2017 */	
	   
		   BEGIN 
			  SELECT spmr.mtr_lb_cd
				INTO lv_mtr_lb_cd
				FROM svc_phys_mtr_read spmr,
					 contr_phys_bllg_mtr_reln cpbmr,
					 mtr_lb ml,
					 svc_phys_mtr spm
			  WHERE     1 = 1
					AND spmr.svc_phys_mtr_read_grp_sq = p_start_phys_rd_grp_seq
					AND spmr.ezcancelflag ='0'
					AND spmr.glbl_cmpy_cd ='ADB'
					AND spmr.ezcancelflag = spm.ezcancelflag
					AND spm.svc_phys_mtr_pk =spmr.svc_phys_mtr_pk
					AND spm.bllg_mtr_lb_cd =p_bllg_mtr_lb_cd
					AND spmr.glbl_cmpy_cd = spm.glbl_cmpy_cd
					AND cpbmr.ds_contr_bllg_mtr_pk =p_ds_contr_bllg_mtr_pk
					AND spmr.ezcancelflag = cpbmr.ezcancelflag
					AND spmr.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
					AND cpbmr.svc_phys_mtr_pk =spmr.svc_phys_mtr_pk
					AND spmr.mtr_lb_cd = ml.mtr_lb_cd
					AND spmr.ezcancelflag = ml.ezcancelflag
					AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
					AND ml.mtr_lb_tp_cd ='R';
		   EXCEPTION 		
			 WHEN OTHERS THEN  lv_mtr_lb_cd := NULL;
		   END;
					
		   g_regular_mtr_lb_cd_tab(p_bllg_mtr_lb_cd).mtr_lb_cd := lv_mtr_lb_cd ;
		   RETURN g_regular_mtr_lb_cd_tab(p_bllg_mtr_lb_cd).mtr_lb_cd ;				
	 
	   END IF;
	  /*
		
			IF p_contract_type ='FLT' THEN 
			   BEGIN
				  SELECT dmm.MDL_MTR_LB_CD
					INTO lv_mtr_lb_cd
					FROM DS_MDL_MTR dmm
					WHERE 
					dmm.FLEET_MTR_LB_CD = p_bllg_mtr_lb_cd
					AND dmm.EZCANCELFLAG         = '0'
					AND dmm.EZINCOMPANYCODE      = 'ADB';

			  EXCEPTION 
				 WHEN OTHERS THEN 
					lv_mtr_lb_cd := NULL;
			  END;
			   
			ELSIF p_contract_type ='AGG' THEN 
			 BEGIN
				  SELECT dmm.MDL_MTR_LB_CD
					INTO lv_mtr_lb_cd
					FROM DS_MDL_MTR dmm
					WHERE 
					dmm.AGGR_MTR_LB_CD = p_bllg_mtr_lb_cd
					AND dmm.EZCANCELFLAG         = '0'
					AND dmm.EZINCOMPANYCODE      = 'ADB';

			  EXCEPTION 
				 WHEN OTHERS THEN 
					lv_mtr_lb_cd := NULL;
			  END;
			ELSE 
			
			  BEGIN
				  SELECT dmm.MDL_MTR_LB_CD
					INTO lv_mtr_lb_cd
					FROM DS_MDL_MTR dmm
					WHERE 
					dmm.BLLG_MTR_LB_CD = p_bllg_mtr_lb_cd
					AND dmm.EZCANCELFLAG         = '0'
					AND dmm.EZINCOMPANYCODE      = 'ADB';

			  EXCEPTION 
				 WHEN OTHERS THEN 
					lv_mtr_lb_cd := NULL;
			  END;
			
			END IF;
		
		    g_regular_mtr_lb_cd_tab(p_bllg_mtr_lb_cd).mtr_lb_cd := lv_mtr_lb_cd ;
	      	 RETURN g_regular_mtr_lb_cd_tab(p_bllg_mtr_lb_cd).mtr_lb_cd ;
			 
		 END IF;
	  */
     ELSE 
	     RETURN NULL;
    END IF; 
	   
   EXCEPTION
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception get_regular_mtr_lb_cd +++');	
		CANON_COMMON_UTIL_PKG.log_error (lv_package_name,
										lv_procedure_name,
										p_contract_type     ,					    
										p_bllg_mtr_lb_cd     ,
										NULL       ,
										NULL      ,					    
										NULL ,
										SQLERRM
                                             );
        RETURN NULL;												  
   END get_regular_mtr_lb_cd;
		
   
    PROCEDURE log_error(
      p_package_name      IN        VARCHAR2,
	  p_procedure_name    IN        VARCHAR2,
	  p_key1              IN        VARCHAR2, 
	  p_key2              IN        VARCHAR2,
	  p_key3              IN        VARCHAR2,
	  p_key4              IN        VARCHAR2, 
	  p_key5              IN        VARCHAR2,
	  p_sqlerrm            IN        VARCHAR2 
   )
   IS
      pragma autonomous_transaction;
   BEGIN
      INSERT INTO CANON_E001_ERRORS_TBL(
		program_name,
		process_name,
		key1,
		key2,
		key3,
		key4,
		key5,
		error_msg,
		error_date
	  )
	  values(
	  p_package_name,
	  p_procedure_name,
	  p_key1,
	  p_key2,
	  p_key3,
	  p_key4,
	  p_key5,
	  p_sqlerrm,
	  sysdate
	  );
	  commit;
   EXCEPTION 
       WHEN OTHERS THEN 
	     null;
   END log_error;

   
END CANON_COMMON_UTIL_PKG;
/

show errors;