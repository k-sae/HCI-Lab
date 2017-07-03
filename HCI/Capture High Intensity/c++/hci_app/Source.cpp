#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "iostream"
#include<Windows.h>
using namespace cv;
using namespace std;
int main()
{
	Mat image;
	double minVal;
	double maxVal;
	Point minLoc;
	Point maxLoc;
	Mat gray;
	image = imread("608.jpg", CV_LOAD_IMAGE_COLOR);
	if (!image.data)
	{
		cout << "Could not open or find the image" << endl;
		waitKey(0);
		return -1;
	}
	cvtColor(image, gray, CV_BGR2GRAY);
	minMaxLoc(gray, &minVal, &maxVal, &minLoc, &maxLoc);
	cout << "min val : " << minVal << endl;
	cout << "max val: " << maxVal << endl;
	cout << "min val : " << minLoc << endl;
	cout << "max val: " << maxLoc << endl;
	circle(image, maxLoc, 5, (255, 0, 0), 2);
	namedWindow("Display window", CV_WINDOW_AUTOSIZE);
	imshow("Display window", image);
	waitKey(0);
	return 0;
}