//step 1 Create a new button in your layout file in the res/layout directory of your project
<Button
    android:id="@+id/button_open_auth_link"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@android:color/transparent"
            android:outlineAmbientShadowColor="@android:color/transparent"
            android:outlineSpotShadowColor="@android:color/transparent"
            >
<ImageView
        android:id="@+id/img_auth_link"
                android:layout_width="300dp"
                android:layout_height="wrap_content"
                android:src="@drawable/otpless_button"
                />
</Button>


//use this code for opening the auth link
        Button button = (Button) findViewById(R.id.button_open_auth_link);
        myButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("YOUR_AUTH_LINK"));
        startActivity(browserIntent);
        }
        });


//step 2 Make your Activity browsable for otpless intent

<activity android:name=".MainActivity"
        android:launchMode="singleTop"
        android:exported="true">
<intent-filter>
<action android:name="android.intent.action.VIEW" />
<category android:name="android.intent.category.DEFAULT" />
<category android:name="android.intent.category.BROWSABLE" />
<data android:scheme="COMPANY" android:host="otpless" />
</intent-filter>
</activity>

//step 3 Parse intent when user redirected back to your app from whatsapp

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Intent that started this activity
        parseOtplessIntent(getIntent());
        }

@Override
protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Get the Intent that started this activity
        parseOtplessIntent(intent);
        }

private void parseOtplessIntent(Intent intent){
        // Checking intent contains value and otpless intent only
        if (intent != null && intent.getData() != null && intent.getData().getHost().contains("otpless")){
        Uri redirectUri = intent.getData();
        String waId = redirectUri.getQueryParameter("waID");
        if(waId != null && !waId.isEmpty()){
        // Use waId to verify your user authentication with otpless server
        // put your verification code here...
        }
        }
        }