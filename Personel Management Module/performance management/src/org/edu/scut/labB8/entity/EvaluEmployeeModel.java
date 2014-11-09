package org.edu.scut.labB8.entity;

import java.io.Serializable;
import java.util.Date;

public class EvaluEmployeeModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String evalue_id;
	private String evalue_no;
	private Date evalue_date;
	private Double total_score;
	private String demo;
	private Double itemOneScore;
	private Double itemTwoScore;
	private Double itemThreeScore;
	private Double itemFourScore;
	private Double itemFiveScore;
	
	public String getevalue_id(){return evalue_id;}
	public void setevalue_id(String evalue_id){this.evalue_id=evalue_id;}
	
	public String getevalue_no(){return evalue_no;}
	public void setevalue_no(String evalue_no){this.evalue_no=evalue_no;}
	
	public Date getevalue_date(){return evalue_date;}
	public void setevalue_date(Date evalue_date){this.evalue_date=evalue_date;}
	
	public Double gettotal_score(){return total_score;}
	public void settotal_score(Double total_score){this.total_score=total_score;}
	
	public String getdemo(){return demo;}
	public void setdemo(String demo){this.demo=demo;}
	
	public Double getitemOneScore(){return itemOneScore;}
	public void setitemOneScore(Double itemOneScore){this.itemOneScore=itemOneScore;}
	
	public Double getitemTwoScore(){return itemTwoScore;}
	public void setitemTwoScore(Double itemTwoScore){this.itemTwoScore=itemTwoScore;}
	
	public Double getitemThreeScore(){return itemThreeScore;}
	public void setitemThreeScore(Double itemThreeScore){this.itemThreeScore=itemThreeScore;}
	
	public Double getitemFourScore(){return itemFourScore;}
	public void setitemFourScore(Double itemFourScore){this.itemFourScore=itemFourScore;}
	
	public Double getitemFiveScore(){return itemFiveScore;}
	public void setitemFiveScore(Double itemFiveScore){this.itemFiveScore=itemFiveScore;}
	
	public EvaluEmployeeModel( String evalue_id,String evalue_no,Date evalue_date,Double total_score,
			String demo,Double itemOneScore,Double itemTwoScore,Double itemThreeScore,
			Double itemFourScore,Double itemFiveScore) {
		super();
		this.evalue_id = evalue_id;
		this.evalue_no = evalue_no;
		this.evalue_date = evalue_date;
		this.total_score = total_score;
		this.demo = demo;
		this.itemOneScore = itemOneScore;
		this.itemTwoScore = itemTwoScore;
		this.itemThreeScore = itemThreeScore;
		this.itemFourScore = itemFourScore;
		this.itemFiveScore = itemFiveScore;
	}
	public EvaluEmployeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
