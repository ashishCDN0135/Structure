package com.structure.app.structuremvp.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ForgotPasswordResponseBean extends BaseResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    class Result {

        @SerializedName("employee_id")
        @Expose
        private Integer employeeId;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("user_account_id")
        @Expose
        private Integer userAccountId;
        @SerializedName("unit_name")
        @Expose
        private Integer unitName;


        public Integer getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

    }

}
