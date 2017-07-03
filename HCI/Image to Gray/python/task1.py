import cv2
image =cv2.imread('landscap.jpg')
gray_image =cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
cv2.imshow('lanscap',image)
cv2.imshow('landscap_gray',gray_image)
cv2.waitKey(0)
cv2.destroyAllWindows()