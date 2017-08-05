import os
import numpy as np
from mnist import MNIST
import matplotlib.pyplot as plt
from sklearn.neighbors import KNeighborsClassifier


def imagesTo2D(images, size):
    imgs = [[[]]]*size
    for i in range(len(images)):
        imgs[i] = np.reshape(images[i], (-1, 28))

    return imgs

path = os.path.join('datasets', 'mnist')
mndata = MNIST(path)

#Using MNIST, with the original files
images, labels = mndata.load_training()
images2, labels2 = mndata.load_testing()

#Print the first digit
print(labels[0])
print(mndata.display(images[0]))

#Convert the whole images to 2D array
imgs = imagesTo2D(images, len(images))

knn = KNeighborsClassifier()
#knn.fit(image, labels[:28])

#Plot the first 15 digits
images_and_labels = list(zip(imgs, labels))
plt.figure(figsize=(5, 5))
for index, (image, label) in enumerate(images_and_labels[:15]):
    plt.subplot(3, 5, index + 1)
    plt.axis('off')
    plt.imshow(image, cmap=plt.cm.gray_r, interpolation='nearest')
    plt.title('%i' % label)
plt.show()
