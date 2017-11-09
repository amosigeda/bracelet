package com.wtwd.gms.entity;

import java.util.Date;

public class WtReportInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6848248096182411822L;

	private Long id;

    private String reportBelong;

    private String filename;

    private Long snSum;

    private String status;

    private String departname;

    private String devname;

    private String country;

    private Date begindate;

    private Date enddate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportBelong() {
        return reportBelong;
    }

    public void setReportBelong(String reportBelong) {
        this.reportBelong = reportBelong == null ? null : reportBelong.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Long getSnSum() {
        return snSum;
    }

    public void setSnSum(Long snSum) {
        this.snSum = snSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname == null ? null : departname.trim();
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname == null ? null : devname.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}