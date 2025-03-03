# Segment and MoEngage integration

Android sample app

The app initializes MoEngage and Segment SDKs and tracks events into the Segment source.
No events are tracked via MoEngage SDK. Only Segment SDK is used for event tracking.
On the segment dashboard, related destination and source are set.
Segment Destination to MoEngage is attached to the same workspaceId as the app.
Destination filters are added to the MoEngage destination as a whitelist events.

It is expected that all events appear in Segment, and only whitelisted events appear in MoEngage.

`trips_list_click` event does not appear in MoEngage because of enabled filter 
in the Segment Destination.

## User data

* User ID: `smint_usid_1`

## All Tracked events

* logged_in_event - on click on the Log in button. Appears in both: Segment and MoEngage
* logged_out_event - on click on the Log out button. Appears in both: Segment and MoEngage
* trips_list_click - on click on View trips button. Appears only in Segment

## Events whitelist in the Destination

* Application Installed
* Application Opened
* Application Updated
* Experiment Viewed
* push_notification_received
* searched_event
* logged_in_event
* logged_out_event
