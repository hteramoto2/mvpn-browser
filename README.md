Micro VPN Browser
=================

A simple Android browser application that illustrates Citrix MAM SDK integration to support network tunneling to company's internal network: this is the source code for Micro VPN Browser app on [Google Play Store](https://play.google.com/store/apps/details?id=com.teramoto.microvpnbrowser).

Introduction
------------

[Citrix MAM SDK](https://docs.citrix.com/en-us/mdx-toolkit/mam-sdk-overview.html) is a set of libraries provided to Citrix Endpoint Management customers to integrate customer-developed apps to take advantage of Citrix's MAM features and capabilities.

[Citrix MAM SDK Documentation](https://developer.cloud.com/citrixworkspace/mobile-application-integration)

Citrix MAM SDK Version
----------------------

Micro VPN Browser supports the latest MAM SDK version 20.10.5.1.  

Getting Started
---------------

### Publishing Public App Store Micro VPN Browser to CEM Server
Download the MDX file (provided in the link below) and publish it as a regular public app store MDX app, similarly to Secure Mail and Secure Web.

<a id="raw-url" href="https://raw.githubusercontent.com/hteramoto2/mvpn-browser/master/MicroVPNBrowser.mdx">Download MDX File</a>


### Build an app for Managed Play Store Distribution (Private/Enterprise app)
#### Step 1
Before you can build this project, Citrix MAM SDK dependencies must be resolved manually.  The first step is to download the "MAM SDK for Android - Java" with the release date of Nov 17, 2020.

[Citrix MAM SDK Download link (Requires Citrix Account authentication)](https://www.citrix.com/downloads/citrix-endpoint-management/product-software/mdx-toolkit.html)

Unzip the downloaded MAM SDK file `MAM_SDK_for_Android_20.10.5.1.zip` to your project root folder\sdk\version.  The folder structure must look precisely as shown below.

```
mvpn-browser\sdk
└── 20.10.5.1
    ├── Documentation
    ├── Library
    └── SampleCode
```

#### Step 2
Set up signing keys for .mdx file generation step.  This can be done by creating a keystore.properties file at the project root folder.  The contents may look as below.  **Note:** Do not use double quotes for these values. 

```
keyAlias=key0
keyPassword=my5ecretP(a)ssword
storeFile=../../../AndroidKeyStore/keystore-file.jks
storePassword=my5ecretP(a)ssword2
```

#### Step 3
Micro VPN Browser app's Android Package Name is already used on the Google Play Store.  Any builds without modifying the package name will be rejected from the Play Store and the Managed Play Store.  To change the Package Name, create a debug.properties file at the project root folder.  The contents may look as below.  **Note:** Do not use double quotes for these values.

```
appPackageName=com.yourcompany.yourapp
versionCode=1
versionName=1.0
```

#### Step 4
Your application is now ready to be built.  Use the `gradlew build` command or use "Open an Exiting Project" in Android Studio.

#### Generating MDX File
To generate an MDX file for publishing as APK, run the following Gradle task:

```
    generateMdx
```

The MDX file will be located under `app\build\outputs\apk\release`.

#### Publishing to Managed Play Store
After generating the APK file and MDX file from the previously performed Gradle task, you will need to upload the app to the Managed Google Play Store and configure MDX on the CEM server.

##### Uploading APK to Managed Google Play Store
First, upload your APK file to Managed Google Play Store.  (Assumption: you have already configured Android Enterprise on the CEM Server.  If not, please refer to: [Android Enterprise](https://docs.citrix.com/en-us/citrix-endpoint-management/device-management/android/android-enterprise.html))

![Managed Play Store](docs/EnterpriseApp.PNG "Begin Enterprise publishing flow")

Start the APK upload process by clicking on the Upload button for Android Enterprise.
![Managed Play Store](docs/Ent-configure.PNG "Upload Enterprise app")

The Google Play iframe will open, allowing you to upload a new app or update an existing app.  Click the plus icon on the bottom right hand corner to upload a new app.
![Managed Play Store](docs/PublishApp.PNG "Upload Enterprise app to Google Play Store")

Upload your APK and provide the necessary details for publishing to the Managed Google Play Store.
![Managed Play Store](docs/UploadManagedPlayStore.PNG "Upload APK to Managed Play Store")

It may take a few minutes for the app to become available on the Managed Google Play Store.  Leave/exit the Enterprise App Publishing flow.  Do not worry, the app would have been already uploaded to Google; Citrix does not need to know this nor record it in their databases.
![Managed Play Store](docs/ExitEnterprisePublishing.PNG "Exit enterprise publishing flow")

Now, publish your MDX file to the CEM server.
![Managed Play Store](docs/PublishMDX.PNG "Publish MDX file")

Upload and finish publishing your MDX file.
![Managed Play Store](docs/UploadMDX.PNG "Upload MDX file and configure")

Troubleshooting Guide
----------------------

Coming Soon! 
Here is a preview:

1. How to resolve Proxy issue caused by legacy Apache http clients.
2. How to support API level 30
3. How to support Android App Bundles
4. How to support Play App Signing

    and more!
