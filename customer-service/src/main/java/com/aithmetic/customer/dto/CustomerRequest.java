package com.aithmetic.customer.dto;

import com.aithmetic.order.model.Order;
import jakarta.mail.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CustomerRequest {

    // Basic Information
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Date of birth is required")
    private Date dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    // Contact Information
    @NotNull(message = "Phone number is required")
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
