import pandas as pd
from mnist import MNIST

mndata = MNIST('datasets')

#Using MNIST, with the original files
images, labels = mndata.load_training()
images2, labels2 = mndata.load_testing()

#Using Pandas, with CSV
df = pd.read_csv("datasets\mnist_train.csv")

print(df.shape)
print(len(images[0]))
