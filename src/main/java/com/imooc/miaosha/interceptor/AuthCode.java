package com.imooc.miaosha.interceptor;

/**
 * 模块名称：接入层
 * 功能描述：权限类型枚举
 */
public enum AuthCode {
    
    Allow("00000", "00000", "允许访问"),
    
    /******************客户权限******************/
    
    AU0001("100001", "AU0001", "新增用户", "新增用户"),
    
    AU0002("100002", "AU0002", "删除用户", "批量删除用户");
    
    /**权限标识 */
    private String authId;
    
    /**权限编码 */
    private String authCode;
    
    /**权限名称 */
    private String authName;
    
    /**权限描述 */
    private String authDesc;
    
    /**
     * 
     * 描述：构建设备类型
     * @author mao2080@sina.com
     * @created 2017年3月22日 上午13:50:58
     * @since 
     * @param authId 权限标识
     * @param authCode 权限编码
     * @param authName 权限名称
     * @return
     */
    private AuthCode(String authId, String authCode, String authName) {
        this.authId = authId;
        this.authCode = authCode;
        this.authName = authName;
    }
    
    /**
     * 
     * 描述：构建设备类型
     * @author mao2080@sina.com
     * @created 2017年3月22日 上午13:50:58
     * @since 
     * @param authId 权限标识
     * @param authCode 权限编码
     * @param authName 权限名称
     * @param authDesc 权限描述
     * @return
     */
    private AuthCode(String authId, String authCode, String authName, String authDesc) {
        this.authId = authId;
        this.authCode = authCode;
        this.authName = authName;
        this.authDesc = authDesc;
    }
    
    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }
    
    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    @Override
    public String toString() {
        return String.format("authId:%s, authCode:%s, authName:%s, authDesc:%s", this.authId, this.authCode, this.authName, this.authDesc);
    }

}
