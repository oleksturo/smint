# Segment and MoEngage integration

Android sample app

App does initial MoEngage and Segment SDKs initialization and track events into Segment only.
On the segment dashboard related destination and source are set.
Segment Destination to MoEngage is attached to the same workspaceId as the app.
That destination contains events filer with whitelited events.

It is expected that all events appears in Segment buy only whitelisted events appears in MoEngage.

# Hardcoded User ID

`smint_usid`

## Tracked events

* logged_in_event - on click on the Log in button
* logged_out_event - on click on the Log out button (appears after click in Log in)
* trips_list_click - on click on View trips button (no matter user loggd in or not)

## Events whitelist

Application Installed
Application Opened
Application Updated
Experiment Viewed
push_notification_received
searched_event
logged_in_event
logged_out_event
