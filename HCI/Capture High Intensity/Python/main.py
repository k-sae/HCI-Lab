from HighestIntensity import *
from tkinter import *
from tkinter.filedialog import askopenfilename

def uploadImg():
    filename = askopenfilename(filetypes=(("Image files", "*.jpg"), ("Image files", "*.png")))

    if filename != '':
        highestIntensity = HighestIntensity(filename)
        highestIntensity.showImage()


def openCamera():
    highestIntensity = HighestIntensity(1)
    highestIntensity.openStream()


window = Tk(className='Capture Highest Intensity')

uploadBtn = Button(window, text='Upload an image', command=uploadImg)
uploadBtn.pack(padx=100, pady=15)

cameraBtn = Button(window, text='Use the Camera', command=openCamera)
cameraBtn.pack(padx=100, pady=15)

window.mainloop()