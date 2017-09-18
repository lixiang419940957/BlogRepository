package com.lix.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_blogger")
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 编号

    @Column(name = "userName")
    private String userName; // 用户名

    @Column(name = "password")
    private String password; // 密码

    @Column(name = "nickName")
    private String nickName; // 昵称

    @Column(name = "sign")
    private String sign; // 个性签名

    @Column(name = "profile")
    private String profile; // 博主信息

    @Column(name = "imageName")
    private String imageName; // 博主头像

    public Blogger() {
        super();
    }

    public Blogger(Integer id, String userName, String password, String nickName, String sign,
            String profile, String imageName) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.sign = sign;
        this.profile = profile;
        this.imageName = imageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Blogger [id=" + id + ", userName=" + userName + ", password=" + password + ", nickName="
                + nickName + ", sign=" + sign + ", profile=" + profile + ", imageName=" + imageName + "]";
    }

}
