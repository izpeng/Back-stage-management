package com.cy.pj.common.vo;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 通过此对象对控制层数据进行封装,主要目的
 * 是业务的执行结果添加状态信息.
 */
@Data
@NoArgsConstructor
public class JsonResult implements Serializable{
	private static final long serialVersionUID = -5766977494287555486L;
	/**状态码*/
	private int state=1;//1表示SUCCESS,0表示ERROR
	/**状态信息*/
	private String message="ok";
	/**正确数据*/
	private Object data;
    public JsonResult(String message){
    	this.message=message;
    }
    public JsonResult(Object data){
    	this.data=data;
    }
    public JsonResult(Throwable e) {
    	this.state=0;
    	this.message=e.getMessage();
    }
}





