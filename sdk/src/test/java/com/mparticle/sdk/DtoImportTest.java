package com.mparticle.sdk;

import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DtoImportTest {

    //region Test JSON
    private String rokuJson = "\n" +
            "{\n" +
            "   \"type\":\"event_processing_request\",\n" +
            "   \"id\":\"aaa7880b-c91d-47b5-b2fe-f14de2a4a38c\",\n" +
            "   \"timestamp_ms\":1459892017807,\n" +
            "   \"source_id\":\"aaa4e9b5-fa71-45e0-a358-e0530f7bdde9\",\n" +
            "   \"source_channel\":\"native\",\n" +
            "   \"device_application_stamp\":\"aaad6f3b-3e7e-4411-baa8-b059fd07b8b3\",\n" +
            "   \"account\":{\n" +
            "      \"account_id\":123,\n" +
            "      \"account_settings\":{\n" +
            "         \"endpointUrl\":\"\",\n" +
            "         \"channelName\":\"@mbenua\"\n" +
            "      }\n" +
            "   },\n" +
            "   \"user_identities\":[\n" +
            "      {\n" +
            "         \"type\":\"other2\",\n" +
            "         \"encoding\":\"raw\",\n" +
            "         \"value\":\"234\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"type\":\"other3\",\n" +
            "         \"encoding\":\"raw\",\n" +
            "         \"value\":\"456\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"type\":\"other4\",\n" +
            "         \"encoding\":\"raw\",\n" +
            "         \"value\":\"789\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"user_attributes\":{\n" +
            "      \"$FirstName\":\"Mel\",\n" +
            "      \"$LastName\":\"Bun\",\n" +
            "      \"$Gender\":\"F\",\n" +
            "      \"$State\":\"FL\",\n" +
            "      \"$Country\":\"US\",\n" +
            "      \"$City\":\"Boca Raton\",\n" +
            "      \"$Zip\":\"33431\"\n" +
            "   },\n" +
            "   \"runtime_environment\":{\n" +
            "      \"sdk_version\":\"4.10.1\",\n" +
            "      \"type\":\"roku\",\n" +
            "      \"is_debug\":true,\n" +
            "      \"identities\":[\n" +
            "         {\n" +
            "            \"type\":\"roku_advertising_id\",\n" +
            "            \"encoding\":\"raw\",\n" +
            "            \"value\":\"aaabc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"type\":\"roku_publisher_id\",\n" +
            "            \"encoding\":\"raw\",\n" +
            "            \"value\":\"bbbbc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "         },\n" +
            "         {\n" +
            "            \"type\":\"roku_device_id\",\n" +
            "            \"encoding\":\"raw\",\n" +
            "            \"value\":\"cccbc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "         }\n" +
            "      ],\n" +
            "      \"build_id\":\"M4=rc20\",\n" +
            "      \"brand\":\"RRROKU\",\n" +
            "      \"product\":\"Roku\",\n" +
            "      \"name\":\"My Awesome Roku\",\n" +
            "      \"manufacturer\":\"ROKU\",\n" +
            "      \"os_version\":\"4.1.1\",\n" +
            "      \"model\":\"ROKUUUUU\",\n" +
            "      \"screen_height\":736,\n" +
            "      \"screen_width\":1280,\n" +
            "      \"screen_dpi\":160,\n" +
            "      \"country\":\"US\",\n" +
            "      \"locale_language\":\"EN\",\n" +
            "      \"locale_country\":\"US\",\n" +
            "      \"timezone_offset\":-5,\n" +
            "      \"android_sdk_level\":0,\n" +
            "      \"application_name\":\"App Name\",\n" +
            "      \"application_version\":\"1.2.001-dbg\",\n" +
            "      \"application_package\":\"com.mparticle.TravelApp\",\n" +
            "      \"client_ip_address\":\"192.168.1.15\",\n" +
            "      \"install_referrer\":\"utm_source%3Dgoogle%26utm_medium%3Dcpc%26utm_term%3Drunning%252Bshoes%26utm_content%3Dlogolink%26utm_campaign%3Dspring_sale\"\n" +
            "   },\n" +
            "   \"events\":[\n" +
            "      {\n" +
            "         \"type\":\"session_start\",\n" +
            "         \"id\":\"fa0ece33-9b62-44f5-88d4-6278f1fd9d08\",\n" +
            "         \"timestamp_ms\":1459892017807,\n" +
            "         \"source_id\":\"0e1b9ea5-936f-486b-981f-e13d12d61140\",\n" +
            "         \"session_id\":-1096377938905861812,\n" +
            "         \"location\":{\n" +
            "            \"latitude\":40.7142,\n" +
            "            \"longitude\":74.0064,\n" +
            "            \"accuracy\":15.0\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"type\":\"session_end\",\n" +
            "         \"id\":\"520ffd0e-14a0-434c-a11a-8cf2a93c45e2\",\n" +
            "         \"timestamp_ms\":1459892017807,\n" +
            "         \"source_id\":\"1b7602e1-344e-4ae8-ab31-41941eb61346\",\n" +
            "         \"session_id\":-1096377938905861812,\n" +
            "         \"location\":{\n" +
            "            \"latitude\":40.7142,\n" +
            "            \"longitude\":74.0064,\n" +
            "            \"accuracy\":15.0\n" +
            "         },\n" +
            "         \"session_length_ms\":336000\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    private String firetvJson = "\n" +
            "{\n" +
            "   \"type\":\"event_processing_request\",\n" +
            "   \"id\":\"aaa7880b-c91d-47b5-b2fe-f14de2a4a38c\",\n" +
            "   \"timestamp_ms\":1459892017807,\n" +
            "   \"source_id\":\"aaa4e9b5-fa71-45e0-a358-e0530f7bdde9\",\n" +
            "   \"source_channel\":\"native\",\n" +
            "   \"device_application_stamp\":\"aaad6f3b-3e7e-4411-baa8-b059fd07b8b3\",\n" +
            "   \"account\":{\n" +
            "      \"account_id\":345,\n" +
            "      \"account_settings\":{\n" +
            "         \"endpointUrl\":\"\",\n" +
            "         \"channelName\":\"@mbenua\"\n" +
            "      }\n" +
            "   },\n" +
            "   \"user_identities\":[\n" +
            "      {\n" +
            "         \"type\":\"email\",\n" +
            "         \"encoding\":\"raw\",\n" +
            "         \"value\":\"foo@bar.com\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"user_attributes\":{\n" +
            "      \"$FirstName\":\"Mel\",\n" +
            "      \"$LastName\":\"Ben\",\n" +
            "      \"$Gender\":\"F\",\n" +
            "      \"$State\":\"FL\",\n" +
            "      \"$Country\":\"US\",\n" +
            "      \"$City\":\"Boca Raton\",\n" +
            "      \"$Zip\":\"33431\"\n" +
            "   },\n" +
            "   \"runtime_environment\":{\n" +
            "      \"sdk_version\":\"4.10.1\",\n" +
            "      \"type\":\"firetv\",\n" +
            "      \"is_debug\":true,\n" +
            "      \"identities\":[\n" +
            "         {\n" +
            "            \"type\":\"fire_advertising_id\",\n" +
            "            \"encoding\":\"raw\",\n" +
            "            \"value\":\"aaabc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "         }\n" +
            "      ],\n" +
            "      \"build_id\":\"M4=rc20\",\n" +
            "      \"brand\":\"AMAZON\",\n" +
            "      \"product\":\"Fire TV Stick\",\n" +
            "      \"name\":\"My FireTV Stick\",\n" +
            "      \"manufacturer\":\"AAAmazon\",\n" +
            "      \"os_version\":\"4.1.1\",\n" +
            "      \"model\":\"Fire TV Stick 2.0\",\n" +
            "      \"country\":\"US\",\n" +
            "      \"locale_language\":\"EN\",\n" +
            "      \"locale_country\":\"US\",\n" +
            "      \"timezone_offset\":-5,\n" +
            "      \"application_name\":\"App Name\",\n" +
            "      \"application_version\":\"1.2.001-dbg\",\n" +
            "      \"application_package\":\"com.mparticle.TravelApp\",\n" +
            "      \"client_ip_address\":\"192.168.1.15\",\n" +
            "      \"install_referrer\":\"utm_source%3Dgoogle%26utm_medium%3Dcpc%26utm_term%3Drunning%252Bshoes%26utm_content%3Dlogolink%26utm_campaign%3Dspring_sale\"\n" +
            "   },\n" +
            "   \"events\":[\n" +
            "      {\n" +
            "         \"type\":\"session_start\",\n" +
            "         \"id\":\"fa0ece33-9b62-44f5-88d4-6278f1fd9d08\",\n" +
            "         \"timestamp_ms\":1459892017807,\n" +
            "         \"source_id\":\"0e1b9ea5-936f-486b-981f-e13d12d61140\",\n" +
            "         \"session_id\":-1096377938905861812,\n" +
            "         \"location\":{\n" +
            "            \"latitude\":40.7142,\n" +
            "            \"longitude\":74.0064,\n" +
            "            \"accuracy\":15.0\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    private String xboxJson = "\n" +
            "{\n" +
            "   \"type\":\"event_processing_request\",\n" +
            "   \"id\":\"aaa7880b-c91d-47b5-b2fe-f14de2a4a38c\",\n" +
            "   \"timestamp_ms\":1459892017807,\n" +
            "   \"source_id\":\"aaa4e9b5-fa71-45e0-a358-e0530f7bdde9\",\n" +
            "   \"source_channel\":\"native\",\n" +
            "   \"device_application_stamp\":\"aaad6f3b-3e7e-4411-baa8-b059fd07b8b3\",\n" +
            "   \"account\":{\n" +
            "      \"account_id\":345,\n" +
            "      \"account_settings\":{\n" +
            "         \"endpointUrl\":\"\",\n" +
            "         \"channelName\":\"@mbenua\"\n" +
            "      }\n" +
            "   },\n" +
            "   \"user_identities\":[\n" +
            "      {\n" +
            "         \"type\":\"other\",\n" +
            "         \"encoding\":\"raw\",\n" +
            "         \"value\":\"456\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"user_attributes\":{\n" +
            "      \"$FirstName\":\"Mel\",\n" +
            "      \"$LastName\":\"Ben\",\n" +
            "      \"$Gender\":\"F\",\n" +
            "      \"$State\":\"FL\",\n" +
            "      \"$Country\":\"US\",\n" +
            "      \"$City\":\"Boca Raton\",\n" +
            "      \"$Zip\":\"33431\"\n" +
            "   },\n" +
            "   \"runtime_environment\":{\n" +
            "      \"sdk_version\":\"4.10.1\",\n" +
            "      \"type\":\"xbox\",\n" +
            "      \"is_debug\":true,\n" +
            "      \"identities\":[\n" +
            "         {\n" +
            "            \"type\":\"microsoft_advertising_id\",\n" +
            "            \"encoding\":\"raw\",\n" +
            "            \"value\":\"aaabc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "         },\n" +
            "\t\t {\n" +
            "            \"type\":\"microsoft_publisher_id\",\n" +
            "            \"encoding\":\"raw\",\n" +
            "            \"value\":\"cccbc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "         }\n" +
            "      ],\n" +
            "      \"build_id\":\"M4=rc20\",\n" +
            "      \"brand\":\"Xbox\",\n" +
            "      \"product\":\"Xbox One X 1\",\n" +
            "      \"name\":\"My Xbox\",\n" +
            "      \"manufacturer\":\"Microsoft\",\n" +
            "      \"os_version\":\"4.1.1\",\n" +
            "      \"model\":\"Xbox One Version 5\",\n" +
            "      \"country\":\"US\",\n" +
            "      \"locale_language\":\"EN\",\n" +
            "      \"locale_country\":\"US\",\n" +
            "      \"timezone_offset\":-5,\n" +
            "      \"application_name\":\"App Name\",\n" +
            "      \"application_version\":\"1.2.001-dbg\",\n" +
            "      \"application_package\":\"com.mparticle.TravelApp\",\n" +
            "      \"client_ip_address\":\"192.168.1.15\",\n" +
            "      \"install_referrer\":\"utm_source%3Dgoogle%26utm_medium%3Dcpc%26utm_term%3Drunning%252Bshoes%26utm_content%3Dlogolink%26utm_campaign%3Dspring_sale\"\n" +
            "   },\n" +
            "   \"events\":[\n" +
            "      {\n" +
            "         \"type\":\"session_start\",\n" +
            "         \"id\":\"fa0ece33-9b62-44f5-88d4-6278f1fd9d08\",\n" +
            "         \"timestamp_ms\":1459892017807,\n" +
            "         \"source_id\":\"0e1b9ea5-936f-486b-981f-e13d12d61140\",\n" +
            "         \"session_id\":-1096377938905861812,\n" +
            "         \"location\":{\n" +
            "            \"latitude\":40.7142,\n" +
            "            \"longitude\":74.0064,\n" +
            "            \"accuracy\":15.0\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}";
    //endregion

    @Test
    public void TestRokuDto() {
        MessageSerializer ser = new MessageSerializer();
        try {
            EventProcessingRequest req = ser.deserialize(rokuJson, EventProcessingRequest.class);
            Assert.assertNotNull(req);

            Assert.assertEquals(RuntimeEnvironment.Type.ROKU, req.getRuntimeEnvironment().getType());

            Boolean other2 = false, other3 = false, other4 = false;
            for(UserIdentity id : req.getUserIdentities()) {
                other2 |= id.getType() == UserIdentity.Type.OTHER2;
                other3 |= id.getType() == UserIdentity.Type.OTHER3;
                other4 |= id.getType() == UserIdentity.Type.OTHER4;
            }

            Assert.assertTrue( other2 && other3 && other4);

            RokuRuntimeEnvironment env =  (RokuRuntimeEnvironment)req.getRuntimeEnvironment();

            Boolean rokuAdId = false, rokuPubId = false, rokuId = false;
            for (DeviceIdentity id : env.getIdentities()) {
                rokuAdId |= id.getType() == DeviceIdentity.Type.ROKU_ADVERTISING_ID;
                rokuPubId |= id.getType() == DeviceIdentity.Type.ROKU_PUBLISHER_ID;
                rokuId |= id.getType() == DeviceIdentity.Type.ROKU_DEVICE_ID;

                if (id.getType() == DeviceIdentity.Type.ROKU_ADVERTISING_ID) {
                    Assert.assertEquals("aaa", id.getValue().substring(0, 3));
                }

                if (id.getType() == DeviceIdentity.Type.ROKU_PUBLISHER_ID) {
                    Assert.assertEquals("bbb", id.getValue().substring(0, 3));
                }

                if (id.getType() == DeviceIdentity.Type.ROKU_DEVICE_ID) {
                    Assert.assertEquals("ccc", id.getValue().substring(0, 3));
                }
            }

            Assert.assertTrue( rokuAdId && rokuPubId && rokuId);
        }
        catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TestFireTvDto() {
        MessageSerializer ser = new MessageSerializer();
        try {
            EventProcessingRequest req = ser.deserialize(firetvJson, EventProcessingRequest.class);
            Assert.assertNotNull(req);

            Assert.assertEquals(RuntimeEnvironment.Type.FIRETV, req.getRuntimeEnvironment().getType());

            Boolean email = false;
            for(UserIdentity id : req.getUserIdentities()) {
                email |= id.getType() == UserIdentity.Type.EMAIL;
            }
            Assert.assertTrue( email);

            FireTVRuntimeEnvironment env =  (FireTVRuntimeEnvironment)req.getRuntimeEnvironment();

            Boolean fireAdId = false;
            for (DeviceIdentity id : env.getIdentities()) {
                fireAdId |= id.getType() == DeviceIdentity.Type.FIRE_ADVERTISING_ID;
                if (fireAdId) {
                    Assert.assertEquals("aaa", id.getValue().substring(0, 3));
                }
            }

            Assert.assertTrue( fireAdId);
        }
        catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void TestXboxDto() {
        MessageSerializer ser = new MessageSerializer();
        try {
            EventProcessingRequest req = ser.deserialize(xboxJson, EventProcessingRequest.class);
            Assert.assertNotNull(req);

            Assert.assertEquals(RuntimeEnvironment.Type.XBOX, req.getRuntimeEnvironment().getType());

            Boolean other = false;
            for(UserIdentity id : req.getUserIdentities()) {
                other |= id.getType() == UserIdentity.Type.OTHER;
            }

            Assert.assertTrue( other);

            XboxRuntimeEnvironment env =  (XboxRuntimeEnvironment)req.getRuntimeEnvironment();

            Boolean msAdId = false, msPubId = false;
            for (DeviceIdentity id : env.getIdentities()) {
                msAdId |= id.getType() == DeviceIdentity.Type.MICROSOFT_ADVERTISING_ID;
                msPubId |= id.getType() == DeviceIdentity.Type.MICROSOFT_PUBLISHER_ID;

                if (id.getType() == DeviceIdentity.Type.MICROSOFT_ADVERTISING_ID) {
                    Assert.assertEquals("aaa", id.getValue().substring(0, 3));
                }

                if (id.getType() == DeviceIdentity.Type.MICROSOFT_PUBLISHER_ID) {
                    Assert.assertEquals("ccc", id.getValue().substring(0, 3));
                }
            }

            Assert.assertTrue( msAdId && msPubId);
        }
        catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
}
