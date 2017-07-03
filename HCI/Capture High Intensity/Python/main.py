from HighestIntensity import *
from tkinter import Tk
from tkinter.filedialog import askopenfilename

Tk().withdraw()
filename = askopenfilename(filetypes=(("Image files", "*.jpg"), ("Image files", "*.png")))

if filename != '':
    #From an image
    highestIntensity = HighestIntensity(filename)
    highestIntensity.showImage()

#From a camera
#highestIntensity.setStream(1)
#highestIntensity.openStream()