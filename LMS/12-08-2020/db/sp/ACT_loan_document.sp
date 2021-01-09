/* __VER_INFO__  :  */
/****************************************************************************************
* Author        : Mirajul islam
* Date          : 22 JAN 2020
* Description   : 
*****************************************************************************************/
#include <nSMART_SQL.h>

#define _SP_NAME        {ACT_loan_document};
#define _TABLE_NAME     {T_LOAN_DOCUMENT};
#define _PRIMARY_KEY    {id_loan_doc_key};
#define _VERSION        {id_loan_doc_ver};


_DROP_PROC

_CREATE_PROC (_SP_NAME)
    
  _SP_PARAM_HEADER

	, @_PRIMARY_KEY					      INT					       = NULL		OUTPUT
	, @_VERSION						        INT					       = NULL	

  , @id_loan_key                INT                = NULL
  , @id_doc_key                 INT                = NULL
	, @tx_doc_type   					    VARCHAR(256)			 = NULL   
  , @int_upload_status          INT                = NULL   
  , @tx_download_link				  	VARCHAR(256)       = NULL
  , @int_is_mandatory					  INT 					     = NULL
  , @tx_document_name   				VARCHAR(256)       = NULL
  , @tx_document_path           VARCHAR(256)       = NULL
  , @int_file_present           INT                = NULL
  , @id_loan_type_key           INT                = NULL
  , @id_customer_type_key       INT                = NULL

   _SP_PARAM_FOOTER

AS

