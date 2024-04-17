CREATE OR REPLACE PACKAGE CANON_E389_DFF_UTILITY_PKG AS
--***********************************************************************************
--Program Name            : CANON_E389_DFF_UTILITY_PKG
--Program Desc            : CANON_E389_DFF_UTILITY_PKG is used to Insert/Update Data 
--                          Into CANON_E193_JSP_DFF_UTIL Table.
--Author                  : Chandra Sekhar
--                        : Created on 16-Apr-2009
--Version                 : 1.0
--***********************************************************************************

   TYPE AttributesRec IS RECORD (ATTRIBUTE1            VARCHAR2(240),
                                 ATTRIBUTE2            VARCHAR2(240),
                                 ATTRIBUTE3            VARCHAR2(240),
                                 ATTRIBUTE4            VARCHAR2(240),
                                 ATTRIBUTE5            VARCHAR2(240),
                                 ATTRIBUTE6            VARCHAR2(240),
                                 ATTRIBUTE7            VARCHAR2(240),
                                 ATTRIBUTE8            VARCHAR2(240),
                                 ATTRIBUTE9            VARCHAR2(240),
                                 ATTRIBUTE10           VARCHAR2(240),
                                 ATTRIBUTE11           VARCHAR2(240),
                                 ATTRIBUTE12           VARCHAR2(240),
                                 ATTRIBUTE13           VARCHAR2(240),
                                 ATTRIBUTE14           VARCHAR2(240),
                                 ATTRIBUTE15           VARCHAR2(240),
                                 ATTRIBUTE16           VARCHAR2(240),
                                 ATTRIBUTE17           VARCHAR2(240),
                                 ATTRIBUTE18           VARCHAR2(240),
                                 ATTRIBUTE19           VARCHAR2(240),
                                 ATTRIBUTE20           VARCHAR2(240),
                                 ATTRIBUTE21           VARCHAR2(240),
                                 ATTRIBUTE22           VARCHAR2(240),
                                 ATTRIBUTE23           VARCHAR2(240),
                                 ATTRIBUTE24           VARCHAR2(240),
                                 ATTRIBUTE25           VARCHAR2(240),
                                 ATTRIBUTE26           VARCHAR2(240),
                                 ATTRIBUTE27           VARCHAR2(240),
                                 ATTRIBUTE28           VARCHAR2(240),
                                 ATTRIBUTE29           VARCHAR2(240),
                                 ATTRIBUTE30           VARCHAR2(240),
                                 ATTRIBUTE31           VARCHAR2(240),
                                 ATTRIBUTE32           VARCHAR2(240),
                                 ATTRIBUTE33           VARCHAR2(240),
                                 ATTRIBUTE34           VARCHAR2(240),
                                 ATTRIBUTE35           VARCHAR2(240),
                                 ATTRIBUTE36           VARCHAR2(240),
                                 ATTRIBUTE37           VARCHAR2(240),
                                 ATTRIBUTE38           VARCHAR2(240),
                                 ATTRIBUTE39           VARCHAR2(240),
                                 ATTRIBUTE40           VARCHAR2(240),
                                 ATTRIBUTE41           VARCHAR2(240),
                                 ATTRIBUTE42           VARCHAR2(240),
                                 ATTRIBUTE43           VARCHAR2(240),
                                 ATTRIBUTE44           VARCHAR2(240),
                                 ATTRIBUTE45           VARCHAR2(240),
                                 ATTRIBUTE46           VARCHAR2(240),
                                 ATTRIBUTE47           VARCHAR2(240),
                                 ATTRIBUTE48           VARCHAR2(240),
                                 ATTRIBUTE49           VARCHAR2(240),
                                 ATTRIBUTE50           VARCHAR2(240));

   PROCEDURE INSERT_DFF_DATA (p_commit                 IN  VARCHAR2,
                              p_source_application     IN  VARCHAR2,
                              p_source_context         IN  VARCHAR2,
                              p_context_value          IN  VARCHAR2,
                              p_last_update_date       IN  DATE    DEFAULT SYSDATE,
                              p_last_updated_by        IN  VARCHAR2,
                              p_creation_date          IN  DATE    DEFAULT SYSDATE,
                              p_created_by             IN  VARCHAR2,
                              p_attributesRec          IN  AttributesRec,
                              x_return_status          OUT VARCHAR2,
                              x_msg_data               OUT VARCHAR2);

   PROCEDURE UPDATE_DFF_DATA (p_commit                 IN  VARCHAR2,
                              p_source_application     IN  VARCHAR2,
                              p_source_context         IN  VARCHAR2,
                              p_context_value          IN  VARCHAR2,
                              p_last_update_date       IN  DATE    DEFAULT SYSDATE,
                              p_last_updated_by        IN  VARCHAR2,
                              p_attributesRec          IN  AttributesRec,
                              x_return_status          OUT VARCHAR2,
                              x_msg_data               OUT VARCHAR2);

