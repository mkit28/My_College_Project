#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "automation-1772c-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "uYYWo69lRVFdlKFl7VWUvYlSEHzJYj131w9eY0lu"
#define WIFI_SSID "4G Router"
#define WIFI_PASSWORD "router123@#"

int motor = 16;
int light1st = 2;
int light2nd = 0;
int fan1st = 4;
int fan2nd = 5;

void setup() {
  Serial.begin(9600);

  pinMode(motor, OUTPUT); // for MotorStatus realy
  pinMode(light1st, OUTPUT); // for 1st light
  pinMode(light2nd, OUTPUT);  // for 2nd light
  pinMode(fan1st, OUTPUT);    // for 1st fan
  pinMode(fan2nd, OUTPUT);    // for 2nd fan

  

  // connect to wifi.
  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

String onOf = "";

String floorFirstFan = "";
String floorFirstLed = "";
String floorSecondFan = "";
String floorSecondLed = "";

int senVal = 0;

void loop() {

    // fatching data from firebase database
  
    senVal = Firebase.getInt("data/ultraSonicValue");
    onOf = Firebase.getString("motorStatus");

   floorFirstLed = Firebase.getString("Led/FirstFloorLed");
    floorSecondLed = Firebase.getString("Led/SecondFloorLed");

  floorFirstFan = Firebase.getString("Fan/FirstFloorFan");
    floorSecondFan = Firebase.getString("Fan/SecondFloorFan");
    
    
    // code for motor contol
//
    if(senVal > 90){
      digitalWrite(motor, HIGH);
      Serial.println("Motor sen > 90 OFF");
    }
//    else if(onOf.equals("ON") || senVal < 15){
//      digitalWrite(motor, LOW);
//      
//    }
    else if(onOf.equals("ON") && senVal > 15 && senVal < 90){
      digitalWrite(motor, LOW);
    }
    else if(onOf.equals("ON")){
      digitalWrite(motor, LOW);
      Serial.println("Manualy Control ON");
    }
    else if(senVal < 15 && senVal < 90){
      digitalWrite(motor, LOW); 
      Serial.println("SenVal is sen<15 and sen<90");
    }
    else{
      digitalWrite(motor, HIGH);
      Serial.println("Motor OFF");
    }

//
//// for light's control code
//
    if(floorFirstLed.equals("ON")){
      digitalWrite(light1st, LOW);
      Serial.println("First Led ON");
    }else{
      digitalWrite(light1st, HIGH);
      Serial.println("First Led OFF");
    }

    if(floorSecondLed.equals("ON")){
      digitalWrite(light2nd, LOW);
    }else{
      digitalWrite(light2nd, HIGH);
    }

//  code for Fan's Control


    if(floorFirstFan.equals("ON")){
      digitalWrite(fan1st, LOW);
      Serial.println("First Fan ON");
    }else{
      digitalWrite(fan1st, HIGH);
      Serial.println("First Fan OFF");
    }

   if(floorSecondFan.equals("ON")){
    digitalWrite(fan2nd, LOW);
   }else{
    digitalWrite(fan2nd, HIGH);
   }
//    
}
