package com.aithmetic.customer.dto;

import com.aithmetic.order.model.Order;
import jakarta.mail.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String customerId;

    // Basic Information
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String gender;

    // Contact Information
    private String phoneNumber;
    private Address address;

    // Account Details
    private Date accountCreationDate;
    private Date lastLoginDate;
    private String accountStatus;

    // Preferences
    private String preferredLanguage;
    private String currencyPreference;
    private String communicationPreferences;

    // Order History
    private List<Order> orderHistory;

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