END CANON_E389_DFF_UTILITY_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E389_DFF_UTILITY_PKG AS
--***********************************************************************************
--Program Name            : CANON_E389_DFF_UTILITY_PKG
--Program Desc            : CANON_E389_DFF_UTILITY_PKG is used to Insert/Update Data 
--                          Into CANON_E193_JSP_DFF_UTIL Table.
--Author                  : Chandra Sekhar
--                        : Created on 16-Apr-2009
--Version                 : 1.0
--***********************************************************************************

   PROCEDURE INSERT_DFF_DATA (p_commit                 IN  VARCHAR2,
                              p_source_application     IN  VARCHAR2,
                              p_source_context         IN  VARCHAR2,
                              p_context_value          IN  VARCHAR2,
                              p_last_update_date       IN  DATE    DEFAULT SYSDATE,
                              p_last_updated_by        IN  VARCHAR2,
                              p_creation_date          IN  DATE    DEFAULT SYSDATE,
                              p_created_by             IN  VARCHAR2,
                              p_attributesRec          IN  AttributesRec,
                              x_return_status          OUT VARCHAR2,
                              x_msg_data               OUT VARCHAR2) IS

   BEGIN
 --debug_pkg1.debug_proc ('Inside INSERT_DFF_DATA');
      INSERT INTO CANON_E193_JSP_DFF_UTIL
      VALUES (p_source_application,
              p_source_context,
              p_context_value,
              NVL(p_last_update_date, SYSDATE),
              p_last_updated_by,
              NVL(p_creation_date, SYSDATE),
              p_created_by,
              p_attributesRec.ATTRIBUTE1,
              p_attributesRec.ATTRIBUTE2,
              p_attributesRec.ATTRIBUTE3,
              p_attributesRec.ATTRIBUTE4,
              p_attributesRec.ATTRIBUTE5,
              p_attributesRec.ATTRIBUTE6,
              p_attributesRec.ATTRIBUTE7,
              p_attributesRec.ATTRIBUTE8,
              p_attributesRec.ATTRIBUTE9,
              p_attributesRec.ATTRIBUTE10,
              p_attributesRec.ATTRIBUTE11,
              p_attributesRec.ATTRIBUTE12,
              p_attributesRec.ATTRIBUTE13,
              p_attributesRec.ATTRIBUTE14,
              p_attributesRec.ATTRIBUTE15,
              p_attributesRec.ATTRIBUTE16,
              p_attributesRec.ATTRIBUTE17,
              p_attributesRec.ATTRIBUTE18,
              p_attributesRec.ATTRIBUTE19,
              p_attributesRec.ATTRIBUTE20,
              p_attributesRec.ATTRIBUTE21,
              p_attributesRec.ATTRIBUTE22,
              p_attributesRec.ATTRIBUTE23,
              p_attributesRec.ATTRIBUTE24,
              p_attributesRec.ATTRIBUTE25,
              p_attributesRec.ATTRIBUTE26,
              p_attributesRec.ATTRIBUTE27,
              p_attributesRec.ATTRIBUTE28,
              p_attributesRec.ATTRIBUTE29,
              p_attributesRec.ATTRIBUTE30,
              p_attributesRec.ATTRIBUTE31,
              p_attributesRec.ATTRIBUTE32,
              p_attributesRec.ATTRIBUTE33,
              p_attributesRec.ATTRIBUTE34,
              p_attributesRec.ATTRIBUTE35,
              p_attributesRec.ATTRIBUTE36,
              p_attributesRec.ATTRIBUTE37,
              p_attributesRec.ATTRIBUTE38,
              p_attributesRec.ATTRIBUTE39,
              p_attributesRec.ATTRIBUTE40,
              p_attributesRec.ATTRIBUTE41,
              p_attributesRec.ATTRIBUTE42,
              p_attributesRec.ATTRIBUTE43,
              p_attributesRec.ATTRIBUTE44,
              p_attributesRec.ATTRIBUTE45,
              p_attributesRec.ATTRIBUTE46,
              p_attributesRec.ATTRIBUTE47,
              p_attributesRec.ATTRIBUTE48,
              p_attributesRec.ATTRIBUTE49,
              p_attributesRec.ATTRIBUTE50);

      IF (p_commit = 'S') THEN
         COMMIT;
      END IF;

      x_return_status := 'S';
      x_msg_data      := NULL;

   EXCEPTION
      WHEN OTHERS THEN
         x_return_status := 'E';
         x_msg_data      := 'Insert_Dff_Data Exception : ' || SQLERRM;

   END INSERT_DFF_DATA;

   PROCEDURE UPDATE_DFF_DATA (p_commit                 IN  VARCHAR2,
                              p_source_application     IN  VARCHAR2,
                              p_source_context         IN  VARCHAR2,
                              p_context_value          IN  VARCHAR2,
                              p_last_update_date       IN  DATE    DEFAULT SYSDATE,
                              p_last_updated_by        IN  VARCHAR2,
                              p_attributesRec          IN  AttributesRec,
                              x_return_status          OUT VARCHAR2,
                              x_msg_data               OUT VARCHAR2) IS

   BEGIN
