import pandas as pd
from mnist import MNIST
import os

path = os.path.join('datasets', 'mnist')

mndata = MNIST(path)

#Using MNIST, with the original files
images, labels = mndata.load_training()
images2, labels2 = mndata.load_testing()

print(len(images[0]))
