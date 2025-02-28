package com.sample.smint

import android.app.Application
import com.moengage.core.MoECoreHelper
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.segment.analytics.kotlin.core.Analytics

class SmintAnalytics(
    private val app: Application,
    private val segmentAnalytics: Analytics
) {

    fun identify(userId: String) {
        segmentAnalytics.identify(userId)
        MoEAnalyticsHelper.setUniqueId(app, userId)
    }

    fun anonymize() {
        segmentAnalytics.flush()
        segmentAnalytics.reset()
        MoECoreHelper.logoutUser(app)
    }

    fun track(eventName: String) {
        segmentAnalytics.track(eventName)
    }
}