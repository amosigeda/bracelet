package com.wtwd.gms.entity;

public class WtBaseCount {
	
	public String sn;
	public String dev_name;
	public String country;
	public String mobile_op;
	public String mobile_model;
	public String mobile_brand;
	public String hour;
	public int count;//每日活跃数
	public int dayActive;//N天活跃数
	public int sum;//活跃总数
	public String occupancy;//占有率
	
	
	
	
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile_model() {
		return mobile_model;
	}
	public void setMobile_model(String mobile_model) {
		this.mobile_model = mobile_model;
	}
	public String getMobile_brand() {
		return mobile_brand;
	}
	public void setMobile_brand(String mobile_brand) {
		this.mobile_brand = mobile_brand;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getMobile_op() {
		return mobile_op;
	}
	public void setMobile_op(String mobile_op) {
		this.mobile_op = mobile_op;
	}
	
	public String getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}
	public int getDayActive() {
		return dayActive;
	}
	public void setDayActive(int dayActive) {
		this.dayActive = dayActive;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
	
	
	
	

}
