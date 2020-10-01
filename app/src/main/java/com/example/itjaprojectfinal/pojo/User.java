package com.example.itjaprojectfinal.pojo;

public class User {


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private String gender;
    private int mainAccount;
    private int savingsAccount;

    public void setMainAccount(int mainAccount) {
        this.mainAccount = mainAccount;
    }

    public void setSavingsAccount(int savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public int getMainAccount() {
        return mainAccount;
    }

    public int getSavingsAccount() {
        return savingsAccount;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getMobile() {

        return mobile;
    }

    public String getGender() {

        return gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }
    public String getPassword() {

        return password;
    }
    public void setPassword(String password) {

        this.password = password;
    }
}
