package com.debuglife.codelabs.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.debuglife.codelabs.batch.data.LineVo;


/**
 * <b>文本行-逻辑对象映射</b><br>
 */
public class CustomLineMapper implements FieldSetMapper<LineVo> {

	/**
	 * <b>映射处理</b><br>
	 * @param fieldSet
	 * @return DelCommandBean
	 */
	@Override
	public LineVo mapFieldSet(FieldSet fieldSet) throws BindException {
		LineVo lv = new LineVo();
		lv.setId(Integer.parseInt(fieldSet.readString(0)));
		lv.setGivenName(fieldSet.readString(1));
		lv.setFamilyName(fieldSet.readString(2));
		return lv;
	}
}
