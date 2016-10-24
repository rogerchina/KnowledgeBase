package com.debuglife.codelabs.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.debuglife.codelabs.batch.data.LineVo;



/**
 * <b>处理器</b><br>
 */
public class CustomProcessor implements ItemProcessor<LineVo, LineVo> {

	@Override
	public LineVo process(LineVo item) throws Exception {
		if (item == null) {
			return null;
		}
		item.setFullName(new StringBuilder().append(item.getFamilyName() == null ? "*" : item.getFamilyName())
				.append(" - ")
				.append(item.getGivenName() == null ? "*" : item.getGivenName())
				.toString());
		return item;
	}
}
