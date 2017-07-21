//INCLUDES
#include <SoftwareSerial.h>
//#define _SS_MAX_RX_BUFF 256 // RX buffer size //BEFORE WAS 64
// OBJECTS

SoftwareSerial esp8266Module(3,4); // RX, TX
//const char lowercase[26] PROGMEM = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//const char uppercase[26] PROGMEM = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
//const char special[25] PROGMEM = {' ','!','"','#','$','%','&','\'','(',')','*','+','`','-','_','.','\\','/',']','[','^','}','{','|','~'};

// GLOBALS
String network = "Kareem";
String password = "123456789cX";
String weatherCode = "Your Weather code"; //BBC Weather code for Leicester - 2644668
String tempWifi;
String DiscoveredNetworks[12] = {"", "", "", "", "", "", "", "", "", "", "", ""};
int findWifiStart = 0;
bool breakOut = false;
int wifiStatus = 1;
int selectedNetwork = 0;
boolean foundWifi  = false;
boolean newNameWifi = false;
int wifiNumber = 0;
int light;
//This is the value where the light is off
//Calibrate this value accurately
int nightVal = 100;
String motionState;
// Temp variables
String val1 = "----------";
String val2 = "----------";
String val3 = "----------";
String val4 = "----------";
String val5 = "----------";
int loopNum = 0;
int runningnow = 0;
void setup() {
  // Serial Start
  pinMode(A0, INPUT);
  pinMode(2, INPUT);
  digitalWrite(2, LOW);
  pinMode(8, OUTPUT); //ESP8266 RESET PIN
  digitalWrite(8, HIGH);  //ESP8266 RESET PIN
  Serial.begin(9600);
  esp8266Module.begin(9600);
  attachInterrupt(0, menu, FALLING);
  delay(5000);
  // Serial end
}

void loop() {
  // picture loop
  light = analogRead(A0);
  
  motionState = digitalRead(2) == 1 ? "true" : "false";

  String GetRequest="/search?";
  if(light <= nightVal){
    GetRequest+="LightState=true";
  }else{
    GetRequest+="LightState=false";
  }
  
  GetRequest+="&MotionState=";
  GetRequest+=motionState;
  
  runEsp8266("192.168.43.196", GetRequest);

  //findWifiNetworks();

}

// START DISPLAY// END DISPLAY

// MAIN ESP8266 FUNCTION
void runEsp8266(String website, String page) {
  // 0 need to reset or beginning of loop
  // 1 reset complete check wifi mode
  // 2 wifi mode is 3, now check network connection
  // 3 If not connected connect to network
  // 4 request page from server
  // 5 unlink from server after request
  // 6 close network connection
  switch (wifiStatus) {
    case 0:    // 0 need to reset or beginning of loop
      Serial.println("TRYING esp8266Reset");
      esp8266Reset();
      break;
    case 1:    // 1 reset complete check wifi mode
      Serial.println("TRYING changeWifiMode");
      changeWifiMode();
      break;
    case 2:    // 2 wifi mode is 3, now check network connection
      Serial.println("TRYING checkWifiStatus");
      checkWifiStatus();
      break;
    case 3:    // 3 If not connected connect to network
      Serial.println("TRYING connectToWifi");
      connectToWifi();
      //connectToWifi("networkIdetifier", "networkPassword");
      break;
    case 4:    // 4 request page from server
      Serial.println("TRYING getPage");
      getPage(website, page);
      //getPage(website, page, "?num=", "3", "&num2=", "2000");
      break;
    case 5:    // 5 unlink from server after request
      Serial.println("TRYING unlinkPage");
      unlinkPage();
      break;
  }
}

// END MAIN ESP8266 FUNCTION

// 0 - RESET
bool esp8266Reset() {
  esp8266Module.println(F("AT+RST"));
  delay(7000);
  if (esp8266Module.find("OK"))
  {
    val1 = F("-RESET-");
    wifiStatus = 1;
    return true;
  }
  else
  {
    val1 = F("-FAILED-");
    wifiStatus = 0;
    return false;
  }
}
// END RESET

// 1 - CHANGE MODE
bool changeWifiMode()
{
  esp8266Module.println(F("AT+CWMODE?"));
  delay(5000);
  if (esp8266Module.find("3"))
  {
    val1 = F("Wifi Mode is 3");
    wifiStatus = 2;
    return true;
  }
  else
  {
    esp8266Module.println(F("AT+CWMODE=3"));
    delay(5000);
    if (esp8266Module.find("no change") || esp8266Module.find("OK"))
    {
      val1 = F("Wifi Mode is 3");
      wifiStatus = 2;
      return true;
    }
    else
    {
      val1 = F("Wifi Mode failed");
      wifiStatus = 0;
      return false;
    }
  }

}
// END CHANGE MODE

