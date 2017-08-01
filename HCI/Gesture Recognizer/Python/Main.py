
from DTW import *
import cv2
import numpy as np

recognizer = DTWRecognizer()
#Using Laser
'''
cap = cv2.VideoCapture(0)
points = np.array([], np.int32)
name = "Draw a gesture"
flag = False
score = 0
while True:
    ret, frame = cap.read()
    #frame = cv2.flip(frame, 1)

    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    lower_red = np.array([0,0,255])
    upper_red = np.array([255,255,255])

    mask = cv2.inRange(hsv, lower_red, upper_red)

    (minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(mask)

    if maxVal == 255:
        cv2.circle(frame, maxLoc, 20, (0, 0, 255), 2, cv2.LINE_AA)
        points = np.append(points, maxLoc)
        points = points.reshape(-1, 1, 2)
        cv2.polylines(frame, [points], False, (0, 0, 255), 5)
    elif points.size > 10:
        points = points.reshape(-1, 2, 1)
        result = recognizer.Recognize(list(points))
        name = result.Name
        score = result.Score
        if flag:
            if name == "Line":
                name = "Double Crossing Time"
            flag = False
        elif name == "Line":
                flag = True

        points = np.array([], np.int32)
    elif 0 < points.size < 10:
        name = "Few points, or unrecognized shape"
        score = 0
        points = np.array([], np.int32)

    cv2.putText(frame, name + '(' + ("%0.2f" % score) + ')', (20, 440), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2, cv2.LINE_AA)
    cv2.putText(frame, "Press 'q' to Quit", (20, 40), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2, cv2.LINE_AA)
    cv2.imshow('Stream', frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()

'''
#Using Mouse

drawing = False
flag = False
points = np.array([], np.int32)
def draw_circle(event, x, y, flags, param):
    global drawing, points, img, flag
     
    if event == cv2.EVENT_LBUTTONDOWN:
        drawing = True
        img = np.zeros((720, 1280, 3), np.uint8)
    elif event == cv2.EVENT_MOUSEMOVE:
        if drawing:
            points = np.append(points, (x, y))
            points = points.reshape(-1, 1, 2)
            cv2.polylines(img, [points], False, (0, 0, 255), 5)
    elif event == cv2.EVENT_LBUTTONUP:
        drawing = False
        if points.size > 10:
            points = points.reshape(-1, 2, 1)
            result = recognizer.Recognize(list(points))
            name = result.Name
            score = result.Score
            if flag:
                if name == "Line":
                    name = "Double Crossing Time"
                flag = False
            elif name == "Line":
                flag = True
            
            font = cv2.FONT_HERSHEY_SIMPLEX
            print([points])  
            cv2.putText(img, name + '(' + ("%0.2f" % score) + ')', (600-(len(name)*15 + 50), 600), font, 2, (255, 255, 255), 3, cv2.LINE_AA)
        points = np.array([], np.int32)
img = np.zeros((720, 1280, 3), np.uint8)
cv2.namedWindow("Recognizer", cv2.WND_PROP_FULLSCREEN)
cv2.setMouseCallback('Recognizer', draw_circle)
while True:
    cv2.putText(img, "Press 'q' to Quit", (20, 40), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2, cv2.LINE_AA)
    cv2.imshow('Recognizer', img)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
cv2.destroyAllWindows()
