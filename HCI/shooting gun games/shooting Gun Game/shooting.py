import cv2
import numpy as np
import random
import math
class LaserCapture():

    def __init__(self, stream):
        self.stream = stream

    def openStream(self):
        cap = cv2.VideoCapture(self.stream)
        circle_Number = random.randint(2,4)
        y=[450]
        acceleration = [random.randint(10,20)] 
        time = 0
        start_time = [random.randint(0,3)]
        j = circle_Number-1
        list_x = [random.randrange(50,650,100)]
        size = [100]
        flag = [True]
        while(j):
            flag.append(True)
            y.append(400)
            acceleration.append(random.randint(10,20)) 
            start_time.append(random.randint(1,5))
            list_x.append(random.randrange(50,650,100))
            size.append(100)
            j-=1
        turnoff =False
        while(True):
            if turnoff == True : 
               break
            time+=.15
            ret, frame = cap.read()
            frame = cv2.flip(frame,1)
            hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
            lower_red = np.array([0,0,255])
            upper_red = np.array([255,255,255])
            mask = cv2.inRange(hsv, lower_red, upper_red)
            (minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(mask)
            i = 0
            while(i<circle_Number):
             if time > start_time[i] :
                speed = int (acceleration[i] / (time - start_time[i]))
                if ( speed < 3) :
                    speed  = 3
                if speed<=15 or  y[i] < 1 :
                  flag[i] =False
                if   flag[i]== True :      
                    y[i]-=speed
                else:
                    y[i]+=speed
                if y[i]+size[i] > 550  :
                   turnoff =True
                   
                if maxVal >= 255 :
                   distance = math.sqrt(((maxLoc[0]-list_x[i])**2)+((maxLoc[1]-y[i])**2))
                  
                   if distance <= size[i]:
                      size[i]/=2
                   if size[i] < 25 :
                     list_x.remove(list_x[i])
                     y.remove(y[i])
                     size.remove(size[i])
                     acceleration.remove(acceleration[i])
                     start_time.remove(start_time[i])
                     circle_Number-=1
                     continue
                cv2.circle(frame,(list_x[i],int(y[i])),3,(150,0,0),int(size[i]))
             i+=1
            if  turnoff == True : 
                 cv2.putText(frame,"Game over",(30,250),cv2.FONT_HERSHEY_SIMPLEX, 3, (0,0,255), 3, cv2.LINE_AA)
                 cv2.imshow('Stream',frame)
                 cv2.waitKey(3000)
            if circle_Number == 0 : 
                turnoff = True
                cv2.putText(frame,"you win",(50,250),cv2.FONT_HERSHEY_SIMPLEX, 3, (0,0,255), 3, cv2.LINE_AA)
                cv2.imshow('Stream',frame)
                cv2.waitKey(3000)
            cv2.imshow('Stream',frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                  break

        cap.release()
        cv2.destroyAllWindows()

    def setStream(self, stream):
        self.stream = stream
