package com.aithmetic.customer.model;

import com.aithmetic.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Information
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private Date dateOfBirth;
    private String gender;

    // Address will be stored as JSON in a TEXT column
    @Column(columnDefinition = "TEXT")
    private String address;

    private String phoneNumber;

    // Account Details
    private Date accountCreationDate;
    private Date lastLoginDate;
    private String accountStatus;

    // Preferences
    private String preferredLanguage;
    private String currencyPreference;
    private String communicationPreferences;

    /* //Order History -- removing circular dependency
    @OneToMany(mappedBy = "customerId")
    private List<Order> orderHistory;*/

    // Security Information
    private boolean twoFactorAuthentication;
    private String securityQuestion;
    private String securityAnswer;

    // Social Media Integration
    private String linkedSocialMediaAccounts;

    // Subscription Information
    private boolean newsletterSubscription;
    private String membershipStatus;

    // Profile Picture
    private String profilePictureUrl;

    // Notification Preferences
    private String notificationPreferences;

    // Mobile App Preferences
    private String pushNotificationSettings;

    // Authentication Tokens
    private String oauthToken;
}
