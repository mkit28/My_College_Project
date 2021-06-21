#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "automation-1772c-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "uYYWo69lRVFdlKFl7VWUvYlSEHzJYj131w9eY0lu"
#define WIFI_SSID "4G Router"
#define WIFI_PASSWORD "router123@#"

const int trigP = 4;
const int echoP = 5;

long duration;
int distance;

void setup() {
  Serial.begin(9600);

  pinMode(5, OUTPUT);
  pinMode(trigP, OUTPUT);
  pinMode(echoP, INPUT);
  

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

  String ip  = WiFi.localIP().toString();
  if(WiFi.status() == WL_CONNECTED){
    
  Firebase.setString("data/nodeMcuStatus", "Connected");
  Firebase.setString("data/nodeMcuIp",ip);
  
  }else{
    
    Firebase.setString("data/nodeMcuStatus", "Not Connected");
    Firebase.set("data/nodeMcuIp","NO FOUND");
    
  }
  
}

int n = 0;
int updateValue = 0;
float ratio = 1.2; // for converting the  84 inch value in to 100 inch; 

void loop() {

    // for ultrasonic sensor
    digitalWrite(trigP, LOW);
    delayMicroseconds(2);
    
    digitalWrite(trigP, HIGH);
    delayMicroseconds(10);

    digitalWrite(trigP, LOW);

    // for facting duration and distance
    
    duration = pulseIn(echoP, HIGH);
    distance = duration * 0.034/2;

    // for set data on firebase database.

    Firebase.setInt("distance", (int)distance);
    updateValue = (int)((20 - distance) * 5);
    Serial.print(updateValue);
    Firebase.setInt("data/ultraSonicValue",updateValue);
    

}
