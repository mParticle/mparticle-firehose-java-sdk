package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.audienceprocessing.Audience;
import com.mparticle.sdk.model.audienceprocessing.UserAttributeAudienceEvent;
import com.mparticle.sdk.model.audienceprocessing.UserProfile;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.Identity;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;
import com.mparticle.sdk.model.registration.Account;

import java.util.Arrays;
import java.util.HashMap;

public class Sample {

    public static Account generateAccount() {
        Account account = new Account();
        account.setAccountId(123456);
        account.setAccountSettings(new HashMap<String, String>());
        account.getAccountSettings().put("Example String Setting", "Example Setting Value");
        account.getAccountSettings().put("Example Boolean Setting", "false");
        account.getAccountSettings().put("Example Integer Setting", "123");

        return account;
    }

    public static UserProfile generateUserProfiles() {
        UserProfile profile = new UserProfile();
        profile.setDeviceIdentities(Arrays.asList(
                generateDeviceIdentity(DeviceIdentity.Type.IOS_ADVERTISING_ID),
                generateDeviceIdentity(DeviceIdentity.Type.IOS_VENDOR_ID),
                generateDeviceIdentity(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID),
                generateDeviceIdentity(DeviceIdentity.Type.ANDROID_ID)));


        profile.setUserIdentities(Arrays.asList(
                generateUserIdentity(UserIdentity.Type.EMAIL),
                generateUserIdentity(UserIdentity.Type.CUSTOMER)));

        profile.setAudiences(Arrays.asList(
                generateAddAudience(),
                generateAttributeUpdateAudience(),
                generateDeleteAudience()
        ));

        return profile;
    }

    public static Audience generateAddAudience() {
        Audience audience = new Audience();
        audience.setAudienceId(456);
        audience.setAudienceName("Example Audience Name");
        audience.setAudienceAction(Audience.AudienceAction.ADD);
        audience.setAudienceSubscriptionSettings(new HashMap<String, String>());
        audience.getAudienceSubscriptionSettings().put("Example Audience-specific setting", "Example Value");

        return audience;
    }


    public static Audience generateAttributeUpdateAudience() {
        Audience audience = new Audience();
        audience.setAudienceId(789);
        audience.setAudienceName("Example Audience Name 2");
        audience.setAudienceAction(Audience.AudienceAction.ATTRIBUTE_UPDATE);
        audience.setAudienceSubscriptionSettings(new HashMap<String, String>());
        audience.getAudienceSubscriptionSettings().put("Example Audience-specific setting", "Example Value 2");

        UserAttributeAudienceEvent attr1 = new UserAttributeAudienceEvent();
        attr1.setAction(UserAttributeAudienceEvent.AttributeAction.UPSERT);
        attr1.setKey("Some Key");
        attr1.setValue("9999999");

        UserAttributeAudienceEvent attrTag = new UserAttributeAudienceEvent();
        attrTag.setAction(UserAttributeAudienceEvent.AttributeAction.UPSERT);
        attrTag.setKey("HighScorer");

        UserAttributeAudienceEvent attrDelete = new UserAttributeAudienceEvent();
        attrTag.setAction(UserAttributeAudienceEvent.AttributeAction.DELETE);
        attrTag.setKey("Churned");

        audience.setUserAttributes(Arrays.asList(attr1, attrTag, attrDelete));

        return audience;
    }

    public static Audience generateDeleteAudience() {
        Audience audience = new Audience();
        audience.setAudienceId(654);
        audience.setAudienceName("Example Audience Name 4");
        audience.setAudienceAction(Audience.AudienceAction.DELETE);
        audience.setAudienceSubscriptionSettings(new HashMap<String, String>());
        audience.getAudienceSubscriptionSettings().put("Example Audience-specific setting", "Example Value 4");

        return audience;
    }

    public static DeviceIdentity generateDeviceIdentity(DeviceIdentity.Type type) {
        switch(type) {
            case IOS_ADVERTISING_ID:
                return new DeviceIdentity(type, Identity.Encoding.RAW, "66b728c2-f9a4-4d87-82ef-ce07414fe3f7");
            case IOS_VENDOR_ID:
                return new DeviceIdentity(type, Identity.Encoding.RAW, "97b826c2-ab80-4876-a184-db36cc39b1ee");
            case GOOGLE_ADVERTISING_ID:
                return new DeviceIdentity(type, Identity.Encoding.RAW, "31a22ef0-f119-48d4-b009-a217a26a862a");
            case ANDROID_ID:
                return new DeviceIdentity(type, Identity.Encoding.RAW, "a0504a8cfa15ce2c");

            default:
                return new DeviceIdentity(type, Identity.Encoding.RAW, "1234567890");
        }
    }

    public static UserIdentity generateUserIdentity(UserIdentity.Type type) {
        switch(type) {
            case EMAIL:
                return new UserIdentity(type, Identity.Encoding.MD5, "e179e95c00e7718ab4a23840f992ea63");
            case CUSTOMER:
                return new UserIdentity(type, Identity.Encoding.MD5, "4739c5c11d833bb199c16ff95a92b267");

            default:
                return new UserIdentity(type, Identity.Encoding.RAW, "1234567890");
        }
    }
}
