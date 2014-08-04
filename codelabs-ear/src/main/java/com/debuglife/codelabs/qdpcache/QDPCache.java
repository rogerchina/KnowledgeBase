package com.debuglife.codelabs.qdpcache;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class QDPCache {
	public static void initDBConfigData(Map<String, String> initParams) {
		DBConnection.initData(initParams);
	}
	public static void setDbConnection(Connection conn){
		DBConnection.setDbConnection(conn);
	}
	public static void initQDPCache(){
		initQDPCache(null);
	}
	public static void initQDPCache(String type) {
		if (type == null) {
			type = "all";
		}
		if (type.equals(TASKORDER_STRING)) {
			DBQuery.initTypeCache(type);
		} else if (type.equals(HPQDPERROR_STRING)) {
			DBQuery.initTypeCache(type);
		} else if (type.equals(SFTPCONNECTION_STRING)) {
			DBQuery.initTypeCache(type);
		} else if (type.equals(QDPWORKFLOW_STRING)) {
			DBQuery.initTypeCache(type);
		} else if (type.equals(TESTTYPE_STRING)) {
			DBQuery.initTypeCache(type);
		} else {
			for(String objectType:OBJECT_TYPES){
				DBQuery.initTypeCache(objectType);
			}
		}
	}
	public static void updateQDPCache(){
		updateQDPCache(null);
	}
	public static void updateQDPCache(String type) {
		if (type == null) {
			type = "all";
		}
		if (type.equals(TASKORDER_STRING)) {
			DBQuery.updateTypeCache(type);
		} else if (type.equals(HPQDPERROR_STRING)) {
			DBQuery.updateTypeCache(type);
		} else if (type.equals(SFTPCONNECTION_STRING)) {
			DBQuery.updateTypeCache(type);
		} else if (type.equals(QDPWORKFLOW_STRING)) {
			DBQuery.updateTypeCache(type);
		} else if (type.equals(TESTTYPE_STRING)) {
			DBQuery.updateTypeCache(type);
		} else {
			for(String objectType:OBJECT_TYPES){
				DBQuery.updateTypeCache(objectType);
			}
		}
	}
	public static void updateQDPCache(String type,String[] objectInstanceKys) {
		checkTypeParam(type);
		DBQuery.updateTypeCache(type, objectInstanceKys);
	}
	public static String getObjectInstanceByKey(String type, String objectInstanceKy){
		checkTypeParam(type);
		Map<String, Object> objectInstance = QDP_MAP.get(type).get(objectInstanceKy);
		if(objectInstance!=null){
			return getObjectInstanceString(type,objectInstance,objectInstanceKy);
		}else{
			DBQuery.initKyCache(type, objectInstanceKy);
			return getObjectInstanceString(type,QDP_MAP.get(type).get(objectInstanceKy),objectInstanceKy);
		}
	}
	public static String getObjectInstanceByName(String type, String objectInstanceNm){
		checkTypeParam(type);
		return getObjectInstanceByName(type, objectInstanceNm, false);
	}
	public static String identifyErrorCode(String message){
		if(message==null || "".equals(message)){
			return "";
		}
		double matchRate = 0.00, tempRate = 0.00;
		Object errorCodeName = null;
		String accurate_error_keywords,error_keywords;
		String noConfilct = "#_!", messageAfterReplace;
		for(Map<String, Object> hpqdperror : QDP_MAP.get(HPQDPERROR_STRING).values()){
			accurate_error_keywords = (String)hpqdperror.get("accurate_error_keywords");
			if(accurate_error_keywords!=null && accurate_error_keywords.length()>0){
				for(String keyword: accurate_error_keywords.split("\\|")){
					if(message.contains(keyword)){
						return (String)hpqdperror.get(DBQuery.object_instance_nm);
					}
				}
			}
			error_keywords = (String)hpqdperror.get("error_keywords");
			if(error_keywords!=null && error_keywords.length()>0){
				if(error_keywords.substring(0, 1).equals("|")){
					error_keywords = error_keywords.substring(1);
				}
				if(error_keywords.length()>0 && error_keywords.substring(error_keywords.length()-1).equals("|")){
					error_keywords = error_keywords.substring(0, error_keywords.length()-1);
				}
				if(error_keywords.length()==0){
					continue;
				}
				messageAfterReplace = message.replaceAll(error_keywords, noConfilct);
				tempRate = (messageAfterReplace.length() - messageAfterReplace.replaceAll(noConfilct, "").length())*1.00
						/(noConfilct.length()*error_keywords.split("\\|").length);
				if(tempRate>matchRate){
					matchRate = tempRate;
					errorCodeName = hpqdperror.get(DBQuery.object_instance_nm);
				}
			}
		}
		return errorCodeName==null?"":errorCodeName.toString();
	}
//	public static void closeDBConnection(){
//		DBConnection.closeConnection();
//	}
	
	private static String getObjectInstanceByName(String type, String objectInstanceNm,boolean needBreak){
		for(Map<String, Object> map: QDP_MAP.get(type).values()){
			if(map.get(DBQuery.object_instance_nm)!=null && map.get(DBQuery.object_instance_nm).equals(objectInstanceNm)){
				return getObjectInstanceString(type,map,objectInstanceNm);
			}
		}
		if(needBreak){
			return getObjectInstanceString(null,null,objectInstanceNm);
		}
		DBQuery.initNmCache(type, objectInstanceNm);
		return getObjectInstanceByName(type, objectInstanceNm, true);
	}
	private static String getObjectInstanceString(String type,Map<String, Object> objectInstance,String nmOrKyForTestType){
		if(type!=null&&type.equals(TESTTYPE_STRING)){
			return getTestTypeString(objectInstance,nmOrKyForTestType);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version = \"1.0\" encoding = \"UTF-8\"?>");
		sb.append("<ns0:recordset xmlns:ns0 = \"http://www.hp.com/QDP/common/schema/QDPProperties\">");
		if(objectInstance!=null){
			if(null!=type && type.equals(TASKORDER_STRING)){
				for(String key: objectInstance.keySet()){
					if(objectInstance.get(key)==null){
						sb.append("<ns0:fld name=\""+key+"\"/>");
					}else{
						sb.append("<ns0:fld name=\""+key+"\">" + objectInstance.get(key) + "</ns0:fld>");
					}
					if(key.endsWith(".connection")){
						String connectionId = (String)objectInstance.get(key);
						if(connectionId==null){
							continue;
						}else{
							if(!QDP_MAP.get(SFTPCONNECTION_STRING).containsKey(connectionId)){
								DBQuery.initKyCache(SFTPCONNECTION_STRING, connectionId);
							}
							addSftpConnectionStringToTaskOrder(sb, key.substring(0, key.lastIndexOf("connection")), QDP_MAP.get(SFTPCONNECTION_STRING).get(connectionId));
						}
					}
				}
			}else{
				for(String key: objectInstance.keySet()){
					if(objectInstance.get(key)==null){
						sb.append("<ns0:fld name=\""+key+"\"/>");
					}else{
						sb.append("<ns0:fld name=\""+key+"\">" + objectInstance.get(key) + "</ns0:fld>");
					}
				}
			}
		}
		sb.append("</ns0:recordset>");
		return sb.toString();
	}
	private static void addSftpConnectionStringToTaskOrder(StringBuilder sb, String keyPrefix,Map<String, Object> objectInstance){
		if(objectInstance!=null){
			for(String key: objectInstance.keySet()){
				if(objectInstance.get(key)==null){
					sb.append("<ns0:fld name=\""+keyPrefix+key+"\"/>");
				}else{
					sb.append("<ns0:fld name=\""+keyPrefix+key+"\">" + objectInstance.get(key) + "</ns0:fld>");
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	private static String getTestTypeString(Map<String, Object> objectInstance,String nmOrKyForTestType){
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version = \"1.0\" encoding = \"UTF-8\"?>");
		sb.append("<ns0:testType name = \""+nmOrKyForTestType+"\" xmlns:ns0 = \"http://www.hp.com/QDP/configuration/schema/GradeBookConfigSchema\">");
		if(objectInstance!=null){
			sb.append("<ns0:table type = \""+DBQuery.tabletype_master+"\">");
			if(objectInstance.get(DBQuery.tabletype_master)==null){
				sb.append("<ns0:name/>");
			}else{
				sb.append("<ns0:name>"+objectInstance.get(DBQuery.tabletype_master)+"</ns0:name>");
			}
			sb.append("</ns0:table>");
			if(objectInstance.get(DBQuery.tabletype_detail)!=null){
				Map<String,Map<String,String>> detailTables = (Map<String,Map<String,String>>)objectInstance.get(DBQuery.tabletype_detail);
				for(Map<String,String> detail : detailTables.values()){
					sb.append("<ns0:table type = \""+DBQuery.tabletype_detail+"\">");
					for(String key: detail.keySet()){
						if(detail.get(key)==null){
							sb.append("<ns0:"+key+"/>");
						}else{
							sb.append("<ns0:"+key+">"+detail.get(key)+"</ns0:"+key+">");
						}
					}
					sb.append("</ns0:table>");
				}
				
			}
		}
		sb.append("</ns0:testType>");
		return sb.toString();
	}
	private static void checkTypeParam(String type){
		if(type==null){
			throw new NullPointerException("Input parameter type is null.");
		}else{
			if(OBJECT_TYPES.indexOf(type)!=-1){
				return;
			}else{
				throw new IllegalArgumentException("Input parameter type["+type+"] is wrong.");
			}
		}
	}
	
	public final static String TASKORDER_STRING = "TaskOrder";
	public final static String HPQDPERROR_STRING = "HPQDP_ERROR";
	public final static String SFTPCONNECTION_STRING = "SFTPConnection";
	public final static String QDPWORKFLOW_STRING = "QDPWorkflow";
	public final static String TESTTYPE_STRING = "TestType";
	
	public final static List<String> OBJECT_TYPES = new ArrayList<String>();

	public final static Map<String, Map<String, Map<String, Object>>> QDP_MAP = new HashMap<String, Map<String, Map<String, Object>>>();

	 static{
		 OBJECT_TYPES.add(TASKORDER_STRING);
		 OBJECT_TYPES.add(HPQDPERROR_STRING);
		 OBJECT_TYPES.add(SFTPCONNECTION_STRING);
		 OBJECT_TYPES.add(QDPWORKFLOW_STRING);
		 OBJECT_TYPES.add(TESTTYPE_STRING);
		 
		 for(String type:OBJECT_TYPES){
			 QDP_MAP.put(type, new HashMap<String, Map<String, Object>>());
		 }
	 }
}
