package com.imooc.miaosha.interceptor;

import java.io.Serializable;

/**
 * 项目名称：
 * 模块名称：
 * 功能描述：
 * 创建人： mao2080@sina.com
 * 创建时间：2017年5月3日 下午6:37:11
 * 修改人： mao2080@sina.com
 * 修改时间：2017年5月3日 下午6:37:11
 */
public class ResObject implements Serializable{
    
    /**序列号*/
    private static final long serialVersionUID = 589903502110209046L;

    /**返回代码*/
    private int code = 200;
    
    /**返回信息*/
    private String desc = "Success.";
    
    /**返回数据*/
    private Object data;

    /**
     * 
     * 构建函数
     * @author mao2080@sina.com
     * @created 2017年3月24日 下午4:25:23
     * @since
     */
    public ResObject() {
        
    }
    
    /**
     * 
     * 描述：构造函数
     * @author mao2080@sina.com
     * @created 2017年4月18日 下午3:32:26
     * @since 
     * @param data 数据
     */
    public ResObject(Object data) {
        super();
        this.data = data;
    }
    
    /**
     * 
     * 构建函数
     * @author mao2080@sina.com
     * @created 2017年3月24日 下午4:25:35
     * @since 
     * @param code 返回代码
     * @param desc 返回信息
     */
    public ResObject(int code, String desc) {
        super();
        this.code = code;
        this.desc = desc;
    }

    /**
     * 
     * 构建函数
     * @author mao2080@sina.com
     * @created 2017年3月24日 下午4:25:39
     * @since 
     * @param code 返回代码
     * @param desc 返回信息
     * @param data 返回数据
     */
    public ResObject(int code, String desc, Object data) {
        super();
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}

