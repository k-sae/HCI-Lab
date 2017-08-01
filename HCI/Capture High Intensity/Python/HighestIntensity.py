import cv2

class HighestIntensity():

    def __init__(self, stream):
        self.stream = stream

    #To get the highest intensity of a live stream
    def openStream(self):
        cap = cv2.VideoCapture(self.stream)
        while(True):
            ret, frame = cap.read()
            frame = cv2.flip(frame,1)
            gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            gray = cv2.GaussianBlur(gray, (111,111), 0)
            (minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(gray)
            cv2.circle(frame, maxLoc, 40, (0, 0, 255), 3, cv2.LINE_AA)
            cv2.putText(frame,"Press 'q' to Quit",(20,40),cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255), 2, cv2.LINE_AA)
            cv2.imshow('Stream',frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

        cap.release()
        cv2.destroyAllWindows()

    #To get the highest intensity of an image
    def showImage(self):
        img = cv2.imread(self.stream)
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        gray = cv2.GaussianBlur(gray, (111,111), 0)
        (minVal, maxVal, minLoc, maxLoc) = cv2.minMaxLoc(gray)
        cv2.circle(img, maxLoc, 40, (0, 0, 255), 3, cv2.LINE_AA)
        cv2.imshow('Highest Intensity',img)
        cv2.waitKey(0)
        cv2.destroyAllWindows()

    #To change the current stream
    def setStream(self, stream):
        self.stream = stream