--debug_pkg1.debug_proc ('Inside Update_DFF_DATA');
      UPDATE CANON_E193_JSP_DFF_UTIL
      SET LAST_UPDATE_DATE = NVL(p_last_update_date, SYSDATE),
          LAST_UPDATED_BY  = p_last_updated_by,
          ATTRIBUTE1       = p_attributesRec.ATTRIBUTE1,
          ATTRIBUTE2       = p_attributesRec.ATTRIBUTE2,
          ATTRIBUTE3       = p_attributesRec.ATTRIBUTE3,
          ATTRIBUTE4       = p_attributesRec.ATTRIBUTE4,
          ATTRIBUTE5       = p_attributesRec.ATTRIBUTE5,
          ATTRIBUTE6       = p_attributesRec.ATTRIBUTE6,
          ATTRIBUTE7       = p_attributesRec.ATTRIBUTE7,
          ATTRIBUTE8       = p_attributesRec.ATTRIBUTE8,
          ATTRIBUTE9       = p_attributesRec.ATTRIBUTE9,
          ATTRIBUTE10      = p_attributesRec.ATTRIBUTE10,
          ATTRIBUTE11      = p_attributesRec.ATTRIBUTE11,
          ATTRIBUTE12      = p_attributesRec.ATTRIBUTE12,
          ATTRIBUTE13      = p_attributesRec.ATTRIBUTE13,
          ATTRIBUTE14      = p_attributesRec.ATTRIBUTE14,
          ATTRIBUTE15      = p_attributesRec.ATTRIBUTE15,
          ATTRIBUTE16      = p_attributesRec.ATTRIBUTE16,
          ATTRIBUTE17      = p_attributesRec.ATTRIBUTE17,
          ATTRIBUTE18      = p_attributesRec.ATTRIBUTE18,
          ATTRIBUTE19      = p_attributesRec.ATTRIBUTE19,
          ATTRIBUTE20      = p_attributesRec.ATTRIBUTE20,
          ATTRIBUTE21      = p_attributesRec.ATTRIBUTE21,
          ATTRIBUTE22      = p_attributesRec.ATTRIBUTE22,
          ATTRIBUTE23      = p_attributesRec.ATTRIBUTE23,
          ATTRIBUTE24      = p_attributesRec.ATTRIBUTE24,
          ATTRIBUTE25      = p_attributesRec.ATTRIBUTE25,
          ATTRIBUTE26      = p_attributesRec.ATTRIBUTE26,
          ATTRIBUTE27      = p_attributesRec.ATTRIBUTE27,
          ATTRIBUTE28      = p_attributesRec.ATTRIBUTE28,
          ATTRIBUTE29      = p_attributesRec.ATTRIBUTE29,
          ATTRIBUTE30      = p_attributesRec.ATTRIBUTE30,
          ATTRIBUTE31      = p_attributesRec.ATTRIBUTE31,
          ATTRIBUTE32      = p_attributesRec.ATTRIBUTE32,
          ATTRIBUTE33      = p_attributesRec.ATTRIBUTE33,
          ATTRIBUTE34      = p_attributesRec.ATTRIBUTE34,
          ATTRIBUTE35      = p_attributesRec.ATTRIBUTE35,
          ATTRIBUTE36      = p_attributesRec.ATTRIBUTE36,
          ATTRIBUTE37      = p_attributesRec.ATTRIBUTE37,
          ATTRIBUTE38      = p_attributesRec.ATTRIBUTE38,
          ATTRIBUTE39      = p_attributesRec.ATTRIBUTE39,
          ATTRIBUTE40      = p_attributesRec.ATTRIBUTE40,
          ATTRIBUTE41      = p_attributesRec.ATTRIBUTE41,
          ATTRIBUTE42      = p_attributesRec.ATTRIBUTE42,
          ATTRIBUTE43      = p_attributesRec.ATTRIBUTE43,
          ATTRIBUTE44      = p_attributesRec.ATTRIBUTE44,
          ATTRIBUTE45      = p_attributesRec.ATTRIBUTE45,
          ATTRIBUTE46      = p_attributesRec.ATTRIBUTE46,
          ATTRIBUTE47      = p_attributesRec.ATTRIBUTE47,
          ATTRIBUTE48      = p_attributesRec.ATTRIBUTE48,
          ATTRIBUTE49      = p_attributesRec.ATTRIBUTE49,
          ATTRIBUTE50      = p_attributesRec.ATTRIBUTE50
      WHERE source_application = p_source_application
      AND   source_context     = p_source_context
      AND   context_value      = p_context_value;

      IF (p_commit = 'S') THEN
         COMMIT;
      END IF;

      x_return_status := 'S';
      x_msg_data      := NULL;

   EXCEPTION
      WHEN OTHERS THEN
         x_return_status := 'E';
         x_msg_data      := 'Update_Dff_Data Exception : ' || SQLERRM;

   END UPDATE_DFF_DATA;

END CANON_E389_DFF_UTILITY_PKG;
/
Show Err
