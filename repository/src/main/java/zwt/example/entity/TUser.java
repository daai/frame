package zwt.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
@TableName("t_user")
public class TUser {
    @TableId(value = "user_id",type= IdType.AUTO)
    private Integer userId;

    private String name;

    private Integer age;

    private Date createTime;

    private Date updateTime;

    public TUser(String name, Integer age, Date createTime, Date updateTime) {
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