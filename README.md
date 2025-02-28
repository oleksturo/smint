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

## Screenshots
<img width="1080" alt="Screenshot_20250228_141352" src="https://github.com/user-attachments/assets/b52965a3-1210-45cf-b808-a41915b62c73" />
<img width="1527" alt="Screenshot 2025-02-28 at 14 01 44" src="https://github.com/user-attachments/assets/9b03fa97-f4b9-4289-8615-d1c6501b823e" />
<img width="1588" alt="Screenshot 2025-02-28 at 14 04 04" src="https://github.com/user-attachments/assets/ca817a55-2eec-4148-a296-b973caad76e3" />



