package com.debuglife.codelabs.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.debuglife.codelabs.batch.data.LineVo;


/**
 * <b>输出</b><br>
 * @author		Gaylen
 * @version		V1.1.0
 * history
 * 1.1.0, 2014年11月24日		Gaylen			FE
 * @since		Java 6.0
 */
public class CustomWriter implements ItemWriter<LineVo> {

	@Override
	public void write(List<? extends LineVo> items) throws Exception {
		if (items == null || items.size() == 0) {
			System.out.println("error.");
		} else {
			for (LineVo lv : items) {
				System.out.println(lv.getFullName());
			}
		}

	}
}
