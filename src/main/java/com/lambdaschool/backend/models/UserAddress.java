package com.lambdaschool.backend.models;

import com.lambdaschool.backend.logging.Loggable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@Loggable
@ApiModel(value = "UserAddress", description = "Location of user")
@Entity
@Table(name = "useraddress")
public class UserAddress extends Auditable
{

    @ApiModelProperty(name = "useraddressid", value = "Primary User Address ID", example = "5")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @ApiModelProperty(name = "useraddress", value = "User Address", example = "123 Street.st")
    @Column
    private String useraddress;

    @ApiModelProperty(name = "userstate", value = "User State", example = "CA")
    @Column
    private String userstate;

    @ApiModelProperty(name = "userzipcode", value = "User Zipcode", example = "93065")
    @Column
    private String userzipcode;

    public UserAddress() {
    }

    public UserAddress(String useraddress, String userstate, String userzipcode) {
        this.useraddress = useraddress;
        this.userstate = userstate;
        this.userzipcode = userzipcode;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public String getUserzipcode() {
        return userzipcode;
    }

    public void setUserzipcode(String userzipcode) {
        this.userzipcode = userzipcode;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "useraddress='" + useraddress + '\'' +
                ", userstate='" + userstate + '\'' +
                ", userzipcode='" + userzipcode + '\'' +
                '}';
    }
}
