package com.f6car.base.po.org;

import com.f6car.base.common.Po;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "tb_org_mac")
public class TbOrgMac extends Po {
    /**
     * 主键id
     */
    @Id
    @Column(name = "pk_id")
    @NotNull
    private BigInteger pkId;

    /**
     * mac地址
     */
    @Column(name = "mac_address")
    @NotNull
    @Size(min = 0, max = 500 , message = "长度必须在{min}和{max}之间")
    private String macAddress;

    /**
     * 组织
     */
    @Column(name = "id_own_org")
    @NotNull
    private BigInteger idOwnOrg;

    /**
     * 创建人
     */
    private BigInteger creator;

    /**
     * 创建日期
     */
    private Date creationtime;

    /**
     * 修改人
     */
    private BigInteger modifier;

    /**
     * 修改日期
     */
    private Date modifiedtime;

    /**
     * 获取主键id
     *
     * @return pk_id - 主键id
     */
    public BigInteger getPkId() {
        return pkId;
    }

    /**
     * 设置主键id
     *
     * @param pkId 主键id
     */
    public void setPkId(BigInteger pkId) {
        this.pkId = pkId;
    }

    /**
     * 获取mac地址
     *
     * @return mac_address - mac地址
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * 设置mac地址
     *
     * @param macAddress mac地址
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * 获取组织
     *
     * @return id_own_org - 组织
     */
    public BigInteger getIdOwnOrg() {
        return idOwnOrg;
    }

    /**
     * 设置组织
     *
     * @param idOwnOrg 组织
     */
    public void setIdOwnOrg(BigInteger idOwnOrg) {
        this.idOwnOrg = idOwnOrg;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public BigInteger getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(BigInteger creator) {
        this.creator = creator;
    }

    /**
     * 获取创建日期
     *
     * @return creationtime - 创建日期
     */
    public Date getCreationtime() {
        return creationtime;
    }

    /**
     * 设置创建日期
     *
     * @param creationtime 创建日期
     */
    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    /**
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public BigInteger getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(BigInteger modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改日期
     *
     * @return modifiedtime - 修改日期
     */
    public Date getModifiedtime() {
        return modifiedtime;
    }

    /**
     * 设置修改日期
     *
     * @param modifiedtime 修改日期
     */
    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }
}