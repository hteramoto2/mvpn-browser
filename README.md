Micro VPN Browser
=================

A simple android browser application that illustrates Citrix MAM SDK integration to support network tunneling to company's internal network. This is the source code for Micro VPN Browser app on [Google PlayStore](https://play.google.com/store/apps/details?id=com.teramoto.microvpnbrowser).

Introduction
------------

[Citrix MAM SDK](https://docs.citrix.com/en-us/mdx-toolkit/mam-sdk-overview.html) is a set of libraries provided to Citrix Endpoint Management customers to integrate customer developed apps to take advantage of Citrix's MAM features and capabilities.

[Citrix MAM SDK Documentation](https://developer.cloud.com/citrixworkspace/mobile-application-integration)

Citrix MAM SDK Version
----------------------

Micro VPN Browser supports the latest MAM SDK version 20.10.5.1.  

Getting Started
---------------

### Publishing Public App Store Micro VPN Browser to CEM Server
Download the following MDX file and publish it as a regular public app store app, such as Secure Mail and Secure Web.

<a id="raw-url" href="https://raw.githubusercontent.com/hteramoto2/mvpn-browser/master/MicroVPNBrowser.mdx">Download MDX File</a>


### Build an app for Managed PlayStore Distribution (Private/Enterprise app)
#### Step 1
Before you can build this project, Citrix MAM SDK dependencies must be resolved manually.  The first step is to download the "MAM SDK for Android - Java" with the release date of Nov 17, 2020.

[Citrix MAM SDK Download link (Requires Citrix Account authentication)](https://www.citrix.com/downloads/citrix-endpoint-management/product-software/mdx-toolkit.html)

Unzip the downloaded MAM SDK file `MAM_SDK_for_Android_20.10.5.1.zip` to your project root folder\sdk\version.  The folder structure must look like below.

```
mvpn-browser\sdk
└── 20.10.5.1
    ├── Documentation
    ├── Library
    └── SampleCode
```

#### Step 2
Setup signing keys for .mdx file generation step.  This can be done by creating a keystore.properties file at the project root folder.  The contents may look like below.  **Note:** Do not use double quotes for these values. 

```
keyAlias=key0
keyPassword=my5ecretP(a)ssword
storeFile=../../../AndroidKeyStore/keystore-file.jks
storePassword=my5ecretP(a)ssword2
```

#### Step 3
Micro VPN Browser app's Android Package Name is already used on Google PlayStore.  Any builds without modifying the package name will be rejected from PlayStore and Managed PlayStore.  To change the Package Name, create a debug.properties file at the project root folder.  The contents may look like below.  **Note:** Do not use double quotes for these values.

```
appPackageName=com.yourcompany.yourapp
versionCode=1
versionName=1.0
```

#### Step 4
Your application is now ready to be built.  Use `gradlew build` command or use "Open an Exiting Project" in Android Studio.

Generating MDX File
-------------------
To generate MDX file for publishing as APK, run the following Gradle task.

```
    generateMdx
```

MDX file will be located under `app\build\outputs\apk\release`.

Publishing to Managed PlayStore
-------------------------------
Coming Soon!