// 2 - CHECK WIFI NETWORK STATUS
bool checkWifiStatus() {
  esp8266Module.println("AT+CWJAP?");
  delay(5000);
  if (esp8266Module.find(":")) {
    Serial.println("WIFI NETWORK CONNECTED");
    val1 = F("WIFI:");
    val1 += esp8266Module.readStringUntil('\n');
    wifiStatus = 4;
    return true;
  }
  else
  {
    wifiStatus = 3;
    return false;
  }
}
// END CHECK WIFI NETWORK STATUS

// 3 - CONNECT TO WIFI
bool connectToWifi() {
  String cmd = F("AT+CWJAP=\"");
  cmd += network;
  cmd += F("\",\"");
  cmd += password;
  cmd += F("\"");
  esp8266Module.println(cmd);
  delay(5000);
  if (esp8266Module.find("OK"))
  {
    Serial.println("CONNECTED TO WIFI");

    val1 = F("CONNECTED TO WIFI");
    wifiStatus = 4;
    return true;
  }
  else
  {
    wifiStatus = 0;
    return false;
  }
}
// optional function that accepts the network ID and password as variables
bool connectToWifi(String networkId, String networkPassword) {
  String cmd = F("AT+CWJAP=\"");
  cmd += networkId;
  cmd += F("\",\"");
  cmd += networkPassword;
  cmd += F("\"");
  esp8266Module.println(cmd);
  delay(5000);
  if (esp8266Module.find("OK"))
  {
    Serial.println("CONNECTED TO WIFI");

    val1 = F("CONNECTED TO WIFI");
    wifiStatus = 4;
    return true;
  }
  else
  {
    wifiStatus = 0;
    return false;
  }
}
// END CONNECT TO WIFI NETWORK

// 4 - GET PAGE
bool getPage(String website, String page) {
  String cmd = F("AT+CIPSTART=\"TCP\",\"");
  cmd += website;
  cmd += F("\",4444");
  esp8266Module.println(cmd);
  delay(500);
  if (esp8266Module.find("Linked"))
  {
    Serial.print("Connected to server");

  }
  cmd =  "GET ";
  cmd += page;
  cmd += " HTTP/1.0\r\n";
  cmd += "Host:";
  cmd += website;
  cmd += "\r\n\r\n";
 Serial.print(cmd);
 String r = "AT+CIPSEND=";
  r += cmd.length();
  esp8266Module.println(r);
  if (esp8266Module.find(">"))
  {
    Serial.println("found > prompt - issuing GET request");
    esp8266Module.println(cmd);
  }
  else
  {
    wifiStatus = 5;
    Serial.println("No '>' prompt received after AT+CPISEND");
    val1 = F("Failed request, retrying...");
    return false;
  }
  Serial.println(esp8266Module.readString());
  while (esp8266Module.available() > 0)
  {
    esp8266Module.read();
  }

  if (esp8266Module.find("*")) {
    String tempMsg = esp8266Module.readStringUntil('\n');
    val2 = splitToVal(tempMsg, "@", "|");
    val3 = splitToVal(tempMsg, "+", "@");
    String piecetemp = splitToVal(tempMsg, "|", "$");
    val5 = splitToVal(tempMsg, "$", "^");
    val4 = splitToVal(tempMsg, "^", "~");
    int peice = piecetemp.toInt();
    Serial.println(val2);
    Serial.println(val3);
    Serial.println(val4);
    Serial.println(val5);
    Serial.println(piecetemp);

    wifiStatus = 5;
    return true;
  }
  else {
    wifiStatus = 5;
    return false;
  }

}

