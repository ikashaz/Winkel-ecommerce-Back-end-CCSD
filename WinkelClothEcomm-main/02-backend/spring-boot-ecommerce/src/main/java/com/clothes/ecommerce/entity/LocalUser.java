package com.clothes.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * User for authentication with our website.
 */
@Setter
@Getter
@Entity
@Table(name = "local_user")
public class LocalUser {

    /** Unique id for the user.
     * -- GETTER --
     *  Gets the id.
     *
     *
     * -- SETTER --
     *  Sets the id.
     *
     @return The id.
      * @param id The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The username of the user.
     * -- GETTER --
     *  Gets the username.
     *
     *
     * -- SETTER --
     *  Sets the username.
     *
     @return The username.
      * @param username The username.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /** The encrypted password of the user.
     * -- GETTER --
     *  Gets the encrypted password.
     *
     *
     * -- SETTER --
     *  Sets the password, this should be pre-encrypted.
     *
     @return The password.
      * @param password The password.
     */
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    private String password;
    /** The email of the user.
     * -- GETTER --
     *  Gets the email.
     *
     *
     * -- SETTER --
     *  Sets the email.
     *
     @return The email.
      * @param email The email.
     */
    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;
    /** The first name of the user.
     * -- GETTER --
     *  Gets the first name.
     *
     *
     * -- SETTER --
     *  Sets the first name.
     *
     @return The first name.
      * @param firstName The first name.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /** The last name of the user.
     * -- GETTER --
     *  Gets the last name.
     *
     *
     * -- SETTER --
     *  Sets the last name.
     *
     @return The last name.
      * @param lastName The last name.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /** The addresses associated with the user. */


    /**
     * Gets the addresses.
     * @return The addresses.
     */


    /**
     * Sets the addresses.
     * @param addresses The addresses.
     */


}
