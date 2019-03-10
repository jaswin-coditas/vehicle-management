package com.vehiclemanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Owner.
 */
@Document(collection = "owner")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("middle_name")
    private String middleName;

    @Field("last_name")
    private String lastName;

    @Field("mobile_num")
    private Long mobileNum;

    @Field("alter_nate_mobile_no")
    private Long alterNateMobileNo;

    @Field("org_email")
    private String orgEmail;

    @Field("personal_email_one")
    private String personalEmailOne;

    @Field("personal_email_two")
    private String personalEmailTwo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Owner firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Owner middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Owner lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMobileNum() {
        return mobileNum;
    }

    public Owner mobileNum(Long mobileNum) {
        this.mobileNum = mobileNum;
        return this;
    }

    public void setMobileNum(Long mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Long getAlterNateMobileNo() {
        return alterNateMobileNo;
    }

    public Owner alterNateMobileNo(Long alterNateMobileNo) {
        this.alterNateMobileNo = alterNateMobileNo;
        return this;
    }

    public void setAlterNateMobileNo(Long alterNateMobileNo) {
        this.alterNateMobileNo = alterNateMobileNo;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public Owner orgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
        return this;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getPersonalEmailOne() {
        return personalEmailOne;
    }

    public Owner personalEmailOne(String personalEmailOne) {
        this.personalEmailOne = personalEmailOne;
        return this;
    }

    public void setPersonalEmailOne(String personalEmailOne) {
        this.personalEmailOne = personalEmailOne;
    }

    public String getPersonalEmailTwo() {
        return personalEmailTwo;
    }

    public Owner personalEmailTwo(String personalEmailTwo) {
        this.personalEmailTwo = personalEmailTwo;
        return this;
    }

    public void setPersonalEmailTwo(String personalEmailTwo) {
        this.personalEmailTwo = personalEmailTwo;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Owner owner = (Owner) o;
        if (owner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), owner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Owner{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", mobileNum=" + getMobileNum() +
            ", alterNateMobileNo=" + getAlterNateMobileNo() +
            ", orgEmail='" + getOrgEmail() + "'" +
            ", personalEmailOne='" + getPersonalEmailOne() + "'" +
            ", personalEmailTwo='" + getPersonalEmailTwo() + "'" +
            "}";
    }
}
