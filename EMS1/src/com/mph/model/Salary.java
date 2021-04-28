package com.mph.model;

import java.io.Serializable;

public class Salary implements Serializable {

	private int basic;
	private double da;
	private double hra;
	private double pf;
	private double net;
	private double gross;
	
	
	
	

	@Override
	public String toString() {
		return "Salary [basic=" + basic + ", da=" + da + ", hra=" + hra + ", pf=" + pf + ", net=" + net + ", gross="
				+ gross + "]";
	}


	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Salary(int basic, int da, int hra, int net, int gross, int pf) {
		super();
		this.basic = basic;
		this.da = da;
		this.hra = hra;
		this.net = net;
		this.gross = gross;
		this.pf = pf;
	}


	public int getBasic() {
		return basic;
	}
	public void setBasic(int basic) {
		this.basic = basic;
	}
	public double getDa() {
		return da;
	}
	public void setDa(int basic) {
		da = 0.1*basic;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(int basic) {
		hra = 0.15*basic;
	}
	public double getNet() {
		return net;
	}
	public void setNet(double d, double e) {
		this.net = d - e;
	}
	public double getGross() {
		return gross;
	}
	public void setGross(int basic, double d, double e) {
		this.gross = basic + d + e;
	}


	public double getPf() {
		return pf;
	}


	public void setPf(int basic) {
		pf = 0.20*basic;
	}
	
	
	
}