{
  _SP_HEADER

  IF( @tx_action_name = 'NEW_CIB_STATUS')
  {
    DECLARE @l_id_loan_doc_key INT = 
    ( 
      SELECT  id_loan_doc_key FROM  T_LOAN_DOCUMENT 
      WHERE id_loan_key     = @id_loan_key
      AND   tx_doc_type     = 'CIB_STATUS'
      AND   is_active       = 1
    )

    IF (@l_id_loan_doc_key IS NULL)
    {
      SET @tx_action_name = _ACTION_NEW
    }
    ELSE
    {
      SET @id_loan_doc_key = @l_id_loan_doc_key
      SET @tx_action_name = _ACTION_UPDATE
    }
  }

  IF ( @tx_action_name = 'NEW_MOBILE_VIEW' )
  {
    DECLARE @ll_id_loan_doc_key INT = 
    (
      SELECT id_loan_doc_key FROM T_LOAN_DOCUMENT
      WHERE id_loan_key = @id_loan_key
      AND tx_doc_type = @tx_doc_type
    )

    IF(@ll_id_loan_doc_key IS NOT NULL)
    {
      _SET_ACTION(_ACTION_UPDATE)
      SET @id_loan_doc_key = @ll_id_loan_doc_key
    }
    ELSE
    {
      _SET_ACTION(_ACTION_NEW)
    }
  }

  IF ( @tx_action_name = 'SELECT_DOC_FOR_EXISTING_LOAN' )
  {
    SELECT tx_rs_type = 'RS_TYPE_LOAN_DOCUMENT_EXIST'
      ,D.* 
    FROM T_LOAN_DOCUMENT D
    WHERE D.id_loan_key = @id_loan_key
    AND   D.tx_doc_type != 'CIB_STATUS' 
    AND D.is_active = 1
  }

  IF ( @tx_action_name = 'SELECT_LOAN_ALL_DOCUMENT' )
  {
    SELECT D.id_loan_doc_key
     , D.id_loan_key
     , D.id_doc_key
     , D.tx_doc_type
     , D.int_is_mandatory
     , D.int_file_present
     , D.int_upload_status
    INTO #TEMP_T_LOAN_DOCUMENT
    FROM T_LOAN_DOCUMENT D
    WHERE D.id_loan_key = @id_loan_key
    AND   D.tx_doc_type     != 'CIB_STATUS' 
    AND   D.is_active = 1

    SELECT NULL AS id_loan_doc_key
    , @id_loan_key AS id_loan_key
    , C.id_configuration_key AS id_doc_key
    , C.tx_value1 AS tx_doc_type
    , M.is_mandatory AS int_is_mandatory
    , 0 AS int_file_present
    , 0 AS int_upload_status
    INTO #TEMP_T_LOAN_DOCUMENT_2
    FROM T_CONFIGURATION C
    JOIN T_LOAN_DOC_MAP M ON M.id_doc_key = C.id_configuration_key
    JOIN T_LOAN_CONFIG LC ON LC.id_loan_config_key = M.id_loan_config_key
    WHERE LC.id_loan_type_key = @id_loan_type_key
    AND LC.id_customer_type_key = @id_customer_type_key
    AND C.tx_value1 NOT IN 
    (
      SELECT tx_doc_type FROM #TEMP_T_LOAN_DOCUMENT
    )
    AND M.is_active = 1
    AND C.is_active = 1
    AND LC.is_active = 1

    SELECT DISTINCT tx_rs_type = 'RS_TYPE_LOAN_DOCUMENT', X.tx_doc_type ,X.*
    FROM
    (
      SELECT * FROM #TEMP_T_LOAN_DOCUMENT
      UNION
      SELECT * FROM #TEMP_T_LOAN_DOCUMENT_2
    ) X
    ORDER BY X.tx_doc_type ASC, X.int_is_mandatory DESC
  }
  
  IF ( @tx_action_name = 'DELETE_DOC_FOR_EXISTING_LOAN' )
  {
    UPDATE _TABLE_NAME 
    SET is_active = 0
    WHERE id_loan_key     = @id_loan_key
    AND   tx_doc_type     != 'CIB_STATUS' 
  }
  IF ( @tx_action_name = 'MAKE_LOAN_DOC_ACTIVE' )
  {
    UPDATE _TABLE_NAME 
    SET is_active = 1
    WHERE id_loan_doc_key  = @id_loan_doc_key
  }

  IF ( @tx_action_name = 'SELECT_CIB_STATUS_DOC' )
  {
    SELECT  tx_rs_type = 'RS_TYPE_LOAN_DOCUMENT'
    , L.*
    FROM  _TABLE_NAME L
    WHERE id_loan_key     = ISNULL(@id_loan_key       , id_loan_key)
    AND   tx_doc_type     = ISNULL(@tx_doc_type       , tx_doc_type) 
    AND   is_active       = 1
  }
  
  IF ( @tx_action_name = 'SELECT_DOC_BY_LOAN_ID_DOC_TYPE' )
  {
    SELECT  tx_rs_type = 'RS_TYPE_LOAN_DOCUMENT'
    , L.*
    FROM  _TABLE_NAME L
    WHERE id_loan_key     = @id_loan_key
    AND   tx_doc_type     = @tx_doc_type
    AND   is_active       = 1
  }

  IF ( @tx_action_name = _ACTION_NEW )
  {
    _INIT_VERSION(@_VERSION)


    INSERT INTO _TABLE_NAME
    (
     id_loan_doc_ver
     ,_TABLE_HEADER_INS_FIELD_WITH_STATE

     ,id_loan_key
     ,id_doc_key
     ,tx_doc_type
     ,int_upload_status
     ,tx_download_link
     ,int_is_mandatory
     ,tx_document_name
     ,tx_document_path
     ,int_file_present
    )
    VALUES
    (
     @_VERSION
    ,_TABLE_HEADER_INS_VAL_WITH_STATE

    , ISNULL(@id_loan_key              , _DB_NULL_INT)
    , ISNULL(@id_doc_key               , _DB_NULL_INT)
    , ISNULL(@tx_doc_type              , _DB_NULL_STR)
    , ISNULL(@int_upload_status        , _DB_NULL_INT)
    , ISNULL(@tx_download_link         , _DB_NULL_STR)
    , ISNULL(@int_is_mandatory         , _DB_NULL_INT)
    , ISNULL(@tx_document_name         , _DB_NULL_STR)
    , ISNULL(@tx_document_path         , _DB_NULL_STR)
    , ISNULL(@int_file_present         , 0)

    )

    SELECT @_PRIMARY_KEY = IDENT_CURRENT('T_LOAN_DOCUMENT')

    _STORE_SYS_VARS     
    SELECT @g_tx_err_msg = _GEM_INSERT(_TABLE_NAME, @_PRIMARY_KEY)
    _HANDLE_ZERO_ROW_COUNT(_GEC_INSERT, @g_tx_err_msg)

    _TOUCHED_TABLE(_TABLE_NAME)

    _SP_FOOTER

    RETURN _STATUS_OK 
  }   

  IF ( @tx_action_name = _ACTION_SELECT )
  {
    SELECT  tx_rs_type = 'RS_TYPE_LOAN_DOCUMENT'
    , L.*
    FROM  _TABLE_NAME L
    WHERE id_loan_doc_key = ISNULL(@id_loan_doc_key   ,id_loan_doc_key)
    AND   id_loan_key     = ISNULL(@id_loan_key       , id_loan_key)
    AND   tx_doc_type     != 'CIB_STATUS' 
    AND   is_active       = 1
    ORDER BY int_is_mandatory DESC, tx_doc_type ASC
  }

  IF (@tx_action_name = _ACTION_DELETE)
  { 
    _SET_ACTION(_ACTION_UPDATE)
    , @is_active = 0
  }

  IF( @tx_action_name = _ACTION_UPDATE )
  {
    UPDATE _TABLE_NAME
    SET 
    _TABLE_HEADER_UPD_WITH_STATE

    , id_loan_key                = ISNULL(@id_loan_key                ,id_loan_key)
    , id_doc_key                 = ISNULL(@id_doc_key                 ,id_doc_key)
    , tx_doc_type                = ISNULL(@tx_doc_type                ,tx_doc_type)
    , int_upload_status          = ISNULL(@int_upload_status          ,int_upload_status)
    , tx_download_link           = ISNULL(@tx_download_link           ,tx_download_link)
    , int_is_mandatory           = ISNULL(@int_is_mandatory           ,int_is_mandatory)
    , tx_document_name           = ISNULL(@tx_document_name           ,tx_document_name)
    , tx_document_path           = ISNULL(@tx_document_path           ,tx_document_path)
    , int_file_present           = ISNULL(@int_file_present           ,int_file_present)

    WHERE   id_loan_doc_key      = @id_loan_doc_key
    AND     is_active = 1
  }

  _SP_FOOTER
}
go

_GRANT_PERM_SP