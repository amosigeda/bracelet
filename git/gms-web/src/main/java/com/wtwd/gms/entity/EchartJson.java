package com.wtwd.gms.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台返回到前台的json实体类
 * @ClassName:       EchartJson
 * @Description:     TODO
 * @author:          wuhaihui
 * @date:            2017年8月1日        上午11:27:57
 */
public class EchartJson extends BaseEntity{

	List<String> xAxisData = new ArrayList<>();
	List<Item> itemData = new ArrayList<>();
	
	public List<String> getxAxisData() {
		return xAxisData;
	}



	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}



	public List<Item> getItemData() {
		return itemData;
	}



	public void setItemData(List<Item> itemData) {
		this.itemData = itemData;
	}



	public class Item{
		String name;
		List<Long> data = new ArrayList<>();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Long> getData() {
			return data;
		}
		public void setData(List<Long> data) {
			this.data = data;
		}
		
		
		
	}
}
