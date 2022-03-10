package com.microsoft.campaign.mobileuetsdk.logsending;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by yaqi@microsoft.com
 * Description:
 */
public final class MicrosoftAnalytics {

    public static class Event {
        public static final String ADD_PAYMENT_INFO = "add_payment_info";
        public static final String ADD_TO_CART = "add_to_cart";
        public static final String ADD_TO_WISHLIST = "add_to_wishlist";
        public static final String PURCHASE = "purchase";
        public static final String GOTO_NEW_ACTIVITY = "goto_new_activity";
        public static final String APP_OPEN = "app_open";
        public static final String BEGIN_CHECKOUT = "begin_checkout";
        public static final String CAMPAIGN_DETAILS = "campaign_details";
        public static final String GENERATE_LEAD = "generate_lead";
        public static final String JOIN_GROUP = "join_group";
        public static final String LEVEL_END = "level_end";
        public static final String LEVEL_START = "level_start";
        public static final String LEVEL_UP = "level_up";
        public static final String LOGIN = "login";
        public static final String POST_SCORE = "post_score";
        public static final String PRESENT_OFFER = "present_offer";
        public static final String PURCHASE_REFUND = "purchase_refund";
        public static final String SEARCH = "search";
        public static final String SELECT_CONTENT = "select_content";
        public static final String SHARE = "share";
        public static final String SIGN_UP = "sign_up";
        public static final String SPEND_VIRTUAL_CURRENCY = "spend_virtual_currency";
        public static final String TUTORIAL_BEGIN = "tutorial_begin";
        public static final String TUTORIAL_COMPLETE = "tutorial_complete";
        public static final String UNLOCK_ACHIEVEMENT = "unlock_achievement";
        public static final String VIEW_ITEM = "view_item";
        public static final String VIEW_ITEM_LIST = "view_item_list";
        public static final String VIEW_SEARCH_RESULTS = "view_search_results";
        public static final String EARN_VIRTUAL_CURRENCY = "earn_virtual_currency";
        public static final String REMOVE_FROM_CART = "remove_from_cart";
        public static final String CHECKOUT_PROGRESS = "checkout_progress";
        public static final String SET_CHECKOUT_OPTION = "set_checkout_option";

        protected Event() {
        }

    }

    public static class Param {
        public static final String ACHIEVEMENT_ID = "achievement_id";
        public static final String CHARACTER = "character";
        public static final String TRAVEL_CLASS = "travel_class";
        public static final String CONTENT_TYPE = "content_type";
        public static final String CURRENCY = "currency";
        public static final String COUPON = "coupon";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String EXTEND_SESSION = "extend_session";
        public static final String FLIGHT_NUMBER = "flight_number";
        public static final String GROUP_ID = "group_id";
        public static final String ITEM_CATEGORY = "item_category";
        public static final String ITEM_ID = "item_id";
        public static final String ITEM_LOCATION_ID = "item_location_id";
        public static final String ITEM_NAME = "item_name";
        public static final String LOCATION = "location";
        public static final String LEVEL = "level";
        public static final String LEVEL_NAME = "level_name";
        public static final String QUANTITY = "quantity";
        public static final String VALUE = "value";
        public static final String PRICE = "price";
        public static final String TAX = "tax";
        public static final String SHIPPING = "shipping";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String SEARCH_TERM = "search_term";
        public static final String ORIGIN = "origin";
        public static final String DESTINATION = "destination";
        public static final String NUMBER_OF_NIGHTS = "number_of_nights";
        public static final String NUMBER_OF_ROOMS = "number_of_rooms";
        public static final String NUMBER_OF_PASSENGERS = "number_of_passengers";
        public static final String ECOMM_TOTALVALUE = "ecomm_totalvalue";
        public static final String PRODID = "prodid";
        public static final String PAGETYPE = "pagetype";
        public static final String TRAVEL_ORIGINID = "travel_originid";
        public static final String TRAVEL_DESTID = "travel_destid";
        public static final String TRAVEL_STARTDATE = "travel_startdate";
        public static final String TRAVEL_ENDDATE = "travel_enddate";
        public static final String TRAVEL_TOTALVALUE = "travel_totalvalue";
        public static final String LOCATION_ID = "location_id";
        public static final String BRAND = "brand";
        public static final String CATEGORY = "category";
        public static final String CREATIVE_NAME = "creative_name";
        public static final String CREATIVE_SLOT = "creative_slot";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ITEMS = "items";
        protected Param() {}
    }

    public static void logEvent(Context context, String events, Bundle bundle){

    }

}
