package com.sample.smint

import android.app.Application
import com.moengage.core.DataCenter
import com.moengage.core.MoEngage
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.MoEngageEnvironmentConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.model.AppStatus
import com.moengage.core.model.IntegrationPartner
import com.moengage.core.model.environment.MoEngageEnvironment
import com.segment.analytics.kotlin.android.Analytics
import com.segment.analytics.kotlin.core.Analytics
import com.segment.analytics.kotlin.destinations.moengage.MoEngageDestination
import com.segment.analytics.plugins.DestinationFilters

class SmintApp : Application() {

    lateinit var smintAnalytics: SmintAnalytics

    private lateinit var segmentAnalytics: Analytics

    override fun onCreate() {
        super.onCreate()
        initSegment()
        initMoEngage()
        initSmintAnalytics()
    }

    private fun initSegment() {
        segmentAnalytics = Analytics(SEGMENT_API_KEY, applicationContext) {
            trackApplicationLifecycleEvents = true
            flushAt = 3
            flushInterval = 10
            trackDeepLinks = true
        }.also {
            it.add(DestinationFilters())
            it.add(MoEngageDestination(this))
        }
    }

    private fun initMoEngage() {
        val moEngage = MoEngage.Builder(this, MOENGAGE_WORKSPACE_ID, DataCenter.DATA_CENTER_4)
            .configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.TEST))
            .configureNotificationMetaData(
                NotificationConfig(
                    smallIcon = R.mipmap.ic_launcher,
                    largeIcon = -1,
                    notificationColor = -1,
                    isMultipleNotificationInDrawerEnabled = false,
                    isBuildingBackStackEnabled = true,
                    isLargeIconDisplayEnabled = false,
                ),
            )
            .configureFcm(
                FcmConfig(isRegistrationEnabled = false),
            )
            .enablePartnerIntegration(IntegrationPartner.SEGMENT)
            .build()
        MoEngage.initialiseDefaultInstance(moEngage)
        val installType: AppStatus = runCatching {
            val pi = packageManager.getPackageInfo(packageName, 0)
            if (pi.firstInstallTime == pi.lastUpdateTime) AppStatus.INSTALL else AppStatus.UPDATE
        }.getOrElse { AppStatus.INSTALL }
        MoEAnalyticsHelper.setAppStatus(this, installType)
        MoEAnalyticsHelper.trackDeviceLocale(this)
    }

    private fun initSmintAnalytics() {
        smintAnalytics = SmintAnalytics(this, segmentAnalytics)
    }
}