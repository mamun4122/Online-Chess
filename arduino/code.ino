#include <SPI.h>
#include <Ethernet.h>
#include <EthernetUdp.h>
#include <String.h>

byte mac[] = {0x90,0xA2,0xDA,0x0D,0x8B,0x8F};
IPAddress ip(192, 168, 1, 7);
unsigned int localPort = 7777;  

char packetBuffer[UDP_TX_PACKET_MAX_SIZE];

EthernetUDP Udp;

int colPins[8]={23,25,27,29,31,33,35,37};
int rowPins[8]={22,24,26,28,30,32,34,36};
int prev[8][8]={{11,12,13,15,14,13,12,11},
                {16,16,16,16,16,16,16,16},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {6,6,6,6,6,6,6,6},
                {1,2,3,5,4,3,2,1},
};
int amar=-1,opponent=-1;
int r=8,c=8;

void setup() {
  // put your setup code here, to run once:
  for(int i=0;i<r;i++)
  {
    pinMode(rowPins[i],OUTPUT);
    digitalWrite(rowPins[i],HIGH);
  }
  for(int i=0;i<c;i++)
  {
    pinMode(colPins[i],INPUT);
    digitalWrite(colPins[i],HIGH);
  }
  /*for(int i=0;i<c;i++)digitalWrite(colPins[i],HIGH);
  for(int i=0;i<r;i++)digitalWrite(rowPins[i],HIGH);
  */
  Serial.begin(9600);

  Ethernet.begin(mac,ip);
  Udp.begin(localPort);
}


boolean sendData = false;
char data[400];
int index=0;


void loop() {
  int packetSize = Udp.parsePacket();

  if(packetSize>0)
  {
    Serial.println("recieved");
    Udp.read(packetBuffer,UDP_TX_PACKET_MAX_SIZE);
      
    if(packetBuffer[0]=='1')
    {
      sendData=true;
    }
    if(packetBuffer[0]=='0')
    {
      sendData=false;
    }
  }
  
  int arr[r][c];
  for(int row=0;row<r;row++)
  {
    digitalWrite(rowPins[row],LOW);
    for(int col=0;col<c;col++)
    {
      arr[row][col]=0;
      arr[row][col]=((digitalRead(colPins[col])==LOW)?1:0);
      //Serial.print(arr[row][col]);
    }
    //Serial.println("");
    delay(100);
    digitalWrite(rowPins[row],HIGH);
  }
  //Serial.println("sesh");
  //for(int row=0;row<r;row++)
  //{
      //digitalWrite(rowPins[row],LOW);
     // delay(1000);
      //for(int col=0;col<c;col++)
      //{
          //digitalWrite(colPins[col],HIGH);
          //delay(500);
        //  arr[row][col]=digitalRead(rowPins[row])==HIGH?1:0;
          //delay(1000);
          //digitalWrite(colPins[col],LOW);
         // delay(20);
     // }
      //digitalWrite(rowPins[row],HIGH);
  //}
  for(int i=0;i<r;i++)
  {
      for(int j=0;j<c;j++)
      {
       Serial.print(arr[i][j]);
      }
      Serial.println("");
  }
  Serial.println("sesh");

  if(sendData == true)
  {
    index = 0;
    for(int i=0;i<r;i++)
    {
      for(int j=0;j<c;j++)
      {
        if(arr[i][j]==0) data[index++] = '0';
        else data[index++] = '1';
      }
    }
    data[index] = NULL ;
    Udp.beginPacket(Udp.remoteIP(), Udp.remotePort());
    Udp.write(data);
    Udp.endPacket();
  }
  
  //delay(1000);
}
