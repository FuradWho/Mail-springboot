package com.spoof.mailspringboot.pojo;

import java.io.Serializable;

/**
 *
 * @author 13375
 */
public class User  implements Serializable {

    private Integer userId;
    private String userPassword;
    private String userName;
    private String userSalt;
    private String anonymousName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getAnonymousName() {
        if (null != anonymousName) {
            return anonymousName;
        }
        if (null == userName) {
            anonymousName = null;
        } else if (userName.length() <= 1) {
            anonymousName = "*";
        } else if (userName.length() == 2) {
            anonymousName = userName.substring(0, 1) + "*";
        } else {
            char[] cs = userName.toCharArray();
            for (int i = 1; i < cs.length - 1; i++) {
                cs[i] = '*';
            }
            anonymousName = new String(cs);
        }
        return anonymousName;
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userSalt='" + userSalt + '\'' +
                ", anonymousName='" + anonymousName + '\'' +
                '}';
    }
}



