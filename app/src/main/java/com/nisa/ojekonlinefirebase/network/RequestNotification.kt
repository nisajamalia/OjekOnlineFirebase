package com.nisa.ojekonlinefirebase.network

import com.google.gson.annotations.SerializedName

class RequestNotification {

    @SerializedName("to")
    var token : String? = null

    @SerializedName("data")
    var sendNotificationModel: Booking? = null
}