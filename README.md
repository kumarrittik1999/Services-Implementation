## Service in Android

Service class helps us with doing background tasks. It dosent have a UI. By default whatever we write inside the Service class runs on the background thread, 
thus we need to create a worker thread by ourselves to run all tasks in background without blocking the UI.

## Bound Service

Services in android that are bound to other components of the application such as activities, content providers or other services are known as Bound Services.
Bound Services can continuously send data to the foreground and help in updating the UI smoothly.

We need to override the onBind method which returns an IBinder interface. This IBinder interface gives the bound component the access to the Service class
and from there intercommunication can happen. We need to create an inner class which either implements the IBinder interface or extends the Binder (abstract) class.

The activity or any component which is bound to a service needs to implement ServiceConnection API which exposes two overridden method onServiceConnected
and onServiceDisconnected

In onServiceConnected method we can take instance of binder and hence we can take instance of service class to establish the intercommunication.


## Intent Service

IntentService is a subclass os Service class which has a onHandleIntent method instead of onStartCommand and we don't need to create a separate thread inside
an IntentService as the code written inside the onHandleIntent method by default runs on a worker thread.

IntentService is now deprecated by Google. 
