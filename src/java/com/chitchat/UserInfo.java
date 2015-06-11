package com.chitchat;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserInfo {

    private String name;
    @Id
    private String username;
    private String passwd;
    private String mobileNumber;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String city;
    private String gender;
    private String nationality;
    private String rolename;
    private String photo;

    public UserInfo() {
    }

    public UserInfo(String name, String username, String passwd, String mobileNumber, String email, Date dateOfBirth, String city, String gender, String nationality, String rolename,String photo) {
        this.name = name;
        this.username = username;
        this.passwd = passwd;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.gender = gender;
        this.nationality = nationality;
        this.rolename = rolename;
        this.photo=photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDob() {
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        String str = fmt.format(dateOfBirth);
        return str;
    }

}
