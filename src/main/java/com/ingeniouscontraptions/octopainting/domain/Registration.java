package com.ingeniouscontraptions.octopainting.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The registration.
 * 
 * @author Arthur Noseda
 */
public class Registration implements Serializable {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phoneNumber;

    private final List<Entry> entries;

    private final String transferOfInformation;

    /**
     * Constructs the registration.
     * 
     * @param id                    the id
     * @param firstName             the first name of the entrant
     * @param lastName              the last name of the entrant
     * @param email                 the email of the entrant
     * @param phoneNumber           the phone number of the entrant
     * @param entries               the list of entries
     * @param transferOfInformation the transfer of information
     */
    public Registration(Long id, String firstName, String lastName, String email, String phoneNumber, List<Entry> entries, String transferOfInformation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.entries = entries;
        this.transferOfInformation = transferOfInformation;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the first name of the entrant.
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the entrant.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the email of the entrant.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone number of the entrant
     * 
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the list of entries.
     * 
     * @return the list of entries
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Returns the transfer of information
     *
     * @return the transfer of information
     */
    public String getTransferOfInformation() {
        return transferOfInformation;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(phoneNumber)
                .append(entries)
                .append(transferOfInformation)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Registration rhs = (Registration) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .append(firstName, rhs.firstName)
                .append(lastName, rhs.lastName)
                .append(email, rhs.email)
                .append(phoneNumber, rhs.phoneNumber)
                .append(entries, rhs.entries)
                .append(transferOfInformation, rhs.transferOfInformation)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Registration{id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + ", phoneNumber=" + phoneNumber
                + ", entries=" + entries
                + ", transferOfInformation=" + transferOfInformation + "}";
    }

}
