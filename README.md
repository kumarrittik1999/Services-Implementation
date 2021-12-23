## JobIntent Service

Service and IntentService (Deprecated by Google) were destroyed by the Android OS after the app goes to background. To deal with this JobIntentService came into picture.

JobIntentService is a subclass of service and has to be executed by a static "enqueueWork" method.
Other methods are "onHandleWork" which is same as "OnHandleIntent" (from the IntentService class) and "onStopCurrentWork" which returns a boolean true or false (true means we want to restart or reschedule the service after it is destroyed by OS and false is vice-versa).

JobIntentService requires uses-permission of WAKE_LOCK and service permission of BIND_JOB_SERVICE.

JobIntentService can't be explicitly closed and has to close itself using "stopSelf" or has to be destroyed by android OS.
