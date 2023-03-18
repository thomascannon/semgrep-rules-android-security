<manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.android.providers.telephony"
        coreApp="true"
        android:sharedUserId="android.uid.phone">
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.INTERACT_ACROSS_USERS" />
    <uses-permission android:name="android.permission.INTERACT_ACROSS_USERS_FULL" />
    <protected-broadcast android:name="android.provider.action.EXTERNAL_PROVIDER_CHANGE" />
    <protected-broadcast android:name="android.intent.action.CONTENT_CHANGED" />
    <!-- This permission is only used to send the ACTION_EXTERNAL_PROVIDER_CHANGE intent. -->
    <uses-permission android:name="android.permission.MODIFY_PHONE_STATE" />
    <application android:process="com.android.phone"
                 android:allowClearUserData="false"
                 android:fullBackupOnly="true"
                 android:backupInForeground="true"
                 android:backupAgent="TelephonyBackupAgent"
                 android:restoreAnyVersion="true"
                 android:label="@string/app_label"
                 android:icon="@mipmap/ic_launcher_phone"
                 android:usesCleartextTraffic="true"
                 android:defaultToDeviceProtectedStorage="true"
                 android:directBootAware="true">
        <provider android:name="TelephonyProvider"
                  android:authorities="telephony"
                  android:exported="true"
                  android:singleUser="true"
                  android:multiprocess="false" />
        <!-- This is a singleton provider that is used by all users.
             A new instance is not created for each user. And the db is shared
             as well. -->
        <provider android:name="SmsProvider"
                  android:authorities="sms"
                  android:multiprocess="false"
                  android:exported="true"
                  android:singleUser="true"
                  android:readPermission="android.permission.READ_SMS" />
        <!-- This is a singleton provider that is used by all users.
             A new instance is not created for each user. And the db is shared
             as well.
             Note: We do not require a write permission as it is guarded by an app op.
              -->
        <provider android:name="MmsProvider"
                  android:authorities="mms"
                  android:multiprocess="false"
                  android:exported="true"
                  android:singleUser="true"
                  android:readPermission="android.permission.READ_SMS">
            <grant-uri-permission android:pathPrefix="/part/" />
            <grant-uri-permission android:pathPrefix="/drm/" />
        </provider>
        <!-- This is a singleton provider that is used by all users.
             A new instance is not created for each user. And the db is shared
             as well. -->
        <provider android:name="MmsSmsProvider"
                  android:authorities="mms-sms"
                  android:multiprocess="false"
                  android:exported="true"
                  android:singleUser="true"
                  android:permission="signature"
                  android:readPermission="android.permission.READ_SMS" />
        <provider
                    android:authorities="com.mwr.example.sieve.DBContentProvider"
                    android:exported="true"
                    android:multiprocess="true"
                    android:name=".DBContentProvider">
                    <path-permission
                        android:path="/Keys"
                        android:readPermission="com.mwr.example.sieve.READ_KEYS"
                        android:writePermission="com.mwr.example.sieve.WRITE_KEYS"
                    />
        </provider>
        <provider
            android:authorities="com.mwr.example.sieve.FileBackupProvider"
            android:exported="false"
            android:multiprocess="true"
            android:name=".FileBackupProvider"
        />
        <service
            android:name=".TelephonyBackupAgent$DeferredSmsMmsRestoreService"
            android:exported="false" />
    </application>
</manifest>
