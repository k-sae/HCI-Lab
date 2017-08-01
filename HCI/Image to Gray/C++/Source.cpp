#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "iostream"
#include<Windows.h>
using namespace cv;
using namespace std;
int main()
{
	Mat image, gray;
	image = imread("608.jpg", CV_LOAD_IMAGE_COLOR);
	if (!image.data)
	{
		cout << "Could not open or find the image" << endl;
		waitKey(0);
		return -1;
	}
	cvtColor(image, gray, CV_BGR2GRAY);
	namedWindow("Display window", CV_WINDOW_AUTOSIZE);
	imshow("Display window", image);
	namedWindow("Result window", CV_WINDOW_AUTOSIZE);
	imshow("Result window", gray);
	waitKey(0);
	return 0;
}