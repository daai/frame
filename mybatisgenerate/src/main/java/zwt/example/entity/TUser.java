package zwt.example.entity;

import java.util.Date;

public class TUser {
    private Integer userId;

    private String name;

    private Integer age;

    private Date createTime;

    private Date updateTime;

    public TUser(Integer userId, String name, Integer age, Date createTime, Date updateTime) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TUser() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}