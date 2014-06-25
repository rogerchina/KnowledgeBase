package com.debuglife.codelabs.qdpcache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class DBQuery {
	public static String object_instance_ky = "object_instance_ky";
	public static String object_instance_nm = "object_instance_nm";
	public static String objecttype_mastertable = "MasterTable";
	public static String objecttype_detailtable = "DetailTable";
	public static String tabletype_master = "master";
	public static String tabletype_detail = "detail";
	
	
	private static String sql_base = "select oi.OBJECT_INSTANCE_KY,sae.STATIC_ATTR_NM,osa.STATIC_ATTR_VALUE_TX,oi.OBJECT_INSTANCE_NM,oi.OBJECT_INSTANCE_VER_NM"
			+ " from SITCONFIGUSER.OBJECT_INSTANCE oi"
			+ " join SITCONFIGUSER.ENVIRONMENT_TYPE et ON et.ENVIRONMENT_TYPE_KY = oi.ENVIRONMENT_TYPE_KY"
			+ " join SITCONFIGUSER.OBJECT_TYPE ot ON ot.OBJECT_TYPE_KY = oi.OBJECT_TYPE_KY"
			+ " join SITCONFIGUSER.PRODUCT_TYPE pt ON pt.PRODUCT_TYPE_KY = oi.PRODUCT_TYPE_KY"
			+ " join SITCONFIGUSER.OBJECT_STATIC_ATTR osa ON osa.OBJECT_INSTANCE_KY = oi.OBJECT_INSTANCE_KY"
			+ " join SITCONFIGUSER.STATIC_ATTR_ENUMERATION sae on sae.STATIC_ATTR_ENUMERATION_KY = osa.STATIC_ATTR_ENUMERATION_KY"
			+ " where et.ENVIRONMENT_TYPE_NM=?"
			+ " and ot.OBJECT_TYPE_NM=?"
			+ " and pt.PRODUCT_TYPE_NM=?"
			+ " and oi.OBJECT_INSTANCE_ACTIVE_FG=?"
			+ " and sae.ACTIVE_FG='Y'";
	private static String sql_query_orderby_desc = " order by oi.OBJECT_INSTANCE_VER_NM desc,oi.OBJECT_INSTANCE_KY";
	private static String sql_query_orderby_asc  = " order by oi.OBJECT_INSTANCE_VER_NM asc,oi.OBJECT_INSTANCE_KY";
	
	private static String sql_query_type = sql_base	+ sql_query_orderby_desc;
	//workflow need be sorted
	private static String sql_query_type_asc = sql_base	+ "order by osa.SORT_ORDER_NR";
	
	private static String sql_query_ky = sql_base + " and oi.OBJECT_INSTANCE_KY=?" + sql_query_orderby_desc;
	private static String sql_query_nm = sql_base + " and oi.OBJECT_INSTANCE_NM=?" + sql_query_orderby_desc;
	
	private static String sql_query_type_update = sql_base + sql_query_orderby_asc;
	
	
	private static String sql_testtype_base = "select oi2.OBJECT_INSTANCE_KY as PARENT_OBJECT_INSTANCE_KY,sae.STATIC_ATTR_NM,osa.STATIC_ATTR_VALUE_TX,oi2.OBJECT_INSTANCE_NM as PARENT_OBJECT_INSTANCE_NM,oi.OBJECT_INSTANCE_VER_NM,ot.OBJECT_TYPE_NM,oi.OBJECT_INSTANCE_KY"
			+ " from SITCONFIGUSER.OBJECT_INSTANCE oi"
			+ " join SITCONFIGUSER.OBJECT_TYPE ot ON ot.OBJECT_TYPE_KY = oi.OBJECT_TYPE_KY"
	  		+ " join SITCONFIGUSER.OBJECT_STATIC_ATTR osa ON osa.OBJECT_INSTANCE_KY = oi.OBJECT_INSTANCE_KY"
	  		+ " join SITCONFIGUSER.STATIC_ATTR_ENUMERATION sae on sae.STATIC_ATTR_ENUMERATION_KY = osa.STATIC_ATTR_ENUMERATION_KY"
	  		+ " join SITCONFIGUSER.OBJECT_INSTANCE oi2 on oi.PARENT_OBJECT_INSTANCE_KY = oi2.OBJECT_INSTANCE_KY"
			+ " where oi.OBJECT_INSTANCE_ACTIVE_FG=?"
	      		+ " and sae.ACTIVE_FG='Y'"
	      		+ " and oi.OBJECT_INSTANCE_KY in"
			+ " (select OBJECT_INSTANCE_KY from SITCONFIGUSER.OBJECT_INSTANCE where PARENT_OBJECT_INSTANCE_KY in"
			+ " (select oi.OBJECT_INSTANCE_KY"
			+ " from SITCONFIGUSER.OBJECT_INSTANCE oi"
	  		+ " join SITCONFIGUSER.ENVIRONMENT_TYPE et ON et.ENVIRONMENT_TYPE_KY = oi.ENVIRONMENT_TYPE_KY"
	  		+ " join SITCONFIGUSER.OBJECT_TYPE ot ON ot.OBJECT_TYPE_KY = oi.OBJECT_TYPE_KY"
	  		+ " join SITCONFIGUSER.PRODUCT_TYPE pt ON pt.PRODUCT_TYPE_KY = oi.PRODUCT_TYPE_KY"
			+ " where et.ENVIRONMENT_TYPE_NM=?"
	    		+ " and pt.PRODUCT_TYPE_NM=?"
	    		+ " and ot.OBJECT_TYPE_NM=?"
	    		+ " and oi.OBJECT_INSTANCE_ACTIVE_FG=?";
	private static String sql_testtype_end = "))";
	private static String sql_testtype_orderby_desc = " order by oi.OBJECT_INSTANCE_VER_NM desc,oi2.OBJECT_INSTANCE_KY,ot.OBJECT_TYPE_NM,oi.OBJECT_INSTANCE_KY";
	private static String sql_testtype_orderby_asc = " order by oi.OBJECT_INSTANCE_VER_NM asc,oi2.OBJECT_INSTANCE_KY,ot.OBJECT_TYPE_NM,oi.OBJECT_INSTANCE_KY";
	
	private static String sql_testtype_init = sql_testtype_base + sql_testtype_end + sql_testtype_orderby_desc;
	private static String sql_testtype_ky_init = sql_testtype_base + " and oi.OBJECT_INSTANCE_KY=?" + sql_testtype_end + sql_testtype_orderby_desc;
	private static String sql_testtype_nm_init = sql_testtype_base + " and oi.OBJECT_INSTANCE_NM=?" + sql_testtype_end + sql_testtype_orderby_desc;
	private static String sql_testtype_update = sql_testtype_base + sql_testtype_end + sql_testtype_orderby_asc;
	
	
	public static void initTypeCache(String type) {
		if(type.equals(QDPCache.TESTTYPE_STRING)){
			initTestTypeCache(null,null);
			return;
		}
		try{
			String sql = sql_query_type;
			if(type.equals(QDPCache.QDPWORKFLOW_STRING)){
				sql = sql_query_type_asc;
			}
			PreparedStatement ps = createPreparedStatement(sql, type);
			ResultSet rs = ps.executeQuery();
			initResultSetData(type,rs);
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while init ["+type+"] cache: "+e);
		}
	}
	public static void updateTypeCache(String type) {
		updateTypeCache(type, null);
	}
	public static void updateTypeCache(String type,String[] objectInstanceKys) {
		if(type.equals(QDPCache.TESTTYPE_STRING)){
			updateTestTypeCache(objectInstanceKys);
			return;
		}
		String sql;
		if(objectInstanceKys==null || objectInstanceKys.length<1){
			sql = sql_query_type_update;
		}else{
			StringBuilder sb = new StringBuilder();
			for(String ky:objectInstanceKys){
				if(ky!=null&&!"".equals(ky))
					sb.append(ky + ",");
			}
			if(sb.length()>1){
				sql = sql_base + "and oi.OBJECT_INSTANCE_KY in ("+sb.substring(0, sb.length()-1)+")" + sql_query_orderby_asc;
			}else{
				sql = sql_query_type_update;
			}
		}
		try{
			PreparedStatement ps = createPreparedStatement(sql, type);
			ResultSet rs = ps.executeQuery();
			updateResultSetData(type,rs);
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while update ["+type+"] cache: "+e);
		}
	}
	
	public static void initKyCache(String type, String objectInstanceKy) {
		if(type.equals(QDPCache.TESTTYPE_STRING)){
			initTestTypeCache(objectInstanceKy,null);
			return;
		}
		try{
			PreparedStatement ps = createPreparedStatement(sql_query_ky, type);
			ps.setInt(5, Integer.parseInt(objectInstanceKy));
			ResultSet rs = ps.executeQuery();
			initResultSetData(type,rs);
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while init ["+type+"]["+objectInstanceKy+"] cache: "+e);
		}
	}
	public static void initNmCache(String type, String objectInstanceNm) {
		if(type.equals(QDPCache.TESTTYPE_STRING)){
			initTestTypeCache(null,objectInstanceNm);
			return;
		}
		try{
			PreparedStatement ps = createPreparedStatement(sql_query_nm, type);
			ps.setString(5, objectInstanceNm);
			ResultSet rs = ps.executeQuery();
			initResultSetData(type,rs);
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while init ["+type+"]["+objectInstanceNm+"] cache: "+e);
		}
	}
	private static PreparedStatement createPreparedStatement(String sql,String type) throws Exception {
		Connection con = DBConnection.getDbConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, DBConnection.environmentTypeNm);
		ps.setString(2, type);
		ps.setString(3, DBConnection.productTypeNm);
		ps.setString(4, DBConnection.activeFg);
		return ps;
	}
	private static void initResultSetData(String type,ResultSet rs) throws Exception{
		String ky;
		Map<String, Map<String, Object>> objectInstancesMap = QDPCache.QDP_MAP.get(type);
		while (rs.next()) {
			ky = rs.getObject(1).toString();
			if(objectInstancesMap.containsKey(ky)){
				if(!objectInstancesMap.get(ky).containsKey(rs.getObject(2))){
					objectInstancesMap.get(ky).put(rs.getObject(2).toString(), rs.getObject(3));
				}
			}else{
				Map<String,Object> objectInstance = new LinkedHashMap<String,Object>();
				objectInstance.put(object_instance_ky, ky);
				objectInstance.put(object_instance_nm, rs.getObject(4).toString());
				if(type.equals(QDPCache.TASKORDER_STRING)){
					objectInstance.put("taskorderversion", rs.getObject(5).toString());
				}
				objectInstance.put(rs.getObject(2).toString(), rs.getObject(3));
				objectInstancesMap.put(ky, objectInstance);
			}
		}
	}
	private static void updateResultSetData(String type,ResultSet rs) throws Exception{
		String ky;
		Map<String, Map<String, Object>> objectInstancesMap = QDPCache.QDP_MAP.get(type);
		List<String> kyls = new ArrayList<String>();
		while (rs.next()) {
			ky = rs.getObject(1).toString();
			if(objectInstancesMap.containsKey(ky)){
				if(!equals(rs.getObject(3),objectInstancesMap.get(ky).get(rs.getObject(2).toString()))){
					objectInstancesMap.get(ky).put(rs.getObject(2).toString(), rs.getObject(3));
				}
				if(!equals(rs.getObject(4),objectInstancesMap.get(ky).get(object_instance_nm))){
					objectInstancesMap.get(ky).put(object_instance_nm, rs.getObject(4));
				}
				if(kyls.indexOf(ky)==-1){
					kyls.add(ky);
				}
			}else{
				Map<String,Object> objectInstance = new HashMap<String,Object>();
				objectInstance.put(object_instance_ky, ky);
				objectInstance.put(object_instance_nm, rs.getObject(4).toString());
				if(type.equals(QDPCache.TASKORDER_STRING)){
					objectInstance.put("taskorderversion", rs.getObject(5).toString());
				}
				objectInstance.put(rs.getObject(2).toString(), rs.getObject(3));
				objectInstancesMap.put(ky, objectInstance);
				kyls.add(ky);
			}
		}
		updateObjectInstanceUpdateFlags(kyls);
	}
	private static void updateObjectInstanceUpdateFlags(List<String> kyls){
		if(kyls==null || kyls.size()<1){
			return;
		}
		StringBuilder sb = new StringBuilder();
		for(String ky:kyls){
			if(ky!=null&&!"".equals(ky))
				sb.append(ky + ",");
		}
//		try{
			//TODO
//			Connection con = DBConnection.getDbConnection();
//			PreparedStatement ps = con.prepareStatement("update SITCONFIGUSER.OBJECT_INSTANCE set ***=? where OBJECT_INSTANCE_KY in ("+sb.substring(0, sb.length()-1)+")");
//			ps.setString(1, "***");
//			ps.executeUpdate();
//			ps.close();
//		} catch (Exception e) {
//			throw new RuntimeException("Exception occurred while updateObjectInstanceFlags:"+e);
//		}
	}
	@SuppressWarnings("unchecked")
	private static void initTestTypeCache(String objectInstanceKy,String objectInstanceNm){
		String sql;
		String anotherParam = null;
		if(objectInstanceKy!=null&&!objectInstanceKy.equals("")){
			sql = sql_testtype_ky_init;
			anotherParam = objectInstanceKy;
		}else if(objectInstanceNm!=null&&!objectInstanceNm.equals("")){
			sql = sql_testtype_nm_init;
			anotherParam = objectInstanceNm;
		}else{
			sql = sql_testtype_init;
		}
		try{
			Connection con = DBConnection.getDbConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, DBConnection.activeFg);
			ps.setString(2, DBConnection.environmentTypeNm);
			ps.setString(3, DBConnection.productTypeNm);
			ps.setString(4, QDPCache.TESTTYPE_STRING);
			ps.setString(5, DBConnection.activeFg);
			if(anotherParam!=null){
				if(anotherParam.equals(objectInstanceKy)){
					ps.setInt(6, Integer.parseInt(objectInstanceKy));
				}else{
					ps.setString(6, objectInstanceNm);
				}
			}
			ResultSet rs = ps.executeQuery();
			/** ResultSet as below:
			 * PARENT_OBJECT_INSTANCE_KY	STATIC_ATTR_NM	STATIC_ATTR_VALUE_TX	PARENT_OBJECT_INSTANCE_NM	OBJECT_INSTANCE_VER_NM	OBJECT_TYPE_NM	OBJECT_INSTANCE_KY
				3495						name			mbpt_obs				mbpt_main					1.0						DetailTable		3497
				3495						keyword			mbpt_obs				mbpt_main					1.0						DetailTable		3497
				3495						name			mbpt_main				mbpt_main					1.0						MasterTable		3496
				3498						name			display_uniform			display_main				1.0						DetailTable		3500
				3498						keyword			uniform					display_main				1.0						DetailTable		3500
				3498						name			display_9ptcontrast		display_main				1.0						DetailTable		3501
				3498						keyword			contrast				display_main				1.0						DetailTable		3501
				3498						name			display_hemi			display_main				1.0						DetailTable		3502
				3498						keyword			hemi					display_main				1.0						DetailTable		3502
				3498						name			display_main			display_main				1.0						MasterTable		3499
			 */
			String ky;
			Map<String, Map<String, Object>> objectInstancesMap = QDPCache.QDP_MAP.get(QDPCache.TESTTYPE_STRING);
			int i = 0;
			while (rs.next()) {
				ky = ObjtoString(rs.getObject(1));
				
				String objectTypeNm = ObjtoString(rs.getObject(6));
				
			//	System.out.println((i++)+" "+ky + " "+ objectTypeNm);
				if(objectInstancesMap.containsKey(ky)){
					if(objectTypeNm.equals(objecttype_mastertable)){
						if(!objectInstancesMap.get(ky).containsKey(tabletype_master)){
							objectInstancesMap.get(ky).put(tabletype_master, rs.getObject(3));
						}
					}else if(objectTypeNm.equals(objecttype_detailtable)){
						if(!objectInstancesMap.get(ky).containsKey(tabletype_detail)){
							Map<String,Map<String,String>> detailTables = new HashMap<String,Map<String,String>>();
							Map<String,String> detailTable = new HashMap<String,String>();
							detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
							detailTables.put(ObjtoString(rs.getObject(7)), detailTable);
							objectInstancesMap.get(ky).put(tabletype_detail, detailTables);
						}else{
							String detailKy = ObjtoString(rs.getObject(7));
							Map<String,Map<String,String>> detailTables = (Map<String,Map<String,String>>)objectInstancesMap.get(ky).get(tabletype_detail);
							if(!detailTables.containsKey(detailKy)){
								Map<String,String> detailTable = new HashMap<String,String>();
							
								detailTable.put(ObjtoString(rs.getObject(2)),ObjtoString(rs.getObject(3)));
								detailTables.put(detailKy, detailTable);
							}else{
								Map<String,String> detailTable = detailTables.get(detailKy);
								if(!detailTable.containsKey(rs.getObject(2))){
									
									detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
								}
							}
						}
					}
				}else{
					Map<String,Object> objectInstance = new HashMap<String,Object>();
					objectInstance.put(object_instance_ky, ky);
					objectInstance.put(object_instance_nm, ObjtoString(rs.getObject(4)));
					if(objectTypeNm.equals(objecttype_mastertable)){
						objectInstance.put(tabletype_master, ObjtoString(rs.getObject(3)));
					}else if(objectTypeNm.equals(objecttype_detailtable)){
						Map<String,Map<String,String>> detailTables = new HashMap<String,Map<String,String>>();
						Map<String,String> detailTable = new HashMap<String,String>();
						detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
						detailTables.put(ObjtoString(rs.getObject(7)), detailTable);
						objectInstance.put(tabletype_detail, detailTables);
					}
					objectInstancesMap.put(ky, objectInstance);
				}
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while init [TestType]["+objectInstanceKy+"]["+objectInstanceNm+"] cache: "+e);
		}
	}
	@SuppressWarnings("unchecked")
	private static void updateTestTypeCache(String[] objectInstanceKys) {
		String sql;
		if(objectInstanceKys==null || objectInstanceKys.length<1){
			sql = sql_testtype_update;
		}else{
			StringBuilder sb = new StringBuilder();
			for(String ky:objectInstanceKys){
				if(ky!=null&&!"".equals(ky))
					sb.append(ky + ",");
			}
			if(sb.length()>1){
				sql = sql_testtype_base + "and oi.OBJECT_INSTANCE_KY in ("+sb.substring(0, sb.length()-1)+")" + sql_testtype_end + sql_testtype_orderby_asc;
			}else{
				sql = sql_testtype_update;
			}
		}
		try{
			Connection con = DBConnection.getDbConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, DBConnection.activeFg);
			ps.setString(2, DBConnection.environmentTypeNm);
			ps.setString(3, DBConnection.productTypeNm);
			ps.setString(4, QDPCache.TESTTYPE_STRING);
			ps.setString(5, DBConnection.activeFg);
			ResultSet rs = ps.executeQuery();
			String ky;
			Map<String, Map<String, Object>> objectInstancesMap = QDPCache.QDP_MAP.get(QDPCache.TESTTYPE_STRING);
			List<String> kyls = new ArrayList<String>();
			while (rs.next()) {
				ky = ObjtoString(rs.getObject(1));
				String objectTypeNm = ObjtoString(rs.getObject(6));
				if(objectInstancesMap.containsKey(ky)){
					if(objectTypeNm.equals(objecttype_mastertable)){
						if(!equals(rs.getObject(3),objectInstancesMap.get(ky).get(objecttype_mastertable))){
							objectInstancesMap.get(ky).put(objecttype_mastertable, rs.getObject(3));
						}
					}else if(objectTypeNm.equals(objecttype_detailtable)){
						if(!objectInstancesMap.get(ky).containsKey(tabletype_detail)){
							Map<String,Map<String,String>> detailTables = new HashMap<String,Map<String,String>>();
							Map<String,String> detailTable = new HashMap<String,String>();
							detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
							detailTables.put(ObjtoString(rs.getObject(7)), detailTable);
							objectInstancesMap.get(ky).put(tabletype_detail, detailTables);
						}else{
							String detailKy = ObjtoString(rs.getObject(7));
							Map<String,Map<String,String>> detailTables = (Map<String,Map<String,String>>)objectInstancesMap.get(ky).get(tabletype_detail);
							if(!detailTables.containsKey(detailKy)){
								Map<String,String> detailTable = new HashMap<String,String>();
								detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
								detailTables.put(detailKy, detailTable);
							}else{
								Map<String,String> detailTable = detailTables.get(detailKy);
								if(!equals(rs.getObject(3),detailTable.get(rs.getObject(2)))){
									objectInstancesMap.get(ky).put(objecttype_mastertable, rs.getObject(3));
									detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
								}
							}
						}
					}
					if(!equals(rs.getObject(4),objectInstancesMap.get(ky).get(object_instance_nm))){
						objectInstancesMap.get(ky).put(object_instance_nm, rs.getObject(4));
					}
					if(kyls.indexOf(ky)==-1){
						kyls.add(ky);
					}
				}else{
					Map<String,Object> objectInstance = new HashMap<String,Object>();
					objectInstance.put(object_instance_ky, ky);
					objectInstance.put(object_instance_nm, ObjtoString(rs.getObject(4)));
					if(objectTypeNm.equals(objecttype_mastertable)){
						objectInstance.put(tabletype_master, ObjtoString(rs.getObject(3)));
					}else if(objectTypeNm.equals(objecttype_detailtable)){
						Map<String,Map<String,String>> detailTables = new HashMap<String,Map<String,String>>();
						Map<String,String> detailTable = new HashMap<String,String>();
						detailTable.put(ObjtoString(rs.getObject(2)), ObjtoString(rs.getObject(3)));
						detailTables.put(ObjtoString(rs.getObject(7)), detailTable);
						objectInstance.put(tabletype_detail, detailTables);
					}
					objectInstancesMap.put(ky, objectInstance);
					kyls.add(ky);
				}
			}
			updateObjectInstanceUpdateFlags(kyls);
			rs.close();
			ps.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while update [TestType] cache: "+e);
		}
	}
	
	private static boolean equals(Object o1, Object o2){
		if(o1==o2){
			return true;
		}
		if(o1==null || o2==null){
			return false;
		}
		return o1.equals(o2);
	}
	
	
	public static String ObjtoString(Object obj){
		if(obj != null){
			return obj.toString();
		}else{
			return "";
		}
	}
	
	
//	public static void main(String[] args) {
//		List<String> kyls = new ArrayList<String>();
//		kyls.add("430");
//		kyls.add("433");
//		updateObjectInstanceUpdateFlags(kyls);
//		DBConnection.closeConnection();
//	}
}
