import cv2
import numpy as np

class LaserCapture():

    def __init__(self, stream):
        self.stream = stream

    def openStream(self):
        cap = cv2.VideoCapture(self.stream)
        while(True):
            ret, frame = cap.read()
            frame = cv2.flip(frame,1)

            hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

            lower_red = np.array([0,0,255])
            upper_red = np.array([255,255,255])

            mask = cv2.inRange(hsv, lower_red, upper_red)

            (minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(mask)

            if maxVal > 254:
                cv2.circle(frame, maxLoc, 20, (0, 0, 255), 2, cv2.LINE_AA)

            cv2.putText(frame,"Press 'q' to Quit",(20,40),cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 2, cv2.LINE_AA)
            cv2.imshow('Stream',frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

        cap.release()
        cv2.destroyAllWindows()

    def setStream(self, stream):
        self.stream = stream