// optional getPage function that accepts user variables
bool getPage(String website, String page, String urlVariableName1, String variable1, String urlVariableName2, String variable2) {
  String cmd = F("AT+CIPSTART=\"TCP\",\"");
  cmd += website;
  cmd += F("\",4444");
  esp8266Module.println(cmd);
  delay(500);
  if (esp8266Module.find("Linked"))
  {
    Serial.print("Connected to server");

  }
  cmd =  "GET ";
  cmd += page;
  cmd += urlVariableName1;  // something like ?num=
  cmd += variable1;
  cmd += urlVariableName2;  // something like &num2=
  cmd += variable2;
  cmd += " HTTP/1.0\r\n";
  cmd += "Host:";
  cmd += website;
  cmd += "\r\n\r\n";
  Serial.println(cmd);
  esp8266Module.print("AT+CIPSEND=");
  esp8266Module.println(cmd.length());
  delay(500);

  if (esp8266Module.find(">"))
  {
    Serial.println("found > prompt - issuing GET request");
    esp8266Module.println(cmd);
  }
  else
  {
    wifiStatus = 5;
    Serial.println("No '>' prompt received after AT+CPISEND");
    val1 = F("Failed request, retrying...");
    return false;
  }

  while (esp8266Module.available() > 0)
  {
    esp8266Module.read();
  }

  if (esp8266Module.find("*")) {
    String tempMsg = esp8266Module.readStringUntil('\n');
    val2 = splitToVal(tempMsg, "@", "|");
    val3 = splitToVal(tempMsg, "+", "@");
    String piecetemp = splitToVal(tempMsg, "|", "$");
    val4 = splitToVal(tempMsg, "$", "^");
    val5 = splitToVal(tempMsg, "^", "~");
    int peice = piecetemp.toInt();
    Serial.println(val2);
    Serial.println(val3);
    Serial.println(val4);
    Serial.println(val5);
    wifiStatus = 5;
    return true;
  }
  else {
    wifiStatus = 5;
    return false;
  }

}

// END GET PAGE

// 5 - UNLINK
bool unlinkPage() {
  esp8266Module.println(F("AT+CIPCLOSE"));
  delay(500);
  if (esp8266Module.find("Unlink"))
  {
    val1 = F("UNLINKED");
    wifiStatus = 0;
    loopNum++;
    return true;
  }
  else
  {
    wifiStatus = 4;
    return false;
  }
}
// END UNLINK

// 6 - CLOSE NETWORK  --- This is not ready
bool closeNetwork() {
  esp8266Module.println(F("AT+CWQAP"));
  delay(5000);
  if (esp8266Module.find("OK"))
  {
    val1 = F("NETWORK DISCONNECTED");
    wifiStatus = 0;
    return true;
  }
  else
  {
    wifiStatus = 4;
    return false;
  }
}
// END CLOSE NETWORK

// 7 - FIND WIFI NETWORKS  --- This is not ready
bool findWifiNetworks() {
  char character;
  if (runningnow == 1 && wifiNumber < 20) {
    if (esp8266Module.available() > 0) {
      //delay(10);
      character = esp8266Module.read();
      //Serial.print(character);
      
    
    if(!foundWifi){
    //Serial.write(temp);
     if (character == ':') {
       foundWifi = true;      
     }
    }
    else{
      if (character == '\n') {
        DiscoveredNetworks[wifiNumber] = splitWifi(tempWifi);
        Serial.println(DiscoveredNetworks[wifiNumber]);
        tempWifi = "";
        Serial.println(wifiNumber);
        wifiNumber++;
        foundWifi = false;
      }
      else {
        tempWifi.concat(character);
      }
    }
    }

    
  }
  else {
    if (wifiNumber < 1 && runningnow == 0) {
      runningnow = 1;
      esp8266Module.println("AT+CWLAP");
    }
  }
return true;
}
// END FIND WIFI NETWORKS

// SPLIT UP STRINGS
String splitToVal(String inputString, String delimiter, String endChar) {
  String tempString = "";
  int from;
  int to;
  for (int i = 0; i < inputString.length(); i++) {
    if (inputString.substring(i, i + 1) == delimiter) {
      from = i + 1;
    }
    if (inputString.substring(i, i + 1) == endChar) {
      to = i;
    }
  }
  tempString = inputString.substring(from, to);
  return tempString;
}
// END SPLIT UP STRINGS

// SPLIT UP STRINGS --- This is not ready
String splitWifi(String inputWifi) {
  int firstListItem = inputWifi.indexOf("\"");
  int secondListItem = inputWifi.indexOf("\"", firstListItem + 1 );
  //String indexWifi = "+CWLAP:("; // unhappy face
  //String findName = "\"";
  return inputWifi.substring(firstListItem + 1, secondListItem);

}
// END SPLIT UP STRINGS

// FLOAT TO STRING

String floatToString(float inputFloat) {
  char CharBuffer[10];
  dtostrf(inputFloat, 1, 2, CharBuffer);
  String floatString = String(CharBuffer);
  return floatString;
}
// END FLOAT TO STRING
void menu(){
  if(findWifiNetworks() == true){
  val2 = DiscoveredNetworks[selectedNetwork];
  }
  
}

