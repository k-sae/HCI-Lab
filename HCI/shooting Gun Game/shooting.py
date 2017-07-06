import cv2
import numpy as np
import random
import math
class LaserCapture():

    def __init__(self, stream):
        self.stream = stream

    def openStream(self):
        cap = cv2.VideoCapture(self.stream)
        circle_Number = random.randint(1,3)
        y=[400]
        acceleration = [random.randint(30,40)] 
        time = 0
        j = circle_Number-1
        list_x = [random.randrange(60,660,120)]
        size = [100]
        flag = [True]
        while(j):
            flag.append(True)
            y.append(400)
            acceleration.append(random.randint(30,40)) 
            m = random.randrange(60,660,120)
            if m not in list_x :
               list_x.append(m)
               size.append(100)
               j-=1
        turnoff =False
        while(True):
            if turnoff == True : 
                break
            time+=.25
            ret, frame = cap.read()
            frame = cv2.flip(frame,1)
            hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
            lower_red = np.array([0,0,255])
            upper_red = np.array([255,255,255])
            mask = cv2.inRange(hsv, lower_red, upper_red)
            (minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(mask)
            i = 0
            while(i<circle_Number):
             speed = acceleration[i] / time
             if speed<=30:
                flag[i] =False

             if   flag[i]== True :      
                  y[i]-=speed
             else:
                y[i]+=speed
             if(y[i] > 450) :
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
                 circle_Number-=1
                 continue
             cv2.circle(frame,(list_x[i],int(y[i])),3,(150,0,0),int(size[i]))
             i+=1
            cv2.imshow('Stream',frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

        cap.release()
        cv2.destroyAllWindows()

    def setStream(self, stream):
        self.stream = stream
