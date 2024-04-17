CREATE OR REPLACE PACKAGE CANON_E307_CONSTANTS
AS
/*******************************************************************************************
   Package Name: CANON_E307_CONSTANTS_SPEC
   Description:  Package to store global constants to be used by ASCC
   Dependencies: NA
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Sesh Ragavachari      1.0                  01-Sep-2015              Inital Version
   Hema Doniparthi		 2.0                  14-Mar-2016			   Modified to add path for attachment
*****************************************************************************************/
g_special_message_name    VARCHAR2(100)       :=      'Memo';
  g_global_company_code     VARCHAR2(100)       :=      'ADB';
  g_cancel_flg VARCHAR2(10):='0';
  g_file_upload_path      VARCHAR2(100)        := '///WebSphere/apps/s21/csawds/updownfiles/upload/';
  g_nar_txt VARCHAR2(1000)    :=  'Our experience shows that we may immediately reduce your downtime by providing you few quick and simple instruction.May I proceed? If no:Will you review the suggested steps first before making a final decision? I am confident you will find them very easy and user friendly.';
  g_rmd_txt VARCHAR2(1000)    :='htpps://emanage.csa.canon.com/OA_HTML/11123_002JAM.pdf';
  g_fsr_report_download_path     VARCHAR2(100)        := '///var/s21/csaextn/updownfiles/download';

END CANON_E307_CONSTANTS;
/