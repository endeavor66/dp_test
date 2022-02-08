package com.example.dp.vo;

public class ResponseVO {
    /**
     * 调用是否成功:0=sucess,1=fail
     */
    private int status;

    /**
     * 返回的提示信息
     */
    private String msg;

    /**
     * 内容
     */
    private Object data;

    public static ResponseVO buildSuccess(){
        ResponseVO response=new ResponseVO();
        response.setStatus(0);
        return response;
    }

    public static ResponseVO buildSuccess(Object content){
        ResponseVO response=new ResponseVO();
        response.setData(content);
        response.setStatus(0);
        return response;
    }

    public static ResponseVO buildFailure(String message){
        ResponseVO response=new ResponseVO();
        response.setStatus(1);
        response.setMsg(message);
        System.out.println(message);
        return response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
