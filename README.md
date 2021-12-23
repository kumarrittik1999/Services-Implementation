## Intent Service

IntentService is a subclass os Service class which has a onHandleIntent method instead of onStartCommand and we don't need to create a separate thread inside
an IntentService as the code written inside the onHandleIntent method by default runs on a worker thread.

IntentService is now deprecated by Google. 
