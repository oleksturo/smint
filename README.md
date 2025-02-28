# Segment and MoEngage integration

Android sample app

The app initializes MoEngage and Segment SDKs and tracks events into the Segment source.
No events are tracked via MoEngage SDK. Only Segment SDK is used for event tracking.
On the segment dashboard, related destination and source are set.
Segment Destination to MoEngage is attached to the same workspaceId as the app.
That destination contains an events filer with whitelited events.

It is expected that all events appear in Segment, but only whitelisted events appear in MoEngage.
However, `trips_list_click` event appear in MoEngage, despite that it is not in the whitefilter lsit.

## User data

* User ID: `smint_usid`

## Tracked events

* logged_in_event - on click on the Log in button
* logged_out_event - on click on the Log out button (appears after click in Log in)
* trips_list_click - on click on View trips button (no matter user loggd in or not)

## Events whitelist

* Application Installed
* Application Opened
* Application Updated
* Experiment Viewed
* push_notification_received
* searched_event
* logged_in_event
* logged_out_event
