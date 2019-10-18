package zwt.example.entity;

import java.util.Date;

public class LitemallHxUser {
    private Integer id;

    private String userName;

    private String password;

    private String nickName;

    private String uuid;

    private String type;

    private String activated;

    private String disturbing;

    private String pushType;

    private String state;

    private Date createTime;

    private Date updateTime;

    public LitemallHxUser(Integer id, String userName, String password, String nickName, String uuid, String type, String activated, String disturbing, String pushType, String state, Date createTime, Date updateTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.uuid = uuid;
        this.type = type;
        this.activated = activated;
        this.disturbing = disturbing;
        this.pushType = pushType;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public LitemallHxUser() {
        super();
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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated == null ? null : activated.trim();
    }

    public String getDisturbing() {
        return disturbing;
    }

    public void setDisturbing(String disturbing) {
        this.disturbing = disturbing == null ? null : disturbing.trim();
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType == null ? null : pushType.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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