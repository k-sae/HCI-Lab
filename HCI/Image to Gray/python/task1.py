import cv2
from tkinter import Tk
from tkinter.filedialog import askopenfilename
Tk().withdraw()
filepath = askopenfilename(filetypes=(("Image files", "*.jpg"), ("Image files", "*.png")))

if filepath != '':
    filename = (filepath.split('/')[-1]).split('.')[0]
    image =cv2.imread(filepath)
    gray_image =cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
    cv2.imshow(filename,image)
    cv2.imshow(filename+'_gray',gray_image)
    cv2.waitKey(0)
    cv2.destroyAllWindows()